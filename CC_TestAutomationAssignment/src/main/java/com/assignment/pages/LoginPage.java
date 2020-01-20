package com.assignment.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.assignment.base.BaseTest;
import com.assignment.util.TestUtility;
import com.assignment.util.WrapperMethods;

public class LoginPage extends BasePage {

	@FindBy(xpath = "//input[@id='identifierId']")
	private WebElement userName;

	@FindBy(xpath = "//input[@name='password']")
	private WebElement password;

	@FindBy(xpath = "//span[contains(text(),'Next')]")
	private WebElement nextBtn;

	public LoginPage() {
		super();
	}

	public void enterUserName(String userName) {
		WrapperMethods.enterText(this.userName, userName);
	}

	public void enterPassword(String password) {
		WrapperMethods.enterText(this.password, password);
	}

	public void clickOnNextBtn() {
		WrapperMethods.clickOnElement(this.nextBtn);
	}

	public LoggedInDefaultPage logInGmailWithCorrectCred(String url) {
		driver.get(url);
		//waitForPageLoaded();
		enterUserName(BaseTest.userName);
		clickOnNextBtn();
		enterPassword(TestUtility.getDecodedString(BaseTest.encodedPassword));
		clickOnNextBtn();
		return new LoggedInDefaultPage();
	}
}
