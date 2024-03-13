package TS_012_doubleclickaction;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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

public class TC_DC
{
	WebDriver driver ;
	ExtentReports extent;
	ExtentTest test;

	@BeforeSuite
	public void configurereport()
	{
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(".//Reports//DC_006.html");
		sparkReporter.config().setReportName("Double click functionality testing report");
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
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://testautomationpractice.blogspot.com/");
	}

	@Test(description = "Validate whether any bydefault text inside Field1 & Field2 textboxes.")
	public void DC_002()
	{
		test = extent.createTest("Double click option").assignAuthor("Mayank Rusia");
		scroll();
		WebElement field1 = driver.findElement(By.id("field1"));
		WebElement field2 = driver.findElement(By.id("field2"));

		String exptext1 = "Hello World!";
		String acttext1 = field1.getAttribute("value");

		String exptext2 = "";
		String acttext2 = field2.getText();

		if(exptext1.equals(acttext1) && exptext2.equals(acttext2))
		{
			test.pass("Field1 have a text bydefault in it & Field2 is empty");
		}
		else 
		{
		test.fail("Field1 & Field2 are not behaving as per requirement");
		test.addScreenCaptureFromBase64String(capturescreenshot());
		}
	}

	@Test(description = "Validate whether after clicking on 'Copy Text' button, Content in (Field1 & Field2) textboxes are copied & same.")
	public void DC_003() throws IOException
	{
		test = extent.createTest("Double click option").assignAuthor("Mayank Rusia");
		scroll();
		String filepath = "C:\\Users\\mayan\\OneDrive\\Desktop\\Testing_course\\java\\java_programs_practice\\Automation_practice_project1\\Data_Files\\DC3.xlsx";
		FileInputStream fileInputStream = new FileInputStream(filepath);
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
		XSSFSheet sheet = workbook.getSheetAt(0);

		int rows = sheet.getLastRowNum();
		int columns = sheet.getRow(0).getLastCellNum();

		WebElement field1 = driver.findElement(By.id("field1"));
		WebElement button = driver.findElement(By.xpath("//button[normalize-space()='Copy Text']"));

		for(int i=0; i<= rows; i++)
		{
			XSSFRow currentrow = sheet.getRow(i);
			for(int j=0; j< columns; j++)
			{
				String value = currentrow.getCell(j).toString();
				field1.clear();
				field1.sendKeys(value);
				Actions actions = new Actions(driver);
				actions.doubleClick(button).build().perform();
			}
		}

		WebElement field2 = driver.findElement(By.id("field2"));
		String actfield1 = field1.getText();
		String actfield2 = field2.getText();

		if(actfield1.equals(actfield2))
		{
			test.pass("Both textboxes having same text");
		}
		else 
		{
		test.fail("Both textboxes having different text");
		test.addScreenCaptureFromBase64String(capturescreenshot());
		}
		workbook.close();
	}

	@Test(description = "Validate whether Field1 textbox content will copy in Field2 textbox only after \"Double click\" action.")
	public void DC_004() throws IOException
	{
		test = extent.createTest("Double click option").assignAuthor("Mayank Rusia");
		scroll();
		String filepath = "C:\\Users\\mayan\\OneDrive\\Desktop\\Testing_course\\java\\java_programs_practice\\Automation_practice_project1\\Data_Files\\DC4.xlsx";
		FileInputStream fileInputStream = new FileInputStream(filepath);
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
		XSSFSheet sheet = workbook.getSheetAt(0);

		int rows  = sheet.getLastRowNum();
		int columns = sheet.getRow(0).getLastCellNum();

		WebElement field1 = driver.findElement(By.id("field1"));
		WebElement field2 = driver.findElement(By.id("field2"));
		WebElement button = driver.findElement(By.xpath("//button[normalize-space()='Copy Text']"));

		for(int i1=0; i1<= rows; i1++)
		{
			XSSFRow currow1 = sheet.getRow(i1);
			for(int j1=0; j1< columns; j1++)
			{
				String value1 = currow1.getCell(j1).toString();
				
				field1.clear();
				field1.sendKeys(value1);
				button.click();
				System.out.println("Single click done!");
			}
		}
		
		String expsingle = "";
		String singlefield2 = field2.getAttribute("value");
		
		
		//double click action
		
		for(int i2=0; i2<= rows; i2++)
		{
			XSSFRow currow = sheet.getRow(i2);
			for(int j2=0; j2< columns; j2++)
			{
				String value2 = currow.getCell(j2).toString();
				
				field1.clear();
				field1.sendKeys(value2);
				Actions actions = new Actions(driver);
				actions.doubleClick(button).build().perform();
				System.out.println("Double click done!");
			}
		}
		
		String singlefield3 = field1.getAttribute("value");
		String singlefield4 = field2.getAttribute("value");
		
		if(expsingle.equals(singlefield2) && singlefield3.equals(singlefield4))
		{
			test.pass("Content from Field1 getting copied in Field2 after only Double click action");
		}
		else 
		{
		test.fail("Text getting copied in Field2 from Fiel1 by single click action");
		test.addScreenCaptureFromBase64String(capturescreenshot());
		}
		workbook.close();
	}

	@Test(description = "Validate the working for Field1 textbox have proper placeholder.")
	public void DC_005()
	{
		test = extent.createTest("Double click option").assignAuthor("Mayank Rusia");
		scroll();
		WebElement field1 = driver.findElement(By.id("field1"));
		field1.clear();
		String exptext = "Enter text here";
		String acttext = field1.getAttribute("value");
		if(exptext.equals(acttext))
		{
			test.pass("Proper placeholder is displayed");
		}
		else 
		{
		test.fail("Wrong / no placeholder is present");
		test.addScreenCaptureFromBase64String(capturescreenshot());
		}
	}

	@Test(description = "Validate features of Double click functionality")
	public void DC_006()
	{
		test = extent.createTest("Double click option").assignAuthor("Mayank Rusia");
		scroll();
		WebElement field1 = driver.findElement(By.id("field1"));
		WebElement field2 = driver.findElement(By.id("field2"));
		WebElement button = driver.findElement(By.xpath("//button[normalize-space()='Copy Text']"));
		WebElement para = driver.findElement(By.xpath("//p[contains(text(),'Double click on button, the text from Field1 will ')]"));

		String expf1 = "text";
		String actf1 = field1.getAttribute("type");

		String expf2 = "text";
		String actf2 = field2.getAttribute("type");

		String exptext1 = "Copy Text";
		String acttext1 = button.getText();

		String exptext2 = "Double click on button, the text from Field1 will be copied into Field2.";
		String acttext2 = para.getText();

		if(actf1.equals(expf1) && actf2.equals(expf2) && acttext1.equals(exptext1) && acttext2.equals(exptext2))
		{
			test.pass("Field 1 is a textbox");
			test.pass("Field 2 is a textbox");
			test.pass("Double click button have correct text inside it");
			test.pass("Correct instruction is displayed");
		}
		else 
		{
		test.fail("Field 1 is NOT a textbox");
		test.fail("Field 2 is NOT a textbox");
		test.fail("Double click button have wrong text inside it");
		test.fail("Wrong instruction is displayed");
		test.addScreenCaptureFromBase64String(capturescreenshot());
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
		WebElement scrollingElement = driver.findElement(By.xpath("//h2[normalize-space()='Double Click']"));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView();", scrollingElement);
	}	

}