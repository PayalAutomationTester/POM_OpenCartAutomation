package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	private WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getElement(By locator) {

		return driver.findElement(locator);
	}

	public List<WebElement> getElements(By locator) {

		return driver.findElements(locator);
	}

	public void doSendKeys(By locator, String value) {
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();

	}

	public String doGetText(By locator) {
		return getElement(locator).getText();

	}

	public String doGetAttribute(By locator, String attributeName) {

		return getElement(locator).getAttribute(attributeName);

	}

	public void getAllLinkTexts(By linksLocator) {
		List<WebElement> linksList = getElements(linksLocator);
		int counter = 1;
		System.out.println("No of links: " + linksList.size());
		for (WebElement e : linksList) {
			String linktext = e.getText();
			if (!(linktext.length() == 0)) {
				if (!(linktext.equals(null))) {
					System.out.println(counter + " = " + linktext);
				}
			}
			counter++;

		}
	}

	public void getAllLinkHref(By linksLocator) {
		List<WebElement> linksList = getElements(By.tagName("a"));
		int counter = 1;
		System.out.println("No of links: " + linksList.size());
		for (WebElement e : linksList) {
			String linktext = e.getText();
			if (!(linktext.length() == 0)) {
				if (!(linktext.equalsIgnoreCase(""))) {
					System.out.println((e.getAttribute("href")) + " || " + (e.getAttribute("aria-label")));
				}
			}
			counter++;
		}
	}

	public void getAllImagesSrc(By locator) {
		List<WebElement> linksList = getElements(locator);
		System.out.println("No of Imgaes: " + linksList.size());
		int counter = 1;
		for (WebElement e : linksList) {
			System.out.println((counter + ": " + e.getAttribute("alt")) + " || " + (e.getAttribute("src")));
			counter++;
		}
	}

	public void selectByIndexInDropDown(By locator, int index) {
		WebElement dropDownLocator = getElement(locator);
		Select select = new Select(dropDownLocator);
		select.selectByIndex(index);
	}

	public void selectByVisibleTextInDropDown(By locator, String text) {
		WebElement dropDownLocator = getElement(locator);
		Select select = new Select(dropDownLocator);
		select.selectByVisibleText(text);
	}

	public void selectByValueInDropDown(By locator, String text) {
		WebElement dropDownLocator = getElement(locator);
		Select select = new Select(dropDownLocator);
		select.selectByValue(text);
	}

	public void selectByOptionsInDropDown(By locator, String text) {
		WebElement dropDownLocator = getElement(locator);
		Select select = new Select(dropDownLocator);
		List<WebElement> dropdownList = select.getOptions();
		System.out.println("No of Countries: " + dropdownList.size());
		for (WebElement e : dropdownList) {
			String elementSelect = e.getAttribute("value");
			if (elementSelect.equals(text)) {
				e.click();
				break;
			}
		}
	}

	public void DropDownWithoutSelectClass(By locator, String text) {
		List<WebElement> dropdownList = getElements(locator);
		System.out.println("No of Countries: " + dropdownList.size());
		for (WebElement e : dropdownList) {
			String elementSelect = e.getAttribute("value");
			if (elementSelect.equals(text)) {
				e.click();
				break;
			}
		}
	}

	/**
	 * 1. This function can select single and multiple checkboxes from dropdown 2.
	 * Pass choice as "all" for selecting all checkboxes from dropdown
	 * 
	 * @param locator
	 * @param choices
	 */
	public void JqueryDropDownSelect(By locator, String... choices) {
		List<WebElement> choicesList = getElements(locator);
		boolean flag = false;
		for (WebElement e : choicesList) {
			if (!(choices[0].equalsIgnoreCase("all"))) {
				for (int i = 0; i < choices.length; i++) {
					if (e.getText().equalsIgnoreCase(choices[i])) {
						e.click();
						flag = true;
						break;
					}

				}

			} else {
				try {
					e.click();
					flag = true;
				} catch (Exception ex) {
				}
			}
		}

		if (flag == false) {
			System.out.println("Choices not available..");
		}

	}

	public void getElementList(By locator) {
		List<WebElement> elementList = getElements(locator);
		List<String> elementListText = new ArrayList<>();
		for (WebElement e : elementList) {
			elementListText.add(e.getText());
		}

		System.out.println("List elements: " + elementListText);
		System.out.println("List count: " + elementListText.size());

	}

	public Boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public void isSingleElementDisplayed(By locator) {
		int size = getElements(locator).size();
		if (size == 1) {
			System.out.println("Single Element is Displayed!");
		} else {
			System.out.println("FAIL");
		}

	}

	public void isTwoElementDisplayed(By locator) {
		int size = getElements(locator).size();
		if (size == 2) {
			System.out.println("Two Elements are Displayed!");
		} else {
			System.out.println("FAIL");
		}

	}

	public void isMultipleElementDisplayed(By locator) {
		int size = getElements(locator).size();
		if (size > 1) {
			System.out.println("Multiple Elements are Displayed: " + size);
		} else {
			System.out.println("FAIL");
		}

	}

	public void isMultipleElementDisplayed(By locator, int expectedcount) {
		int size = getElements(locator).size();
		if (size == expectedcount) {
			System.out.println("Multiple Elements are Displayed: " + size);
		} else {
			System.out.println("FAIL");
		}

	}

	public void selectSubMenu(String htmltag, String parentMenu, String childMenu) throws InterruptedException {

		WebElement parentElement = getElement(By.xpath("//" + htmltag + "[text()='" + parentMenu + "']"));
		Actions act = new Actions(driver);
		act.moveToElement(parentElement).build().perform();
		Thread.sleep(1000);
		WebElement childElement = getElement(By.xpath("//" + htmltag + "[text()='" + childMenu + "']"));
		childElement.click();
	}

	public void selectMultilevelSubMenu(By parentLocator, String htmltag, String L1Menu, String L2Menu, String L3Menu)
			throws InterruptedException {
		WebElement ParentElement = getElement(parentLocator);
		Actions act = new Actions(driver);
		act.moveToElement(ParentElement).build().perform();
		;
		Thread.sleep(2000);

		WebElement ElementL1 = driver.findElement(By.xpath("(//" + htmltag + "[text()='" + L1Menu + "'])[2]"));
		act.moveToElement(ElementL1).build().perform();
		;
		Thread.sleep(3000);

		WebElement ElementL2 = driver.findElement(By.xpath("(//" + htmltag + "[text()='" + L2Menu + "'])[2]"));
		act.moveToElement(ElementL2).build().perform();
		;
		Thread.sleep(2000);

		WebElement ElementL3 = driver.findElement(By.xpath("(//" + htmltag + "[text()='" + L3Menu + "'])[2]"));
		ElementL3.click();

	}

	public void dragAndDrop(By sourceLocator, By targetLocator) throws InterruptedException {
		WebElement sourceElement = getElement(sourceLocator);
		WebElement targetElement = getElement(targetLocator);
		Actions act = new Actions(driver);
		act.clickAndHold(sourceElement).moveToElement(targetElement).release().build().perform();
		// act.dragAndDrop(sourceElement, targetElement).build().perform();;
		Thread.sleep(2000);
		System.out.println("Source element dropped to target successfully...");

	}

	public void doSendKeysActionsClass(By locator, String value) {
		WebElement element = getElement(locator);
		Actions act = new Actions(driver);
		act.sendKeys(element, value).build().perform();
		;
	}

	public void doClickActionsClass(By locator) {
		WebElement element = getElement(locator);
		Actions act = new Actions(driver);
		act.click(element).build().perform();
	}

	public void switchToAlert() {
		Alert alert = driver.switchTo().alert();
		System.out.println("Alert Desc : " + alert.getText());
		alert.accept();
	}

	public void switchToConfirmAlert() {
		Alert alert = driver.switchTo().alert();
		System.out.println("Alert Desc : " + alert.getText());
		alert.accept();
	}

	public void switchToDismissAlert() {
		Alert alert = driver.switchTo().alert();
		System.out.println("Alert Desc : " + alert.getText());
		alert.dismiss();
	}

	public void switchToPromtAlert(String value) throws InterruptedException {
		Alert alert = driver.switchTo().alert();
		System.out.println("Alert Desc : " + alert.getText());
		alert.sendKeys(value);
		Thread.sleep(2000);
		alert.accept();
	}

	public void fileUpload(By locator, String path) {
		WebElement fileUploadButton = getElement(locator);
		fileUploadButton.sendKeys(path);
		System.out.println("File uploaded...");
	}

	public void authPopup(String username, String password, String url) {
		driver.get("https://" + username + ":" + password + "@" + url + "");

	}

	public void browserWindowPopup(By locator, String url) throws InterruptedException {
		WebElement linkElement = getElement(locator);
		linkElement.click();
		Thread.sleep(5000);
		Set<String> windowHandleList = driver.getWindowHandles();
		Iterator<String> it = windowHandleList.iterator();
		String parentWindowId = it.next();
		System.out.println("parentWindowId: " + parentWindowId);
		String childWindowId = it.next();
		System.out.println("childWindowId: " + childWindowId);
		driver.switchTo().window(childWindowId);
		if (driver.getCurrentUrl().equals(url)) {
			System.out.println("Page opened successfully..");
		}
		driver.close();
		driver.switchTo().window(parentWindowId);
		System.out.println("Back to parent window: " + driver.getWindowHandle());
	}

	// ExplicitWait - TimeInterval + DefaultSleepTime = 500ms

	public WebElement waitForPresenceOfElement(int timeInterval, By locator) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInterval));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitForvisibilityOfElement(int timeInterval, By locator) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInterval));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement waitForElementToBeClickable(int timeInterval, By locator) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInterval));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public String waitForTitleContains(int timeInterval, String titleFractionValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInterval));
		if (wait.until(ExpectedConditions.titleContains(titleFractionValue))) {
			return driver.getTitle();
		} else {
			System.out.println("Title is incorrect..");
			return null;
		}
	}

	public String waitForTitleIs(int timeInterval, String title) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInterval));
		if (wait.until(ExpectedConditions.titleIs(title))) {
			return driver.getTitle();
		} else {
			System.out.println("Title is incorrect..");
			return null;
		}
	}

	public String waitForUrlContains(int timeInterval, String UrlFractionValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInterval));
		if (wait.until(ExpectedConditions.urlContains(UrlFractionValue))) {
			return driver.getCurrentUrl();
		} else {
			System.out.println("URL is Incorrect");
			return null;
		}
	}

	public String waitForUrlToBe(int timeInterval, String Url) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInterval));
		if (wait.until(ExpectedConditions.urlToBe(Url))) {
			return driver.getCurrentUrl();
		} else {
			System.out.println("URL is Incorrect");
			return null;
		}
	}

	public void waitForJSAlertAndAccept(int timeInterval) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInterval));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
	}

	public void waitForConfirmAlertAndAccept(int timeInterval) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInterval));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
	}

	public void waitForConfirmAlertAndDismiss(int timeInterval) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInterval));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.dismiss();
	}

	public void waitForPromtAlertAndSendKeys(int timeInterval, String keysToSend) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInterval));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.sendKeys(keysToSend);
		alert.accept();
	}

	public WebDriver waitForFrameAndSwitchUsingIndex(int timeInterval, int index) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInterval));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));

	}

	public WebDriver waitForFrameAndSwitchUsingId(int timeInterval, String NameOrId) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInterval));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(NameOrId));

	}

	public WebDriver waitForFrameAndSwitchUsingFrameLocator(int timeInterval, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInterval));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));

	}

	public List<WebElement> waitForAllElementsToBeVisible(int timeInterval, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInterval));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}

	// TimeInterval + CustomSleepTime

	public WebElement waitWithCustomIntervalTime(int timeInterval, long sleepTime, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInterval), Duration.ofMillis(sleepTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	// Fluent wait

	public WebElement waitForElementWithFluentWait(int timeInterval, By locator) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeInterval))
				.pollingEvery(Duration.ofMillis(1000)).ignoring(NoSuchElementException.class)
				.ignoring(NoAlertPresentException.class).withMessage("Element Not Found..");

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement WebDriverWaitWithFluent(int timeInterval, long sleepTime, By locator) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInterval), Duration.ofMillis(sleepTime));
		wait.ignoring(NoSuchElementException.class).ignoring(NoAlertPresentException.class)
				.withMessage("Element Not Found..");
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// CustomWait : With custom TimeOut and custom Interval Time

	public WebElement customWaitWithIntervalTime(int timeOut, long intervalTime, By locator) throws FrameworkException {
		WebElement element = null;
		int attempts = 0;
		while (attempts < timeOut) {

			try {
				element = getElement(locator);
				System.out.println("Element found...");
				break;
			} catch (NoSuchElementException e) {
				System.out.println("Element not found after attempt " + attempts + " for locator " + locator);
				try {
					Thread.sleep(intervalTime);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}

			attempts++;

		}

		if (element == null) {
			System.out.println(
					"Element not found, tried for " + attempts + " times with " + intervalTime + " ms IntervalTime");
			throw new FrameworkException("TimeOutException");
		}

		return element;

	}

	// WaitForPageLoad

	public void waitForPageLoad(int timeOut) {
		long endTime = System.currentTimeMillis() + timeOut;

		while (System.currentTimeMillis() < endTime) {
			System.out.println(System.currentTimeMillis());
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String pageState = js.executeScript("return document.readyState").toString();
			if (pageState.equalsIgnoreCase("Complete")) {
				System.out.println("Page is loaded for " + driver.getTitle());
				break;
			}
		}
	}

}
