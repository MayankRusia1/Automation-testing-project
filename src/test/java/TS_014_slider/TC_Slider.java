package TS_014_slider;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
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

public class TC_Slider
{
	WebDriver driver ;
	ExtentReports extent;
	ExtentTest test;

	@BeforeSuite
	public void configurereport()
	{
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(".//Reports//Slider_004.html");
		sparkReporter.config().setReportName("Slider functionality testing report");
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


	@Test(description = "Validate whether Slider is able to scroll at discrete points by clicking through mouse on slider bar.")
	public void Slider_002() throws InterruptedException
	{
		test = extent.createTest("Slider functionality").assignAuthor("Mayank Rusia");
		scroll();
		WebElement sliderbar = driver.findElement(By.xpath("//span[@class='ui-slider-handle ui-corner-all ui-state-default']"));
		Actions actions = new Actions(driver);
		test.addScreenCaptureFromBase64String(capturescreenshot(),"Original position");
		actions.dragAndDropBy(sliderbar, 10, 0).build().perform();					Thread.sleep(1000);
		test.addScreenCaptureFromBase64String(capturescreenshot(),"1st new position");
		actions.dragAndDropBy(sliderbar, 50, 0).build().perform();					Thread.sleep(1000);
		test.addScreenCaptureFromBase64String(capturescreenshot(),"2nd new position");		
		actions.dragAndDropBy(sliderbar, -30, 0).build().perform();					Thread.sleep(1000);
		test.addScreenCaptureFromBase64String(capturescreenshot(),"3rd new position");
		actions.dragAndDropBy(sliderbar, 60, 0).build().perform();					Thread.sleep(1000);
		test.addScreenCaptureFromBase64String(capturescreenshot(),"4th new position");
		actions.dragAndDropBy(sliderbar, -50, 0).build().perform();					Thread.sleep(1000);
		test.addScreenCaptureFromBase64String(capturescreenshot(),"5th new position");
	}

	
	@Test(description = "Validate whether Slider is smoothly scrolling  from minimium to maximum value.")
	public void Slider_003() throws InterruptedException
	{
		test = extent.createTest("Slider functionality").assignAuthor("Mayank Rusia");
		scroll();
		WebElement sliderbar = driver.findElement(By.xpath("//span[@class='ui-slider-handle ui-corner-all ui-state-default']"));
		Actions actions = new Actions(driver);

		int i=0;
		while(i<30)
		{
			actions.dragAndDropBy(sliderbar, i, 0).build().perform();				
			i++;
		}
		test.pass("Slider is moving smoothly");

	}

	@Test(description = "Validate whether Slider is smoothly scrolling using keyboard keys (Up, Down Left , Right) from minimium to maximum value.")
	public void Slider_004()
	{
		test = extent.createTest("Slider functionality").assignAuthor("Mayank Rusia");
		scroll();
		WebElement sliderbar = driver.findElement(By.xpath("//span[@class='ui-slider-handle ui-corner-all ui-state-default']"));

		sliderbar.click();									test.info("Slider bar is clicked");
		sliderbar.sendKeys(Keys.ARROW_RIGHT);				test.info("Right keyboard key is clicked");
		sliderbar.sendKeys(Keys.ARROW_RIGHT);				test.info("Right keyboard key is clicked");
		sliderbar.sendKeys(Keys.ARROW_LEFT);				test.info("Left keyboard key is clicked");
		sliderbar.sendKeys(Keys.ARROW_LEFT);				test.info("Left keyboard key is clicked");
		sliderbar.sendKeys(Keys.ARROW_RIGHT);				test.info("Right keyboard key is clicked");
		sliderbar.sendKeys(Keys.ARROW_LEFT);				test.info("Left keyboard key is clicked");
		sliderbar.sendKeys(Keys.ARROW_RIGHT);				test.info("Right keyboard key is clicked");
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