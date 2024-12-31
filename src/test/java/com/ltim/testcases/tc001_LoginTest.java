package com.ltim.testcases;

import org.testng.annotations.Test;

import com.ltim.pageobjects.LoginPage;
import com.ltim.testbase.BaseClass1;

public class tc001_LoginTest extends BaseClass1{
	private LoginPage loginPage;
	
	@Test(groups= {"Sanity", "Regression"})
	public void verifyUserLogin() {
		loginPage = new LoginPage(driver);
		logger.info("*****starting tc001_LoginTest *****");
		loginPage.checkHeader();
		loginPage.UserLogin(prop.getProperty("userName"), prop.getProperty("password"));
		logger.info("*****Finished tc001_LoginTest *****");
	}
	
}//end class


