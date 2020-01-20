package com.assignment.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.assignment.base.BaseTest;
import com.assignment.util.WrapperMethods;

public class LoggedInDefaultPage extends BasePage {

	@FindBy(xpath = "//a[contains(@aria-label, 'Google Account: ')]")
	private WebElement googleAccount;

	@FindBy(xpath = "//div[@aria-label='Primary']")
	private WebElement primaryTab;

	@FindBy(xpath = "//div[@id=':2e']")
	private WebElement socialTab;

	@FindBy(xpath = "//div[@id=':2f']")
	private WebElement promotionsTab;

	@FindBy(xpath = "//div[@aria-label='Show more messages']//span[contains(text(),'of')]")
	private WebElement emailCountInfo;

	@FindBy(xpath = "//a[contains(text(),'Sign out')]")
	private WebElement signOut;

	@FindBy(xpath = "//div[contains(text(),'Use another account')]")
	private WebElement chooseAnotherAccount;
	
	@FindBy(xpath = "//div[contains(text(),'Remove an account')]")
	private WebElement removeAnAccount;
	
	@FindBy(xpath = "(//span[contains(text(),'Yes, remove')])[2]")
	private WebElement yesRemoveElement;

	public LoggedInDefaultPage() {
		super();
	}

	public String getGoogleAccountAriaLabel() {
		return this.googleAccount.getAttribute("aria-label");
	}

	public void selectPrimaryTab() {
		WrapperMethods.clickOnElement(this.primaryTab);
	}

	public void selectSocialTab() {
		WrapperMethods.clickOnElement(this.socialTab);
	}

	public void selectPromotionsTab() {
		WrapperMethods.clickOnElement(this.promotionsTab);
	}

	public boolean isTabSelected(String tabName) {
		String tabAreaSelectionStatus = "";
		switch (tabName.toLowerCase()) {

		case "primary":
			tabAreaSelectionStatus = isTabAreaSelected(this.primaryTab);
			break;

		case "social":
			tabAreaSelectionStatus = isTabAreaSelected(this.socialTab);
			break;

		case "promotions":
			tabAreaSelectionStatus = isTabAreaSelected(this.promotionsTab);
			break;

		default:
			throw new IllegalArgumentException("invalid tab name");
		}

		if (tabAreaSelectionStatus.toLowerCase().equals("true")) {
			return true;
		}
		return false;
	}

	public String getTotalNoOfEmail() {
		return this.emailCountInfo.getText().split("of")[1].trim();
	}

	public String getSubjectOfNthEmailInSocialTab(int emailNo) {
		return driver
				.findElement(By.xpath(
						"(//span[text()='Ad']//following::table)//tr[" + emailNo + "]//td[6]//span[@class='bqe']"))
				.getText();
	}

	public String getSenderOfNthEmailInSocialTab(int emailNo) {
		return driver
				.findElement(By.xpath(
						"(//span[text()='Ad']//following::table)//tr[" + emailNo + "]//td[5]//div[2]//span[@email]"))
				.getText();
	}

	public void clickOnGoogleAccount() {
		WrapperMethods.clickOnElement(this.googleAccount);
	}

	public boolean existenseOfChooseAnAccount() {
		List<WebElement> chooseAccount = driver.findElements(By.xpath("//span[contains(text(), 'Choose an account')]"));
		if (chooseAccount.size() > 0) {
			return true;
		}
		return false;
	}

	public void selectGmailAccount() {
		driver.findElement(By.xpath("//div[contains(text(),'"+ BaseTest.userName + "')]")).click();
	}
	
	
	public void signOutGmail() {
		clickOnGoogleAccount();
		WrapperMethods.clickOnElement(this.signOut);
		WrapperMethods.clickOnElement(this.removeAnAccount);
		selectGmailAccount();
		WrapperMethods.clickOnElement(this.yesRemoveElement);
		//waitForPageLoaded();
	}
}
