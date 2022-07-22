@tag@LoginPage @TestNG
@severity=blocker
Feature: Verify Get Request for Morbidity ID API.

   @ValidCredentials
  Scenario: Verify that user is able get all Morbidity records with given id.
    Given For MorbidityId Get request, the Authorization is set to Basic auth with servers running.
    When User sends Get request.
    Then User checks for 200  Ok and
    | MorbidityTestName |  MorbidityTestUnit | MorbidityMarkerRef | MorbidityName  | MorbidityTestId |
    | Blood glucose      |   mg/dL             | 126 or higher      | Diabetes Type1 | DIA1_BG |
      
  @InvalidCredentials
  Scenario: Verify that user gets UnAuthorized Access.
    Given For MorbidityId Get request, the invalid Authorization is set to Basic auth with servers running.
    When User sends Get request.
    Then User checks for 401  Ok and
    |blank|
    |blank|
     #this is the limitation of datatable that you cannot pass blank table. It will convert it to empty string.
     
  @InvalidCredentials
  Scenario: Verify that user gets Not Found error(invalid endpoints).
    Given For MorbidityId Get request, the Authorization is set to Basic auth and invalid endpoints servers running.
    When User sends Get request.
    Then User checks for 404  Ok and 
    |blank|
    |blank|
    
  @InvalidCredentials
  Scenario: Verify that user gets Internal Server error.
    Given For MorbidityId Get request, the Authorization is set to Basic auth with servers NOT running.
    When User sends Get request.
    Then User checks for 500  Ok and
    |blank|
    |blank|
      
   


