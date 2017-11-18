package com.magazine.tests;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.magazine.architecture.WebDriverSingleton;
import com.magazine.pages.HomePO;

public class Order {

	private WebDriver driver = WebDriverSingleton.get();
	private StringBuffer verificationErrors = new StringBuffer();

	@Test
	public void testUntitledTestCase() throws Exception {
		
		try {
			
		    HomePO homePO = new HomePO().acessar();
		    homePO.moveCursorToSearch();
			homePO.fillFieldSearch();
			homePO.clickOnSearchButton();
			homePO.SelectProduct();
			homePO.AddCar();
			homePO.GotoLoginPage();
			homePO.Login();
			homePO.ConfimBuy();
		} catch (Throwable t) {
			WebDriverSingleton.capturaEvidencia("Order fail.");
			throw t;
		}
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	
}
