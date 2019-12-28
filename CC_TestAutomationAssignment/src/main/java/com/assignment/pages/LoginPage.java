package com.assignment.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.assignment.util.Constants;
import com.assignment.util.TestUtility;
import com.assignment.util.WrapperMethods;

public class LoginPage extends BasePage {

	@FindBy(xpath = "//input[@id='identifierId']")
	private WebElement userName;

	
	@FindBy(xpath = "//input[@name='password']")
	private WebElement password;

	@FindBy(xpath = "//span[contains(text(),'Next')]")
	private WebElement nextBtn;

	public LoginPage(WebDriver driver) {
		super(driver);
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

	public LoggedInDefaultPage logInGmail(WebDriver driver) {
		String userName = Constants.userName;
		String encodedPassword = Constants.encodedPassword;
		enterUserName(userName);
		clickOnNextBtn();
		enterPassword(TestUtility.getDecodedString(encodedPassword));
		clickOnNextBtn();
		return new LoggedInDefaultPage(driver);
	}
}
