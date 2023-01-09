package com.app.pages;

import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

//import com.app.test.is;

public class Scenario4 {

	static WebDriver driver = null;
	String rootFolder;
	Properties propObj;

	public Scenario4(WebDriver driver, String rootFolder, Properties propObj) {
		Scenario4.driver = driver;
		this.rootFolder = rootFolder;
		this.propObj = propObj;
	}

	public boolean isEmp() {
		// TODO Auto-generated method stub

		driver.findElement(By.xpath("//span[.='Bag']")).click();
		
		String expectedText = propObj.getProperty("emptyBag");
		String actualText = driver.findElement(By.xpath("//div[@data-testid='button']")).getText();

		// return assertEquals(actualText, expectedText);
		if (actualText.equals(expectedText)) {
			return true;
		} else {
			return false;
		}

	}

	public void addObject() throws Exception {
		// TODO Auto-generated method stub
		driver.findElement(By.xpath("//div[@data-testid='button']")).click();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// driver.findElement(By.xpath("//a[@data-group='women']")).click();
		driver.findElement(By.xpath("//input[@class='desktop-searchBar']")).sendKeys("earrings Golden");
		driver.findElement(By.xpath("//span[@class='myntraweb-sprite desktop-iconSearch sprites-search']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.findElement(By.xpath("//img[@title='KARATCART Gold-Plated Handcrafted Hoop Earrings']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String mainWindow = driver.getWindowHandle();
		
		Set<String> a = driver.getWindowHandles();
		String strArray[] = a.toArray(new String[a.size()]);
		String childWindow = strArray[1];
		if (!mainWindow.equalsIgnoreCase(childWindow)) {
			driver.switchTo().window(strArray[1]);
		} else {
			driver.switchTo().window(strArray[0]);
		}
		
		driver.findElement(By.xpath("//*[contains(text(),\"ADD TO BAG\")]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.findElement(By.xpath("//*[contains(text(),'GO TO BAG')]")).click();
		Thread.sleep(5);
		
		String actual = driver.findElement(By.xpath("//div[.='1/1 ITEMS SELECTED']")).getText();
		String expected = "1/1 ITEMS SELECTED";

		Assert.assertEquals(actual, expected, "Item Not Added");
	}

}
