package StepDefinations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Locators.LoginPageLocators;
import Utility.Configreader;
import Utility.Utility;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefinations {
	WebDriver driver;
	ExtentReports extent;
    ExtentTest logger;

    @Before
	public void setup() {
		System.setProperty("webdriver.gecko.driver", Configreader.getPropertyValue("webdriver.gecko.driver"));
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        
        
     // Initialize ExtentReports instance
        ExtentHtmlReporter reporter = new ExtentHtmlReporter("./Reports/learn_automation1.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);

        // Create a test in the Extent report
        logger = extent.createTest("LoginTest");
    }
	
	
	@After
    public void tearDown() {
		 extent.flush();
        if (driver != null) {
            driver.quit();
        }
    }
	
	 @Given("User enters Email id")
	    public void user_enters_Email_id() {
        
        driver.get(Configreader.getPropertyValue("base.url"));
        WebElement emailInput = driver.findElement(LoginPageLocators.Email_Input);
        //emailInput.sendKeys("test1998@gmail.com");
        emailInput.sendKeys(Configreader.getEmail());

        // Capture screenshot for step
        String screenshotPath = Utility.captureScreenshot(driver);
        try {
            // Attach screenshot to Extent report
            logger.pass("Navigated to Google homepage", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
	 }
        
        @When("User enters Password")
        public void user_enters_Password() {
        WebElement passwordInput = driver.findElement(LoginPageLocators.Password_Input);
        passwordInput.sendKeys(Configreader.getPassword());

        //passwordInput.sendKeys("Project@123");
        // Capture screenshot for step
        String screenshotPath = Utility.captureScreenshot(driver);
        try {
            // Attach screenshot to Extent report
            logger.pass("Navigated to Google homepage", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
        
        
        @When("User presses Submit button")
        public void user_presses_Submit_button() {
        WebElement submitButton = driver.findElement(LoginPageLocators.Submit_Button);
        submitButton.click();
       
        
        // Capture screenshot for step
        String screenshotPath = Utility.captureScreenshot(driver);
        try {
            // Attach screenshot to Extent report
            logger.pass("Navigated to Google homepage", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    @Then("Page title should contain {string}")
    public void page_title_should_contain(String expectedTitle) {
        String actualTitle = driver.getTitle();
        // Capture screenshot for step
        String screenshotPath = Utility.captureScreenshot(driver);
        try {
            // Attach screenshot to Extent report
            logger.pass("Page title is: " + actualTitle, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(actualTitle.contains(expectedTitle), "Page title doesn't contain expected text");
    }
}
