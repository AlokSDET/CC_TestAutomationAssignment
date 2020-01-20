package com.assignment.tests;

import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.assignment.base.BaseTest;
import com.assignment.pages.LoggedInDefaultPage;
import com.assignment.pages.LoginPage;
import com.aventstack.extentreports.Status;

public class LoginTest extends BaseTest {

	public LoginPage loginPage;

	public LoggedInDefaultPage loggedInDefaultPage;

	@BeforeClass()
	public void setUpBeforeLoginClass() {
		loginPage = new LoginPage();
		loggedInDefaultPage = new LoggedInDefaultPage();
		logger.info(LoginTest.class.getSimpleName() + " class started ...");
	}


	@BeforeMethod()
	public void startTest(Method method) {
		loginPage.logInGmailWithCorrectCred(url);
		logger.info("Logged in gmail..");
		extentTest = extent.createTest(method.getName(), " Test Method Started at " + extentHtmlReporter.getStartTime());
		logger.info(method.getName() + " method started ...");
	}

	@AfterMethod()
	public void endTest(Method method) {
		loggedInDefaultPage.signOutGmail();
		logger.info("Logged out gmail..");
		extentTest = extent.createTest(method.getName(), " Test ended at " + extentHtmlReporter.getStartTime());
		logger.info("method " + method.getName() + " ended...");
	}
	

	@AfterClass
	public void tearDownAfterLogInClass() {
		logger.info( LoginTest.class.getSimpleName() + " class ended...");
	}

	
	@Test(description = "Verify that user is logged-in in Gmail Application successfully", priority = 0, groups = {
			"Successfull login" })
	public void loginGmail() {
		String actualAccountEmail = loggedInDefaultPage.getGoogleAccountAriaLabel().trim().split("\\(")[1]
				.split("\\)")[0];
		assertTrue(actualAccountEmail.contains(BaseTest.userName.trim()),
				"Gmail Login is not successfull." + "Expected user name is " + BaseTest.userName + " and actual is "
						+ loggedInDefaultPage.getGoogleAccountAriaLabel());
		extentTest.log(Status.PASS, "Gmail login is successfull for " + BaseTest.userName);
		extentTest.pass("Login Successful;");
		Reporter.log("Login is successfull");
	}

	@Test(description = "Verify that Primary section is selected by default after login", priority = 1, groups = {
			"Successfull login" })
	public void VerifyPrimaryTabIsSelectedByDefault() {
		assertTrue(loggedInDefaultPage.isTabSelected("Primary"), "Primary Section is not selected by default");
		extentTest.log(Status.PASS, "Primary Tab is selected by default");
		extentTest.pass("Primary Tab is selected by default;");
		Reporter.log("Primary Tab is selected by default");
		logger.info("Logged in gmail and verified that primary section is selected by default");
	}
}
