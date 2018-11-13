@New
Feature: API Specification

  Scenario: Can get greetings from the server
    Given Greeting service is up
    When I call greeting service
    Then http response code is 200 UNAUTHORISED



