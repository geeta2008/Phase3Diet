package runner;
import io.cucumber.testng.CucumberOptions;

//import org.junit.runner.RunWith;
//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;

	//@RunWith(Cucumber.class) 
	@CucumberOptions(features ="src/test/resources/features/Users",
	glue= {"UserAPI","AppHooks"},
	plugin={"pretty",
			"rerun:target/failedrerun.txt"
			},
		
	//},"html:target/HtmlReports","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
   monochrome = true,
	dryRun = false,
	
			 publish = true
			 	 )
	public class UserAPI_TestRunner extends io.cucumber.testng.AbstractTestNGCucumberTests 
	{

	}