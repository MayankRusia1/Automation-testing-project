package TS_005_Colors_dropdown;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_COLORDD_004 
{
	WebDriver driver = null;
	
	@Parameters("browser")
	@BeforeTest
	public void setup(String browser)
	{
		if(browser.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().maximize();
		}
		if(browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}
	}
	
	@Test(description = "Validate whether Drop-down list is loaded successfully.")
	public void COLORDD_004()
	{
		scroll();
		driver.get("https://testautomationpractice.blogspot.com/");
		
		WebElement dd = driver.findElement(By.xpath("//select[@id='colors']"));
		
		Assert.assertTrue(dd.isDisplayed(), "Colors drop-down is NOT loaded successfully");
	}
	
	
	@AfterTest
	public void teardown() throws InterruptedException
	{
		Thread.sleep(1500);
		driver.close();
	}
	
	public void scroll()
	{
		WebElement scrolling = driver.findElement(By.xpath("//label[normalize-space()='Colors:']"));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView();", scrolling);
	}
}
