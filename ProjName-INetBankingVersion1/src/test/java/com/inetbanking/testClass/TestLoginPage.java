/*
 * For test cases we need to add log in every lines of the code
 * 
 */

package com.inetbanking.testClass;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObject.LoginPage;

public class TestLoginPage extends BaseClass
{
	LoginPage loginPage;
	
	@Test
	public void loginTest() throws IOException
	{
		loginPage = new LoginPage(driver);
		logger.info("Chrome browser Opened succesfully.");
				
		loginPage.setUserID(userID);
		logger.info("UserName entered succesfully");
		
		loginPage.setPassword(password);
		logger.info("Password entered successfully");
		
		loginPage.clickOnLoginBTN();
		logger.info("clicked on Login button");
		
		if(driver.getTitle().equalsIgnoreCase("Guru99 Bank Manager HomePage1234"))
		{
			System.out.println("title of the Home page is : "+driver.getTitle());
			Assert.assertTrue(true);
			logger.info("Login Test passed");
		}
		else
		{
			captureScreen(driver, "loginTest");
			Assert.assertTrue(false);
			logger.info("Login test failed");
		}
	}
	
}
