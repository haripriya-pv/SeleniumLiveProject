package com.qa.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.qa.utility.ElementUtility;
import com.qa.utility.WaitUtility;

public class MessagesPage {
	WebDriver driver;
	ElementUtility elementutility;
	WaitUtility waitutility;
	// compose message
	@FindBy(xpath = "//a[@class='list-group-item']")
	List<WebElement> optionList;
	@FindBy(partialLinkText = "Messages")
	WebElement msgs;
	@FindBy(xpath = "//span[text()='-']")
	WebElement toField;
	@FindBy(xpath = "//ul[@id='select2-results-3']//child::li[2]")
	WebElement selectTo;
	@FindBy(xpath = "//input[@name='subject']")
	WebElement subjectField;
	@FindBy(xpath = "//textarea[@name='message']")
	WebElement messageField;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement sendButton;
	@FindBy(xpath = "//div[@class='app-alert-message']")
	WebElement msgConfirm;
	@FindBy(xpath = "//button[@class='close' and @type='button']")
	WebElement closeMessage;

	// search inbox
	@FindBy(xpath = "//input[@id='search-messages']")
	WebElement searchField;
	@FindBy(xpath = "//table//tbody//tr//td")
	WebElement searchResult;

	// search send items
	@FindBy(xpath = "//table[@id='message-table']//tbody//tr//td")
	List<WebElement> tableDataList;
	@FindBy(xpath = "//table//tbody//tr[2]//td")
	WebElement tableResult;
	@FindBy(xpath = "//p[@class='pt5 pb10 b-b']")
	WebElement subjectLine;
	@FindBy(xpath = "//p[@class='pt5 pb10 b-b']//following-sibling::p")
	WebElement messageContent;

	// send reply
	@FindBy(xpath = "//textarea[@id='reply_message']")
	WebElement replyMsg;
	@FindBy(xpath = "//button[@class='btn btn-primary pull-right btn-sm ']")
	WebElement replyButton;
	@FindBy(xpath = "//button[@type='button']//following-sibling::div[1]")
	WebElement sentNote;
	@FindBy(xpath = "//div[@class='media-heading']//following-sibling::p")
	List<WebElement> replyList;

	public MessagesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);// initiaze webelements declared using @FindBY
		elementutility = new ElementUtility(driver);
		waitutility = new WaitUtility(driver);
	}

	public void composeMessage(String subject) {
		elementutility.doClick(msgs);
		optionList.get(0).click();
		waitutility.doWait(toField);
		elementutility.doClick(toField);
		waitutility.doWait(selectTo);
		elementutility.doClick(selectTo);
		elementutility.doSendKeys(subjectField, subject);
		elementutility.doSendKeys(messageField, "it's my first message demo");
		elementutility.doClick(sendButton);
		waitutility.doWait(closeMessage);
		elementutility.doClick(closeMessage);
		
	}

	public String searchInbox(String text) {
		elementutility.doClick(msgs);
		optionList.get(1).click();
		elementutility.doSendKeys(searchField, text);
		return searchResult.getText();
	}

	public String searchSendItems(String text) {
		waitutility.doWait(msgs);
		elementutility.doClick(msgs);
		waitutility.doWait(optionList.get(2));
		optionList.get(2).click();
		waitutility.doWait(searchField);
		elementutility.doSendKeys(searchField, text);
		List<String> tableData = new ArrayList<String>();
		for (int j = 0; j < tableDataList.size(); j++) {
			String data = tableDataList.get(j).getText();
			tableData.add(data);
		}
		System.out.println("All table data = " + tableData);
		elementutility.doClick(tableResult);
		System.out.println(subjectLine.getText());
		return subjectLine.getText();
	}

	public String sendReply(String text, String reply) {
		elementutility.doClick(msgs);
		optionList.get(2).click();
		elementutility.doSendKeys(searchField, text);
		elementutility.doClick(tableResult);
		System.out.println(subjectLine.getText());
		if (messageContent.getText().equalsIgnoreCase("it's my first message demo")) {
			elementutility.doSendKeys(replyMsg, reply);
			elementutility.doClick(replyButton);
		}
		waitutility.doWait(closeMessage);
		elementutility.doClick(closeMessage);
		return replyList.get(0).getText();

	}

}
