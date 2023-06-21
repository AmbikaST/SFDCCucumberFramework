package com.cucumberTests.steps;

import static org.testng.Assert.assertEquals;

import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.automation.utility.Constants;
import com.automation.utility.Log4JUtility;
import com.automation.utility.PropertiesUtility;
import com.cucumberTests.page.LoginPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesforceStepDef {
	
	public  static WebDriver driver;
	protected static Logger log;
	protected static Log4JUtility logObject=Log4JUtility.getInstance();
	LoginPage login;
	protected static PropertiesUtility propUtility = PropertiesUtility.getInstance();
	protected static  LoginPage page;
	String userId = null;
	String userName = null;
	String title = null;
	

	public static void launchBrowser(String browserName) {
		switch(browserName) {
		case "Chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			break;
		}
		log.info(browserName+" browser is opened");
		
	}
	
	public static void goToURL(String url) {
		driver.get(url);
		log.info(url+" is entered");
		
	}
	
	public static void closeBrowser() {
		driver.close();
		log.info("current browser is closed");
	}
	
	@BeforeAll
	public static void setUpBeforeAllScenarios() {
		log=logObject.getLogger();
	}
	@Before
	public void setUpEachScenario() {
		
		launchBrowser("Chrome");
		
	}
	@After
	public void tearDown() {
		closeBrowser();
	}
	
	
	@Given("application is up and running and in loginpage")
	public void application_is_up_and_running_and_in_loginpage() {
		PropertiesUtility pro=new PropertiesUtility();
		Properties appProp= pro.loadFile("applicationDataProperties");
		String url= appProp.getProperty("url");
		//launchBrowser("chrome");
		System.out.println("driver in baseTest="+driver);
		goToURL(url);
	}

	@When("i enter username and password")
	public void i_enter_username_and_password() {
		page = new LoginPage(driver);
		userId = page.loginValidUsername();
		String password = page.loginValidPassword();
		title = page.getTitleOfThePage();
		page.enterUserName(userId);
		page.enterPassWord(password);
	}

	@When("click on the login button")
	public void click_on_the_login_button() {
		
		driver = page.clickButton();
	}

	@Then("i should get the home page")
	public void i_should_get_the_home_page() {
		Assert.assertEquals(Constants.LOGINTITLE, title);
		Assert.assertEquals(Constants.HOMEPAGE, page.getTitleOfThePage());
		
		log.info("Testscript TC-02 execution completed");
	}
	
	@When("i enter username and blank password")
	public void i_enter_username_and_blank_password() {
		page = new LoginPage(driver);
		userId = page.loginValidUsername();
		String password = page.loginEmptyPassword();
		title = page.getTitleOfThePage();		
		page.enterUserName(userId);
		page.enterEmptyPassword(password);
	}

	@Then("i should get the error message")
	public void i_should_get_the_error_message() {
		Assert.assertEquals(Constants.LOGINTITLE, title);
		String expectedMsg = Constants.LOGINERRORMSG;
		String actualMsg = page.loginErrMsg("error");
		assertEquals(expectedMsg, actualMsg);
		
		log.info("Testscript TC-01 execution completed");
	}
	
	@When("select the remember username checkbox")
	public void select_the_remember_username_checkbox() {
		page.rememberButton();
	}

	@When("click on the usermenu drop down and select the logout link")
	public void click_on_the_usermenu_drop_down_and_select_the_logout_link() {
		page.usrNavButton();
		page.clickusrDropDown();
		userName = page.identityText();
	}

	@Then("user name should be displayed in the username field")
	public void user_name_should_be_displayed_in_the_username_field() {
		Assert.assertEquals(userId, userName);
		Assert.assertEquals(Constants.LOGINTITLE, page.getTitleOfThePage());
		
		log.info("Testscript TC-03 execution completed");
	}
	
	@When("click on forgot password")
	public void click_on_forgot_password() {
		page = new LoginPage(driver);
		userId = page.loginValidUsername();
		page.forgotLink();
	}
	@When("provide username and click on continue button")
	public void provide_username_and_click_on_continue_button() {
		page.enterUsrNameForgot(userId);
		page.clickContinue();
	}
	@Then("password reset link should be send to user email id")
	public void password_reset_link_should_be_send_to_user_email_id() {
		Assert.assertEquals(Constants.FORGOTPWD,page.getTitleOfThePage());
		log.info("Testscript TC-04A execution completed");
	}
	
	@When("i enter invalidUsername and invalidPassword")
	public void i_enter_invalid_username_and_invalid_password() {
		page = new LoginPage(driver);
		String userId = page.loginInValidUsername();
		String password = page.loginInValidPassword();
		page.enterUserName(userId);
		page.enterPassWord(password);
	}
	
	@Then("error message should be displayed")
	public void error_message_should_be_displayed() {
		String actualErrorMsg = page.loginErrMsg("error");
		Assert.assertEquals(Constants.INVALIDLOGINMSG,actualErrorMsg);
		log.info("Testscript TC-04B execution completed");
	}


}
