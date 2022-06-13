package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eu;

	private By productheader=By.cssSelector("div.col-sm-4 h1");
	private By imgcount=By.cssSelector("ul.thumbnails img");
	private By prodmetadata=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By pricingdata=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By inputqty=By.id("input-quantity");
	private By addtocart=By.id("button-cart");
	private By successMessage=By.cssSelector("div.alert.alert-success");
	private By cart=By.cssSelector("div#cart button.dropdown-toggle");
	
	Map<String, String> productInfoMap;
	
	public ProductInfoPage(WebDriver driver) 
	{
		this.driver=driver;
		eu=new ElementUtil(driver); 
	}
	
	public String getProductHeaderName()
	{
		return eu.WaitforElemVisible(productheader, Constants.DEFAULT_ELEMENT_TIMEOUT).getText();
	}
	
	public int getImageCount()
	{
		return eu.waitForVisibilityOfElements(imgcount, Constants.DEFAULT_ELEMENT_TIMEOUT).size();
	}
	
	public Map<String, String> getProductInformation()
	{
		productInfoMap=new HashMap<String, String>();
		productInfoMap.put("Name",getProductHeaderName());
		getProductMetaData();           // encapsulation, private methods being accessed by public layer
		getProductPrice();
		productInfoMap.forEach((k,v) -> System.out.println(k+" : "+v));
		return productInfoMap;
		
	}
	
	private void getProductMetaData()
	{
		//meta data
		List <WebElement> metadatalist=eu.getElementsList(prodmetadata);
		System.out.println("Total Count of Meda Data Rows : "+metadatalist.size());
		
//		Capturing MetaData		
//		Brand: Apple
//		Product Code: Product 18
//		Reward Points: 800
//		Availability: In Stock
		
		for(WebElement e : metadatalist)
		{
			String meta[]=e.getText().split(":");
			String metakey=meta[0].trim();
			String metavalue=meta[1].trim();
			productInfoMap.put(metakey, metavalue);
		}
	
	}
	
	
	private void getProductPrice()
	{
//		Capturing Price
//		$2,000.00
//		Ex Tax: $2,000.00
		
		List <WebElement> metapricelist=eu.getElementsList(pricingdata);
		String prodprice=metapricelist.get(0).getText().trim();
		String extprice=metapricelist.get(1).getText().trim();
		
		productInfoMap.put("price", prodprice);
		productInfoMap.put("extprice", extprice);
	}
	
	public String getProductInfoPageInnerText()
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		String pageinnertext=js.executeScript("return document.documentElement.innerText").toString();
		System.out.println("========================\n"+pageinnertext+"\n==================");
		return pageinnertext;
	}
	
	public ProductInfoPage enterQuantity(String qty)
	{
		eu.dosendKeys(inputqty, qty);
		return this;                     // advantage of this we will get builder pattern/chaining 
	}
	
	public ProductInfoPage clickOnAddToCart()
	{
		eu.doClick(addtocart);
		return this;
	}

	public String getCartSuccessMessage()
	{
		return eu.WaitforElemVisible(successMessage, Constants.DEFAULT_ELEMENT_TIMEOUT).getText();
	}
	
	public String getCartItemText()
	{
		return eu.doGetText(cart);
	}
}
