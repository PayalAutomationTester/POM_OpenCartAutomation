package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class AccountsPage {
	private WebDriver driver;
	private By LogoutLink = By.linkText("Logout");
	private By searchBox = By.xpath("//input[@name='search']");
	private By searchButton = By.xpath("//div[@id='search']//button[@type='button']");
	private By accountSecHeaders = By.cssSelector("#content h2");
	private ElementUtil eleUtil;

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String doGetTitle() {
		return eleUtil.waitForTitleIs(TimeUtil.DEFAULT_TIMEOUT, AppConstants.ACCOUNTS_PAGE_TITLE);
	}

	public String doGetURL() {
		return eleUtil.waitForUrlToBe(TimeUtil.DEFAULT_TIMEOUT, AppConstants.ACCOUNTS_PAGE_URL);
	}

	public Boolean IsLogoutLinkPresent() {
		return eleUtil.waitForPresenceOfElement(TimeUtil.DEFAULT_TIMEOUT, LogoutLink).isDisplayed();
	}

	public Boolean IsSearchBoxPresent() {
		return eleUtil.waitForPresenceOfElement(TimeUtil.DEFAULT_TIMEOUT, searchBox).isDisplayed();
	}

	public Boolean IsSearchButtonPresent() {
		return eleUtil.doIsDisplayed(searchButton);
	}

	public List<String> getAccSecHeadersList() {
		List<WebElement> accountSecHeadersEle = eleUtil.waitForAllElementsToBeVisible(TimeUtil.DEFAULT_TIMEOUT,
				accountSecHeaders);
		List<String> accountSecHeadersVal = new ArrayList<String>();
		for (WebElement e : accountSecHeadersEle) {
			accountSecHeadersVal.add(e.getText());
		}
		return accountSecHeadersVal;
	}

	public ResultsPage SearchProduct(String product) {
		eleUtil.doSendKeys(searchBox, product);
		eleUtil.doClick(searchButton);
		return new ResultsPage(driver);

	}
}
