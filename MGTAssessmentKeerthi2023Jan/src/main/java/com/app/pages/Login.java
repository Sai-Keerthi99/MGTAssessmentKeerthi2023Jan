package com.app.pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {
	static WebDriver driver = null;
	String rootFolder;
	Properties propObj;

	public Login(WebDriver driver, String rootFolder, Properties propObj) {
		Login.driver = driver;
		this.rootFolder = rootFolder;
		this.propObj = propObj;
	}
	
	public void login() throws Exception {
		String email = propObj.getProperty("email");
		String password = propObj.getProperty("password");
		driver.findElement(By.xpath("//input[@id='mobileNumberPass']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
		
		
		driver.findElement(By.xpath("//button[.='LOGIN']")).click();
		
	     Thread.sleep(32000);
	     
	     driver.findElement(By.xpath("//button[.='LOGIN']")).click();
	     Thread.sleep(3000);
	}

	public String getPageTitle() {
		// TODO Auto-generated method stub
		return driver.getTitle();
	}
}
