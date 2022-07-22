package stepdefinitions;

import java.io.File;

//import org.junit.Assert;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetUser_UserType {
	
	RequestSpecification request;
	Response response;
	String dieticianAPI_url="http://127.0.0.1:5000/api/";
	String userEndpoint = "Users/UserType=Patient";
	String username = "KMSASM2022";
	String password = "Dietician1!";
	@Given("User is on Get Request with {string}")
	public void user_is_on_get_request_with(String endpoint) {
		
		RestAssured.baseURI = dieticianAPI_url;
		RestAssured.basePath = endpoint;
		request = RestAssured.given().auth().preemptive().basic(username, password).contentType("application/json"); 
		
	}

	@When("User sends Get Request with authorization")
	public void user_sends_get_request_with_authorization() {
		response = request.when().get();
	}

	@Then("Status code {int} should be displayed")
	public void status_code_should_be_displayed(Integer status) {
		response.then().log().all();
		String str = response.asPrettyString();
		int code = status;
		int statuscode = response.getStatusCode();
		System.out.println(response.getStatusLine());
		Assert.assertEquals(statuscode, code);
		if (statuscode==200) {
			Assert.assertTrue(response.getStatusLine().contains("200 OK"));
			System.out.println("*****************Verfied that status line contains 200 OK*****************");
			Assert.assertTrue(response.contentType().contains("application/json"));
			Assert.assertTrue(str.contains("FoodCategory"));
			Assert.assertTrue(str.contains("Email"));
			Assert.assertTrue(str.contains("Address"));
			Assert.assertTrue(str.contains("FirstName"));
			Assert.assertTrue(str.contains("UserId"));
			Assert.assertTrue(str.contains("LastName"));
			Assert.assertTrue(str.contains("Contact"));
            response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/Schema/DieticianAPI.json")));
			
	    	System.out.println("************Schema Validation Successful**************");
			
			
		}
		
	}

	@Given("User is on Get User-User Type request")
	public void user_is_on_get_user_user_type_request() {
		RestAssured.baseURI = dieticianAPI_url;
		//RestAssured.basePath = endpoint;
		request = RestAssured.given().auth().preemptive().basic(username, password).contentType("application/json");
	}

	@When("User sends request with invalid User Type {string}")
	public void user_sends_request_with_invalid_user_type(String endpoint) {
		response = request.when().get(endpoint);
	}

	@Then("Status {string} Not found should be displayed")
	public void status_not_found_should_be_displayed(String code) {
	    response.then().log().all();
		int status = Integer.parseInt(code);
	    int statuscode = response.getStatusCode();
	    Assert.assertEquals(status, statuscode);
	    System.out.println("*************"+response.getStatusLine()+"*******************");
	}

	@Given("User is on Get User Type request")
	public void user_is_on_get_user_type_request() {
		RestAssured.baseURI = dieticianAPI_url;
		RestAssured.basePath = userEndpoint;
		request = RestAssured.given().contentType("application/json"); 
	}

	@When("User sends Get request with no authorization")
	public void user_sends_get_request_with_no_authorization() {
	    response = request.when().get();
	}

	@Then("Status code {int} Unauthorized should be displayed")
	public void status_code_unauthorized_should_be_displayed(Integer status) {
	    response.then().log().all();
	    int statuscode = status;
	    System.out.println("****************"+response.getStatusLine()+"*************************");
	    Assert.assertEquals(statuscode, response.getStatusCode());
	    
	}

	@Given("User is on Get User Type request with invalid {string} and {string}")
	public void user_is_on_get_user_type_request_with_invalid_and(String username, String password) {
		RestAssured.baseURI = dieticianAPI_url;
		
		request = RestAssured.given().auth().preemptive().basic(username, password).contentType("application/json");
	}

	@When("User sends Get request with {string}")
	public void user_sends_get_request_with(String endpoint) {
		
		response = request.when().get(endpoint);
	}

	@Then("Status {int} Unauthorized access should be displayed")
	public void status_unauthorized_access_should_be_displayed(Integer status) {
		response.then().log().all();
	    int statuscode = status;
	    System.out.println("****************"+response.getStatusLine()+"*************************");
	    Assert.assertEquals(statuscode, response.getStatusCode());
	}

	@Given("User is on get user by user type request")
	public void user_is_on_get_user_by_user_type_request() {
		
        RestAssured.baseURI = dieticianAPI_url;	
		request = RestAssured.given().auth().preemptive().basic(username, password).contentType("application/json");
	}

	@When("User sends get request with user type {string}")
	public void user_sends_get_request_with_user_type(String endpoint) {
	    response = request.when().get(endpoint);
	}

	@Then("Status code {int} Internal Server Error will be displayed")
	public void status_code_internal_server_error_will_be_displayed(Integer status) {
	    System.out.println("*****************"+response.getStatusLine()+"*****************");
	    int statuscode = response.getStatusCode();
	    if (status==statuscode) {
	    	System.out.println("500 Internal Server Error");
	    }
	}

}
