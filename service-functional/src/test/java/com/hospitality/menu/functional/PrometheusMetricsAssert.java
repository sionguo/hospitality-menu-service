package com.hospitality.menu.functional;

import com.hospitality.menu.functional.PrometheusMetrics.MetricNotation;
import org.assertj.core.api.AbstractAssert;

import java.util.Map;

import static org.junit.platform.commons.util.StringUtils.isBlank;

public class PrometheusMetricsAssert extends AbstractAssert<PrometheusMetricsAssert, PrometheusMetrics> {

    private String name;

    public static PrometheusMetricsAssert assertThat(PrometheusMetrics actual) {
        return new PrometheusMetricsAssert(actual);
    }

    public PrometheusMetricsAssert(PrometheusMetrics prometheusMetrics) {
        super(prometheusMetrics, PrometheusMetricsAssert.class);
    }

    public PrometheusMetricsAssert hasMetric(String name) {
        isNotNull();
        this.name = name;
        if (!actual.hasMetricMatchingName(name)) {
            failWithMessage("Expected metrics to contain metric name %s in %s", name, actual);
        }
        return this;
    }

    public PrometheusMetricsAssert withExactLabels(Map<String, String> expectedLabels) {
        isNotNull();
        if(isBlank(this.name)) {
            throw new RuntimeException("name is required. Use 'hasName' method to specify one");
        }

        MetricNotation metricNotation = MetricNotation.valueOf(name, expectedLabels);
        if (!actual.hasMetric(metricNotation)) {
            failWithMessage("Expected metrics to contain %s in %s", metricNotation, actual);
        }
        return this;
    }
}
