package com.hospitality.menu.functional.steps;

import com.hospitality.menu.functional.PrometheusMetrics;
import com.hospitality.menu.functional.ResponseContext;
import io.cucumber.java.en.And;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static com.hospitality.menu.functional.PrometheusMetricsAssert.assertThat;


public class MetricsStepDefinitions {

    @Autowired
    private ResponseContext responseContext;

    @And("the application metrics contain expected metrics")
    public void theApplicationMetricsContainExpectedMetrics() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @And("the application contains metric notation {string} with name value labels:")
    public void theApplicationContainsMetricNotation(String name, Map<String, String> labels) {
        PrometheusMetrics prometheusMetrics = PrometheusMetrics.valueOf(responseContext.lastResponse());
        assertThat(prometheusMetrics).hasMetric(name).withExactLabels(labels);
    }
}
