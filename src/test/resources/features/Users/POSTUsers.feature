#Background: Authorization is set to 'Basic Auth' with valid username and password

@userspost
Feature: Validate and Test POST request for Users API 

  @userspostsuccess
  Scenario: User is able to create a new user record
    Given User is on POST Request with Users endpoint
    When User sends the POST request with valid users inputs 
    Then User should be displayed success status code 200 with message Successfully Created. with automatically generated userid
    
  @userspostneemail
  Scenario: User is able to create a new user record with existing firstname and contact and non-existing email
    Given User is on POST Request with Users endpoint
    When User sends the POST request with existing firstname and contact and non existing email
    Then User should be displayed success status code 200 with message Successfully Created. with automatically generated userid
  
  @userspostnecontact
  Scenario: User is able to create a new user record with existing firstname and email and non-existing contact
    Given User is on POST Request with Users endpoint
    When User sends the POST request with existing firstname and email and non existing contact
    Then User should be displayed success status code 200 with message Successfully Created. with automatically generated userid
  
  @userspostnefirstname
  Scenario: User is able to create a new user record with existing email and contact and non-existing firstname
    Given User is on POST Request with Users endpoint
    When User sends the POST request with existing email and contact and non-existing firstname
    Then User should be displayed success status code 200 with message Successfully Created. with automatically generated userid
  
  @userspostexistsfnameconemail
  Scenario: User is able to create a new user record with existing firstname, contact and email
    Given User is on POST Request with Users endpoint
    When User sends the POST request with existing firstname, contact and email
    Then User should be displayed success status code 200 with message User detail already Exists. Check on [ Firstname, Contact , Email ] 
  
  @userspostnomandfields
  Scenario Outline: User is able to create a new user record with missing mandatory field
    Given User is on POST Request with Users endpoint
    When User sends the POST request with missing data from "<SheetName>" and <RowNumber>
    Then User should be displayed success status code 200 with message Missing Items OR Invalid Entry. Check on ["<SheetName>"] and <RowNumber>

    Examples: 
      | SheetName  | RowNumber | 
      | PostUsers |     0 | 
      | PostUsers |     1 |
      | PostUsers |     2 |
      | PostUsers |     3 |
      | PostUsers |     4 |
      | PostUsers |     5 |
      | PostUsers |     6 |
      | PostUsers |     7 | 
      | PostUsers |     8 |
      | PostUsers |     9 |
      
  @userspostemptybody
  Scenario: User is able to create a new user record with empty request body
    Given User is on POST Request with Users endpoint
    When User sends the POST request with empty request body
    Then User should be displayed status code 400 Bad Request 

  @userspostserverdown
  Scenario: Dietician API server is down
    Given User is on POST Request with Users endpoint
    When User sends POST request with valid inputs
    Then User should be displayed message The browser (or proxy) sent a request that this server could not understand.
    
  @userspostneusertype
  Scenario: User is not able to create a new user record when UserType is other than Dietician/Patient
    Given User is on POST Request with Users endpoint
    When User sends POST request with UserType as Doctor
    Then User should be displayed 400 validation error message UserType should be Dietician/Patient