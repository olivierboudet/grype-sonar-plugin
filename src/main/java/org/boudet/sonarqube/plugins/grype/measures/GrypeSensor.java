package org.boudet.sonarqube.plugins.grype.measures;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.boudet.sonarqube.plugins.grype.metrics.GrypeMetrics;
import org.boudet.sonarqube.plugins.grype.model.Cvs;
import org.boudet.sonarqube.plugins.grype.model.GrypeReport;
import org.boudet.sonarqube.plugins.grype.rules.CveRuleDefinition;
import org.boudet.sonarqube.plugins.grype.settings.GrypeConfiguration;
import org.sonar.api.batch.fs.FileSystem;
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
import java.util.Comparator;
import java.util.Optional;

public class GrypeSensor implements Sensor {
    private static final Logger LOGGER = Loggers.get(GrypeSensor.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final FileSystem fs;

    public GrypeSensor(FileSystem fs) {
        this.fs = fs;
    }

    @Override
    public void describe(SensorDescriptor sensorDescriptor) {
        sensorDescriptor.name("Grype vulnerabilities analysis");
    }


    @Override
    public void execute(SensorContext sensorContext) {
        Profiler profiler = Profiler.create(LOGGER);
        profiler.startInfo("Grype process report");

        try {
            Optional<GrypeReport> analysis = parseReport(sensorContext);
            if (analysis.isPresent()) {
                createIssues(sensorContext, analysis.get());
            } else {
                LOGGER.info("No Grype report found.");
            }
            saveHtmlReport(sensorContext);
        } catch (ParsingException e) {
            LOGGER.error("Grype report can not be parsed, aborting grype analysis.", e);
        }

        profiler.stopInfo();
    }

    private static void createIssues(SensorContext sensorContext, GrypeReport analysis) {
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

            LOGGER.info("Link issues to project");
            NewIssueLocation location = issue.newLocation()
                    .on(sensorContext.project())
                    .message(title);

            issue.at(location);

            issue.save();


        });
    }

    private Optional<GrypeReport> parseReport(SensorContext sensorContext) throws ParsingException {
        String path = sensorContext.config().get(GrypeConfiguration.JSON_REPORT_PATH_PROPERTY).orElse(GrypeConfiguration.JSON_REPORT_PATH_DEFAULT);
        File report = this.fs.resolvePath(path);

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
                File report = this.fs.resolvePath(path.get());
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
}
