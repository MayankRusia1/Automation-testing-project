package TS_006_Hyperlinks;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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



public class TC_HL 
{
	WebDriver driver ;
	ExtentReports extent;
	ExtentTest test;
	
	
	@BeforeSuite
	public void configurereport()
	{
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(".//Reports//HL_006.html");
		sparkReporter.config().setReportName("Hyperlink functionality testing report");
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
	
@BeforeTest
public void setup()
{
	WebDriverManager.edgedriver().setup();
	driver = new EdgeDriver();
	driver.manage().window().maximize();
	driver.get("https://testautomationpractice.blogspot.com/");
}

@Test(description = "Validate whether after clicking on \"Orange HRM\" Hyperlinks provided, we are navigating to \"Orange HRM\" login webpage.")
public void HL_002()
{
	test = extent.createTest("Orange HRM link").assignAuthor("Mayank Rusia");
	scroll();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	WebElement orangehrm = driver.findElement(By.xpath("//a[normalize-space()='orange HRM']"));
	orangehrm.click();
	
	String exptitle = "OrangeHRM";
	String acttitle = driver.getTitle();
	String expheading = "Login";
	String actheading = driver.findElement(By.xpath("//h5[normalize-space()='Login']")).getText();
	WebElement username = driver.findElement(By.xpath("//input[@placeholder='Username']"));
	WebElement password = driver.findElement(By.xpath("//input[@placeholder='Password']"));
	

if(exptitle.equals(acttitle) && expheading.equals(actheading) && username.isDisplayed() && password.isDisplayed())
{
	test.pass("User landed on OrangeHRM login page");
}
else 
{
	test.fail("User is NOT landed on OrangeHRM login page");
test.addScreenCaptureFromBase64String(capturescreenshot(),"Orange HRM hyperlink");	
}
}


@Test(description = "Validate whether after clicking on \"Open cart\" Hyperlinks provided, we are navigating to \"Open cart\" home webpage.")
public void HL_003()
{
	test = extent.createTest("Opencart link").assignAuthor("Mayank Rusia");
	scroll();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	WebElement opencart = driver.findElement(By.xpath("//a[normalize-space()='open cart']"));
	opencart.click();
	String exptitle = "Your Store";
	String acttitle = driver.getTitle();
	WebElement logo = driver.findElement(By.xpath("//img[@title='Your Store']"));	

if(exptitle.equals(acttitle) && logo.isDisplayed())
{
	test.pass("User landed on Open cart Home page");
}
else 
{
	test.fail("User is NOT landed on Open cart Home page");
test.addScreenCaptureFromBase64String(capturescreenshot(),"Open cart hyperlink");	
}
	
}


@Test(description = "Validate whether after clicking on \"Home\" Hyperlinks provided, we are navigating to \"Automation testing practice\" home webpage.")
public void HL_004()
{
	test = extent.createTest("Home hyperlink").assignAuthor("Mayank Rusia");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	scroll();
	WebElement home = driver.findElement(By.xpath("//a[normalize-space()='Home']"));
	home.click();
	String exptitle = "Automation Testing Practice";
	String acttitle = driver.getTitle();
	WebElement heading = driver.findElement(By.xpath("//h1[normalize-space()='Automation Testing Practice']"));	

if(exptitle.equals(acttitle) && heading.isDisplayed())
{
	test.pass("User landed on Automation Testing Practice Home page");
}
else 
{
	test.fail("User is NOT landed on Automation Testing Practice Home page");
test.addScreenCaptureFromBase64String(capturescreenshot(),"Open cart hyperlink");	
}
	
}

@Test(description = "Validate whether after clicking on hyperlink, we are directed to webpage in new browsing tab.")
public void HL_005()
{
	test = extent.createTest("opening a new browsing window").assignAuthor("Mayank Rusia");
	scroll();
	WebElement orangehrm = driver.findElement(By.xpath("//a[normalize-space()='orange HRM']"));
	orangehrm.click();
	
	Set<String> op = driver.getWindowHandles();
	
	int count = 0;
	for(String string : op)
	{
		count ++;
		System.out.println("ID of open browser tabs: "+string);
		String titleString = driver.switchTo().window(string).getTitle();
		System.out.println("title : "+titleString);
	}
	
	if(count == 1)
	{
		test.fail("User is navigated to orange HRM login page, in the same browser tab");
	}
	else 
	{
		test.pass("User is navigated to orange HRM login page, on a new browser tab");
	}
	
	
}

@Test(description = "Validate the working, after clicking on \"Posts (Atom)\" hyperlink, a text file is downloaded in system.")
public void HL_006()
{
	test = extent.createTest("Downloading a file").assignAuthor("Mayank Rusia");
	scroll();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	WebElement downloadElement = driver.findElement(By.xpath("//a[normalize-space()='Posts (Atom)']"));
	downloadElement.click();
	test.pass("Text file is downlaoded");
}

@AfterTest
public void teardown() throws InterruptedException
{
Thread.sleep(2500);
driver.quit();
}
	
	public void scroll()
	{
		WebElement scrollingElement = driver.findElement(By.xpath("//p[normalize-space()='Date:']"));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView();", scrollingElement);
	}
	
	public String capturescreenshot()
	{
		String image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
		return image;
	}
}