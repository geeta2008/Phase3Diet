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
	
public class RecipeAPIGetNonVegetarianSteps {
		RequestSpecification getrequest;
		Response response;

	@Given("Users give GET request with the valid endpoint {string}")
	public void users_give_get_request_with_the_valid_endpoint(String string) {
		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = ApplicationHook.prop.getProperty("RecipeAPI_basePath_valid_Endpoint_Non-Veg"); 
		getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");

	}
	    
	@When("Users give GET request")
	public void users_give_get_request() {
		 
		response = getrequest.when().get();
	}

	@Then("Status {int} success be displayed")
	public void status_success_be_displayed(Integer statusCode) {
		response.then().log().all();
		int statuscode = response.getStatusCode();

		// status code validation
		Assert.assertEquals(statuscode, statusCode.intValue());
		// json schema validation
	response.then().assertThat().body(JsonSchemaValidator 
		.matchesJsonSchema(new File(ApplicationHook.prop.getProperty("RecipeAPI_JsonSchema"))));

	}

	@Given("Users sets GET request with invalid request query string")
	public void users_sets_get_request_with_invalid_request_query_string() {
		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = ApplicationHook.prop.getProperty("RecipeAPI_basePath_invalid"); 
		getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");

	}
	
	@When("Users will sends the GET request")
	public void users_will_sends_the_get_request() {
		response = getrequest.when().get();
	}

	@Then("Status {int} Validation Error be displayed")
	public void status_Validation_Error_be_displayed(Integer statusCode) {
		response.then().log().all();
		int statuscode = response.getStatusCode();

		// status code validation
		Assert.assertEquals(statuscode, statusCode.intValue());
	}

	@Given("User set GET request endpoint {string} with default authorization")
	public void user_set_get_request_endpoint_with_default_authorization(String string) {
		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = ApplicationHook.prop.getProperty("RecipeAPI_basePath_valid_Endpoint_Non-Veg"); 
		getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_invalid"), ApplicationHook.prop.getProperty("password_invalid")).contentType("application/json");

	}

	@When("Users will send GET request")
	public void users_will_send_get_request() {
		response = getrequest.when().get();
		
	}

	@Then("Status {int} Unauthorised Acces be displayed")
	public void status_unauthorised_acces_be_displayed(Integer statusCode) {
		response.then().log().all();
		int statuscode = response.getStatusCode();

		// status code validation
		Assert.assertEquals(statuscode, statusCode.intValue());

	}

	@Given("Users sets get request with invalid endpoint")
	public void users_sets_get_request_with_invalid_endpoint() {
		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = ApplicationHook.prop.getProperty("RecipeAPI_basePath_invalid"); 
		getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"),  ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");
	}

	@When("Users will send the GET request")
	public void users_will_send_the_get_request() {
		response = getrequest.when().get();
	}

	@Then("Status {int} Not Found be displayed")
	public void status_not_found_be_displayed(Integer statusCode) {
		response.then().log().all();
		int statuscode = response.getStatusCode();

		// status code validation
		Assert.assertEquals(statuscode, statusCode.intValue());

	}

	}


