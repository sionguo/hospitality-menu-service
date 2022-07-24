Feature: Application health

  Scenario: health-check success
    Given the application is running
    When a request is made to "/actuator/health"
    Then the application returns a response status 200
    And the response body is "{\"status\":\"UP\"}"
