package com.qa.page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.utility.ElementUtility;
import com.qa.utility.WaitUtility;

public class LoginDemoPage {
	WebDriver driver;
	ElementUtility elementutility;
	WaitUtility waitutility;
	@FindBy(xpath = "//input[@name='email']")
	WebElement emailField;
	@FindBy(xpath = "//input[@name='password']")
	WebElement passwordField;
	@FindBy(xpath = "//button[@type = 'submit']")
	WebElement submit;
	@FindBy(xpath = "//div[@class='widget-details']")
	WebElement widget;
	public LoginDemoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);// initiaze webelements declared using @FindBY
		elementutility = new ElementUtility(driver);
		waitutility = new WaitUtility(driver);
	}

	public boolean doLogin(String name, String pass) {
		elementutility.doSendKeys(emailField, name);
		elementutility.doSendKeys(passwordField, pass);
		elementutility.doClick(submit);
		waitutility.doWait(widget);
		boolean f = false;
		if(widget.isDisplayed()) {
			f=true;
		}
		return f;
	}

}
