package com.test.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.test.helper.ObjectHelper;
import com.test.helper.UserActions;

public class PaymentPage {
	WebDriver driver;
	UserActions user;
	
	public PaymentPage(UserActions user) {
		this.user = user;
		this.driver = user.getDriver();
		PageFactory.initElements(driver, this);
		
	}
	
	
	
	@FindBy(xpath = "//label[contains(text(), 'Card number')]")
	private WebElement cardNumber;
	
	@FindBy(xpath = "//label[contains(text(), 'Month')]")
	private WebElement cardExpiryMonth;
	
	@FindBy(xpath = "//label[contains(text(), 'Year')]")
	private WebElement cardExpiryYear;
	
	@FindBy(xpath = "//input[@placeholder='CVV']")
	private WebElement cvv;
	
	
	@FindBy(xpath = "//button[contains(text(),'Pay now')]")
	private WebElement payNow;
	
	@FindBy(xpath = "(//input[@id='insurance-opt-out']/following-sibling::div)[1]")
	private WebElement optOutInsurance;
	
	
	@FindBy(xpath = "//input[@id='termsAndConditions']")
	private WebElement termsAndConditions;
	
	
	private WebElement Spancurrency(String countryCode) {
		return ObjectHelper.getElement(driver,
				By.xpath("//span[contains(text(),'"+countryCode+"')]"));
	}
	public WebElement Divcurrency(String occurance) {
		return ObjectHelper.getElement(driver,
				By.xpath("//div[contains(text(),'"+occurance+"')]"));
	}
	
	private WebElement formFields(Integer occurance) {
		return ObjectHelper.getElement(driver,
				By.xpath("(//input[@name='undefined'])["+occurance+"]"));
	}
	private WebElement expiryDate(Integer occurance) {
		return ObjectHelper.getElement(driver,
				By.xpath("(//button[@class='dropdown__toggle b2'])["+occurance+"]"));
	}
	
	private WebElement selectMonth(String month) {
		return ObjectHelper.getElement(driver,
				By.xpath("//div[contains(text(),'"+month+"')]"));
	
	}
	
	private WebElement country() {
		return ObjectHelper.getElement(driver,
				By.xpath("//input[contains(@class,'b2 _autocomplete_input _autocomplete_input--dropdown')]"));
	}
	
	
	public void enterPhoneNumber(String number)
	{
		user.waitClikable(formFields(1)).click();
		formFields(1).sendKeys(number);
		optOutInsurance.click();
	}
	public void payNow()
	{
		 payNow.click();

	}
	
	
	
	public void enterFormFields(String cardNo, String expiryDate,String CVV, String name,String add1,String city,String country,String postalCode) throws InterruptedException {

		formFields(2).sendKeys(cardNo);
		formFields(3).sendKeys(CVV);
		formFields(4).sendKeys(name);
		formFields(5).sendKeys(add1);
		//formFields(7).sendKeys(add2);
		formFields(7).sendKeys(city);
		 String expiry[]=expiryDate.split("/");
		 user.waitClikable(expiryDate(1)).click();
		 user.waitClikable(selectMonth(user.getMonth(expiry[0]))).click();
		 user.waitClikable(expiryDate(2)).click();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();",selectMonth("20"+expiry[1]));

				 country().sendKeys(country);
				 Thread.sleep(5000);
				 country().sendKeys(Keys.ARROW_DOWN);
				 country().sendKeys(Keys.ENTER);
				 user.waitClikable(formFields(8)).click();	
				 formFields(8).sendKeys(postalCode);	
				 user.waitClikable(Spancurrency("Select a currency")).click();
				 Thread.sleep(5000);
				((JavascriptExecutor) driver).executeScript("arguments[0].click();",Spancurrency("Select a currency"));

				((JavascriptExecutor) driver).executeScript("arguments[0].click();",Divcurrency("EUR"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();",termsAndConditions);
			//	termsAndConditions.click();

	}
	
}

