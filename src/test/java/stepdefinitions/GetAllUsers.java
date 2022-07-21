
package stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.Assert;

import AppHooks.ApplicationHook;

public class GetAllUsers {

	RequestSpecification getAllUsersRequest;
	Response getAllUsersResponse;
	String username;
	String password;
	String getPostUsersEndpoint;
	String requestBody;
	Object firstName, lastName, address, contact, email, foodCategory, allergy, loginUsername, userPassword, userType,
			dieticianId;

	@Before
	public void setUp() throws IOException {
		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = ApplicationHook.prop.getProperty("getPostUsersBasePath");
	}

	@Given("User is on GET request with Users endpoint")
	public void user_is_on_get_request_with_users_endpoint() {
		username = ApplicationHook.prop.getProperty("username");
		password = ApplicationHook.prop.getProperty("password");
		getAllUsersRequest = given().contentType("application/json").auth().preemptive().basic(username, password);
	}

	@When("User sends GET request")
	public void user_sends_get_request() {
		getAllUsersResponse = getAllUsersRequest.when().get();

	}

	@Then("User should be displayed success status code {int} with list of all users.")
	public void user_should_be_displayed_success_status_code_with_list_of_all_users(Integer statuscode) {
		// StatusCode Validation
		int statusCode = getAllUsersResponse.getStatusCode();
		System.out.println("statusCode is : " + statusCode);
		// response.then().assertThat().statusCode(statuscode.intValue());
		Assert.assertEquals(statusCode, statuscode.intValue());

		// Response Body Validation
		Assert.assertNotNull(getAllUsersResponse);

		// JSON Schema Validation
		getAllUsersResponse.then()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/DieticianAPISchema.json")).extract()
				.response();
		System.out.println("JsonSchema is validated successfully for Users GET method");
	}

}
