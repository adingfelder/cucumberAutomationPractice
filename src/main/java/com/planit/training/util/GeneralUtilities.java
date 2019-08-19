package com.planit.training.util;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class GeneralUtilities
{
	private static final Logger logger  = LogManager.getLogger(GeneralUtilities.class.getName());

	public void selectItem(WebDriver driver, String elementToBeSelected)
	{
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText(elementToBeSelected)));
		driver.findElement(By.linkText(elementToBeSelected)).click();
	}
	
	public final static File resourcesDirectory = new File("src\\main\\resources\\bin");
	public final static String chromeDriverPath = resourcesDirectory + "\\chromedriver.exe";
	// driver used to open the firefox browser
	public final static String firefoxDriverPath = resourcesDirectory+"\\geckodriver.exe";

	public static void scrollOnScreen(WebDriver driver, int horisontal, int vertical) {
		logger.debug("scrolling");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		logger.debug("done  scrolling");

	}

	
	public static WebDriver setChromeDriver() {
		logger.debug("using " + chromeDriverPath);
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		WebDriver driver = new ChromeDriver();
		return driver;
	}
	
	public static WebDriver setGeckoDriver() {
		// tell selenium where to find the driver
		System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
		WebDriver driver = new FirefoxDriver();

		// maximise the browser window
		try {
			driver.manage().window().maximize();
		} catch (Exception e) {
			//e.printStackTrace();
			//logger.debug("you can ignore " + e.getMessage());
		}
		return driver;
	}

	public static void getScreenShot(WebDriver driver) throws Exception {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("C:\\dev\\screenshots\\screenshot.png"));
	}
	
}
