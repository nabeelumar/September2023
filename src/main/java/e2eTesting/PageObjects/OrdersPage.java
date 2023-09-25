package e2eTesting.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage {
	WebDriver driver;

	public OrdersPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> orderItems;
	
	public boolean verifyOrder(String orderName) {
		
		boolean orderPresent =orderItems.stream().anyMatch(orderItem->orderItem.getText().equalsIgnoreCase(orderName));
		return orderPresent;
	}
	
	

}
