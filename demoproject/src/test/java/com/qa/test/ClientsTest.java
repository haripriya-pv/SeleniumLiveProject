package com.qa.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.page.ClientsPage;
import com.qa.page.LoginDemoPage;
import com.qa.utility.ElementUtility;

public class ClientsTest extends BaseTest{
  @Test(priority = 1)
  public void verifyAddClient() {
	  LoginDemoPage lpage = new LoginDemoPage(driver);
	  lpage.doLogin(ElementUtility.getPropertyValue("username"), ElementUtility.getPropertyValue("password"));
	  ClientsPage clientspage = new ClientsPage(driver);
	  String actualName= clientspage.addClient("hari","address","city","state","zip","country","9898989898","www.com","vat","group");
	  Assert.assertEquals(actualName, "hari");
  }
  
  @Test(priority = 2)
  public void verifyDeleteClient() {
	  LoginDemoPage lpage = new LoginDemoPage(driver);
	  lpage.doLogin(ElementUtility.getPropertyValue("username"), ElementUtility.getPropertyValue("password"));
	  ClientsPage clientspage = new ClientsPage(driver);
	  String actualResult=clientspage.deleteClient("hari");
	  Boolean f = true;
		if (actualResult.equalsIgnoreCase("No record found.")) {
			f = false;
		}
		Assert.assertFalse(f);
  }
}
