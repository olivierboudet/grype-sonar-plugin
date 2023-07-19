package org.boudet.sonarqube.plugins.grype.metrics;

import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

import java.util.List;

public class GrypeMetrics implements Metrics {
    private static final String REPORT_KEY = "grype-report";
    private static final String DOMAIN = "grype";
    public static final Metric<String> REPORT = new Metric.Builder(REPORT_KEY, "Grype Report", Metric.ValueType.DATA)
            .setDescription("Grype HTML report")
            .setQualitative(Boolean.FALSE)
            .setDomain(DOMAIN)
            .setHidden(false)
            .setDeleteHistoricalData(true)
            .create();

    @Override
    public List<Metric> getMetrics() {
        return List.of(
                REPORT
        );
    }
}
