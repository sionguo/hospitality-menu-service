Feature: Application health

  Scenario: health-check success
    Given the application is running
    When a user makes a request to /actuator/health
    Then the service returns a success status response
    And the response has a body showing application status as "UP"
