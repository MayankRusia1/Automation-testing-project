package TS_007_WebTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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



public class TC_WT
{
	WebDriver driver ;
	ExtentReports extent;
	ExtentTest test;

	@BeforeSuite
	public void configurereport()
	{
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(".//Reports//WT_003.html");
		sparkReporter.config().setReportName("WebTable functionality testing report");
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

	@BeforeTest
	public void setup()
	{
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://testautomationpractice.blogspot.com/");
	}


	@AfterTest
	public void teardown() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.close();
	}


	public String capturescreenshot()
	{
		String image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
		return image;
	}


	@Test(description = "Validate whether table data is properly arranged in any order (ascending / descending).")
	public void WT_002()
	{
		test = extent.createTest("Webtable data checking").assignAuthor("Mayank Rusia");

		scroll();
		List<WebElement> rows = driver.findElements(By.xpath("//table[@name='BookTable']/tbody/tr"));
		List<WebElement> column = driver.findElements(By.xpath("//table[@name='BookTable']/tbody/tr/th"));

		//		System.out.println("Rows: "+rows.size());
		//		System.out.println("Columns: "+column.size());

		List<WebElement> beforesort = driver.findElements(By.xpath("//table[@name='BookTable']/tbody/tr/td[1]"));

		List<String> stringData = new ArrayList<String>();

		//add data in List
		for (WebElement element : beforesort) 
		{
			stringData.add(element.getText());
		}

		//convert List to String array
		String[] originalStringArray = stringData.toArray(new String[0]);

		//original table data
		for(int i=0; i< originalStringArray.length; i++)
		{
			System.out.println(originalStringArray[i]);
		}

		// Sort the list of strings
		Collections.sort(stringData);

		// Convert the sorted list to an array
		String[] sortedStringArray = stringData.toArray(new String[0]);

		for(int i=0; i< sortedStringArray.length; i++)
		{
			System.out.println(sortedStringArray[i]);
		}

		if(originalStringArray.equals(sortedStringArray))
		{
			test.pass("WebTable data is arranged in ascending order");
		}
		else 
		{
			test.fail("WebTable data is NOT arranged in ascending order");
			test.addScreenCaptureFromBase64String(capturescreenshot(),"WebTable");
		}
	}




	@Test(description = "Validate whether Heading of webtable are properly labeled.")
	public void WT_003()
	{
		test = extent.createTest("Webtable heading checking").assignAuthor("Mayank Rusia");

		scroll();
		List<WebElement> rows = driver.findElements(By.xpath("//table[@name='BookTable']/tbody/tr/th"));

		List<String> stringData = new ArrayList<String>();

		//add data in List
		for (WebElement element : rows) 
		{
			stringData.add(element.getText());
		}

		//convert List to String array
		String[] originalStringArray = stringData.toArray(new String[0]);

		String [] expdata = {"BookName","Author","Subject","Price"};
		
				//comparing 2 arrays, using Arrays.equals() method
				if(Arrays.equals(expdata, originalStringArray))
				{
					test.pass("Heading of table is correctly displayed");
				}
				else 
				{
					test.fail("Heading of table is NOT correctly displayed");
					test.addScreenCaptureFromBase64String(capturescreenshot(),"WebTable headings");
		}

	}


	public void scroll()
	{
		WebElement scrolling = driver.findElement(By.xpath("//h2[normalize-space()='Web Table']"));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView();", scrolling);
	}

}
