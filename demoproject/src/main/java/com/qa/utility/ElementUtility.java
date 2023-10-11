package com.qa.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qa.constants.Constants;

public class ElementUtility {
	WebDriver driver;

	public ElementUtility(WebDriver driver) {
		this.driver = driver;

	}

	public void doSendKeys(WebElement element, String value) {
		element.sendKeys(value);
	}

	public void doClick(WebElement element) {
		element.click();
	}
	
	public void getText(WebElement element) {
		element.getText();
	}
	
	public void doJavaScriptClick(String id) {
		JavascriptExecutor js=(JavascriptExecutor)driver;//typecast driver to javascriptexecutor
		js.executeScript("document.getElementById('"+id+"').click();");
	}
	
	public void pageScroll(WebElement element) {
		JavascriptExecutor js=(JavascriptExecutor)driver;//typecast driver to javascriptexecutor
		js.executeScript("window.scrollBy(0,500)");//vertical down 500 pixel
		js.executeScript("arguments[0].scrollIntoView()",element);
	}
	
	public void doClear(WebElement element) {
		element.clear();
	}
	
	
	public static String getPropertyValue(String key) 
	{

		File src=new File(Constants.propertyConfig_File);
		Properties pro=new Properties();
		try {
			FileInputStream fis = new FileInputStream (src);
			
			pro.load(fis);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		String value=pro.get(key).toString();
		return value;
	}
	
	public int getTableDataRowCount(List<WebElement> tableRowData ,String expectedValue)
	{
		int counter=0;
		for(int i=0;i<tableRowData.size();i++)
		{
			String value=tableRowData.get(i).getText();
			if(expectedValue.equalsIgnoreCase(value))
			{
				counter=i+1;
				break;
			}
		}
		return counter;
	}

}
