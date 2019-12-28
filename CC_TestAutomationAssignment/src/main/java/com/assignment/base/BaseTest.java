package com.assignment.base;

import static com.assignment.util.Constants.IMPLICIT_WAIT;
import static com.assignment.util.Constants.PAGE_LOAD_TIMEOUT;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.assignment.util.BrowserEnum;
import com.assignment.util.Constants;
import com.assignment.util.DriverManagerFactory;
import com.assignment.util.TestUtility;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

//Using Base Class we are achieving Inheritance Concept from Java.
public class BaseTest {

	public static final String PropertyFilePath = System.getProperty("user.dir")
			+ "/src/main/resources/com/assignment/config/config.Properties";

	public static Properties properties;

	public static ExtentReports extentReport;

	public static ExtentTest extentTest;

	public static WebDriver driver;

	// Constructor to read configuration data from property file.
	public BaseTest() {
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
	}

	@BeforeTest()
	public void setUp() {
		BrowserEnum browser = BrowserEnum.valueOf(Constants.browser);
		driver = DriverManagerFactory.setUpDriver(browser);
		driver.get(Constants.url);
		driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}

	@BeforeClass()
	public void startReport() {
		// Telling System Where Exactly Extent Report has to be Generated under Project.
		extentReport = new ExtentReports(System.getProperty("user.dir") + "/AssignmentResults/AssignmentExtentReports"
				+ TestUtility.getSystemDate() + ".html");
		extentReport.addSystemInfo("Host Name", "Alok's Windows System");
		extentReport.addSystemInfo("User Name", "Alok Shrivastava");
		extentReport.addSystemInfo("Environment", "Automation Testing");
	}

	@AfterClass()
	public void endReport() {
		extentReport.flush();
		extentReport.close();
	}

	@AfterTest()
	public void tearDown() {
		driver.quit();
	}
}
