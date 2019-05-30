package com.inetbanking.testClass;

import org.testng.annotations.Test;
import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import com.inetbanking.pageObject.HomePage;
import com.inetbanking.pageObject.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TestLoginPage_DataDriven extends BaseClass
{
	LoginPage loginPage;
	HomePage homePage;
	
	@Test(dataProvider = "loginData")
	public void loginTestDataDriven(String userName, String pwd) throws IOException, InterruptedException
	{
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);

		loginPage.setUserID(userName);
		logger.info("user name is provided");
		
		loginPage.setPassword(pwd);
		logger.info("password is provided");
		
		loginPage.clickOnLoginBTN();
		logger.info("succefully clicked on login button");
		
		Thread.sleep(2000);//wait for 2 second
		
		if(isAlertPresent()==true)//invalid login data 
		{
			logger.warn("login failed.");
			driver.switchTo().alert().accept();//accept the alert message
			
			captureScreen(driver, "loginTestDataDriven");
			
			driver.switchTo().defaultContent();//switching back to loginpage
			Assert.assertTrue(false, "login to home page got failed.");
		}
		else  	//valid login data
		{
			
			logger.info("login passed!");
		
			homePage.clickOnLogOutLink();
			logger.info("successfully clicked on logout button.");
			Thread.sleep(2000);
			
			Alert alert  = driver.switchTo().alert();
			System.out.println("Alert Msg after clicking on logout is : "+alert.getText());
			alert.accept();
			
			driver.switchTo().defaultContent();	
			
			Assert.assertTrue(true);
		}
	}
	
	public boolean isAlertPresent()
	{
		try
		{
			Alert alert= driver.switchTo().alert();
			System.out.println("Alert msg for logout is : "+alert.getText());
			return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
	}
	
	@DataProvider(name = "loginData")
	String[][] getData() throws IOException
	{
		String path = System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/loginData.xlsx";
		
		int rowNum = XLUtils.getRowCount(path, "sheet1");
		int colNum = XLUtils.getCellCount(path, "sheet1", 1);
		
		String loginData[][] = new String[rowNum][colNum];
		for(int i=1; i<=rowNum; i++)
		{
			for(int j=0; j<colNum; j++)
			{
				loginData[i-1][j] = XLUtils.getCellData(path, "sheet1", i, j);
			}	
		}
		return loginData;
	}
	
	
}
