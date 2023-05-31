package com.visionit.automation.stepdefs;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.visionit.automation.core.WebDriverFactory;
import com.visionit.automation.pageObjects.LandingPageObjects;
import com.visionit.automation.pageObjects.SearchDetailPageObjects;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StepDefs {
	private static final Logger logger = LogManager.getLogger(StepDefs.class);

	WebDriver driver;
	String base_url = "https://amazon.in";
	int implicit_wait_timeout_in_sec = 20;
	Scenario scn;
	
	LandingPageObjects landingPageObjects;
	SearchDetailPageObjects searchDetailPageObjects;
	

	@Before
	public void setup(Scenario scn)throws Exception {
		this.scn = scn;
		//driver = new ChromeDriver();
		//  driver = WebDriverManager.chromedriver().create();
		//  driver = WebDriverManager.firefoxdriver().create();
		String browserName = WebDriverFactory.getBrowserName();
		driver = WebDriverFactory.getWebDriverForBrowser(browserName);
		logger.info("Browser is invoked");
		driver.manage().window().maximize();
		scn.log("Browser is maximized");
		driver.manage().timeouts().implicitlyWait(implicit_wait_timeout_in_sec, TimeUnit.SECONDS);
		logger.info("implicitly Wait timeout set is -> " + implicit_wait_timeout_in_sec);
		landingPageObjects = new LandingPageObjects(driver,scn);
		searchDetailPageObjects = new SearchDetailPageObjects(driver,scn);
		
	}

	@After(order=1)
	public void tearDown() {
		WebDriverFactory.quitDriver();
	//	driver.quit();
		scn.log("browser is closed");
	//	logger.info("Browser is closed");
	}
	@After(order=2)
	public void takeScreenshot(Scenario s) {
	if(s.isFailed()) {
		TakesScreenshot scrnShot =(TakesScreenshot)driver;
		byte[] data = scrnShot.getScreenshotAs(OutputType.BYTES);
		scn.attach(data, "image/png", "failed step name: " + s.getName());
		}else {
			scn.log("test case is passed,no screenshot is captured");
		}
	}

//	@Given("User opened browser")
//	public void user_opened_browser() {
////		driver = new ChromeDriver();
////		driver.manage().window().maximize();
////		driver.manage().timeouts().implicitlyWait(implicit_wait_timeout_in_sec, TimeUnit.SECONDS);
//	}

	@Given("User navigated to the home application url")
	public void user_navigated_to_the_home_application_url() {
		WebDriverFactory.navigateToTheUrl(base_url);
		//driver.get(base_url);
//		String expected = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
//		String actual = driver.getTitle();
//		Assert.assertEquals("Page Title validation", expected, actual);
//		scn.log("Base url -> " + base_url + "title validation is successful");
//		logger.info("Base url => " + base_url + "title validation is successful");
		landingPageObjects.validateLandingPageTitle(base_url);
		
	}

	@When("User Search for product {string}")
	public void user_search_for_product(String productName) {
//		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
//		WebElement elementSearchBox = webDriverWait
//				.until(ExpectedConditions.elementToBeClickable(By.id("twotabsearchtextbox")));
//		elementSearchBox.sendKeys(productName);
//		driver.findElement(By.xpath("//input[@value='Go']")).click();
//		logger.info("clicked on search button");
		landingPageObjects.searchProduct(productName);
		
	}

	@Then("Search Result page is displayed with title contain {string}")
	public void search_result_page_is_displayed_with_title_contain(String prodNameInTitle) {
//	WebDriverWait webDriverWait1 = new WebDriverWait(driver, 20);
//	webDriverWait1.until(ExpectedConditions.titleIs("Amazon.in : " + prodNameInTitle));
//
//		Assert.assertEquals("Page Title validation", "Amazon.in : " + prodNameInTitle, driver.getTitle());
//		scn.log("page title validation for product in title -> " + prodNameInTitle + "is successful");
//		logger.info("Page title validation for Product name in title -> "+prodNameInTitle+" is successful");
		searchDetailPageObjects.validationProdNameTitle(prodNameInTitle);
	}

	@Then("Browser is closed")
	public void browser_is_closed() {
	//	driver.quit();
	}
	

	@When("User click on any product")
	public void user_click_on_any_product() {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);

//    List<WebElement> listOfProducts = driver.findElements(By.xpath("//a[@class='a-link-normal a-text-normal']"));
//    webDriverWait.until(ExpectedConditions.visibilityOfAllElements(listOfProducts));
//    listOfProducts.get(0).click();

		WebElement firstProdLink = driver.findElement(By.xpath(
				"(//span[@class='a-size-medium a-color-base a-text-normal']//ancestor::div[@class='a-section a-spacing-small a-spacing-top-small']//a[@target='_blank'])[1]"));
		webDriverWait.until(ExpectedConditions.elementToBeClickable(firstProdLink));
		firstProdLink.click();
	}

	@Then("Product Description is displayed in new tab")
	public void product_description_is_displayed_in_new_tab() {

		Set<String> handles = driver.getWindowHandles(); // get all the open windows
		Iterator<String> it = handles.iterator(); // get the iterator to iterate the elements in set
		String original = it.next();// gives the parent window id
		String prodDescp = it.next();// gives the child window id

		driver.switchTo().window(prodDescp); // switch to product Descp

		WebElement productTitle = driver.findElement(By.id("productTitle"));
		Assert.assertEquals("Product Title", true, productTitle.isDisplayed());

		WebElement addToCartButton = driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));
		Assert.assertEquals("Product Title", true, addToCartButton.isDisplayed());
		scn.log("Add to cart button is displayed");
		// Switch back to the Original Window, however no other operation to be done
		driver.switchTo().window(original);

	}

}
