package com.qa.opencart.basetests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.driverfactory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ResultsPage;

public class BaseTest {

	protected WebDriver driver;
	protected DriverFactory df;
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected ResultsPage resultPage;
	protected SoftAssert softAssert;
	

	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		driver = df.initDriver("Firefox");
		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();

	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
