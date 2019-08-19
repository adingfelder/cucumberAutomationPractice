package com.planit.training.automationPractice;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ContactPage_Stepdef {

	ContactPage contact;

	public ContactPage_Stepdef() {
		contact = new ContactPage();
	}
	
/*	@When("^I click the Contact button$")
	public void i_click_contact_button() {
		contact.navigateToPage();
	}
*/
	
	@Given("^I navigate to the contact page$")
	public void i_navigate_to_the_contact_page(){
		contact.navigateToContactPage();
	}

	@Given("^I fill out the form$")
	public void i_fill_out_the_form(){
		contact.fillOutForm();
	}

	@When("^I click the send button on the contact page$")
	public void i_click_the_send_button(){
		contact.submitMessage();
	}

	@Then("^the message is sent$")
	public void the_message_is_sent(){
		contact.verifyMessageSent();
	}
}
