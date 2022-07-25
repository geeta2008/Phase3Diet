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
		  features = {"src/test/resources/features/MorbidityFeatures"}, 
		  glue= {"MorbidityAPI", "AppHooks"},
          dryRun = false,
		  publish = true)

	public class MorbidityAPITestRunner extends AbstractTestNGCucumberTests
	{
		 
		 @Override
		    @DataProvider(parallel = false)
		    public Object[][] scenarios() {
		        return super.scenarios();
		    }
	}





