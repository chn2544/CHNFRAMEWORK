package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {
	
	@BeforeClass
	public void RegisterSetup ()       // we should be landed on registration form page
	{
		rp=lp.gotoRegistration();
	}
	
	@DataProvider
	public Object[][] getRegistrationTestData()
	{
	   Object[][] regdata= ExcelUtil.readTestData(Constants.REGISTRATION_SHEET_NAME);
	   return regdata;
	}

	public String createRandomEmail()     // to generate random email as it is unique id for  user
	{
		Random rd=new Random();
		String email="IPLUser"+rd.nextInt(1000)+"@gmail.com";
		return email;
	}

	@Test(dataProvider = "getRegistrationTestData")
	public void registrationUserTest(String fname,String lname,String telephone,String password,String subscribe) 
	{
		Assert.assertTrue(rp.registerUser(fname,lname,createRandomEmail(),telephone,password,subscribe));
	}
}


