package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class LoginPage {
	private WebDriver driver;
	private By forgotPassword = By.linkText("Forgotten Password");
	private By username = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By logoutLink = By.linkText("Logout");
	private ElementUtil eleUtil;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String doGetTitle() {
		return eleUtil.waitForTitleIs(TimeUtil.DEFAULT_TIMEOUT, AppConstants.LOGIN_PAGE_TITLE);
	}

	public String doGetURL() {
		return eleUtil.waitForUrlToBe(TimeUtil.DEFAULT_TIMEOUT, AppConstants.LOGIN_PAGE_URL);
	}

	public boolean IsForgotPasswordLinkPresent() {
		return eleUtil.doIsDisplayed(forgotPassword);
	}

	public AccountsPage doLogin(String user, String pass) {
		eleUtil.waitForvisibilityOfElement(TimeUtil.DEFAULT_TIMEOUT, username).sendKeys(user);
		eleUtil.doSendKeys(password, pass);
		eleUtil.doClick(loginBtn);
		TimeUtil.SmallWait();
		return new AccountsPage(driver);
	}
}
