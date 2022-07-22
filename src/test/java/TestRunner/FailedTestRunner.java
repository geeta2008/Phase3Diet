package TestRunner;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features ="@target/failedrerun.txt",
glue= {"stepdefinitions"},
plugin={"pretty",
		"rerun:target/failedrerun.txt",
		"html:target/FailedRerunCucumber.html"
		
		},
	

monochrome = true,
dryRun = false,
	//strict = true,
		 publish = true
		 	 )
public class FailedTestRunner extends io.cucumber.testng.AbstractTestNGCucumberTests  {

}
