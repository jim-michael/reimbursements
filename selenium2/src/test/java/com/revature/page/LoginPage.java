package com.revature.page;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	private WebDriver driver;
	private WebDriverWait wdw;
	
	@FindBy(xpath ="//input[@id='username']")
	private WebElement usernameInput;
	
	@FindBy(id="password")
	private WebElement passwordInput;
	
	@FindBy(id="login-btn")
	private WebElement loginButton;
	
	@FindBy(xpath ="//div[@id='login-info']/p")
	private WebElement errorMessage;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wdw = new WebDriverWait(driver,Duration.ofSeconds(15));
		PageFactory.initElements(driver,this);
	}
	
	public WebElement getUsernameInput() {
		return this.usernameInput;
	}
	
	public WebElement getPasswordInput() {
		return passwordInput;
	}
	public WebElement getLoginButton() {
		return loginButton;
	}

	public WebElement getErrorMessage() {
		return this.wdw.until(ExpectedConditions.visibilityOf(this.errorMessage));
	}
		

}
