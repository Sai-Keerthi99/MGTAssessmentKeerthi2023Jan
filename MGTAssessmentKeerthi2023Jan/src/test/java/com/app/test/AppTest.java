package com.app.test;

import org.testng.annotations.Test;

import com.app.pages.Login;
import com.app.pages.Scenario4;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;

public class AppTest {

	static WebDriver driver = null;
	static String rootFolder;
	Properties propObj;
	Login login;
	Scenario4 bag;

	@BeforeMethod
	public void launchBrowser() throws Exception {

		rootFolder = System.getProperty("user.dir");
		propObj = new Properties();

		propObj.load(new FileInputStream(rootFolder + "//src//test//resources//AppProperties"));
		String url = propObj.getProperty("url");

		driver = new ChromeDriver();
		
		driver.get(url);
		// adjusting the window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	@Test
	public void verifyLogin() throws Exception {//for scenario 2
		login = new Login(driver, rootFolder, propObj);
		login.login();//assert this
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		WebElement element = driver.findElement(By.xpath("//span[.='Profile']"));//find profile
		
		Assert.assertTrue(element.isDisplayed(), "Login Unsuccessful");
		
	     String expectedTitle=propObj.getProperty("expectedTitle");
	     String actualTitle=login.getPageTitle();
	     Assert.assertEquals(expectedTitle,actualTitle,"The user is not logged in");
	     Assert.assertTrue(driver.findElement(By.xpath("//*[@data-reactid='855']")).isDisplayed());
		
	}
	
	@Test
	public void verifyBag() throws Exception {//for scenario 4
		bag = new Scenario4(driver, rootFolder, propObj);
		driver.get("https://www.myntra.com");
		if(bag.isEmp()) {
			bag.addObject();
		}
		
	}
	
	@AfterClass
	public void quit() {
		driver.quit();
	}
}
