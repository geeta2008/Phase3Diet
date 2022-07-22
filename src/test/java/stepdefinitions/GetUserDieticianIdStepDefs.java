package stepdefinitions;

import static io.restassured.RestAssured.given;

import org.junit.Assert;

import AppHooks.ApplicationHook;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetUserDieticianIdStepDefs {
	
	RequestSpecification getrequest;
	Response response;
	
	/*
	 * GET users records By valid DieticianId
	 */
	@Given("User sets GET request with endpoint \\/DieticianId=DieticianID")
	public void user_sets_get_request_with_endpoint_dietician_id_dietician_id() {
		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = ApplicationHook.prop.getProperty("UserDieticianId_basePath_valid");
		getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");
	}

	@When("User sends GET request DieticianID")
	public void user_sends_get_request_dietician_id() {
		response = getrequest.when().get();
	}

	@Then("User will get {int} Ok status code")
	public void user_will_get_ok_status_code(Integer statusCode) {
		response.then().log().all();
		int statuscode = response.getStatusCode();
		Assert.assertEquals(statuscode, statusCode.intValue());
	}
	

	/*
	 * GET users records By Invalid Dietician Id
	 */
	
	@Given("User sets GET request with endpoint \\/DieticianId={string}")
	public void user_sets_get_request_with_endpoint_dietician_id(String dirticianId) {
		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = "Users/DieticianId=" +dirticianId;
		getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");
	}

	@When("User sends GET request with endpoint \\/DieticianId")
	public void user_sends_get_request_with_endpoint_dietician_id() {
		response = getrequest.when().get();
	}

	@Then("Status {int} ok will be given with msg")
	public void status_ok_will_be_given_with_msg(Integer statusCode) {
		response.then().log().all();
		int statuscode = response.getStatusCode();
		String msg = response.jsonPath().getString("msg");
//		ResponseBody message =response.getBody();
		String expMessge = "Item not found!";
		System.out.println("response body is" +msg) ;
		// status code validation
		Assert.assertEquals(statuscode, statusCode.intValue());
		Assert.assertEquals(msg,expMessge);
	}

	/*
	 * GET users records By Invalid EndPoint
	 */
	
	@Given("User sets GET request with Invalid endpoint \\/DieticianI")
	public void user_sets_get_request_with_invalid_endpoint_dietician_i() {
		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath =  ApplicationHook.prop.getProperty("UserDieticianId_basePath_invalid");
		getrequest =  given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");
	}

	@When("User sends GET request with endpoint \\/DieticianI")
	public void user_sends_get_request_with_endpoint_dietician_i() {
		response = getrequest.when().get();
	}

	@Then("Status {int} Not Found will be given")
	public void status_not_found_will_be_given(Integer statusCode) {
		response.then().log().all();
		int statuscode = response.getStatusCode();
		Assert.assertEquals(statuscode, statusCode.intValue());
	}
}
