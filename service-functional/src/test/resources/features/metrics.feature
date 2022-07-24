Feature: Application metrics

  @wip
  Scenario: Endpoint responds with applications metrics
    Given the application is running
    When a request is made to '/actuator/metrics'
    Then the application returns a response status 200
    And the application metrics contain expected metrics
