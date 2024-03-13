package TS_002_RadioButtons;

import org.openqa.selenium.By;
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

public class TC_RB_007
{
	WebDriver driver;	
	
	@Parameters("browser")
	@BeforeTest
	public void setup(String browser)
	{
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
		
		if(browser.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().maximize();
		}
		
	}
	
	
	@Test(description = "Validate 'Radio buttons' functionality working consistent in different environments.")
	public void TB_001()
	{
		driver.get("https://testautomationpractice.blogspot.com/");
		WebElement maleElement = driver.findElement(By.xpath("//input[@id='male']"));
		WebElement femaleElement = driver.findElement(By.xpath("//input[@id='female']"));
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(maleElement.isDisplayed(),"Male radio button not loaded successfully");
		softAssert.assertTrue(femaleElement.isDisplayed(),"Female radio button not loaded successfully");
		softAssert.assertAll();
	}
	
	@AfterTest
	public void teardown()
	{
		driver.quit();
	}

}
