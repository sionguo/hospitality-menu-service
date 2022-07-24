Feature: Application Info Page

  Scenario: It should display the application info
    Given the application is running
    When a request is made to '/actuator/info'
    Then the application returns a response status 200
    And the application info app 'name' is 'service'
    And the application info app 'description' is 'Hospitality Menu Service is a project to manage hospitality services menus such as restaurants, bar, food-truck, etc.'
    And the application info app 'java.version' is '17'
