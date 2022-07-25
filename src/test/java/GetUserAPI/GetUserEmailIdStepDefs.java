package GetUserAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.MatcherAssert.*;

import java.io.File;

import org.junit.Assert;

import AppHooks.ApplicationHook;

import static io.restassured.RestAssured.given;

public class GetUserEmailIdStepDefs {
	RequestSpecification getrequest;
	Response response;
	
	/*
	 * GET users records By valid EmailId
	 */
	
	@Given("User sets GET request with endpoint \\/Email=ValidEmailid")
	public void user_sets_get_request_with_endpoint_email_valid_emailid() {
		
		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = ApplicationHook.prop.getProperty("UserEmail_basePath_valid");
		getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");
	}

	@When("GET request is send by user")
	public void get_request_is_send_by_user() {
		response = getrequest.when().get();
	}

	@Then("User gets {int} Ok Status")
	public void user_gets_ok_status(Integer statusCode) {
		response.then().log().all();
		int statuscode = response.getStatusCode();
		Assert.assertEquals(statuscode, statusCode.intValue());
		
		// JSON Schema Validation
		response.then().assertThat().body(
				JsonSchemaValidator.matchesJsonSchema(new File(ApplicationHook.prop.getProperty("GetUser_JsonSchema"))));
         System.out.println("JsonSchema is validated successfully for Users GET method");
	}

	/*
	 * GET users records By Invalid Email Id
	 */
	@Given("User sets GET request with endpoint \\/Email={string}")
	public void user_sets_get_request_with_endpoint_email(String email) {
		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = "Users/Email=" +email;
		getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");
		
	}

	@When("GET request is send by user with EndPoint")
	public void get_request_is_send_by_user_with_end_point() {
		response = getrequest.when().get();
	}

	@Then("Status {int} not found will be given with messsage")
	public void status_not_found_will_be_given_with_messsage(Integer statusCode) {
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
	@Given("User sets GET request with Invalid endpoint mail=EmailId")
	public void user_sets_get_request_with_invalid_endpoint_mail_email_id() {
		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath =  ApplicationHook.prop.getProperty("UserEmail_basePath_invalid");
		getrequest =  given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");
	}

	@When("User sends GET request with invalid endpoint Email")
	public void user_sends_get_request_with_invalid_endpoint_email() {
		response = getrequest.when().get();
	}

	@Then("Status {int} Not Found will be given with message")
	public void status_not_found_will_be_given_with_message(Integer statusCode) {
		response.then().log().all();
		int statuscode = response.getStatusCode();
		Assert.assertEquals(statuscode, statusCode.intValue());
	}
}
