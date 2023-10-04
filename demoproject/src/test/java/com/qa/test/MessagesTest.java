package com.qa.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.page.LoginDemoPage;
import com.qa.page.MessagesPage;
import com.qa.utility.ElementUtility;

public class MessagesTest extends BaseTest {
	
	@Test(priority = 1)
	public void verifyComposeMessage() {
		LoginDemoPage lpage = new LoginDemoPage(driver);
		lpage.doLogin(ElementUtility.getPropertyValue("username"), ElementUtility.getPropertyValue("password"));
		MessagesPage messagespage = new MessagesPage(driver);
		messagespage.composeMessage("second message");
		String actualSubject=messagespage.searchSendItems("second");
		String expected = "Subject: second message";
		Assert.assertEquals(actualSubject, expected);
		
	}

	@Test(priority = 2)
	public void verifySearchInbox() {
		LoginDemoPage lpage = new LoginDemoPage(driver);
		lpage.doLogin(ElementUtility.getPropertyValue("username"), ElementUtility.getPropertyValue("password"));
		MessagesPage messagespage = new MessagesPage(driver);
		String actualResult = messagespage.searchInbox("message");
		Assert.assertEquals(actualResult, "No record found.");
	}

	@Test(priority = 3)
	public void verifySendReply() {
		LoginDemoPage lpage = new LoginDemoPage(driver);
		lpage.doLogin(ElementUtility.getPropertyValue("username"), ElementUtility.getPropertyValue("password"));
		MessagesPage messagespage = new MessagesPage(driver);
		String replyContent = "it's my second demo reply";
		String actualNote =messagespage.sendReply("second",replyContent );
		Assert.assertEquals(actualNote,replyContent);
	}
}
