package TS_005_Colors_dropdown;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
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
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_COLORDD 
{
	WebDriver driver ;
	ExtentReports extent;
	ExtentTest test;

	@BeforeSuite
	public void configurereport()
	{
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(".//Reports//COLORDD_006.html");
		sparkReporter.config().setReportName("Color drop-down functionality testing report");
		sparkReporter.config().setDocumentTitle("Test Execution Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

	}



	@AfterSuite
	public void generatereport()
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

	@Test(description = "Validate whether drop-down option is displayed.")
	public void COLORDD_001()
	{
		test = extent.createTest("Color drop-down").assignAuthor("Mayank Rusia");
		scroll();
		WebElement dd = driver.findElement(By.xpath("//select[@id='colors']"));

		if(dd.isDisplayed())
		{
			test.pass("Color drop-down is displayed on page");
		}
		else 
		{
			test.fail("Color drop-down is NOT displayed properly on page");
			test.addScreenCaptureFromBase64String(capturescreenshot(),"Color drop-down");
		}
	}


	@Test(description = "Validate an element can be selected from drop-down list ")
	public void COLORDD_002()
	{
		test = extent.createTest("Color drop-down, selecting element from it").assignAuthor("Mayank Rusia");
		scroll();
		WebElement dd = driver.findElement(By.xpath("//select[@id='colors']"));
		Select select = new Select(dd);
		select.selectByVisibleText("Blue");

		WebElement selectedoption =	select.getFirstSelectedOption();
		String actselectedtext = selectedoption.getText();
		String expselectedtext = "Blue";

		if(actselectedtext.equals(expselectedtext))
		{
			test.pass("Blue option is selected");
		}
		else
		{
			test.fail("Blue is NOT selected");
			test.addScreenCaptureFromBase64String(capturescreenshot(),"Color drop-down");
		}
	}

	@Test(description = "Validate whether Drop-down elements are arranged in proper order.")
	public void COLORDD_003()
	{
		test = extent.createTest("Color drop-down element arrangement").assignAuthor("Mayank Rusia");
		scroll();
		WebElement dd = driver.findElement(By.xpath("//select[@id='colors']"));
		Select select = new Select(dd);

		List orglist = new ArrayList();
		List<WebElement> options1 = select.getOptions();

		//printing drop-down options
		for(WebElement e : options1)
		{
			orglist.add(e.getText());
		}
		System.out.println("Original list: "+orglist);

		List templist = new ArrayList();
		List<WebElement> options2 = select.getOptions();

		//printing drop-down options
		for(WebElement e1 : options2)
		{
			templist.add(e1.getText());
		}

		//sorting
		Collections.sort(templist);


		System.out.println("Sorted list: "+templist);

		if(templist ==  orglist)
		{
			test.pass("Color drop-down elements are arranged in ascending order");
		}
		else 
		{
			test.fail("Color drop-down elements are NOT arranged in proper order");
			test.addScreenCaptureFromBase64String(capturescreenshot(),"Color drop-down");
		}

	}

	@Test(description = "Validate different ways to select a option from drop-down list.")
	public void COLORDD_005() throws InterruptedException
	{
		test = extent.createTest("Ways to select option from color drop-down").assignAuthor("Mayank Rusia");
		scroll();
		WebElement dd = driver.findElement(By.xpath("//select[@id='colors']"));
		Select select = new Select(dd);
		select.selectByVisibleText("Red");
		test.log(Status.INFO, "Red is selected");
		test.addScreenCaptureFromBase64String(capturescreenshot(),"Red option");
		Thread.sleep(1500);
		select.selectByIndex(2);
		test.log(Status.INFO, "Green is selected");
		test.addScreenCaptureFromBase64String(capturescreenshot(),"Green option");	
		Thread.sleep(1500);
		select.selectByValue("blue");
		test.log(Status.INFO, "Blue is selected");
		test.addScreenCaptureFromBase64String(capturescreenshot(),"Blue option");
	}

	@Test(description = "Validate jumping directly to the options from drop-down list by typing colors initials through keyboard.")
	public void COLORDD_006()
	{
		test = extent.createTest("Direct selecting option").assignAuthor("Mayank Rusia");
		scroll();
		WebElement dd = driver.findElement(By.xpath("//select[@id='colors']"));
		dd.click();
		test.info("Click on color drop-down");
		dd.sendKeys("b");
		test.info("Type 'b' through keyboard");
		dd.sendKeys(Keys.ENTER);
		test.info("Press ENTER key");
		test.addScreenCaptureFromBase64String(capturescreenshot(),"Blue option");
	}


	@AfterTest
	public void teardown() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.close();
	}


	public void scroll()
	{
		WebElement scrollingElement = driver.findElement(By.xpath("//label[normalize-space()='Colors:']"));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView();", scrollingElement);
	}


	public String capturescreenshot()
	{
		String image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
		return image;
	}

}