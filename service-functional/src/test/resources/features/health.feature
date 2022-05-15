Feature: Application status

  Scenario: health-check success
    When a user makes a request to /actuator/health
    Then the service returns a response body showing status as "UP"
