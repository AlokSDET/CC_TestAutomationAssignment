package com.assignment.tests;

import java.lang.reflect.Method;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.assignment.base.BaseTest;
import com.assignment.pages.LoggedInDefaultPage;
import com.assignment.pages.LoginPage;
import com.aventstack.extentreports.Status;

public class LoggedInDefaultPageTest extends BaseTest {

	public LoggedInDefaultPage loggedInDefaultPage;
	public LoginPage loginPage;

	@BeforeClass()
	public void setUpBeforeLoggedInClass() {
		loginPage = new LoginPage();
		loggedInDefaultPage = loginPage.logInGmailWithCorrectCred(url);
		logger.info(LoggedInDefaultPageTest.class.getSimpleName() + " class started...");

	}


	@BeforeMethod()
	public void startTest(Method method) {
		extentTest = extent.createTest(method.getName(), " Test Started at " + extentHtmlReporter.getStartTime());
		logger.info("method " + method.getName() + " started...");
	}

	@AfterClass
	public void tearDownAfterLoggedInClass() {
		loggedInDefaultPage.signOutGmail();
		logger.info("logged out from gmail....and " + LoggedInDefaultPageTest.class.getSimpleName() + " class ended.");
	}

	
	@AfterMethod()
	public void endTest(Method method) {
		extentTest = extent.createTest(method.getName(), " Test ended at " + extentHtmlReporter.getStartTime());
		logger.info("method " + method.getName() + " ended...");
	}

	@Test(description = "This test gives the total number of emails in social Tab")
	public void getEmailCountInSocialTab() {
		// By default primary tab is selected , if not then select it.
		if (!loggedInDefaultPage.isTabSelected("primary")) {
			loggedInDefaultPage.selectPrimaryTab();
		}
		// Now Select Social Tab
		loggedInDefaultPage.selectSocialTab();

		String totalEmailCountInSocialTab = loggedInDefaultPage.getTotalNoOfEmail();

		extentTest.log(Status.INFO, "Total no of email in social tab are :" + totalEmailCountInSocialTab);
		Reporter.log(totalEmailCountInSocialTab);
		logger.info("total email counted in social tab");
	}

	@Test(description = " This test gives the sender and subject of 12th Email of inbox. (Under the social tab)")
	public void getSubjectAndSenderOfEmail() {
		// Now Select Social Tab
		loggedInDefaultPage.selectSocialTab();

		String subject = loggedInDefaultPage.getSenderOfNthEmailInSocialTab(12);
		String sender = loggedInDefaultPage.getSubjectOfNthEmailInSocialTab(12);

		extentTest.log(Status.INFO, "Subject of 12th email in Social Tab is : " + subject);
		Reporter.log(subject);

		extentTest.log(Status.INFO, "Sender of 12th email in Social Tab is : " + sender);
		Reporter.log(sender);
		logger.info("subject and sender of nth email found ...");
	}
}
