package com.assignment.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManagerFactory {

	private static WebDriver driver;

	/**
	 * This method is to initialize the driver based on the browser name.
	 * 
	 * @param browserName: Accept browser name type of browser enum.
	 * @return
	 */
	public static WebDriver setUpDriver(BrowserEnum browserName) {

		driver = WebDriverManager.getInstance();

		switch (browserName) {
		case CHROME:
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Driver/chromedriver.exe");

			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("--incognito");
			driver = new ChromeDriver(options);
			return driver;

		case FIREFOX:
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/Driver/geckodriver.exe");

			driver = new FirefoxDriver();
			return driver;

		default:
			throw new IllegalArgumentException("Invalid browser name");
		}
	}
}
