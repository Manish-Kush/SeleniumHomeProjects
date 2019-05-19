package com.inetbanking.utilities;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
/**
 * This is an listener class used to generate the Extent Reports
 * 
 * */
public class Reporting extends TestListenerAdapter
{
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	public void onStart(ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//current time stamp
		String repName = "Test-Report-"+timeStamp+".html";
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test/output/"+repName);
		//htmlReporter.loadXMLConfig(System.getProperty("user.dir"+"/extent-config.xml"));
		htmlReporter.loadXMLConfig("F:\\testNG_Programs\\ProjName-INetBankingVersion1\\extent-config.xml");
		
		extent = new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Enviroment", "QA");
		extent.setSystemInfo("user", "manish");

		htmlReporter.config().setDocumentTitle("INet Banking Project");
		htmlReporter.config().setReportName("Functional Test Automation Report");
		//htmlReporter.config().setTestViewChartLocation(Chart);
		htmlReporter.config().setTheme(Theme.DARK);
		
	}

	public void onTestSuccess(ITestResult testResult)
	{
		logger = extent.createTest(testResult.getName());
		logger.log(Status.PASS, MarkupHelper.createLabel(testResult.getName(), ExtentColor.GREEN));
	}
	
	public void onTestFailure(ITestResult testResult)
	{
		logger = extent.createTest(testResult.getName());
		logger.log(Status.FAIL, MarkupHelper.createLabel(testResult.getName(), ExtentColor.RED));
		
		//String screenShotPath = System.getProperty("user.dir")+"/ScreenShots/"+testResult.getName()+".png";
		String screenShotPath = "F:\\testNG_Programs\\ProjName-INetBankingVersion1\\ScreenShots"+testResult.getName()+".png";
		
		File file = new File(screenShotPath);
		
		if(file.exists())
		{
			try 
			{
				logger.fail("Screenshot is below : "+logger.addScreenCaptureFromPath(screenShotPath));
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void onTestSkipped(ITestResult testResult)
	{
		logger = extent.createTest(testResult.getName());
		logger.log(Status.SKIP, MarkupHelper.createLabel(testResult.getName(), ExtentColor.ORANGE));
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
}
