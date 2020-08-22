package com.qa.ClearTripQA.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver
;

import com.qa.ClearTripQA.base.BasePage;
import com.qa.ClearTripQA.utilits.ElementUtils;

public class LoginPage extends BasePage {
	
	private WebDriver driver;
	ElementUtils elut;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		elut=new ElementUtils(driver);
		
	}
	
	By yourtripaccount= By.id("userAccountLink");
	By signIn= By.id("SignIn");
	
	By email=By.cssSelector("#email");
	By password=By.cssSelector("#password");
	By sign_In=By.id("signInButton");
	By frameId=By.id("ContentFrame");
	
	

	
	public HomePage doSelectAccount(String username, String pass) {
		elut.doActionsClick(yourtripaccount);
		elut.waitForElementToBeClickable(signIn, 10);
		elut.doActionsClick(signIn);
		driver.switchTo().frame(driver.findElement(By.id("modal_window")));
		elut.doSendKeys(email, username);
		elut.doSendKeys(password, pass);
		elut.doClick(sign_In);
		driver.switchTo().defaultContent();
				
		return new HomePage(driver);
	}
	public HotelPage doSelectAccountforHotel(String username, String pass) {
		elut.doActionsClick(yourtripaccount);
		elut.waitForElementToBeClickable(signIn, 10);
		elut.doActionsClick(signIn);
		driver.switchTo().frame(driver.findElement(By.id("modal_window")));
		elut.doSendKeys(email, username);
		elut.doSendKeys(password, pass);
		elut.doClick(sign_In);
		driver.switchTo().defaultContent();
				
		return new HotelPage(driver);
	}
	

}
