package stepdefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
//import org.junit.Assert;

public class GetUser_Dietician {
	
	RequestSpecification request;
	Response response;
	String dieticianAPI_url="http://127.0.0.1:5000/api/";
	//String dieticianEndpoint = "Users/UserType=Dietician";
	String username;
	String password;
	Properties properties = new Properties();
	
	@Before
	public void setUp() throws IOException {
		FileInputStream fis = new FileInputStream("src\\test\\resources\\config\\config.properties");
		properties.load(fis);
		
		RestAssured.baseURI = properties.getProperty("baseURI");
		RestAssured.basePath = properties.getProperty("dieticianEndpoint");
		username = properties.getProperty("username");
		password = properties.getProperty("password");
	}
	
	@Given("User is on Get request with User Type Dietician")
	public void user_is_on_get_request_with_user_type_dietician() {
		RestAssured.baseURI = dieticianAPI_url;
		//RestAssured.basePath = dieticianEndpoint;
		request = RestAssured.given().auth().preemptive().basic(username, password).contentType("application/json");
	}

	@When("User sends Get request with endpoint and authorization")
	public void user_sends_get_request_with_endpoint_and_authorization() {
	    response = request.when().get();
	}

	@Then("Status {int} should be displayed with user records of type Dietician")
	public void status_should_be_displayed_with_user_records_of_type_dietician(Integer status) {
	    int statuscode = status;
	    String str = response.asPrettyString();
	    response.then().log().all();
	    Assert.assertEquals(statuscode, response.getStatusCode());
	    System.out.println("************Status code "+statuscode+" validation is successful**************");
	    if(response.getStatusCode()==200) {
	    	response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/Schema/DieticianAPI.json")));
			
	    	System.out.println("************Schema Validation Successful**************");
		    Assert.assertTrue(str.contains("Email"));
		    Assert.assertTrue(str.contains("LastName"));
		    Assert.assertTrue(str.contains("FirstName"));
		    Assert.assertTrue(str.contains("UserId"));
		    Assert.assertTrue(str.contains("Contact"));
		    System.out.println("***********Validation of response body fields done********************");
		
	    }
	
	}

}
