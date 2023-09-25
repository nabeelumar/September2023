package e2eTesting.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOut {
	
	public WebDriver driver;
	public CheckOut(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".ta-results button")
	List<WebElement> results;
	
	@FindBy(css=".action__submit")
	WebElement placeOrder;
	
	public void selectCountry(String countryName) {
		
		Actions a  = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		for(WebElement result:results) {
			
			if(result.getText().equals(countryName)) {
				
				result.click();
			}
		}
	}
	
	public ConfirmationPage submitOrder() {
		placeOrder.click();
		ConfirmationPage  cop = new ConfirmationPage(driver);
		return cop;
		
	}


}
