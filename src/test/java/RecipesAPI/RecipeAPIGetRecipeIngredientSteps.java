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

	public class RecipeAPIGetRecipeIngredientSteps {
		RequestSpecification getrequest;
		Response response;

	@Given("User sets GET request with endpoint with valid RecipeIngredient")
	public void user_sets_get_request_with_endpoint_with_valid_recipe_ingredient() {
		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = ApplicationHook.prop.getProperty("RecipeAPI_basePath_valid_Endpoint_Ingredient"); 
		getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");
	}

	@When("Users sents GET request")
	public void users_sents_get_request() {
		response = getrequest.when().get();
	}

	@Then("Status {int} success will be seen")
	public void status_success_will_be_seen(Integer statusCode) {
		response.then().log().all();
		int statuscode = response.getStatusCode();

		// status code validation
		Assert.assertEquals(statuscode, statusCode.intValue());
		// json schema validation
	response.then().assertThat().body(JsonSchemaValidator 
		.matchesJsonSchema(new File(ApplicationHook.prop.getProperty("RecipeAPI_JsonSchema"))));
	}

	@Given("User set GET request with endpoint with invalid request query string")
	public void user_set_get_request_with_endpoint_with_invalid_request_query_string() {
		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = ApplicationHook.prop.getProperty("RecipeAPI_basePath_invalid"); 
		getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");

	}

	@When("User sending GET request")
	public void user_sending_get_request() {
		response = getrequest.when().get();
	}

	@Then("Status {int} Validation Error will be seen")
	public void status_validation_error_will_be_seen(Integer statusCode) {
		response.then().log().all();
		int statuscode = response.getStatusCode();

		// status code validation
		Assert.assertEquals(statuscode, statusCode.intValue());
	}

	@Given("User sets Get request with endpoint with {string} with default authorization")
	public void user_sets_get_request_with_endpoint_with_with_default_authorization(String string) {
		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = ApplicationHook.prop.getProperty("RecipeAPI_basePath_valid_Endpoint_Ingredient"); 
		getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_invalid"), ApplicationHook.prop.getProperty("password_invalid")).contentType("application/json");

	}

	@When("User sent get request")
	public void user_sent_get_request() {
		response = getrequest.when().get();
	}

	@Then("Status {int} Unauthorised Acces will be seen")
	public void status_unauthorised_acces_will_be_seen(Integer statusCode) {
		response.then().log().all();
		int statuscode = response.getStatusCode();

		// status code validation
		Assert.assertEquals(statuscode, statusCode.intValue());
	}

	@Given("User sets GET request with invalid endpoints")
	public void user_sets_get_request_with_invalid_endpoints() {
		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = ApplicationHook.prop.getProperty("RecipeAPI_basePath_invalid"); 
		getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"),  ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");

	}

	@When("User click sent GET request")
	public void user_click_sent_get_request() {
		response = getrequest.when().get();
	}

	@Then("Status {int} Not Found will be seen")
	public void status_not_found_will_be_seen(Integer statusCode) {
		response.then().log().all();
		int statuscode = response.getStatusCode();

		// status code validation
		Assert.assertEquals(statuscode, statusCode.intValue());
	}
	}




