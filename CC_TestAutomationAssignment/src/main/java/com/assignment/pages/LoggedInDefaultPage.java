package com.assignment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.assignment.util.WrapperMethods;

public class LoggedInDefaultPage extends BasePage{


	@FindBy(xpath = "//a[contains(@aria-label, 'Google Account: ')]")
	private WebElement googleAccount;
	
	@FindBy(xpath = "//div[@id=':2d']")
	private WebElement primaryTab;
	
	@FindBy(css = "#:2m")
	private WebElement socialTab;
	
	@FindBy(xpath = "//div[@id=':jt']//span[@class='Dj' and contains(text(),'of')]")
	private WebElement emailCountInfo;
	
	
	public LoggedInDefaultPage(WebDriver driver) {
		super(driver);
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
	
	public boolean isPrimaryTabSelected() {
		return this.primaryTab.isSelected();
	}
	
	public String getTotalNoOfEmail() {
		return this.emailCountInfo.getText().split("of")[1];
	}
	
	public String getSubjectOfNthEmailInSocialTab(int emailNo) {
		return driver.findElement(By.xpath("//table[@id=':la']//tbody//tr[" + emailNo + "]//td[6]//span[@id=':p7']")).getText();
	}
	
	public String getSenderOfNthEmailInSocialTab(int emailNo) {
		return driver.findElement(By.xpath("//table[@id=':la']//tbody//tr[" + emailNo + "]//td[5]//span[@class='bA4']")).getText();
	}
}
