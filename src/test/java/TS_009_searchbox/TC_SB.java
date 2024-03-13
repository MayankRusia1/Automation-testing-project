package TS_009_searchbox;

import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TC_SB 
{
	WebDriver driver ;
	ExtentReports extent;
	ExtentTest test;

	@BeforeSuite
	public void configurereport()
	{
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(".//Reports//SB_006.html");
		sparkReporter.config().setReportName("Search box functionality testing report");
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

	
	
	@Test(description = "Validate by clicking on image provided just before search bar, user is directed to Wikipedia main page, in new window.")
	public void SB_002()
	{
		test = extent.createTest("Search box functionality").assignAuthor("Mayank Rusia");
		WebElement image = driver.findElement(By.xpath("//img[@class='wikipedia-icon']"));
		image.click();
		int count = 0;

		Set<String> options = driver.getWindowHandles();

		String pagetitle1 = "Automation Testing Practice";
		String pagetitle2 = "Wikipedia, the free encyclopedia";
		String pageurl1 = "https://testautomationpractice.blogspot.com/";
		String pageurl2 = "https://en.wikipedia.org/wiki/Main_Page";
		
		for(String eString : options)
		{
			count++;
			driver.switchTo().window(eString);
			if(count == 1)
			{
				if(pagetitle1.equals(driver.getTitle()) && pageurl1.equals(driver.getCurrentUrl()))
				{
					test.pass("Automation Testing Practice is opened in 1 separate tab");
					test.addScreenCaptureFromBase64String(capturescreenshot());
				}
				else 
				{
				test.fail("Automation Testing Practice is NOT opened in 1 separate tab");	
				test.addScreenCaptureFromBase64String(capturescreenshot());
				}
			}
			if(count == 2)
			{
				if(pagetitle2.equals(driver.getTitle()) && pageurl2.equals(driver.getCurrentUrl()))
				{
					test.pass("Wikipedia, the free encyclopedia is opened in 1 separate tab");
					test.addScreenCaptureFromBase64String(capturescreenshot());
				}
				else 
				{
				test.fail("Wikipedia, the free encyclopedia is NOT opened in 1 separate tab");	
				test.addScreenCaptureFromBase64String(capturescreenshot());
				}				
			}
		}
		
	}

	
	
	@Test(description = "Validate Search functionality, without entering any text, clicking Search icon.")
	public void SB_003()
	{
		test = extent.createTest("Search box functionality").assignAuthor("Mayank Rusia");
		WebElement searchicon = driver.findElement(By.xpath("//input[@type='submit']"));
		searchicon.click();
		String acttext = driver.findElement(By.xpath("//div[@class='wikipedia-search-results']")).getText();
		String exptext = "Please enter text to search.";

		if(acttext.equals(exptext))
		{
			test.pass("Warning message is displayed");
		}
		else 
		{
		test.fail("Warning message is NOT displayed");
		test.addScreenCaptureFromBase64String(capturescreenshot());
		}
	}

	@Test(description = "Validate whether Search bar have appropriate placeholder.")
	public void SB_004()
	{
		test = extent.createTest("Search box functionality").assignAuthor("Mayank Rusia");
		WebElement searchBox = driver.findElement(By.xpath("//input[@class='wikipedia-search-input']"));
		String exptext = "Enter Search text here";
		String acttext = searchBox.getAttribute("value");
		if(exptext.equals(acttext))
		{
			test.pass("Proper placeholder is displayed");
		}
		else 
		{
		test.fail("Wrong / no placeholder is provided");
		test.addScreenCaptureFromBase64String(capturescreenshot());
		}
	}


	@Test(dataProvider = "sb_006", description = "Validate searching for invalid / non-existing option.")
	public void SB_006(String data) throws InterruptedException
	{
		test = extent.createTest("Search box functionality").assignAuthor("Mayank Rusia");

		WebElement searchbox = driver.findElement(By.xpath("//input[@class='wikipedia-search-input']"));
		WebElement searchicon = driver.findElement(By.xpath("//input[@type='submit']"));

		searchbox.sendKeys(data);
		searchicon.click();

		Thread.sleep(1500);

		String expwarning = "No results found.";
		String actwarning = driver.findElement(By.id("Wikipedia1_wikipedia-search-results")).getText();
		if(expwarning.equals(actwarning))
		{
			test.pass("Proper warning message is displayed");
		}
		else
		{
			test.fail("Wrong warning message is displayed");
			test.addScreenCaptureFromBase64String(capturescreenshot());
		}
	}

	@DataProvider(name = "sb_006")
	public Object[][] getdata1()
	{
		Object [][] data = {{"2365411"},{"!!!!$%#"}};
		return data;
	}
	
	
	@AfterTest
	public void teardown() throws InterruptedException
	{
		Thread.sleep(3000);
		driver.quit();
	}

}
