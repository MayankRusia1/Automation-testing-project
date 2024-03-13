package TS_018_FooterSection;

import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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


public class TC_FS 
{
	WebDriver driver ;
	ExtentReports extent;
	ExtentTest test;

	@BeforeSuite
	public void configurereport()
	{
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(".//Reports//FS_005.html");
		sparkReporter.config().setReportName("footer section testing report");
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


	@Test(description = "Validate whether \" merrymoonmary\" is hyperlink & when clicked ,navigating to \"istock\" photo home page in a new browser window")
	public void FS_002()
	{
		test = extent.createTest("Footer section").assignAuthor("Mayank Rusia");
		scroll();
		WebElement links = driver.findElement(By.linkText("merrymoonmary"));
		int count = 0;

		if (links.getTagName().equals("a")) 
		{
			links.click();

			Set<String> options = driver.getWindowHandles();

			for(String eString : options)
			{
				count++;
				String title = driver.switchTo().window(eString).getTitle();
				
				if(title.contains("Automation Testing Practice"))
				{
					System.out.println(count+"st browser window ID: "+eString); 
					System.out.println(title);    				//Automation Testing Practice
					System.out.println(driver.getCurrentUrl()); //https://testautomationpractice.blogspot.com/
					
					test.info("1st browser window ID: "+eString);
					test.info("1st Page title: "+title);
					test.info("1st page URL: "+driver.getCurrentUrl());
					test.addScreenCaptureFromBase64String(capturescreenshot(),"1st browsing window");
				}
				if(title.contains("merrymoonmary Stock Image and Video Portfolio - iStock"))
				{
					System.out.println(count+"nd browser window ID: "+eString);  
					System.out.println(title);					//merrymoonmary Stock Image and Video Portfolio - iStock
					System.out.println(driver.getCurrentUrl());	//https://www.istockphoto.com/portfolio/merrymoonmary?platform=blogger		
					
					test.info("2nd browser window ID: "+eString);
					test.info("2nd Page title: "+title);
					test.info("2nd page URL: "+driver.getCurrentUrl());
					test.addScreenCaptureFromBase64String(capturescreenshot(),"2nd browsing window");
				}
			}
		} 
		else 
		{
			System.out.println("The element is not a hyperlink.");
		}
		
		if(count == 2)
		{
			test.pass("merrymoonmary Stock Image and Video Portfolio - iStock, opened in a new browsing window");
		}
		else
		{
			test.fail("merrymoonmary Stock Image and Video Portfolio - iStock, opened in Same browsing window");
		}
		
	}
	
	

	@Test(description = "Validate whether \" Bloggger\" is hyperlink & when clicked ,navigating to \"Blogger\" home page in a new browser window")
	public void FS_003()
	{
		test = extent.createTest("Footer section").assignAuthor("Mayank Rusia");
		scroll();
		WebElement blogger = driver.findElement(By.linkText("Blogger"));
		String tagname = blogger.getTagName();
		int count=0;
		if(tagname.contains("a"))
		{
			blogger.click();

			Set<String> options = driver.getWindowHandles();

			for(String eString : options)
			{
				count++;
				String title = driver.switchTo().window(eString).getTitle();
				
				if(title.contains("Automation Testing Practice"))
				{
					System.out.println(count+"st browser window ID: "+eString); 
					System.out.println(title);    				//Automation Testing Practice
					System.out.println(driver.getCurrentUrl()); //https://testautomationpractice.blogspot.com/
					
					test.info("1st browser window ID: "+eString);
					test.info("1st Page title: "+title);
					test.info("1st page URL: "+driver.getCurrentUrl());
					test.addScreenCaptureFromBase64String(capturescreenshot(),"1st browsing window");
				}
				if(title.contains("Blogger.com - Create a unique and beautiful blog easily."))
				{
					System.out.println(count+"nd browser window ID: "+eString);  
					System.out.println(title);					
					System.out.println(driver.getCurrentUrl());	
					
					test.info("2nd browser window ID: "+eString);
					test.info("2nd Page title: "+title);
					test.info("2nd page URL: "+driver.getCurrentUrl());
					test.addScreenCaptureFromBase64String(capturescreenshot(),"2nd browsing window");
				}
			}
		} 
		else 
		{
			System.out.println("The element is not a hyperlink.");
		}
		
		if(count == 2)
		{
			test.pass("Blogger.com - Create a unique and beautiful blog easily., opened in a new browsing window");
		}
		else
		{
			test.fail("Blogger.com - Create a unique and beautiful blog easily., opened in Same browsing window");
		}
	}
	
	

	@Test(description = "Validate the behaviour of hyperlinks, when user hover on it.")
	public void FS_004()
	{
		test = extent.createTest("Footer section").assignAuthor("Mayank Rusia");
		scroll();
		WebElement blogger = driver.findElement(By.linkText("Blogger"));
		WebElement links = driver.findElement(By.linkText("merrymoonmary"));
		WebElement textElement = driver.findElement(By.xpath("//div[contains(@class,'widget Attribution')]//div[contains(@class,'widget-content')]"));

		String bloggercolor = blogger.getCssValue("color"); 		
		String linkscolor = links.getCssValue("color");		
		String textcolor =	textElement.getCssValue("color");  
		
		if(bloggercolor.equals("rgba(38, 78, 170, 1)") && linkscolor.equals("rgba(38, 78, 170, 1)") && textcolor.equals("rgba(33, 37, 41, 1)"))
		{
			test.pass("Hyperlink & rest of the text are displayed with different color code, hyperlink is highlighted");
			test.addScreenCaptureFromBase64String(capturescreenshot(),"Footer section hyperlinks");
		}
		else 
		{
		test.fail("Hyperlink & rest of the text are displayed with same color code, hyperlink is NOT highighted");
		test.addScreenCaptureFromBase64String(capturescreenshot(),"Hyperlinks");
		}
		
		
		
	}


	@Test(description = "Validate footer section content display")
	public void FS_005()
	{
		test = extent.createTest("Footer section").assignAuthor("Mayank Rusia");
		scroll();
		String exptext = "Simple theme. Theme images by merrymoonmary. Powered by Blogger.";
		String acttext = driver.findElement(By.xpath("//div[@class='widget Attribution']//div[@class='widget-content']")).getText();		
		WebElement blogger = driver.findElement(By.linkText("Blogger"));
		WebElement merrymoon = driver.findElement(By.linkText("merrymoonmary"));

		if(blogger.getTagName().contains("a"))
		{
			test.pass("'Blogger' is a hyperlink");
		}
		else 
		{
			test.fail("'Blogger' is NOT a hyperlink");
			test.addScreenCaptureFromBase64String(capturescreenshot(),"Blogger");
		}

		if(merrymoon.getTagName().contains("a"))
		{
			test.pass("'merrymoonmary' is a hyperlink");
		}
		else 
		{
			test.fail("'merrymoonmary' is NOT a hyperlink");
			test.addScreenCaptureFromBase64String(capturescreenshot(),"merrymoonmary");
		}

		if(exptext.equals(acttext))
		{
			test.pass("Correct text is displayed");
		}
		else 
		{
		test.fail("Wrong text is displayed");
		test.addScreenCaptureFromBase64String(capturescreenshot(),"Text");
		}
	}


	@AfterTest
	public void teardown() throws InterruptedException
	{
		Thread.sleep(2500);
		driver.quit();
	}

	public void scroll()
	{
		WebElement scrolling = driver.findElement(By.xpath("//div[@class='widget Attribution']//div[@class='widget-content']"));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView();",scrolling);
	}
}