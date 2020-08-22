package com.qa.ClearTripQA.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.ClearTripQA.utilits.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	/**
	 * This method is used to initialize the driver on the basis of given browser name
	 * @param browserName
	 * @return driver
	 */	
	
public WebDriver init_driver(Properties prop) {
		
		String browserName = prop.getProperty("browser").trim();
		
		System.out.println("browser name is : " + browserName);
		optionsManager=new OptionsManager(prop);
		
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver= new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		
		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver= new FirefoxDriver(optionsManager.getfirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getfirefoxOptions()));
		}
		
		else if (browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			//driver= new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		
		else {
				
				System.out.println("please pass the correct driver name= "+ browserName);
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		getDriver().get(prop.getProperty("url"));
		return getDriver();
		
	}

public static synchronized WebDriver getDriver(){
	return tlDriver.get();
}

	public Properties init_prop() {
		
		prop= new Properties();
		String path=null;
		String env=null;
		
		try {
			env=System.getProperty("env");
			System.out.println("Running on Environment: ----->" + env);
			System.out.println();
			if(env==null)
			{
				path="C:\\Users\\14387\\eclipse-workspaceNew\\ClearTripQA\\src\\main\\java\\com\\qa\\ClearTripQA\\config\\config.properties";
			}
			else {
				switch(env) {
				case"qa":
					path="C:\\Users\\14387\\eclipse-workspaceNew\\ClearTripQA\\src\\main\\java\\com\\qa\\ClearTripQA\\config\\qa.config.properties";
				break;
				case"dev":
					path="C:\\Users\\14387\\eclipse-workspaceNew\\ClearTripQA\\src\\main\\java\\com\\qa\\ClearTripQA\\config\\dev.config.properties";
				break;
				case"stage":
					path="C:\\Users\\14387\\eclipse-workspaceNew\\ClearTripQA\\src\\main\\java\\com\\qa\\ClearTripQA\\config\\stage.config.properties";
				break;
				default:
				System.out.println("Please pass the corect env value....");
				break;
				}
			}
			FileInputStream ip= new FileInputStream(path);
			prop.load(ip);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	return prop;
	
	}
	
	
	
	
	public String getScreenshot(){
		File src  = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
}
