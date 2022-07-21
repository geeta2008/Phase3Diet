package AppHooks;

/*import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

//import com.util.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHook {

	//private ConfigReader configReader;
	Properties properties;
	private WebDriver driver;
	
	@Before(order = 0)
	public void setUp() throws IOException {
			FileInputStream fis = new FileInputStream("src\\test\\resources\\config\\config.properties");
			properties.load(fis);
			//excelReader = new ExcelReader();
		}

	@After(order = 1)
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			// take screenshot
			String screenshotName = scenario.getName().replaceAll("   ", "_");
			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);
		}
	}
}
	
	private DriverFactory driverfactory;
	;
	

	

	@Before(order = 1)
	public void launchBrowser() throws IOException {
		String browserName = prop.getProperty("browser");
		driverfactory = new DriverFactory();
		System.out.println(browserName);
		driver = driverfactory.init_driver(browserName);
	}

	
	 @After(order = 0) 
	 public void quitBrowser() { driver.quit(); }
	 

	
	}
}*/
