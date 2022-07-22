package RecipesAPI;

	import static io.restassured.RestAssured.given;

	import java.io.File;

	import org.testng.Assert;

	import AppHooks.ApplicationHook;
	import io.cucumber.java.en.Given;
	import io.cucumber.java.en.Then;
	import io.cucumber.java.en.When;
	import io.restassured.RestAssured;
	import io.restassured.module.jsv.JsonSchemaValidator;
	import io.restassured.response.Response;
	import io.restassured.specification.RequestSpecification;

	public class RecipeAPIGetVegetarianSteps {
		RequestSpecification getrequest;
		Response response;

	@Given("User set get request with endpoint with {string}")
	public void user_set_get_request_with_endpoint_with(String string) {
		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = ApplicationHook.prop.getProperty("RecipeAPI_basePath_valid_Endpoint_Veg"); 
		getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");
	   
	}

	@When("Users sends get request")
	public void users_sends_get_request() {
		response = getrequest.when().get();
	    
	}

	@Then("Status {int} success will be shown")
	public void status_success_will_be_shown(Integer statusCode) {
		response.then().log().all();
		int statuscode = response.getStatusCode();

		// status code validation
		Assert.assertEquals(statuscode, statusCode.intValue());
		// json schema validation
	response.then().assertThat().body(JsonSchemaValidator 
		.matchesJsonSchema(new File(ApplicationHook.prop.getProperty("RecipeAPI_JsonSchema"))));
	   
	  
	}

	@Given("User sets get request with invalid request query string")
	public void user_sets_get_request_with_invalid_request_query_string() {
		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = ApplicationHook.prop.getProperty("RecipeAPI_basePath_invalid"); 
		getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");
	}

	@When("User sending get request")
	public void user_sending_get_request() {
		response = getrequest.when().get();
	}

	@Then("Status {int} Validation Error will be shown")
	public void status_validation_error_will_be_shown(Integer statusCode) {
		response.then().log().all();
		int statuscode = response.getStatusCode();

		// status code validation
		Assert.assertEquals(statuscode, statusCode.intValue());
	}

	@Given("Users sets GET request with endpoint {string} with default authorization")
	public void users_sets_get_request_with_endpoint_with_default_authorization(String string) {
		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = ApplicationHook.prop.getProperty("RecipeAPI_basePath_valid_Endpoint_Veg"); 
		getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_invalid"), ApplicationHook.prop.getProperty("password_invalid")).contentType("application/json");
	}

	@When("User sents GET request")
	public void user_sents_get_request() {
		response = getrequest.when().get();
	}

	@Then("Status {int} Unauthorised Acces will be shown")
	public void status_unauthorised_acces_will_be_shown(Integer statusCode) {
		response.then().log().all();
		int statuscode = response.getStatusCode();

		// status code validation
		Assert.assertEquals(statuscode, statusCode.intValue());
	}

	@Given("Users set GET request with invalid endpoint")
	public void users_set_get_request_with_invalid_endpoint() {
		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = ApplicationHook.prop.getProperty("RecipeAPI_basePath_invalid"); 
		getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"),  ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");
	}
	    


	@When("Users send GET request")
	public void users_send_get_request() {
		response = getrequest.when().get();
	}

	@Then("Status {int} Not Found will be shown")
	public void status_not_found_will_be_shown(Integer statusCode) {
		response.then().log().all();
		int statuscode = response.getStatusCode();

		// status code validation
		Assert.assertEquals(statuscode, statusCode.intValue());
	}
	}


