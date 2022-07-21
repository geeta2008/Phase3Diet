package MorbidityAPI;

import static io.restassured.RestAssured.given;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteMorbidity {
	
	{
		RestAssured.baseURI = "http://127.0.0.1:5000/api/";
		RestAssured.basePath = "/MorbidityName=<MorbidityName>&MorbidityTestId=<MorbidityTestId>";
	}
	RequestSpecification Deletemorbidityrequest;
	Response morbidityresponse;
	

	
RequestSpecification postmorbidityrequest;
Response Morbiditypostresponse;
	
	@Given("User sets Delete request  with endpoint as {string} with valid authorization")
	public void user_sets_delete_request_with_endpoint_as_with_valid_authorization(String string) {
		Deletemorbidityrequest = given().auth().preemptive().basic("KMSASM2022", "Dietician1!").contentType("application/json");

	}

	@When("User sends Delete request with {string},{string}")
	public void user_sends_delete_request_with(String MorbidityName,String MorbidityTestId) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	

	@Then("Status {int} OK is displayed with Morbidity Name, MorbidityTestId and Message as {string} is displayed")
	public void status_ok_is_displayed_with_morbidity_name_morbidity_test_id_and_message_as_is_displayed(Integer int1, String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("User sets Delete request with non existing with endpoint as {string} with valid authorization")
	public void user_sets_delete_request_with_non_existing_with_endpoint_as_with_valid_authorization(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User will sends Delete request")
	public void user_will_sends_delete_request() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("Status {int} Not Found is displayed with a Message {string} is displayed")
	public void status_not_found_is_displayed_with_a_message_is_displayed(Integer int1, String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("User sets Delete request  with invalid endpoint  {string} with valid authorization")
	public void user_sets_delete_request_with_invalid_endpoint_with_valid_authorization(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User send Delete request")
	public void user_send_delete_request() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("Status {string} Not Found will be displayed")
	public void status_not_found_will_be_displayed(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("User sets Delete request  with endpoint as {string} with Invalid UserName and valid Password \\/ valid UserName and Invalid Password")
	public void user_sets_delete_request_with_endpoint_as_with_invalid_user_name_and_valid_password_valid_user_name_and_invalid_password(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User will send Delete request")
	public void user_will_send_delete_request() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("Status {int} Unauthorised Acces will be displayed")
	public void status_unauthorised_acces_will_be_displayed(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("User sets Delete request  with endpoint as {string} with valid authorization with dynamodb Server down")
	public void user_sets_delete_request_with_endpoint_as_with_valid_authorization_with_dynamodb_server_down(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User sends Delete requests")
	public void user_sends_delete_requests() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("Status {int} Ok should display with a Message Internal Server Error.")
	public void status_ok_should_display_with_a_message_internal_server_error(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

}
