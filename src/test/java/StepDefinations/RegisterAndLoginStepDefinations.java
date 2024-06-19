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
import Locators.RegisterPageLocators;
import Utility.Configreader;
import Utility.Utility;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterAndLoginStepDefinations {
    WebDriver driver;
    ExtentReports extent;
    ExtentTest logger;
    String email;
    String password;

    @Before
    public void setup() {
        System.setProperty("webdriver.gecko.driver", Configreader.getPropertyValue("webdriver.gecko.driver"));
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        
        ExtentHtmlReporter reporter = new ExtentHtmlReporter("./Reports/learn_automation1.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        logger = extent.createTest("RegistrationAndLoginTest");
        
        // Generate a unique email for each test run
        email = "test" + System.currentTimeMillis() + "@gmail.com";
        password = Configreader.getPropertyValue("password");
    }
    
    @After
    public void tearDown() {
        extent.flush();
        if (driver != null) {
            driver.quit();
        }
    }
    
    @Given("User navigates to Registration page")
    public void user_navigates_to_Registration_page() {
        driver.get(Configreader.getPropertyValue("registration.url"));
        String screenshotPath = Utility.captureScreenshot(driver);
        try {
            logger.pass("Navigated to Registration page", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @When("User enters Registration details")
    public void user_enters_Registration_details() {
        driver.findElement(RegisterPageLocators.FirstName_Input).sendKeys("TestFirstName");
        driver.findElement(RegisterPageLocators.LastName_Input).sendKeys("TestLastName");
        driver.findElement(RegisterPageLocators.Email_Input).sendKeys(email);
        driver.findElement(RegisterPageLocators.Password_Input).sendKeys(password);
        driver.findElement(RegisterPageLocators.ConfirmPassword_Input).sendKeys(password);
        String screenshotPath = Utility.captureScreenshot(driver);
        try {
            logger.pass("Entered Registration details", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @And("User submits the Registration form")
    public void user_submits_the_Registration_form() {
        driver.findElement(RegisterPageLocators.Register_Button).click();
        String screenshotPath = Utility.captureScreenshot(driver);
        try {
            logger.pass("Submitted Registration form", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Then("User should be registered successfully")
    public void user_should_be_registered_successfully() {
        String actualTitle = driver.getTitle();
        String screenshotPath = Utility.captureScreenshot(driver);
        try {
            logger.pass("Registration successful. Page title is: " + actualTitle, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(actualTitle.contains("Your registration completed"), "Registration failed");
    }

    @Given("User navigates to Login page")
    public void user_navigates_to_Login_page() {
        driver.get(Configreader.getPropertyValue("base.url"));
        String screenshotPath = Utility.captureScreenshot(driver);
        try {
            logger.pass("Navigated to Login page", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @When("User enters Email id for login")
    public void user_enters_Email_id_for_login() {
        WebElement emailInput = driver.findElement(LoginPageLocators.Email_Input);
        emailInput.sendKeys(email);
        String screenshotPath = Utility.captureScreenshot(driver);
        try {
            logger.pass("Entered Email ID for login", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @When("User enters Password for login")
    public void user_enters_Password_for_login() {
        WebElement passwordInput = driver.findElement(LoginPageLocators.Password_Input);
        passwordInput.sendKeys(password);
        String screenshotPath = Utility.captureScreenshot(driver);
        try {
            logger.pass("Entered Password for login", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @When("User presses Login button")
    public void user_presses_Login_button() {
        WebElement submitButton = driver.findElement(LoginPageLocators.Submit_Button);
        submitButton.click();
        String screenshotPath = Utility.captureScreenshot(driver);
        try {
            logger.pass("Pressed Login Button", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Then("User should be logged in successfully")
    public void user_should_be_logged_in_successfully() {
        String actualTitle = driver.getTitle();
        String screenshotPath = Utility.captureScreenshot(driver);
        try {
            logger.pass("Logged in successfully. Page title is: " + actualTitle, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(actualTitle.contains("nopCommerce demo store"), "Login failed");
    }

}
