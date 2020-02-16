package com.auto.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class A {
	
	
	public static void main(String[] args) {
		
		Logger logger = Logger.getLogger("A");
		PropertyConfigurator.configure("log4j.properties");
		WebDriverManager.chromedriver().setup();
		
		//logger.info("browser opened");
		WebDriver driver = new ChromeDriver();
		logger.info("browser opened");
	//in project home directory it will create a new folder and inside this folder it will create the logs
		
	}

}
