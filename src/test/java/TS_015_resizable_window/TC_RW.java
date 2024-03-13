package TS_015_resizable_window;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
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

public class TC_RW 
{
	WebDriver driver ;
	ExtentReports extent;
	ExtentTest test;

	@BeforeSuite
	public void configurereport()
	{
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(".//Reports//RW_004.html");
		sparkReporter.config().setReportName("Resizing window functionality testing report");
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
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://testautomationpractice.blogspot.com/");
	}

	@Test(description = "Validate whether Resizable window , having text \"Resizable\" inside it.")
	public void RW_002()
	{
		test = extent.createTest("Resizable window functionality").assignAuthor("Mayank Rusia");
		scroll();
		WebElement resizing = driver.findElement(By.xpath("//h3[normalize-space()='Resizable']"));
		String exptext = "Resizable";
		String acttext = resizing.getText();
		if(exptext.equals(acttext))
		{
			test.pass("Resizable text is present & correctly spelled");
		}
		else 
		{
		test.fail("Resizable text is NOT present / not correctly spelled");
		test.addScreenCaptureFromBase64String(capturescreenshot());
		}
	}

	@Test(description = "Validate whether Resizable window have resizing icon at right-down corner.")
	public void RW_003()
	{
		test = extent.createTest("Resizable window functionality").assignAuthor("Mayank Rusia");
		scroll();
		WebElement resizeicon = driver.findElement(By.xpath("//div[@class='ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se']"));
		if(resizeicon.isDisplayed())
		{
			test.pass("Resizing icon is present");
		}
		else 
		{
		test.fail("Resizing icon is NOT present");
		test.addScreenCaptureFromBase64String(capturescreenshot());
		}
	}

	@Test(description = "Validate whether resizable window is able to resize window in all 4 directions (left, right, up, down) using mouse.")
	public void RW_004() throws InterruptedException
	{
		test = extent.createTest("Resizable window functionality").assignAuthor("Mayank Rusia");
		scroll();
		WebElement resizeicon = driver.findElement(By.xpath("//div[@class='ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se']"));
		
		Actions actions = new Actions(driver);
		System.out.println("Moving in x axis (left - right)");
		actions.dragAndDropBy(resizeicon, 80, 0).build().perform();					Thread.sleep(1000);
		actions.dragAndDropBy(resizeicon, -30, 0).build().perform();				Thread.sleep(1000);
		
		
		System.out.println("Moving in y axis (up - down)");
		actions.dragAndDropBy(resizeicon, 0, 90).build().perform();					Thread.sleep(1000);
		
		actions.dragAndDropBy(resizeicon, 0, -30).build().perform();				Thread.sleep(1000);
		
		
		System.out.println("Moving in both direction simultaneously");
		actions.dragAndDropBy(resizeicon, 150, 80).build().perform();				Thread.sleep(1000);
		
		
		actions.dragAndDropBy(resizeicon, -50, -10).build().perform();				Thread.sleep(1000);
		
		
		actions.dragAndDropBy(resizeicon, 150, -80).build().perform();				Thread.sleep(1000);
		
		
		actions.dragAndDropBy(resizeicon, -20, 100).build().perform();				Thread.sleep(1000);

		
	}


	@AfterTest
	public void teardown() throws InterruptedException
	{
		Thread.sleep(2500);
		driver.close();
	}

	public void scroll()
	{
		WebElement scrollingElement = driver.findElement(By.xpath("//h2[normalize-space()='Resizable']"));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView();", scrollingElement);
	}
}