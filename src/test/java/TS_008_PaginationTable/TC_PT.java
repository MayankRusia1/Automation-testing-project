package TS_008_PaginationTable;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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



public class TC_PT 
{
	WebDriver driver ;
	ExtentReports extent;
	ExtentTest test;

	@BeforeSuite
	public void configurereport()
	{
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(".//Reports//PT_007.html");
		sparkReporter.config().setReportName("Pagination webtable functionality testing report");
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

	@Test(description = "Validate whether by clicking on checkboxes, user is able to select whole row.")
	public void PT_002()
	{
		test = extent.createTest("Pagination webtable").assignAuthor("Mayank Rusia");
		scroll();
		WebElement checkbox = driver.findElement(By.xpath("//tbody/tr[2]/td[4]/input[1]"));
		checkbox.click();
		if(checkbox.isSelected())
		{
			test.pass("2nd row checkbox is selected");
		}
		else 
		{
			test.fail("2nd row checkbox is NOT selected");
			test.addScreenCaptureFromBase64String(capturescreenshot());
		}
	}
	
	

	@Test(description = "Validate table data is arranged in ascending order with ID.")
	public void PT_003() throws InterruptedException
	{		
		test = extent.createTest("Pagination webtable content arrangement").assignAuthor("Mayank Rusia");
		scroll();
		List<WebElement> paginationnumber = driver.findElements(By.xpath("//ul[@id='pagination']/li"));
		List<WebElement> rows =	driver.findElements(By.xpath("//table[@id='productTable']/tbody/tr"));
		int totalsize = (paginationnumber.size() * rows.size());
		int [] actdata = new int[totalsize];

		int count =0;

		for(int i=1; i<= paginationnumber.size(); i++)
		{
			driver.findElement(By.xpath("//ul[@id='pagination']/li["+i+"]")).click();
			Thread.sleep(1000);

			for(int j=1; j<= rows.size(); j++)
			{
				String result =	driver.findElement(By.xpath("//table[@id='productTable']/tbody/tr["+j+"]/td[1]")).getText();
				actdata[count] = Integer.parseInt(result); 
				count++;
			}
		}


		System.out.println("********Before sort********");
		for(int i=0; i<totalsize; i++)
		{
			System.out.print(actdata[i]+" ");
		}
		System.out.println();

		Arrays.sort(actdata);

		int [] sortdata = new int[totalsize];
		for(int i=0; i<totalsize; i++)
		{
			sortdata[i] = actdata[i];
		}


		System.out.println("********After sort********");
		for(int i=0; i<totalsize; i++)
		{
			System.out.print(sortdata[i]+" ");
		}

		if(Arrays.equals(sortdata, actdata))
		{
			test.pass("Pagination table data is arranged in asceding order, according to its ID");
		}
		else
		{
		test.fail("Pagination table data is NOT arranged in ascending order, as per its ID");
		test.addScreenCaptureFromBase64String(capturescreenshot());
		}

	}

	
	@Test(description = "Validate user is directly able to navigate to any page of table, by clicking on Page No.")
	public void PT_004() throws InterruptedException
	{
		test = extent.createTest("Pagination webtable feature").assignAuthor("Mayank Rusia");
		scroll();
		List<WebElement> paginationnumber = driver.findElements(By.xpath("//ul[@id='pagination']/li"));
		List<WebElement> rows =	driver.findElements(By.xpath("//table[@id='productTable']/tbody/tr"));
		String[] actdata = new String[rows.size()];
		int count = 0;

		String beforeclickcolor = driver.findElement(By.xpath("//a[normalize-space()='3']")).getCssValue("color");
		String beforeclickbgcolor = driver.findElement(By.xpath("//a[normalize-space()='3']")).getCssValue("background-color");;

		WebElement afterclickc = driver.findElement(By.xpath("//a[normalize-space()='3']"));
		String afterclickcolor = null;
		String afterclickbgcolor = null;


		for(int i=1; i<= paginationnumber.size(); i++)
		{
			if(i == 3)
			{
				driver.findElement(By.xpath("//ul[@id='pagination']/li["+i+"]")).click();
				Thread.sleep(1000);

				afterclickcolor = afterclickc.getCssValue("color");
				afterclickbgcolor = afterclickc.getCssValue("background-color");;

				for(int j=1; j<= rows.size(); j++)
				{
					String result =	driver.findElement(By.xpath("//table[@id='productTable']/tbody/tr["+j+"]")).getText();
					actdata[count] = result; 
					count++;
				}
			}
		}


		String expdata[] = new String[rows.size()];
		expdata[0] = "11 Product 11 $20.99";
		expdata[1] = "12 Product 12 $24.99";
		expdata[2] = "13 Product 13 $30.99";
		expdata[3] = "14 Product 14 $19.99";
		expdata[4] = "15 Product 15 $2.99";

		
		System.out.println(beforeclickbgcolor);
		System.out.println(afterclickbgcolor);
		System.out.println(beforeclickcolor);
		System.out.println(afterclickcolor);
		
		for(int i=0; i< expdata.length; i++)
		{
			System.out.print(expdata[i]+" ");
		}
		
		
		for(int j=0; j< rows.size(); j++)
		{
			System.out.print(actdata[j]+" ");
		}
		if(!beforeclickbgcolor.equals(afterclickbgcolor) && !beforeclickcolor.equals(afterclickcolor) && Arrays.equals(expdata, actdata))
		{
			test.pass("User able to navigate to different pages of table & content is properly displayed");
		}
		else 
		{
			test.fail("User is NOT  able to navigate to different pages of table & content is NOT properly displayed");
			test.addScreenCaptureFromBase64String(capturescreenshot());
		}
	}



	@Test(description = "Validate whether Heading of table is properly loaded & displayed as the top row of table & should not get affected, as we navigate to any page of table.")
	public void PT_005()
	{
		test = extent.createTest("Pagination webtable heading").assignAuthor("Mayank Rusia");
		scroll();
		WebElement headingdata = driver.findElement(By.xpath("//table[@id='productTable']/thead/tr"));
		String heading = null;
		List<WebElement> pagination = driver.findElements(By.xpath("//ul[@id='pagination']/li"));

		String expdata = "ID Name Price Select";
		
		System.out.println("*******Expected heading on each page*******");
		System.out.println(expdata);
		
		System.out.println("*******Actual heading on every page*******");
		for(int i=1; i<= pagination.size(); i++)
		{
			driver.findElement(By.xpath("//ul[@id='pagination']/li["+i+"]")).click();
			heading = headingdata.getText();
			System.out.println(heading);
		}
		
		if(expdata.equals(heading))
		{
			test.pass("Proper heading is displayed");
		}
		else 
		{
		test.fail("Proper heading is NOT displayed");
		test.addScreenCaptureFromBase64String(capturescreenshot());
		}
	}

	
	
	@Test(description = "Validate selecting multiple checkboxes on different page of table.")
	public void PT_006() throws InterruptedException
	{
		test = extent.createTest("Pagination webtable").assignAuthor("Mayank Rusia");
		scroll();
		List<WebElement> pagination = driver.findElements(By.xpath("//ul[@id='pagination']/li"));
		boolean expresult1 = true;
		boolean expresult2 = true;
		boolean expresult3 = true;
		boolean expresult4 = true;
		
		boolean result1=false;
		boolean result2 =false;
		boolean result3 =false;
		boolean result4 = false;
		
		for(int i=1; i<= pagination.size(); i++)
		{
			driver.findElement(By.xpath("//ul[@id='pagination']/li["+i+"]")).click();
			Thread.sleep(1000);
			
			if(i==1)
			{
				driver.findElement(By.xpath("//tbody/tr[2]/td[4]/input[1]")).click();
				result1 = driver.findElement(By.xpath("//tbody/tr[2]/td[4]/input[1]")).isSelected();
				test.addScreenCaptureFromBase64String(capturescreenshot(),"1st page checkbox");
			}
			
			if(i==2)
			{
				driver.findElement(By.xpath("//tbody/tr[3]/td[4]/input[1]")).click();
				result2 = driver.findElement(By.xpath("//tbody/tr[3]/td[4]/input[1]")).isSelected();
				test.addScreenCaptureFromBase64String(capturescreenshot(),"2nd page checkbox");
			}
			
			if(i==3)
			{
				driver.findElement(By.xpath("//tbody/tr[1]/td[4]/input[1]")).click();
				result3 = driver.findElement(By.xpath("//tbody/tr[1]/td[4]/input[1]")).isSelected();
				test.addScreenCaptureFromBase64String(capturescreenshot(),"3rd page checkbox");
			}
			
			if(i==4)
			{
				driver.findElement(By.xpath("//tbody/tr[4]/td[4]/input[1]")).click();
				result4 = driver.findElement(By.xpath("//tbody/tr[4]/td[4]/input[1]")).isSelected();			
				test.addScreenCaptureFromBase64String(capturescreenshot(),"4th page checkbox");
			}
		}

		if(result1 == expresult1 && result2 == expresult2 && result3 == expresult3 && result4 == expresult4)
		{
			test.pass("User is able to select all the different checkboxes on different pages");
		}
		else 
		{
			test.fail("User is unable to select all the different checkboxes on different pages");
			test.addScreenCaptureFromBase64String(capturescreenshot());
		}
	}

	
	@Test(description = "Validate status of selected checkbox after navigating to next page after selecting checkbox on previous page.")
	public void PT_007()
	{
		test = extent.createTest("Pagination webtable options features").assignAuthor("Mayank Rusia");
		scroll();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebElement firstpage = driver.findElement(By.xpath("//a[normalize-space()='1']"));
		firstpage.click();
		
		WebElement button1 = driver.findElement(By.xpath("//tbody/tr[2]/td[4]/input[1]"));
		button1.click();
		test.addScreenCaptureFromBase64String(capturescreenshot(),"1st page screenshot");
		boolean beforestatus = button1.isSelected();
		
		WebElement secondpage = driver.findElement(By.xpath("//a[normalize-space()='2']"));
		secondpage.click();
		
		WebElement button2 = driver.findElement(By.xpath("//tbody/tr[3]/td[4]/input[1]"));
		button2.click();
		test.addScreenCaptureFromBase64String(capturescreenshot(),"2nd page screenshot");
		
		firstpage.click();
		boolean afterstatus = driver.findElement(By.xpath("//tbody/tr[2]/td[4]/input[1]")).isSelected();
		test.addScreenCaptureFromBase64String(capturescreenshot(),"After coming back to page 1");
		
		if(beforestatus == afterstatus)
		{
			test.pass("Checkbox doesnot get unchecked, until page is refreshed or reloaded, when user navigate to different pages");
		}
		else 
		{
			test.fail("Checkbox get unchecked automatically, as user navigate to different pages");			
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
		WebElement scrollingElement = driver.findElement(By.xpath("//h2[normalize-space()='Pagination Table']"));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView();", scrollingElement);
	}
}