package MorbidityAPI;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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


public class MorbidPutAPIStepDef {

	RequestSpecification getrequest;
	Response response;

	@Given("For Morbidity Put request, the Authorization is set to Basic auth with servers running and paramteres {string} , {string} ,{string} , {string}")
	public void for_morbidity_put_request_the_authorization_is_set_to_basic_auth_with_servers_running_and_paramteres(String MorbidityName, String MorbidityTestId, String MorbidityMarkerRef, String MorbidityTestUnit) {

		HashMap<String, String> putMorbidityData = new HashMap<String, String>();
		putMorbidityData.put(ApplicationHook.prop.getProperty("NameOf_Put_BodyParameter_MorbidityTestUnit"), MorbidityTestUnit );
		putMorbidityData.put(ApplicationHook.prop.getProperty("NameOf_Put_BodyParameter_MorbidityMarkerRef"), MorbidityMarkerRef);
		

		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = ApplicationHook.prop.getProperty("Morbidity_Put_basePath_valid").replace("MorbidityNameValue", MorbidityName).replace("GiveMorbidityTestIdValue", MorbidityTestId); 
		getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json").body(putMorbidityData);

	}

	@When("User will send Put request.")
	public void user_will_send_put_request() {

		response = getrequest.when().put();

	}

	@Then("User will check for {int}  {string}  {string}")
	public void user_will_check_for(Integer statusCode , String MorbidityMarkerRef, String MorbidityTestUnit) {

		response.then().log().all();
		int statuscode = response.getStatusCode();

		// status code validation
		Assert.assertEquals(statuscode, statusCode.intValue());

		//Json Scehma Validation
			response.then().assertThat().body(JsonSchemaValidator 
				.matchesJsonSchema(new File(ApplicationHook.prop.getProperty("MorbidityName_JsonSchema"))));

		// Json Body Validation
		Map<String, String> getDataFromResponse = response.jsonPath().getMap("");
		Assert.assertEquals(MorbidityMarkerRef, getDataFromResponse.get("MorbidityMarkerRef").trim());
		Assert.assertEquals(MorbidityTestUnit, getDataFromResponse.get("MorbidityTestUnit").trim());

	}

}


