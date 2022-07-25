@GetUserContact
Feature: Validate and Test Get Request for UserAPI By Contact

	@Functional @Smoke @Negative
	Scenario: Verify if we are getting records of Users By Contact with Valid ContactNo
    Given User sets GET request with endpoint /Contact=ValidContactNo
    When User sends GET request with endpoint /Contact
    Then User will get 200 Ok Status code 
   
   @Functional @Smoke @Negative
   Scenario Outline:  Validating if we are getting record of Users By Contact with Invalid ContactNo
    Given User sets GET request with endpoint /Contact="<InValidContactNo>"
    When  GET request sent by User with endpoint /Contact
    Then Status 400 will be displayed with msg Not Found
   
    Examples:
    |InValidContactNo|
    |Null|
    |7445|
   
   
  @Functional @Smoke @Negative
	Scenario: Validating negative Scenario if we are getting record of Users By ContactNo With Invalid EndPoint
    Given User sets GET request with Invalid endpoint /Contact=ContactNo
    When User sends GET request with invalid endpoint /Contact
    Then Status 404 Not Found will be displayed 
    
    
       
   
    
    
   
   
        
  