package TestRunner;

import org.testng.annotations.DataProvider;
import io.cucumber.testng.CucumberOptions;

//import org.junit.runner.RunWith;
//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;

//@RunWith(Cucumber.class) 

@CucumberOptions(features = "src/test/resources/features/GetMorbidityFeatures/", glue = {
		"stepdefinitions", "AppHooks" }, plugin = { "pretty" },

		// },"html:target/HtmlReports","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		monochrome = true, dryRun = false,
		// strict = true,
		publish = true)
public class GetMorbidity extends io.cucumber.testng.AbstractTestNGCucumberTests {
	/*
	 * @Override
	 * 
	 * @DataProvider(parallel = false) public Object[][] scenarios() { return
	 * super.scenarios(); }
	 */

}