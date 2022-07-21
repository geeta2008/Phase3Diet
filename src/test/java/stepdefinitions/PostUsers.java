package stepdefinitions;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;

import com.util.XLSUtility;

import AppHooks.ApplicationHook;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.testng.Assert;

public class PostUsers {

	String JSON_SCHEMA;
	XLSUtility excelUtil;
	RequestSpecification postUsersRequest;
	Response postUsersResponse;
	String username;
	String password;
	String getPostUsersEndpoint;
	String requestBody;
	String userId;
	String postUsersMissingFieldName;
	//static String excelPath = ApplicationHook.prop.getProperty("postUsersExcelPath");
	Object firstName, lastName, contact, email, foodCategory, allergy, loginUsername, userPassword, userType,
			dieticianId;
	String address;

	@Before
	public void setUp() throws IOException {
		JSON_SCHEMA = ApplicationHook.prop.getProperty("dieticianJSONSchema");
		String excelPath= ApplicationHook.prop.getProperty("postUsersExcelPath");
		excelUtil = new XLSUtility(excelPath);
		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = ApplicationHook.prop.getProperty("getPostUsersBasePath");
	}

	@Given("User is on POST Request with Users endpoint")
	public void user_is_on_post_request_with_users_endpoint() {

		username = ApplicationHook.prop.getProperty("username");
		password = ApplicationHook.prop.getProperty("password");
		postUsersRequest = given().contentType("application/json").auth().preemptive().basic(username, password);

	}

	@When("User sends the POST request with valid users inputs")
	public void user_sends_the_post_request_with_valid_users_inputs() throws IOException {

		int rowCount = excelUtil.getRowCount("PostUsers");

		for (int i = 1; i <= rowCount; i++) {
			firstName = excelUtil.getCellData("PostUsers", i, 0);
			lastName = excelUtil.getCellData("PostUsers", i, 1);
			address = excelUtil.getCellData("PostUsers", i, 2);
			contact = excelUtil.getCellData("PostUsers", i, 3);
			email = excelUtil.getCellData("PostUsers", i, 4);
			foodCategory = excelUtil.getCellData("PostUsers", i, 5);
			allergy = excelUtil.getCellData("PostUsers", i, 6);
			loginUsername = excelUtil.getCellData("PostUsers", i, 7);
			userPassword = excelUtil.getCellData("PostUsers", i, 8);
			userType = excelUtil.getCellData("PostUsers", i, 9);
			dieticianId = excelUtil.getCellData("PostUsers", i, 10);

			JSONObject addressJson = (JSONObject) JSONValue.parse(address);

			JSONObject requestparams = new JSONObject();
			requestparams.put("FirstName", firstName);
			requestparams.put("LastName", lastName);
			requestparams.put("Address", addressJson);
			requestparams.put("Contact", contact);
			requestparams.put("Email", email);
			requestparams.put("FoodCategory", foodCategory);
			requestparams.put("Allergy", allergy);
			requestparams.put("LoginUsername", loginUsername);
			requestparams.put("Password", userPassword);
			requestparams.put("UserType", userType);
			requestparams.put("DieticianId", dieticianId);

			System.out.println("Post request is : " + requestparams.toString());
			postUsersResponse = postUsersRequest.body(requestparams).post();

			String userId = postUsersResponse.jsonPath().getString("UserId");
			System.out.println("UserId in First Scenario : " + userId);
			excelUtil.setCellData("PostUsers", i, 11, userId);

			System.out.println("Post response is : " + postUsersResponse.asPrettyString());
		}
	}

	@Then("User should be displayed success status code {int} with message Successfully Created. with automatically generated userid")
	public void user_should_be_displayed_success_status_code_with_message_successfully_created_with_automatically_generated_userid(
			Integer statusCode) {
		// postUsersResponse.then().log().all();
		// Assert.assertEquals(postUsersResponse.getStatusCode(),
		// statusCode.intValue());

		// StatusCode Validation
		postUsersResponse.then().assertThat().statusCode(statusCode.intValue());

		// Response Body Validation
		Assert.assertNotNull(postUsersResponse);
		Assert.assertNotNull("Userid");
		postUsersResponse.then().assertThat().body("Message", equalTo("Successfully Created."));

		// JSON Schema Validation
		postUsersResponse.then()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JSON_SCHEMA)).extract()
				.response();
		System.out.println("JsonSchema is validated successfully for Users POST method");
	}

	@When("User sends the POST request with either existing firstname or existing contact or existing email")
	public void user_sends_the_post_request_with_either_existing_firstname_or_existing_contact_or_existing_email()
			throws IOException {

		int rowCount = excelUtil.getRowCount("PostUsersExistOneField");

		for (int i = 1; i <= rowCount; i++) {
			firstName = excelUtil.getCellData("PostUsersExistOneField", i, 0);
			lastName = excelUtil.getCellData("PostUsersExistOneField", i, 1);
			address = excelUtil.getCellData("PostUsersExistOneField", i, 2);
			contact = excelUtil.getCellData("PostUsersExistOneField", i, 3);
			email = excelUtil.getCellData("PostUsersExistOneField", i, 4);
			foodCategory = excelUtil.getCellData("PostUsersExistOneField", i, 5);
			allergy = excelUtil.getCellData("PostUsersExistOneField", i, 6);
			loginUsername = excelUtil.getCellData("PostUsersExistOneField", i, 7);
			userPassword = excelUtil.getCellData("PostUsersExistOneField", i, 8);
			userType = excelUtil.getCellData("PostUsersExistOneField", i, 9);
			dieticianId = excelUtil.getCellData("PostUsersExistOneField", i, 10);

			JSONObject requestparams = new JSONObject();
			requestparams.put("FirstName", firstName);
			requestparams.put("LastName", lastName);
			requestparams.put("Address", new JSONObject());
			requestparams.put("Contact", contact);
			requestparams.put("Email", email);
			requestparams.put("FoodCategory", foodCategory);
			requestparams.put("Allergy", allergy);
			requestparams.put("LoginUsername", loginUsername);
			requestparams.put("Password", userPassword);
			requestparams.put("UserType", userType);
			requestparams.put("DieticianId", dieticianId);

			System.out.println("Post request is : " + requestparams.toString());
			postUsersResponse = postUsersRequest.body(requestparams).post();

			String userId = postUsersResponse.jsonPath().getString("UserId");
			excelUtil.setCellData("PostUsersExistOneField", i, 11, userId);

			System.out.println("Post response is : " + postUsersResponse.asPrettyString());
		}
	}

	@When("User sends the POST request with existing firstname, contact and email")
	public void user_sends_the_post_request_with_existing_firstname_contact_and_email() throws IOException {

		int rowCount = excelUtil.getRowCount("PostUsers");

		for (int i = 1; i <= rowCount; i++) {
			firstName = excelUtil.getCellData("PostUsers", i, 0);
			lastName = excelUtil.getCellData("PostUsers", i, 1);
			address = excelUtil.getCellData("PostUsers", i, 2);
			contact = excelUtil.getCellData("PostUsers", i, 3);
			email = excelUtil.getCellData("PostUsers", i, 4);
			foodCategory = excelUtil.getCellData("PostUsers", i, 5);
			allergy = excelUtil.getCellData("PostUsers", i, 6);
			loginUsername = excelUtil.getCellData("PostUsers", i, 7);
			userPassword = excelUtil.getCellData("PostUsers", i, 8);
			userType = excelUtil.getCellData("PostUsers", i, 9);
			dieticianId = excelUtil.getCellData("PostUsers", i, 10);

			JSONObject requestparams = new JSONObject();
			requestparams.put("FirstName", firstName);
			requestparams.put("LastName", lastName);
			requestparams.put("Address", new JSONObject());
			requestparams.put("Contact", contact);
			requestparams.put("Email", email);
			requestparams.put("FoodCategory", foodCategory);
			requestparams.put("Allergy", allergy);
			requestparams.put("LoginUsername", loginUsername);
			requestparams.put("Password", userPassword);
			requestparams.put("UserType", userType);
			requestparams.put("DieticianId", dieticianId);

			System.out.println("Post request is : " + requestparams.toString());
			postUsersResponse = postUsersRequest.body(requestparams).post();

			String userId = postUsersResponse.jsonPath().getString("UserId");
			excelUtil.setCellData("PostUsers", i, 11, userId);

			System.out.println("Post response is : " + postUsersResponse.asPrettyString());
		}

	}

	@Then("User should be displayed success status code {int} with message User detail already Exists. Check on [ Firstname, Contact , Email ]")
	public void user_should_be_displayed_success_status_code_with_message_user_detail_already_exists_check_on_firstname_contact_email(
			Integer statusCode) {

		// Response Body Validation
		Assert.assertNotNull(postUsersResponse);
		postUsersResponse.then().assertThat().body("Message",
				equalTo("User detail already Exists. Check on [ Firstname, Contact , Email ]"));

		// StatusCode Validation
		postUsersResponse.then().assertThat().statusCode(statusCode.intValue());

	}

	@When("User sends the POST request with mandatory fields")
	public void user_sends_the_post_request_with_mandatory_fields() throws IOException {

		int rowCount = excelUtil.getRowCount("PostUsersMissingFields");

		for (int i = 1; i <= rowCount; i++) {
			firstName = excelUtil.getCellData("PostUsersMissingFields", i, 0);
			lastName = excelUtil.getCellData("PostUsersMissingFields", i, 1);
			address = excelUtil.getCellData("PostUsersMissingFields", i, 2);
			contact = excelUtil.getCellData("PostUsersMissingFields", i, 3);
			email = excelUtil.getCellData("PostUsersMissingFields", i, 4);
			foodCategory = excelUtil.getCellData("PostUsersMissingFields", i, 5);
			allergy = excelUtil.getCellData("PostUsersMissingFields", i, 6);
			loginUsername = excelUtil.getCellData("PostUsersMissingFields", i, 7);
			userPassword = excelUtil.getCellData("PostUsersMissingFields", i, 8);
			userType = excelUtil.getCellData("PostUsersMissingFields", i, 9);
			dieticianId = excelUtil.getCellData("PostUsersMissingFields", i, 10);

			JSONObject requestparams = new JSONObject();
			requestparams.put("FirstName", firstName);
			requestparams.put("LastName", lastName);
			requestparams.put("Address", new JSONObject());
			requestparams.put("Contact", contact);
			requestparams.put("Email", email);
			requestparams.put("FoodCategory", foodCategory);
			requestparams.put("Allergy", allergy);
			requestparams.put("LoginUsername", loginUsername);
			requestparams.put("Password", userPassword);
			requestparams.put("UserType", userType);
			requestparams.put("DieticianId", dieticianId);

			System.out.println("Post request is : " + requestparams.toString());
			postUsersResponse = postUsersRequest.body(requestparams).post();

			postUsersMissingFieldName = excelUtil.getCellData("PostUsersMissingFields", i, 11);

			System.out.println("Post response is : " + postUsersResponse.asPrettyString());
		}
	}

	@Then("User should be displayed success status code {int} with message Missing Items OR Invalid Entry. Check on fieldname")
	public void user_should_be_displayed_success_status_code_with_message_missing_items_or_invalid_entry_check_on_fieldname(
			Integer statusCode) {

		postUsersResponse.then().assertThat().statusCode(statusCode.intValue());
		postUsersResponse.then().assertThat().body("Message",
				equalTo("Missing Items OR Invalid Entry. Check on ['" + postUsersMissingFieldName + "']"));
	}

	@When("User sends the POST request with empty request body")
	public void user_sends_the_post_request_with_empty_request_body() {
		postUsersResponse = postUsersRequest.body("").post();
	}

	@Then("User should be displayed status code {int} Bad Request")
	public void user_should_be_displayed_status_code_bad_request(Integer statusCode) {

		// Response Body Validation
		Assert.assertNotNull(postUsersResponse);
		postUsersResponse.then().assertThat().body("message",
				equalTo("The browser (or proxy) sent a request that this server could not understand."));

		// StatusCode Validation
		postUsersResponse.then().assertThat().statusCode(statusCode.intValue());

	}

	@When("User sends POST request with UserType other than Dietician or Patient")
	public void user_sends_post_request_with_user_type_other_than_Dietician_or_Patient() throws IOException {

		int rowCount = excelUtil.getRowCount("PostUsersUserType");

		for (int i = 1; i <= rowCount; i++) {
			firstName = excelUtil.getCellData("PostUsersUserType", i, 0);
			lastName = excelUtil.getCellData("PostUsersUserType", i, 1);
			address = excelUtil.getCellData("PostUsersUserType", i, 2);
			contact = excelUtil.getCellData("PostUsersUserType", i, 3);
			email = excelUtil.getCellData("PostUsersUserType", i, 4);
			foodCategory = excelUtil.getCellData("PostUsersUserType", i, 5);
			allergy = excelUtil.getCellData("PostUsersUserType", i, 6);
			loginUsername = excelUtil.getCellData("PostUsersUserType", i, 7);
			userPassword = excelUtil.getCellData("PostUsersUserType", i, 8);
			userType = excelUtil.getCellData("PostUsersUserType", i, 9);
			dieticianId = excelUtil.getCellData("PostUsersUserType", i, 10);

			JSONObject requestparams = new JSONObject();
			requestparams.put("FirstName", firstName);
			requestparams.put("LastName", lastName);
			requestparams.put("Address", new JSONObject());
			requestparams.put("Contact", contact);
			requestparams.put("Email", email);
			requestparams.put("FoodCategory", foodCategory);
			requestparams.put("Allergy", allergy);
			requestparams.put("LoginUsername", loginUsername);
			requestparams.put("Password", userPassword);
			requestparams.put("UserType", userType);
			requestparams.put("DieticianId", dieticianId);

			System.out.println("Post request is : " + requestparams.toString());
			postUsersResponse = postUsersRequest.body(requestparams).post();

			System.out.println("Post response is : " + postUsersResponse.asPrettyString());
		}
	}

	@Then("User should be displayed {int} validation error message UserType should be Dietician\\/Patient")
	public void user_should_be_displayed_validation_error_message_user_type_should_be_dietician_patient(
			Integer statusCode) {

		// Response Body Validation
		Assert.assertNotNull(postUsersResponse);

		// StatusCode Validation
		postUsersResponse.then().assertThat().statusCode(statusCode.intValue());
		// postUsersResponse.then().assertThat().body("Message", equalTo ("Successfully
		// Created."));
	}

}
