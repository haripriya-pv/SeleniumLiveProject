package com.qa.test;

import java.io.IOException;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.constants.Constants;
import com.qa.page.LoginDemoPage;
import com.qa.page.NotesPage;
import com.qa.utility.CRMExcelRead;
import com.qa.utility.ElementUtility;

public class NotesTest extends BaseTest {
	@Test(priority = 1, groups = { "regression", "smoke" }, dataProvider = "dpAddNote")
	public void verifyAddNotes(String heading, String note) {
		LoginDemoPage lpage = new LoginDemoPage(driver);
		lpage.doLogin(ElementUtility.getPropertyValue("username"), ElementUtility.getPropertyValue("password"));
		NotesPage notepage = new NotesPage(driver);
		notepage.addNotes(heading, note);
		String actualNoteContent = notepage.doSearch(heading);
		Assert.assertEquals(actualNoteContent, "Note DP\n" + "Important");
	}

	@DataProvider
	public Object[][] dpAddNote() throws InvalidFormatException, IOException {
		Object[][] data = CRMExcelRead.getDataFromExcel(Constants.testData, "addNote");
		return data;
	}

    //,dataProvider = "dpSearchNote",  String search,String content
	@Test(priority = 2)
	public void verifySearch() {
		LoginDemoPage lpage = new LoginDemoPage(driver);
		lpage.doLogin(ElementUtility.getPropertyValue("username"), ElementUtility.getPropertyValue("password"));
		NotesPage notepage = new NotesPage(driver);
		String search = "Note DP\n"
				+ "Important";
		String content = "first selenium note from dp";
		String actualContent = notepage.doSearch(search);
		Assert.assertEquals(actualContent, search);
	}
	/*
	 * @DataProvider
	 * 
	 * public Object[][] dpSearchNote() throws InvalidFormatException, IOException {
	 * 
	 * Object[][] data =
	 * CRMExcelRead.getDataFromExcel(Constants.testData,"searchNote");
	 * 
	 * return data;
	 * 
	 * }
	 */

	@Test(priority = 3)
	public void verifySearchNoRecord() {
		LoginDemoPage lpage = new LoginDemoPage(driver);
		lpage.doLogin(ElementUtility.getPropertyValue("username"), ElementUtility.getPropertyValue("password"));
		NotesPage notepage = new NotesPage(driver);
		String text = notepage.doSearchNoRecord("My Demo Note");
		Boolean f = true;
		if (text.equalsIgnoreCase("No record found.")) {
			f = false;
		}
		Assert.assertFalse(f);
	}
    
	@Test(priority = 4, retryAnalyzer = generaltests.Retry.class)
	public void verifyEditNote() {
		LoginDemoPage lpage = new LoginDemoPage(driver);
		lpage.doLogin(ElementUtility.getPropertyValue("username"), ElementUtility.getPropertyValue("password"));
		NotesPage notepage = new NotesPage(driver);
		String searchNote = "Note DP";
		notepage.doSearch(searchNote);
		String newHeader = "edited name test";
		notepage.editNotes(newHeader, searchNote);
		String actualContent = notepage.doSearch(newHeader);
		Assert.assertEquals(actualContent, newHeader);
	}

	@Test(priority = 5, groups = { "regression" })
	public void verifyDeleteNote() {
		LoginDemoPage lpage = new LoginDemoPage(driver);
		lpage.doLogin(ElementUtility.getPropertyValue("username"), ElementUtility.getPropertyValue("password"));
		NotesPage notepage = new NotesPage(driver);
		String deleteNote = "Note DP";
		//String actualHeader = notepage.deleteNote(deleteNote);
		String text = notepage.deleteNote(deleteNote);
		//String text = notepage.doSearchNoRecord(actualHeader);
		Boolean f = true;
		if (text.equalsIgnoreCase("No record found.")) {
			f = false;
		}
		Assert.assertFalse(f);

	}

	@Test(priority = 6)
	public void verifyDisplayCount() {
		LoginDemoPage lpage = new LoginDemoPage(driver);
		lpage.doLogin(ElementUtility.getPropertyValue("username"), ElementUtility.getPropertyValue("password"));
		NotesPage notepage = new NotesPage(driver);
		String count = notepage.selectDisplayCount();
		SoftAssert softassert=new SoftAssert();
		softassert.assertEquals(count, "25", "25 notes displayed");
	}

	@Test(priority = 7,groups={"smoke"})
	public void verifyHideColumn() {
		LoginDemoPage lpage = new LoginDemoPage(driver);
		lpage.doLogin(ElementUtility.getPropertyValue("username"), ElementUtility.getPropertyValue("password"));
		NotesPage notepage = new NotesPage(driver);
		List<String> headers = notepage.selectHideColumn();
		System.out.println(headers);
		Boolean f = true;
		for (int i = 0; i < headers.size(); i++) {
			if (headers.get(i).equalsIgnoreCase("Files")) {
				System.out.println("header is not deleted");

			} else
				f = false;
		}
		Assert.assertFalse(f);
	}
}
