package AppHooks;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.factory.DriverFactory;
import com.util.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHook {

	private DriverFactory driverfactory;
	private WebDriver driver;
	private ConfigReader configReader;
	//Properties prop;
	

	@Before(order = 0)
	public void getProperty()  {
//		Properties properties = new Properties();
//		FileInputStream fis = new FileInputStream("src\\test\\resources\\config\\config.properties");
//		properties.load(fis);
//		configReader = new ConfigReader();
//		prop = configReader.init_prop();
	}

	@Before(order = 1)
	public void launchBrowser() throws IOException {
//		String browserName = prop.getProperty("browser");
//		driverfactory = new DriverFactory();
//		System.out.println(browserName);
//		driver = driverfactory.init_driver(browserName);
	}

	
	 @After(order = 0) 
	public void quitBrowser() { 
		 //driver.quit();
		 }
	 
	 

	@After(order = 1)
	public void tearDown(Scenario scenario) {
//		if (scenario.isFailed()) {
//			// take screenshot
//			String screenshotName = scenario.getName().replaceAll("   ", "_");
//			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//			scenario.attach(sourcePath, "image/png", screenshotName);
//		}
	}
}
