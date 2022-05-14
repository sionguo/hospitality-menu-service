Feature: Health check status

  Scenario: Status should be OK
    When the user makes a request to the status page
    Then the service returns a status 200
