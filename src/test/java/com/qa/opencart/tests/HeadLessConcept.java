package com.qa.opencart.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HeadLessConcept {    
	// This class is for practice, not used anywhere in framework
	public static void main(String args[])
	{
		WebDriverManager.chromedriver().setup();
		//Headless browser : no launching of browser
		// slightly faster execution than normal mode
		//testing is behind the scenes
		
		// incognito mode : private mode
		
		ChromeOptions co=new ChromeOptions();
		//co.addArguments("--headless");
		// OR    //co.setHeadless(true);
		co.addArguments("--incognito");
		WebDriver driver=new ChromeDriver(co);
		driver.get("https://www.google.com");
		System.out.println("Title of page : "+driver.getTitle());
		driver.quit();
		
	}

}
