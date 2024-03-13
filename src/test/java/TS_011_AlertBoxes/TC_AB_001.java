package TS_011_AlertBoxes;

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

public class TC_AB_001 
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
		SoftAssert softAssert =  new SoftAssert();
		
		WebElement alertButton = driver.findElement(By.xpath("//button[normalize-space()='Alert']"));
		softAssert.assertTrue(alertButton.isDisplayed(), "Alert button is NOT displayed");
		softAssert.assertTrue(alertButton.isEnabled(), "Alert button is Disabled");
		
		WebElement promptButton = driver.findElement(By.xpath("//button[normalize-space()='Alert']"));
		softAssert.assertTrue(promptButton.isDisplayed(), "Prompt button is NOT displayed");
		softAssert.assertTrue(promptButton.isEnabled(), "Prompt button is Disabled");		
		
		WebElement confirmButton = driver.findElement(By.xpath("//button[normalize-space()='Alert']"));
		softAssert.assertTrue(confirmButton.isDisplayed(), "Confirm button is NOT displayed");
		softAssert.assertTrue(confirmButton.isEnabled(), "Confirm button is Disabled");
	}


	@AfterTest
	public void teardown() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.close();
	}

	public void scroll()
	{
		WebElement scrollingElement = driver.findElement(By.xpath("//body/div[@class='content']/div[@class='content-outer']/div[@class='fauxborder-left content-fauxborder-left']/div[@class='content-inner']/div[@class='main-outer']/div[@class='fauxborder-left main-fauxborder-left']/div[@class='region-inner main-inner']/div[@class='columns fauxcolumns']/div[@class='columns-inner']/div[@class='column-right-outer']/div[@class='column-right-inner']/aside/div[@class='sidebar section']/div[3]"));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView();", scrollingElement);
	}

}
