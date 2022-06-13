package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eu;
	
	//1. Private By Locators should be maintained (so that no other class can access them directly)  : Object Repository
	// Page class is classic example of Encapsulation
	
	private By email=By.id("input-email");
	private By pwd=By.id("input-password");
	private By loginbtn=By.xpath("//input[@type='submit']");
	private By forgotpwd=By.linkText("Forgotten Password");
	private By RegisterLink=By.linkText("Register");
	private By logoutvalidation=By.cssSelector("div #content h1");
	
	private By chnloc=By.cssSelector("Chinmay_For_GIT");
	

	//2. Page Class Constructor
	
	
	public LoginPage(WebDriver driver) 
	{
		this.driver=driver;
		eu=new ElementUtil(driver); 
	}

	//3. Page Actions:
	@Step("Getting Login Page Title of Open Cart Application")
	public String  getLoginPageTitle()
	{
		return eu.waitForTitleIs(Constants.LOGIN_PAGE_TITLE,Constants.DEFAULT_NONELEMENT_TIMEOUT );
	}

	@Step("Getting Login Page URL of Open Cart Application")
	public String getLoginPageURL()
	{
		return eu.waitForUrlContains(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_NONELEMENT_TIMEOUT);
	}
	
	@Step("User is able to login with username : {0} and password : {1}")
	public AccountsPage doLogin(String username, String password)
	{
		System.out.println("Login Credentials are - username : "+username+" , password : "+password);
	//	driver.findElement(email).sendKeys(username);    // private members being used in public method
	//	driver.findElement(pwd).sendKeys(password);		// Encapsulation
	//	driver.findElement(loginbtn).click();
		eu.WaitforElemVisible(email, Constants.DEFAULT_ELEMENT_TIMEOUT).sendKeys(username);
		eu.dosendKeys(pwd,password);
		eu.doClick(loginbtn);
		return new AccountsPage(driver);
	}
	
	@Step("Checking if ForgotPasswordLink Exist")
	public boolean isForgotPasswordLinkExist()
	{
		return eu.doisDisplayed(forgotpwd);
	}
	
	@Step("Checking if RegisterLink Exist")
	public boolean isRegisterLinkExist()
	{
		return eu.doisDisplayed(RegisterLink);
	}
	
	@Step("Fetching successfull message for Logout")
	public String getLogoutSuccessMessage() {
		// 
		return eu.WaitforElemPresence(logoutvalidation, Constants.DEFAULT_ELEMENT_TIMEOUT).getText();
		
	}
	
	@Step("Navigating to Registration Page after clicking on Registration Link")
	public RegisterUserPage gotoRegistration()
	{
		eu.doClick(RegisterLink);
		return new RegisterUserPage(driver);
	}
	
}
