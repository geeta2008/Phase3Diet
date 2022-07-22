package runner;

import org.testng.annotations.Test;
import io.cucumber.testng.CucumberOptions;


@Test
@CucumberOptions(
		features = "src/test/resources/features/RecipeAPIFeatures", 
		glue = {"RecipesAPI","AppHooks" }, 
		monochrome = true, dryRun = false,
		plugin = { "pretty", "html:target/cucumber-reports.html"},	
		publish = true)


public class Recipes extends io.cucumber.testng.AbstractTestNGCucumberTests {
	/*
	 * @Override
	 * 
	 * @DataProvider(parallel = false) public Object[][] scenarios() { return
	 * super.scenarios(); }
	 */

}

	
	
	

	
