#Background: Authorization is set to 'Basic Auth' with valid username and password

@usersget @Functional @Smoke
Feature: Validate and Test GET request for Users API

  @usersgetallsuccess @Functional @Smoke 
  Scenario: User is able to get list of all users
    Given User is on GET request with Users endpoint
    When User sends GET request for all users
    Then User should be displayed success status code 200 with list of all users.