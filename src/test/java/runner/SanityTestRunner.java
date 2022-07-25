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
	  features = {"src/test/resources/features"}, 
	  tags = "@Smoke",
	  //glue= {"MorbidityAPI","GetUserAPI","RecipesAPI","UserAPI","AppHooks"},
      dryRun = false,
	  publish = true)

public class SanityTestRunner extends AbstractTestNGCucumberTests
{
	 
	 @Override
	    @DataProvider(parallel = false)
	    public Object[][] scenarios() {
	        return super.scenarios();
	    }
}

