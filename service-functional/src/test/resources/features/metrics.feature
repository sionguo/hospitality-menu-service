Feature: Application metrics

  Scenario: Endpoint responds with applications metrics
    Given the application is running
    When a get request is made to '/management/prometheus'
    Then the application returns a response status 200
    And the application contains metric notation 'jvm_buffer_memory_used_bytes' with value '0.0' and name-value pair labels:
      | id        | "mapped" |
    And the application contains metric notation 'jvm_memory_max_bytes' with value '-1.0' and name-value pair labels:
      | area        | "nonheap"   |
      | id          | "Metaspace" |
    And the application contains metric name 'system_cpu_usage' with value '0.0'
