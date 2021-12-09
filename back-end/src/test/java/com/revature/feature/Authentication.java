package com.revature.feature;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Authentication {
	
	public WebDriver driver;
	@Given("i am at login page")
	public void i_am_at_login_page() {
		
		System.setProperty("webdriver.chrome.driver","/home/gamal/Documents/chromedriver");
		this.driver = new ChromeDriver();
		this.driver.get("http://localhost:5500/index.html");
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	}

	@When("i type username into username input")
	public void i_type_username_into_username_input() {
	    // Write code here that turns the phrase above into concrete actions
		WebElement userName = driver.findElement(By.id("username"));
		userName.sendKeys("gamal");
	   
	}

	@When("i click login-btn")
	public void i_click_login_btn() {
		WebElement loginButton = driver.findElement(By.id("login-btn"));
		loginButton.click();
		
	}

	@Then("Message displayed Incorrect username andor password")
	public void message_displayed_Incorrect_username_and_or_password() {
		WebElement p = driver.findElement(By.tagName("p"));
		String s =" Incorrect username and or password";
		assertEquals(s,p.getText());
		driver.quit();
	   
	}


}
