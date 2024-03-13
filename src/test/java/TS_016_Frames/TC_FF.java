package TS_016_Frames;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
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



public class TC_FF 
{
	WebDriver driver ;
	ExtentReports extent;
	ExtentTest test;

	@BeforeSuite
	public void configurereport()
	{
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(".//Reports//FF_009.html");
		sparkReporter.config().setReportName("Frames testing report");
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

	
	@Test(description = "Validate whether user is able to click \"Report abuse\" hyperlink, present inside iframe.") 
	public void FF_001()
	{
		test = extent.createTest("Frames functionality").assignAuthor("Mayank Rusia");
		scroll();
		driver.switchTo().frame(0);
		WebElement reportabuse = driver.findElement(By.linkText("Report abuse"));
		reportabuse.click();

		Set<String> windowHandles = driver.getWindowHandles();

		// Iterate through each window handle
		for (String windowHandle : windowHandles)
		{
			System.out.println("ID of browser: "+windowHandle);
			driver.switchTo().window(windowHandle);
			String newPageTitle = driver.getTitle();
			System.out.println("New Page Title: " + newPageTitle);
		}

		int windowcount = windowHandles.size();
		
		if(windowcount == 2)
		{
			test.pass("Report abuse page is opened in a new browsing window");
		}
		else 
		{
		test.fail("Report abuse page is opened in same browsing window");
		test.addScreenCaptureFromBase64String(capturescreenshot());
		}
	}

	

	@Test(description = "Validate whether user is able to click \"Powered by  formsite\" hyperlink, present inside iframe.") 
	public void FF_002()
	{
		test = extent.createTest("Frames functionality").assignAuthor("Mayank Rusia");
		scroll();
		driver.switchTo().frame(0);
		WebElement links = driver.findElement(By.partialLinkText("Powered by"));
		links.click();

		Set<String> windowhandle = driver.getWindowHandles();

		for(String e : windowhandle)
		{
			System.out.println("ID of browser: "+e);
			System.out.println("Page title: "+driver.getTitle());
			driver.switchTo().window(e);
		}

		int windowcount = windowhandle.size();
		if(windowcount == 2)
		{
			test.pass("Formsite page is opened in a new browsing window");
		}
		else 
		{
		test.fail("Formsite page is opened in same browsing window");
		test.addScreenCaptureFromBase64String(capturescreenshot());
		}

	}


	@Test(description = "Validate whether user is able to fill form, present inside iframe.") 
	public void FF_003()
	{
		test = extent.createTest("Frames functionality").assignAuthor("Mayank Rusia");
		scroll();
		driver.switchTo().frame(0);
		WebElement nameElement = driver.findElement(By.cssSelector("input[class='text_field']"));
		WebElement JobElement = driver.findElement(By.cssSelector(".drop_down"));
		WebElement dateElement = driver.findElement(By.cssSelector("input[placeholder='mm/dd/yyyy']"));
		WebElement MaleGenderElement = driver.findElement(By.cssSelector("label[for='RESULT_RadioButton-1_0']"));
		WebElement button = driver.findElement(By.cssSelector("input[value='Submit']"));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		nameElement.sendKeys("aman");

		dateElement.click();
		dateElement.sendKeys("5/12/1997");

		Select select = new Select(JobElement);
		select.selectByVisibleText("Manager");

		MaleGenderElement.click();

		button.click();

		String expresult = "Form is submitted successfully";
		WebElement result = driver.findElement(By.xpath("//div[@class='form_table']"));
		String actresult = result.getText();	

		if(actresult.equals(expresult))
		{
			test.pass("Form is submitted successfully");
		}
		else 
		{
		test.fail("Form is NOT submitted successfully");
		test.addScreenCaptureFromBase64String(capturescreenshot());
		}

	}


	@Test(description = "Validate fields inside iframes, having proper placeholders.") 
	public void FF_004()
	{
		test = extent.createTest("Frames functionality").assignAuthor("Mayank Rusia");
		scroll();
		driver.switchTo().frame(0);
		WebElement nameElement = driver.findElement(By.cssSelector("input[class='text_field']"));
		WebElement dateElement = driver.findElement(By.cssSelector("input[placeholder='mm/dd/yyyy']"));


		String expname = "Enter Name";
		String expdate = "mm/dd/yyyy";
		String actname = nameElement.getAttribute("placeholder");
		String actdate = dateElement.getAttribute("placeholder");

		if(actdate.equals(expdate) &&  actname.equals(expname))
		{
			test.pass("Correct placholder for Date");
			test.pass("Correct placeholder for Name");			
		}
		else 
		{
		test.fail("Wrong placholder for Date");
		test.fail("Wrong placeholder for Name");		
		test.addScreenCaptureFromBase64String(capturescreenshot());
		}

	}


	@Test(description = "Validate presence of iframes") 
	public void FF_005()
	{
		test = extent.createTest("Frames functionality").assignAuthor("Mayank Rusia");
		scroll();
		List<WebElement> iframes =	driver.findElements(By.tagName("iframe"));
		WebElement iframe = driver.findElement(By.tagName("iframe"));
		
		Assert.assertTrue(iframe.isDisplayed(),"Iframe is NOT present on page");
		int sizeOfiframe =	iframes.size();
		
		if(sizeOfiframe == 1)
		{
			test.pass("1 iframes are present on page");
		}
		else 
		{
		test.fail("More or less than 1 iframes are present on page");
		test.addScreenCaptureFromBase64String(capturescreenshot());
		}
		
	}

	
	@Test(description = "Validate Iframe Attributes") 
	public void FF_007()
	{
		test = extent.createTest("Frames functionality").assignAuthor("Mayank Rusia");
		scroll();
		WebElement iframe = driver.findElement(By.tagName("iframe"));
		
		String actsrc = iframe.getAttribute("src");
		String actscrolling = iframe.getAttribute("scrolling");
		String actheight = iframe.getAttribute("height");
		String actstyle = iframe.getAttribute("style");
		
		String expsrc = "https://fs24.formsite.com/res/showFormEmbed?EParam=m_OmK8apOTDpwCqUlfXbL2rYe2Y6sJfY&796456169&EmbedId=796456169";
		String expscrolling = "no";
		String expStyling = "border: 0px; margin: 0px; padding: 0px; width: 100%;";
		String expheight = "545";
		
		if(actheight.equals(expheight) && actscrolling.equals(expscrolling) && actsrc.equals(expsrc) &&  actstyle.equals(expStyling))
		{
			test.pass("Correct height property");
			test.pass("Correct scrolling property");
			test.pass("Correct src property");
			test.pass("Correct styling property");
		}
		else 
		{
		test.fail("Wrong height property");
		test.fail("Wrong scrolling property");
		test.fail("Wrong src property");
		test.fail("Wrong styling property");
		test.addScreenCaptureFromBase64String(capturescreenshot());
		}

	}

	

	@Test(description = "Validate Iframe Navigation") 
	public void FF_008()
	{
		test = extent.createTest("Frames functionality").assignAuthor("Mayank Rusia");
		scroll();
		
		//after navigating to iframe

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.switchTo().frame(0);
		WebElement nameElement = driver.findElement(By.cssSelector("input[class='text_field']"));
		WebElement JobElement = driver.findElement(By.cssSelector(".drop_down"));
		WebElement dateElement = driver.findElement(By.cssSelector("input[placeholder='mm/dd/yyyy']"));
		WebElement MaleGenderElement = driver.findElement(By.cssSelector("label[for='RESULT_RadioButton-1_0']"));
		WebElement FemaleGenderElement = driver.findElement(By.cssSelector("label[for='RESULT_RadioButton-1_1']"));		
		WebElement Reportabuse = driver.findElement(By.cssSelector("a[tabindex='-1'][target='_blank']"));
		WebElement PoweredbyFormsite = driver.findElement(By.cssSelector("a[target='_top']"));
		WebElement button = driver.findElement(By.cssSelector("input[value='Submit']"));
		
		if(nameElement.isDisplayed() && JobElement.isDisplayed() && dateElement.isDisplayed() && MaleGenderElement.isDisplayed() && FemaleGenderElement.isDisplayed() && Reportabuse.isDisplayed() && PoweredbyFormsite.isDisplayed() && button.isDisplayed())
		{
			test.pass("Name textbox is loaded");
			test.pass("Job drop-down is loaded");
			test.pass("Date picker is loaded");
			test.pass("Male Gender radio buttons is loaded");
			test.pass("Female Gender radio buttons is loaded");
			test.pass("Report abuse link is loaded");
			test.pass("Powered by formsite link is loaded");
			test.pass("Submit button is loaded");
		}
		else 
		{
		test.fail("Name textbox is NOt loaded");
		test.fail("Job drop-down is NOt loaded");
		test.fail("Date picker is NOt loaded");
		test.fail("Male Gender radio buttons is NOt loaded");
		test.fail("Female Gender radio buttons is NOt loaded");
		test.fail("Report abuse link is NOt loaded");
		test.fail("Powered by formsite link is NOt loaded");
		test.fail("Submit button is NOt loaded");
		test.addScreenCaptureFromBase64String(capturescreenshot());
		}
		
	}


	@Test(description = "Validate Iframe Refresh") 
	public void FF_009()
	{
		test = extent.createTest("Frames functionality").assignAuthor("Mayank Rusia");
		scroll();

		//before refreshing page
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.switchTo().frame(0);
		
		WebElement nameElement1 = driver.findElement(By.cssSelector("input[class='text_field']"));
		WebElement dateElement1 = driver.findElement(By.cssSelector("input[placeholder='mm/dd/yyyy']"));

		nameElement1.sendKeys("Aman");
		dateElement1.sendKeys("5/15/1999");

		String bactname1 = nameElement1.getAttribute("value");
		String bactdate1 = dateElement1.getAttribute("value");
				
		String expname = "Aman";
		String expdate = "5/15/1999";

		
		//after refreshing page
		
		driver.navigate().refresh();
		
		driver.switchTo().frame(0);
		
		WebElement nameElement = driver.findElement(By.cssSelector("input[class='text_field']"));
		WebElement dateElement = driver.findElement(By.cssSelector("input[placeholder='mm/dd/yyyy']"));

		String aactname = nameElement.getAttribute("value");
		String aactdate = dateElement.getAttribute("value");
				
		if(!aactdate.equals(bactdate1) && !aactname.equals(bactname1))
		{
			test.pass("Data is removed after refreshing page");
			test.pass("Name is removed after refreshing page");
		}
		else 
		{
		test.fail("Data is NOT removed after refreshing page");
		test.fail("Name is NOT removed after refreshing page");
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
		WebElement scrollingElement = driver.findElement(By.xpath("//div[@class='widget-content']//iframe"));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView();", scrollingElement);
	}
}