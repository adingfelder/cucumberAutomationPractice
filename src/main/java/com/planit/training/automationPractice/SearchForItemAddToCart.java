package com.planit.training.automationPractice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.planit.training.util.GeneralUtilities;

//import java.util.concurrent.TimeUnit;

public class SearchForItemAddToCart extends AutomationPracticeBaseClass {

	private static final Logger logger = LogManager.getLogger(SearchForItemAddToCart.class.getName());

	@Test(alwaysRun = true)
	public static void main() {

		// website we want to open
		// String baseUrl =
		// "http://automationpractice.com/index.php?controller=my-account";

		// getDriver().get(baseUrl + "/index.php");
		getDriver().findElement(By.id("search_query_top")).click();
		getDriver().findElement(By.id("search_query_top")).clear();
		getDriver().findElement(By.id("search_query_top")).sendKeys("blouse");
		getDriver().findElement(By.name("submit_search")).click();

		sleep(2);
	
		GeneralUtilities.scrollOnScreen(getDriver(), 0,250);
		/*logger.debug("scrolling");
		JavascriptExecutor jse = (JavascriptExecutor) getDriver();
		jse.executeScript("window.scrollBy(0,250)", "");
		logger.debug("done  scrolling");

		try {
			GeneralUtilities.getScreenShot(getDriver());
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		sleep(2);
		logger.debug("looking for blouse");
		By by = By.xpath("//img[@title='Blouse']");
		Actions action = new Actions(getDriver());
		WebElement elem = getDriver().findElement(by);
		sleep(2);
		logger.debug("moving");
		action.moveToElement(elem);
		logger.debug("hovering");
		action.perform();
		// sleep(2);

		logger.debug("done");
		getDriver().findElement(By.xpath("//a[contains(@class,'ajax_add_to_cart_button')]")).click();
	}

	
}
