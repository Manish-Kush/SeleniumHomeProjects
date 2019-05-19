package com.inetbanking.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage
{
	WebDriver ldriver;
	public HomePage(WebDriver rdriver)
	{
		ldriver = rdriver;
		PageFactory.initElements(ldriver, this);
	}
	
	@FindBy(linkText = "Log out")
	WebElement logOutLink;
	
	@FindBy(linkText = "New Customer")
	WebElement addNewCustomer;
	
	public void clickOnLogOutLink()
	{
		logOutLink.click();
	}
	
	public void clickOnAddNewCustomerLink()
	{
		
	}
}
