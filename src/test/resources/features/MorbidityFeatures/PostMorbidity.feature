@PostMorbidity
Feature: Validate and Test Post Request for Morbidity API with authorization
  I want to use this template for my feature file

  @Smoke @Regression @Functional
  Scenario: Verify if user is able to add a new record successfully 
    Given User sets POST request with endpoint with valid authorization
    When User sends Post request with new data as user input in json format 
    Then Status 200 Ok should be displayed with a "MorbidityTestID" (Auto Generated) with a Message Morbidity created successfully.
    
 @Regression @Functional
  Scenario Outline: Checking if user is able to add a existing record successfully
    Given User set POST request with endpoint with valid authorization
    When  User sends Post request with Existing data in Request body in json format 
    Then  Status 400  Bad Request should display with a Message "Error Occured"
   
  @NegativetestPostMorbidityDataMissing @Functional @Smoke
  Scenario Outline: Checking if user is able to add a new record with missing items successfully
    Given User with endpoint set POST request with valid authorization 
    When  User sends Post request with any two fields in different combination in Request body in json format  
    Then  Status 400 Bad Request should display with a Message Missing Items OR Invalid Entry.Check on "Missing Field" Names
    
   @NegativetestPostMorbidityNoData @Functional @Smoke 
    Scenario: Checking if user is able to add a new record successfully with out request body data
    Given User with endpoint sets POST request with valid authorization
    When  User sends Post request without data in Request body 
    Then  Status 400 Bad Request should display with a Message The browser (or proxy) sent a request that this server could not understand.
    
   @NegativetestPostMorbidityInvalidAuthorization @Functional @Smoke
  Scenario Outline: Checking if user is able to add a new record successfully with invalid authorization
    Given User set POST request with endpoint with Invalid authorization
    When  User sends Post request with "MorbidityName","MorbidityTestName","MorbidityMarkerRef","MorbidityTestUnit" in Request body in json format 
    Then  Status 401 Unauthorized should display with a Message "The server could not verify that you are authorized to access the URL requested. You either supplied the wrong credentials (e.g. a bad password), or your browser doesn't understand how to supply the credentials required."
    Examples:
      |MorbidityName  |MorbidityTestName | MorbidityMarkerRef | MorbidityTestUnit    |
      |423ASpondylosis  |  423ABloodTest       |   180/201       |     Mg/dl        |
    
  
