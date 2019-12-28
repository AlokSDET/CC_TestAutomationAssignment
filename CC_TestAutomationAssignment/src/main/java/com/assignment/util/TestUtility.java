package com.assignment.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.assignment.base.BaseTest;

public class TestUtility extends BaseTest{

	public static String getSystemDate() {
		DateFormat dateFormat = new SimpleDateFormat("_ddMMyyyy_HHmmss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String getDecodedString(String encodedString) {
		return new String(Base64.getDecoder().decode(encodedString.getBytes()));
	}
	
	//Explicit wait
	public static void waitUntilElementBecomeClicable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.MAX_TIMEOUT);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
}
