package com.atmecs.website.testbase;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.atmecs.website.constants.FileConstants;
import com.atmecs.website.extentreport.Extent;
import com.atmecs.website.logreports.LogReporter;
import com.atmecs.website.utils.PropertiesReader;
/**
 * @author ajith.periyasamy
 * This Class is extend from the Extent class and class contains the browser selection method.
 * baseSetup method is used to select the script run in the Selenium grid or user system.
 */
public class TestBase extends Extent{
	protected Properties prop=null;
	PropertiesReader propertyReader = new PropertiesReader();
	LogReporter log=new LogReporter();
	/**
	 * This baseSetup method will provide the option to choose the browser driver to run the script.
	 *  The configuration details is read from the config.properties file.
	 *  and this browser details passed to the switch case and based on the configuration details script is running in different browsers. 
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("deprecation")
	@BeforeTest
	@Parameters("browser")
	public void baseSetup(String browser) throws IOException, InstantiationException, IllegalAccessException {
		prop = propertyReader.keyValueLoader(FileConstants.CONFIG_PATH);
		String[] chooser=browser.split(",");
		if(chooser[0].equals("GRID")) {
			String Node = "http://55.55.53.104:4444/wd/hub";
			switch (chooser[1]) {
			case "CHROME":
				System.out.println("Executing on chrome");
				DesiredCapabilities chromecapabilities =new DesiredCapabilities();
				chromecapabilities.setBrowserName("chrome");
				driver = new RemoteWebDriver(new URL(Node), chromecapabilities);
				break;
			case "FIREFOX":
				System.out.println("Executing on FireFox");
				DesiredCapabilities firefoxcapabilities = new DesiredCapabilities();
				firefoxcapabilities.setBrowserName("firefox");
				driver = new RemoteWebDriver(new URL(Node), firefoxcapabilities);
				break;
			case "IE":
				System.out.println("Executing on IE");
				DesiredCapabilities iecapabilities = new DesiredCapabilities();
				iecapabilities.setBrowserName("ie");
				driver = new RemoteWebDriver(new URL(Node), iecapabilities);
				break;
			}
			//driver.get(prop.getProperty("url"));
			//log.logReportMessage("url is loaded");
		}
		else {
			switch (chooser[1]) {
			case "CHROME":
				System.setProperty("webdriver.chrome.driver", FileConstants.CHROME_DRIVER_PATH);
				ChromeOptions chromeoptions = new ChromeOptions();
				chromeoptions.addArguments("--disable-notifications");
				chromeoptions.addArguments("disable-geolocation");
				driver = new ChromeDriver(chromeoptions);
				break;
			case "FIREFOX":
				System.setProperty("webdriver.gecko.driver", FileConstants.FIREFOX_DRIVER_PATH);
				FirefoxOptions firefoxoptions = new FirefoxOptions();
				firefoxoptions.addPreference("dom.webnotifications.enabled", false);
				driver = new FirefoxDriver(firefoxoptions);
				break;
			case "IE":
				System.setProperty("webdriver.ie.driver", FileConstants.IE_DRIVER_PATH);
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, " ");
				capabilities.setCapability("requireWindowFocus", true);
				capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				driver=new InternetExplorerDriver(capabilities);
				break;
			case "EDGE":
				System.setProperty("webdriver.edge.driver", FileConstants.EDGE_DRIVER_PATH);
				driver = new EdgeDriver();
				break;
			}
			//driver.get(prop.getProperty("url"));
			//log.logReportMessage("url is loaded");
		}
	}
}
