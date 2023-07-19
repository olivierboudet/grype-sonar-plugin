package org.boudet.sonarqube.plugins.grype.settings;

import org.sonar.api.PropertyType;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.resources.Qualifiers;

import java.util.Arrays;
import java.util.List;

public class GrypeConfiguration {
    public static final String JSON_REPORT_PATH_PROPERTY = "sonar.grype.reportPath";
    public static final String JSON_REPORT_PATH_DEFAULT = "grype-report.json";
    public static final String HTML_REPORT_PATH_PROPERTY = "sonar.grype.htmlReportPath";

    private GrypeConfiguration() {
        // do nothing
    }

    public static List<PropertyDefinition> getPropertyDefinitions() {
        return Arrays.asList(
                PropertyDefinition.builder(JSON_REPORT_PATH_PROPERTY)
                        .onQualifiers(Qualifiers.PROJECT)
                        .name("Grype JSON report path")
                        .description("path to the 'grype-report.json' file")
                        .defaultValue(JSON_REPORT_PATH_DEFAULT)
                        .build(),
                PropertyDefinition.builder(HTML_REPORT_PATH_PROPERTY)
                        .onQualifiers(Qualifiers.PROJECT)
                        .name("Grype HTML report path")
                        .description("path to the 'grype-report.html' file")
                        .build()
        );
    }
}
