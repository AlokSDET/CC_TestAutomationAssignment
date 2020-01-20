package com.assignment.util;

import org.openqa.selenium.WebElement;

import com.assignment.pages.BasePage;

public class WrapperMethods {

	public static void enterText(WebElement element, String textToEnter) {
		BasePage.waitUntilElementBecomeClicable(element);
		element.clear();
		element.click();
		element.sendKeys(textToEnter);
		//BasePage.waitForPageLoaded();
	}

	public static void clickOnElement(WebElement element) {
		BasePage.waitUntilElementBecomeClicable(element);
		element.click();
		//BasePage.waitForPageLoaded();
	}
}
