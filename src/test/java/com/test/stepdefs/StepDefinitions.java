package com.test.stepdefs;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.test.helper.UserActions;
import com.test.pageobjects.FlightSelectionPage;
import com.test.pageobjects.HomePage;
import com.test.pageobjects.LoginPage;
import com.test.pageobjects.PaymentPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class StepDefinitions {
	UserActions user;
	WebDriver driver;
	FlightSelectionPage flightSelectionPage;
	PaymentPage payment;
	int sum_of_passengers = 0;
	int no_of_children=0;

	public StepDefinitions(UserActions user) {
		this.user = user;
		this.driver = user.getDriver();
	}

	@Given("Login with gmail {string} and {string}")
	public void login_with_gmail(String username, String password) {
		LoginPage loginPage = new LoginPage(user);
		loginPage.performLogin(driver, username, password);
	}

	@Given("I search filghts from {string} to {string} on {string} for {int} adults and {int} child")
	public void i_search_filghts_from_to_on_for_adults_and_child(String from, String to, String date, Integer nadults,
			Integer nchilds) {
		sum_of_passengers = nadults+nchilds;
		no_of_children = nchilds;
		HomePage homepage = new HomePage(user);
		homepage.searchFlights(from, to, date, nadults, nchilds);
	}

	@And("I select the flight")
	public void i_select_the_flight_and_proceed() {
		flightSelectionPage = new FlightSelectionPage(user);
		flightSelectionPage.flightSelection();
	}
	
	@And("I enter the adults information")
	public void i_enter_the_adults_information(DataTable dataTable) {
		List<List<String>> rows = dataTable.asLists(String.class);
		int i = 0;
		for (List<String> columns : rows) {
			System.out.println("Counter "+i);
			flightSelectionPage.enterAdultInfo(i, columns.get(0), columns.get(1), columns.get(2));
			i++;
		}
		
		
	}

	@And("I enter the child information")
	public void i_enter_the_child_information(DataTable dataTable) {
		List<List<String>> rows = dataTable.asLists(String.class);
		int i = 0;
		for (List<String> columns : rows) {
			System.out.println("Counter "+i);
			flightSelectionPage.enterChildInfo(i, columns.get(0), columns.get(1));
			i++;
		}
		
	}

	

	@And("I proceed to continue and checkout")
	public void i_proceed_to_continue_and_checkout(){
		flightSelectionPage.clickContinue();
		if (no_of_children > 0 ) {
			flightSelectionPage.gotItButton();	
		}
			
		flightSelectionPage.selectSeats(sum_of_passengers);
		flightSelectionPage.clickContinue();
		flightSelectionPage.noThanksButton();
		flightSelectionPage.clickContinue();
		flightSelectionPage.continueBtn();
		flightSelectionPage.checkOut();
	}


	


@When("I pay for booking with card details {string},{string}, {string} and {string} for {string} , {string} , {string} , {string}")
public void i_pay_for_booking_with_card_details_and_for(String string, String string2, String string3, String string4, String string5, String string6, String string7) {
System.out.println("test");

//payment.formFeilds();

}
@Given("I provide phoneNumber as {string}")
public void i_provide_phone_number_as(String number) {
payment=new PaymentPage(user);
	payment.enterPhoneNumber(number);
}

@Given("I enter CardPayment details as {string}, {string} and {string} for {string} , {string} , {string} , {string} , {string}")
public void i_enter_card_payment_details_as_and_for(String cardNo, String expiryDate,String CVV, String name,String add1,String city,String country,String postalCode) throws InterruptedException {
payment.enterFormFields(cardNo, expiryDate, CVV, name, add1,  city, country,postalCode);
	
}


@When("on clicking PayNow button with invalid values")
public void on_clicking_pay_now_button_with_invalid_values() {
	payment.payNow();
}



	@Then("I should get payment declined message")
	public void i_should_get_payment_declined_message() {
		Assert.assertEquals("Transaction could not be processed. Your payment was not authorised therefore we could not complete your booking. Please ensure that the information was correct and try again or use a new payment card.", payment.Divcurrency("Transaction could not be processed").getText());
	}

}
