package TS_014_slider;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_Slider_001 
{
WebDriver driver;

@Parameters("browser")
@BeforeTest
public void setup(@Optional("edge") String browsername)
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


@Test(description = "Cross browser testing")
public void Slider_001()
{
	driver.manage().window().maximize();
	driver.get("https://testautomationpractice.blogspot.com/");
	scroll();
	WebElement slider = driver.findElement(By.xpath("//div[@id='slider']"));
	assertTrue(slider.isDisplayed(), "Slider is NOT loaded on page");	
}

@AfterTest
public void teardown() throws InterruptedException
{
	Thread.sleep(2000);
	driver.close();
}

public void scroll()
{
	WebElement scrollingElement = driver.findElement(By.xpath("//h2[normalize-space()='Slider']"));
	JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	jsExecutor.executeScript("arguments[0].scrollIntoView();", scrollingElement);
}
}
