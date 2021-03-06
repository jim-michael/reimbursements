package com.revature.page;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Regular {
	private WebDriver driver;
	private WebDriverWait wdw;
	@FindBy(xpath="//*[text()='Welcome to the Associate homepage']")
	private WebElement welcomeHeading;
	
	public Regular(WebDriver driver) {
		this.driver = driver;
		this.wdw= new WebDriverWait(driver,Duration.ofSeconds(15));
		PageFactory.initElements(driver,this);
		
	}
	
	public WebElement getWelcomeHeading() {
		return this.wdw.until(ExpectedConditions.visibilityOf(welcomeHeading));
	}

}
