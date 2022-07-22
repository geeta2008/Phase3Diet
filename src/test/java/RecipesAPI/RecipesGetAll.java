package RecipesAPI;

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


	public class RecipesGetAll {
		RequestSpecification getrequest;
		Response response;
		
		@Given("Users set Get Request with no parameters")
		public void users_set_get_request_with_no_parameters() {
			RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
			RestAssured.basePath = ApplicationHook.prop.getProperty("RecipeAPI_basePath_valid"); 
			getrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");

		}
		    
		@When("Users send GeT request with no parameters")
		public void users_send_get_request_with_no_parameters() {
		    
			response = getrequest.when().get();
		}

		@Then("Status {int} should be displayed")
		public void status_should_be_displayed(Integer statusCode) {
		    
			response.then().log().all();
			int statuscode = response.getStatusCode();

			// status code validation
			Assert.assertEquals(statuscode, statusCode.intValue());
			// json schema validation
		response.then().assertThat().body(JsonSchemaValidator 
			.matchesJsonSchema(new File(ApplicationHook.prop.getProperty("RecipeAPI_JsonSchema"))));

		}

	}


