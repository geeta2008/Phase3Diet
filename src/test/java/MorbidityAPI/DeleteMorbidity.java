package MorbidityAPI;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.testng.Assert;

import AppHooks.ApplicationHook;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.specification.RequestSpecification;

public class DeleteMorbidity {
	
	{
		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = ApplicationHook.prop.getProperty("MorbidityDelete_basePath_valid");
		
		
	}
	//RequestSpecification Deletemorbidityrequest1;
	RequestSpecification Deletemorbidityrequest;
	Response morbiditydeleteresponse;
	
	@Given("User sets Delete request  with endpoint {string},{string} with valid authorization")
	public void user_sets_delete_request_with_endpoint_with_valid_authorization(String MorbidityName, String MorbidityTestId) {
		RestAssured.basePath = ApplicationHook.prop.getProperty("MorbidityDelete_basePath_valid").replace("<MorbidityName>",MorbidityName).replace("<MorbidityTestId>",MorbidityTestId);
		Deletemorbidityrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");
	}
	
	@When("User sends Delete request")
	public void user_sends_delete_request(){
	 	morbiditydeleteresponse = Deletemorbidityrequest.when().delete();
		System.out.println(morbiditydeleteresponse.statusCode());
		
		//Assert.assertTrue(endpoint.contains(deleteresponse.get("MorbidityName,")));
		//Assert.assertTrue(endpoint.contains(deleteresponse.get("MorbidityTestId")));
	}
	@SuppressWarnings("unused") 
	@Then("Status {int} OK is displayed with Morbidity Name, MorbidityTestId and Message as {string} is displayed")
	public void status_ok_is_displayed_with_morbidity_name_morbidity_test_id_and_message_as_is_displayed(Integer status, String Message1) {
		JsonPath deleteresponse = new JsonPath(morbiditydeleteresponse.asPrettyString());
		int statuscode = morbiditydeleteresponse.getStatusCode();
		String Message = morbiditydeleteresponse.jsonPath().getString("Message");
		Assert.assertEquals(statuscode,status.intValue());
		assertEquals(Message,Message1);
	    
	}

	@Given("User sets Delete request with non existing with endpoint {string},{string} with valid authorization")
	public void user_sets_delete_request_with_non_existing_with_endpoint_with_valid_authorization(String MorbidityName, String MorbidityTestId) {
		RestAssured.basePath = ApplicationHook.prop.getProperty("MorbidityDelete_basePath_valid").replace("<MorbidityName>",MorbidityName).replace("<MorbidityTestId>",MorbidityTestId);
		Deletemorbidityrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");
	}

	@When("User will sends Delete request")
	public void user_will_sends_delete_request() {
		morbiditydeleteresponse = Deletemorbidityrequest.when().delete();
		System.out.println(morbiditydeleteresponse.statusCode());
		
	}
	@SuppressWarnings("unused")
	@Then("Status {int} Not Found is displayed with a Message {string} is displayed")
	public void status_not_found_is_displayed_with_a_message_is_displayed(Integer status, String Message1) {
		
		JsonPath deleteresponse = new JsonPath(morbiditydeleteresponse.asPrettyString());
		int statuscode = morbiditydeleteresponse.getStatusCode();
		String Message = morbiditydeleteresponse.jsonPath().getString("Message");
		Assert.assertEquals(statuscode,status.intValue());
		assertEquals(Message,Message1);
	}

	@Given("User set Delete request with incorrect endpoint {string},{string} with valid authorization")
public void user_set_delete_request_with_incorrect_endpoint_with_valid_authorization(String MorbidityName, String MorbidityTestName) {
		RestAssured.basePath = ApplicationHook.prop.getProperty("MorbidityDelete_basePath_valid").replace("<MorbidityName>",MorbidityName).replace("<MorbidityTestName>",MorbidityTestName);
		Deletemorbidityrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");
	
}

@When("User will send Delete request")
public void user_will_send_delete_request() {
	morbiditydeleteresponse = Deletemorbidityrequest.when().delete();
	System.out.println(morbiditydeleteresponse.statusCode());
	}

@Then("Status {int} Method Not Allowed will be displayed with a message {string}")
public void status_method_not_allowed_will_be_displayed_with_a_message(Integer status, String Message1) {
 	JsonPath deleteresponse = new JsonPath(morbiditydeleteresponse.asPrettyString());
	int statuscode = morbiditydeleteresponse.getStatusCode();
	String Message = morbiditydeleteresponse.jsonPath().getString("Message");
	Assert.assertEquals(statuscode,status.intValue());
	assertEquals(Message,Message1);
}

@Given("User set Delete request  with endpoint {string},{string} with Invalid UserName and valid Password or valid UserName and Invalid Password")
public void user_set_delete_request_with_endpoint_with_invalid_user_name_and_valid_password_or_valid_user_name_and_invalid_password(String MorbidityName, String MorbidityTestId) {
    RestAssured.basePath = ApplicationHook.prop.getProperty("MorbidityDelete_basePath_valid").replace("<MorbidityName>",MorbidityName).replace("<MorbidityTestName>",MorbidityTestId);
	Deletemorbidityrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_invalid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");

}

@When("User send Delete request")
public void user_send_delete_request() {
	morbiditydeleteresponse = Deletemorbidityrequest.when().delete();
	System.out.println(morbiditydeleteresponse.statusCode());
}

@SuppressWarnings("unused")
@Then("Status {int} Unauthorised Access will be displayed")
public void status_unauthorised_access_will_be_displayed(Integer status) {
	JsonPath deleteresponse = new JsonPath(morbiditydeleteresponse.asPrettyString());
	int statuscode = morbiditydeleteresponse.getStatusCode();
	//String Message = morbiditydeleteresponse.jsonPath().getString("Message");
	Assert.assertEquals(statuscode,status.intValue());
	//assertEquals(Message,Message1);
}

	
}
