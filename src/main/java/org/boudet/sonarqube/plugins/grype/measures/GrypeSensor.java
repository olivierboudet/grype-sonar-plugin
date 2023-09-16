package org.boudet.sonarqube.plugins.grype.measures;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.packageurl.MalformedPackageURLException;
import com.github.packageurl.PackageURL;
import com.github.packageurl.PackageURLBuilder;
import org.boudet.sonarqube.plugins.grype.metrics.GrypeMetrics;
import org.boudet.sonarqube.plugins.grype.model.dependencies.DependenciesReport;
import org.boudet.sonarqube.plugins.grype.model.dependencies.Dependency;
import org.boudet.sonarqube.plugins.grype.model.grype.Cvs;
import org.boudet.sonarqube.plugins.grype.model.grype.GrypeReport;
import org.boudet.sonarqube.plugins.grype.rules.CveRuleDefinition;
import org.boudet.sonarqube.plugins.grype.settings.GrypeConfiguration;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.rule.Severity;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.api.batch.sensor.issue.NewIssue;
import org.sonar.api.batch.sensor.issue.NewIssueLocation;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;
import org.sonar.api.utils.log.Profiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class GrypeSensor implements Sensor {
    private static final Logger LOGGER = Loggers.get(GrypeSensor.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final List<GradleDependencyResolver> dependencyResolvers = new ArrayList<>();

    @Override
    public void execute(SensorContext sensorContext) {
        Profiler profiler = Profiler.create(LOGGER);
        profiler.startInfo("Grype process report");

        try {
            Optional<GrypeReport> analysis = parseReport(sensorContext);
            Optional<DependenciesReport> dependenciesReport = parseDependenciesReport(sensorContext);

            if (analysis.isPresent()) {
                Iterable<InputFile> gradleFiles = sensorContext.fileSystem().inputFiles(sensorContext.fileSystem().predicates().hasFilename("build.gradle"));
                gradleFiles.forEach(file -> {
                    GradleDependencyResolver resolver = new GradleDependencyResolver(file);
                    dependencyResolvers.add(resolver);
                });
                createIssues(sensorContext, analysis.get(), dependenciesReport.orElse(null));
            } else {
                LOGGER.info("No Grype report found.");
            }
            saveHtmlReport(sensorContext);
        } catch (ParsingException e) {
            LOGGER.error("Grype report can not be parsed, aborting grype analysis.", e);
        }

        profiler.stopInfo();
    }

    private void createIssues(SensorContext sensorContext, GrypeReport analysis, DependenciesReport dependenciesReport) {
        Map<String, PackageURL> depsMap = initDepsMap(dependenciesReport);

        analysis.getMatches().forEach(match -> {
            Severity severity = switch (match.getVulnerability().getSeverity()) {
                case ("Negligible"), ("Unknown"):
                    yield Severity.INFO;
                case ("Low"):
                    yield Severity.MINOR;
                case ("Medium"):
                    yield Severity.MAJOR;
                case ("High"):
                    yield Severity.BLOCKER;
                case ("Critical"):
                    yield Severity.CRITICAL;
                default:
                    throw new IllegalStateException("Unexpected value: " + match.getVulnerability().getSeverity());
            };
            NewIssue issue = sensorContext
                    .newIssue()
                    .forRule(CveRuleDefinition.RULE_CVE)
                    .overrideSeverity(severity);

            Cvs maxCvs = match.getVulnerability().getCvss().stream().max(Comparator.comparing(cvs -> cvs.getMetrics().getBaseScore())).orElse(null);
            String title = String.format("%s v%s| CVSS Score: %f | Type: %s",
                    match.getArtifact().getName(),
                    match.getArtifact().getVersion(),
                    maxCvs != null ? maxCvs.getMetrics().getBaseScore() : 0f,
                    match.getArtifact().getType()
            );

            Optional<GradleMatch> firstMatchedtextRange = Optional.empty();
            try {
                PackageURL packageURL = new PackageURL(match.getArtifact().getPurl());
                PackageURL rootDep = findRootDep(packageURL, depsMap);

                firstMatchedtextRange = this.dependencyResolvers.stream()
                        .map(resolver -> resolver.findLine(rootDep))
                        .filter(Objects::nonNull)
                        .findFirst();

            } catch (MalformedPackageURLException e) {
                LOGGER.warn("Malformed package url {}", match.getArtifact().getPurl(), e);
            }

            NewIssueLocation location = issue.newLocation()
                    .message(title);
            if(firstMatchedtextRange.isPresent()) {
                location.on(firstMatchedtextRange.get().getInputFile());
                location.at(firstMatchedtextRange.get().getTextRange());

            } else {
                location.on(sensorContext.project());
            }
            issue.at(location);
            issue.save();
        });
    }

    private static PackageURL findRootDep(PackageURL matchPUrl, Map<String, PackageURL> depsMap) {
        PackageURL rootDep = depsMap.get(matchPUrl.getNamespace() + ":" + matchPUrl.getName() + ":" + matchPUrl.getVersion());
        if (rootDep != null) {
            LOGGER.info("The root dependency for {}/{}:{} is {}", matchPUrl.getNamespace(), matchPUrl.getName(), matchPUrl.getVersion(), rootDep);
        } else {
            LOGGER.info("No root dependency for {}/{}:{}", matchPUrl.getNamespace(), matchPUrl.getName(), matchPUrl.getVersion());
        }
        return rootDep;
    }

    private static Map<String, PackageURL> initDepsMap(DependenciesReport dependenciesReport) {
        if (dependenciesReport == null) {
            return Collections.emptyMap();
        }

        Map<String, PackageURL> rootDepByDep = new HashMap<>();

        // only compileClassPath configuration
        dependenciesReport
                .getProject()
                .getConfigurations().stream()
                .filter(configuration -> configuration.getName().equals("compileClasspath"))
                .flatMap(configuration -> configuration.getDependencies().stream())
                .filter(dep -> !dep.isHasConflict())
                .forEach(dep -> {
                    String name = dep.getName();
                    putInMap(rootDepByDep, name, dep);
                });

        return rootDepByDep;
    }

    private static void putInMap(Map<String, PackageURL> rootDepByDep, String rootName, Dependency dependency) {
        try {
            if (!rootDepByDep.containsKey(dependency.getName())) {
                LOGGER.info("Added {}/{} key/value", dependency.getName(), rootName);
                String[] parts = rootName.split(":");
                PackageURL rootDepPUrl = PackageURLBuilder.aPackageURL().withType("maven").withNamespace(parts[0]).withName(parts[1]).withVersion(parts[2]).build();

                rootDepByDep.put(dependency.getName(), rootDepPUrl);
            }
            dependency.getChildren().forEach(dep -> {
                putInMap(rootDepByDep, rootName, dep);
            });
        } catch (MalformedPackageURLException e) {
            LOGGER.warn("Malformerd package url for {}", rootName);
        }
    }

    @Override
    public void describe(SensorDescriptor sensorDescriptor) {
        sensorDescriptor.name("Grype vulnerabilities analysis");
    }

    private Optional<GrypeReport> parseReport(SensorContext sensorContext) throws ParsingException {
        String path = sensorContext.config().get(GrypeConfiguration.JSON_REPORT_PATH_PROPERTY).orElse(GrypeConfiguration.JSON_REPORT_PATH_DEFAULT);
        File report = sensorContext.fileSystem().resolvePath(path);

        try {
            return Optional.ofNullable(objectMapper.readValue(report.toURI().toURL(), GrypeReport.class));
        } catch (Exception e) {
            throw new ParsingException("grype report can not be read.", e);
        }
    }

    private void saveHtmlReport(SensorContext sensorContext) {

        Optional<String> path = sensorContext.config().get(GrypeConfiguration.HTML_REPORT_PATH_PROPERTY);
        if (path.isPresent()) {
            try {
                File report = sensorContext.fileSystem().resolvePath(path.get());
                if (report == null) {
                    throw new FileNotFoundException("grype report does not exist.");
                }
                String htmlReport = Files.readString(report.toPath());
                if (htmlReport != null) {
                    LOGGER.info("Uploading grype HTML report");
                    sensorContext.<String>newMeasure().forMetric(GrypeMetrics.REPORT).on(sensorContext.project())
                            .withValue(htmlReport).save();
                }
            } catch (FileNotFoundException e) {
                LOGGER.warn("grype html report not found", e);
            } catch (IOException e) {
                LOGGER.error("fails to read grype html report", e);
            }
        } else {
            LOGGER.warn("no configuration for grype html report, ignoring upload");
        }
    }

    private Optional<DependenciesReport> parseDependenciesReport(SensorContext sensorContext) throws ParsingException {
        Optional<String> path = sensorContext.config().get(GrypeConfiguration.DEPENDENCIES_REPORT_PATH_PROPERTY);
        if (path.isPresent()) {
            File report = sensorContext.fileSystem().resolvePath(path.get());

            try {
                return Optional.ofNullable(objectMapper.readValue(report.toURI().toURL(), DependenciesReport.class));
            } catch (Exception e) {
                throw new ParsingException("dependencies report can not be read.", e);
            }
        }
        return Optional.empty();
    }
}
