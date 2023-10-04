package com.qa.page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.utility.ElementUtility;

public class LoginDemoPage {
	WebDriver driver;
	ElementUtility elementutility;
	@FindBy(xpath = "//input[@name='email']")
	WebElement emailField;
	@FindBy(xpath = "//input[@name='password']")
	WebElement passwordField;
	@FindBy(xpath = "//button[@type = 'submit']")
	WebElement submit;

	public LoginDemoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);// initiaze webelements declared using @FindBY
		elementutility = new ElementUtility(driver);
	}

	public void doLogin(String name, String pass) {
		elementutility.doSendKeys(emailField, name);
		elementutility.doSendKeys(passwordField, pass);
		elementutility.doClick(submit);
	}

}
