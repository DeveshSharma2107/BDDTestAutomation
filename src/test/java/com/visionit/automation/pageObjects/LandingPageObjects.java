package com.visionit.automation.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;

public class LandingPageObjects {
	  private static final Logger logger = LogManager.getLogger(LandingPageObjects.class);
	  

	    //Section1:  Declare a driver object
	    private WebDriver driver;
	    Scenario scn;
	    
	    //parameterized constructor
		  public LandingPageObjects( WebDriver driver,Scenario scn) {
			  this.driver=driver;
			  this.scn=scn;
		  }
	    
	    //define the locators
	    private By elementSearchBoxElement = By.id("twotabsearchtextbox");
	    private By searchButtonElement = By.xpath("//input[@value='Go']");
	    
	    
//	    WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
//		WebElement elementSearchBox = webDriverWait
//				.until(ExpectedConditions.elementToBeClickable(By.id("twotabsearchtextbox")));
//		elementSearchBox.sendKeys(productName);
//		driver.findElement(By.xpath("//input[@value='Go']")).click();
//		logger.info("clicked on search button");
	    
//		String expected = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
//		String actual = driver.getTitle();
//		Assert.assertEquals("Page Title validation", expected, actual);
//		scn.log("Base url -> " + base_url + "title validation is successful");
//		logger.info("Base url => " + base_url + "title validation is successful");

	    //Associated methods
	    public void validateLandingPageTitle(String base_url) {
	    	String expected = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
			String actual = driver.getTitle();
			Assert.assertEquals("Page Title validation", expected, actual);
			scn.log("Base url -> " + base_url + "title validation is successful");
			logger.info("Base url => " + base_url + "title validation is successful");
	    }
	    
	    public void searchProduct(String prodName) {
	    	  WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
	    	  WebElement elementSearchBox = webDriverWait.until(ExpectedConditions.elementToBeClickable( elementSearchBoxElement));
	    	  elementSearchBox.sendKeys(prodName);
	    	  logger.info("Send the keys to search box as -> "+prodName);
	    		driver.findElement(searchButtonElement).click();
	    		logger.info("clicked on search button");
	    }
}
