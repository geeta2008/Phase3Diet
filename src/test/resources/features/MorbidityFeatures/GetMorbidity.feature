@GetMorbidity
Feature: Validate and Test Get Request for Morbidity API with valid authorization
 

  @testGetMorbidity
  Scenario: Verify that user is able get All Morbidity records.

    Given User sets Get request with endpoint "/Morbidity/" 
    When User sends Get request.
    Then Status "200 Ok".

  