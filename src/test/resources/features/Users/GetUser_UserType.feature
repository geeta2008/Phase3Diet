Feature: Validate Get User Request for Dietician Users API for User Type - Patient / Dietician

  @Smoke @Regression
  Scenario Outline: Verify that we are getting records of User by User Type
    Given User is on Get Request with "<endpoint>"
    When User sends Get Request with authorization
    Then Status code 200 should be displayed

    Examples: 
      | endpoint               |
      | Users/UserType=Patient |

  @NegativeTest
  Scenario Outline: Verify if we can get user records in case of invalid User Type
    Given User is on Get User-User Type request
    When User sends request with invalid User Type "<uri>"
    Then Status "<code>" Not found should be displayed

    Examples: 
      | uri                      | code |
      | Users/UserType=12345     |  404 |
      | Users/UserType=          |  404 |
      | Users/UserType=patient   |  404 |
      | Users/UserType=dietician |  404 |
      | Users/UserType=Doctor    |  404 |

  @Smoke @Security
  Scenario: Verify if we are getting records of User Type with no authorization
    Given User is on Get User Type request
    When User sends Get request with no authorization
    Then Status code 401 Unauthorized should be displayed

  @Smoke @Security
  Scenario Outline: Verify if we can get records of User by User Type with invalid credentials
    Given User is on Get User Type request with invalid "<username>" and "<password>"
    When User sends Get request with "<userendpoint>"
    Then Status 401 Unauthorized access should be displayed

    Examples: 
      | username | password   | userendpoint           |
      | user     | testpwd123 | Users/UserType=Patient |

  @Sanity
  Scenario Outline: Verify if we can get records of User Type when server is down
    Given User is on get user by user type request
    When User sends get request with user type "<endointuser>"
    Then Status code 500 Internal Server Error will be displayed

    Examples: 
      | endpointuser             |
      | Users/UserType=Patient   |
      | Users/UserType=Dietician |
