package com.atmecs.website.helper;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.atmecs.website.constants.FileConstants;
/**
 * This WaitForElement class contains the method for the wait operations like implicit wait, explicit wait, and fluent wait
 * @author ajith.periyasamy
 *
 */
public class WaitForElement {
	SeleniumHelper seleniumhelp=new SeleniumHelper();
	/**
	 *  This waitForElementToBeClickable method take the below parameters
	 * @param driver
	 * @param locator
	 * and put the wait in driver until the element to be clickable.
	 */
	public void waitForElementToBeClickable(WebDriver driver,String locator) 
	{
		WebDriverWait wait=new WebDriverWait(driver,FileConstants.EXPLICIT_WAIT);
		wait.until(ExpectedConditions.elementToBeClickable(seleniumhelp.matchElement(locator)));
	}
	/**
	 * waitForElementToBeSelected method take the below parameters
	 * @param driver
	 * @param locator
	 * and put the wait in driver until the element to be selected.
	 */
	public void waitForElementToBeSelected(WebDriver driver,String locator) 
	{
		WebDriverWait wait=new WebDriverWait(driver,FileConstants.EXPLICIT_WAIT);
		wait.until(ExpectedConditions.elementToBeSelected(seleniumhelp.matchElement(locator)));
	}
	/**
	 * This waitForFrameToBeAvailable method take the below parameters
	 * @param driver
	 * @param locator
	 * and put the wait in driver until the frame to be available and switch to it.
	 */
	public void waitForFrameToBeAvailable(WebDriver driver,String locator) 
	{
		WebDriverWait wait=new WebDriverWait(driver,FileConstants.EXPLICIT_WAIT);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(seleniumhelp.matchElement(locator)));
	}
	/**
	 * waitForInvisibilityOfElementLocated method take the below parameters
	 * @param driver
	 * @param locator
	 * and put the wait in driver until the visibility of element to be located.
	 */
	public void waitForInvisibilityOfElementLocated(WebDriver driver,String locator) 
	{
		WebDriverWait wait=new WebDriverWait(driver,FileConstants.EXPLICIT_WAIT);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(seleniumhelp.matchElement(locator)));
	}
	/**
	 * waitPresenceOfAllElementsLocated method take the below parameters
	 * @param driver
	 * @param locator
	 * and put the wait in driver until the presence of all element to be located.
	 */
	public void waitPresenceOfAllElementsLocated(WebDriver driver,String locator) 
	{
		WebDriverWait wait=new WebDriverWait(driver,FileConstants.EXPLICIT_WAIT);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(seleniumhelp.matchElement(locator)));
	}
	/**
	 * waitForImplicit method take the below parameter
	 * @param driver
	 * and put the wait in driver when ever driver find the no such Element Exception. 
	 */
	public void waitForImplicit(WebDriver driver) 
	{
		try 
		{
			driver.manage().timeouts().implicitlyWait(FileConstants.FLUENT_WAIT, TimeUnit.SECONDS);
		}catch(Exception e) 
		{
			System.out.println("Element is not available or not clickable");	
		}
	}
	/**
	 * waitForPageLoadTime method take the below parameter
	 * @param driver
	 * and put the wait in driver until the page loading. 
	 */
	public void waitForPageLoadTime(WebDriver driver) 
	{
		try {
			driver.manage().timeouts().implicitlyWait(FileConstants.PAGE_LOAD_TIME, TimeUnit.SECONDS);
		}catch(Exception e) 
		{
			System.out.println("Element is not available or not clickable");
		}
	}
	/**
	 * waitForSetScripttimeout method take the below parameter
	 * @param driver
	 * and put the wait in driver before throw an error to finish the script execution
	 */
	public void waitForSetScripttimeout(WebDriver driver) 
	{
		try {
			driver.manage().timeouts().setScriptTimeout(FileConstants.PAGE_LOAD_TIME, TimeUnit.SECONDS);
		}
		catch(Exception e) 
		{
			System.out.println("Element is not available or not clickable");
		}
	}
	/**
	 * WaitForFluent method take the below parameter
	 * @param driver
	 * @param locator
	 * and put the wait for before throwing the exception and poll the element after the specific polling time interval. 
	 */
	public WebElement WaitForFluent(WebDriver webdriver,String locator) {
		WebElement element = null;
		try {
			@SuppressWarnings("deprecation")
			Wait<WebDriver> wait = new FluentWait<WebDriver>(webdriver)
			.withTimeout(FileConstants.FLUENT_WAIT, TimeUnit.SECONDS)
			.pollingEvery(FileConstants.FLUENT_POLL, TimeUnit.SECONDS)
			.ignoring(ElementClickInterceptedException.class)
			.ignoring(NoSuchElementException.class);
			element=wait.until(new Function<WebDriver,WebElement>() 
			{
				public WebElement apply(WebDriver driver) {
					return driver.findElement(seleniumhelp.matchElement(locator));
				}
			});
		}catch(Exception e) 
		{
			System.out.println("Element is not available or not clickable");
		}
		return element;
	}
}
