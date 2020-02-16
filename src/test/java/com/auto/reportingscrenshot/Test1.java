package com.auto.reportingscrenshot;


import java.io.File;
import java.util.Arrays;

import javax.naming.spi.DirStateFactory.Result;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import net.bytebuddy.matcher.MethodExceptionTypeMatcher;

public class Test1 {
	
	
	public ExtentHtmlReporter htmlreporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	@BeforeTest
	public void setReport() {
		//creating an object of extenthtmlreportr and defining path of the report
		//htmlreporter = new ExtentHtmlReporter("./reports/pinkurpt.html");
		htmlreporter = new ExtentHtmlReporter(new File(System.getProperty("user.dir") + "/test-output/pinkurpt.html"));
		//htmlreporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/pinkurpt.html");
		htmlreporter.config().setDocumentTitle("Practice report");
		htmlreporter.config().setReportName("Automation Practice Report");
		htmlreporter.config().setTheme(Theme.DARK);
		
		//creating an object of extentreports and adding information to report
		extent = new ExtentReports();
		extent.attachReporter(htmlreporter);
		extent.setSystemInfo("HostName", "Software Testing");
		extent.setSystemInfo("Organization", "Mindq");
		extent.setSystemInfo("UserName", "PinkuBabu");
		extent.setSystemInfo("Environment", "Production");
	
		
		
	}
	
	@AfterTest
	public void endReport() {
		
		extent.flush();
			
	}
	
	@Test
	public void doLogin() {
		
		logger = extent.createTest("Login Test");
		System.out.println("Executing Login Test");
		
	}
	
	@Test
	public void userReg() {
		
		logger = extent.createTest("User reg Test");
		Assert.assertTrue(false);
		System.out.println("User reg failed");
		
	}
	
	@Test
	public void isSkip() {
		
		logger = extent.createTest("Skip Test");
		throw new SkipException("skipping the testcases");
		
	}
	
	
	
   @AfterMethod
	public void tearDown(ITestResult result) {
		
		if (result.getStatus()==ITestResult.FAILURE) {
			
			String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
			logger.fail("<details>"+ "<summary>" + "<b>" + "<font color=" + "red>" +"Exception Occured : click to see" +"</font>" +"</b>" + "</summary" + exceptionMessage.replaceAll(",", "<br>")+ "</details>" + "\n");
			
			String failureLogg = "TEST CASE FAILED";
			Markup m1 = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
			logger.log(Status.FAIL, m1);
			//String methodName = result.getMethod().getMethodName();
			//String logtext = "<b>" + "TEST CASE : - " + methodName.toUpperCase()+ "   FAILED" + "</b>";
			//Markup m = MarkupHelper.createLabel(logtext, ExtentColor.RED);
			//logger.fail(m);
			
		} else if (result.getStatus()== ITestResult.SKIP) {
			
			String methodName = result.getMethod().getMethodName();
			String logtext = "<b>" + "TEST CASE : - " + methodName.toUpperCase()+ "   SKIPPED" + "</b>";
			Markup m = MarkupHelper.createLabel(logtext, ExtentColor.PINK);
			logger.skip(m);
			
		} else if (result.getStatus()==ITestResult.SUCCESS) {
			
			String methodName = result.getMethod().getMethodName();
			String logtext = "<b>" + "TEST CASE : - " + methodName.toUpperCase()+ "   PASSED" + "</b>";
			Markup m = MarkupHelper.createLabel(logtext, ExtentColor.GREEN);
			logger.pass(m);
			
		} 
		
		
		
		
	}
	
	
	
}
