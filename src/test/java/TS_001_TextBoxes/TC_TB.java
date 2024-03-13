package TS_001_TextBoxes;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_TB 
{
    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setting() 
    {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(".//Reports//TB_006.html");
        sparkReporter.config().setReportName("Textbox functionality testing report");
        sparkReporter.config().setDocumentTitle("Test Execution Report");
        sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    @AfterSuite
    public void generateReport() {
        extent.flush();
    }
	
	
	@BeforeTest
	public void setup()
	{
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.get("https://testautomationpractice.blogspot.com/");
	driver.manage().window().maximize();
	}

	
	
	@Test(description = "Testing whether fields accepting whitespaces & showing warning messages for Email, Phone textboxes")
	public void TB_002()
	{
	     test = extent.createTest("Textboxes functionality").assignAuthor("Mayank Rusia");
	     
		
		WebElement name = driver.findElement(By.xpath("//input[@id='name']"));
		name.sendKeys("     jay     ");								
		
		WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
		email.sendKeys("mohit   875@ yahoo.com");					
		
		WebElement phone = driver.findElement(By.xpath("//input[@id='phone']"));
		phone.sendKeys("6523   985");								
		
		WebElement address = driver.findElement(By.xpath("//textarea[@id='textarea']"));
		address.sendKeys("453/s      sagar,         M.P");			
		address.sendKeys(Keys.TAB);
		
		//assertions
		String actname = name.getAttribute("value");
		String expname = "     jay     ";
		if(expname.equals(actname)) 
		{
			test.log(Status.PASS,"Name entered correctly").log(Status.INFO, "Name is correctly entered in textbox");
		}
		else 
		{
			test.log(Status.FAIL, "Name is NOT correctly entered in textbox");		
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Name Textbox");		
		}
		
		
		
		String actemail = email.getAttribute("value");
		String expemail = "invalid email address";
		
		if(actemail.equals(expemail))
		{
			test.log(Status.PASS, "Proper warning message is displayed");
		}
		else 
		{
			test.log(Status.FAIL, "No warning message is displayed");			
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Email Textbox");					
		}
		
		
		
		String actphone = phone.getAttribute("value");
		String expphone = "invalid phone number";
		if(actphone.equals(expphone))
		{
			test.log(Status.PASS, "Proper warning message is displayed");
		}
		else 
		{
			test.log(Status.FAIL, "No warning message is displayed");			
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Phone Textbox");					
		}

		
		
		String actaddress = address.getAttribute("value");
		String expaddress = "453/s      sagar,         M.P";
		if(actaddress.equals(expaddress))
		{
			test.log(Status.PASS, "Proper warning message is displayed");
		}
		else 
		{
			test.log(Status.FAIL, "No warning message is displayed");			
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Address Textbox");					
		}
		
	}

	public String captureScreenshot()
	{
	String image =	((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
	return image;
	}
	
	
	@Test(description = "Validate whether all textboxes have proper Placeholders")
	public void TB_004()
	{
		test = extent.createTest("Placeholder checking").assignAuthor("Mayank Rusia");
		
		WebElement name = driver.findElement(By.xpath("//input[@id='name']"));
		String actnameplace =  name.getAttribute("value");
		
		WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
		String actemailplace = email.getAttribute("value");
		
		WebElement phone = driver.findElement(By.xpath("//input[@id='phone']"));
		String actphoneplace = phone.getAttribute("value");
		
		WebElement address = driver.findElement(By.xpath("//textarea[@id='textarea']"));
		String actaddressplace = address.getAttribute("value");
		
		String expname = "Enter name";
		String expemail = "Enter Email address";
		String expphone = "Enter phone number";
		String expaddress = "Enter Street address";

		
		if(expname.equals(actnameplace))
		{
		test.pass("Correct placeholder is present inside Name textbox");
		}
		else 
		{
			test.fail("Wrong placeholder is present inside Name textbox");			
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Name Textbox");					
		}
		
		
		if(expaddress.equals(actaddressplace))
		{
		test.pass("Correct placeholder is present inside address textbox");
		}
		else 
		{
			test.fail("Wrong placeholder is present inside address textbox");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Email Textbox");					
		}
		
		if(expphone.equals(actphoneplace))
		{
		test.pass("Correct placeholder is present inside Phone textbox");
		}
		else 
		{
			test.fail("Wrong placeholder is present inside Phone textbox");	
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Phone Textbox");					
		}
		
		if(expemail.equals(actemailplace))
		{
		test.pass("Correct placeholder is present inside Email textbox");
		}
		else 
		{
			test.fail("Wrong placeholder is present inside Email textbox");	
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Address Textbox");					
		}
		
		}

	
	@Test(description = "Validate working of textboxes, by entering all textboxes fields with keyboard key actions.")
	public void TB_005() throws InterruptedException
	{
		test = extent.createTest("Keyboard action").assignAuthor("Mayank Rusia");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		WebElement name = driver.findElement(By.xpath("//input[@id='name']"));
		name.sendKeys("Rohit yadav");      				test.log(Status.INFO, "Entered Rohit yadav in Name textbox");
		name.sendKeys(Keys.TAB);		   				test.log(Status.INFO, "Press TAB key");
		
		WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
		email.sendKeys("rohit766@gmail.com");			test.log(Status.INFO, "Entered 'rohit766@gmail.com' in Email textbox");
		email.sendKeys(Keys.TAB);						test.log(Status.INFO, "Press TAB key");
		
		WebElement phone = driver.findElement(By.xpath("//input[@id='phone']"));
		phone.sendKeys("7845962136");					test.log(Status.INFO, "Entered '7845962136' in Phone textbox");
		phone.sendKeys(Keys.TAB);						test.log(Status.INFO, "Press TAB key");
		
		WebElement address = driver.findElement(By.xpath("//textarea[@id='textarea']"));
		address.sendKeys("545/2, subhas nagar, Nagda");	test.log(Status.INFO, "Entered '545/2, subhas nagar, Nagda' in address textbox");
		address.sendKeys(Keys.TAB);						test.log(Status.INFO, "Press TAB key");
		
		test.addScreenCaptureFromBase64String(capturescreenshot(),"Full operation");
	}



	@Test(description = "Validate UI of Textboxes functionality.")
	public void TB_006()
	{
		test = extent.createTest("UI of Textbox").assignAuthor("Mayank Rusia");
		
		WebElement name = driver.findElement(By.xpath("//input[@id='name']"));		
		WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
		WebElement phone = driver.findElement(By.xpath("//input[@id='phone']"));
		WebElement address = driver.findElement(By.xpath("//textarea[@id='textarea']"));
		
		System.out.println("Email option: ");
		System.out.println("Outline color: "+email.getCssValue("outline-color"));
		System.out.println("Type of Name option: "+email.getAttribute("type"));	
		
		System.out.println("Phone number option: ");
		System.out.println("Outline color: "+phone.getCssValue("outline-color"));
		System.out.println("Type of Name option: "+phone.getAttribute("type"));	
		
		System.out.println("Address option: ");
		System.out.println("Outline color: "+address.getCssValue("outline-color"));
		System.out.println("Type of Name option: "+address.getAttribute("type"));	

		if(name.getCssValue("outline-color").equals("rgba(73, 80, 87, 1)") && name.getAttribute("type").equals("text"))
		{
			test.pass("Correct properties of Name option");
		}
		else 
		{
			test.fail("Wrong properties of Name option");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Name Textbox");					
		}

		
		if(email.getCssValue("outline-color").equals("rgba(73, 80, 87, 1)") && email.getAttribute("type").equals("text"))
		{
			test.pass("Correct properties of Email option");
		}
		else 
		{
			test.fail("Wrong properties of Email option");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Email Textbox");					
		}

		if(phone.getCssValue("outline-color").equals("rgba(73, 80, 87, 1)") && phone.getAttribute("type").equals("text"))
		{
			test.pass("Correct properties of Phone option");
		}
		else 
		{
			test.fail("Wrong properties of Phone option");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Phone Textbox");					
		}

		if(address.getCssValue("outline-color").equals("rgba(73, 80, 87, 1)") && address.getAttribute("type").equals("textarea"))
		{
			test.pass("Correct properties of Address option");
		}
		else 
		{
			test.fail("Wrong properties of Address option");
			test.addScreenCaptureFromBase64String(captureScreenshot(),"Address Textbox");					
		}

	}
	
	@AfterTest
	public void teardown()
	{
		driver.quit();
	}
	
	public String capturescreenshot()
	{
		String b64string =	((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
		return b64string;
	}

}

