Feature: Application Info Page

  Scenario: It should display the application info
    Given the application is running
    When a request is made to "/actuator/info"
    Then the application returns a response status 200
    And the response body is "{\"app\":{\"name\":\"service\"}}"