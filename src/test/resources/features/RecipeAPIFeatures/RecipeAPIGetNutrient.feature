@GetRecipes
Feature: Validate and Test Get Request for DieticianAPI with RecipeNutrient
Background: The Authorization is set to "Basic auth" with valid username and password

@test01
Scenario: Checking if we are getting record of with RecipeNutrient
Given User sets GET request with endpoint with valid RecipeNutrient 
When User sends GET request 
Then Status 200 success 

Scenario: Checking if we are getting record of RecipeNutrient with invalid request query string
Given User sets GET request endpoint with invalid request query string
When User sent GET request 
Then Status 400 Validation Error 

Scenario: Checking if we are getting record of RecipeNutrient with Unauthorised Acces 
Given User set GET request with endpoint with "/RecipeNutrient" with default authorization
When User send GET request 
Then Status 401 Unauthorised Acces 

Scenario: Checking if we are getting record of RecipeNutrient with invalid endpoint        
Given User set get request with invalid endpoint
When User sents get request 
Then Status 404 Not Found   


