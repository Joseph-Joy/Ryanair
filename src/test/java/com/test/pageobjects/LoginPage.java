package com.test.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.helper.UserActions;

public class LoginPage {
	WebDriver driver;
	UserActions user;

	public LoginPage(UserActions user) {
		this.user = user;
		this.driver = user.getDriver();
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@name='identifier']")
	private WebElement user_name;

	@FindBy(name = "email")
	private WebElement email_username;

	

	@FindBy(xpath = "//span[text()='Log in']")
	private WebElement login_link;

	@FindBy(xpath = "//img[@class='image-wrapper__image' and @alt='Google']")
	private WebElement google_login_link;

	@FindBy(xpath = "(//div[@class='VfPpkd-RLmnJb'][1])")
	private WebElement nextButton;

	@FindBy(xpath = "(//input[@name='password'])")
	private WebElement password_input;

	@FindBy(xpath = "(//div[@class='VfPpkd-RLmnJb'][1])")
	private WebElement login;

	@FindBy(xpath = "//input[@name='email']")
	private WebElement username;

	@FindBy(xpath = "//input[@name='password']")
	private WebElement password;

	
	@FindBy(xpath="//span[contains(@class,'avatar')]")
	private WebElement avatar;
	
	
	

	@FindBy(xpath = "//button[@class='dropdown__toggle b2']")
	private WebElement salutation;

	@FindBy(xpath = "//input[@id='formState.passengers.ADT-0.name']")
	private WebElement firstName;

	@FindBy(xpath = "//input[@id='formState.passengers.ADT-0.surname']")
	private WebElement lastName;

	public void performLogin(WebDriver driver, String username,String password) {
		login_link.click();
		user.waitClikable(google_login_link).click();
		String parentWindow = driver.getWindowHandle();
		user.switchToLatestWindow(driver);
		user_name.click();
		user_name.sendKeys(username);
		nextButton.click();
		password_input.click();
		password_input.sendKeys(password);
		user.waitClikable(login).click();

		user.switchToWindow(driver, parentWindow);
		
		user.waitUntilVisible(avatar);
		
	}

	/*
	public void login(String Uname, String Pass, String Depart, String DestCountry, String DestAirport)
			throws InterruptedException {

		Thread.sleep(10000);

		Thread.sleep(7000);

		// Perform the click operation that opens new window

		// Switch to new window opened
		user.switchToLatestWindow(driver);

		user_name.click();
		user_name.sendKeys(Uname);
		NextButton.click();
		Thread.sleep(3000);
		password.sendKeys(Pass);

		driver.switchTo().defaultContent();
		Login.click();

		driver.switchTo().window(winHandleBefore);

		Thread.sleep(3000);
		CookieAccept.click();
		OneWayCheckBox.click();
		Thread.sleep(3000);
		Departure.click();
		Departure.clear();

		Thread.sleep(3000);
		DepartureSelection.click();
		Thread.sleep(5000);

		DestCuntrySelection.click();
		DestAirportSelection.click();
		Thread.sleep(5000);
		if (TravelDate.isEnabled()) // Not Working
		{
			TravelDate.click();
		} else
			System.out.println("InvalidDate Selection");

		SearchButton.click();
		Thread.sleep(5000);

		// Second Page

		Thread.sleep(5000);
		FlightSelection.click();
		Thread.sleep(3000);
		RegularButton.click();

	}
	*/
}
