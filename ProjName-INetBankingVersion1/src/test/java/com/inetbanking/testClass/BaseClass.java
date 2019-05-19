/**
 * In this class include all the common methods and variable which will be used in all the other class
 * (e.g open the browser, implicit wait and so on.) setUp method, tearDown method etc
 * */
package com.inetbanking.testClass;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.ReadConfig;


public class BaseClass
{
	ReadConfig readConfig = new ReadConfig();
	
	public String baseURL= readConfig.getApplicationURL();
	public String userID= readConfig.getUserID();
	public String password = readConfig.getPassword();
	
	public static WebDriver driver;
	public static Logger logger; 
	
	@Parameters("browser")
	@BeforeClass
	public void setUp(String br)		//all the prerequsite include in this.( br = bowser)
	{
		//configuration we have to do for the base class
		logger = Logger.getLogger("eBanking"); //name of the project
		PropertyConfigurator.configure("Log4j.properties");
		
		if(br.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",readConfig.getChromePath());
			driver = new ChromeDriver(); //Instantiation(i.e create chrome driver object)
		}
		else if(br.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",readConfig.getFirefoxPath());
			driver = new FirefoxDriver(); //Instantiation(i.e create firefox driver object)
		}
		else if(br.equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver", readConfig.getIEPath());
			driver = new InternetExplorerDriver();
		}
		
		driver.get(baseURL);
		logger.info("URL is opened where page title is : "+driver.getTitle());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	//On failure of any test method we need to call this method
	public void captureScreen(WebDriver driver, String testCaseName) throws IOException
	{
		TakesScreenshot screenShot = (TakesScreenshot)driver;
		File sourceFile = screenShot.getScreenshotAs(OutputType.FILE);
		File destinationFile = new File(System.getProperty("user.dir")+"/ScreenShots/"+testCaseName+".png");
		FileUtils.copyFile(sourceFile, destinationFile);
		System.out.println("Screen Shot is taken.");
	}

	 
}
