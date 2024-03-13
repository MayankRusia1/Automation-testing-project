package TS_017_DatePicker;

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

public class TC_DF_001 
{
WebDriver driver;

@Parameters("browser")
@BeforeTest
public void setup(@Optional("chrome") String browsername)
{
	if(browsername.equalsIgnoreCase("chrome"))
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	if(browsername.equalsIgnoreCase("firefox"))
	{
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	}
	if(browsername.equalsIgnoreCase("edge"))
	{
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
	}
	
}

@Test
public void DF_001()
{
	driver.manage().window().maximize();
	driver.get("https://testautomationpractice.blogspot.com/");
	scroll();
	WebElement datepicker = driver.findElement(By.xpath("//p[normalize-space()='Date:']"));
	Assert.assertTrue(datepicker.isDisplayed(), "Date picker is NOT loaded on page successfully");
}

@AfterTest
public void teardown() throws InterruptedException
{
	Thread.sleep(2500);
	driver.quit();
}

public void scroll()
{
	WebElement scrollingElement = driver.findElement(By.xpath("//p[normalize-space()='Date:']"));
	JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	jsExecutor.executeScript("arguments[0].scrollIntoView();", scrollingElement);

}
}