package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.basetests.BaseTest;
import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.AppErrors;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void setupAccPage() {
		accPage = loginPage.doLogin("payalgaikwad21@gmail.com", "Payal@21nov");
	}

	@Test(priority = 1)
	public void titleTest() {
		String ActTitle = accPage.doGetTitle();
		System.out.println("Accounts Page Title is : " + ActTitle);
		Assert.assertEquals(ActTitle, AppConstants.ACCOUNTS_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void urlTest() {
		String ActUrl = accPage.doGetURL();
		System.out.println("Accounts Page Url is : " + ActUrl);
		Assert.assertEquals(ActUrl, AppConstants.ACCOUNTS_PAGE_URL);
	}

	@Test(priority = 3)
	public void logoutLinkPresentTest() {
		Assert.assertTrue(accPage.IsLogoutLinkPresent(), AppErrors.NO_LOGOUT_LINK_PRESENT);
	}

	@Test(priority = 4)
	public void searchBoxPresentTest() {
		Assert.assertTrue(accPage.IsSearchBoxPresent());
	}

	@Test(priority = 5)
	public void searchButtonPresentTest() {
		Assert.assertTrue(accPage.IsSearchButtonPresent());
	}

	@Test(priority = 6)
	public void accSecHeadersPresentTest() {
		List<String> ActualAccSecHeaders = accPage.getAccSecHeadersList();
		Assert.assertEquals(ActualAccSecHeaders, AppConstants.ExpectedAccSecHeaders);
	}

	@DataProvider
	public Object[][] productData() {
		return new Object[][] { { "MacBook" }, { "Iphone" }, { "Samsung" } };
	}

	@Test(priority = 7, dataProvider = "productData")
	public void searchProductTest(String productName) {
		resultPage = accPage.SearchProduct(productName);
		String actTitle = resultPage.doGetTitle(productName);
		Assert.assertEquals(actTitle, (AppConstants.RESULTS_PAGE_TITLE + productName), AppErrors.NO_TITLE_MATCHED);
		Assert.assertTrue(resultPage.getSearchProductCount() > 0, AppErrors.NO_PRODUCT_FOUND);

	}
}
