package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterUserPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {

	public DriverFactory df;   // Creating Ref Object of DriverFactory as we need methods of it in 
							   // Base Class	
	public WebDriver driver;
	public Properties prop;
	
	protected LoginPage lp;       // Creating Ref Obj of LoginPage and we will pass driver of this class in constructor
	protected AccountsPage ap;
	protected SearchResultsPage srp;
	protected ProductInfoPage pi;
	protected RegisterUserPage rp;
	
	public SoftAssert sa;     // SoftAssert is class from TestNg to use for Soft Assertions
	
	@Parameters({"browser", "browserversion"})
	@BeforeTest
	public void setup(String browser, String browserversion)  // this variables are set with values coming from xml
	{
		df=new DriverFactory();
		prop=df.init_prop_config_file();     //     taking properties in local variable
		if(browser!=null)										// Here we are updating browser and browserversion values
		{														// in prop file if browser is coming from xml
			prop.setProperty("browser", browser);
			prop.setProperty("browserversion", browserversion);
			
		}
		
		driver=df.init_driver(prop);
		lp=new LoginPage(driver);
		sa=new SoftAssert();
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
}
