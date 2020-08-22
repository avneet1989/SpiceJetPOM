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
import com.qa.ClearTripQA.pages.LoginPage;
import com.qa.ClearTripQA.utilits.Constants;

public class HomePageTest {	
	
	 HomePage homePage;
	 WebDriver driver;
	 BasePage basePage;
	 Properties prop;
	 LoginPage loginPage;
	
	@Parameters("browser")	
	@BeforeTest
		public void HomePageSetUp(String browserName) {
		basePage= new BasePage();
		prop=basePage.init_prop();
		prop.setProperty("browser", browserName);
		driver=basePage.init_driver(prop);
		loginPage=new LoginPage(driver);	
	homePage=loginPage.doSelectAccount(prop.getProperty("username"),prop.getProperty("password"));
	
	}
	
	
	@Test(priority=1)
	public void verfiyHomePageTitle() {
		String titletext=homePage.getHomePageTitle();
		System.out.println("Home page title is:"+ titletext);
		Assert.assertEquals(titletext,Constants.HOME_PAGE_TITLE);		
	}
	
	@Test(priority=2)
	public void verifyPageHeader() {
		String titletext=homePage.getPageHeader();
		System.out.println("Home page header:"+ titletext);
		Assert.assertEquals(titletext, Constants.HOME_PAGE_HEADER);			
	}
	
	
	@Test(priority=3)
	public void verifyOneWay() {
	boolean oneway=homePage.doSelectOneWay();
	Assert.assertEquals(oneway, true);
	}
	
	@Test(priority=4)
	public void verifyselectFrom() {
		homePage.selectFrom(prop.getProperty("cityfrom"));
	}
	
	@Test(priority=5)
	public void verifySelectDestination() {
	homePage.selectDestination(prop.getProperty("destinationcity"));
	}
	
	@Test(priority=6)
	public void verifycalenderDate() {
		homePage.calenderDate(prop.getProperty("flightdate"));
	}
	
	@Test(priority=7)
	public void verifyAdult() {
		homePage.adult(prop.getProperty("adult"));
	}
	
	@Test(priority=8)
	public void verifyChildren() {
		homePage.children(prop.getProperty("children"));
	}
	@Test(priority=9)
	public void verifyMoreOption() {
		homePage.moreOptions(prop.getProperty("flightclass"));
		}
	
	@Test(priority=10)
	public void verifyCurrencySelector() {
		homePage.checkCurrencySelected(prop.getProperty("currency"));
	}
	
	@Test(priority=11)
	public void verifyClickSearch() {
		homePage.doClickSearch();
	}
	
	
	
	//*************************************Flight Details test*********************
	
	
	@Test(priority=12)
	public void verifyFlightSearchSummary() {
		String summarytext=homePage.getSearchSummary();
		System.out.println("Flight details page search summary is:"+ summarytext);
		
	}
	
	
	@Test(priority=13)
	public void verfiyPriceticket() {
		String pricetext=homePage.getFlightPrice();
		System.out.println(pricetext);
	}
	
	
	@Test(priority=14)
	public void verfiyflightInformation() {
		homePage.getFlightmoreInformation();	
	}
	

	
	@Test(priority=17)
	public void verifyBookFlight() {
		homePage.clickbook();
	}
	
	
	
//****************************************************
	
	
	@Test(priority=18)
	public void verifyInformationheader() {
		String text=homePage.getInformationHeader();
		System.out.println("Information header is: "+ text);
		Assert.assertEquals(text, Constants.CUSTOMER_INFORMATION_PAGE_HEADER);
	}
	
	@Test(priority=19)
	public void verifyGetuserInfo() {
		String textuser=homePage.getUserInfo();
		System.out.println("userInfo is: "+textuser);
		
	}
	
	
	
	@Test(priority=21)
	public void verifyContinueBooking() {
		homePage.continuebooking();
		
	}
	
	@Test(priority=22)
	public void verifyInsertInformation() {
		homePage.insertAdultandchildname(prop.getProperty("1adultname"),prop.getProperty("childname"),prop.getProperty("childdob"),prop.getProperty("mobilenu"));
	}
	
	@Test(priority=22)
	public void verifyPaymentMethod() {
	homePage.doClickDebit(prop.getProperty("paymentmethod"));
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	
}
