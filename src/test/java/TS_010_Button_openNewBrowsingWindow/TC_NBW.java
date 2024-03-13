package TS_010_Button_openNewBrowsingWindow;

import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_NBW 
{
	WebDriver driver ;
	ExtentReports extent;
	ExtentTest test;

	@BeforeSuite
	public void configurereport()
	{
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(".//Reports//NBW_004.html");
		sparkReporter.config().setReportName("New Browsing window functionality testing report");
		sparkReporter.config().setDocumentTitle("Test Execution Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
	}

	@AfterSuite
	public void geneartereport()
	{
		extent.flush();
	}


	public String capturescreenshot()
	{
		String image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
		return image;
	}

	@BeforeTest
	public void setup()
	{
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://testautomationpractice.blogspot.com/");
	}



	@Test(description = "Validate whether  \"New Browser Window\" button is navigating to \"open cart\" home page.")
	public void NBW_002()
	{
		test = extent.createTest("New browser window button functionality").assignAuthor("Mayank Rusia");
		WebElement buttonElement = driver.findElement(By.xpath("//button[normalize-space()='New Browser Window']"));
		buttonElement.click();

		Set<String> options = driver.getWindowHandles();

		String pagetitle1 = "Automation Testing Practice";
		String pagetitle2 = "Your Store";
		String pageurl1 = "https://testautomationpractice.blogspot.com/";
		String pageurl2 = "https://demo.opencart.com/";
		int count=0;

		for(String eString : options)
		{
			count++;
			driver.switchTo().window(eString);
			if(count == 1)
			{
				if(pagetitle1.equals(driver.getTitle()) && pageurl1.equals(driver.getCurrentUrl()))
				{
					test.pass("Automation Testing Practice is opened in 1 separate tab");
					test.addScreenCaptureFromBase64String(capturescreenshot(),"Automation testing practice");
				}
				else 
				{
					test.fail("Automation Testing Practice is NOT opened in 1 separate tab");	
					test.addScreenCaptureFromBase64String(capturescreenshot(),"Automation testing practice");
				}
			}
			if(count == 2)
			{
				if(pagetitle2.equals(driver.getTitle()) && pageurl2.equals(driver.getCurrentUrl()))
				{
					test.pass("Your Store is opened in 1 separate tab");
					test.addScreenCaptureFromBase64String(capturescreenshot(),"Your store");
				}
				else 
				{
					test.fail("Your Store is NOT opened in 1 separate tab");	
					test.addScreenCaptureFromBase64String(capturescreenshot(),"Your store");
				}				
			}
		}


	}

	@Test(description = "Validate whether after clicking on \"New Browser window\" button, one new browsing window opens.")
	public void NBW_003()
	{
		test = extent.createTest("check multiple browser window opens").assignAuthor("Mayank Rusia");
		WebElement buttonElement = driver.findElement(By.xpath("//button[normalize-space()='New Browser Window']"));
		buttonElement.click();

		Set<String> windowhandles = driver.getWindowHandles();
		System.out.println(windowhandles);

		int count =0;
		for(String e : windowhandles)
		{
			count++;
			driver.switchTo().window(e);
		}

		if(count > 1)
		{
			test.pass("A new browser window opens");
		}
		else 
		{
			test.fail("No new browser window opens , after clicking on button");			
			test.addScreenCaptureFromBase64String(capturescreenshot());
		}
	}



	@Test(description = "Validate features of New browser window button")
	public void NBW_004()
	{
		test = extent.createTest("New browser window button").assignAuthor("Mayank Rusia");
		WebElement buttonElement = driver.findElement(By.xpath("//button[normalize-space()='New Browser Window']"));
		String exptext = "New Browser Window";
		String acttext = buttonElement.getText();

		if(exptext.equals(acttext))
		{
			test.pass("Correct spelling of New browser window button");
		}
		else 
		{	
			test.fail("Wrong spelling is displayed on button");
			test.addScreenCaptureFromBase64String(capturescreenshot());
		}
	}	

	@AfterTest
	public void teardown() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.quit();
	}
}
