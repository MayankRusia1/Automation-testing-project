package TS_012_doubleclickaction;

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

public class TC_DC_001 
{
	WebDriver driver;

	@Parameters("browser")
	@BeforeTest
	public void setup(String browser)
	{
		if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println("Firefox browser is launched");
		}

		if(browser.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println("Edge browser is launched");
		}

		if(browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println("Chrome browser is launched");
		}

	}


	@Test(description = "Cross browser testing")
	public void PT_001()
	{
		driver.manage().window().maximize();
		driver.get("https://testautomationpractice.blogspot.com/");
		scroll();
		WebElement dca = driver.findElement(By.xpath("//h2[normalize-space()='Double Click']"));
		Assert.assertTrue(dca.isDisplayed(), "Double click button is NOT displayed");
		Assert.assertTrue(dca.isEnabled(), "Double click button is Disabled");		
	}


	@AfterTest
	public void teardown() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.close();
	}

	public void scroll()
	{
		WebElement scrollingElement = driver.findElement(By.xpath("//h2[normalize-space()='Double Click']"));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView();", scrollingElement);
	}

}
