/*
 * package stepdefinitions;
 * 
 * import io.cucumber.java.en.Given; import io.cucumber.java.en.Then; import
 * io.cucumber.java.en.When; import io.restassured.http.ContentType; import
 * io.restassured.response.Response; import
 * io.restassured.specification.RequestSpecification; import static
 * io.restassured.RestAssured.given;
 * 
 * 
 * public class GetAllUsers {
 * 
 * RequestSpecification request; Response response;
 * 
 * @Given("User is on GET request with Users endpoint") public void
 * user_is_on_get_request_with_users_endpoint() { request =
 * given().contentType(ContentType.JSON).auth().basic("KMSASM2022",
 * "Dietician1!"); }
 * 
 * @When("User sends GET request") public void user_sends_get_request() {
 * response = request.when().get("http://127.0.0.1:5000/api/Users/"); }
 * 
 * @Then("User should be displayed success status code {int} with list of all users."
 * ) public void
 * user_should_be_displayed_success_status_code_with_list_of_all_users(Integer
 * int1) { response.then().statusCode(200); }
 * 
 * @Then("user should get status code error {int} unauthorized access") public
 * void user_should_get_status_code_error_unauthorized_access(Integer int1) {
 * 
 * }
 * 
 * @Then("user should get status code error {int} internal server error") public
 * void user_should_get_status_code_error_internal_server_error(Integer int1) {
 * 
 * } }
 */