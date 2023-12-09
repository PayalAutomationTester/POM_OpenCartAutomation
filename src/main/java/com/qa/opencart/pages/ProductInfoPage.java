package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private By productPageHeader = By.cssSelector("#content h1");
	private By productImages = By.xpath("//a[@class='thumbnail']");
	private By productDataLoc = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPricingLoc = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");

	HashMap <String,String> productMap;
			
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}

	public String doGetTitle(String title) {
		return eleUtil.waitForTitleIs(TimeUtil.DEFAULT_TIMEOUT, title);
	}

	public String doGetProdcutPageHeader() {
		return eleUtil.doGetText(productPageHeader);
	}

	public int doGetImageCount() {
		return eleUtil.waitForAllElementsToBeVisible(TimeUtil.DEFAULT_TIMEOUT, productImages).size();
	}

	public void getprodMetaData() {
		List<WebElement> elementList = eleUtil.getElements(productDataLoc);
		for (WebElement e : elementList) {
			String[] metaDataArray = e.getText().split(":");
			productMap.put(metaDataArray[0].trim(), metaDataArray[1].trim());
		}
	}

	public void getProdPricingData() {
		List<WebElement> elementList = eleUtil.getElements(productPricingLoc);
		for(WebElement e : elementList) {
			if(e.getText().contains(":")) {
			String[] priceDataArray = e.getText().split(":");
			productMap.put(priceDataArray[0].trim(), priceDataArray[1].trim());
			}
			else {
				productMap.put("Price", e.getText());
			}
		}
	}
	
	public HashMap <String,String> getProductData() {
		productMap = new HashMap<String,String>();
		getprodMetaData();
		getProdPricingData();
		return productMap;

	}
}
