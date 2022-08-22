Feature: Application Info Page

  Background:
    Given the application is running
    And a get request is made to '/management/info'
    And the application returns a response status 200

  Scenario: It should display the app section
    Given the application info contains 'app' node
    And the application info app 'name' is 'service'
    And the application info app 'description' is 'Hospitality Menu Service is a project to manage hospitality services menus such as restaurants, bar, food-truck, etc.'
    And the application info app 'java.version' is '17'
    And the application info app contains version

  Scenario: It should display the build section
    Given the application info contains 'build' node
    And the application info build 'name' is 'service'
    And the application info build 'group' is 'com.hospitality.menu'
    And the application info build 'artifact' is 'service'
    And the application info build contains version
    And the application info build contains build time

  Scenario: It should display the git section
    Given the application info contains 'git' node
    And the application info git contains branch name
    And the application info git contains commit id
    And the application info git contains commit time
