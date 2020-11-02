package com.test.pageobjects;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.helper.ObjectHelper;
import com.test.helper.UserActions;

public class HomePage {

	WebDriver driver;
	UserActions user;

	public HomePage(UserActions user) {
		this.user = user;
		this.driver = user.getDriver();
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@class='cookie-popup__button'][2]")
	private WebElement cookieAccept;
	
	@FindBy(xpath = "//body/hp-app-root[1]/hp-home-container[1]/hp-home[1]/hp-search-widget-container[1]/hp-search-widget[1]/div[1]/hp-flight-search-widget-container[1]/fsw-flight-search-widget-container[1]/fsw-flight-search-widget[1]/fsw-trip-type-container[1]/fsw-trip-type[1]/fsw-trip-type-button[2]/button[1]/icon[1]/span[1]/*[1]")
	private WebElement oneWayCheckBox;

	@FindBy(xpath = "//input[@id='input-button__departure']")
	private WebElement departure;

	private WebElement selectAirport(String departure) {
		return ObjectHelper.getElement(driver, By.xpath("//span[contains(text(),'"+departure+"')]"));
	}
	
	@FindBy(xpath = "//input[@placeholder='Destination']")
	private WebElement destination;

	

	@FindBy(xpath = "//span[contains(text(),'London Stansted')]") // parameterizse the location
	private WebElement destAirportSelection;

	
	private WebElement travelDate(String date) {
		return ObjectHelper.getElement(driver, By.xpath("//div[@data-id = '"+date+"']"));
	}
	
	@FindBy(xpath="//div[text()='Adults']/../..//ry-counter-button[@data-ref='counter.counter__increment']/..")
	private WebElement adultIncrementor;
	
	@FindBy(xpath="//div[text()='Adults']/../..//div[@data-ref='counter.counter__value']")
	private WebElement adultCounterValue;
	
	@FindBy(xpath="//div[text()='Children']/../..//ry-counter-button[@data-ref='counter.counter__increment']/..")
	private WebElement childIncrementor;
	
	@FindBy(xpath="//div[text()='Children']/../..//div[@data-ref='counter.counter__value']")
	private WebElement childCounterValue;
	
	
	@FindBy(xpath = "//body/ry-tooltip[@id='ry-tooltip-9']/div[2]/hp-app-controls-tooltips[1]/fsw-controls-tooltips-container[1]/fsw-controls-tooltips[1]/fsw-passengers-container[1]/fsw-passengers[1]/fsw-passengers-picker-container[1]/fsw-passengers-picker[1]/ry-counter[1]/div[2]/div[3]/ry-counter-button[1]")
	private WebElement adults;

	@FindBy(xpath = "//button[@class = 'flight-search-widget__start-search ry-button--gradient-yellow ng-trigger ng-trigger-collapseExpandCta']")
	private WebElement searchButton;

	public void searchFlights(String from, String to,String date,int nadults,int nchilds) {
		cookieAccept.click();
		user.waitClikable(oneWayCheckBox).click();
		user.clearAndSendKeys(departure, from);
		selectAirport(from).click();
		user.clearAndSendKeys(destination, to);
		selectAirport(to).click();
		String availablity = travelDate(date).getAttribute("class");
		assertTrue("Travel Date is not available for booking",!availablity.contains("disabled"));
		travelDate(date).click();
		user.waitUntilVisible(adultCounterValue);
		while(Integer.parseInt(adultCounterValue.getText())!=nadults) {
			user.waitClikable(adultIncrementor).click();
		}
		user.waitUntilVisible(childCounterValue);
		while(Integer.parseInt(childCounterValue.getText())!=nchilds) {
			user.waitClikable(childIncrementor).click();
		}
		searchButton.click();
		
	}
	
}
