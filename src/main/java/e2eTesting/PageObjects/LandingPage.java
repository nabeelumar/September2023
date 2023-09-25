package e2eTesting.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	WebDriver driver;

	public LandingPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#userEmail")
	WebElement username;

	@FindBy(xpath = "//input[@id='userPassword']")
	WebElement password;

	@FindBy(css = "input[name='login']")
	WebElement submit;
	
	@FindBy(css="div[class*='toast-error']")
	WebElement errorMsg;

	public void goTo() {

		driver.get("http://rahulshettyacademy.com/client");
	}

	public ProductCatalogue LoginToApplication(String uname, String pwd) {

		username.sendKeys(uname);
		password.sendKeys(pwd);
		submit.click();
		ProductCatalogue pc = new ProductCatalogue(driver);
		return pc;
	}

	
	public String getErrorMsg() {
		
		String errorMessage = errorMsg.getText();
		return errorMessage;
	}
}
