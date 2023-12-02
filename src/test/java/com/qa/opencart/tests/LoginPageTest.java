package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.basetests.BaseTest;
import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.AppErrors;

public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	public void titleTest() {
		String ActTitle = loginPage.doGetTitle();
		System.out.println("Login Page Title is : " + ActTitle);
		Assert.assertEquals(ActTitle, AppConstants.LOGIN_PAGE_TITLE, AppErrors.NO_TITLE_MATCHED);
	}

	@Test(priority = 2)
	public void urlTest() {
		String ActualUrl = loginPage.doGetURL();
		System.out.println("Login Page Url is : " + ActualUrl);
		Assert.assertEquals(ActualUrl, AppConstants.LOGIN_PAGE_URL, AppErrors.NO_URL_MATCHED);
	}

	@Test(priority = 3)
	public void forgotPwdLinkPresentTest() {
		Assert.assertTrue(loginPage.IsForgotPasswordLinkPresent(), AppErrors.FORGOT_PASSWORD_LINK_NOT_PRESENT);
	}

	@Test(priority = 4)
	public void loginTest() throws InterruptedException {
		accPage = loginPage.doLogin("payalgaikwad21@gmail.com", "Payal@21nov");
		Assert.assertTrue(accPage.IsLogoutLinkPresent(), AppErrors.LOGIN_UNSUCCESSFUL);

	}

}
