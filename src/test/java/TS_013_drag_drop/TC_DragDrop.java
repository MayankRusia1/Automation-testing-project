package TS_013_drag_drop;

import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
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
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TC_DragDrop 
{
	WebDriver driver ;
	ExtentReports extent;
	ExtentTest test;

	@BeforeSuite
	public void configurereport()
	{
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(".//Reports//DragDrop_007.html");
		sparkReporter.config().setReportName("Drag & drop functionality testing report");
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


	@Test(description = "Validate whether Drop window have text in it \"Drop here\".")
	public void DragDrop_002()
	{
		test = extent.createTest("Drop window features").assignAuthor("Mayank Rusia");
		scroll();
		WebElement dropable = driver.findElement(By.xpath("//div[@id='droppable']"));
		String exptext = "Drop here";
		String acttext = dropable.getText();
		if(exptext.equals(acttext))
		{
			test.pass("Correct message is displayed");
		}
		else 
		{	
			test.fail("Wrong message is displayed");
			test.addScreenCaptureFromBase64String(capturescreenshot());
		}
	}


	@Test(description = "Validate whether Drag element have text in it \"Drag me to my target\".")
	public void DragDrop_003()
	{
		test = extent.createTest("Drag element feature").assignAuthor("Mayank Rusia");
		scroll();
		WebElement dragable = driver.findElement(By.xpath("//div[@id='draggable']"));
		String exptext = "Drag me to my target";
		String acttext = dragable.getText();
		if(exptext.equals(acttext))
		{
			test.pass("Correct message is displayed");
		}
		else 
		{	
			test.fail("Wrong message is displayed");
			test.addScreenCaptureFromBase64String(capturescreenshot());
		}
	}

	@Test(description = "Validate features of Draggable element")
	public void DragDrop_004()
	{
		test = extent.createTest("Draggable element features").assignAuthor("Mayank Rusia");
		scroll();
		WebElement dragable = driver.findElement(By.xpath("//div[@id='draggable']"));
		String act = dragable.getCssValue("outline-color");
		String exp = "rgb(51, 51, 51)";
		if(exp.equals(act))
		{
			test.pass("Correct message is displayed");
		}
		else 
		{	
			test.fail("Wrong message is displayed");
			test.addScreenCaptureFromBase64String(capturescreenshot());
		}	}

	@Test(description = "Validate features of Drop window")
	public void DragDrop_005()
	{
		test = extent.createTest("Drop window functionality").assignAuthor("Mayank Rusia");
		scroll();
		WebElement dropable = driver.findElement(By.xpath("//div[@id='droppable']"));
		String act = dropable.getCssValue("outline-color");
		String exp = "rgb(51, 51, 51)";
		if(exp.equals(act))
		{
			test.pass("Correct message is displayed");
		}
		else 
		{	
			test.fail("Wrong message is displayed");
			test.addScreenCaptureFromBase64String(capturescreenshot());
		}	}

	
	@Test(description = "Validate Drag element is movable & Drop window is Immovable.")
	public void DragDrop_006()
	{
		test = extent.createTest("Drag & drop functionality").assignAuthor("Mayank Rusia");
		scroll();
		WebElement dragable = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement dropable = driver.findElement(By.xpath("//div[@id='droppable']"));
		Actions actions = new Actions(driver);

		Point beforedragelement = dragable.getLocation();

		//drag element
		actions.dragAndDropBy(dragable, 25, 50).build().perform();
		actions.dragAndDropBy(dragable, -25, 80).build().perform();
		actions.dragAndDropBy(dragable, 75, 100).build().perform();
		actions.dragAndDropBy(dragable, 152, -50).build().perform();
		actions.dragAndDropBy(dragable, 140, 80).build().perform();

		Point afterdragelement = dragable.getLocation();

		Point beforedropwindow = dropable.getLocation();


		//drop window
		actions.dragAndDropBy(dropable, 50, 80).build().perform();
		actions.dragAndDropBy(dropable, -50, 280).build().perform();
		actions.dragAndDropBy(dropable, -100, 140).build().perform();
		actions.dragAndDropBy(dropable, 88, 20).build().perform();

		Point afterdropwindow = dropable.getLocation();


		if(beforedropwindow.equals(afterdropwindow) && !beforedragelement.equals(afterdragelement))
		{
			test.pass("Drag element is movable");
			test.pass("Drop window is immovable");
		}
		else 
		{
			test.fail("Drag element is movable");
			test.fail("Drop window is movable");
			test.addScreenCaptureFromBase64String(capturescreenshot());
		}
	}   



	@Test(description = "Validate indication applied on Drop window, as Drag element is dropped inside Drop window.")
	public void DragDrop_007()
	{
		test = extent.createTest("Drag & drop functionality").assignAuthor("Mayank Rusia");
		scroll();
		WebElement dragable = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement dropable = driver.findElement(By.xpath("//div[@id='droppable']"));

		//before dropping
		String expbeforedrop = "Drop here";
		String actbeforedrop = dropable.getText();

		Actions actions = new Actions(driver);
		actions.dragAndDrop(dragable, dropable).build().perform();

		//after drop
		String expafterdrop = "Dropped!";
		String actafterdrop = dropable.getText();

		if(actbeforedrop.equals(expbeforedrop) && actafterdrop.equals(expafterdrop))
		{
			test.pass("Correct message is displayed, before dropping!");
			test.pass("Correct message displayed, after dropping!");
		}
		else 
		{
			test.fail("Wrong message is displayed, before dropping!");
			test.fail("Wrong message displayed, after dropping!");
			test.addScreenCaptureFromBase64String(capturescreenshot());
		}

	}


	@AfterTest
	public void teardown() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.close();
	}

	public void scroll()
	{
		WebElement scrolling = driver.findElement(By.xpath("//h2[normalize-space()='Drag and Drop']"));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView();", scrolling);
	}
}