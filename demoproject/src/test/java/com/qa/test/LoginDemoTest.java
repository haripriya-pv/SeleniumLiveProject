package com.qa.test;

import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;
import com.qa.constants.Constants;
import com.qa.page.LoginDemoPage;
import com.qa.utility.CRMExcelRead;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;


public class LoginDemoTest extends BaseTest {

	 @Parameters({"userName","password"})
	// public void verifyLoginDemoPage(@Optional("admin@admin.com")String
	// userName,@Optional("12345678") String password)throws InterruptedException {

	@Test(dataProvider = "dpLogin")
	public void verifyLoginDemoPage(String emailId, String password){
		LoginDemoPage logindemopage = new LoginDemoPage(driver);
		logindemopage.doLogin(emailId, password);

	}

/*	@DataProvider(name = "SearchProvider")
	public Object[][] getDataFromDataprovider() {
		return new Object[][] { { "admin@admin.com", "12345678" }, };

	}*/

	@DataProvider

	public Object[][] dpLogin() throws InvalidFormatException, IOException {

		Object[][] data = CRMExcelRead.getDataFromExcel(Constants.testData,"cred");
		
		return data;

	}

}
