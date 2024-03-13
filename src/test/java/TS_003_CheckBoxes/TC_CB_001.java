package TS_003_CheckBoxes;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_CB_001 
{
	WebDriver driver;
	
	@Parameters("browser")
	@BeforeTest
	public void initialize(String browser)
	{
		if(browser.equalsIgnoreCase("firefox"))
		{
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		System.out.println("Firefox browser launched");
		}

		if(browser.equalsIgnoreCase("chrome"))
		{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		System.out.println("Chrome browser launched");
		}

		if(browser.equalsIgnoreCase("edge"))
		{
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		System.out.println("Edge browser launched");
		}	
	}
	
	@Test
	public void CB_001()
	{
		driver.get("https://testautomationpractice.blogspot.com/");		
		scroll();
		
		WebElement sunday = driver.findElement(By.xpath("//input[@id='sunday']"));
		WebElement monday = driver.findElement(By.xpath("//input[@id='monday']"));
		WebElement tuesday = driver.findElement(By.xpath("//input[@id='tuesday']"));
		WebElement wednesday = driver.findElement(By.xpath("//input[@id='wednesday']"));
		WebElement thursday = driver.findElement(By.xpath("//input[@id='thursday']"));
		WebElement friday = driver.findElement(By.xpath("//input[@id='friday']"));
		WebElement saturday = driver.findElement(By.xpath("//input[@id='saturday']"));
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(monday.isDisplayed(),"Monday checkbox is not loaded on page");
		softAssert.assertTrue(tuesday.isDisplayed(),"Tuesday checkbox is not loaded on page");
		softAssert.assertTrue(wednesday.isDisplayed(),"Wednesday checkbox is not loaded on page");
		softAssert.assertTrue(thursday.isDisplayed(),"Thursday checkbox is not loaded on page");
		softAssert.assertTrue(friday.isDisplayed(),"Friday checkbox is not loaded on page");
		softAssert.assertTrue(saturday.isDisplayed(),"Saturday checkbox is not loaded on page");
		softAssert.assertTrue(sunday.isDisplayed(),"Sunday checkbox is not loaded on page");

		softAssert.assertAll();
	}
	
	
	@AfterTest
	public void teardown() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.quit();
	}
	
	public void scroll()
	{
	WebElement scrolling = driver.findElement(By.xpath("//label[normalize-space()='Days:']"));
	JavascriptExecutor jExecutor = (JavascriptExecutor) driver;
	jExecutor.executeScript("arguments[0].scrollIntoView();",scrolling);
	}
	
}
