package com.qa.opencart.driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

	public WebDriver driver;

	public WebDriver initDriver(String browserName) {

		switch (browserName) {

		case "Chrome":
			driver = new ChromeDriver();
			break;

		case "Edge":
			driver = new EdgeDriver();
			break;

		case "Firefox":
			driver = new FirefoxDriver();
			break;

		case "Safari":
			driver = new SafariDriver();
			break;

		}

		if (driver == null) {
			System.out.println("Please Enter valid browser name..");
		}

		return driver;

	}
}
