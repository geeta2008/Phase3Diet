package MorbidityAPI;

import static io.restassured.RestAssured.given;

import org.junit.Assert;

import AppHooks.ApplicationHook;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetMorbidityAPI {
	{
		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = ApplicationHook.prop.getProperty("Morbidity_basePath_valid");
		
	}
	RequestSpecification getrequest;
	Response Morbidityresponse;

	
	@Given("User sets Get request with endpoint")
	public void user_sets_get_request_with_endpoint() {
		getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");
	}
	
	@When("User send Get request")
	public void user_send_get_request() {
		Morbidityresponse=getrequest.when().get();
	}
	
	@Then("Status {int} Ok")
	public void status_ok(Integer int1) {
		Morbidityresponse.then().log().all();
		int statuscode = Morbidityresponse.getStatusCode();
		Assert.assertEquals(statuscode, 200);
	}

}
