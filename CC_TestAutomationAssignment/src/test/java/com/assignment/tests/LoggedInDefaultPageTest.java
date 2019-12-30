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
import com.relevantcodes.extentreports.LogStatus;

public class LoggedInDefaultPageTest extends BaseTest {

	public LoggedInDefaultPage loggedInDefaultPage;
	public LoginPage loginPage;

	@BeforeClass()
	public void setUpBeforeLoggedInClass() {
		loginPage = new LoginPage();
		loginPage.logInGmailWithCorrectCred(url);
		loggedInDefaultPage = new LoggedInDefaultPage();
	}

	@AfterClass
	public void tearDownAfterLoggedInClass() {
		loggedInDefaultPage.signOutGmail();
	}

	@BeforeMethod()
	public void startTest(Method method) {
		extentReport.startTest(method.getName(), "Test Started at " + extentTest.getStartedTime());
	}

	@AfterMethod()
	public void endTest(Method method) {
		extentReport.endTest(extentTest);
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

		extentTest.log(LogStatus.INFO, "Total no of email in social tab are :" + totalEmailCountInSocialTab);
		Reporter.log(totalEmailCountInSocialTab);
	}

	@Test(description = " This test gives the sender and subject of 12th Email of inbox. (Under the social tab)")
	public void getSubjectAndSenderOfEmail() {
		// Now Select Social Tab
		loggedInDefaultPage.selectSocialTab();

		String subject = loggedInDefaultPage.getSenderOfNthEmailInSocialTab(12);
		String sender = loggedInDefaultPage.getSubjectOfNthEmailInSocialTab(12);

		extentTest.log(LogStatus.INFO, "Subject of 12th email in Social Tab is : " + subject);
		Reporter.log(subject);

		extentTest.log(LogStatus.INFO, "Sender of 12th email in Social Tab is : " + sender);
		Reporter.log(sender);
	}
}
