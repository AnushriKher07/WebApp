package extent;

import java.io.IOException;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Locators.LoginPageLocators;
import Utility.Configreader;
import Utility.Utility;



public class extentDemo {
	ExtentReports extent;
    ExtentTest logger;
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.gecko.driver", Configreader.getPropertyValue("webdriver.gecko.driver"));
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        ExtentHtmlReporter reporter = new ExtentHtmlReporter("./Reports/learn_automation1.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);

        logger = extent.createTest("LoginTest");
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            String temp = Utility.captureScreenshot(driver);
            logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        } else {
            logger.pass("Test passed");
        }

        extent.flush();
        driver.quit();
    }

    @Test
    public void loginTest() {
        driver.get(Configreader.getPropertyValue("base.url"));

        // Enter email
        WebElement emailInput = driver.findElement(LoginPageLocators.Email_Input);
        emailInput.sendKeys("test1998@gmail.com");
        String screenshotPath1 = Utility.captureScreenshot(driver);
        try {
            logger.pass("Entered Email ID", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath1).build());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Enter password
        WebElement passwordInput = driver.findElement(LoginPageLocators.Password_Input);
        passwordInput.sendKeys("Project@123");
        String screenshotPath2 = Utility.captureScreenshot(driver);
        try {
            logger.pass("Entered Password", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath2).build());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Press submit button
        WebElement submitButton = driver.findElement(LoginPageLocators.Submit_Button);
        submitButton.click();
        String screenshotPath3 = Utility.captureScreenshot(driver);
        try {
            logger.pass("Pressed Submit Button", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath3).build());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Verify page title
        String pageTitle = driver.getTitle();
        Assert.assertTrue(pageTitle.contains("nopCommerce demo store"), "Page title doesn't contain expected text");
        String screenshotPath4 = Utility.captureScreenshot(driver);
        try {
            logger.pass("Page title is: " + pageTitle, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath4).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
        
		
    }
    


