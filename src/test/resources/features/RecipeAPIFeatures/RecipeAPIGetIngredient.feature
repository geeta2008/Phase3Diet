@GetRecipesIngredient
Feature: Validate and Test Get Request for DieticianAPI with RecipeIngredient 
Background: The Authorization is set to "Basic auth" with valid username and password

@TestRecipeIngredientValid
Scenario: Checking if we are getting record of with RecipeIngredient 
Given User sets GET request with endpoint with valid RecipeIngredient
When Users sents GET request  
Then Status 200 success will be seen

@TestRecipeIngredientInValid
Scenario: Checking if we are getting record of with RecipeIngredient with invalid request query string
Given User set GET request with endpoint with invalid request query string
When User sending GET request 
Then Status 400 Validation Error will be seen

@TestRecipeIngredientUnauthorized
Scenario: Checking if we are getting record of RecipeIngredient with Unauthorised Acces 
Given User sets Get request with endpoint with "/RecipeIngredient" with default authorization
When User sent get request   
Then Status 401 Unauthorised Acces will be seen

@TestRecipeIngredientInvalidendpoint
Scenario: Checking if we are getting record of RecipeNutrient with invalid endpoint              
Given User sets GET request with invalid endpoints
When User click sent GET request 
Then Status 404 Not Found will be seen  
