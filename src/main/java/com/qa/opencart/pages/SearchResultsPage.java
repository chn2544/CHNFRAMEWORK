package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil eu;
	
	private By searchResults=By.cssSelector("div.product-layout.product-grid");
	private By productNameLink;
	
	public SearchResultsPage(WebDriver driver) 
	{
		this.driver=driver;
		eu=new ElementUtil(driver); 
	}
	
	public int searchResultsCount()    // if count >0, search is successfull, if count =0, search is not done
	{
		return eu.waitForVisibilityOfElements(searchResults, Constants.DEFAULT_ELEMENT_TIMEOUT).size();
	}
	
	public ProductInfoPage selectProduct(String productname)
	{
		productNameLink=By.linkText(productname);   // we have initialized here, to catch parameter value
		eu.doClick(productNameLink);
		return new ProductInfoPage(driver);
	}
	
	//Additional GIT Data

}
