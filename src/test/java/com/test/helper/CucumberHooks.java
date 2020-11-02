package com.test.helper;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.test.enumerations.Browser;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class CucumberHooks {

	WebDriver driver;
	UserActions user;

	public CucumberHooks() {

	}

	public CucumberHooks(UserActions user) {
		this.user = user;
	}

	@Before()
	public void beforeChrome() {
		user.launchApplication(Browser.Chrome, System.getProperty("url"));

	}

	@After()
	public void afterChrome() {

		user.closeApplication();
	}

	@AfterStep
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			scenario.attach(((TakesScreenshot) user.getDriver()).getScreenshotAs(OutputType.BYTES), "image/png",
					"Failed");
		} else {
			scenario.attach(((TakesScreenshot) user.getDriver()).getScreenshotAs(OutputType.BYTES), "image/png",
					"Passed");

		}
	}

}
