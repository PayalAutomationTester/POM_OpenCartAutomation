package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ResultsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By resultsLoc = By.cssSelector("div h4 a");

	public ResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String doGetTitle(String productName) {
		return eleUtil.waitForTitleContains(TimeUtil.DEFAULT_TIMEOUT, productName);
	}

	public int getSearchProductCount() {
		return eleUtil.waitForAllElementsToBeVisible(TimeUtil.DEFAULT_TIMEOUT, resultsLoc).size();
	}
	
	public ProductInfoPage selectProduct(String productName) {
		System.out.println("Selecting product : "+ productName);
		eleUtil.doClick(By.linkText(productName));
		TimeUtil.SmallWait();
		return new ProductInfoPage(driver);
	}

}
