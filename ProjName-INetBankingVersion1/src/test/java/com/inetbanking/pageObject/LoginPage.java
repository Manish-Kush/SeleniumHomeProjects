package com.inetbanking.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage
{
	WebDriver ldriver;	//local driver
	
	public LoginPage(WebDriver rdriver)			//remote driver
	{
		this.ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(xpath = "//input[@name='uid']")
	WebElement userId;
	
	@FindBy(name = "password")
	WebElement password;
	
	@FindBy(name = "btnLogin")
	WebElement loginBTN;
	
	@FindBy(name = "btnReset")
	WebElement resetBTN;
	
	//Action methods
	public void setUserID(String userID)
	{
		userId.sendKeys(userID);
	}
	
	public void setPassword(String userPassword)
	{
		password.sendKeys(userPassword);
	}

	public void clickOnLoginBTN()
	{
		loginBTN.click();
	}
	
}
