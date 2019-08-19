package com.planit.training.automationPractice; 

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.xalan.xsltc.runtime.ErrorMessages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage  extends AutomationPracticeBaseClass {
	private static final Logger logger  = LogManager.getLogger(HomePage.class.getName());
	
	By memberName = By.xpath("//*[@id='header']/div[2]/div/div/nav/div[1]/a/span");
		
	public HomePage() {
		logger.info("Accessing Home Page Object");
	}
		
	public void validateMemberName(String expectedName) {
		String memName = getDriver().findElement(memberName).getText();
	 	Assert.assertEquals(expectedName, memName,"Member name does not match");	
	}
}
