package e2eTesting.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import reusableClasses.ReusableClass;

public class ProductCatalogue extends ReusableClass {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".card-body")
	List<WebElement> productGroups;

	By productGroupsBy = By.cssSelector(".card-body");

	By addcart = By.cssSelector(".card-body button:last-of-type");

	By toastContainer = By.cssSelector(".toast-message");

	public List<WebElement> getProductGroups() {
		waitElementLocatedBy(productGroupsBy);
		return productGroups;
	}

	public WebElement getProductItem(String product) {

		WebElement productItem = getProductGroups().stream()
				.filter(productGroup -> productGroup.findElement(By.tagName("b")).getText().equals(product)).findFirst()
				.orElse(null);
		return productItem;
	}

	public void addToCart(String product) {

		WebElement productItem = getProductItem(product);
		productItem.findElement(addcart).click();
		waitElementLocatedBy(toastContainer);

	}

}
