package com.assignment.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManagerFactory {

	/**
	 * The WebDriverClass class is used to generate a single instance of driver to
	 * launch the browser using singleton design pattern.
	 */
	private static WebDriver driver;

	private DriverManagerFactory() {

	}

	public static synchronized WebDriver getInstanceOfWebDriver(BrowserEnum browserName) {
		if (driver == null) {
			setUpDriver(browserName);
		}
		return driver;
	}

	/**
	 * This method is to initialize the driver based on the browser name.
	 * 
	 * @param browserName: Accept browser name type of browser enum.
	 * @return
	 */
	public static WebDriver setUpDriver(BrowserEnum browserName) {

		switch (browserName) {
		case CHROME:
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");

			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("--incognito");
			driver = new ChromeDriver(options);
			return driver;

		case FIREFOX:
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\Drivers\\geckodriver.exe");

			driver = new FirefoxDriver();
			return driver;

		default:
			throw new IllegalArgumentException("Invalid browser name");
		}
	}
}
