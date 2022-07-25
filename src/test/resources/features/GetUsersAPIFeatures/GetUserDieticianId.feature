@GetUserDieticianId
Feature: Validate and Test Get Request for UserAPI By Dietician Id

	@Functional @Smoke
	Scenario: Verify if we are getting records of Users By Dietician ID with Valid DieticianID
    Given User sets GET request with endpoint /DieticianId=DieticianID
    When User sends GET request DieticianID
    Then User will get 200 Ok status code

   
   @Functional @Smoke @Negative
   Scenario Outline:  Validating if we are getting record of Users By DieticianID with Invalid DieticianID
    Given User sets GET request with endpoint /DieticianId="<InvalidDieticianID>"
    When User sends GET request with endpoint /DieticianId
    Then Status 400 NotFound will be given with message
   
    Examples:
    |InvalidDieticianID|
    |Null|
    |1234|
   
   
  @Functional @Smoke @Negative
	Scenario: Validating negative Scenario if we are getting record of Users By DieticianID With Invalid EndPoint
    Given User sets GET request with Invalid endpoint /DieticianI
    When User sends GET request with endpoint /DieticianI
    Then Status 404 Not Found will be given 
   
        
  