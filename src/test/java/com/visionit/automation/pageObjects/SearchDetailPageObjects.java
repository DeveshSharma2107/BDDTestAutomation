package com.visionit.automation.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;



public class SearchDetailPageObjects {
	  private static final Logger logger = LogManager.getLogger(SearchDetailPageObjects.class);

	    //Section1:  Declare a driver object
	    private WebDriver driver;
	    Scenario scn;
	   	    
	    //parameterized constructor
		  public SearchDetailPageObjects( WebDriver driver,Scenario scn) {
			  this.driver=driver;
			  this.scn=scn;
		  }
		  
//		Section2: Define the locators
	    
//	    WebDriverWait webDriverWait1 = new WebDriverWait(driver, 20);
//		webDriverWait1.until(ExpectedConditions.titleIs("Amazon.in : " + prodNameInTitle));
//
//			Assert.assertEquals("Page Title validation", "Amazon.in : " + prodNameInTitle, driver.getTitle());
//			scn.log("page title validation for product in title -> " + prodNameInTitle + "is successful");
//			logger.info("Page title validation for Product name in title -> "+prodNameInTitle+" is successful");
		  
		  //Associated methods
		  
		  public void validationProdNameTitle(String prodNameInTitle) {
				WebDriverWait webDriverWait1 = new WebDriverWait(driver, 20);
				webDriverWait1.until(ExpectedConditions.titleIs("Amazon.in : " + prodNameInTitle));

				Assert.assertEquals("Page Title validation", "Amazon.in : " + prodNameInTitle, driver.getTitle());
				scn.log("page title validation for product in title -> " + prodNameInTitle + "is successful");
				logger.info("Page title validation for Product name in title -> " + prodNameInTitle + " is successful");
		  }

}
