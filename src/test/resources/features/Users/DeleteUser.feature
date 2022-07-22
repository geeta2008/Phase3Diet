Feature: Validate DeleteUser Request for Dietician Users API

  @Smoke @Regression
  Scenario Outline: Verify that user record gets deleted
    Given User is on delete user request
    When User sends delete user request with existing dietician and user id "<endpoint>"
    Then Status code 200 successfully deleted should be displayed

    Examples: 
      | endpoint                              |
      | Users/DieticianId=DT002&UserId=PT0006 |

  Scenario Outline: Verify if user record gets deleted when dietician and user id are incorrect
    Given User is on delete user request with endpoint
    When User sends delete request with incorrect dietician id and user id "<uri>"
    Then Status 404 user not found should be displayed

    Examples: 
      | uri                                |
      | Users/DieticianId=D002&UserId=U001 |
      | Users/DieticianId=&UserId=         |

  @NegativeTesting
  Scenario Outline: Verify if user record gets deleted without authorization
    Given User is on Delete request with invalid credentials "<username>" and "<password>"
    When User sends delete request with correct dietician and user "<id>"
    Then Status 401 Unauthorized should be displayed

    Examples: 
      | username | password    | id                                   |
      | user123  | password123 | Users/DieticianId=DT002&UserId=DT002 |
