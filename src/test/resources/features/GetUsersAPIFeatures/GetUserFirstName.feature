@GetUserFirtsName
Feature: Validate and Test Get Request for UserAPI By First Name 

	@Positive @Smoke
	Scenario: Verify if we are getting records of Users By First Name with Valid First Name
    Given User sets GET request with endpoint /FirstName=ValidFirstname
    When User sends GET request 
    Then User will get 200 Ok Status with correct Response Body 

   
   @Negative 
   Scenario Outline:  Validating if we are getting record of Users By First Name with Invalid First Name
    Given User sets GET request with endpoint /FirstName="<InValidFirstname>"
    When User sends GET request with endpoint 
    Then Status 200 ok will be displayed with message
   
    Examples:
    |Invalidfirstname|
    |Null|
    |1234|
   
   
   	@Negative @Smoke
	Scenario: Validating negative Scenario if we are getting record of Users By First Name With Invalid EndPoint
    Given User sets GET request with Invalid endpoint Firstame=firtsname
    When User sends GET request with invalid endpoint
    Then Status 404 Not Found will be displayed with message
    
       
   @Negative @Smoke
   Scenario: Validating Negative case if we are getting record of Users By First Name With Invalid Authorisation
    Given The Authorization is set to Invalid Basic auth
    When User sends GET request with Invalid endpoint /FirstName=ValidFirstname 
    Then Status 401 Unauthorised Acces will be displayed with Message
    
     
    
    
    @Negative @Smoke 
	Scenario: Checking if we are getting record of Users By First Name When servers no running
    Given For Get request, the Authorization is set to Basic auth with servers not running
    When User sends GET request with valid endpoint /FirstName=ValidFirstname 
    Then Status 500 INTERNAL SERVER ERROR
    
    
    
   
   
        
  