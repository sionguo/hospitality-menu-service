Feature: Application Info Page

  @wip
  Scenario: It should display the application name
    Given the application is running
    When a request is made to "/actuator/info"
    Then the application returns a response status 200
    And the application info name is 'Hospitality Menu Service'