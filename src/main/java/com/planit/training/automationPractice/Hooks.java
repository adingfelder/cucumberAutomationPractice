package com.planit.training.automationPractice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cucumber.api.java.After;


public class Hooks extends AutomationPracticeBaseClass {
	
	private static final Logger logger  = LogManager.getLogger(Hooks.class.getName());

	public Hooks() {
		logger.debug("constructed hooks");
	}


	// moved to CommonBaseClass
/*	@Before
	public void initDriver(){
		logger.debug("initialising automation pracice driver in hook");
		driver = GeneralUtilities.setChromeDriver();

		getDriver().manage().window().maximize();
		
		logger.debug("loading website "+ baseurl);
		getDriver().navigate().to(baseurl);
	}
*/
	@After
	public static void closeDriver(){
		logger.debug("quitting driver in hook");
		AutomationPracticeBaseClass.closeDriver();
		// do we need to do anything else?
//		getDriver().quit();
	}

}
