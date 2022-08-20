package com.hospitality.menu.functional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.logging.log4j.util.Strings;

public class PrometheusMetrics {

  private final Map<MetricNotation, BigDecimal> metrics;
  private final String bodyAsString;

  private PrometheusMetrics(String prometheusMetricsResponse) {
    this.bodyAsString = prometheusMetricsResponse;
    this.metrics =
        Stream.of(prometheusMetricsResponse.split("\n"))
            .filter(line -> !line.startsWith("#"))
            .map(metric -> metric.split("[\s](?=[^\s]*$)"))
            .collect(
                Collectors.toMap(
                    metric -> metricNotationValueOf(metric[0]),
                    metric -> metricValueOf(metric[1])));
    System.out.println(metrics);
  }

  /**
   * @param prometheusMetricsResponse Parses prometheus metrics response where each line format is:
   *     metric_name{label_name=label_value, ...} metric_value
   *     <p>Metric notation: The metric name and a set of optional labels Metric value: The metric
   *     value
   */
  public static PrometheusMetrics valueOf(ResponseWrapper prometheusMetricsResponse) {
    return new PrometheusMetrics(prometheusMetricsResponse.getBody());
  }

  private static MetricNotation metricNotationValueOf(String metricNotationAsString) {
    return MetricNotation.valueOf(metricNotationAsString);
  }

  private static BigDecimal metricValueOf(String metricValueAsString) {
    return new BigDecimal(metricValueAsString);
  }

  public boolean hasMetricMatchingName(String name) {
    return this.metrics.keySet().stream().anyMatch(notation -> notation.hasName(name));
  }

  public long numberOfMetricsMatchingName(String name) {
    List<MetricNotation> metricNotations =
        this.metrics.keySet().stream().filter(notation -> notation.hasName(name)).toList();
    return metricNotations.size();
  }

  public boolean hasMetric(MetricNotation metricNotation) {
    return metrics.containsKey(metricNotation);
  }

  public boolean metricHasValue(MetricNotation metricNotation, BigDecimal expectedValue) {
    return metrics.get(metricNotation).compareTo(expectedValue) == 0;
  }

  @Override
  public String toString() {
    return "PrometheusMetrics{" + "metrics=" + metrics + '}';
  }

  public String toRawBodyString() {
    return bodyAsString;
  }

  /**
   * Represents a metric notation given by prometheus data model See <a
   * href="https://prometheus.io/docs/concepts/data_model/#notation">Prometheus. Data model
   * notation</a>
   */
  static class MetricNotation {
    private final String name;
    private final Map<String, String> labels = new HashMap<>();

    private MetricNotation(String name) {
      this.name = name;
    }

    private MetricNotation(String name, Map<String, String> labels) {
      this.name = name;
      this.labels.putAll(labels);
    }

    static MetricNotation valueOf(String name, Map<String, String> labels) {
      return new MetricNotation(name, labels);
    }

    private static MetricNotation valueOf(String metricNotationAsString) {
      System.out.println("metricNotationAsString = " + metricNotationAsString);
      String[] metricNotation = metricNotationAsString.split("\\{");
      if (metricNotation.length == 1) {
        return new MetricNotation(metricNotation[0]);
      } else {
        return new MetricNotation(metricNotation[0], parseLabelsAsMap(metricNotation[1]));
      }
    }

    private static Map<String, String> parseLabelsAsMap(String metricNotationLabelsAsString) {
      System.out.println("metricNotationLabelsAsString = " + metricNotationLabelsAsString);
      return Stream.of(
              metricNotationLabelsAsString
                  .substring(0, metricNotationLabelsAsString.indexOf("}") - 1)
                  .split(","))
          .filter(Strings::isNotBlank)
          .map(metric -> metric.split("="))
          .filter(metricValue -> metricValue.length == 2)
          .collect(Collectors.toUnmodifiableMap(metric -> metric[0], metric -> metric[1]));
    }

    boolean hasName(String name) {
      return name.equals(this.name);
    }

    @Override
    public String toString() {
      return "MetricNotation{" + "name='" + name + '\'' + ", labels=" + labels + '}';
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }

      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      MetricNotation that = (MetricNotation) o;

      if (!name.equals(that.name)) {
        return false;
      }
      return labels != null ? labels.equals(that.labels) : that.labels == null;
    }

    @Override
    public int hashCode() {
      int result = name.hashCode();
      result = 31 * result + (labels != null ? labels.hashCode() : 0);
      return result;
    }
  }
}
