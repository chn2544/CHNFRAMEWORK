package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constants;
import com.qa.opencart.pages.LoginPage;

import io.netty.util.Constant;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import net.bytebuddy.build.Plugin.Factory.UsingReflection.Priority;

@Epic("Epic - 100 - Login Page for Open Cart App")
@Story("Desing Login Page with Various Features")
public class LoginPageTest extends BaseTest{
	
	
	@Description("Login Page Title Test...")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginPageTitleTest()
	{
		String title=lp.getLoginPageTitle();
		System.out.println("Login Page Title : "+title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);   // Instead of writing Hard Coded Value, we have declared expected tile as constant
	}
	
	@Description("Login Page URL Test...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void loginPageURLTest()
	{
		String act_url=lp.getLoginPageURL();
		System.out.println("Login Page URL : "+act_url);
		Assert.assertTrue(act_url.contains(Constants.LOGIN_PAGE_URL_FRACTION));
	}
	
	@Description("Forgot Password Link Test...")
	@Severity(SeverityLevel.CRITICAL)
	@Test (priority = 3)
	public void loginforgetpwdlinkTest()
	{
		Assert.assertTrue(lp.isForgotPasswordLinkExist());
	}
	
	@Description("Registration Link Test...")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 4)
	public void loginregisterlinkTest()
	{
		Assert.assertTrue(lp.isRegisterLinkExist());
	}
	
	@Description("User able to login to App...")
	@Severity(SeverityLevel.BLOCKER)
	@Test (priority = 5)
	public void logintoapp()
	{	Assert.assertTrue(
		lp.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim())
		           .isLogoutLinkExist());
			       
	}
	
	
}
