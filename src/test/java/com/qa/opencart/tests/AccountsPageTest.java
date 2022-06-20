package com.qa.opencart.tests;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constants;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.utils.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic - 200 - Account Page for Open Cart App")
@Story("Desing Account Page with Various Features")
public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accSetup()
	{
		ap=lp.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());    // till demo.opencart id down
		ap=new AccountsPage(driver);
	}
	
	@Description("Account Page Title Test...")
	@Severity(SeverityLevel.MINOR)
	@Test (priority = 1)
	public void accPageTitleTest()
	{
		String title= ap.getAccountsPageTitle();
		System.out.println("Account Page Title is :"+title);
		Assert.assertEquals(title, Constants.ACCOUNTS_PAGE_TITLE);
	}

	@Test (priority = 2)
	public void accPageURLTest()
	{
		String url=ap.getAccountsPageURL();
		System.out.println("Account Page URL is :"+url);
		Assert.assertTrue(url.contains(Constants.ACCOUNTS_PAGE_URL_FRACTION));
	}
	
//	@Test
//	public void accPageHeaderTest()
//	{
//		Assert.assertEquals(ap.getAccountsPageHeader(),Constants.HEADER);
//	}
	
	@Test (priority = 3)
	public void accPageSectionList()
	{
		List<String> acc_list=ap.getAccountsPageSectionsList();
		System.out.println("List of Sections :"+acc_list);
		Assert.assertEquals(acc_list, Constants.EXPECTED_ACC_SEC_LIST);
	}
	
	@Description("Logout Page Title Test...")
	@Severity(SeverityLevel.CRITICAL)
	@Test (priority = 4)
	public void logoutlinkTest()
	{
		Assert.assertTrue(ap.isLogoutLinkExist());
	}
	
	@Test (priority = 5)
	public void searchboxTest()
	{
		Assert.assertTrue(ap.isSearchExist());
	}
	
	@DataProvider
	public Object[][] getSearchKey()
	{
		return new Object[][] {
			{"MacBook"},
			{"Apple"},
			{"Samsung"},
			{"CHIN"},        // This search will get failed
			{"iMac"}
		};
	}
	
	@Test (priority = 6, dataProvider ="getSearchKey")
	public void searchTest(String searchval)
	{
		srp=ap.doSearch(searchval);    // this will return SearchResultsPage object, so we can store in object
		Assert.assertTrue(srp.searchResultsCount()>0);  // if count is greater than 0 , test will pass, else it will fail
	}
	
	@DataProvider
	public Object[][] getProductName()
	{
		return new Object[][] {
			{"MacBook", "MacBook Pro"},
			{"Apple", "Apple Cinema 30\""},
			{"Samsung","Samsung Galaxy Tab 10.1"},
			{"CHIN","CHINPROD"},        // This search will get failed
			{"iMac","iMac"}
		};
	}
	
	@Test(priority = 7,dataProvider ="getProductName",enabled=false)
	public void selectProductTest(String searchval, String prodname)
	{
		srp=ap.doSearch(searchval);
		pi=srp.selectProduct(prodname);
		String prodheadername=pi.getProductHeaderName();
		System.out.println(prodheadername);
		Assert.assertEquals(prodheadername, prodname);
	
	}
	
	@DataProvider
	public Object[][] gerProductNamefromExcel()
	{
		Object[][] regdata= ExcelUtil.readTestData(Constants.PRODUCT_SHEET_NAME);
		   return regdata;
	}
	
	@Test(priority = 7,dataProvider ="gerProductNamefromExcel",enabled=false)
	public void selectProductTestfromExcel(String searchval, String prodname)
	{
		srp=ap.doSearch(searchval);
		pi=srp.selectProduct(prodname);
		String prodheadername=pi.getProductHeaderName();
		System.out.println(prodheadername);
		Assert.assertEquals(prodheadername, prodname);
		
	}
	
	@DataProvider
	public Object[][] getProductImgData()
	{
		return new Object[][] {
			{"MacBook", "MacBook Pro",4},
			{"Apple", "Apple Cinema 30\"",6},
			{"Samsung","Samsung Galaxy Tab 10.1",7},
			{"iMac","iMac",3}
		};
	}
	
	@Test(priority = 8,dataProvider ="getProductImgData")
	public void selectedProductImageTest(String searchval, String prodname,int prodimgcount)
	{
		srp=ap.doSearch(searchval);
		pi=srp.selectProduct(prodname);
		Assert.assertEquals(pi.getImageCount(),prodimgcount);
		
	}
	
	@DataProvider
	public Object[][] getProductInfoData()
	{
		return new Object[][] {
			{"MacBook", "MacBook Pro","Name","MacBook Pro"},
			{"Apple", "Apple Cinema 30\"","Brand","Apple"},
			{"Samsung","Samsung Galaxy Tab 10.1","price","$2,000.00"},            // failing condition
			{"iMac","iMac","price","$122.00"}                                     // failing condition    //correct 122 , wrong 154
		};
	}
	
	@Test(priority = 9,dataProvider ="getProductInfoData")
	public void productInfoTest(String searchval, String prodname,String key,String value)
	{
		srp=ap.doSearch(searchval);
		pi=srp.selectProduct(prodname);
		Map <String, String> accProductInfo=pi.getProductInformation();
		sa.assertEquals(accProductInfo.get(key),value);         // softassert
		sa.assertAll();
	}
	
	@Test(priority = 10,enabled=false)
	public void productInfoDescriptionTest()                // Content Testing
	{
		srp=ap.doSearch("MacBook");
		pi=srp.selectProduct("MacBook Pro");
		sa.assertTrue((pi.getProductInfoPageInnerText().contains("FireWire 800 and DVI")));
		sa.assertTrue((pi.getProductInfoPageInnerText().contains("802.11n")));
		sa.assertTrue((pi.getProductInfoPageInnerText().contains("NVIDIA GeForce 8600M GT")));
//		sa.assertTrue((pi.getProductInfoPageInnerText().contains("NVIDIA GeForce 8600M GT22")));   // For Failing Conditon
		sa.assertAll();
	}
	
	@Test(priority = 11,enabled=false)
	public void addToCartTest()       // Need to Write Data Provider for This
	{
		srp=ap.doSearch("MacBook");
		pi=srp.selectProduct("MacBook Pro");
		String successMsg=pi
			.enterQuantity("2")
			                 .clickOnAddToCart()
			                                  .getCartSuccessMessage();       
		//  hence we have written return this in enterQuantity and clickOnAddToCart methods for builder pattern
		System.out.println(successMsg);
		Assert.assertTrue(successMsg.contains("MacBook Pro"));
//		Assert.assertTrue(successMsg.contains("iMac"));                                       // Failing Condition
		sa.assertTrue(pi.getCartItemText().contains("2"+" item(s)"));                    
//		sa.assertTrue(pi.getCartItemText().contains("3"+" item(s)"));                     // Failing Condition
		sa.assertAll();
		
	}
	
	
	
	
	
	
	
	
	
	@Test (enabled = false)
	public void logoutTest()
	{
		Assert.assertEquals(ap.doLogout().getLogoutSuccessMessage(),Constants.ACCOUNT_LOGOUT);
	}
	
	
	
}
