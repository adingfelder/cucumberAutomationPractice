package com.planit.training.automationPractice;


import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.planit.training.util.GeneralUtilities;
import com.planit.training.util.ReadExcel;

import java.io.File;
import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class VerifySuccessfulLogin  extends AutomationPracticeBaseClass {
	
	public VerifySuccessfulLogin() {
		
	}
	
/*	@BeforeTest
	public void launchBrowser()
	{
		System.out.println("Running Tests...");
		System.out.println("Initialisaing firefox browser");

		
		
		// set the driver
		System.out.println("Creating firefox driver");
		driver = GeneralUtilities.setGeckoDriver();  //new FirefoxDriver();
		
		//Setup browser
		try {
			// wrap in a try/catch as firefox is now throwing an exception when it calls maximize
			getDriver().manage().window().maximize();
		} catch (Exception e) {
			if (!e.getMessage().contains("Failed to find width field")){
				// ignore the exception, we don't want to see this exception
				e.printStackTrace();
			}
		}
	}
	*/
	@Test(priority=1)
	public void doSomething(){
		Assert.assertEquals("expected","expected");
	}

	
	@Test(enabled = false)
	public void doSomethingAndFail(){
		Assert.assertEquals("expectedddd","expected");
	}
	
	@Test(priority=1)
	public void verifyValidLogin(){
		System.out.println("starting verifyValidLogin");
		String baseUrl = "http://automationpractice.com/index.php?controller=my-account";
		getDriver().get(baseUrl);

		File testResourcesDirectory = new File("src/test/resources");
		
		// driver used to open the firefox browser
		String testDataPath = testResourcesDirectory+"\\data\\Test Data.xlsx";

		
		//Get External Data
		ReadExcel excelFile = new ReadExcel(testDataPath,"Sheet1");  
		
		String email =excelFile.getStringData(1,0);
		String password = excelFile.getStringData(1,1);
		String memName = excelFile.getStringData(1,2);    // can get numbers via getNumericData
		
		//Instantiate Pages
		LoginPage login = new LoginPage();
		HomePage home = new HomePage();
		
		//Enter Parameters
		login.loginToStore();
		
		//Sync
		getDriver().manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
		//Validate Membership Name
		home.validateMemberName(memName);
		System.out.println("completed verifyValidLogin");
		
	}
/*	
	@AfterTest
	public void terminateBrowser()
	{
		
		if (driver != null) {
			//getDriver().close();
			getDriver().quit();
		}
		System.out.println("Test Completed");
		//System.exit(0);
		 
		 
	}
	*/
}