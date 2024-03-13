package TS_002_RadioButtons;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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

public class TC_RB 
{
	WebDriver driver;
	ExtentReports extent;
	ExtentTest test;

	@BeforeSuite
	public void setting() 
	{
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(".//Reports//RB_008.html");
		sparkReporter.config().setReportName("Radio Button functionality testing report");
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
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://testautomationpractice.blogspot.com/");
	}

	
	@Test(description = "Validate the working of Radio Button, whether they are Displayed, Enabled.")
	public void RB_001()
	{
		test = extent.createTest("Radio button display, enable").assignAuthor("Mayank Rusia");
		
		scroll();
		WebElement maleElement = driver.findElement(By.xpath("//input[@id='male']"));
		WebElement femaleElement = driver.findElement(By.xpath("//input[@id='female']"));

		if(maleElement.isDisplayed() && maleElement.isEnabled())
		{
			test.pass("Male radio button is displayed & enabled");
		}
		else 
		{
			test.fail("Male radio button is NOT displayed & it is disabled");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Male radio button");
		}
		
		if(femaleElement.isDisplayed() && femaleElement.isEnabled())
		{
			test.pass("Female radio button is displayed & enabled");			
		}
		else 
		{
			test.fail("Female radio button is NOT displayed & it is disabled");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Female radio button");			
		}

	}


	@Test(description = "Validate whether only one radio button is Selected at a time & other remain unselected.")
	public void RB_002()
	{
		test = extent.createTest("Radio button function").assignAuthor("Mayank Rusia");

		scroll();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		WebElement maleElement = driver.findElement(By.xpath("//input[@id='male']"));
		WebElement femaleElement = driver.findElement(By.xpath("//input[@id='female']"));

		maleElement.click();
		if(maleElement.isSelected() && !femaleElement.isSelected())
		{
			test.pass("Male radio button is Selected & female radio button is unselected");
		}
		else 
		{
			test.fail("Male radio button is NOT working as per requirement");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Male radio button");
		}
	}

	@Test(description = "Validate whether 'Male' radio button is selected by clicking on 'Male' Text.")
	public void RB_003()
	{
		test = extent.createTest("Male radio button, by clicking text").assignAuthor("Mayank Rusia");

		scroll();
		WebElement maletext = driver.findElement(By.xpath("//label[normalize-space()='Male']"));
		maletext.click();
		WebElement maleElement = driver.findElement(By.xpath("//input[@id='male']"));
		if(maleElement.isSelected())
		{
			test.pass("Male radio button is Selected"); 
		}
		else 
		{
			test.fail("Male radio button is NOT working as per requirement");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Male radio button");
		}
	}


	@Test(description = "Validate whether 'Male' radio button is selected by clicking on 'Male' radio button.")
	public void RB_004()
	{
		test = extent.createTest("Male radio button, by clicking button").assignAuthor("Mayank Rusia");
		scroll();
		WebElement maleElement = driver.findElement(By.xpath("//input[@id='male']"));
		maleElement.click();
		if(maleElement.isSelected())
		{
			test.pass("Male radio button is Selected"); 
		}
		else 
		{
			test.fail("Male radio button is NOT working as per requirement");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Male radio button");
		}
	}

	@Test(description = "Validate  whether 'Female' radio button is selected by clicking on 'Female' Text.")
	public void RB_005()
	{
		test = extent.createTest("Female radio button, by clicking text").assignAuthor("Mayank Rusia");

		scroll();
		WebElement femaletext = driver.findElement(By.xpath("//label[normalize-space()='Female']"));
		femaletext.click();
		WebElement femaleElement = driver.findElement(By.xpath("//input[@id='female']"));

		if(femaleElement.isSelected())
		{
			test.pass("Female radio button is Selected"); 
		}
		else 
		{
			test.fail("Female radio button is NOT working as per requirement");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Female radio button");
		}
	}


	@Test(description = "Validate whether 'Female' radio button is selected by clicking on 'Female' radio button.")
	public void RB_006()
	{
		test = extent.createTest("Female radio button, by clicking button").assignAuthor("Mayank Rusia");

		scroll();
		WebElement femaleElement = driver.findElement(By.xpath("//input[@id='female']"));
		femaleElement.click();
		if(femaleElement.isSelected())
		{
			test.pass("Female radio button is Selected"); 
		}
		else 
		{
			test.fail("Female radio button is NOT working as per requirement");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Female radio button");
		}

	}


	@Test(description = "Validate UI of radio button functionality.")
	public void RB_008()
	{
		test = extent.createTest("UI of Radio buttons").assignAuthor("Mayank Rusia");

		scroll();
		WebElement maletext = driver.findElement(By.xpath("//label[normalize-space()='Male']"));
		WebElement femaletext = driver.findElement(By.xpath("//label[normalize-space()='Female']"));

		String expmaletext = "Male";
		String actmaletext = maletext.getText();
		String expfemaletext = "Female";
		String actfemaletext = femaletext.getText();
		
		if(expmaletext.equals(actmaletext))
		{
			test.pass("Male text is correctly spelled");
		}
		else
		{
			test.fail("Male text is Wrong spelled");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Male radio button");
		}
		
		if(expfemaletext.equals(actfemaletext))
		{
			test.pass("Female text is correctly spelled");			
		}
		else 
		{
			test.fail("Female text is Wrong spelled");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Female radio button");			
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
		WebElement scrolling = driver.findElement(By.xpath("//label[normalize-space()='Gender:']"));
		JavascriptExecutor jExecutor = (JavascriptExecutor) driver;
		jExecutor.executeScript("arguments[0].scrollIntoView();", scrolling);

	}
	
	public String captureScreenshot()
	{
		String image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
		return image;
	}
	
}