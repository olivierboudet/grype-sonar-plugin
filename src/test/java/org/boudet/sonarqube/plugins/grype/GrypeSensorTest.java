package org.boudet.sonarqube.plugins.grype;

import org.boudet.sonarqube.plugins.grype.measures.GrypeSensor;
import org.boudet.sonarqube.plugins.grype.metrics.GrypeMetrics;
import org.boudet.sonarqube.plugins.grype.rules.CveRuleDefinition;
import org.boudet.sonarqube.plugins.grype.settings.GrypeConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.rule.Severity;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.api.batch.sensor.internal.SensorContextTester;
import org.sonar.api.batch.sensor.issue.Issue;
import org.sonar.api.config.internal.MapSettings;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class GrypeSensorTest {

    private GrypeSensor sensor;

    private FileSystem fileSystem;
    @BeforeEach
    public void init() {
        this.fileSystem = mock(FileSystem.class);
        this.sensor = new GrypeSensor(this.fileSystem);
    }

    @Test
    void testDescribe() {
        final SensorDescriptor descriptor = mock(SensorDescriptor.class);
        sensor.describe(descriptor);
        verify(descriptor).name("Grype vulnerabilities analysis");
    }

    @Test
    void shouldReturnIssues() throws URISyntaxException {
        final SensorContextTester context = SensorContextTester.create(new File(""));

        String report = "report.json";

        // Plugin Configuration
        MapSettings settings = new MapSettings();
        settings.setProperty(GrypeConfiguration.JSON_REPORT_PATH_PROPERTY, report);
        context.setSettings(settings);

        final URI sampleUri = Objects.requireNonNull(getClass().getClassLoader().getResource(report)).toURI();
        File sample = Paths.get(sampleUri).toFile();

        when(fileSystem.resolvePath(report)).thenReturn(sample);
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
        final SensorContextTester context = SensorContextTester.create(new File(""));

        String report = "notfound.json";

        // Plugin Configuration
        MapSettings settings = new MapSettings();
        settings.setProperty(GrypeConfiguration.JSON_REPORT_PATH_PROPERTY, report);
        context.setSettings(settings);

        File wrongFile = new File(report);

        when(fileSystem.resolvePath(report)).thenReturn(wrongFile);
        sensor.execute(context);
        assertEquals(0, context.allIssues().size());
    }

    @Test
    void shouldUseDefaultPathIfNotDefined() {
        final SensorContextTester context = SensorContextTester.create(new File(""));

        // Plugin Configuration
        MapSettings settings = new MapSettings();
        context.setSettings(settings);

        sensor.execute(context);
        assertEquals(0, context.allIssues().size());
        verify(this.fileSystem).resolvePath("grype-report.json");
    }

    @Test
    void shouldSaveHtmlReport() throws URISyntaxException {
        final SensorContextTester context = SensorContextTester.create(new File(""));

        String jsonReport = "report.json";
        String htmlReport = "report.html";

        // Plugin Configuration
        MapSettings settings = new MapSettings();
        settings.setProperty(GrypeConfiguration.JSON_REPORT_PATH_PROPERTY, jsonReport);
        settings.setProperty(GrypeConfiguration.HTML_REPORT_PATH_PROPERTY, htmlReport);
        context.setSettings(settings);

        final URI sampleJsonUri = Objects.requireNonNull(getClass().getClassLoader().getResource(jsonReport)).toURI();
        File sampleJson = Paths.get(sampleJsonUri).toFile();

        final URI sampleHtmlUri = Objects.requireNonNull(getClass().getClassLoader().getResource(htmlReport)).toURI();
        File sampleHtml = Paths.get(sampleHtmlUri).toFile();

        when(fileSystem.resolvePath(jsonReport)).thenReturn(sampleJson);
        when(fileSystem.resolvePath(htmlReport)).thenReturn(sampleHtml);

        sensor.execute(context);
        assertEquals(730, context.allIssues().size());
        verify(this.fileSystem).resolvePath(jsonReport);
        verify(this.fileSystem).resolvePath(htmlReport);
        assertNotNull(context.measure("projectKey", GrypeMetrics.REPORT).value());
    }
}
