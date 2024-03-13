package TS_003_CheckBoxes;

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
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TB_CB 
{
	WebDriver driver;
	ExtentReports extent;
	ExtentTest test;

	@BeforeSuite
	public void setting() 
	{
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(".//Reports//CB_006.html");
		sparkReporter.config().setReportName("Checkbox functionality testing report");
		sparkReporter.config().setDocumentTitle("Test Execution Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
	}

	@AfterSuite
	public void generateReport()
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
	
	@Test(description = "Validate whether \"Days\" category Check Boxes are Displayed & Enabled.")
	public void CB_002()
	{
		test = extent.createTest("UI of checkboxes").assignAuthor("Mayank Rusia");
		
		scroll();		
		WebElement sunday = driver.findElement(By.xpath("//input[@id='sunday']"));
		WebElement monday = driver.findElement(By.xpath("//input[@id='monday']"));
		WebElement tuesday = driver.findElement(By.xpath("//input[@id='tuesday']"));
		WebElement wednesday = driver.findElement(By.xpath("//input[@id='wednesday']"));
		WebElement thursday = driver.findElement(By.xpath("//input[@id='thursday']"));
		WebElement friday = driver.findElement(By.xpath("//input[@id='friday']"));
		WebElement saturday = driver.findElement(By.xpath("//input[@id='saturday']"));
		
		if(sunday.isDisplayed() && sunday.isEnabled())
		{
			test.pass("Sunday checkbox is displayed & enabled");
		}
		else 
		{
			test.fail("Sunday checkbox is NOT displayed & enabled");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Sunday checkbox");
		}
		
		if(monday.isDisplayed() && monday.isEnabled())
		{
			test.pass("Monday checkbox is displayed & enabled");			
		}
		else 
		{
			test.fail("Monday checkbox is NOT displayed & enabled");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Monday checkbox");			
		}
		if(tuesday.isDisplayed() && tuesday.isEnabled())
		{
			test.pass("Tuesday checkbox is displayed & enabled");
		}
		else 
		{
			test.fail("Tuesday checkbox is NOT displayed & enabled");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Tuesday checkbox");
		}
		if(wednesday.isDisplayed() && wednesday.isEnabled())
		{
			test.pass("Wednesday checkbox is displayed & enabled");			
		}
		else 
		{
			test.fail("Wednesday checkbox is NOT displayed & enabled");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Wednesday checkbox");			
		}
		if(thursday.isDisplayed() && thursday.isEnabled())
		{
			test.pass("Thursday checkbox is displayed & enabled");
		}
		else 
		{
			test.fail("Thursday checkbox is NOT displayed & enabled");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Thursday checkbox");
		}
		if(friday.isDisplayed() && friday.isEnabled())
		{
			test.pass("Friday checkbox is displayed & enabled");
		}
		else 
		{
			test.fail("Friday checkbox is NOT displayed & enabled");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Friday checkbox");
		}
		if(saturday.isDisplayed() && saturday.isEnabled())
		{
			test.pass("Saturday checkbox is displayed & enabled");
		}
		else 
		{
			test.fail("Saturday checkbox is NOT displayed & enabled");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Saturday checkbox");
		}
		
	}
	
	
	
	@Test(description = "Validate whether \"Days\" category Check Boxes, allows to select multiple checkboxes.")
	public void CB_003() throws InterruptedException
	{
		test = extent.createTest("Selecting multiple checkboxes").assignAuthor("Mayank Rusia");

		scroll();		
		WebElement monday = driver.findElement(By.xpath("//input[@id='monday']"));
		WebElement wednesday = driver.findElement(By.xpath("//input[@id='wednesday']"));
		WebElement saturday = driver.findElement(By.xpath("//input[@id='saturday']"));
		WebElement sunday = driver.findElement(By.xpath("//input[@id='sunday']"));

		monday.click();						Thread.sleep(2000);
		wednesday.click();					Thread.sleep(2000);
		saturday.click();					Thread.sleep(2000);
		sunday.click();						Thread.sleep(2000);
		
		if(monday.isSelected())
		{
			test.pass("Monday checkbox is selected");
		}
		else 
		{
			test.fail("Monday checkbox is NOT selected");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Monday checkbox");
		}
		
		if(wednesday.isSelected())
		{
			test.pass("Wednesday checkbox is selected");
		}
		else 
		{
			test.fail("Wednesday checkbox is NOT selected");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Wednesday checkbox");
		}
		
		if(saturday.isSelected())
		{
			test.pass("Saturday checkbox is selected");
		}
		else 
		{
			test.fail("Saturday checkbox is NOT selected");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Saturday checkbox");
		}
		
		if(sunday.isSelected())
		{
			test.pass("Sunday checkbox is selected");
		}
		else 
		{
			test.fail("Sunday checkbox is NOT selected");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Sunday checkbox");
		}
		
	}
	
	@Test(description = "Validate the working of Check boxes of (selecting & unselecting) by click action.")
	public void CB_004() throws InterruptedException
	{
		test = extent.createTest("Select-unselect functionality").assignAuthor("Mayank Rusia");

		scroll();		
		WebElement tuesday = driver.findElement(By.xpath("//input[@id='tuesday']"));
		tuesday.click();
		if(tuesday.isSelected())
		{
			test.pass("Tuesday checkbox is selected");
		}
		else 
		{
			test.fail("Tuesday checkbox is NOT selected");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Tuesday checkbox");
		}
		
		Thread.sleep(2000);
		
		tuesday.click();
		if(!tuesday.isSelected())
		{
			test.pass("Tuesday checkbox is unselected");
		}
		else 
		{
			test.fail("Tuesday checkbox is still selected");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Tuesday checkbox");
		}
		
		
	}

	
	@Test(description = "Validate the working of checkboxes, by clicking on their \"Text\", Checkbox gets selected.")
	public void CB_005()
	{
		test = extent.createTest("Checkbox functionality").assignAuthor("Mayank Rusia");

		scroll();		
		WebElement thursdaytext = driver.findElement(By.xpath("//label[normalize-space()='Thursday']"));
		WebElement thursdaybutton = driver.findElement(By.xpath("//input[@id='thursday']"));
		
		thursdaytext.click();
		
		if(thursdaybutton.isSelected())
		{
			test.pass("Thursday checkbox is selected, by clicking on Thursday text");
		}
		else 
		{
			test.fail("Thursday checkbox is NOT selected, by clicking Thursday text");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Thursday checkbox");
		}
		
	}

	
	@Test(description = "Validate the working of checkboxes, by clicking on their \"Button\", Checkbox gets selected.")
	public void CB_006()
	{
		test = extent.createTest("Checkboxes functionality").assignAuthor("Mayank Rusia");

		scroll();		
		WebElement thursdaybutton = driver.findElement(By.xpath("//input[@id='thursday']"));

		thursdaybutton.click();
		if(thursdaybutton.isSelected())
		{
			test.pass("Thursday checkbox is selected, by clicking on Thursday text");
		}
		else 
		{
			test.fail("Thursday checkbox is NOT selected, by clicking Thursday text");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Thursday checkbox");
		}
	
	}

	@AfterTest
	public void teardown() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.quit();
	}

	
	public void scroll()
	{
		WebElement scrolling = driver.findElement(By.xpath("//label[normalize-space()='Days:']"));
		JavascriptExecutor jExecutor = (JavascriptExecutor) driver;
		jExecutor.executeScript("arguments[0].scrollIntoView();",scrolling);
	}
	
	public String captureScreenshot()
	{
		String image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
		return image;
	}
	
}