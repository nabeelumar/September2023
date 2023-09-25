package e2eTesting;

import org.testng.Assert;
import org.testng.annotations.Test;

import testComponents.BaseTest;

public class NegativeScenario extends BaseTest{
	
@Test(groups= {"errorHandling"}, retryAnalyzer = testComponents.Retry.class)
public void negativeTest() {
lp.LoginToApplication("nabeelans@gmail.com", "ABC");
Assert.assertEquals(lp.getErrorMsg(), "Incorrect email or password. Extra");

	
}
}
