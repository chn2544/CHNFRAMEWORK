package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	private WebDriver driver;   // initially driver of this class is pointing to null   and it is created private because we need to prevent unnecessary access to driver
	
	public ElementUtil(WebDriver driver)    // We need to write this constructor because we need to get driver from main class and assign it to driver of this class
	{       
		this.driver=driver;
	}
	
	public   void selectdropdownwithgetOptions(By locator, String str)
	{
		Select country_all=new Select(getElement(locator));
		List <WebElement> allopt=country_all.getOptions();    //getOptions returns list of all available options in dropdown
		
		for(WebElement e:allopt)
		{
			String text=e.getText();
			System.out.println(text);
			if(text.equals(str))
			{
				e.click();
				break;
			}
		}
	}
	
	public void levelTwoMenuHandling(By parent, By child) throws InterruptedException
	{
		
		Actions act=new Actions(driver);
		act.moveToElement(getElement(parent)).perform();
		Thread.sleep(2000);
		getElement(child).click();
	}
	
	public List<String> getDropdownListByString(By locator)
	{
		List <WebElement> list=getElementsList(locator);
		List<String> strlist=new ArrayList<String>();
		for(WebElement e:list)
		{
			String text=e.getText();
			strlist.add(text);
		}
		return strlist;
	}
	
	public void getDDValuesSelectClassNotAval(By locator,String... str)
	{
		List<WebElement> list=driver.findElements(locator);
	
		if(str[0].equals("selectall"))
		{
			for(WebElement e:list)
			{
				e.click();
			}
		}
		else
		{
			for(WebElement e:list)
		 {
			String text=e.getText().trim();
			for(int i=0;i<str.length;i++)
			{
				if(text.equals(str[i]))
				{
					e.click();
					break;
				}
			}
		 }
		}
		
	}
	
	public int getdropdownValuesCount(By locator)
	{
		Select select=new Select(getElement(locator));
		return select.getOptions().size();
	}
	
	public void getDropdownValueswithoutSelect(By locator, String str)
	{
		List <WebElement> mylist=getElementsList(locator);
		for(WebElement e: mylist)
		{
			String text= e.getText();
			System.out.println(text);
			if(text.equals(str))
			{
				e.click();
				break;
			}
		}
		
	}
	
	public void actionsSendKeys(By locator,String value)
	{
		Actions act=new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
		
	}
	
	public void actionsClick(By locator)
	{
		Actions act=new Actions(driver);
		act.click(getElement(locator)).perform();
		
	}
	

	public List<WebElement> getElementsList(By locator)
	{
		return driver.findElements(locator);
	}
	

	//custom method to check if element is displayed or not and has size > 0
	public boolean isElemDisplayed(By locator)
	{
		WebElement webelem=getElement(locator);
		List <WebElement> mylist=getElementsList(locator);
		if(mylist.size()>0)
		{
			if(webelem.isDisplayed())
			return true;	
		}
		return false;
		
	}
	
	
	
	public List <String> getGoogleSearchList(By locator,String searchkey) throws InterruptedException
	{
		driver.findElement(By.name("q")).sendKeys(searchkey);
		Thread.sleep(3000);
		List<WebElement> list=driver.findElements(locator);
		List <String> mylist=new ArrayList<String>();
		for(WebElement e:list)
			{
				String text=e.getText();
				System.out.println(text);
				
			}
		return mylist;
	}
	
	public void selectGoogleSuggesstion(By locator,String searchkey,String toselect) throws InterruptedException
	{
		driver.findElement(By.name("q")).sendKeys(searchkey);
		Thread.sleep(3000);
		List<WebElement> list=driver.findElements(locator);
		
		for(WebElement e:list)
			{
				String text=e.getText();
				System.out.println(text);
				if(text.equals(toselect))
				{
					e.click();
					break;
				}
			}
		
	}

	
	public  List<String> getElementsAttributeList(By locator,String atrname)       // return type is List<String> becuase we are returning that
	{
		List<String> strlist =new ArrayList<String>();       // TopCasting is used
		List<WebElement> elelist=getElementsList(locator);
		for(WebElement e  : elelist)
		{
			String atrval=e.getAttribute(atrname);
			strlist.add(atrval);
		}
		return strlist;
	}
	
	public  int getElementCount(By locator)
	{ 
		int totalcount=getElementsList(locator).size();
		return totalcount;
	
		
	}
	public  List<String> getElementsTest(By locator)       // return type is List<String> because we are returning that
	{
		List<String> strlist =new ArrayList<String>();       // TopCasting is used
		List<WebElement> elelist=getElementsList(locator);
		for(WebElement e  : elelist)
		{
			String atrtext=e.getText();
			strlist.add(atrtext);
		}
		return strlist;
	}
	
	public void printList(List <WebElement> scorelist)
	{
		for(WebElement e:  scorelist)
		{
			System.out.print(e.getText());
		}
	}
	
	public List<String> getScore(String player)
	{
		System.out.println("Player Name : "+player);
		By listdata=By.xpath("(//a[contains(text(),'"+player+"')])[1]/ancestor::div[@class='cb-col cb-col-100 cb-scrd-itms']");
		List<WebElement> scorelist=getElementsList(listdata);
		List<String> score=new ArrayList<String>();
		for(WebElement e: scorelist )
		{
			String text=e.getText();
			score.add(text);
		}
		
		return score;
	}
	
	public  void selectDDByIndex(By locator,int n)
	{
		Select sel=new Select(getElement(locator));
		sel.selectByIndex(n);
	}
	
	public  void selectDDByVisibleText(By locator,String visibletext)
	{
		Select sel=new Select(getElement(locator));
		sel.selectByVisibleText(visibletext);
	}
	
	public  void selectDDByValue(By locator,String visibletext)
	{
		Select sel=new Select(getElement(locator));
		sel.selectByVisibleText(visibletext);
	}
	
	public void multilevelMenuFunction(By parent,By level1,By level2,By level3,By level4) throws InterruptedException
	{
		Actions act=new Actions(driver);
		act.moveToElement(getElement(parent)).perform();
		Thread.sleep(2000);
		act.moveToElement(getElement(level1)).perform();
		Thread.sleep(2000);
		act.moveToElement(getElement(level2)).perform();
		Thread.sleep(2000);
		act.moveToElement(getElement(level3)).perform();
		Thread.sleep(2000);
		getElement(level4).click();
		
	}
	
	

	public void listOfRightClickMenuItems(By rightclickelem, By rightclickmenuitems) {
		Actions act = new Actions(driver);
		act.contextClick(getElement(rightclickelem)).perform();

		List<WebElement> mylist = getElementsList(rightclickmenuitems);
		for (WebElement e : mylist) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals("Delete"))
			{
				e.click();
				break;
			}
		}

	}

	
	public void selectRightClickMenu(By rightclick,By rightclickmenuitems)
	{
		Actions act=new Actions(driver);
		act.contextClick(getElement(rightclick)).perform();
		getElement(rightclickmenuitems).click();
	}

	public void dragAndDrop(By src, By tgt)
	{
		Actions act=new Actions(driver);
		act.clickAndHold(getElement(src)).moveToElement(getElement(tgt)).build().perform();
	}
	
	//*********************************** Wait Utils Start********************************************
	
	//Explicitly Wait 

	public WebElement WaitforElemPresence(By locator, int timeout)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));   // returning webelement
	// presenceOfElementLocated - An expectation for checking that an element is present on the DOM of a page. 
	//This does not necessarily mean that the element is visible on Page.	
	}
	
	public WebElement WaitforElemPresenceWithPolling(By locator, int timeout,long poll)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofMillis(poll));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));   // returning webelement
	// presenceOfElementLocated - An expectation for checking that an element is present on the DOM of a page. 
	//This does not necessarily mean that the element is visible on Page.	
	}
	
	public WebElement WaitforElemVisible(By locator, int timeout)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));   // returning webelement
	// visibilityOfElementLocated - An expectation for checking that an element is present on the DOM of a page and visible.
	// Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
	}
	
	////////////////////////////// Non WebElement Utilities
	public  String waitForTitleContains(String titlesubstring,int Timeout)
	{
		 WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(Timeout));
		 if(wait.until(ExpectedConditions.titleContains(titlesubstring)))   // as until function is not returing boolean
		    {
		    	return driver.getTitle();
		    }
		 return null;
	}
	
	public  String waitForTitleIs(String title,int Timeout)
	{
		 WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(Timeout));
		 if(wait.until(ExpectedConditions.titleIs(title)))   // as until function is not returing boolean
		    {
		    	return driver.getTitle();
		    }
		 return null;
	}
	
	public String doGetText(By locator)
	{
		return getElement(locator).getText();
	}
	
	public  String waitForUrlContains(String urlsubstring,int Timeout)
	{
		 WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(Timeout));
		 if(wait.until(ExpectedConditions.urlContains(urlsubstring)))   // as until function is not returing boolean
		    {
		    	return driver.getCurrentUrl();
		    }
		 return null;
	}
	
	public  String waitForUrlToBe(String url,int Timeout)
	{
		 WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(Timeout));
		 if(wait.until(ExpectedConditions.urlToBe(url)))   // as until function is not returing boolean
		    {
		    	return driver.getCurrentUrl();
		    }
		 return null;
	}
	
	public Alert waitForAlert(int timeout)
	{
		 WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
		 return wait.until(ExpectedConditions.alertIsPresent());     // wait for alert and switch to alert will be carried out
		
	}
	
	public String doGetAlertText(int timeout)
	{
		return waitForAlert(timeout).getText();
		
	}
	
	public void aceeptAlert(int timeout)
	{
		waitForAlert(timeout).accept();
		
	}
	
	public void dismissAlert(int timeout)
	{
		waitForAlert(timeout).dismiss();
		
	}
	
	public void alertSendKeys(String value,int timeout)
	{
		waitForAlert(timeout).sendKeys(value);
		
	}
	
	public void waitForFrameByLocator(By locator,int timeout)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));   // with By Locator
		
	}
	
	public void waitForFrameByLocator(int frameindex,int timeout)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameindex));   // with By Locator
		
	}
	
	public void waitForFrameByLocator(WebElement frameelement,int timeout)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameelement));   // with By Locator
		
	}
	
	public  void clickElementWhenReady(By locator, int timeout)
	{

		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	
	public void clickElementWhenReadyWithPolling(By locator, int timeout, long poll)
	{
		// polling interval being set value from poll variable (in milliseconds), so each attempt to find element would be made after that much seconds
		// by default it is 500 ms
	
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofMillis(poll));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	
	
	public List<WebElement> waitForVisibilityOfElements(By locator, int timeout)
	{
		 WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
		  return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
			
	}
	
	public List<WebElement> waitForPresenceOfElements(By locator, int timeout)
	{
		 WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
		  return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
			
	}
	
	public  void waitForElementPresenseWithFluentWait(By locator, int timeout, int poll, String message)
	{
		Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout)).pollingEvery(Duration.ofMillis(poll))
				.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
				// this method ignores any exception, try to find elem in 10 secs, 
				//during this time if selenium throws this exception, ignore it.
				.withMessage(message);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(locator)).click();
		
	}
	
	public WebElement customWaitretryingElement(WebDriver driver,By locator, int timeout, int polling) throws InterruptedException
	{
		WebElement element= null;
		int attempts=0;
		
		while (attempts<timeout)
		{
			try 
			{
			element=driver.findElement(locator);
			element.click();
			break;
			}
			catch(NoSuchElementException e)
			{
				System.out.println("Element is not found in attempt :"+attempts+"for :"+locator);
				try
				{
					Thread.sleep(polling);
				}
				catch(InterruptedException e1)
				{
					e1.printStackTrace();
				}
				
			}
			attempts++; 
		}
		if(element==null)
		{
			try 
			{
			throw new Exception("Element not found Here");
			}
			catch(Exception e)
			{
				System.out.println("Element is not found in attempt... tried for  :"+timeout+" secs with interval of "+polling+"ms");
			}
		}
		return element;
	}		
	

	
	//*********************************** Wait Utils End********************************************
	
	public  WebElement getElement(By locator)  // taken from LocatorStrategies.java , static is not req becuase we are going to create object of ElementUtil
	{
		return driver.findElement(locator);
	}
	
	public String getAttributeValue(By locator,String atrname)
	{
		return getElement(locator).getAttribute(atrname);
	}
	
	public  void dosendKeys(By locator,String value)  // taken from LocatorStrategies.java , static is not req becuase we are going to create object of ElementUtil
	{
		WebElement elem=getElement(locator);
		elem.clear();
		elem.sendKeys(value);
	}
	
	public boolean doisDisplayed(By locator)
	{
		return getElement(locator).isDisplayed();
	}
	
	public boolean doisEnabled(By locator)
	{
		return getElement(locator).isEnabled();
	}
	
	public  void dosendKeys(String locatortype,String  locatorvalue,String value)  // Overloaded dosendKeys method for string locator scenarios
	{
		getElement(getBy(locatortype, locatorvalue)).sendKeys(value);
	}
	
	public  void dosendKeys(String locatortype,String  locatorvalue)  // Overloaded dosendKeys method for string locator clicking action
	{
		getElement(getBy(locatortype, locatorvalue)).click();
	}
	
	public String checkText(By locator)
	{
		return getElement(locator).getText();
		
	}
	
	public void waitForPageLoad(int timeout)
	{
		long endtime=System.currentTimeMillis()+ timeout;
		
		while (System.currentTimeMillis() <endtime)
		{
			JavascriptExecutor js=(JavascriptExecutor)driver;
			String state=js.executeScript("return document.readyState").toString();
			System.out.println("Current Page State :" + state);
			if(state.equals("complete")) {
				System.out.println("Page is fully loaded now");
				break;
			}
		}
	}

	public void doClick(By locator)
	{
		getElement(locator).click();
	}
	public By getBy(String locatortype, String locatorvalue)  
	{
		By locator=null;
		switch (locatortype.toLowerCase()) {
		case "id": 
			  locator=By.id(locatorvalue);
			  break;
		case "name":
			  locator=By.name(locatorvalue);
			  break;
		case "Classname":
			  locator=By.className(locatorvalue);
			  break;
		case "xpath":
			  locator=By.xpath(locatorvalue);
			  break;
		case "css":
			  locator=By.cssSelector(locatorvalue);
			  break;
		case "linktext":
			  locator=By.cssSelector(locatorvalue);
			  break;
		case "partiallinktext":
			  locator=By.cssSelector(locatorvalue);
			  break;
		case "tagname":
			  locator=By.cssSelector(locatorvalue);
			  break;
		default : 
			  break;
		}	  
		return locator;
			

}
}
