package reusableClasses;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import e2eTesting.PageObjects.CartPage;
import e2eTesting.PageObjects.OrdersPage;

public class ReusableClass {

	WebDriver driver;

	public ReusableClass(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "button[routerlink*='cart']")
	WebElement cart;
	
	@FindBy(css = "	button[routerlink='/dashboard/myorders']")
	WebElement orders;
	


	public void waitElementLocatedBy(By FindBy) {

		WebDriverWait wd = new WebDriverWait(driver, Duration.ofSeconds(5));
		wd.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}

	public CartPage goToCart() {

		cart.click();
		CartPage cp = new CartPage(driver);
		return cp;
	}
	
	
	public OrdersPage goToOrder() {
		
		orders.click();
		OrdersPage op = new OrdersPage(driver);
		return op;
	}
	
}
