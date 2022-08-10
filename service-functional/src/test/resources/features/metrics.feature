Feature: Application metrics

  @wip
  Scenario: Endpoint responds with applications metrics
    Given the application is running
    When a request is made to '/management/prometheus'
    Then the application returns a response status 200
    And the application contains metric notation 'jvm_buffer_memory_used_bytes' with name value labels:
      | id        | "mapped" |

