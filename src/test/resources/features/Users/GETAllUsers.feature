#Background: Authorization is set to 'Basic Auth' with valid username and password

@usersget
Feature: Validate and Test GET request for Users API

  @usersgetallsuccess
  Scenario: User is able to get list of all users
    Given User is on GET request with Users endpoint
    When User sends GET request
    Then User should be displayed success status code 200 with list of all users.