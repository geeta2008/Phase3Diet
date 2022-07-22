package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
//import org.junit.Assert;
import org.testng.Assert;

public class DeleteUser {
	RequestSpecification request;
	Response response;
	String dieticianAPI_url="http://127.0.0.1:5000/api/";
	String userEndpoint = "Users/UserType=Patient";
	String username = "KMSASM2022";
	String password = "Dietician1!";
	
	@Given("User is on delete user request")
	public void user_is_on_delete_user_request() {
		RestAssured.baseURI = dieticianAPI_url;
		
		request = RestAssured.given().auth().preemptive().basic(username, password).contentType("application/json");
	}

	@When("User sends delete user request with existing dietician and user id {string}")
	public void user_sends_delete_user_request_with_existing_dietician_and_user_id(String endpoint) {
		response = request.when().delete(endpoint);
		System.out.println(response.statusCode());
		JsonPath jp = new JsonPath(response.asPrettyString());
		Assert.assertTrue(endpoint.contains(jp.get("UserId")));
		Assert.assertTrue(endpoint.contains(jp.get("DieticianId")));
		Assert.assertEquals(jp.get("Message"), "Successfully Deleted.");
		
		
	}

	@Then("Status code {int} successfully deleted should be displayed")
	public void status_code_successfully_deleted_should_be_displayed(Integer status) {
	   // response.then().log().all();
	    int code = status;
	    int statuscode = response.getStatusCode();
	    Assert.assertEquals(code, statuscode);
	    
	    
	    System.out.println(response.jsonPath().prettyPrint());
	    System.out.println("**********Status code "+statuscode+" validation is successful*************");
	    //Assert.assertEquals(code, statuscode);
	    
	}

	@Given("User is on delete user request with endpoint")
	public void user_is_on_delete_user_request_with_endpoint() {
        RestAssured.baseURI = dieticianAPI_url;
		request = RestAssured.given().auth().preemptive().basic(username, password).contentType("application/json");
	}

	@When("User sends delete request with incorrect dietician id and user id {string}")
	public void user_sends_delete_request_with_incorrect_dietician_id_and_user_id(String endpoint) {
	    response = request.when().delete(endpoint);
	}

	@Then("Status {int} user not found should be displayed")
	public void status_user_not_found_should_be_displayed(Integer status) {
		response.then().log().all();
		String str = response.asPrettyString();
		int statuscode = status;
		//System.out.println(response.statusCode());
		
		JsonPath jp = new JsonPath(str);
		Assert.assertEquals(jp.get("Message"), "Already Deleted OR wrong DieticinaId ,UserId.");
		Assert.assertEquals(statuscode, response.getStatusCode());
		System.out.println("**********Status code "+statuscode+" validation is successful*************");
		
	}

	@Given("User is on Delete request with invalid credentials {string} and {string}")
	public void user_is_on_delete_request_with_invalid_credentials_and(String username, String password) {
		 RestAssured.baseURI = dieticianAPI_url;
		 request = RestAssured.given().auth().preemptive().basic(username, password).contentType("application/json");
	}

	@When("User sends delete request with correct dietician and user {string}")
	public void user_sends_delete_request_with_correct_dietician_and_user(String endpoint) {
		response = request.when().delete(endpoint);
	}

	@Then("Status {int} Unauthorized should be displayed")
	public void status_unauthorized_should_be_displayed(Integer status) {
	    System.out.println(response.jsonPath().prettyPrint());
	    int code = response.getStatusCode();
	    int statuscode = status;
	    Assert.assertEquals(code, statuscode);
	    System.out.println("***************Status code "+statuscode+" validation is successfull***********");
	}


}
