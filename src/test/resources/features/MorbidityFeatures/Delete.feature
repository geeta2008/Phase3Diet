@DeleteMorbidity
Feature: Validate and Test Delete Request for Morbidity API with valid authorization

  @TestDelete1
  Scenario Outline: Checking if user is able to delete record successfully
    Given User sets Delete request  with endpoint as "/MorbidityName=<MorbidityName>&MorbidityTestId=<MorbidityTestId>" with valid authorization
    When User sends Delete request with "<MorbidityName>","<MorbidityTestId>"
    Then Status 200 OK is displayed with Morbidity Name, MorbidityTestId and Message as "Successfully Deleted" is displayed
    Examples: 
      |MorbidityTestName | MorbidityTestID   |
      |312ABspondylosis   |     Mg/dl        |
      |423ABspondylosis  |  Dl/Mg        |
   
  @TestDelete2
  Scenario: Checking if user is able to delete non existing record successfully
    Given User sets Delete request with non existing with endpoint as " /MorbidityName=<MorbidityName>&MorbidityTestId=<MorbidityTestId> " with valid authorization
    When User will sends Delete request
    Then Status 404 Not Found is displayed with a Message "Already Deleted OR wrong MorbidityName ,MorbidityTestId." is displayed
   
  @TestDelete3
  Scenario: Checking if user is able to delete record successfully with incorrect url
    Given User sets Delete request  with invalid endpoint  "/MorbidityName=<MorbidityName>&MorbidityTestName=<MorbidityTestName>" with valid authorization
    When User send Delete request
    Then Status "404" Not Found will be displayed
    
    
  @TestDelete4
  Scenario Outline: Checking if user is able to delete record successfully with invalid credentials
    Given User sets Delete request  with endpoint as "/MorbidityName=<MorbidityName>&MorbidityTestId=<MorbidityTestId>" with Invalid UserName and valid Password / valid UserName and Invalid Password
    When User will send Delete request
    Then Status 401 Unauthorised Acces will be displayed
    Examples: 
      | name  | value | status  |
      | name1 |     5 | success |
      | name2 |     7 | Fail    |
    
    
  @TestDelete5
  Scenario: Checking if user is able to delete record successfully with servers down
    Given User sets Delete request  with endpoint as "/MorbidityName=<MorbidityName>&MorbidityTestId=<MorbidityTestId>" with valid authorization with dynamodb Server down
    When User sends Delete requests
    Then Status 500 Ok should display with a Message Internal Server Error.  
    

    
