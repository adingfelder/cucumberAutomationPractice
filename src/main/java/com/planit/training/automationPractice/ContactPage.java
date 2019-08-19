package com.planit.training.automationPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ContactPage extends AutomationPracticeBaseClass { 

	By email = By.id("email");
	By idOrder = By.id("id_order");
	By messageField = By.id("message");
	By submitMessage = By.id("submitMessage");

	private static final Logger logger = LogManager.getLogger(ContactPage.class.getName());

	public ContactPage() {

	}

	public void typeEmail(String em) {
		logger.debug("typing email");
		getDriver().findElement(email).clear();
		getDriver().findElement(email).sendKeys(em);
	}

	public void selectSubject(String subject) {
		// getDriver().findElement(By.cssSelector("#id_contact")).click();
		logger.debug("selecting subject");
		new Select(getDriver().findElement(By.cssSelector("#id_contact"))).selectByVisibleText(subject);
	}

	public void typeOrder(String orderNum) {
		logger.debug("adding order number");
		getDriver().findElement(idOrder).sendKeys(orderNum);
	}

	public void typeMessage(String message) {
		logger.debug("typing message");
		getDriver().findElement(messageField).sendKeys(message);
	}

	public void submitMessage() {
		logger.debug("submitting form");
		getDriver().findElement(submitMessage).click();
	}
/*
	public void verifyContactPage() {
		logger.debug("verifying contact page");
		verifyPageFromUrl("contact");
	}
*/

	public void navigateToContactPage() {
		logger.debug("navigating to contact page");
		waitForElementByCss(getDriver(), "#contact-link > a:nth-child(1)").click();
	}

	public void fillOutForm() {
		this.typeEmail("test@test.com");
		this.selectSubject("Customer service");
		this.typeOrder("12345");
		this.typeMessage("Some message");
	}

	public void verifyMessageSent() {
		waitForElementByCss(getDriver(), ".alert");
		if (getDriver().findElement(By.cssSelector(".alert")).getText()
				.equals("Your message has been successfully sent to our team.")) {
			logger.debug("Message sent successfully");
		} else {
			logger.debug("Message not sent successfully");
		}
	}
}
