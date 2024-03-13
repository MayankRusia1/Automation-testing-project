package TS_009_searchbox;

import org.openqa.selenium.By;
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

public class TC_SB_001 
{
	WebDriver driver ;

	@Parameters("browser")
	@BeforeTest
	public void setup(String browser)
	{
		if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			System.out.println("Firefox browser is launched");
		}
		if(browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			System.out.println("Chrome browser is launched");
		}

		if(browser.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			System.out.println("Edge browser is launched");
		}

	}
	
	@Test
	public void SB_001()
	{
		driver.get("https://testautomationpractice.blogspot.com/");
		WebElement searchBox = driver.findElement(By.xpath("//input[@class='wikipedia-search-input']"));
		Assert.assertTrue(searchBox.isDisplayed(), "Search box is NOT loaded on page");
		WebElement searchicon = driver.findElement(By.xpath("//input[@type='submit']"));
		Assert.assertTrue(searchicon.isDisplayed(), "Search icon is NOT loaded on page");		
	}
	
	@AfterTest
	public void teardown() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.close();
	}
	
}