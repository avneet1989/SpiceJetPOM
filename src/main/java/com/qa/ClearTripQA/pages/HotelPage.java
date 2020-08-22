package com.qa.ClearTripQA.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.ClearTripQA.base.BasePage;
import com.qa.ClearTripQA.utilits.Constants;
import com.qa.ClearTripQA.utilits.ElementUtils;

public class HotelPage extends BasePage{
	
	WebDriver driver;
	ElementUtils elut;
	
	public HotelPage(WebDriver driver) {
		this.driver=driver;
		elut=new ElementUtils(driver);
		
	}
	
	
	
	By Hotellink=By.linkText("Hotels");
	By HotelBanner=By.xpath("//*[@class='searchForm']//h1");
	By cityforhotel= By.xpath("//input[@id='Tags']");
	By cityList=By.xpath("//*[@id='ui-id-1']/li");
	By travellers= By.id("travellersOnhome");//1 room, 2 adults selct
	

	
	public void doClickHotel() {
		driver.navigate().to("https://www.cleartrip.com/hotels");
	}
	public String getHotelPageTitle() {
		return elut.doGetPageTitleWithIsTitle(10, Constants.HOTEL_PAGE_TITLE);
			}
	
	public String HotelPageBanner() {
		if(elut.isElementDisplayed(HotelBanner, 10))
			return elut.doGetText(HotelBanner);
			return null;					
	}
	
	public void doClickOncity(String hotelcity,String hotellist) 
	{
		elut.doClick(cityforhotel);
		elut.doSendKeys(cityforhotel, hotelcity);
		elut.waitForElementPresent(cityList, 10);
		elut.selectValuesFromDropDown(cityList, hotellist);	
		}
	
public void checkin(String checkindate) {
		By calender1=By.id("CheckInDate");
		By calender1Month=By.xpath("(//div[@class='monthBlock first']//span)[1]");
		By calender1arrow=By.xpath("//div[@class='monthBlock last']/div/a");
		By calender1date= By.xpath("//*[@class='monthBlock first']//tbody/tr/td/a");
		
		elut.doActionsClick(calender1);
		String date=checkindate;
		String dateArr[]= date.split("-");
		String exptmonth=dateArr[1];
		String exptyear=dateArr[2];
		String exptdate=dateArr[0];
		while(true) {
			String monthtext=elut.doGetText(calender1Month);
			String year= driver.findElement(By.xpath("(//*[@class='ui-datepicker-year'])[1]")).getText();
			if (monthtext.equals(exptmonth) && year.equals(exptyear))
			{
				break;
			}
			else
			{				
				elut.waitForElementPresent(calender1arrow, 10);
				elut.doClick(calender1arrow);
			}			
		}		
		elut.selectValuesFromDropDown(calender1date, exptdate);
		}
	
public void checkout(String checkoutdate) {
	By calender2=By.id("CheckOutDate");
	By calender2Month=By.xpath("(//div[@class='monthBlock first']//span)[1]");
	By calender2arrow=By.xpath("//div[@class='monthBlock last']/div/a");
	By calender2date= By.xpath("//*[@class='monthBlock first']//tbody/tr/td/a");
	
	elut.doActionsClick(calender2);
	String date=checkoutdate;
	String dateArr[]= date.split("-");
	String exptmonth=dateArr[1];
	String exptyear=dateArr[2];
	String exptdate=dateArr[0];
	while(true) {
		String monthtext=elut.doGetText(calender2Month);
		String year= driver.findElement(By.xpath("(//*[@class='ui-datepicker-year'])[1]")).getText();
		if (monthtext.equals(exptmonth) && year.equals(exptyear))
		{
			break;
		}
		else
		{				
			elut.waitForElementPresent(calender2arrow, 10);
			elut.doClick(calender2arrow);
		}			
	}		
	elut.selectValuesFromDropDown(calender2date, exptdate);
	}

	
	
	public void doSelectTravellers(String travellersnu) {
		elut.doSelectValuesByVisibleText(travellers, travellersnu);
	}
	
	
}
