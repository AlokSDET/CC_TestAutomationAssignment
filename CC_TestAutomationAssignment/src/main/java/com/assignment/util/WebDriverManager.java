package com.assignment.util;

import org.openqa.selenium.WebDriver;

/**
 * The WebDriverClass class is used to generate a single instance of driver to
 * launch the browser using singleton design pattern.
 */
public class WebDriverManager {

	private static WebDriver driver;

	private WebDriverManager() {

	}

	public static synchronized WebDriver getInstance() {
		if (driver == null) {
			driver = (WebDriver) new WebDriverManager();
			return driver;
		} else {
			return driver;
		}
	}
}
