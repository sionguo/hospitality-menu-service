package com.hospitality.menu.functional;

import com.hospitality.menu.functional.PrometheusMetrics.MetricNotation;
import org.assertj.core.api.AbstractAssert;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.platform.commons.util.StringUtils.isBlank;

public class PrometheusMetricsAssert extends AbstractAssert<PrometheusMetricsAssert, PrometheusMetrics> {

    private String name;
    private final Map<String, String> labels = new HashMap<>();

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
            failWithMessage("Expected metrics to contain metric name %s in %s", name, actual.toRawBodyString());
        }
        return this;
    }

    public PrometheusMetricsAssert withExactLabels(Map<String, String> expectedLabels) {
        isNotNull();
        if(isBlank(this.name)) {
            throw new RuntimeException("Name is required. Use 'hasName' method to specify one");
        }
        this.labels.putAll(expectedLabels);
        MetricNotation metricNotation = MetricNotation.valueOf(name, expectedLabels);
        if (!actual.hasMetric(metricNotation)) {
            failWithMessage("Expected metrics to contain %s in %s", metricNotation, actual.toRawBodyString());
        }
        return this;
    }

    public PrometheusMetricsAssert withValue(BigDecimal expectedValue) {
        isNotNull();
        if(isBlank(this.name)) {
            throw new RuntimeException("Name is required. Use 'hasName' method to specify one");
        }

        long numberOfMetricsMatchingName = actual.numberOfMetricsMatchingName(name);
        if(numberOfMetricsMatchingName > 1 && labels.isEmpty()) {
            throw new RuntimeException("Labels are required because there are " + numberOfMetricsMatchingName + " metrics with the same name. Use 'withExactLabels' method to specify them");
        }

        MetricNotation metricNotation = MetricNotation.valueOf(name, labels);
        if (!actual.metricHasValue(metricNotation, expectedValue)) {
            failWithMessage("Expected metrics to contain %s with value %s in %s", metricNotation, expectedValue, actual.toRawBodyString());
        }
        return this;
    }
}
