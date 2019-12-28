package com.assignment.tests;

import java.lang.reflect.Method;

import org.testng.Reporter;
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
	public void setUp() {
		loginPage = new LoginPage(driver);
		loginPage.logInGmail(driver);
		loggedInDefaultPage = new LoggedInDefaultPage(driver);
	}

	@BeforeMethod()
	public void startTest(Method method) {
		extentReport.startTest(method.getName(), "Test Started at " + extentTest.getStartedTime());
	}

	@AfterMethod()
	public void endTest(Method method) {
		extentReport.startTest(method.getName(), "Test ended at " + extentTest.getEndedTime());
	}

	@Test(description = "This test gives the total number of emails in social Tab")
	public void getEmailCountInSocialTab() {
		// By default primary tab is selected , if not then select it.
		if (!loggedInDefaultPage.isPrimaryTabSelected()) {
			loggedInDefaultPage.selectPrimaryTab();
		}
		// Now Select Social Tab
		loggedInDefaultPage.selectSocialTab();

		String totalEmailCountInSocialTab = loggedInDefaultPage.getTotalNoOfEmail();

		extentTest.log(LogStatus.INFO, "Total no of email in social tab are " + totalEmailCountInSocialTab);
		Reporter.log(totalEmailCountInSocialTab);
	}

	@Test(description = " This test gives the sender and subject of 12th Email of inbox. (Under the social tab)")
	public void getSubjectAndSenderOfEmail() {
		// Now Select Social Tab
		loggedInDefaultPage.selectSocialTab();

		String subject = loggedInDefaultPage.getSenderOfNthEmailInSocialTab(12);
		String sender = loggedInDefaultPage.getSubjectOfNthEmailInSocialTab(12);

		extentTest.log(LogStatus.INFO, "Subject of 12th email in Social Tab " + subject);
		Reporter.log(subject);

		extentTest.log(LogStatus.INFO, "Sender of 12th email in Social Tab " + sender);
		Reporter.log(sender);
	}
}
