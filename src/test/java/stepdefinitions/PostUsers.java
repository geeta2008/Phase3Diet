package stepdefinitions;

import java.io.IOException;
import java.util.Properties;
import static org.hamcrest.Matchers.equalTo;

import com.util.XLSUtility;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostUsers {
	
	XLSUtility excelUtil;
	RequestSpecification postUsersRequest;
	Response postUsersResponse;
	String username;
	String password;
	String baseURI;
	String getPostUsersEndpoint;
	String requestBody;
	static String excelPath = "src/test/resources/exceldata/UsersPostAPI.xlsx";
	Properties properties = new Properties();
	Object firstName, lastName, address, contact, email, foodCategory, allergy, loginUsername, userPassword, userType, dieticianId;
	
	@Before
	public void setUp() throws IOException {
		FileInputStream fis = new FileInputStream("src\\test\\resources\\config\\config.properties");
		properties.load(fis);
		excelUtil = new XLSUtility(excelPath);
	}
	
	@Given("User is on POST Request with Users endpoint")
	public void user_is_on_post_request_with_users_endpoint() {
	   
	    baseURI = properties.getProperty("baseURI");
	    getPostUsersEndpoint = properties.getProperty("getPostUsersEndpoint");
		username = properties.getProperty("username");
		password = properties.getProperty("password");
	    postUsersRequest = given().auth().basic(username, password)
	    		.header("Content-Type", "application/json");
	    
	}

	@When("User sends the POST request with valid users inputs")
	public void user_sends_the_post_request_with_valid_users_inputs() throws IOException {
		firstName = excelUtil.getCellData("PostUsers", 1, 0);
		lastName = excelUtil.getCellData("PostUsers", 1, 1);
		address = excelUtil.getCellData("PostUsers", 1, 2);
		contact = excelUtil.getCellData("PostUsers", 1, 3);
		email = excelUtil.getCellData("PostUsers", 1, 4);
		foodCategory = excelUtil.getCellData("PostUsers", 1, 5);
		allergy = excelUtil.getCellData("PostUsers", 1, 6);
		loginUsername = excelUtil.getCellData("PostUsers", 1, 7);
		userPassword = excelUtil.getCellData("PostUsers", 1, 8);
		userType = excelUtil.getCellData("PostUsers", 1, 9);
		dieticianId = excelUtil.getCellData("PostUsers", 1, 10);
		
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
	    postUsersResponse = postUsersRequest
	    		.body(requestparams)
	    		.post(baseURI + getPostUsersEndpoint);
	    
	    System.out.println("Post response is : " + postUsersResponse.asPrettyString());
	}

	@Then("User should be displayed success status code {int} with message Successfully Created. with automatically generated userid")
	public void user_should_be_displayed_success_status_code_with_message_successfully_created_with_automatically_generated_userid(Integer statusCode) {
	    //postUsersResponse.then().log().all();
	    //Assert.assertEquals(postUsersResponse.getStatusCode(), statusCode.intValue());
	    postUsersResponse.then().assertThat().statusCode(statusCode.intValue());
		postUsersResponse.then().assertThat().body("Message", equalTo ("Successfully Created."));
	}

	@When("User sends the POST request with existing firstname and contact and non existing email")
	public void user_sends_the_post_request_with_existing_firstname_and_contact_and_non_existing_email() {
	    
	}

	@When("User sends the POST request with existing firstname and email and non existing contact")
	public void user_sends_the_post_request_with_existing_firstname_and_email_and_non_existing_contact() {
	    
	}

	@When("User sends the POST request with existing email and contact and non-existing firstname")
	public void user_sends_the_post_request_with_existing_email_and_contact_and_non_existing_firstname() {
	    
	}

	@When("User sends the POST request with existing firstname, contact and email")
	public void user_sends_the_post_request_with_existing_firstname_contact_and_email() {
	    
	}

	@Then("User should be displayed success status code {int} with message User detail already Exists. Check on [ Firstname, Contact , Email ]")
	public void user_should_be_displayed_success_status_code_with_message_user_detail_already_exists_check_on_firstname_contact_email(Integer int1) {
	    
	}

	@When("User sends the POST request with missing data from {string} and {int}")
	public void user_sends_the_post_request_with_missing_data_from_and(String string, Integer int1) {
	    
	}

	@Then("User should be displayed success status code {int} with message Missing Items OR Invalid Entry. Check on [{string}] and {int}")
	public void user_should_be_displayed_success_status_code_with_message_missing_items_or_invalid_entry_check_on_and(Integer int1, String string, Integer int2) {
	   
	}

	@When("User sends the POST request with empty request body")
	public void user_sends_the_post_request_with_empty_request_body() {
	    
	}

	@Then("User should be displayed status code {int} Bad Request")
	public void user_should_be_displayed_status_code_bad_request(Integer int1) {
	    
	}

	@When("User sends POST request with valid inputs")
	public void user_sends_post_request_with_valid_inputs() {
	    
	}

	@Then("User should be displayed message The browser \\(or proxy) sent a request that this server could not understand.")
	public void user_should_be_displayed_message_the_browser_or_proxy_sent_a_request_that_this_server_could_not_understand() {
	    
	}

	@When("User sends POST request with UserType as Doctor")
	public void user_sends_post_request_with_user_type_as_doctor() {
	    
	}

	@Then("User should be displayed {int} validation error message UserType should be Dietician\\/Patient")
	public void user_should_be_displayed_validation_error_message_user_type_should_be_dietician_patient(Integer int1) {
	    
	}

}

