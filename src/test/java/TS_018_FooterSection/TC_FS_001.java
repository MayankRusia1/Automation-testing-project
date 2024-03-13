package TS_018_FooterSection;

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

public class TC_FS_001 
{
	WebDriver driver = null;
	
	
@Parameters("browser")	
@BeforeTest
public void setup(String browsername)
{
	if(browsername.equalsIgnoreCase("firefox"))
	{
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}
	
	if(browsername.equalsIgnoreCase("edge"))
	{
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
	}
	
	if(browsername.equalsIgnoreCase("chrome"))
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
}

@Test(description = "Cross browser testing")
public void FS_001()
{
	driver.get("https://testautomationpractice.blogspot.com/");
	scroll();
	WebElement bottom = driver.findElement(By.xpath("//div[@class='widget Attribution']//div[@class='widget-content']"));
	Assert.assertTrue(bottom.isDisplayed(),"Footer section is NOT displayed on page");
}

@AfterTest
public void teardown() throws InterruptedException
{
	Thread.sleep(2000);
	driver.quit();
}


public void scroll()
{
	WebElement scrolling = driver.findElement(By.xpath("//div[@class='widget Attribution']//div[@class='widget-content']"));
	JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	jsExecutor.executeScript("arguments[0].scrollIntoView();",scrolling);
}
}
