@DeleteMorbidity
Feature: Validate and Test Delete Request for Morbidity API with valid authorization

  @TestDelete1 @Functional @Smoke @Regression
  Scenario Outline: Checking if user is able to delete record successfully
    Given User sets Delete request  with endpoint "<MorbidityName>","<MorbidityTestID>" with valid authorization
    When User sends Delete request
    Then Status 200 OK is displayed with Morbidity Name, MorbidityTestId and Message as "Successfully Deleted." is displayed
    Examples: 
      |MorbidityName|MorbidityTestID   |
      |1Abecholestrol|1AB_1LK|
      |23ABspondylosis|23A_23A|
      |Shypothyroid|SHY_ST4| 
      
   
  @NegativeTestDelete2 @Functional @Smoke 
 Scenario Outline: Checking if user is able to delete non existing record successfully
    Given User sets Delete request with non existing with endpoint "<MorbidityName>","<MorbidityTestID>" with valid authorization
    When User will sends Delete request
    Then Status 404 Not Found is displayed with a Message "Already Deleted OR wrong MorbidityName ,MorbidityTestId." is displayed
    Examples: 
      |MorbidityName  | MorbidityTestID   |
      |VderPre Diabetes|VDE_VBL|
      
   
  @NegativeTestDelete3 @Functional @Smoke
   Scenario Outline: Checking if user is able to delete record successfully with incorrect url
   Given User set Delete request with incorrect endpoint "<MorbidityName>","<MorbidityTestName>" with valid authorization
    When User will send Delete request
    Then Status 405 Method Not Allowed will be displayed with a message "The method is not allowed for the requested URL."
    Examples: 
      |MorbidityName | MorbidityTestName   |
      |Aspondylosis  |BloodPressure       |
      
    
    
  @NegativeTestDelete4 @Functional @Smoke
  Scenario Outline: Checking if user is able to delete record successfully with invalid credentials
    Given User set Delete request  with endpoint "<MorbidityName>","<MorbidityTestId>" with Invalid UserName and valid Password or valid UserName and Invalid Password
    When  User send Delete request
    Then  Status 401 Unauthorised Access will be displayed
    Examples: 
      |MorbidityName  | MorbidityTestId   |
      |Aspondylosis   |ASP_ABL      |
    
    
  

    
