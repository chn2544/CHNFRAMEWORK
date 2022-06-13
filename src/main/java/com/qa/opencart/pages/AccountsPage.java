package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class AccountsPage {
	

	private WebDriver driver;
	private ElementUtil eu;
	
	
	 private By header=By.cssSelector("div#logo img");
	 private By logoutlink=By.linkText("Logout");	
	 private By section_headers=By.cssSelector("div#content h2");
	 private By search_feild=By.name("search");
	 private By search_button=By.cssSelector("div #search button");
	
	
	public AccountsPage(WebDriver driver) 
	{
		this.driver=driver;
		eu=new ElementUtil(driver); 
	}
	
	@Step("Getting Accounts Page Title of Open Cart Application")
	public String getAccountsPageTitle() 
	{
		return eu.waitForTitleIs(Constants.ACCOUNTS_PAGE_TITLE,Constants.DEFAULT_ELEMENT_TIMEOUT);
	}
	
	public String getAccountsPageURL()
	{
		return eu.waitForUrlContains(Constants.ACCOUNTS_PAGE_URL_FRACTION, Constants.DEFAULT_NONELEMENT_TIMEOUT);
	}
	
	public String getAccountsPageHeader()
	{
		return eu.doGetText(header);
	}
	
	@Step("TC for Account Page Section List")
	public List<String> getAccountsPageSectionsList()
	{
		List<WebElement> sectionlist=eu.getElementsList(section_headers);
		List<String> secvaluelist=new ArrayList<String>();
		for(WebElement e:sectionlist)
		{
			String text=e.getText();
			secvaluelist.add(text);
		}
		return secvaluelist;
	}
	
	public boolean isLogoutLinkExist()
	{
		return eu.WaitforElemVisible(logoutlink, Constants.DEFAULT_ELEMENT_TIMEOUT).isDisplayed();
	}
	
	@Step("Checking if Search Box exists for Account Page...")
	public boolean isSearchExist()
	{
		return eu.WaitforElemVisible(search_feild, Constants.DEFAULT_ELEMENT_TIMEOUT).isDisplayed();
	}
	
	public SearchResultsPage doSearch(String searchkey)
	{
		if(isSearchExist())
		{
			
			eu.dosendKeys(search_feild, searchkey);
			eu.doClick(search_button);
			
			return new SearchResultsPage(driver);  // we will be landing on new page, so we need to return next landing page class object
		}
		return null;
	}
	
	public LoginPage doLogout()
	{
		if(isLogoutLinkExist())
		{
			eu.doClick(logoutlink);
		}
		return new LoginPage(driver);
	}
}
