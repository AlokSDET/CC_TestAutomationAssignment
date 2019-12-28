package com.assignment.tests;

import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.assignment.base.BaseTest;
import com.assignment.pages.LoggedInDefaultPage;
import com.assignment.pages.LoginPage;
import com.assignment.util.Constants;
import com.relevantcodes.extentreports.LogStatus;

public class LoginTest extends BaseTest {

	public LoginPage loginPage;

	public LoggedInDefaultPage loggedInDefaultPage;

	@BeforeClass()
	public void setUp() {
		loginPage = new LoginPage(driver);
		loggedInDefaultPage = new LoggedInDefaultPage(driver);
	}

	@BeforeMethod()
	public void startTest(Method method) {
		extentReport.startTest(method.getName(), "Test Method Started at " + extentTest.getStartedTime());
	}

	@AfterMethod()
	public void endTest(Method method) {
		extentReport.startTest(method.getName(), "Test Method ended at " + extentTest.getEndedTime());
	}

	@Test(description = "Test to verify that user logged in Gmail Application successfully", priority = 0)
	public void loginGmail() {
		loggedInDefaultPage = loginPage.logInGmail(driver);
		assertTrue(loggedInDefaultPage.getGoogleAccountAriaLabel().contains(Constants.userName),
				"Gmail Login is not successfull.");
		Reporter.log("Login is successfull");
	}

	@Test(description = "Verify that Primary section is selected by default after login", priority = 1, dependsOnMethods = "loginGmail")
	public void VerifyPrimaryTabIsSelectedByDefault() {
		assertTrue(loggedInDefaultPage.isPrimaryTabSelected(), "Primary Section is not selected by default");
		extentTest.log(LogStatus.PASS, "Primary Tab is selected by default");
	}

}
