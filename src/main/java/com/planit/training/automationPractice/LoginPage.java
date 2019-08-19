package com.planit.training.automationPractice; 

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author pjmendoza
 *
 */
public class LoginPage   extends AutomationPracticeBaseClass {
	
	By email=By.id("email");
	By password=By.id("passwd");
	By loginButton=By.id("SubmitLogin");
	By SignInButton=By.linkText("Sign in");
	By AccountButton=By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span");
	
	public LoginPage() {
	}

	public void loginToStore() {
		clickSignInButton();
	}
	

	public void typeEmail(String em) {
		getDriver().findElement(email).sendKeys(em);
	}
	
	public void typePassword(String pw) {
		getDriver().findElement(password).sendKeys(pw);	
	}
	

	public void clickSignInButton() {
		getDriver().findElement(SignInButton).click();
	}
	
	public void clickLoginButton() {
		getDriver().findElement(loginButton).click();
	}

	public String VerifyUserButton() {
		return getDriver().findElement(AccountButton).getText();
	}

	
}
