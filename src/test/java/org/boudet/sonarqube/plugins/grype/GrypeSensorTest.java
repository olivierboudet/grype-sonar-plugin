package org.boudet.sonarqube.plugins.grype;

import org.boudet.sonarqube.plugins.grype.measures.GrypeSensor;
import org.boudet.sonarqube.plugins.grype.metrics.GrypeMetrics;
import org.boudet.sonarqube.plugins.grype.rules.CveRuleDefinition;
import org.boudet.sonarqube.plugins.grype.settings.GrypeConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.fs.internal.DefaultInputFile;
import org.sonar.api.batch.fs.internal.TestInputFileBuilder;
import org.sonar.api.batch.rule.Severity;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.api.batch.sensor.internal.SensorContextTester;
import org.sonar.api.batch.sensor.issue.Issue;
import org.sonar.api.batch.sensor.issue.IssueLocation;
import org.sonar.api.config.internal.MapSettings;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class GrypeSensorTest {

    private GrypeSensor sensor;

    private SensorContextTester context;

    @BeforeEach
    public void init() {
        context = SensorContextTester.create(new File("src/test/resources"));

        this.sensor = new GrypeSensor();
    }

    @Test
    void testDescribe() {
        final SensorDescriptor descriptor = mock(SensorDescriptor.class);
        sensor.describe(descriptor);
        verify(descriptor).name("Grype vulnerabilities analysis");
    }

    @Test
    void shouldReturnIssues() throws URISyntaxException {
        String report = "report.json";

        // Plugin Configuration
        MapSettings settings = new MapSettings();
        settings.setProperty(GrypeConfiguration.JSON_REPORT_PATH_PROPERTY, report);
        context.setSettings(settings);

        sensor.execute(context);
        assertEquals(730, context.allIssues().size());
        for (Issue issue : context.allIssues()) {
            assertEquals(CveRuleDefinition.RULE_CVE.rule(), issue.ruleKey().rule());
        }
        Issue firstIssue = context.allIssues().stream().findFirst().orElseThrow();
        assertEquals("php-checks v3.27.1.9352| CVSS Score: 5.000000 | Type: java-archive", firstIssue.primaryLocation().message());
        assertEquals(Severity.MAJOR, firstIssue.overriddenSeverity());
    }

    @Test
    void shouldNotReturnIssuesIfReportNotFound() {
        String report = "notfound.json";

        // Plugin Configuration
        MapSettings settings = new MapSettings();
        settings.setProperty(GrypeConfiguration.JSON_REPORT_PATH_PROPERTY, report);
        context.setSettings(settings);

        sensor.execute(context);
        assertEquals(0, context.allIssues().size());
    }

    @Test
    void shouldUseDefaultPathIfNotDefined() {
        // Plugin Configuration
        MapSettings settings = new MapSettings();
        context.setSettings(settings);

        sensor.execute(context);
        assertEquals(0, context.allIssues().size());
    }

    @Test
    void shouldSaveHtmlReport() throws URISyntaxException {
        String jsonReport = "report.json";
        String htmlReport = "report.html";

        // Plugin Configuration
        MapSettings settings = new MapSettings();
        settings.setProperty(GrypeConfiguration.JSON_REPORT_PATH_PROPERTY, jsonReport);
        settings.setProperty(GrypeConfiguration.HTML_REPORT_PATH_PROPERTY, htmlReport);
        context.setSettings(settings);

        sensor.execute(context);
        assertEquals(730, context.allIssues().size());

        assertNotNull(context.measure("projectKey", GrypeMetrics.REPORT).value());
    }

    @Test
    void shouldParseDependenciesReport() throws URISyntaxException, IOException {
        File gradleFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("build.gradle")).toURI());
        context.fileSystem().add(TestInputFileBuilder.create("", "build.gradle").setContents(Files.readString(gradleFile.toPath())).build());

        String jsonReport = "report.json";
        String dependenciesReport = "dependencies.json";

        // Plugin Configuration
        MapSettings settings = new MapSettings();
        settings.setProperty(GrypeConfiguration.JSON_REPORT_PATH_PROPERTY, jsonReport);
        settings.setProperty(GrypeConfiguration.DEPENDENCIES_REPORT_PATH_PROPERTY, dependenciesReport);
        context.setSettings(settings);

        sensor.execute(context);
        assertEquals(730, context.allIssues().size());

        List<IssueLocation> nettyCves = context.allIssues().stream().filter(issue -> issue.primaryLocation().message().equals("netty-codec-http v4.1.66.Final| CVSS Score: 5.500000 | Type: java-archive")).map(issue -> issue.primaryLocation()).toList();

        assertEquals(1, nettyCves.size());
        assertEquals(13, nettyCves.get(0).textRange().start().line());
        assertEquals(13, nettyCves.get(0).textRange().end().line());

    }
}
