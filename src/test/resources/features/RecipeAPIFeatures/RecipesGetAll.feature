@ALLRecipes
Feature: Validate and Test Get Request for DieticianAPI with no parameters
Background: The Authorization is set to "Basic auth" with valid username and password

@RecipesALL
Scenario: Checking if We are getting all the records 
Given Users set Get Request with no parameters
When Users send GeT request with no parameters
Then Status 200 should be displayed 

