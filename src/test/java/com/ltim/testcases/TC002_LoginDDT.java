package com.ltim.testcases;

import org.testng.annotations.Test;

import com.ltim.pageobjects.LoginPage;
import com.ltim.testbase.BaseClass1;
import com.ltim.utilities.DataProviders;

public class TC002_LoginDDT extends BaseClass1 {
	LoginPage loginPage;

	@Test(dataProvider="LoginData", dataProviderClass= DataProviders.class, groups={"Regression"})
	public void verify_LoginDDT(String userName, String password, String expResult) throws InterruptedException {
		loginPage = new LoginPage(driver);
		loginPage.UserLogin(userName, password);
		
		if (!loginPage.checkLoginError() && expResult.equalsIgnoreCase("valid")) {
			System.out.println("Login success!!");
			loginPage.checkLoginError();
			driver.navigate().refresh();
			driver.navigate().back();
		} else if (loginPage.checkLoginError() && expResult.equalsIgnoreCase("valid")) {
			System.out.println("Login Not success!!");
		} else if (loginPage.checkLoginError() && expResult.equalsIgnoreCase("invalid")) {
			System.out.println("Login success!!");
		} else if (!loginPage.checkLoginError() && expResult.equalsIgnoreCase("invalid")) {
			System.out.println("Login Not success!!");
			loginPage.checkLoginError();
			driver.navigate().refresh();
			driver.navigate().back();
		}
	}
}


