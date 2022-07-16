package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager_Headless_Incognito {

	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	
	public OptionsManager_Headless_Incognito(Properties prop)
	{
		this.prop=prop;
	}
	
	public ChromeOptions getChromeOptions()
	{
		// These are capabilities which we are taking in driverfactory
		co=new ChromeOptions();
		
		if(Boolean.parseBoolean(prop.getProperty("remote")))          // true will come as string, so we need to convert to boolean with wrapper class
		{
		co.setCapability("enableVNC",Boolean.parseBoolean(prop.getProperty("enableVNC")));
		// key and value  - capabilityname and value
		co.setBrowserVersion(prop.getProperty("browserversion"));  // added this while running on aws selenoid
		}
		
		if(Boolean.parseBoolean(prop.getProperty("headless")))          // true will come as string, so we need to convert to boolean with wrapper class
		{
		co.setHeadless(true);
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito")))
		{
		co.addArguments("--incognito");
		}
		return co;
	}
	
	public FirefoxOptions getFireFoxOptions()
	{
		fo=new FirefoxOptions();
		
		if(Boolean.parseBoolean(prop.getProperty("remote")))          // true will come as string, so we need to convert to boolean with wrapper class
		{
		fo.setCapability("enableVNC",Boolean.parseBoolean(prop.getProperty("enableVNC")));
		// key and value  - capabilityname and value
		fo.setBrowserVersion(prop.getProperty("browserversion"));
		}
		
		if(Boolean.parseBoolean(prop.getProperty("headless")))          // true will come as string, so we need to convert to boolean with wrapper class
		{
		fo.setHeadless(true);
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito")))
		{
			fo.addArguments("--incognito");
		}
		return fo;
	}
}
