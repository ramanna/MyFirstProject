package com.ltim.pageobjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[text()='Swag Labs']")
	WebElement hdrSwabLab;
	@FindBy(id = "user-name")
	WebElement txtUserName;
	@FindBy(name = "password")
	WebElement txtPaasword;
	@FindBy(id = "login-button")
	WebElement btnLogin;
	@FindBy(xpath = "//div[contains(@class,'error-message-container error')]")
	WebElement LoginError;
	@FindBy(xpath = "//button[@class='error-button']")
	WebElement btnErrLogin;

	public void checkHeader() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			//WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(hdrSwabLab));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void UserLogin(String userName, String password) {
		try {
			txtUserName.clear();
			txtUserName.sendKeys(userName);
			txtUserName.clear();
			txtPaasword.sendKeys(password);
			btnLogin.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public boolean checkLoginError() {
		if (LoginError.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}
	public void clickErrLogin() {
		btnErrLogin.click();
	}

}// end class
