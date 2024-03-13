package TS_007_WebTable;

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
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_WT_001 
{
WebDriver driver;

@Parameters("browser")
@BeforeTest
public void setup(@Optional("firefox") String browsername)
{
	if(browsername.equalsIgnoreCase("chrome"))
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		System.out.println("Chrome browser launched");
	}
	if(browsername.equalsIgnoreCase("edge"))
	{
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		System.out.println("Edge browser launched");
	}
	if(browsername.equalsIgnoreCase("firefox"))
	{
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		System.out.println("Firefox browser launched");
	}
	
}

@Test(description = "Cross browser testing")
public void WT_001()
{
	scroll();
	driver.manage().window().maximize();
	driver.get("https://testautomationpractice.blogspot.com/");
	WebElement tablElement = driver.findElement(By.xpath("//body/div[@class='content']/div[@class='content-outer']/div[@class='fauxborder-left content-fauxborder-left']/div[@class='content-inner']/div[@class='main-outer']/div[@class='fauxborder-left main-fauxborder-left']/div[@class='region-inner main-inner']/div[@class='columns fauxcolumns']/div[@class='columns-inner']/div[@class='column-center-outer']/div[@class='column-center-inner']/div[@name='Main']/div[2]"));
	Assert.assertTrue(tablElement.isDisplayed(), "WebTable is NOT displayed on page");
}


@AfterTest
public void teardown() throws InterruptedException
{
	Thread.sleep(2000);
	driver.close();
}

public void scroll()
{
	WebElement scrolling = driver.findElement(By.xpath("//h2[normalize-space()='Web Table']"));
	JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	jsExecutor.executeScript("arguments[0].scrollIntoView();", scrolling);
}
}
