package com.test.helper;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.enumerations.Browser;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UserActions {

	WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver launchApplication(Browser browser, String url) {
		switch (browser) {
		case Chrome:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.get(url);
			return driver;
		case Firefox:
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.get(url);
			return driver;
		default:
			return null;
		}

	}
	
	public WebElement waitClikable(WebElement ele) {
		return new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	
	
	public void clearAndSendKeys(WebElement ele,String text) {
		ele.click();
		ele.clear();
		ele.sendKeys(text);
	}
	
	public void waitUntilVisible(WebElement ele) {
		 new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void switchToLatestWindow(WebDriver driver) {
		for(String winHandle :driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
	}
	
	public void switchToWindow(WebDriver driver,String window) {
		driver.switchTo().window(window);
	}
	
	public void closeApplication() {
		driver.quit();
	}
	public String getMonth(String month) {
		HashMap<String, String> map=new HashMap();
		map.put("1","January");
		map.put("2","February");
		return map.get(month);
	}
}
