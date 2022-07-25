package UserAPI;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.util.XLSUtility;

import AppHooks.ApplicationHook;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

public class PutUsers {
	
	private static RequestSpecification request;
	private static Response response;
	String username="KMSASM2022";
	String password="Dietician1!";
	String address;
	
	//Properties properties = new Properties();
	static String path="src/test/resources/exceldata/UsersPostAPI.xlsx";

	XLSUtility xlutil=new XLSUtility(path);
	
	{
		RestAssured.baseURI = "http://127.0.0.1:5000/api/";
		RestAssured.basePath = "Users/";
	}

	
	
	
	@Given("User is on put user request with endpoint")
	public void user_is_on_put_user_request_with_endpoint() {
		
		request = RestAssured.given();
		//request = RestAssured.given().auth().preemptive().basic(username, password).contentType("application/json");
	}

	@SuppressWarnings("unchecked")
	@When("User sends put request with all valid parameter values in request body")
	public void user_sends_put_request_with_all_valid_parameter_values_in_request_body() throws IOException {
		//HashMap<String,String> users=new HashMap<String,String>();
		//request = RestAssured.given();
		int rows = xlutil.getRowCount("PutUsers");
		for(int i=1;i<=rows;i++) {
		JSONObject users = new JSONObject();
		users.put("FirstName", xlutil.getCellData("PutUsers", i, 0));
		users.put("LastName", xlutil.getCellData("PutUsers", i, 1));
		address = xlutil.getCellData("PutUsers", i, 7);
		JSONObject addressJson = (JSONObject) JSONValue.parse(address);
		users.put("Address", addressJson);
		users.put("Contact", xlutil.getCellData("PutUsers", i, 2));
		users.put("Email", xlutil.getCellData("PutUsers", i, 3));
		users.put("FoodCategory", xlutil.getCellData("PutUsers", i, 4));
		users.put("Allergy", xlutil.getCellData("PutUsers", i, 5));
		String userendpoint = xlutil.getCellData("PutUsers", i, 6);
		System.out.println(userendpoint);
		request = RestAssured.given().auth().preemptive().basic(username, password).contentType("application/json");
			
		response = request.when().body(users.toString()).put(userendpoint);
		System.out.println(response.asPrettyString());
		
		} 
	}

	@Then("Status code {int} record updated should be displayed")
	public void status_code_record_updated_should_be_displayed(Integer status) throws IOException {
	    
	    Assert.assertEquals(status.intValue(),response.getStatusCode());
	    System.out.println("**********Status code "+status+" validation successful**********");
	    if(response.getStatusCode()==200) {
	    response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(ApplicationHook.prop.getProperty("DieticianAPI_json"))));
			
	    	System.out.println("************Schema Validation Successful**************");
	    	String userid = response.jsonPath().getString("UserId");
	    	String name = response.jsonPath().getString("FirstName");
	    	String msg = response.jsonPath().getString("Message");
	    	String userendpoint = xlutil.getCellData("PutUsers", 5, 6);
	    	//System.out.println(userid+"********"+name+"*************"+msg);
	    	Assert.assertTrue(userendpoint.contains(userid));
	    	Assert.assertEquals(name,xlutil.getCellData("PutUsers", 5, 0));
	    	Assert.assertEquals(msg, "User updated successful");
	    	System.out.println("***********Response body validation successful***********");
	    }
	    
	    
	}

	@SuppressWarnings("unchecked")
	@When("User sends put request with same values in request body")
	public void user_sends_put_request_with_same_values_in_request_body() throws IOException {
		JSONObject users = new JSONObject();
		users.put("FirstName", xlutil.getCellData("PutUsers", 1, 0));
		users.put("LastName", xlutil.getCellData("PutUsers", 1, 1));
		address = xlutil.getCellData("PutUsers", 1, 7);
		JSONObject addressJson = (JSONObject) JSONValue.parse(address);
		users.put("Address", addressJson);
		users.put("Contact", xlutil.getCellData("PutUsers", 1, 2));
		users.put("Email", xlutil.getCellData("PutUsers", 1, 3));
		users.put("FoodCategory", xlutil.getCellData("PutUsers", 1, 4));
		users.put("Allergy", xlutil.getCellData("PutUsers", 1, 5));
		String userendpoint = xlutil.getCellData("PutUsers", 1, 6);
		//String address = xlutil.getCellData("PutUsers", 1, 7);
		System.out.println(userendpoint);
		
		request = RestAssured.given().auth().preemptive().basic(username, password).contentType("application/json");
			
		response = request.when().body(users.toString()).put(userendpoint);
	}

	@Then("status code {int} for successful update should be displayed")
	public void status_code_for_successful_update_should_be_displayed(Integer status) throws IOException {
		System.out.println(response.asPrettyString()); 
		Assert.assertEquals(status, response.getStatusCode());
		 System.out.println("**********Status code "+status+" validation successful**********");
		    if(response.getStatusCode()==200) {
		    	response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(ApplicationHook.prop.getProperty("DieticianAPI_json"))));
				
		    	System.out.println("************Schema Validation Successful**************");
		    	String userid = response.jsonPath().getString("UserId");
		    	String name = response.jsonPath().getString("FirstName");
		    	String msg = response.jsonPath().getString("Message");
		    	String userendpoint = xlutil.getCellData("PutUsers", 1, 6);
		    	//System.out.println(userid+"********"+name+"*************"+msg);
		    	Assert.assertTrue(userendpoint.contains(userid));
		    	Assert.assertEquals(name,xlutil.getCellData("PutUsers", 1, 0));
		    	Assert.assertEquals(msg, "User updated successful");
		    	System.out.println("***********Response body validation successful***********");
		    }
	}

	@SuppressWarnings("unchecked")
	@When("User sends put request with incorrect values")
	public void user_sends_put_request_with_incorrect_values() throws IOException {
		JSONObject users = new JSONObject();
		users.put("FirstName", xlutil.getCellData("PutUsers_Negative", 2, 0));
		users.put("LastName", xlutil.getCellData("PutUsers_Negative", 2, 1));
		users.put("Address", new JSONObject());
		users.put("Contact", xlutil.getCellData("PutUsers_Negative", 2, 2));
		users.put("Email", xlutil.getCellData("PutUsers_Negative", 2, 3));
		users.put("FoodCategory", xlutil.getCellData("PutUsers_Negative", 2, 4));
		users.put("Allergy", xlutil.getCellData("PutUsers_Negative", 2, 5));
		String userendpoint = xlutil.getCellData("PutUsers_Negative", 2, 6);
		System.out.println(userendpoint);
		request = RestAssured.given().auth().preemptive().basic(username, password).contentType("application/json");
			
		response = request.when().body(users.toString()).put(userendpoint);
	}

	@Then("Status code {int} bad request should be displayed")
	public void status_code_bad_request_should_be_displayed(Integer status) {
		System.out.println(response.asPrettyString()); 
		System.out.println("**********Status code: "+response.getStatusCode()+"*************");
		String msg = response.jsonPath().getString("Message");
		Assert.assertEquals(msg, "Missing Items OR Invalid Entry.Check on ['FoodCategory', 'Allergy', 'Contact']");
		Assert.assertEquals(status.intValue(), response.getStatusCode());
		
	}

	@SuppressWarnings("unchecked")
	@When("User sends put request for incorrect dietician and user id")
	public void user_sends_put_request_for_incorrect_dietician_and_user_id() throws IOException {
		JSONObject users = new JSONObject();
		users.put("FirstName", xlutil.getCellData("PutUsers", 1, 0));
		users.put("LastName", xlutil.getCellData("PutUsers", 1, 1));
		users.put("Address", new JSONObject());
		users.put("Contact", xlutil.getCellData("PutUsers", 1, 2));
		users.put("Email", xlutil.getCellData("PutUsers", 1, 3));
		users.put("FoodCategory", xlutil.getCellData("PutUsers", 1, 4));
		users.put("Allergy", xlutil.getCellData("PutUsers", 1, 5));
		String userendpoint = xlutil.getCellData("PutUsers_Negative", 1, 6);
		System.out.println(userendpoint);
		request = RestAssured.given().auth().preemptive().basic(username, password).contentType("application/json");
			
		response = request.when().put(userendpoint);
	}

	@Then("Status {int} user id not found should be displayed")
	public void status_user_id_not_found_should_be_displayed(Integer status) {
		System.out.println(response.asPrettyString()); 
		Assert.assertEquals(status, response.getStatusCode());
		String msg = response.jsonPath().getString("message");
		Assert.assertEquals(msg, "The browser (or proxy) sent a request that this server could not understand.");
	}


}
