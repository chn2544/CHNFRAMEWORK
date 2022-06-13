package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class Constants {

	public final static String LOGIN_PAGE_TITLE="Account Login";
	public final static String LOGIN_PAGE_URL_FRACTION="route=account/login";
		
	public final static String ACCOUNTS_PAGE_TITLE="My Account";
	public final static String ACCOUNTS_PAGE_URL_FRACTION="route=account/account";
	public final static String HEADER="TOBEUPDATED";
	
	public final static List<String> EXPECTED_ACC_SEC_LIST=
							Arrays.asList("My Account",
										  "My Orders",
										  "My Affiliate Account",
										  "Newsletter");
	
	public final static String ACCOUNT_LOGOUT="Account Logout";
	public final static String ACCOUNT_CREATION_SUCCESS="Your Account Has Been Created!";
	
	//Sheet Name Constants
	public final static String REGISTRATION_SHEET_NAME="RegistrationNewSheet";	
	public final static String PRODUCT_SHEET_NAME="ProductSheet";	
	
	public final static int DEFAULT_ELEMENT_TIMEOUT=10;
	public final static int DEFAULT_NONELEMENT_TIMEOUT=5;
}
