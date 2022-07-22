package runner;


	import io.cucumber.testng.AbstractTestNGCucumberTests;
	import io.cucumber.testng.CucumberOptions;
	import io.cucumber.junit.Cucumber;
	import org.junit.runner.RunWith;
	import org.testng.annotations.DataProvider;


	@RunWith(Cucumber.class)

	@CucumberOptions(
		  plugin = {"pretty", "html:target/cucumber.html"},
		  monochrome=true, 
		  //tags = "@feature01",
		  features = {"src/test/resources/features/GetUsersAPiFeatures/GetUserContactNo.feature"}, 
		  glue= {"stepdefinitions", "AppHooks"},
		
//					
//				//},"html:target/HtmlReports","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
			 
				dryRun = false,
					//strict = true,
						 publish = true)

	public class GetUserContactNoTestRunner extends AbstractTestNGCucumberTests
	{
		 
		 @Override
		    @DataProvider(parallel = false)
		    public Object[][] scenarios() {
		        return super.scenarios();
		    }
	}
