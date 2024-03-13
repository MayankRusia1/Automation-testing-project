package TS_006_Hyperlinks;

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

public class TC_HL_001 
{
	WebDriver driver = null;
	
@Parameters("browser")	
@BeforeTest
public void setup(String browser)
{
	if(browser.equalsIgnoreCase("edge"))
	{
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
	}
	if(browser.equalsIgnoreCase("firefox"))
	{
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}
	if(browser.equalsIgnoreCase("chrome"))
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
}

@Test
public void HL_001()
{
	driver.get("https://testautomationpractice.blogspot.com/");
	WebElement orangehrm = driver.findElement(By.xpath("//a[normalize-space()='orange HRM']"));
	WebElement opencart = driver.findElement(By.xpath("//a[normalize-space()='open cart']"));
	WebElement home = driver.findElement(By.xpath("//a[normalize-space()='Home']"));
	
	SoftAssert softAssert = new SoftAssert();
	softAssert.assertTrue(orangehrm.isDisplayed(), "Orange hrm link is loaded & diplayed successfully on page");
	softAssert.assertTrue(orangehrm.isEnabled(), "Orange hrm link is Enabled");
	softAssert.assertTrue(opencart.isDisplayed(), "Open cart link is loaded & diplayed successfully on page");
	softAssert.assertTrue(opencart.isEnabled(), "Open cart link is Enabled");
	softAssert.assertTrue(home.isDisplayed(), "Home link is loaded & diplayed successfully on page");
	softAssert.assertTrue(home.isEnabled(), "Home link is Enabled");

}

@AfterTest
public void teardown() throws InterruptedException
{
Thread.sleep(2500);
driver.close();
}
}
