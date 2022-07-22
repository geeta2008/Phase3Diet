#Background: Authorization is set to 'Basic Auth' with valid username and password

@userspost
Feature: Validate and Test POST request for Users API 

  @userspostsuccess
  Scenario: User is able to create a new user record
    Given User is on POST Request with Users endpoint
    When User sends the POST request with valid users inputs 
    Then User should be displayed success status code 200 with message Successfully Created. with automatically generated userid
    
  @userspostexisting
  Scenario: User is able to create a new user record with either existing firstname or existing contact or existing email
    Given User is on POST Request with Users endpoint
    When User sends the POST request with either existing firstname or existing contact or existing email
    Then User should be displayed success status code 200 with message Successfully Created. with automatically generated userid
  
  @userspostexistsfnameconemail
  Scenario: User is unable to create a new user record with existing firstname, contact and email
    Given User is on POST Request with Users endpoint
    When User sends the POST request with existing firstname, contact and email
    Then User should be displayed success status code 200 with message User detail already Exists. Check on [ Firstname, Contact , Email ] 
  
  @userspostnomandfields
  Scenario: User is able to create a new user record with missing mandatory field
    Given User is on POST Request with Users endpoint
    When User sends the POST request with mandatory fields
    Then User should be displayed success status code 200 with message Missing Items OR Invalid Entry. Check on fieldname
   
  @userspostemptybody
  Scenario: User is able to create a new user record with empty request body
    Given User is on POST Request with Users endpoint
    When User sends the POST request with empty request body
    Then User should be displayed status code 400 Bad Request 
   
  @userspostneusertype
  Scenario: User is not able to create a new user record when UserType is other than Dietician/Patient
    Given User is on POST Request with Users endpoint
    When User sends POST request with UserType other than Dietician or Patient
    Then User should be displayed 400 validation error message UserType should be Dietician/Patient