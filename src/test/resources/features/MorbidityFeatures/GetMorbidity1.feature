@GetMorbidity
Feature: Validate and Test Get Request for Morbidity API with valid authorization
 

  @Smoke @Functional
  Scenario: Verify that user is able get All Morbidity records.

    Given User sets Get request with endpoint
    When User send Get request
    Then Status 200 Ok

  