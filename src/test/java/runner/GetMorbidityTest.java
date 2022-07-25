package runner;

import org.testng.annotations.Test;

import io.cucumber.testng.CucumberOptions;

//import org.junit.runner.RunWith;
//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;

//@RunWith(Cucumber.class) 

//this @test is for Allure report
@Test
@CucumberOptions(
		features = "src/test/resources/features/GetMorbidityFeatures/", 
		glue = {"MorbidityAPI", "AppHooks" }, 
		//plugin = { "pretty" , "html:target/HtmlReports","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		monochrome = true, dryRun = false,
		plugin = { "pretty", "html:target/cucumber-reports.html", "json:target/cucumber-html-reports/cucumber.json","rerun:target/failed_scenarios.txt" },
		// strict = true,
		publish = true)
public class GetMorbidityTest extends io.cucumber.testng.AbstractTestNGCucumberTests {
	/*
	 * @Override
	 * 
	 * @DataProvider(parallel = false) public Object[][] scenarios() { return
	 * super.scenarios(); }
	 */

}

