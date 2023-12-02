package com.qa.opencart.tests;

import org.testng.annotations.BeforeClass;

import com.qa.opencart.basetests.BaseTest;

public class ResultsPageTest extends BaseTest{
	
	@BeforeClass
	public void setupResultPage() {
		loginPage.doLogin("payalgaikwad21@gmail.com", "Payal@21nov");
	}

	
	
}
