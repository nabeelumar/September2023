package e2eTesting;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import e2eTesting.PageObjects.CartPage;
import e2eTesting.PageObjects.CheckOut;
import e2eTesting.PageObjects.ConfirmationPage;
import e2eTesting.PageObjects.LandingPage;
import e2eTesting.PageObjects.OrdersPage;
import e2eTesting.PageObjects.ProductCatalogue;
import reusableClasses.ReusableClass;
import testComponents.BaseTest;

public class EcommercePageFactoryTesting  extends BaseTest{
	String product = "ADIDAS ORIGINAL";
	
	
	@Test(dataProvider="getData")
	public void test(HashMap<String,String> input) {
		
		ProductCatalogue pc = lp.LoginToApplication(input.get("username"), input.get("password"));

		
		String country ="India";

		pc.addToCart(product);
		CartPage cp = pc.goToCart();
		


		boolean itemPresent =cp.checkItems(product);
		AssertJUnit.assertTrue(itemPresent);
		CheckOut ch = cp.checkOut();
		
		ch.selectCountry(country);
		ConfirmationPage cop = ch.submitOrder();
		
		String confirmationMessage =cop.getConfrimationMessage();
		System.out.println(confirmationMessage);
		
		AssertJUnit.assertEquals(confirmationMessage, "THANKYOU FOR THE ORDER.");
		
		
		
	}
	
	@Test(dependsOnMethods= {"test"})
	public void orderTest() {
		
		ProductCatalogue pc = lp.LoginToApplication("nabeelans@gmail.com", "Abcd@123");
		OrdersPage op = pc.goToOrder();
		boolean  orderPresent =op.verifyOrder(product);
		Assert.assertTrue(orderPresent);
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException{
		
		
		List<HashMap<String,String>> data = getJsonData(System.getProperty("user.dir")+"/src/test/java/data/credentials.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
		
	/*	HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("username","nabeelans@gmail.com");
		map1.put("password", "Abcd@123");
		
		
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("username","sathish123@gmail.com");
		map2.put("password", "Abcd@123");
		
		return new Object[][] {{map1},{map2}};*/
		//return new Object[][] {{"nabeelans@gmail.com", "Abcd@123"}, {"sathish123@gmail.com", "Abcd@123"}};
	}

}
