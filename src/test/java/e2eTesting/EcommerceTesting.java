package e2eTesting;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EcommerceTesting {
	
	@Test
	public void buyItem() {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		WebDriverWait wd = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		//Login to website using credentials
		driver.get("http://rahulshettyacademy.com/client");
		driver.findElement(By.cssSelector("#userEmail")).sendKeys("nabeelans@gmail.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("Abcd@123");
		driver.findElement(By.cssSelector("input[name='login']")).click();
		
		//Add product to cart
		wd.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card-body")));
		List<WebElement> productGroups = driver.findElements(By.cssSelector(".card-body"));
		WebElement product =productGroups.stream().filter(productGroup->productGroup.findElement(By.tagName("b")).getText().equals("IPHONE 13 PRO")).findFirst().orElse(null);
		product.findElement(By.cssSelector(".card-body button:last-child")).click();
		
		
		//Wait for Product Added message and click on Car
		wd.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".toast-message"))));
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		
		//Verifying if item present in cart and clicking checkout
		List<WebElement> items =driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean itemPresent = items.stream().anyMatch(item->item.getText().equals("IPHONE 13 PRO"));
		Assert.assertTrue(itemPresent);
		driver.findElement(By.cssSelector(".totalRow button")).click();
	
		//Selecting country and clicking on Place order
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "India").build().perform();
		List<WebElement> countries = driver.findElements(By.cssSelector(".ta-results  button"));
		for(WebElement country:countries) {
			
			if(country.getText().equals("India")) {
				
				country.click();
				break;
			}
		}
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		//Validating order confirmation message is displayed
		String confirmationMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println(confirmationMessage);
		AssertJUnit.assertEquals(confirmationMessage, "THANKYOU FOR THE ORDER.");
		
		
		
	}

}
