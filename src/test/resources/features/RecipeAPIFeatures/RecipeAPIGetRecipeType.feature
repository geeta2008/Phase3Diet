@GetRecipesByType
Feature: Validate and Test Get Request for DieticianAPI with RecipeType
Background: The Authorization is set to "Basic auth" with valid username and password

@TestGetRecipesTypeValid @Functional @Smoke
Scenario: Checking if we are getting record of with RecipeType
Given User set GET request and endpoint with all valid Recipe Type
When Users sending GET request 
Then Status 200 success will be displays

@TestGetRecipesTypeInValid @Functional @Smoke
Scenario: Checking if we are getting record of RecipeType with invalid query
Given User sets GET request with endpoint with invalid request query string
When User gave GET request 
Then Status 400 Validation Error will be displays
  
@TestGetRecipesTypeUnauthorized @Functional @Smoke 
Scenario: Checking if we are getting record of RecipeType with Unauthorised Acces 
Given User set GET request with endpoint "/RecipeType" with default authorization
When User sets to sents get request  
Then Status 401 Unauthorised Acces will be displays

@TestGetRecipesTypeInvalidEndpoint @Functional @Smoke
Scenario: Checking if we are getting record of RecipeType with invalid endpoint        
Given User set GET request with invalid endpoint
When User clicks sent GET request
Then Status 404 Not Found will be displays 