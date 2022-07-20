package stepdefinitions;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.testng.Assert;
//import org.junit.Assert;

import AppHooks.ApplicationHook;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class MorbidAPIStepDef {

	RequestSpecification getrequest;
	Response response;


	@Given("For Morbidity Get request, the Authorization is set to Basic auth with servers running.")
	public void for_morbidity_get_request_the_authorization_is_set_to_basic_auth_with_servers_running() {

		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = ApplicationHook.prop.getProperty("Morbidity_basePath_valid"); 
		getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");

	}

	@When("User will send Get requestt.")
	public void user_will_send_get_requestt() {

		response = getrequest.when().get();

	}

	@Then("User will check for {int}  Ok.")
	public void user_will_check_for_ok(Integer statusCode, io.cucumber.datatable.DataTable dataTable) {

		response.then().log().all();
		int statuscode = response.getStatusCode();

		// status code validation
		Assert.assertEquals(statuscode, statusCode.intValue());

		// getting dataTable values from feature file.
		List<List<String>> getDataFromDataTable = dataTable.asLists();

//		if (getDataFromDataTable.get(0).size() == 0){
		if (!getDataFromDataTable.get(0).contains("blank")){
			// json schema validation
			response.then().assertThat().body(JsonSchemaValidator 
					.matchesJsonSchema(new File(ApplicationHook.prop.getProperty("MorbidityName_JsonSchema"))));

			// Json Body Validation
			JsonPath jsResponse = new JsonPath(response.asString());
			List<String> listOfMorbidityNameFromResponse = jsResponse.get("Items.MorbidityName");
			List<String> listOfMorbidityIDFromResponse = jsResponse.get("Items.MorbidityTestId");

			List<String> listOfMorbidityNameFromDataTable = getDataFromDataTable.get(0);
			List<String> listOfMorbidityIDFromDataTable = getDataFromDataTable.get(1);

			// Assert the list for body validation
			Assert.assertTrue(equalLists(listOfMorbidityNameFromResponse, listOfMorbidityNameFromDataTable));
			Assert.assertTrue(equalLists(listOfMorbidityIDFromResponse, listOfMorbidityIDFromDataTable));
		}

	}

	public boolean equalLists(List<String> one, List<String> two) {
		if (one == null && two == null) {
			return true;
		}

		if ((one == null && two != null) || one != null && two == null || one.size() != two.size()) {
			return false;
		}

		// to avoid messing the order of the lists we will use a copy
		one = new ArrayList<String>(one);
		two = new ArrayList<String>(two);

		Collections.sort(one);
		Collections.sort(two);
		return one.equals(two);
	}

	@Given("For Morbidity Get request, the invalid Authorization is set to Basic auth with servers running.")
	public void for_morbidity_get_request_the_invalid_authorization_is_set_to_basic_auth_with_servers_running() {

		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = ApplicationHook.prop.getProperty("Morbidity_basePath_valid"); 
		getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_invalid"), ApplicationHook.prop.getProperty("password_invalid")).contentType("application/json");


	}

	@Given("For Morbidity Get request, the Authorization is set to Basic auth and invalid endpoints with servers running.")
	public void for_morbidity_get_request_the_authorization_is_set_to_basic_auth_and_invalid_endpoints_with_servers_running() {
		
		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = ApplicationHook.prop.getProperty("Morbidity_basePath_invalid"); 
		getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");

	}

	@Given("For Morbidity Get request, the Authorization is set to Basic auth with servers NOT running.")
	public void for_morbidity_get_request_the_authorization_is_set_to_basic_auth_with_servers_not_running() {
		
		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = ApplicationHook.prop.getProperty("Morbidity_basePath_valid"); 
		getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");


	}

}
