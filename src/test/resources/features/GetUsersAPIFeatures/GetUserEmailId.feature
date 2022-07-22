@GetUserEmailId
Feature: Validating and Testing Get Request for UserAPI By EmailId 

	@get
	Scenario: Verify if we are getting records of Users By Email with Valid Email Id
    Given User sets GET request with endpoint /Email=ValidEmailid
    When GET request is send by user 
    Then User gets 200 Ok Status 

   
   @get
   Scenario Outline:  Validating if we are getting record of Users By Email with Invalid EmailId
    Given User sets GET request with endpoint /Email="<InValidEmailid>"
    When GET request is send by user with EndPoint
    Then Status 200 ok will be given with messsage
   
    Examples:
    |InValidEmailid|
    |Null|
    |1234|
   
   
   	@get
	Scenario: Validating negative Scenario if we are getting record of Users By Email With Invalid EndPoint
    Given User sets GET request with Invalid endpoint mail=EmailId
    When User sends GET request with invalid endpoint Email
    Then Status 404 Not Found will be given with message
    
       
   
    
    
    
   
   
        
  