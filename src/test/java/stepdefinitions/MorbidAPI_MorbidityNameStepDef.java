package stepdefinitions;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
//import org.junit.Assert;

import AppHooks.ApplicationHook;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class MorbidAPI_MorbidityNameStepDef {

	RequestSpecification getrequest;
	Response response;

	@Given("For MorbidityName Get request, the Authorization is set to Basic auth with servers running.")
	public void for_morbidity_name_get_request_the_authorization_is_set_to_basic_auth_with_servers_running() {

		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = ApplicationHook.prop.getProperty("MorbidityName_basePath_valid"); 
		getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");

	}

	@When("User will sends Get request.")
	public void user_will_sends_get_request() {

		response = getrequest.when().get();

	}

	@Then("User will checks for {int}  Ok and")
	public void user_will_checks_for_ok_and(Integer statusCode, io.cucumber.datatable.DataTable dataTable) {

		response.then().log().all();
		int statuscode = response.getStatusCode();

		// status code validation
		Assert.assertEquals(statuscode, statusCode.intValue());

		// getting dataTable values from feature file.
	    List<Map<String, String>> getDataFromDataTable = dataTable.asMaps(String.class, String.class);

		if (!getDataFromDataTable.get(0).containsKey("blank")) {
			// json schema validation
			response.then().assertThat().body(JsonSchemaValidator 
					.matchesJsonSchema(new File(ApplicationHook.prop.getProperty("MorbidityName_JsonSchema"))));

			// Json Body Validation
			
			List<Map<String, String>> getDataFromResponse = response.jsonPath().getList("Items");
		
		
			Assert.assertEquals(getDataFromDataTable.get(0).get("MorbidityTestId").trim(), getDataFromResponse.get(0).get("MorbidityTestId").trim());
			Assert.assertEquals(getDataFromDataTable.get(0).get("MorbidityName").trim(), getDataFromResponse.get(0).get("MorbidityName").trim());
			Assert.assertEquals(getDataFromDataTable.get(0).get("MorbidityMarkerRef").trim(), getDataFromResponse.get(0).get("MorbidityMarkerRef").trim());
			Assert.assertEquals(getDataFromDataTable.get(0).get("MorbidityTestUnit").trim(), getDataFromResponse.get(0).get("MorbidityTestUnit").trim());
			Assert.assertEquals(getDataFromDataTable.get(0).get("MorbidityTestName").trim(), getDataFromResponse.get(0).get("MorbidityTestName").trim());
			

		}

	}
	
	

	@Given("For MorbidityName Get request, the invalid Authorization is set to Basic auth with servers running.")
	public void for_morbidity_name_get_request_the_invalid_authorization_is_set_to_basic_auth_with_servers_running() {

		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = ApplicationHook.prop.getProperty("MorbidityName_basePath_valid"); 
		getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_invalid"), ApplicationHook.prop.getProperty("password_invalid")).contentType("application/json");

	}

	@Given("For MorbidityName Get request, the Authorization is set to Basic auth and invalid endpoints servers running.")
	public void for_morbidity_name_get_request_the_authorization_is_set_to_basic_auth_and_invalid_endpoints_servers_running() {

		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = ApplicationHook.prop.getProperty("MorbidityName_basePath_invalid"); 
		getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");


	}

	@Given("For MorbidityName Get request, the Authorization is set to Basic auth with servers NOT running.")
	public void for_morbidity_name_get_request_the_authorization_is_set_to_basic_auth_with_servers_not_running() {

		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = ApplicationHook.prop.getProperty("MorbidityName_basePath_valid"); 
		getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");


	}

}
