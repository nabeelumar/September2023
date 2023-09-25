package e2eTesting.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {
	
	public WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css=".hero-primary")
	WebElement confirmationMessage;
	
	public String getConfrimationMessage() {
		
		return confirmationMessage.getText();
	}

}
