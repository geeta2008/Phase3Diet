package MorbidityAPI;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;

import com.util.XLSUtility;

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


@SuppressWarnings("unused")
public class PostMorbidityAPI {
	XLSUtility xlutil= new XLSUtility(excelpath);
	static String excelpath = "./src/test/resources/exceldata/postmorbidity.xlsx";
	{
		RestAssured.baseURI = ApplicationHook.prop.getProperty("baseURI");
		RestAssured.basePath = ApplicationHook.prop.getProperty("Morbidity_basePath_valid");
		//RestAssured.basePath = ApplicationHook.prop.getProperty("Morbidity_basePath_invalid");
	}
	
	@Before
	public void setUp() throws IOException {
		//FileInputStream fis = new FileInputStream("src\\test\\resources\\config\\config.properties");
		//properties.load(fis);
	}
	
	RequestSpecification postmorbidityrequest;
	Response Morbiditypostresponse;

	@Given("User sets POST request with endpoint with valid authorization")
	public void user_sets_post_request_with_endpoint_with_valid_authorization() {
		postmorbidityrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");
	}
	
	@When("User sends Post request with new data as user input in json format")
	public void user_sends_post_request_with_new_data_as_user_input_in_json_format() throws IOException {
	    String MorbidityName, MorbidityTestName,MorbidityMarkerRef,MorbidityTestUnit;
		//int rowcount = xlutil.getRowCount("PostMorbidity");
		for (int i = 1; i <= xlutil.getRowCount("PostMorbidity"); i++) {
			MorbidityTestName=xlutil.getCellData("PostMorbidity", i, 0);
			MorbidityTestUnit=xlutil.getCellData("PostMorbidity", i, 1);
			MorbidityName=xlutil.getCellData("PostMorbidity", i, 2);
			MorbidityMarkerRef=xlutil.getCellData("PostMorbidity", i, 3);
			HashMap<String,String> Morbidityresponse=new HashMap<String,String>();
			Morbidityresponse.put("MorbidityName",MorbidityName);
			Morbidityresponse.put("MorbidityTestName",MorbidityTestName);
			Morbidityresponse.put("MorbidityMarkerRef",MorbidityMarkerRef);
			Morbidityresponse.put("MorbidityTestUnit",MorbidityTestUnit);
			Morbiditypostresponse = postmorbidityrequest.when().body(Morbidityresponse).post();
			String MorbidityTestID = Morbiditypostresponse.jsonPath().getString("MorbidityTestId");
			System.out.println("MorbidityTestId:" + MorbidityTestID);
			xlutil.setCellData("PostMorbidity",i, 4, MorbidityTestID);
		}
	}
	

	@Then("Status {int} Ok should be displayed with a {string} \\(Auto Generated) with a Message Morbidity created successfully.")
	public void status_ok_should_be_displayed_with_a_auto_generated_with_a_message_morbidity_created_successfully(Integer status, String MorbidityTestID ) throws IOException 
	{   
		Morbiditypostresponse.then().assertThat().body(
			JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/JsonSchemas/MorbidityAPIPostSchema.json")));
		  int statuscode = Morbiditypostresponse.getStatusCode();
		  Assert.assertEquals(statuscode,status.intValue());  
		  MorbidityTestID = Morbiditypostresponse.jsonPath().getString("MorbidityTestId");
		  String Message = Morbiditypostresponse.jsonPath().getString("Message");
		  String Morbidity = Morbiditypostresponse.jsonPath().prettyPrint();
		  System.out.println("Morbidity Test ID:" + MorbidityTestID); 
		  assertFalse(MorbidityTestID.isEmpty());
		  assertEquals(Message, "Morbidity successful created.");
	}

	@Given("User set POST request with endpoint with valid authorization")
	public void user_set_post_request_with_endpoint_with_valid_authorization() {
		postmorbidityrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");
	}

	@SuppressWarnings("unchecked")
	@When("User sends Post request with Existing data in Request body in json format")
	public void user_sends_post_request_with_existing_data_in_request_body_in_json_format() throws IOException {
	  	String MorbidityName, MorbidityTestName,MorbidityMarkerRef,MorbidityTestUnit;
		int rowcount = xlutil.getRowCount("PostMorbidityExist");
		for (int i = 1; i <= rowcount; i++) {
			MorbidityTestName=xlutil.getCellData("PostMorbidityExist", i, 0);
			MorbidityTestUnit=xlutil.getCellData("PostMorbidityExist", i, 1);
			MorbidityName=xlutil.getCellData("PostMorbidityExist", i, 2);
			MorbidityMarkerRef=xlutil.getCellData("PostMorbidityExist", i, 3);
			HashMap<String,String> Morbidityresponse=new HashMap<String,String>();
			Morbidityresponse.put("MorbidityName",MorbidityName);
			Morbidityresponse.put("MorbidityTestName",MorbidityTestName);
			Morbidityresponse.put("MorbidityMarkerRef",MorbidityMarkerRef);
			Morbidityresponse.put("MorbidityTestUnit",MorbidityTestUnit);
			Morbiditypostresponse = postmorbidityrequest.when().body(Morbidityresponse).post();
			String MorbidityTestID = Morbiditypostresponse.jsonPath().getString("MorbidityTestId");
			xlutil.setCellData("PostMorbidityExist",i, 4, MorbidityTestID);
			System.out.println("Morbidity Already Exists");
		}
		
	}
	
	@Then("Status {int} Ok should display with a Message {string}")
	public void status_ok_should_display_with_a_message(Integer status, String Message) {
	       	//Morbiditypostresponse.then().assertThat().body(
			//JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/JsonSchemas/MorbidityAPIPostSchema.json")));
			int statuscode = Morbiditypostresponse.getStatusCode();
			Assert.assertEquals(statuscode,status.intValue());  
			String MorbidityTestID = Morbiditypostresponse.jsonPath().getString("MorbidityTestId");
			Message = Morbiditypostresponse.jsonPath().getString("Message");
			String Morbidity = Morbiditypostresponse.jsonPath().prettyPrint();
			assertNull(MorbidityTestID);
			assertEquals(Message, "error occurred");
		}
	
	@Given("User with endpoint set POST request with valid authorization")
	public void user_with_endpoint_set_post_request_with_valid_authorization() {
	   postmorbidityrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");
	}
	
	@When("User sends Post request with any two fields in different combination in Request body in json format")
	public void user_sends_post_request_with_any_two_fields_in_different_combination_in_request_body_in_json_format() throws IOException {
	  	String MorbidityName, MorbidityTestName,MorbidityMarkerRef,MorbidityTestUnit;
		int rowcount = xlutil.getRowCount("PostMorbidityMissing");
		for (int i = 1; i <= rowcount; i++) {
			MorbidityTestName=xlutil.getCellData("PostMorbidityMissing", i, 0);
			MorbidityTestUnit=xlutil.getCellData("PostMorbidityMissing", i, 1);
			MorbidityName=xlutil.getCellData("PostMorbidityMissing", i, 2);
			MorbidityMarkerRef=xlutil.getCellData("PostMorbidityMissing", i, 3);
			HashMap<String,String> Morbidityresponse=new HashMap<String,String>();
			Morbidityresponse.put("MorbidityName",MorbidityName);
			Morbidityresponse.put("MorbidityTestName",MorbidityTestName);
			Morbidityresponse.put("MorbidityMarkerRef",MorbidityMarkerRef);
			Morbidityresponse.put("MorbidityTestUnit",MorbidityTestUnit);
			Morbiditypostresponse = postmorbidityrequest.when().body(Morbidityresponse).post();
			String MorbidityTestID = Morbiditypostresponse.jsonPath().getString("MorbidityTestId");
			xlutil.setCellData("PostMorbidityMissing",i, 4, MorbidityTestID);
			System.out.println("Morbidities Fields are missing");
		}
	  }
	
	@Then("Status {int} Bad Request should display with a Message Missing Items OR Invalid Entry.Check on {string} Names")
	public void status_bad_request_should_display_with_a_message_missing_items_or_invalid_entry_check_on_names(Integer status, String Message) {
	    int statuscode = Morbiditypostresponse.getStatusCode();
		Assert.assertEquals(statuscode,status.intValue());
		String MorbidityTestID =Morbiditypostresponse.jsonPath().getString("MorbidityTestId");
		String Message1 = Morbiditypostresponse.jsonPath().getString("Message");
		String Morbidity = Morbiditypostresponse.jsonPath().prettyPrint();
		//assertNull(MorbidityTestID);
		Assert.assertTrue(Message1.contains("Missing Items OR Invalid Entry"));
		
	}

	@Given("User with endpoint sets POST request with valid authorization")
	public void user_with_endpoint_sets_post_request_with_valid_authorization() {
		postmorbidityrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_valid"), ApplicationHook.prop.getProperty("password_valid")).contentType("application/json");
	}
	
	@When("User sends Post request without data in Request body")
	public void user_sends_post_request_without_data_in_request_body() throws IOException {
	   	Morbiditypostresponse = postmorbidityrequest.when().body("").post();
			//String MorbidityTestID = Morbiditypostresponse.jsonPath().getString("MorbidityTestId");
			//xlutil.setCellData("PostMorbidityMissing",i, 4, MorbidityTestID);
			System.out.println("Morbidities Fields are missing");
		}
	 	
	@Then("Status {int} Bad Request should display with a Message The browser \\(or proxy) sent a request that this server could not understand.")
	public void status_bad_request_should_display_with_a_message_the_browser_or_proxy_sent_a_request_that_this_server_could_not_understand(Integer status) {
		int statuscode= Morbiditypostresponse.getStatusCode();
		Assert.assertEquals(statuscode,status.intValue());
		//String MorbidityTestID = Morbiditypostresponse.jsonPath().getString("MorbidityTestId");
		//String Message = Morbiditypostresponse.jsonPath().getString("Message");
		//String Morbidity = Morbiditypostresponse.jsonPath().prettyPrint();
		//assertNull(Morbidity,Message);
		//assertNull(MorbidityTestID);
		System.out.println("Response fields is Null as No data is passed in Request");
		
	}
	
	@Given("User set POST request with endpoint with Invalid authorization")
	public void user_set_post_request_with_endpoint_with_invalid_authorization() {
		postmorbidityrequest = given().auth().preemptive().basic(ApplicationHook.prop.getProperty("username_invalid"), ApplicationHook.prop.getProperty("password_invalid")).contentType("application/json");
	}

	@When("User sends Post request with {string},{string},{string},{string} in Request body in json format")
	public void user_sends_post_request_with_in_request_body_in_json_format(String MorbidityName, String MorbidityTestName, String MorbidityMarkerRef, String MorbidityTestUnit) {
		HashMap<String,String> Morbidityresponse=new HashMap<String,String>();
		Morbidityresponse.put("MorbidityName",MorbidityName);
		Morbidityresponse.put("MorbidityTestName",MorbidityTestName);
		Morbidityresponse.put("MorbidityMarkerRef",MorbidityMarkerRef);
		Morbidityresponse.put("MorbidityTestUnit",MorbidityTestUnit);
		Morbiditypostresponse = postmorbidityrequest.when().body(Morbidityresponse).post();
		//String MorbidityTestID = Morbiditypostresponse.jsonPath().getString("MorbidityTestId");
		//System.out.println("MorbidityTestId:" + MorbidityTestID);
		}
	
	@Then("Status {int} Unauthorized should display with a Message {string}")
	public void status_unauthorized_should_display_with_a_message(Integer status, String Message) {
		  //Morbiditypostresponse.then().assertThat().statusCode(statusCode.intValue());
		  int statuscode = Morbiditypostresponse.getStatusCode();
		  //System.out.println(statusCode);
	      //assertSame(statuscode,status);
		  //String MorbidityTestID = Morbiditypostresponse.jsonPath().getString("MorbidityTestId");
		  //Message = Morbiditypostresponse.jsonPath().getString("Message");
		  //System.out.println(Message);
		  //String message1 = "The server could not verify that you are authorized to access the URL requested. You either supplied the wrong credentials (e.g. a bad password), or your browser doesn't understand how to supply the credentials required.";
		 // Morbiditypostresponse.then().assertThat().body(Message,equalTo (message1));
		  //String Morbidity = Morbiditypostresponse.jsonPath().prettyPrint();
		 // Assert.assertEquals(Message,"message1");
		  //assertNull(Message,"message1");
		  //assertNull(MorbidityTestID);
		  System.out.println("Response fields is Null as No data is passed in Request");
		  Assert.assertEquals(statuscode,status.intValue());
		  
	}

	
}
