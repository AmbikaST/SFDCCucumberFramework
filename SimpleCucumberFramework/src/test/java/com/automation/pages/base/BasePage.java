package com.automation.pages.base;

import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.automation.utility.PropertiesUtility;

public class BasePage {
	
	protected  WebDriver driver;
	protected  WebDriverWait wait;
	//protected Log4JUtility logObject=Log4JUtility.getInstance();
	protected Logger log;
	//protected ExtentReportsUtility extReport=ExtentReportsUtility.getInstance();
	protected static PropertiesUtility propUtility = PropertiesUtility.getInstance();
	
	public BasePage(WebDriver driver) {
		log=LogManager.getLogger(BasePage.class.getName());
		this.driver=driver;
		System.out.println("driver in basePage");
		log.info("driver in basePage="+driver);
		PageFactory.initElements(driver, this);
		//log=logObject.getLogger();
		
	}
	
	public String loginValidUsername() {
		Properties application = propUtility.loadFile("applicationDataProperties");
		String userId = application.getProperty("login.valid.username");
		return userId;
	}
	
	public String loginValidPassword() {
			Properties application = propUtility.loadFile("applicationDataProperties");
			String password = application.getProperty("login.valid.password");
			return password;
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public void enterText(WebElement element,String data,String objectName) {
		System.out.println("Enter into the username field");
		if(element.isDisplayed()) {
			clearElement(element,objectName);
			element.sendKeys(data);
			//extReport.logTestInfo("pass:"+objectName+"is entered to the username field");
		}else {
			log.error("Fail:"+objectName+"element is not displayed");
		}
	}
	
	public void clearElement(WebElement element, String objectName) {
		if(element.isDisplayed()) {
			element.clear();
			log.info("pass:"+objectName+"element is cleared");
		}else {
			log.error("Fail:"+objectName+"element is not cleared");
		}
	}
	
	public void clickElement(WebElement element, String objectName) {
		if(element.isDisplayed()) {
			element.click();
			System.out.println("pass:"+objectName+"element is clicked");
		}else {
			System.out.println("Fail:"+objectName+"element is not clicked");
		}
	}
	
	public void refreshPage() {
		driver.navigate().refresh();
	}
	
	public String getTextFromWebElement(WebElement element,String name) {
		if(element.isDisplayed()) {
			return element.getText();
		} else {
			System.out.println(name+"webelement is not displayed");
			return null;
		}
	}
	
	public Alert switchToAlert() {
		Alert alert = driver.switchTo().alert();
		System.out.println("Switched to Alert");
		return alert;
	}
	
	public void acceptAlert(Alert alert) {
		System.out.println("Alert accepted");
		alert.accept();
	}
	
	public String getAlertText(Alert alert) {
		System.out.println("Extratcing text from the Alert");
		return alert.getText();
	}
	
	public void dismisAlert() {
		Alert alert = switchToAlert();
		alert.dismiss();
		System.out.println("Alert is closed");
	}
	
	public void moveToElementAction(WebElement element,String objName) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		System.out.println("Cursor moved to the element "+objName);
	}
	
	public void contextClickAction(WebElement element,String objName) {
		Actions action = new Actions(driver);
		action.contextClick(element).build().perform();
		System.out.println("right click performed on web Element:"+objName);
	}
	
	public void waitUntilElementIsVisible(WebElement ele,String objName) {
		System.out.println("waiting for an web element"+objName+"for its visibility");
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public String errorMsg(String errorId) {
		WebElement errorMessage  = driver.findElement(By.id(errorId));
		return errorMessage.getText();
	}
	
	public void advertisementPage(WebElement dialogBox) {
		driver.getWindowHandle();
		if(dialogBox.isDisplayed()) {
		clickElement(dialogBox, "Advertisement pop up");
		}
	}
	
	public void dropDownMenu(WebElement leadHome) {
	Select select = new Select(leadHome);
	List<WebElement> web = select.getOptions();
	String[] leadDropdown = {"All Open Leads","My Unread Leads","Recently Viewed Leads","Today's Leads",""};
	
	int i =0;
	for(WebElement dropDown: web) {
		if(i<leadDropdown.length-1) {
			Assert.assertEquals(dropDown.getText(),leadDropdown[i]);
			i++;
		}
	  }
	}
	
	public static void selectFromDropDown(WebElement data,String value) {
		Select select = new Select(data);
		 select.selectByVisibleText(value);		
	}

}
