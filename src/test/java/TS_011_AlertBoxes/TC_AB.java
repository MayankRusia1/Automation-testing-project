package TS_011_AlertBoxes;

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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TC_AB 
{
	WebDriver driver ;
	ExtentReports extent;
	ExtentTest test;

	@BeforeSuite
	public void configurereport()
	{
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(".//Reports//AB_009.html");
		sparkReporter.config().setReportName("Alert boxes functionality testing report");
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


	@Test(description = "Validate the functionality of simple 'Alert' button with  'OK' button functionality.")
	public void AB_002() throws InterruptedException
	{
		test = extent.createTest("Simple alert box with OK button").assignAuthor("Mayank Rusia");
		scroll();
		WebElement alertbutton = driver.findElement(By.xpath("//button[normalize-space()='Alert']"));
		alertbutton.click();											Thread.sleep(2000);
		test.info("Alert button is clicked");
		String exptext = "I am an alert box!";
		String acttext = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		test.info("OK button is clicked");
		if(exptext.equals(acttext))
		{
			test.pass("Correct message is displayed inside alert box");
		}
		else 
		{
			test.fail("Wrong message is displayed");
			test.addScreenCaptureFromBase64String(capturescreenshot(),"Simple alert box");
		}

	}


	@Test(description = "Validate the functionality of 'Confirm box' alert button with  'OK'  button functionality.")
	public void AB_003() throws InterruptedException
	{
		test = extent.createTest("Confirm box with OK option").assignAuthor("Mayank Rusia");
		scroll();
		WebElement confirmbutton = driver.findElement(By.xpath("//button[normalize-space()='Confirm Box']"));
		confirmbutton.click();						Thread.sleep(1500);
		test.info("Confirm alert box button is clicked");
		String actintext = driver.switchTo().alert().getText();
		String expintext = "Press a button!";
		driver.switchTo().alert().accept();
		test.info("OK button is clicked");
		String exptext = "You pressed OK!";
		String acttext = driver.findElement(By.xpath("//p[@id='demo']")).getText();

		if(actintext.equals(expintext) && acttext.equals(exptext))
		{
			test.pass("Text inside alert box is correctly displayed");
			test.pass("Correct message is displayed on webpage");
		}
		else 
		{
			test.fail("Text inside alert box is NOT correctly displayed");
			test.fail("Wrong message is displayed on webpage");
			test.addScreenCaptureFromBase64String(capturescreenshot());
		}

	}


	@Test(description = "Validate the functionality of 'Confirm box' alert button with  'Cancel'  button functionality.")
	public void AB_004() throws InterruptedException
	{
		test = extent.createTest("Confirm box with CANCEL option").assignAuthor("Mayank Rusia");
		scroll();
		WebElement confirmbutton = driver.findElement(By.xpath("//button[normalize-space()='Confirm Box']"));
		confirmbutton.click();						Thread.sleep(1500);
		test.info("Confirm button is clicked");
		String actintext = driver.switchTo().alert().getText();
		String expintext = "Press a button!";
		driver.switchTo().alert().dismiss();
		test.info("Cancel button is clicked");
		String exptext = "You pressed Cancel!";
		String acttext = driver.findElement(By.xpath("//p[@id='demo']")).getText();

		if(actintext.equals(expintext) && acttext.equals(exptext))
		{
			test.pass("Text inside alert box is correctly displayed");
			test.pass("Correct message is displayed on webpage");
		}
		else 
		{
			test.fail("Text inside alert box is NOT correctly displayed");
			test.fail("Wrong message is displayed on webpage");
			test.addScreenCaptureFromBase64String(capturescreenshot());
		}

	}



	@Test(description = "Validate the functionality of \"Prompt\" alert button, directly clicking on 'OK' button without giving any prompt in textbox.")
	public void AB_005() throws InterruptedException
	{
		test = extent.createTest("Prompt alert button, without passing any text").assignAuthor("Mayank Rusia");
		scroll();
		WebElement prompt = driver.findElement(By.xpath("//button[normalize-space()='Prompt']"));
		prompt.click();  													Thread.sleep(1500);
		test.info("Prompt button is clicked");
		driver.switchTo().alert().accept();
		String exptext = "Hello Harry Potter! How are you today?";
		String acttext = driver.findElement(By.xpath("//p[@id='demo']")).getText();

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

	@Test(description = "Validate the functionality of 'Prompt' alert button, directly clicking on 'Cancel' button without giving any prompt in textbox.")
	public void AB_006() throws InterruptedException
	{
		test = extent.createTest("Prompt alert button,without passing any text").assignAuthor("Mayank Rusia");
		scroll();
		WebElement prompt = driver.findElement(By.xpath("//button[normalize-space()='Prompt']"));
		prompt.click();  					Thread.sleep(1500);
		test.info("Prompt button is clicked");
		driver.switchTo().alert().dismiss();
		String exptext = "User cancelled the prompt.";
		String acttext = driver.findElement(By.xpath("//p[@id='demo']")).getText();

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


	@Test(dataProvider = ("ab_007"),  description = "Validate the functionality of \"Prompt\" alert button, giving a prompt in textbox, & clicking \"OK\" button.")
	public void AB_007(String data) throws InterruptedException
	{
		test = extent.createTest("Prompt alert button,passing prompt & click OK button").assignAuthor("Mayank Rusia");
		scroll();	
		WebElement prompt = driver.findElement(By.xpath("//button[normalize-space()='Prompt']"));
		prompt.click();  										Thread.sleep(1500);
		test.info("Prompt button is clicked");
		driver.switchTo().alert().sendKeys(data);				Thread.sleep(1500);
		driver.switchTo().alert().accept();

		if(data.equalsIgnoreCase("Hello"))
		{
			String exptext2 = "Hello Hello! How are you today?";
			String acttext2 = driver.findElement(By.xpath("//p[@id='demo']")).getText();
			if(exptext2.equals(acttext2))
			{
				test.pass("Correct message is displayed");
			}
			else 
			{
				test.fail("Wrong message is displayed");
				test.addScreenCaptureFromBase64String(capturescreenshot());
			}
		}

		if(data.equalsIgnoreCase("8473968586"))
		{
			String exptext3 = "Hello 8473968586! How are you today?";
			String acttext3 = driver.findElement(By.xpath("//p[@id='demo']")).getText();
			if(exptext3.equals(acttext3))
			{
				test.pass("Correct message is displayed");
			}
			else 
			{
				test.fail("Wrong message is displayed");
				test.addScreenCaptureFromBase64String(capturescreenshot());
			}
		}

		if(data.equalsIgnoreCase("Hello jjffj  ##@@ (&&)"))
		{
			String exptext4 = "Hello jjffj  ##@@ (&&)! How are you today?";
			String acttext4 = driver.findElement(By.xpath("//p[@id='demo']")).getText();
			if(exptext4.equals(acttext4))
			{
				test.pass("Correct message is displayed");
			}
			else 
			{
				test.fail("Wrong message is displayed");
				test.addScreenCaptureFromBase64String(capturescreenshot());
			}
		}

		if(data.equalsIgnoreCase("jjjaiiiia"))
		{
			String exptext5 = "Hello jjjaiiiia! How are you today?";
			String acttext5 = driver.findElement(By.xpath("//p[@id='demo']")).getText();
			if(exptext5.equals(acttext5))
			{
				test.pass("Correct message is displayed");
			}
			else 
			{
				test.fail("Wrong message is displayed");
				test.addScreenCaptureFromBase64String(capturescreenshot());
			}
		}

		if(data.equalsIgnoreCase("9888 22 556 6 ^ %"))
		{
			String exptext6 = "Hello 9888 22 556 6 ^ %! How are you today?";
			String acttext6 = driver.findElement(By.xpath("//p[@id='demo']")).getText();		
			if(exptext6.equals(acttext6))
			{
				test.pass("Correct message is displayed");
			}
			else 
			{
				test.fail("Wrong message is displayed");
				test.addScreenCaptureFromBase64String(capturescreenshot());
			}
		}

		if(data.equalsIgnoreCase("aa     hh"))
		{
			String exptext1 = "Hello aa     hh! How are you today?";
			String acttext1 = driver.findElement(By.xpath("//p[@id='demo']")).getText();
			if(exptext1.equals(acttext1))
			{
				test.pass("Correct message is displayed");
			}
			else 
			{
				test.fail("Wrong message is displayed");
				test.addScreenCaptureFromBase64String(capturescreenshot());
			}
		}
	}



	@DataProvider(name = "ab_007")
	public Object [][] getdata()
	{
		Object [][] data = {
				{"Hello"},{"8473968586"},{"jjffj  ##@@ (&&)"},
				{"jjjaiiiia"},{"9888 22 556 6 ^ %"},{"aa     jh"}
		};
		return data;
	}


	@Test(description = "Validate the functionality of 'Prompt' alert button, giving a prompt in textbox, & clicking 'Cancel' button.")
	public void AB_008() throws InterruptedException
	{
		test = extent.createTest("Prompt alert button,passing prompt & click Cancel button").assignAuthor("Mayank Rusia");
		scroll();
		WebElement prompt = driver.findElement(By.xpath("//button[normalize-space()='Prompt']"));
		prompt.click();  					Thread.sleep(1500);
		test.info("Prompt button is clicked");
		driver.switchTo().alert().sendKeys("Hello");
		driver.switchTo().alert().dismiss();
		String exptext = "User cancelled the prompt.";
		String acttext = driver.findElement(By.xpath("//p[@id='demo']")).getText();

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


	@Test(description = "Validate Prompt functionality behaviour with whitespaces.")
	public void AB_009() throws InterruptedException
	{
		test = extent.createTest("Prompt alert button").assignAuthor("Mayank Rusia");
		scroll();
		WebElement prompt = driver.findElement(By.xpath("//button[normalize-space()='Prompt']"));
		prompt.click();  					Thread.sleep(1500);
		test.info("Prompt button is clicked");
		driver.switchTo().alert().sendKeys("     ");
		driver.switchTo().alert().accept();
		String exptext = "Hello      ! How are you today?";
		String acttext = driver.findElement(By.xpath("//p[@id='demo']")).getText();
		
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

	@AfterTest
	public void teardown() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.close();
	}

	public void scroll()
	{
		WebElement scrollingElement = driver.findElement(By.xpath("//h2[normalize-space()='JS Alerts']"));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView();", scrollingElement);
	}

}