package com.test.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.helper.ObjectHelper;
import com.test.helper.UserActions;

public class FlightSelectionPage {
	WebDriver driver;
	UserActions user;

	public FlightSelectionPage(UserActions user) {
		this.user = user;
		this.driver = user.getDriver();
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//body/flights-root[1]/div[1]/div[1]/div[1]/div[1]/flights-summary-container[1]/flights-summary[1]/div[1]/div[1]/journey-container[1]/journey[1]/flight-list[1]/div[1]/flight-card[1]/div[1]/div[1]/div[1]/div[1]")
	private WebElement flightSelection;

	@FindBy(xpath = "//button[@class='fare-card__button fare-card__price ry-button--gradient-light-blue']")
	private WebElement regularButton;
	
	@FindBy(xpath = "//button[contains(text(),'Continue')]")
	private WebElement continueButton;

	private WebElement title(Integer occurance) {
		return ObjectHelper.getElement(driver,
				By.xpath("(//button[contains(@class,'dropdown__toggle b2')])["+occurance+"]"));
	}
	
	private WebElement titleValue(String text) {
		return ObjectHelper.getElement(driver,
				By.xpath("//div[.='"+text+"']"));
	}
	
	private WebElement firstName(Integer occurance) {
		return ObjectHelper.getElement(driver,
				By.xpath("//input[@id='formState.passengers.ADT-" + occurance + ".name']"));
	}
	
	private WebElement surName(Integer occurance) {
		return ObjectHelper.getElement(driver,
				By.xpath("//input[@id='formState.passengers.ADT-" + occurance + ".surname']"));
	}
	
	private WebElement childfirstName(Integer occurance) {
		return ObjectHelper.getElement(driver,
				By.xpath("//input[@id='formState.passengers.CHD-" + occurance + ".name']"));
	}
	
	private WebElement childsurName(Integer occurance) {
		return ObjectHelper.getElement(driver,
				By.xpath("//input[@id='formState.passengers.CHD-" + occurance + ".surname']"));
	}
	
	@FindBy(xpath = "//button[@class='seats-modal__cta ry-button--gradient-blue']")
	private WebElement gotItButton;
	
	@FindBy(xpath = "//button[@class='enhanced-takeover-beta__product-dismiss-cta ry-button--anchor-blue ry-button--anchor']")
	private WebElement noThanks;
	
	private WebElement selectSeat(int row, Integer occurance) {
		return ObjectHelper.getElement(driver,
				By.xpath("(//div[contains(@class,'seatmap__seats')]/button[contains(@class,'seat--standard')]/..)["+row+"]/button["+occurance+"]"));
	}
	
	@FindBy(xpath = "//a[@class='basket-tooltip__open-basket']")
	private WebElement checkOutBasket;
	
	@FindBy(xpath = "//button[@data-ref='basket-continue-flow__check-out']")
	private WebElement checkOutButton;
	
	@FindBy(xpath = "//button[@class='ry-button--full ng-tns-c167-1 ry-button--gradient-yellow ry-button--large']")
	private WebElement continuebtn;

	private List<WebElement> seatRows(){
		return driver.findElements(By.xpath("//div[@class='seatmap__seats']/button[contains(@class,'seat--standard')]/.."));
	}
	
	private List<WebElement> unavailableSeats(int row){
		return driver.findElements(By.xpath("(//div[contains(@class,'seatmap__seats')]/button[contains(@class,'seat--standard')]/..)["+row+"]/div[contains(@class,'unavailable')]"));
	}
	
	
	
	
	
	public void flightSelection() {
		user.waitClikable(flightSelection).click();
		user.waitClikable(regularButton).click();
	}
	
	public void enterAdultInfo(int i,String title, String firstname, String lastname) {
		title(i+1).click();
		titleValue(title).click();
		firstName(i).sendKeys(firstname);
		surName(i).sendKeys(lastname);

	}
	
	public void enterChildInfo(int i, String firstname, String lastname) {
		childfirstName(i).sendKeys(firstname);
		childsurName(i).sendKeys(lastname);
		
		
	}
	
	public void clickContinue() {
		//user.waitClikable(continueButton).click();
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueButton);
	}
	
	public void gotItButton() {
		user.waitClikable(gotItButton).click();
	}
	
	public void continueBtn() {
		user.waitClikable(continuebtn).click();
	}
	
	public void noThanksButton() {
		user.waitClikable(noThanks).click();
	}
	
	public void checkOut() {
		user.waitClikable(checkOutBasket).click();
		user.waitClikable(checkOutButton).click();
	}
	
	public void selectSeats(int i) {
		
		List<WebElement> seatrows = seatRows();
		for(int p=1; p<=seatrows.size();p++) {
			if(unavailableSeats(p).size()<=0) {
				for(int j=1;j<=i;j++) {
					//user.waitClikable(selectSeat(j)).click();
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectSeat(p,j));

					System.out.println("seats" +j);
					
				}
				break;
			}
		}
		
	}

}
