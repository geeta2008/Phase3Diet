package MorbidityAPI;

import static io.restassured.RestAssured.given;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetMorbidityAPI {
	{
		RestAssured.baseURI = "http://127.0.0.1:5000/api/";
		RestAssured.basePath = "Morbidity/";

	}
	RequestSpecification getrequest;
	Response Morbidityresponse;

	
	@Given("User sets Get request with endpoint {string}")
	public void user_sets_get_request_with_endpoint(String string) {
		getrequest = given().auth().preemptive().basic("KMSASM2022", "Dietician1!").contentType("application/json");
	}

	@When("User sends Get request.")
	public void user_sends_get_request() {
		Morbidityresponse=getrequest.when().get();
	}
	
	@Then("Status {string}.")
	public void status(String string) {
		Morbidityresponse.then().log().all();
		int statuscode = Morbidityresponse.getStatusCode();
		Assert.assertEquals(statuscode, 200);
	}

}
