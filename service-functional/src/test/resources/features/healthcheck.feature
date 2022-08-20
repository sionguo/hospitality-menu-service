Feature: Application health

  Scenario: health-check success
    Given the application is running
    When a request is made to '/management/health'
    Then the application returns a response status 200
    And the response body is '{\"status\":\"UP\"}'

  Scenario: health-check liveness probe
    Given the application is running
    When a request is made to '/management/health/liveness'
    Then the application returns a response status 200
    And the response body is '{\"status\":\"UP\"}'

  Scenario: health-check readiness probe
    Given the application is running
    When a request is made to '/management/health/readiness'
    Then the application returns a response status 200
    And the response body is '{\"status\":\"UP\"}'
