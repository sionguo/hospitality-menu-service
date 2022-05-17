Feature: Application status

  Scenario: health-check success
    Given the application is running
    When a user makes a request to /actuator/health
    Then the service returns a response body showing status as "UP"
