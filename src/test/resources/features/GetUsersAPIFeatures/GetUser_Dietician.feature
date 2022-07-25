Feature: Validate Get User Request for Dietician Users API for User Type - Dietician

  @Functional @Smoke 
  Scenario: Verify that we are getting records of User by User Type - Dietician
    Given User is on Get request with User Type Dietician
    When User sends Get request with endpoint and authorization
    Then Status 200 should be displayed with user records of type Dietician
