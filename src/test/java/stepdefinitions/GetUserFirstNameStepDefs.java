package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.MatcherAssert.*;
import org.junit.Assert;

import AppHooks.ApplicationHook;

import static io.restassured.RestAssured.given;

public class GetUserFirstNameStepDefs {
	
	RequestSpecification getrequest;
	Response response;
	
	/*
	 * GET users records By valid First Name
	 */
	
	@Given("User sets GET request with endpoint \\/FirstName=ValidFirstname")
	public void user_sets_get_request_with_endpoint_first_name_valid_firstname() {
		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = ApplicationHook.prop.getProperty("UserFirstName_basePath_valid");
		getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");
	}

	@When("User sends GET request")
	public void user_sends_get_request() {
		response = getrequest.when().get();
	}

	@Then("User will get {int} Ok Status with correct Response Body")
	public void user_will_get_ok_status_with_correct_response_body(Integer statusCode) {
		response.then().log().all();
		int statuscode = response.getStatusCode();

		// status code validation
		Assert.assertEquals(statuscode, statusCode.intValue());
	}
	
	/*
	 * GET users records By Invalid First Name
	 */

	@Given("User sets GET request with endpoint \\/FirstName={string}")
	public void user_sets_get_request_with_endpoint_first_name(String firstname) {
		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = "Users/FirstName=" +firstname;
		getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");
		
		
	}

	@When("User sends GET request with endpoint")
	public void user_sends_get_request_with_endpoint() {
		response = getrequest.when().get();
	}

	@Then("Status {int} ok will be displayed with message")
	public void status_ok_will_be_displayed_with_message(Integer statusCode) {
		response.then().log().all();
		int statuscode = response.getStatusCode();
		String msg = response.jsonPath().getString("Message");
		//ResponseBody message =response.getBody();
		String expMessge = "Item not found!";
		System.out.println("response body is" +msg);
		// status code validation
		Assert.assertEquals(statuscode, statusCode.intValue());
		Assert.assertEquals(msg,expMessge);
	}
	

	/*
	 * GET users records By Invalid EndPoint
	 */
	@Given("User sets GET request with Invalid endpoint Firstame=firtsname")
	public void user_sets_get_request_with_invalid_endpoint_firstame_firtsname() {
		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath =  ApplicationHook.prop.getProperty("UserFirstName_basePath_invalid");
		getrequest =  given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");
	}

	@When("User sends GET request with invalid endpoint")
	public void user_sends_get_request_with_invalid_endpoint() {
		response = getrequest.when().get();
	}

	@Then("Status {int} Not Found will be displayed with message")
	public void status_not_found_will_be_displayed_with_message(Integer statusCode) {
		response.then().log().all();
		int statuscode = response.getStatusCode();
		Assert.assertEquals(statuscode, statusCode.intValue());
	}
	
	/*
	 * GET users records By Invalid Authorization
	 */
	
	@Given("The Authorization is set to Invalid Basic auth")
	public void the_authorization_is_set_to_invalid_basic_auth() {
		RestAssured.baseURI =ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = ApplicationHook.prop.getProperty("UserFirstName_basePath_valid");
		getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_invalid"), ApplicationHook.prop.getProperty("password_invalid")).contentType("application/json");
	}

	@When("User sends GET request with Invalid endpoint \\/FirstName=ValidFirstname")
	public void user_sends_get_request_with_invalid_endpoint_first_name_valid_firstname() {
		response = getrequest.when().get();
	}

	@Then("Status {int} Unauthorised Acces will be displayed with Message")
	public void status_unauthorised_acces_will_be_displayed_with_message(Integer statusCode) {
		response.then().log().all();
		int statuscode = response.getStatusCode();
		Assert.assertEquals(statuscode, statusCode.intValue());
	}
	
	/*
	 * GET users records when Servers Not running
	 */

	@Given("For Get request, the Authorization is set to Basic auth with servers not running")
	public void for_get_request_the_authorization_is_set_to_basic_auth_with_servers_not_running() {
		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath =  ApplicationHook.prop.getProperty("UserFirstName_basePath");
		getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_invalid"), ApplicationHook.prop.getProperty("password_invalid ")).contentType("application/json");
	}

	@When("User sends GET request with valid endpoint \\/FirstName=ValidFirstname")
	public void user_sends_get_request_with_valid_endpoint_first_name_valid_firstname() {
		response = getrequest.when().get();
	}

	@Then("Status {int} INTERNAL SERVER ERROR")
	public void status_internal_server_error(Integer statusCode) {
		response.then().log().all();
		int statuscode = response.getStatusCode();
		Assert.assertEquals(statuscode, statusCode.intValue());
	}

	
	
	
	



	
	


}






