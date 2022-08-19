package com.hospitality.menu.functional.steps;

import static com.hospitality.menu.functional.PrometheusMetricsAssert.assertThat;

import com.hospitality.menu.functional.PrometheusMetrics;
import com.hospitality.menu.functional.ResponseContext;
import io.cucumber.java.en.And;
import java.math.BigDecimal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

public class MetricsStepDefinitions {

  @Autowired private ResponseContext responseContext;

  @And("the application metrics contain expected metrics")
  public void theApplicationMetricsContainExpectedMetrics() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @And(
      "the application contains metric notation {string} with value {string} and name-value pair labels:")
  public void theApplicationContainsMetricNotation(
      String name, String value, Map<String, String> labels) {
    PrometheusMetrics prometheusMetrics = PrometheusMetrics.valueOf(responseContext.lastResponse());
    assertThat(prometheusMetrics)
        .hasMetric(name)
        .withExactLabels(labels)
        .withValue(new BigDecimal(value));
  }

  @And("the application contains metric name {string} with value {string}")
  public void theApplicationContainsMetricName(String name, String value) {
    PrometheusMetrics prometheusMetrics = PrometheusMetrics.valueOf(responseContext.lastResponse());
    assertThat(prometheusMetrics).hasMetric(name).withValue(new BigDecimal(value));
  }
}
