@GetRecipeVeg
Feature: Validate and Test Get Request for DieticianAPI with Vegetarian
Background: The Authorization is set to "Basic auth" with valid username and password

@GetRecipeVegValid
Scenario: Checking if we are getting record of Vegetarian
Given User set get request with endpoint with "/Vegetarian" 
When Users sends get request 
Then Status 200 success will be shown

@GetRecipeVegInvalid
Scenario: Checking if we are getting record of Vegetarian with invalid query
Given User sets get request with invalid request query string
When User sending get request 
Then Status 400 Validation Error will be shown

@GetRecipeVegUnauthorized
Scenario:Checking if we are getting record of Vegetarian with default authorization
Given Users sets GET request with endpoint "/Vegatarian" with default authorization
When User sents GET request  
Then Status 401 Unauthorised Acces will be shown

@GetRecipeVegInvalidendpoint
Scenario:Checking if we are getting record of Vegetarian with invalid endpoint        
Given Users set GET request with invalid endpoint
When Users send GET request 
Then Status 404 Not Found will be shown   