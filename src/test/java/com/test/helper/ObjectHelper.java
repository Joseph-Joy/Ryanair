package com.test.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ObjectHelper {

	public static WebElement getElement(WebDriver driver,By locator) {
		return driver.findElement(locator);
	}
}
