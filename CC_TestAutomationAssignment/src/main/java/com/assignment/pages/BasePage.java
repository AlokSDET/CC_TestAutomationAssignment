package com.assignment.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.assignment.util.Constants;

public class BasePage {

	public static WebDriver driver;

	public static void setWebDriver(WebDriver driver) {
		BasePage.driver = driver;
	}

	public BasePage() {
		PageFactory.initElements(driver, this);
	}

	// Explicit wait
	public static void waitUntilElementBecomeClicable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.MAX_TIMEOUT);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	// Wait until page loads completely
	public static void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 40);
			wait.until(expectation);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}

	public String isTabAreaSelected(WebElement element) {
		return element.getAttribute("aria-selected");
	}
}
