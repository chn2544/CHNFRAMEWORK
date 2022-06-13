package com.qa.opencart.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterUserPage {

	private WebDriver driver;
	private ElementUtil eu;

	// Git Hub Test
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("//label[@class='radio-inline']/input[@value='1' and @type='radio']");
	private By subscribeNo = By.xpath("//label[@class='radio-inline']/input[@value='0' and @type='radio']");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	private By registerSuccessMesg = By.cssSelector("div#content h1");

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	private By confirmregistration=By.cssSelector("div #content h1");
	
	public RegisterUserPage(WebDriver driver) 
	{
		this.driver=driver;
		eu=new ElementUtil(driver); 
	}

	public boolean registerUser(String firstName,String lastname,String email,String telephone,String password,String subscribe)
	{  
	   WebElement fname=eu.WaitforElemVisible(this.firstName, Constants.DEFAULT_ELEMENT_TIMEOUT);
	   fname.clear();     // to clear value of first element, as we are using waitforelemvisible method , so cannot use dosendkeys
	   fname.sendKeys(firstName);
	   eu.dosendKeys(this.lastName,lastname);    // this is used  to get class variable
	   eu.dosendKeys(this.email,email);
	   eu.dosendKeys(this.telephone,telephone);
	   eu.dosendKeys(this.password,password);
	   eu.dosendKeys(this.confirmpassword,password);
	   if(subscribe.equalsIgnoreCase("Yes"))
	   {
		   eu.doClick(subscribeYes);
	   }
	   else
	   {
		   eu.doClick(subscribeNo);
	   }
	   eu.doClick(agreeCheckBox);
	   eu.doClick(continueButton);
	   
	   String successmsg=eu.WaitforElemVisible(confirmregistration, Constants.DEFAULT_ELEMENT_TIMEOUT).getText();
	   if(successmsg.contains(Constants.ACCOUNT_CREATION_SUCCESS))
	   {
		   eu.doClick(logoutLink);       // post successful creation of user, this website login in with same user so we need to logout
		   eu.doClick(registerLink);    // and then click on register to create second user
		   return true;
	   }
	   else
	   { 
		   eu.doClick(logoutLink);     
		   eu.doClick(registerLink);   
		   return false;
	   }
	   }
	}
	

