package com.qa.ClearTripQA.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.ClearTripQA.base.BasePage;
import com.qa.ClearTripQA.utilits.Constants;
import com.qa.ClearTripQA.utilits.ElementUtils;
import com.qa.ClearTripQA.utilits.JavaScriptUtil;


public class HomePage extends BasePage {
	
	WebDriver driver;
	ElementUtils elut;
	JavaScriptUtil js;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		elut=new ElementUtils(driver);
		js= new JavaScriptUtil(driver);
	}
	
	By header=By.xpath("//*[@id='SearchForm']/h1");//Search flights
	By oneWay= By.xpath("//*[@id='OneWay']");
	By from= By.cssSelector("#FromTag");
	By fromcity=By.xpath("//a[text()='Amritsar, IN - Sri Guru Ram Dass Jee (ATQ)']");
	By destination = By.cssSelector("#ToTag");
	By destcity= By.xpath("//a[text()='New Delhi, IN - Indira Gandhi Airport (DEL)']");
	By calender=By.cssSelector("#DepartDate");
	By calenderMonth= By.xpath("(//div[@class='monthBlock first']//span)[1]");
	By calenderarrow=By.xpath("//div[@class='monthBlock last']/div/a");
	By calenderdate= By.xpath("//*[@class='monthBlock first']//tbody/tr/td/a");
	
	By childrennumber	= By.xpath("//*[@id='Childrens']");
	By adultnumber=By.xpath("//*[@id='Adults']");	
	
	By moreOptions=By.xpath("//*[@id='MoreOptionsDiv']//a");
	By class1= By.xpath("//*[@id='Class']");
	
	By currency1=By.xpath("//*[@class='menuItem listMenuContainer currencyMenuContainer']");
	By currencylist= By.xpath("//*[@class='menuItem listMenuContainer currencyMenuContainer']//span");//Canadian Dollar
	By search= By.id("SearchBtn");
	
	
	//*************************Flight Detail page by locator***************

	By searchsummary=By.cssSelector(".searchSummary");
	By price= By.id("BaggageBundlingTemplate");
	
	By flightdetails=By.xpath("//*[@class='booking']");
	By book=By.xpath("//*[@class='booking']");
	
//*******************Information submit Page**********************************
	
	By InformationHeader=By.cssSelector(".colZero.col12");
	By userInfo=By.id("user_name");
	
	By continuebooking= By.id("itineraryBtn");
	
	By Firstadulttitle=By.xpath("//*[@id='AdultTitle1']");
	By FirstName= By.id("AdultFname1");
	By lastName= By.id("AdultLname1");
	By childtile= By.id("ChildTitle1");
	By childfirstname= By.id("ChildFname1");
	By childlast=By.id("ChildLname1");
	By childday=By.id("ChildDobDay1");
	By childmonth= By.id("ChildDobMonth1");
	By childyear=By.id("ChildDobYear1");
	By mobilenumb=By.id("mobileNumber");
	By continuetopay= By.id("travellerBtn");
	By debit=By.xpath("//*[@id='paymentTypes']//a");
	
	
	
	
//******************************************************************************************************************** 

	By frameclose=By.xpath("//*[@id='ModalFrame']//a");
	public void frameclose() {
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='ModalFrame']")));
		if(elut.isElementDisplayed(frameclose, 10)){
		elut.doActionsClick(frameclose);
		driver.switchTo().defaultContent();
	}else {
		driver.switchTo().defaultContent();
	}
	}
	public String getHomePageTitle() {
	return elut.doGetPageTitleWithIsTitle(10, Constants.HOME_PAGE_TITLE);
		}
	
	public String getPageHeader() {
		if(elut.isElementDisplayed(header, 10))
		return elut.doGetText(header);
		return null;
	}
	
	public boolean doSelectOneWay() {
		return driver.findElement(oneWay).isEnabled();
	}
	
	public void selectFrom(String cityfrom) {
		
		elut.doActionsClick(from);
		elut.doSendKeys(from, cityfrom);
		elut.clickWhenReady(fromcity,10);
		}	
	
	public void selectDestination(String destinationcity) {		
		elut.doActionsClick(destination);		
		elut.doSendKeys(destination, destinationcity);
		elut.clickWhenReady(destcity, 10);
		}
	
	public void calenderDate(String flightdate) {
		
		
		elut.doActionsClick(calender);
		String date=flightdate
				;
		String dateArr[]= date.split("-");
		String exptmonth=dateArr[1];
		String exptyear=dateArr[2];
		String exptdate=dateArr[0];
		while(true) {
			String monthtext=elut.doGetText(calenderMonth);
			String year= driver.findElement(By.xpath("(//*[@class='ui-datepicker-year'])[1]")).getText();
			if (monthtext.equals(exptmonth) && year.equals(exptyear))
			{
				break;
			}
			else
			{				
				elut.waitForElementPresent(calenderarrow, 10);
				elut.doClick(calenderarrow);
			}			
		}		
		elut.selectValuesFromDropDown(calenderdate, exptdate);
		}
 	
	public void adult(String adult) {		
		elut.waitForElementToBeClickable(adultnumber, 10);				
		elut.doSelectValuesByVisibleText(adultnumber, adult);	
	}
	
	public void children(String children) {		
		elut.waitForElementToBeClickable(childrennumber, 10);				
		elut.doSelectValuesByVisibleText(childrennumber, children);		
	}
	public void moreOptions(String flightclass) {
		elut.doClick(moreOptions);
		elut.waitForElementPresent(class1, 10);
		elut.doSelectValuesByVisibleText(class1, flightclass);	
	}
	
	public void checkCurrencySelected(String currency) {
		elut.doClick(currency1);
		elut.selectValuesFromDropDown(currencylist, currency);		
	}
	
	public void doClickSearch() {
		elut.doClick(search);
		
	}

//****************************flight Detail Page********************
//******************************************************************
	public String getFlightPrice() {		
		return elut.doGetText(price);
	}
	
	public String getSearchSummary() {
		if(elut.isElementDisplayed(searchsummary, 10))
			return elut.doGetText(searchsummary);
			return null;
			}
	
	public void getFlightmoreInformation() {
		 elut.doClick(flightdetails);		
	}
	
	public void getFlightDetails() {
		elut.doClick(flightdetails);
	}	

	public void clickbook() {
		elut.doClick(book);
	}
//*****************************************************************	
	
	public String getInformationHeader() {
		if(elut.isElementDisplayed(InformationHeader, 10))
			return elut.doGetText(InformationHeader);
			return null;
			
	}
	
	public String getUserInfo() {
		if(elut.isElementDisplayed(userInfo, 10))
			return elut.doGetText(userInfo);
			return null;			
	}
	
	
	
	public void continuebooking() {
		if(elut.isElementDisplayed(continuebooking, 10))
			elut.doClick(continuebooking);
	}
	
	public void insertAdultandchildname(String firstadultname,String childname, String childdob,String mobilenu) {
		String firstadult=firstadultname;
		String adult1Arr[]=firstadult.split(" ");
		String adult1title=adult1Arr[0];
		String adult1name=adult1Arr[1];
		String adult1last=adult1Arr[2];
		
		String firstchildname=childname;
		String child1Arr[]=firstchildname.split(" ");
		String child1title=child1Arr[0];
		String child1name=child1Arr[1];
		String child1last=child1Arr[2];
		
		String childdb=childdob;
		String childdb1Arr[]=childdb.split("-");
		String child1day=childdb1Arr[0];
		String child1month=childdb1Arr[1];
		String child1year=childdb1Arr[2];
		
		
		
		elut.waitForElementPresent(Firstadulttitle, 10);
		elut.doSelectValuesByVisibleText(Firstadulttitle, adult1title);
		elut.doSendKeys(FirstName, adult1name);
		elut.doSendKeys(lastName, adult1last);
		elut.doSelectValuesByVisibleText(childtile, child1title);
		elut.doSendKeys(childfirstname, child1name);
		elut.doSendKeys(childlast, child1last);
		elut.doSelectValuesByVisibleText(childday, child1day);
		elut.doSelectValuesByVisibleText(childmonth, child1month);
		elut.doSelectValuesByVisibleText(childyear, child1year);
		driver.findElement(mobilenumb).clear();
		elut.doSendKeys(mobilenumb, mobilenu);
		elut.doClick(continuetopay);
	
}
	public void doClickDebit(String paymentmethod) {
		elut.waitForElementPresent(debit, 10);
		elut.selectValuesFromDropDown(debit, "paymentmethod");
	}
	

}
