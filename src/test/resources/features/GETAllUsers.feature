#Background: Authorization is set to 'Basic Auth' with valid username and password

@usersget
Feature: Validate and Test GET request for Users API

  @usersgetallsuccess
  Scenario: User is able to get list of all users
    Given User is on GET request with Users endpoint
    When User sends GET request
    Then User should be displayed success status code 200 with list of all users.
    
  @usersgetallinvalidusername
  Scenario: User gets unauthorized access due to invalid username
    Given User is on GET request with Users endpoint
    When User sends GET request
    Then user should get status code error 401 unauthorized access

  @usersgetallinvalidpwd
  Scenario: User gets unauthorized access due to invalid password
    Given User is on GET request with Users endpoint
    When User sends GET request
    Then user should get status code error 401 unauthorized access

  @usersgetallserverdown
  Scenario: User does not get any list due to server issue
    Given User is on GET request with Users endpoint
    When User sends GET request
    Then user should get status code error 500 internal server error 