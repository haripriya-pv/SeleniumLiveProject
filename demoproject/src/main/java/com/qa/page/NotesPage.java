package com.qa.page;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.utility.ElementUtility;
import com.qa.utility.WaitUtility;



public class NotesPage {
	WebDriver driver;
	ElementUtility elementutility;
	WaitUtility waitutility;
	String notetitlecolumn="2";
	
	@FindBy(xpath="//*[text()='Notes']")
	WebElement addNote;
	@FindBy(xpath = "//a[@class='btn btn-default']")
	WebElement typeNote;
	@FindBy(xpath = "//input[@name='title']")
	WebElement title;
	@FindBy(xpath = "//textarea[@name='description']")
	WebElement description;
	@FindBy(xpath = "//input[@class='select2-input select2-default']")
	WebElement label;
	@FindBy(xpath = "//li[@class='select2-search-choice']")
	WebElement labelChoice;
	@FindBy(xpath = "//button[@class='btn btn-default upload-file-button pull-left btn-sm round dz-clickable']")
	WebElement uploadFile;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement submit;
	@FindBy(xpath = "//table[@id='note-table']//tbody//tr[1]//td[2]//a")
	WebElement savedNote;
	@FindBy(xpath = "//div[@class='col-md-12 mb15 notepad']")
	WebElement savedContent;

	// search web elements
	@FindBy(xpath = "//input[@type='search']")
	WebElement search;
	@FindBy(xpath = "//table/tbody/tr/td[2]/a")
	WebElement searchResult;
	@FindBy(xpath = "//button[text()=' Close']")
	WebElement closeResultButton;
	@FindBy(xpath = "//td[text()='No record found.']")
	WebElement noRecord;

	// edit notes
	@FindBy(xpath = "//table[@id='note-table']/tbody/tr[1]/td[4]/a")
	WebElement editNote;
	@FindBy(xpath = "//table[@id='note-table']/tbody/tr[1]/td[2]/a")
	WebElement edittedNote;
	@FindBy(xpath = "//button[@class='close'and @type='button']")
	WebElement closebuttonf;

	// delete note
	@FindBy(xpath = "//table/tbody/tr[3]/td[2]/a")
	WebElement noteToDelete;
	@FindBy(xpath = "//table/tbody/tr[3]/td[4]/a[2]")
	WebElement deleteNote;
	@FindBy(id = "confirmDeleteButton")
	WebElement confirmDelete;
	@FindBy(xpath = "//button[text()=' Cancel']")
	WebElement cancelButton;
	@FindBy(xpath = "//div[text()='The record has been deleted.']")
	WebElement deleteNotification;

	// select display count
//	@FindBy(xpath = "//div[@class='select2-result-label']")
//	WebElement count;
	@FindBy(xpath = "//div[@class='select2-drop-mask']")
	WebElement clickCount;

	// hide column
	@FindBy(xpath = "//button[@class='btn btn-default column-show-hide-popover ml15']")
	WebElement hideButton;
	@FindBy(xpath = "//li[@class=' list-group-item clickable toggle-table-column']")
	List<WebElement> hideOptions;
	@FindBy(xpath = "//div[@class='page-title clearfix']//child::h1")
	WebElement freeSpace;
	@FindBy(xpath = "//table[@id='note-table']//thead//tr//th")
	List<WebElement> headerList;

	public NotesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);// initiaze webelements declared using @FindBY
		elementutility = new ElementUtility(driver);
		waitutility = new WaitUtility(driver);
	}

	public void addNotes(String header, String note) {
		elementutility.doClick(addNote);
		elementutility.doClick(typeNote);
		elementutility.doSendKeys(title, header);
		elementutility.doSendKeys(description,note);
		// label.sendKeys("Important");
		//elementutility.doSendKeys(uploadFile, "C:\\Users\\hpriy\\OneDrive\\Pictures\\reviewProgram1.png"); // add an
																											// extra '\'
																											// after
																											// copying
																											// path
		submit.submit();
		
	}

	public String doSearch(String notes) {
		waitutility.doWait(addNote);
		elementutility.doClick(addNote);
		waitutility.doWait(search);
		elementutility.doSendKeys(search, notes);
		
		By locator=By.xpath("//table[@id='note-table']//tbody//tr//td//a[contains(text(),'"+notes+"')]");

		waitutility.waitForVisibilty(locator);

		List<WebElement> notetable=driver.findElements(By.xpath("//table[@id='note-table']//tbody//tr//td//a[contains(text(),'"+notes+"')]"));

		waitutility.waitForVisibilty(notetable);

		int row=elementutility.getTableDataRowCount(notetable, notes);

		String actualmsg="";
		

		if(row!=0) 

		{

			WebElement tableRow=driver.findElement(By.xpath("//table[@id='note-table']//tbody//tr["+row+"]//td["+notetitlecolumn+"]"));

			actualmsg=tableRow.getText();

			System.out.println("Searched Element : " +actualmsg);
			

		}

		return actualmsg;
	
		
		
//		String result = searchResult.getText();
//		System.out.println(result);
//		elementutility.doClick(searchResult);
//		return savedContent.getText();
//		//elementutility.doClick(closeResultButton);
		
	}

	public String doSearchNoRecord(String notes) {
		elementutility.doClick(addNote);
		elementutility.doSendKeys(search, notes);
		return noRecord.getText();
		
	}

	public void editNotes(String editText, String searchNote1) {
//		elementutility.doClick(addNote);
//		elementutility.doSendKeys(search, searchNote1);
		
		elementutility.doClick(editNote);
		elementutility.doClear(title);
		elementutility.doSendKeys(title, editText);
		elementutility.doClick(submit);
		waitutility.doWait(closebuttonf);
		elementutility.doClick(closebuttonf);
//		return doSearch(editText);
//		return edittedNote.getText();
	}

	public String deleteNote() {
		elementutility.doClick(addNote);
		String header = noteToDelete.getText();
		elementutility.doClick(deleteNote);
		elementutility.doClick(confirmDelete);
		//elementutility.doClick(cancelButton);
		waitutility.doWait(deleteNotification);
		System.out.println(deleteNotification.getText());
		return header;
	}

	public void selectDisplayCount() {
		elementutility.doClick(addNote);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//		WebElement clickCount = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='select2-drop']")));
		elementutility.doClick(clickCount);
		WebElement count = wait.until(ExpectedConditions.elementToBeClickable(By.name("note-table_length")));
		Select oselect = new Select(count);
		oselect.selectByIndex(2);
		List<WebElement> options = oselect.getOptions();
		for (int i = 0; i < options.size(); i++) {
			String optionsDisplay = options.get(i).getText();
			System.out.println(optionsDisplay);
		}
	}

	public List<String> selectHideColumn() {
		elementutility.doClick(addNote);
		elementutility.doClick(hideButton);
		List<String> optionName = new ArrayList<String>();
		for (int j = 0; j < hideOptions.size(); j++) {
			String data = hideOptions.get(j).getText();
			optionName.add(data);
		}
		System.out.println("All table data = " + optionName);
		hideOptions.get(2).click();
		elementutility.doClick(freeSpace);
		List<String> headerName = new ArrayList<String>();
		for (int j = 0; j < headerList.size(); j++) {
			String name = headerList.get(j).getText();
			System.out.println(name);
			headerName.add(name);
		}
		return headerName;
		
	}

}
