package com.cucumberTests.page;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.automation.pages.base.BasePage;

public class LoginPage extends BasePage{
	
	@FindBy(id = "username") WebElement userNameElement;
	@FindBy(id = "password") WebElement passwordElement;
	@FindBy(id = "Login") WebElement button;
	@FindBy(id = "rememberUn") WebElement rememberElement;
	@FindBy(id = "userNavButton") WebElement userNavElement;
	@FindBy(id = "forgot_password_link") WebElement forgotLinkElement;
	@FindBy(id = "un") WebElement userElement;
	@FindBy(id = "continue") WebElement continueElement;
	@FindBy(xpath = "//*[@id=\"userNav-menuItems\"]/a[5]") WebElement userNavPath;
	@FindBy(id = "idcard") WebElement identityElement;
	@FindBy(id = "error") WebElement errorElement;
	Properties application;
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public String loginInValidUsername() {
		System.out.println("Enter text");
		application = propUtility.loadFile("applicationDataProperties");
		String userId = application.getProperty("login.invalid.username");
		return userId;
	}
	
	public String loginInValidPassword() {
		application = propUtility.loadFile("applicationDataProperties");
		String password = application.getProperty("login.invalid.password");
		return password;
	} 
	
	public String loginEmptyPassword() {
		Properties application = propUtility.loadFile("applicationDataProperties");
		String password = application.getProperty("login.empty.password");
		return password;
}
	
	public void enterUserName(String userNameData) {
		log.info("entering into the login page for the username");
		enterText(userNameElement,userNameData,"username");
		waitUntilElementIsVisible(userNameElement, "username field");
	}
	
	public void enterPassWord(String passWordData) {
		log.info("entering into the login page for the Password");
		enterText(passwordElement,passWordData,"Password");
	}
	
	public void enterEmptyPassword(String passWordData) {
		log.info("entering into the login page for the Password");
		clearElement(passwordElement,"Password");
	}
	
	public WebDriver clickButton() {
		waitUntilElementIsVisible(button,"login Button");
		clickElement(button, "login Button");
		return driver;
	}
	
	public void rememberButton() {
		clickElement(rememberElement, "Remember check Box ");
	}
	
	public void usrNavButton() {
		waitUntilElementIsVisible(userNavElement,"User Navigation");
		clickElement(userNavElement, "User Navigation ");
	}
	
	public void forgotLink() {
		waitUntilElementIsVisible(forgotLinkElement,"Forgot link");
		clickElement(forgotLinkElement, "Forgot link ");
	}
	
	public String getTitleOfThePage() {
		return getPageTitle();
	}
	
	public void enterUsrNameForgot(String userNameData) {
		enterText(userElement,userNameData,"username");
	}
	
	public void clickContinue() {
		clickElement(continueElement, "Continue button ");
	}
	
	public void clickusrDropDown() {
		waitUntilElementIsVisible(userNavPath,"User Drop Down");
		clickElement(userNavPath, "User Drop Down");
	}
	
	public String identityText() {
		waitUntilElementIsVisible(identityElement,"User Identity");
		return getTextFromWebElement(identityElement, "User Identity ");
	}
	
	public String loginErrMsg(String errorId) {
		waitUntilElementIsVisible(errorElement, "error");
		return errorMsg(errorId);
		
	}
}
