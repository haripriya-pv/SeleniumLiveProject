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
	String notetitlecolumn = "2";

	@FindBy(xpath = "//*[text()='Notes']")
	WebElement addNote;
	@FindBy(xpath = "//a[@class='btn btn-default']")
	WebElement typeNote;
	@FindBy(xpath = "//input[@name='title']")
	WebElement title;
	@FindBy(xpath = "//textarea[@name='description']")
	WebElement description;
	@FindBy(xpath = "//div[@id='s2id_note_labels']//ul//li//input")
	WebElement label;
	@FindBy(xpath = "//ul[@class='select2-results']//child::li[1]//div")
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
	@FindBy(xpath = "//table[@id='note-table']//tbody//tr[1]//td[2]")
	WebElement noteToDelete;
	@FindBy(xpath = "//table[@id='note-table']//tbody//tr[1]//td[4]//a[2]")
	WebElement deleteNote;
	@FindBy(id = "confirmDeleteButton")
	WebElement confirmDelete;
	@FindBy(xpath = "//button[text()=' Cancel']")
	WebElement cancelButton;
	@FindBy(xpath = "//div[text()='The record has been deleted.']")
	WebElement deleteNotification;
	@FindBy(xpath = "//td[@class='dataTables_empty']")
	WebElement noRecordText;

	// Number of data per page
	@FindBy(id = "select2-results-2")
	WebElement selectDropdown;
	@FindBy(className = "select2-arrow")
	WebElement selectArrow;
	@FindBy(xpath = "//ul[@id='select2-results-2']//li[2]//div")
	WebElement number;
	//@FindBy(xpath = "//div[@class='select2-result-label']")
	//WebElement count;
	@FindBy(xpath = "//div[@class='select2-drop-mask']")
	WebElement clickCount;
	@FindBy(xpath = "//span[@id='select2-chosen-2']")
	WebElement countShown;

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
		elementutility.doSendKeys(description, note);
		elementutility.pageScroll(label);
		elementutility.doClick(label);
		waitutility.doWait(labelChoice);
		elementutility.doClick(labelChoice);
		submit.submit();
		elementutility.doClick(closebuttonf);
	}

	public String doSearch(String notes) {
		waitutility.doWait(addNote);
		elementutility.doClick(addNote);
		waitutility.doWait(search);
		elementutility.doSendKeys(search, notes);
		By locator = By.xpath("//table[@id='note-table']//tbody//tr//td//a[contains(text(),'" + notes + "')]");
		waitutility.waitForVisibilty(locator);
		List<WebElement> notetable = driver.findElements(locator);
		waitutility.waitForVisibilty(notetable);
		int row = elementutility.getTableDataRowCount(notetable, notes);
		String actualmsg = "";
		if (row != 0)
		{
			WebElement tableRow = driver.findElement(
					By.xpath("//table[@id='note-table']//tbody//tr[" + row + "]//td[" + notetitlecolumn + "]"));
			actualmsg = tableRow.getText();
			System.out.println("Searched Element : " + actualmsg);
		}
		return actualmsg;
	}

	public String doSearchNoRecord(String notes) {
		elementutility.doClick(addNote);
		elementutility.doSendKeys(search, notes);
		return noRecord.getText();

	}

	public void editNotes(String editText, String searchNote1) {
		//elementutility.doClick(addNote);
		//elementutility.doSendKeys(search, searchNote1);
		elementutility.doClick(editNote);
		elementutility.doClear(title);
		elementutility.doSendKeys(title, editText);
		elementutility.doClick(submit);
		waitutility.doWait(closebuttonf);
		elementutility.doClick(closebuttonf);
		//return doSearch(editText);
		//return edittedNote.getText();
	}

	public String deleteNote(String deleteNote2) {
		elementutility.doClick(addNote);
		waitutility.doWait(search);
		elementutility.doSendKeys(search, deleteNote2);
		String header = noteToDelete.getText();
		elementutility.doClick(deleteNote);
		elementutility.doClick(confirmDelete);
		// elementutility.doClick(cancelButton);
		waitutility.doWait(deleteNotification);
		System.out.println(deleteNotification.getText());
		System.out.println(header);
		waitutility.doWait(noRecordText);
		return noRecordText.getText();
	}

	public String selectDisplayCount() {
		elementutility.doClick(addNote);
		elementutility.doClick(selectArrow);
		elementutility.doClick(number);
		System.out.println(countShown.getText());
		return countShown.getText();
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
