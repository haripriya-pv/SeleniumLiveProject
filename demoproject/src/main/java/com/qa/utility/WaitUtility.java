package com.qa.utility;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtility {
	WebDriver driver;

	public WaitUtility(WebDriver driver) {
		this.driver = driver;
	}

	public void doWait(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(550));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForVisibiity(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForVisibilty(List<WebElement> element)

	{

		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(60));

		wait.until(ExpectedConditions.visibilityOfAllElements(element));

	}

	

	public void waitForVisibilty(By locator)

	{

		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(60));

		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

}
