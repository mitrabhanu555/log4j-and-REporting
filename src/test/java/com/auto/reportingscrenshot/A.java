package com.auto.reportingscrenshot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class A {

	public static void main(String[] args) {
	  
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://selenium.dev/");
		WebElement ele = driver.findElement(By.linkText("Projects"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		while (!(ele.isDisplayed()==true)) {
		 
			
		

	  }
	}
}
