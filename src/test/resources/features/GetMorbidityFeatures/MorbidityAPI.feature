@tag
Feature: Verify Get Request for Morbidity API.

  @positive
  Scenario: Verify that user is able get All Morbidity records.
    Given For Morbidity Get request, the Authorization is set to Basic auth with servers running.
    When User will send Get requestt.
    Then User will check for 200  Ok.
    | Diabetes Type1        | Rheumatoid Arthritis  | Hypothyroidism       | Hypothyroidism        | Pre Diabetes         | Hypotension            | Hypertension           | Diabetes Type2    |
    | DIA1_BG | RHEU_CCF | THY_T4 | HPOT_BP | PDIA_BG | HYPO_T3 | HPT_BP | DIA2_BG |
     
  @negative
  Scenario: Verify that user gets UnAuthorized Access.
    Given For Morbidity Get request, the invalid Authorization is set to Basic auth with servers running.
    When User will send Get requestt.
    Then User will check for 401  Ok.
    |blank|
     #this is the limitation of datatable that you cannot pass empty table. It will convert it to empty string.
      
  @negative
  Scenario: Verify that user gets Not Found error(invalid url).
    Given For Morbidity Get request, the Authorization is set to Basic auth and invalid endpoints with servers running.
    When User will send Get requestt.
    Then User will check for 404  Ok.
    |blank|
      
  @negative
   Scenario: Verify that user gets Internal Server error.
    Given For Morbidity Get request, the Authorization is set to Basic auth with servers NOT running.
    When User will send Get requestt.
    Then User will check for 500  Ok.
    |blank|
 
     
      
   


