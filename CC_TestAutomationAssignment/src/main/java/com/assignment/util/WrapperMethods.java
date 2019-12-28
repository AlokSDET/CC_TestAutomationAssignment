package com.assignment.util;

import static com.assignment.util.TestUtility.waitUntilElementBecomeClicable;

import org.openqa.selenium.WebElement;

public class WrapperMethods {

	public static void enterText(WebElement element, String textToEnter) {
		waitUntilElementBecomeClicable(element);
		element.clear();
		element.click();
		element.sendKeys(textToEnter);
	}
	
	public static void clickOnElement(WebElement element) {
		waitUntilElementBecomeClicable(element);
		element.click();
	}
}
