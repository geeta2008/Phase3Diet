package GetUserAPI;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.junit.Assert;

import AppHooks.ApplicationHook;

public class GerUserContactStepDefs {
	RequestSpecification getrequest;
	Response response;
	
	/*
	 * GET users records By valid Contact
	 */
	
	@Given("User sets GET request with endpoint \\/Contact=ValidContactNo")
	public void user_sets_get_request_with_endpoint_contact_valid_contact_no() {
		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = ApplicationHook.prop.getProperty("UserContact_basePath_valid");
		getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");
	}

	@When("User sends GET request with endpoint \\/Contact")
	public void user_sends_get_request_with_endpoint_contact() {
		response = getrequest.when().get();
	}

	@Then("User will get {int} Ok Status code")
	public void user_will_get_ok_status_code(Integer statusCode) {
		response.then().log().all();
		int statuscode = response.getStatusCode();
		Assert.assertEquals(statuscode, statusCode.intValue());
		
		// JSON Schema Validation
		response.then().assertThat().body(
				JsonSchemaValidator.matchesJsonSchema(new File(ApplicationHook.prop.getProperty("GetUser_JsonSchema"))));
         System.out.println("JsonSchema is validated successfully for Users GET method");
	}
	
	/*
	 * GET users records By Invalid Contact no
	 */

	@Given("User sets GET request with endpoint \\/Contact={string}")
	public void user_sets_get_request_with_endpoint_contact(String contactnum) {
		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = "Users/Contact=" +contactnum;
		getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");
	}

	@When("GET request sent by User with endpoint \\/Contact")
	public void get_request_sent_by_user_with_endpoint_contact() {
		response = getrequest.when().get();
	}
	
	@Then("Status {int} will be displayed with msg Not Found")
	public void status_will_be_displayed_with_msg_Not_Found(Integer statusCode) {
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

	@Given("User sets GET request with Invalid endpoint \\/Contact=ContactNo")
	public void user_sets_get_request_with_invalid_endpoint_contact_contact_no() {
		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath =  ApplicationHook.prop.getProperty("UserContact_basePath_invalid");
		getrequest =  given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");
	}

	@When("User sends GET request with invalid endpoint \\/Contact")
	public void user_sends_get_request_with_invalid_endpoint_contact() {
		response = getrequest.when().get();
	}

	@Then("Status {int} Not Found will be displayed")
	public void status_not_found_will_be_displayed(Integer statusCode) {
		response.then().log().all();
		int statuscode = response.getStatusCode();
		Assert.assertEquals(statuscode, statusCode.intValue());
	}


}