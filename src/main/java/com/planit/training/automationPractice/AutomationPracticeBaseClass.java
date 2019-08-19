//cut in wholesale from globalSQA, since we might need it. 
//Some methods probably won't work, though - Liam
package com.planit.training.automationPractice;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.planit.training.util.GeneralUtilities;

public class AutomationPracticeBaseClass  {

	
	private static final Logger logger  = LogManager.getLogger(AutomationPracticeBaseClass.class.getName());
	private static WebDriver driver = null;
	public final static int explicitWaitDefault = 20;
	public static final int timeToWaitForClickable = 10;
	public static final int timeToWaittoFind = 10;

	private static String defaultFrame = null;
	private static String baseURL = null;
	

//	public static final String demoFrameName = "demo-frame";

	private static String START_PAGE_TITLE = "My Store";
	
	String website = "http://automationpractice.com"; 

	public AutomationPracticeBaseClass() {
//		super();
		
		logger.debug("initialising CommonBaseClass for website {}", website );
		try {
			initDriver();
			logger.debug("initialised driver" );
			loadWebsiteFromURL(website);
			logger.debug("loaded website {}", website );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("done constructing AutomationPracticeBaseClass");
	}
	
	public static void verifyPage(String page) {
		logger.debug("verifying {} page",page);
		verifyPageFromUrl(page);
	}

	public static final void verifyPageFromUrl(String pageId) {
		logger.debug("verifying page ({}) from url",pageId);
		if (getDriver() == null) {
			logger.debug("driver is null");
			return;
		}
		if (getDriver().getCurrentUrl().toLowerCase().indexOf(pageId.toLowerCase()) != -1) {
			logger.debug("You are on the {} page", pageId);
		} else {
			logger.debug("You are NOT on the ({}) page, instead on {}", pageId, 
					getDriver().getCurrentUrl());
		}
	}

	public void setBaseURL(String url) {
		baseURL = url;
	}
	public String getBaseURL() {
		return baseURL;
	}

	public void setDefaultFrameName(String frameName) {
		defaultFrame = frameName;
	}
	

/*	public final static void setUpChrome() {
		logger.debug("using chrome");
		String Chromedriverpath = "src\\main\\resources\\bin\\chromegetDriver().exe";
		System.setProperty("webgetDriver().chrome.driver", Chromedriverpath);
	}
	*/
	public String getPageTitle() {
		return getDriver().getTitle();
	}

	public static WebDriver getDriver() {
		if (driver == null) {
			initDriver();
		}
		return driver;
	}

	
	public static void initDriver(){
		if (driver == null) {
			logger.debug("initialising driver");
			driver = GeneralUtilities.setChromeDriver();
			//getDriver().manage().window().maximize();
			logger.debug("done initialising driver");
		} else {
			logger.debug("not initialising driver, already set");
		}
	}
	
	public static void clickButtonByLinkText(String buttonName) {
		logger.debug("clicking {} button by LinkText", buttonName);
		try {
			WebElement button = getDriver().findElement(By.partialLinkText(buttonName));
			button.click();
		} catch (NoSuchElementException e) {
			logger.error("Failed to find and click button {}.  "
					+ "On page {}", buttonName, getDriver().getTitle());
			throw e;
		}
	}

	
	public void loadWebsiteFromURL(String url){
		
		if (url != null) { 
			logger.debug("loading website "+ url);
			getDriver().navigate().to(url);
		} else {
			logger.debug("URL is not set, not loading page");
		}
	}

	public static final void verifyTabFromUrl(String tabId) {
		logger.debug("verifying tab");
		if (getDriver().getCurrentUrl().indexOf(tabId) != -1) {
			logger.debug("You are on the correct tab");
		} else {
			logger.debug("You are NOT on the correct tab");
		}
	}

	public final static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			//e.printStackTrace();
		}
	}
	

	public void waitUntilElementContainsText(WebDriver driver, 
											By elementBy, 
											String desiredText, 
											int timeToWaitInSeconds) {
		
		logger.debug("now verifying text ({}) is shown",desiredText);
		getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		for (int i = 0; i < timeToWaitInSeconds*4; i++) {
			sleep(250);
			WebElement we;
			try {
				//page_closeButton = waitForElementByLinkText(driver, "Close", 1);
				we = getDriver().findElement(elementBy);
				//String text =  page_closeButton.getText();
				logger.debug("found element, checking if text = {}", desiredText);
				if (we.getText().trim().equalsIgnoreCase(desiredText)) {
					logger.debug("found element with text = {}", desiredText);
					break;
				}else {
					logger.debug("found element with different text = {}, still looking", we.getText());
				}
			} catch (NoSuchElementException e) {
				logger.debug("haven't fount element yet - " + i);
			}
		}
		
	}	

	
	public static Wait<WebDriver> getExplictWaitDriver() {

		return getExplictWaitDriver(explicitWaitDefault);
	}

	public static Wait<WebDriver> getExplictWaitDriver( int timeToWait) {

		return new WebDriverWait(driver, timeToWait);
	}
	/**
	 * switchFrameAndWaitByClassname is used to switch to the demo-frame and
	 * then wait for an element by id takes parameters of the driver currently
	 * in use and the id of the element to wait for
	 * 
	 * @param driver
	 * @param id
	 */
	public static void switchFrameByCSS(WebDriver driver, String cssSelector) {
		logger.debug("switching to frame by CSS" , cssSelector);

		getDriver().switchTo().frame(getDriver().findElement(By.cssSelector(cssSelector)));
	}
	
	public static WebElement switchFrameAndWaitByClassname(WebDriver driver, String classname)
			throws NoSuchElementException {
		// switch to demo frame in the driver
		switchFrame(driver);
		// wait for the element in the method call to be clickable
		// or until 10 seconds have passed
		return waitForElementByClassName(driver, classname);
	}

	
	public static WebElement switchFrameAndWaitForElementByxPath(WebDriver driver, String string)
			throws NoSuchElementException {
		// switch to demo frame in the driver
		switchFrame(driver);
		// wait for the element in the method call to be clickable
		// or until 10 seconds have passed
		return waitForElementByxPath(driver, string);
	}

	public static List<WebElement> switchFrameAndWaitForElementListByxPath(WebDriver driver, String string)
			throws NoSuchElementException {
		// switch to demo frame in the driver
		switchFrame(driver);
		// wait for the element in the method call to be clickable
		// or until 10 seconds have passed
		return waitForElementListByxPath(driver, string);
	}

	public static List<WebElement> switchFrameAndWaitForElementListById(WebDriver driver, String string)
			throws NoSuchElementException {
		// switch to demo frame in the driver
		switchFrame(driver);
		// wait for the element in the method call to be clickable
		// or until 10 seconds have passed
		return waitForElementListById(driver, string);
	}

	public static List<WebElement> switchFrameAndWaitForElementListByClassName(WebDriver driver, String string)
			throws NoSuchElementException {
		// switch to demo frame in the driver
		switchFrame(driver);
		// wait for the element in the method call to be clickable
		// or until 10 seconds have passed
		return waitForElementListByClassName(driver, string);
	}

	public static List<WebElement> switchFrameAndWaitForElementListByLinkText(WebDriver driver, String string)
			throws NoSuchElementException {
		// switch to demo frame in the driver
		switchFrame(driver);
		// wait for the element in the method call to be clickable
		// or until 10 seconds have passed
		return waitForElementListByLinkText(driver, string);
	}

	/**
	 * switchFrameAndWaitById is used to switch to the demo-frame and then wait
	 * for an element by id takes parameters of the driver currently in use and
	 * the id of the element to wait for
	 * 
	 * @param driver
	 * @param id
	 */
	public static WebElement switchFrameAndWaitById(WebDriver driver, String id) throws NoSuchElementException {
		// switch to demo frame in the driver
		switchFrame(driver);
		// wait for the element in the method call to be clickable
		// or until 10 seconds have passed
		return waitForElementById(driver, id);
	}

	/**
	 * switchFrameAndWaitByLinkText is used to switch to the demo-frame and then
	 * wait for an element by id takes parameters of the driver currently in use
	 * and the LinkText of the element to wait for
	 * 
	 * @param driver
	 * @param id
	 */
	public static WebElement switchFrameAndWaitByLinkText(WebDriver driver, String id) throws NoSuchElementException {
		// switch to demo frame in the driver
		switchFrame(driver);
		// wait for the element in the method call to be clickable
		// or until 10 seconds have passed
		return waitForElementByLinkText(driver, id);
	}


	public static void switchFrameByNumber(WebDriver driver, String frameName, String sframeNumber) {
		
		int frameNum = Integer.parseInt(sframeNumber);
		
		switchFrame(driver, frameName, frameNum);
	}

	
	/**
	 * SwitchFrame is used to switch to the demo-frame where elements are found
	 * takes parameters of the driver currently in use
	 * 
	 * @param driver
	 */
	public static void switchFrame(WebDriver driver) throws NoSuchElementException {
		// if they dont pass in a frame name
		// assume they will use the default frame
		// if null then skip this step as we dont use a default frame
		
		switchFrame(driver,defaultFrame);
	}

	

	private static List<WebElement> waitForElements(WebDriver driver, By elementBy) throws NoSuchElementException {

		logger.debug("looking for element {} (and waiting)", elementBy.toString());
		getDriver().manage().timeouts().implicitlyWait(timeToWaittoFind, TimeUnit.SECONDS);

		long timestarted = System.currentTimeMillis();
		// wait for the element in the method call to be clickable or until 10
		// seconds
		// have passed
		List<WebElement> elementList = null;

		try {
			elementList = getDriver().findElements(elementBy);
		} catch (NoSuchElementException e1) {
			logger.debug("error finding {} in {}ms", elementBy.toString(), (System.currentTimeMillis() - timestarted));
			throw e1;
		}

		WebDriverWait wait = new WebDriverWait(driver, timeToWaitForClickable);
		try {
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(elementBy));
			// .elementIfVisible(element));
			logger.debug("waited and found element {} after {}ms", elementBy.toString(),
					(System.currentTimeMillis() - timestarted));
		} catch (NoSuchElementException e) {
			logger.error("waited but didnt find element {} in {}ms", elementBy.toString(),
					(System.currentTimeMillis() - timestarted));
			throw e;
		} catch (Exception e) {
			logger.error("someother error (time elapsed=" + (System.currentTimeMillis() - timestarted) + "ms", e);
		}
		if (elementList == null) {
			// probably should have had exception so this is unreachable code
			logger.warn("waited but didnt find any elements {} in {}ms", elementBy.toString(),
					(System.currentTimeMillis() - timestarted));
		}
		return elementList;
	}

	/**
	 * TODO: add logic to stop waiting for it to be clickable
	 * 
	 * 
	 * @param driver
	 * @param elementBy
	 * @return
	 * @throws NoSuchElementException
	 */
	private static WebElement waitForElementToBeClickable(WebDriver driver, By elementBy)
			throws NoSuchElementException {

		getDriver().manage().timeouts().implicitlyWait(timeToWaittoFind, TimeUnit.SECONDS);

		logger.debug("looking for element {} (and waiting {} seconds)", elementBy.toString(), timeToWaittoFind);

		long timestarted = System.currentTimeMillis();
		// wait for the element in the method call to be clickable or until 10
		// seconds
		// have passed
		WebElement element = null;
		try {
			element = getDriver().findElement(elementBy);
		} catch (NoSuchElementException e1) {
			logger.error("error finding {} in {}ms", elementBy.toString(), (System.currentTimeMillis() - timestarted));
			throw e1;
		}catch(Exception e) {
			logger.error("error finding {} in {}ms", elementBy.toString(), (System.currentTimeMillis() - timestarted));
			throw new NoSuchElementException("error finding"+ elementBy.toString(),e);
		}
		
		if (element == null) {
			logger.error("error finding {} in {}ms", elementBy.toString(), (System.currentTimeMillis() - timestarted));
			throw new NoSuchElementException("error finding"+ elementBy.toString());
		} else {
			logger.debug("found element {}, now waiting for it to be clickable", elementBy.toString() );
		}
		// we found it.
		// now wait for it to be clickable
		WebDriverWait wait = new WebDriverWait(driver, timeToWaitForClickable);
		try {
			// wait.until(ExpectedConditions.presenceOfElementLocated(elementBy));
			wait.until(ExpectedConditions.elementToBeClickable(element));

			logger.debug("waited and found element {} after {}ms", elementBy.toString(),
					(System.currentTimeMillis() - timestarted));
		} catch (NoSuchElementException e) {
			logger.error("waited but didnt find element {} in {}ms", elementBy.toString(),
					(System.currentTimeMillis() - timestarted));
			throw e;
		} catch (Exception e) {
			logger.error("some other error (time elapsed=" + (System.currentTimeMillis() - timestarted) + "ms", e);
		}
		return element;
	}

	public static WebElement waitForElementById(WebDriver driver, String id) throws NoSuchElementException {
		return waitForElementToBeClickable(driver, By.id(id));
	}

	public static WebElement waitForElementByClassName(WebDriver driver, String className)
			throws NoSuchElementException {
		return waitForElementToBeClickable(driver, By.className(className));
	}

	public static WebElement waitForElementByLinkText(WebDriver driver, String linkText) throws NoSuchElementException {

		return waitForElementToBeClickable(driver, By.partialLinkText(linkText));
		// return waitForElement(driver, By.linkText(linkText));
	}

	public static WebElement waitForElementByxPath(WebDriver driver, String xPath) throws NoSuchElementException {
		return waitForElementToBeClickable(driver, By.xpath(xPath));
	}

	public static List<WebElement> waitForElementListByxPath(WebDriver driver, String xPath)
			throws NoSuchElementException {
		return waitForElements(driver, By.xpath(xPath));
	}

	public static List<WebElement> waitForElementListById(WebDriver driver, String id) throws NoSuchElementException {
		return waitForElements(driver, By.id(id));
	}

	public static List<WebElement> waitForElementListByClassName(WebDriver driver, String id)
			throws NoSuchElementException {
		return waitForElements(driver, By.className(id));
	}

	public static List<WebElement> waitForElementListByLinkText(WebDriver driver, String id)
			throws NoSuchElementException {
		return waitForElements(driver, By.linkText(id));
	}

	public static WebElement waitForElementByCss(WebDriver driver, String css) throws NoSuchElementException {
		return waitForElementToBeClickable(driver, By.cssSelector(css));
	}


	public static void switchFrame(WebDriver driver, String frameName) 
			throws NoSuchElementException {
		logger.debug("switching to frame {} - not choosing number" , frameName);
		// go to the parent first, in case we call switch when we are are already in the frame
	    getDriver().switchTo().defaultContent();
		
//	    logger.debug("switching to frame {}", frameName);
	    // switch to demo frame in the driver
		try {
			getDriver().switchTo().frame(getDriver().findElement(By.className(frameName)));
			logger.debug("switched to frame {} - no number" , frameName);
		} catch (NoSuchElementException e) {
			logger.error("error switching to frame "+frameName, e);
			throw e;
		}
//	    logger.debug("switched to frame {}", frameName);
	}

	public static void switchFrame(WebDriver driver, String frameName, int frameNumber) 
			throws NoSuchElementException {
		logger.debug("switching to frame {}[{}]" , frameName, frameNumber);
		// go to the parent first, in case we call switch when we are are already in the frame
	    getDriver().switchTo().defaultContent();
		
//	    logger.debug("switching to frame {}", frameName);
	    // switch to demo frame in the driver
		try {
			List <WebElement> frames = getDriver().findElements(By.className(frameName)); 
			getDriver().switchTo().frame(frames.get(frameNumber));
			logger.debug("switched to frame {}" , frameNumber);
			
		} catch (NoSuchElementException e) {
			logger.error("error switching to frame "+frameName, e);
			throw e;
		}
//	    logger.debug("switched to frame {}", frameName);
	}

	/**
	 * SwitchFrame is used to switch to the demo-frame where elements are found
	 * takes parameters of the driver currently in use
	 * 
	 * @param driver
	 */
	public static void switchFrameBackToParent(WebDriver driver) throws NoSuchElementException {
		
		logger.debug("switching frame to parent");
		getDriver().switchTo().defaultContent();
		// switch to demo frame in the driver
		// getDriver().switchTo().frame(getDriver().findElement(By.className("demo-frame")));
	}
/*
	public static void closeDriver(WebDriver driver) {
		if (driver != null) {
			try {
				getDriver().close();
				logger.debug("Driver closed");
			} catch (Exception e) {
				e.printStackTrace();
			}
			// getDriver().quit();
		}
	}
	*/
	public static void closeDriver() {
		logger.debug("Closing Driver");
		
		if (driver != null) {
			try {
				getDriver().close();
				logger.debug("Driver closed");
			} catch (Exception e) {
				e.printStackTrace();
			}
			// getDriver().quit();
			driver = null;
		}
	}

	
	public static void clickTab(String tabId) {
		switchFrameBackToParent(driver);
		logger.debug("Clicking tab : " + tabId);
//CommonBaseClass.		Re-Size Accordion
		WebElement tab = waitForElementById(getDriver(), tabId);
		//WebElement we = waitForElementByLinkText(CommonBaseClass.getDriver(), tabId);
		//getDriver().findElement(By.id(tabId)).click();
//		String xpathId = "//*[@id='"+tabId+"']";
//		logger.debug("looking for " + xpathId);
//		WebElement tab = getDriver().findElement(By.xpath(xpathId));
		tab.click();
		//we.click();
	}

}