package com.qa.opencart.tests;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.basetests.BaseTest;

public class ProductPageInfoTest extends BaseTest {

	@BeforeClass
	public void productPageSetup() {
		accPage = loginPage.doLogin("payalgaikwad21@gmail.com", "Payal@21nov");
	}

	@DataProvider
	public Object[][] productTestData() {
		return new Object[][] { { "MacBook", "MacBook Pro" }, { "MacBook", "MacBook Air" }, { "iPhone", "iPhone" } };
	}

	@Test(dataProvider = "productTestData")
	public void headerTest(String searchKey, String productHeader) {
		resultPage = accPage.SearchProduct(searchKey);
		productInfoPage = resultPage.selectProduct(productHeader);
		String actualHeader = productInfoPage.doGetProdcutPageHeader();
		System.out.println("Actual Header of product page : " + actualHeader);
		Assert.assertEquals(actualHeader, productHeader);
	}

	@DataProvider
	public Object[][] productImageData() {
		return new Object[][] { { "MacBook", "MacBook Pro", 4 },
				{ "Samsung", "Samsung Galaxy Tab 10.1" + "", 7 }, { "HP", "HP LP3065", 3 } };
	}

	@Test(dataProvider = "productImageData")
	public void imageCountTest(String searchKey, String productName, int expImageCount) {
		resultPage = accPage.SearchProduct(searchKey);
		productInfoPage = resultPage.selectProduct(productName);
		int imageCount = productInfoPage.doGetImageCount();
		System.out.println("Total Number of images : " + imageCount);
		Assert.assertEquals(imageCount, expImageCount);
	}

	@Test
	public void productMetaDataTest() {
		resultPage = accPage.SearchProduct("MacBook");
		productInfoPage = resultPage.selectProduct("MacBook Pro");
		HashMap<String,String> actualproductMetaData = productInfoPage.getProductData();
		System.out.println(actualproductMetaData);
		softAssert.assertEquals(actualproductMetaData.get("Brand"), "Apple");
		softAssert.assertEquals(actualproductMetaData.get("Availability"), "In Stock");
		softAssert.assertEquals(actualproductMetaData.get("Product Code"), "Product 18");
		softAssert.assertEquals(actualproductMetaData.get("Reward Points"), "800");
		softAssert.assertEquals(actualproductMetaData.get("Price"), "$2,000.00");
		softAssert.assertEquals(actualproductMetaData.get("Ex Tax"), "$2,000.00");
		softAssert.assertAll();





	}
}
