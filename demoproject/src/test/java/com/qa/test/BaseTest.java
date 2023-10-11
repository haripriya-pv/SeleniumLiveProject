package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.qa.constants.Constants;
import com.qa.utility.ElementUtility;

public class BaseTest {
	WebDriver driver;

	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(@Optional("chrome") String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(200));// wait before each line of code
		driver.get(ElementUtility.getPropertyValue("baseURL"));
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void takeScreenShotOnFailure(ITestResult iTestResult) throws IOException {
		if (iTestResult.getStatus() == ITestResult.FAILURE) {
			takeScreenShotOnFailure(iTestResult.getName());
		}
		// driver.quit();
	}

	public String takeScreenShotOnFailure(String name) throws IOException {
		String dateName = new SimpleDateFormat("yyyy_MM_dd_hh_mm").format(new Date());
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destination = Constants.screenShot_path + name + dateName + ".png";
     	File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;

		/*
		 * when each test is executed ,we need to check whether the test is
		 * failed/passed if failed then take the screenshot
		 * ------------------------------------------ afterMethod
		 * 
		 * ITestResult-Interface getStatus()-return status of the method
		 * getName()-returns the name of @test that got executed last
		 * 
		 * 
		 * methodnameyyyy_MM_dd_hh_mm -------------------------- constants -give the
		 * constants of the project. screenshot name = path from constant +
		 * methodnameyyyy_MM_dd_hh_mm + .png
		 */
	}
}
