package com.auto.reportingscrenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;
import se.vidstige.jadb.ITransportFactory;

public class Test2 {
	
	
	public WebDriver driver = null;
	public ExtentHtmlReporter htmlreporter;
	public ExtentReports extent;
	public ExtentTest test;
    
	@BeforeTest
	public void setExtent() {
		
		htmlreporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/pinkubaburpt.html");
		//title of the report
		htmlreporter.config().setDocumentTitle("Practice report");
		//name of the report
		htmlreporter.config().setReportName("Automation Practice Report");
		//theme of the report
		htmlreporter.config().setTheme(Theme.DARK);
		
		//creating an object of extentreports and adding information to report
				extent = new ExtentReports();
				//attaching the report
				extent.attachReporter(htmlreporter);
				//ading some extra info
				extent.setSystemInfo("HostName", "Local Host");
				extent.setSystemInfo("Organization", "Mindq");
				extent.setSystemInfo("UserName", "PinkuBabu");
				extent.setSystemInfo("Environment", "Production");
				extent.setSystemInfo("Browser", "Chrome");
		
	}
	
	@AfterTest
	public void endReport() {
		
		extent.flush();
			
	}
	
	@BeforeMethod
	public void setUp() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://demo.nopcommerce.com/");
		
	}
	
	@Test
	public void noCommerceTitleTest() {
		
		test = extent.createTest("noCommerceTitleTest");
		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertEquals(title, "noCommerce demo store");
		
		
	}
	
	@Test
	public void noCommerceLogoTest() {
		
		test = extent.createTest("noCommerceLogoTest");
		WebElement ele = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[2]/div[1]/a/img"));
		Boolean status =ele.isDisplayed();
	
		System.out.println(status);
		Assert.assertTrue(status);
		
	}
	
	@Test
	public void noCommerceLoginTest() {
		
		test = extent.createTest("noCommerceLoginTest");
		
		test.createNode("TEST CASE WITH VALID DATA");
		Assert.assertTrue(true);
		test.createNode("TEST CASE WITH INVAID DATA");
		Assert.assertTrue(true);
	
		
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws Throwable {
		
		if (result.getStatus()== ITestResult.FAILURE) {
			
			//to add name in extent report
			test.log(Status.FAIL,"TEST CASE FAILED IS : " + result.getName());
			//to add error or exception in extentreports
			test.log(Status.FAIL,"TEST CASE FAILED IS : " + result.getThrowable());
			
			String screenshotPath = Test2.getScreenshot(driver, result.getName());
			//attach scrrenshot to extent report
			test.addScreenCaptureFromPath(screenshotPath);
			
		} else if (result.getStatus()== ITestResult.SKIP) {
			
			test.log(Status.SKIP, "TEST CASE SKIPPED IS : " + result.getName());
			
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			
			test.log(Status.PASS,"TEST CASE IS PASSED" + result.getName());
			
		} 
		
	}
	
	public static String getScreenshot(WebDriver driver,String screenshotName) throws Throwable {
		
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts =(TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/screenshots/" + screenshotName + dateName + ".png" ;
	    File finalDestination = new File(destination);
	    FileUtils.copyFile(source, finalDestination);
	    return destination;
	
	
	
	}
	
}
