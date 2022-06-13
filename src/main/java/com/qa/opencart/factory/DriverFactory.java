package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.opencart.exceptions.FrameWorkException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager_Headless_Incognito om;
	public static ThreadLocal <WebDriver> tldriver =new ThreadLocal <WebDriver>();
	
	
	/*
		This method is used to initialize WebDriver on basis of browser name 
		and it returns driver
	 */
	
	public WebDriver init_driver(Properties prop)
	{
		String browsername=prop.getProperty("browser").trim();
		String url=prop.getProperty("url").trim();
		om=new OptionsManager_Headless_Incognito(prop);
		
		System.out.println("Browser Name : "+browsername);
	
		if(browsername.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
//			driver=new ChromeDriver(om.getChromeOptions());
			tldriver.set(new ChromeDriver(om.getChromeOptions()));
		}
		else if(browsername.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
//			driver=new FirefoxDriver(om.getFireFoxOptions());
			tldriver.set(new FirefoxDriver(om.getFireFoxOptions()));
		}
		else
		{
			System.out.println("Please pass Valid Brower, "+browsername+" is invalid browser name");
		}
	
		getDriver().manage().deleteAllCookies();
//		driver.manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(url);

		return getDriver();
	}
	
	// to get thread local copy of driver 
	
	public  static WebDriver getDriver() 
	{
		return tldriver.get();
	}
	
	// This method is used to initialize properties
	
	public Properties init_prop_config_file() 
	{
		prop=new Properties();
		FileInputStream ip=null;
		
		//mvn clean install -Denv="qa"                // execute this line via CMD
		String env=System.getProperty("env");
		System.out.println("Running Automation on "+env+" Environment");
		if(env==null)
		{
			System.out.println("No Env specified, running on QA Env");
			try {
				ip=new FileInputStream("./src/test/resources/Config Folder/qa.config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			try {
				
			
			switch(env.toLowerCase()) {
			
			case "qa"  : 
					System.out.println("Running on QA Env");
					ip=new FileInputStream("./src/test/resources/Config Folder/qa.config.properties");
					break;

			case "stage"  : 
					System.out.println("Running on Stage Env");
					ip=new FileInputStream("./src/test/resources/Config Folder/stage.config.properties");
					break;

			case "uat"  : 
					System.out.println("Running on UAT Env");
					ip=new FileInputStream("./src/test/resources/Config Folder/uat.config.properties");
					break;

			case "dev"  : 
					System.out.println("Running on Dev Env");
					ip=new FileInputStream("./src/test/resources/Config Folder/dev.config.properties");
					break;
			case "prod"  : 
					System.out.println("Running on Prod Env");
					ip=new FileInputStream("./src/test/resources/Config Folder/config.properties");
					break;
				
			default :
					System.out.println("Please pass right env... "+env+" is incorrect");
					throw new FrameWorkException("Custom-No Environment is found exception...");
					
			}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		try {
			
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public String getScreenShot()
	{
		File srcFile=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path="./"+"screenshot/"+System.currentTimeMillis()+".png";
		File destFile=new File(path);
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
	
	
}
