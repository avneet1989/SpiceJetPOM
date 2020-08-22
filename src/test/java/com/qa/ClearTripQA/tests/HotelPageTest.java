package com.qa.ClearTripQA.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.ClearTripQA.base.BasePage;
import com.qa.ClearTripQA.pages.HomePage;
import com.qa.ClearTripQA.pages.HotelPage;
import com.qa.ClearTripQA.pages.LoginPage;
import com.qa.ClearTripQA.utilits.Constants;

public class HotelPageTest {
	
	HomePage homePage;
	 WebDriver driver;
	 BasePage basePage;
	 Properties prop;
	 LoginPage loginPage;
	HotelPage hotelPage; 
	
	@Parameters("browser")
	@BeforeTest
	public void HotelPageSetUp(String browserName) {
	

		basePage= new BasePage();
		prop=basePage.init_prop();
		prop.setProperty("browser", browserName);
		driver=basePage.init_driver(prop);
		loginPage=new LoginPage(driver);	
		hotelPage=loginPage.doSelectAccountforHotel(prop.getProperty("username"),prop.getProperty("password"));
	
	}
	
	@Test(priority=1)
	public void  verifyClickHotel() {
		hotelPage.doClickHotel();		
	}
	@Test(priority=2)
	public void verifyHotelTitle() {
		String title=hotelPage.getHotelPageTitle();
		System.out.println("Hotel Page title is: "+ title);
		Assert.assertEquals(title, Constants.HOTEL_PAGE_TITLE);
	}
	
	@Test(priority=5)
	public void verifyHotelCheckInDate() {
		hotelPage.checkin(prop.getProperty("checkindate"));
	}
	
	@Test(priority=6)
	public void verifyHotelCheckoutDate() {
		hotelPage.checkout(prop.getProperty("checkoutdate"));
	}
	@Test(priority=3)
	public void  verifyPageBanner() {
		String banner=hotelPage.HotelPageBanner();		
		System.out.println(banner);
		Assert.assertEquals(banner, Constants.HOTEL_PAGE_BANNER);
	}
	
	@Test(priority=4)
	public void verifySearchCity() {
		hotelPage.doClickOncity(prop.getProperty("hotelcity"),prop.getProperty("hotellist"));
	}
	

	@Test(priority=7)
	public void verifyTravellersNumbers() {
		hotelPage.doSelectTravellers(prop.getProperty("travellersnu"));
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}	
	
	
	

}
