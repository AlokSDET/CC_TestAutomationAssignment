package com.assignment.base;

import static com.assignment.util.Constants.IMPLICIT_WAIT;
import static com.assignment.util.Constants.PAGE_LOAD_TIMEOUT;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.assignment.pages.BasePage;
import com.assignment.util.BrowserEnum;
import com.assignment.util.DriverManagerFactory;
import com.assignment.util.TestUtility;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

//Using Base Class we are achieving Inheritance Concept from Java.
public class BaseTest {

	public static final String PropertyFilePath = System.getProperty("user.dir")
			+ "\\src\\main\\resources\\com\\assignment\\config\\config.Properties";

	public static Properties properties;

	public static ExtentReports extentReport;

	public static ExtentTest extentTest;

	public static WebDriver driver;

	public static String userName;

	public static String encodedPassword;

	public static String url;

	public static String browser;

	// Method to load and set configuration data from property file.
	public static void loadPropertyFile() {
		try {
			properties = new Properties();
			File file = new File(PropertyFilePath);
			FileInputStream fis = new FileInputStream(file);
			properties.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		userName = properties.getProperty("username");
		encodedPassword = properties.getProperty("password");
		url = properties.getProperty("url");
		browser = properties.getProperty("browser");

	}

	@BeforeTest()
	public static void setUp() {
		loadPropertyFile();
		// Telling System Where Exactly Extent Report has to be Generated under Project.
		extentReport = new ExtentReports(System.getProperty("user.dir")
				+ "\\AssignmentResults\\AssignmentExtentReports\\" + TestUtility.getSystemDate() + ".html");

		extentReport.addSystemInfo("Host Name", "Alok's Windows System");
		extentReport.addSystemInfo("User Name", "Alok Shrivastava");
		extentReport.addSystemInfo("Environment", "Automation Testing");

		extentTest = extentReport.startTest("Gmail Suite");

		BrowserEnum browserEnumVal = BrowserEnum.valueOf(browser.toUpperCase());
		driver = DriverManagerFactory.getInstanceOfWebDriver(browserEnumVal);
		driver.get(url);
		driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().addCookie(new Cookie("cc", "Gmail"));
		BasePage.setWebDriver(driver);
	}

	@AfterTest()
	public static void tearDown() {
		if (driver != null) {
			driver.quit();
			Reporter.log("Browser Terminated");
		}
		extentReport.endTest(extentTest);
		extentReport.flush();
		extentReport.close();
	}

}
