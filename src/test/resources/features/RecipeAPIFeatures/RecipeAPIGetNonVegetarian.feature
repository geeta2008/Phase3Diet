@RecipebyNONVEG
Feature: Validate and Test Get Request for DieticianAPI with Non-Vegetarian
Background: The Authorization is set to "Basic auth" with valid username and password

@testNonvegvalid
Scenario: Checking if we are getting record of Non-Vegetarian with valid
Given Users give GET request with the valid endpoint "/Non-Vegetarian" 
When Users give GET request 
Then Status 200 success be displayed

@TestNonvegInvalidparameter
Scenario: Checking if we are getting record of Non-Vegetarian with invalid request query string
Given Users sets GET request with invalid request query string
When Users will sends the GET request 
Then Status 400 Validation Error be displayed

@TestNonvegunauthorized
Scenario: Checking if we are getting record of Non-Vegetarian with Unauthorised Access
Given User set GET request endpoint "/Non-Vegatarian" with default authorization
When Users will send GET request 
Then Status 401 Unauthorised Acces be displayed

@TestNonVegInvalidendpoint
Scenario: Checking if we are getting record of Non-Vegetarian with invalid endpoint
Given Users sets get request with invalid endpoint
When Users will send the GET request 
Then Status 404 Not Found be displayed   
