package TS_004_Country_dropdown;

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

public class TC_CDD_004 
{
	WebDriver driver = null;
	
	@Parameters("browser")
	@BeforeTest
	public void initialize(String browser)
	{
	if(browser.equalsIgnoreCase("firefox"))
	{
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		System.out.println("Firefox browser is launched");
	}
	if(browser.equalsIgnoreCase("chrome"))
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		System.out.println("Chrome browser is launched");
	}
	if(browser.equalsIgnoreCase("edge"))
	{
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		System.out.println("Edge browser is launched");
	}
	}
	
	@Test
	public void CDD_004()
	{
		driver.manage().window().maximize();
		driver.get("https://testautomationpractice.blogspot.com/");
		scroll();
		WebElement dd = driver.findElement(By.xpath("//select[@id='country']"));
		Assert.assertTrue(dd.isDisplayed(),"Country drop-down is NOT displayed");
	}
	
	@AfterTest
	public void teardown() throws InterruptedException
	{
		Thread.sleep(3000);
		driver.close();
	}
	
	public void scroll()
	{
		WebElement scrolling = driver.findElement(By.xpath("//label[normalize-space()='Country:']"));
		JavascriptExecutor sc = (JavascriptExecutor) driver;
		sc.executeScript("arguments[0].scrollIntoView();",scrolling);
	}
}