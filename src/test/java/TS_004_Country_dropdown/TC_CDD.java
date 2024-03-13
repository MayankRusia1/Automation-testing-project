package TS_004_Country_dropdown;

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
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;




public class TC_CDD 
{
	WebDriver driver ;
	ExtentReports extent;
	ExtentTest test;

	@BeforeSuite
	public void setting() 
	{
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(".//Reports//CDD_006.html");
		sparkReporter.config().setReportName("Country drop-down functionality testing report");
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

	@Test(description = "Validate whether drop-down elements are displayed after clicking on the drop-down list.")
	public void TC_CDD_001() throws InterruptedException
	{
		test = extent.createTest("Country drop-down").assignAuthor("Mayank Rusia");	
		scroll();
		WebElement dd = driver.findElement(By.xpath("//select[@id='country']"));
		dd.click();
		Thread.sleep(1500);
		test.addScreenCaptureFromBase64String(captureScreenshot(),"Country drop-down");
		test.pass("Country drop-down is working fine & get opened when clicked on it");
	}

	@Test(description = "Validate whether elements is able to select from drop-down")
	public void TC_CDD_002() throws InterruptedException
	{
		test = extent.createTest("Country drop-down").assignAuthor("Mayank Rusia");
		scroll();
		WebElement dd = driver.findElement(By.xpath("//select[@id='country']"));
		Select select = new Select(dd);
		select.selectByVisibleText("India");

		//retrieving name of option selected & displayed on drop-down bar
		WebElement selectedoption =	select.getFirstSelectedOption();
		String actselectedtext = selectedoption.getText();
		String expselectedtext = "India";

		if(actselectedtext.equals(expselectedtext))
		{
			test.pass("India is selected from country drop-down & it is highlighted in drop-down bar");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Country drop-down");
		}
		else
		{
			test.pass("India is NOT selected from country drop-down & it is NOT highlighted in drop-down bar");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Country drop-down");			
		}
	}


	@Test(description = "Validate whether Drop-down elements are arranged in proper order.")
	public void TC_CDD_003() throws InterruptedException
	{
		test = extent.createTest("Country drop-down").assignAuthor("Mayank Rusia");
		scroll();
		WebElement dd = driver.findElement(By.xpath("//select[@id='country']"));

		Select select = new Select(dd);
		List orglist = new ArrayList();
		List<WebElement> optionsElements = select.getOptions();

		//adding drop-down options
		for(WebElement e : optionsElements)
		{
			orglist.add(e.getText());
		}
		System.out.println("Original list: "+orglist);


		List templist = new ArrayList();
		List<WebElement> options1 = select.getOptions();

		//adding drop-down options
		for(WebElement e1 : options1)
		{
			templist.add(e1.getText());
		}


		//sorting
		Collections.sort(templist);

		System.out.println("List after sort: "+templist);

		//assertions
		if(orglist == templist)
		{
			test.pass("Country drop down elements are arranged in ascending order");
		}
		else 
		{
			test.fail("Country drop down elements are NOT arranged in ascending order");
		}

	}


	@Test(description = "Validate different ways to select a option from drop-down list.")
	public void TC_CDD_005() throws InterruptedException
	{
		test = extent.createTest("Country drop-down").assignAuthor("Mayank Rusia");
		scroll();
		WebElement dd = driver.findElement(By.xpath("//select[@id='country']"));
		Select select = new Select(dd);
		select.selectByVisibleText("India");
		
		String expc1 = "india";
		String actc1 = dd.getAttribute("value");
		if(expc1.equals(actc1))
		{
			test.pass("India option is selected from drop-down");
		}
		else
		{
			test.fail("India option is NOT selected from drop-down");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Country drop-down");
		}

		Thread.sleep(1500);

		select.selectByIndex(2);
		String expc2 = "uk";
		String actc2 = dd.getAttribute("value");
		if(expc2.equals(actc2))
		{
			test.pass("United Kingdom option is selected from drop-down");
		}
		else
		{
			test.fail("United Kingdom option is NOT selected from drop-down");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Country drop-down");
		}

		Thread.sleep(1500);

		select.selectByValue("france");
		String expc3 = "france";
		String actc3 = dd.getAttribute("value");
		if(expc3.equals(actc3))
		{
			test.pass("France option is selected from drop-down");
		}
		else
		{
			test.fail("France option is NOT selected from drop-down");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Country drop-down");
		}

	}


	@Test(description = "Validate jumping directly to the options from drop-down list by typing country initials through keyboard.")
	public void TC_CDD_006() throws InterruptedException
	{
		test = extent.createTest("Country drop-down").assignAuthor("Mayank Rusia");
		scroll();
		WebElement dd = driver.findElement(By.xpath("//select[@id='country']"));			Thread.sleep(1500);
		dd.click();																			Thread.sleep(1500);
		dd.sendKeys("A");																	Thread.sleep(1500);
		dd.sendKeys(Keys.ENTER);															Thread.sleep(1500);		

		String expc1 = "australia";
		String actc1 = dd.getAttribute("value");
		if(expc1.equals(actc1))
		{
			test.pass("Australia is selected");
		}
		else 
		{
			test.fail("Australia is NOt selected");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Country drop-dwon");
		}

	}

	@AfterTest
	public void teardown() throws InterruptedException
	{
		Thread.sleep(2500);
		driver.close();
	}

	public void scroll()
	{
		WebElement scrolling = driver.findElement(By.xpath("//label[normalize-space()='Country:']"));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView();", scrolling);
	}

	public String captureScreenshot()
	{
		String image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
		return image;
	}

}