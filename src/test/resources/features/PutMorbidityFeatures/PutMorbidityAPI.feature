@LoginPage @TestNG
@severity=blocker
Feature: Verify Put Request for Morbidity API.

  @ValidCredentials
  Scenario Outline: Verify that user is able put (update) Morbidity records.
    Given For Morbidity Put request, the Authorization is set to Basic auth with servers running and paramteres "<MorbidityName>" , "<MorbidityTestId>" ,"<MorbidityMarkerRef>" , "<MorbidityTestUnit>"
    When User will send Put request.
    Then User will check for <statusCode>  "<MorbidityMarkerRef>"  "<MorbidityTestUnit>"
   
   #http://127.0.0.1:5000/api/Morbidity/MorbidityName=kiranTestNameUpd&MorbidityTestId=KIR_KIR_Upd
   
    Examples: 
      | MorbidityName    | MorbidityTestId |  MorbidityMarkerRef        | MorbidityTestUnit       |  statusCode  |
      | autoTestName     | AUT_AUT         |  autoTestMarkerRefupt5     | autoTestTestUnitupt5    |  200     |
     
  @InvalidCredentials
    Scenario Outline: Verify that user is able put (update) Morbidity records 
    Given For Morbidity Put request, the Authorization is set to Basic auth with servers running and paramteres "<MorbidityName>" , "<MorbidityTestId>" ,"<MorbidityMarkerRef>" , "<MorbidityTestUnit>"
    When User will send Put request.
    Then User will check for <statusCode>  "<MorbidityMarkerRef>"  "<MorbidityTestUnit>"
   
   #http://127.0.0.1:5000/api/Morbidity/MorbidityName=kiranTestNameUpd&MorbidityTestId=KIR_KIR_Upd
   
    Examples: 
      | MorbidityName    | MorbidityTestId |  MorbidityMarkerRef        | MorbidityTestUnit       |  statusCode  |
      | autoTestNameNo   | AUT_AUT         |  autoTestMarkerRefNo       | autoTestTestUnitNo      |  404     |
      | autoTestName     | AUT_AUTnoo      |  autoTestMarkerRefnoo      | autoTestTestUnituptnoo  |  404     | 
      | autoTestNameN    | AUT_AUTN        |  autoTestMarkerRefN        | autoTestTestUnituptN    |  404     |
     
  
      
   


