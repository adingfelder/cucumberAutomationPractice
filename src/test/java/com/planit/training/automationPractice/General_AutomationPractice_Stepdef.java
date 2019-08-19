package com.planit.training.automationPractice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class General_AutomationPractice_Stepdef {
	
	private static final Logger logger = LogManager.getLogger(General_AutomationPractice_Stepdef.class.getName());

	AutomationPracticeBaseClass bc = new AutomationPracticeBaseClass();
	
	@Given("^I am on the main page$")
	public void i_am_on_main_page(){
//		contact.verifyIndexPage();
		bc.verifyPageFromUrl("index");
	}
	
	@Then("^I am taken to the (.*) page$")
	public void i_am_taken_to_page(String page){
		// verify page is in url
		bc.verifyPage(page);
	}


	
/*	public void verifyIndexPage() {
		logger.debug("verifying main page");
		AutomationPracticeBaseClass.verifyPageFromUrl("index");
	}
*/
	
	@Then("^driver is closed$")
	public void closeDriver() {
		AutomationPracticeBaseClass.closeDriver();
	}
	
	@And("^I click on (.*) tab$")
	public void newtab(String tab) {
			logger.debug("verifing tab is clicked");
			AutomationPracticeBaseClass.clickTab(tab);
	}

	@Then("^(.*) is visible$")
	public void is_displayed(String tabId) {
		AutomationPracticeBaseClass.verifyTabFromUrl(tabId);
	}

//	@When("^I click DatePicker button$")
	@When("^I click (.*) button$")
	public void i_click_button(String buttonLinkText) throws Throwable {
		logger.info("When: I click {} button", buttonLinkText);
		
		AutomationPracticeBaseClass.clickButtonByLinkText(buttonLinkText);
		logger.debug("successfully clicked {} button",buttonLinkText);
	}


	/*
	public void clickTab(String tabId) {
		logger.debug("Clicking tab : " + tabId);
		CommonBaseClass.waitForElementById(CommonBaseClass.getDriver(), tabId);
		CommonBaseClass.getDriver().findElement(By.id(tabId)).click();
	}

*/


}
