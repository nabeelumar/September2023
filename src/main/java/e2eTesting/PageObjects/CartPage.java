package e2eTesting.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	
	public WebDriver driver;
	public CartPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(css=".cart h3")
	List<WebElement> items;
	
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	
	
	public Boolean checkItems(String productName) {
		
	boolean isPresent =	items.stream().anyMatch(item->item.getText().equals(productName));
	return isPresent;
	}
	
	public CheckOut checkOut() {
		
		checkout.click();
		CheckOut ch = new CheckOut(driver);
		return ch;
	}
	
	
}

