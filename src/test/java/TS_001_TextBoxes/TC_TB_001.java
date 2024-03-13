package TS_001_TextBoxes;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_TB_001 
{
	WebDriver driver;	

	@BeforeTest
	@Parameters({"browser"})
	public void setup(String browser)
	{
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

		if(browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
	}


	@Parameters({"browser"})
	@Test(description = "Validate the working of textboxes, by entering all valid data into (Name, Email, Phone, Address) textboxes.")
	public void TB_001(String browser)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.get("https://testautomationpractice.blogspot.com/");
		WebElement name = driver.findElement(By.xpath("//input[@id='name']"));
		name.sendKeys("Aman Gupta");												

		WebElement email = driver.findElement(By.xpath("//input[@id='email']"));	
		email.sendKeys("amangupta667@yahoo.com");

		WebElement phone = driver.findElement(By.xpath("//input[@id='phone']"));	
		phone.sendKeys("9826311979");

		WebElement address = driver.findElement(By.xpath("//textarea[@id='textarea']"));
		address.sendKeys("543/4 Saharangpur road, Nagpur, Maharashtra");			
		
		//assertions
		SoftAssert sAssert = new SoftAssert();

		String expname = "Aman Gupta";
		String actname = name.getAttribute("value");
		sAssert.assertEquals(actname, expname,"Name is not correctly entered");
	
		
		String expemail = "amangupta667@yahoo.com";
		String actemail = email.getAttribute("value");
		sAssert.assertEquals(actemail, expemail,"Email is not correctly entered");
		
		
		String expphone = "9826311979";
		String actphone = phone.getAttribute("value");
		sAssert.assertEquals(actphone, expphone,"Phone number is not correctly entered");
		
		String expaddress = "543/4 Saharangpur road, Nagpur, Maharashtra";
		String actaddress = address.getAttribute("value");
		sAssert.assertEquals(actaddress, expaddress,"Address is not correctly entered");
		
		sAssert.assertAll();
	}

	@AfterTest
	public void teardown()
	{
		driver.quit();
	}
	
}
