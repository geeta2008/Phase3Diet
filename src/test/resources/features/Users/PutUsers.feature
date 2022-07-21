Feature: Validate Put User Request for Dietician Users API

  @Smoke @Regression @Functional
  Scenario: Verify that existing user record is updated
    Given User is on put user request with endpoint
    When User sends put request with all valid parameter values in request body
    Then Status code 200 record updated should be displayed

  @Regression
  Scenario: Verify if existing record gets updated with same values
    Given User is on put user request with endpoint
    When User sends put request with same values in request body
    Then status code 200 for successful update should be displayed

  @NegativeTest
  Scenario: Verify if existing user record can be updated with null values or empty fields
    Given User is on put user request with endpoint
    When User sends put request with incorrect values
    Then Status code 400 bad request should be displayed

  @NegativeTest
  Scenario: Verify if user record gets updated in case of incorrect or non existent id
    Given User is on put user request with endpoint
    When User sends put request for incorrect dietician and user id
    Then Status 400 user id not found should be displayed

 
