package com.assignment.tests;

import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.testng.Reporter;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.assignment.base.BaseTest;
import com.assignment.pages.LoggedInDefaultPage;
import com.assignment.pages.LoginPage;
import com.relevantcodes.extentreports.LogStatus;

public class LoginTest extends BaseTest {

	public LoginPage loginPage;

	public LoggedInDefaultPage loggedInDefaultPage;

	@BeforeClass()
	public void setUpBeforeLoginClass() {
		loginPage = new LoginPage();
		loggedInDefaultPage = new LoggedInDefaultPage();
	}

	@BeforeGroups("Successfull login")
	public void setUpBeforeGroup() {
		loginPage.logInGmailWithCorrectCred(url);
	}

	@AfterGroups("Successfull login")
	public void tearDownAfterGroup() {
		loggedInDefaultPage.signOutGmail();
	}

	@BeforeMethod()
	public void startTest(Method method) {
		extentReport.startTest(method.getName(), "Test Method Started at " + extentTest.getStartedTime());
	}

	@AfterMethod()
	public void endTest(Method method) {
		extentReport.endTest(extentTest);
	}

	@Test(description = "Verify that user is logged-in in Gmail Application successfully", priority = 0, groups = {
			"Successfull login" })
	public void loginGmail() {
		String actualAccountEmail = loggedInDefaultPage.getGoogleAccountAriaLabel().trim().split("\\(")[1]
				.split("\\)")[0];
		assertTrue(actualAccountEmail.contains(BaseTest.userName.trim()),
				"Gmail Login is not successfull." + "Expected user name is " + BaseTest.userName + " and actual is "
						+ loggedInDefaultPage.getGoogleAccountAriaLabel());
		extentTest.log(LogStatus.PASS, "Gmail login is successfull for " + BaseTest.userName);
		Reporter.log("Login is successfull");
	}

	@Test(description = "Verify that Primary section is selected by default after login", priority = 1, dependsOnMethods = "loginGmail", groups = {
			"Successfull login" })
	public void VerifyPrimaryTabIsSelectedByDefault() {
		assertTrue(loggedInDefaultPage.isTabSelected("Primary"), "Primary Section is not selected by default");
		extentTest.log(LogStatus.PASS, "Primary Tab is selected by default");
		Reporter.log("Primary Tab is selected by default");
	}

}
