Feature: Application health

  Scenario: health-check success
    Given the application is running
    When a request is made to "/actuator/health"
    Then the service returns a 200 status response
    And the response body is "{\"status\":\"UP\"}"
