package com.qa.test;

import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.constants.Constants;
import com.qa.page.LoginDemoPage;
import com.qa.utility.CRMExcelRead;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;


public class LoginDemoTest extends BaseTest {

	@Parameters({"userName","password"})
	@Test(dataProvider = "dpLogin")
	public void verifyLoginDemoPage(String emailId, String password){
		LoginDemoPage logindemopage = new LoginDemoPage(driver);
		boolean f =logindemopage.doLogin(emailId, password);
		SoftAssert softassert=new SoftAssert();
		softassert.assertTrue(f);
		softassert.assertAll();
	}


	@DataProvider
	public Object[][] dpLogin() throws InvalidFormatException, IOException {
		Object[][] data = CRMExcelRead.getDataFromExcel(Constants.testData,"cred");
		return data;
	}

}
