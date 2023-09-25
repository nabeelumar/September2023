package testComponents;

import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import e2eTesting.PageObjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage lp;

	public WebDriver intializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/resources/data.properties");
		prop.load(fis);

		String browser = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		if (browser.equalsIgnoreCase("chrome")) {

			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {

			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("safari")) {

			driver = new SafariDriver();
		} else {

			System.out.println("Please mention correct browser details");
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		return driver;
	}

	public List<HashMap<String, String>> getJsonData(String filePath) throws IOException {

		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;

	}
	
	public String getScreenshot(WebDriver driver, String testCaseName) throws IOException {
		
		File src =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+"/reports/screenshots/"+testCaseName+".png";
		System.out.println("SCREENSHOT PATH ISSSSSSSS"+System.getProperty("user.dir")+"/reports/"+testCaseName+".png");
		FileUtils.copyFile(src, new File(destinationFile));
		return destinationFile;
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchURL() throws IOException {

		driver = intializeDriver();
		lp = new LandingPage(driver);
		lp.goTo();
		return lp;

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

}
