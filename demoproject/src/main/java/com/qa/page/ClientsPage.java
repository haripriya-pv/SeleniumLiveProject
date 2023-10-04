package com.qa.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.utility.ElementUtility;
import com.qa.utility.WaitUtility;

public class ClientsPage {
    WebDriver driver;
    ElementUtility elementutility;
    WaitUtility waitutility;
	@FindBy(partialLinkText = "Clients")
	WebElement clientOption;
	@FindBy(xpath = "//a[@title='Add client']")
	WebElement addClientButton;
	@FindBy(xpath = "//input[@id='company_name']")
	WebElement companyNameField;
	@FindBy(xpath = "//textarea[@id='address']")
	WebElement addressField;
	@FindBy(xpath = "//input[@id='city']")
	WebElement cityField;
	@FindBy(xpath = "//input[@id='state']")
	WebElement stateField;
	@FindBy(xpath = "//input[@id='zip']")
	WebElement zipField;
	@FindBy(xpath = "//input[@id='country']")
	WebElement countryField;
	@FindBy(xpath = "//input[@id='phone']")
	WebElement phoneField;
	@FindBy(xpath = "//input[@id='website']")
	WebElement websiteField;
	@FindBy(xpath = "//input[@id='vat_number']")
	WebElement vatNumberField;
	@FindBy(xpath = "//input[@id='s2id_autogen6']")
	WebElement clientGroupField;
	@FindBy(xpath = "//input[@id='s2id_autogen5_search']")
	WebElement currencyField;
	@FindBy(xpath = "//li[@role='presentation'][68]")
	WebElement currencySelect;
	@FindBy(xpath = "//input[@id='disable_online_payment']")
	WebElement checkBox;
	@FindBy(xpath = "//label[text()='VAT Number']")
	WebElement pageClick;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement fornSubmitButton;
	@FindBy(xpath = "//div[@id='client-table_filter']//child::label[1]//input")
	WebElement searchClients;
	@FindBy(xpath = "//table[@id='client-table']//tbody//tr[1]//td[2]")
	WebElement searchResult;
	@FindBy(xpath = "//table[@id='client-table']//tbody//tr[1]//td[9]//a[2]")
	WebElement deleteButton;
	@FindBy(xpath = "//button[@id='confirmDeleteButton']")
	WebElement confirmDelete;
	@FindBy(xpath = "//td[text()='No record found.']")
	WebElement noRecordFound;
	
	public ClientsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);// initiaze webelements declared using @FindBY
		elementutility = new ElementUtility(driver);
		waitutility = new WaitUtility(driver);
		
	}
	
	public String addClient(String cName,String address,String city,String state,String zip,String country,String phone,String website,String vat,String cGroup) {
		elementutility.doClick(clientOption);
		elementutility.doClick(addClientButton);
		elementutility.doSendKeys(companyNameField, cName);
		elementutility.doSendKeys(addressField, address);
		elementutility.doSendKeys(cityField, city);
		elementutility.doSendKeys(stateField, state);
		elementutility.doSendKeys(zipField, zip);
		elementutility.doSendKeys(countryField, country);
		elementutility.doSendKeys(phoneField, phone);
		elementutility.doSendKeys(websiteField, website);
		elementutility.doSendKeys(vatNumberField, vat);
		elementutility.doClick(pageClick);
		//elementutility.pageScroll(currencyField);
//		waitutility.doWait(clientGroupField);
//		elementutility.doSendKeys(clientGroupField, cGroup);	
		elementutility.pageScroll(checkBox);
		waitutility.doWait(checkBox);
		elementutility.doClick(checkBox);
		//elementutility.doClick(currencyField);
		//elementutility.doClick(currencySelect);
		elementutility.doClick(fornSubmitButton);
		waitutility.doWait(searchClients);
		elementutility.doClick(searchClients);
		elementutility.doSendKeys(searchClients, cName);
		return searchResult.getText();
	}
	
	public String deleteClient(String cName) {
		elementutility.doClick(clientOption);
		waitutility.doWait(searchClients);
		elementutility.doClick(searchClients);
		elementutility.doSendKeys(searchClients, cName);
		elementutility.doClick(deleteButton);
		elementutility.doClick(confirmDelete);
		return noRecordFound.getText();
		
	}
	

}
