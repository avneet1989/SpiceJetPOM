package com.qa.ClearTripQA.tests;


import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.ClearTripQA.base.BasePage;
import com.qa.ClearTripQA.base.BaseTest;
import com.qa.ClearTripQA.pages.LoginPage;



public class LoginPageTest extends BaseTest {
	
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	
	@Parameters("browser")
	@BeforeTest
	public void setUp(String browserName) {
		
		basePage= new BasePage();
		prop=basePage.init_prop();
		prop.setProperty("browser", browserName);
		driver=basePage.init_driver(prop);
		loginPage=new LoginPage(driver);			
	}
	
	
	@Test
	public void verifyLoginPage() {
		
		loginPage.doSelectAccount(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
