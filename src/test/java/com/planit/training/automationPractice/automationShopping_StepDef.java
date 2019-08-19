package com.planit.training.automationPractice;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class automationShopping_StepDef {

	LoginPage l = new LoginPage();
	
	@Given("^I click on sign in page$")
	public void i_click_on_sign_in_page() throws Throwable {
	    	l.loginToStore();
	}

	@Given("^Login page is displayed$")
	public String login_page_is_displayed() throws Throwable {
	    return "false";
	}	
	
	@When("^I enter (.*)$")
	public void i_enter_cool_gmail_com(String em) throws Throwable {
	    l.typeEmail(em);
	}

	@When("^(.*) and sign in$")
	public void iamcool_and_sign_in(String pw) throws Throwable {
	    l.typePassword(pw);
	    l.clickLoginButton();
	}


	@Then("^Verify (.*) into account$")
	public void verify_name_of_account(String name) throws Throwable {
		String s = l.VerifyUserButton();
		Assert.assertEquals(name, s);  
		System.out.println("this is what is expected "+name);
		System.out.println("this is what is returned "+s);

	}

}
