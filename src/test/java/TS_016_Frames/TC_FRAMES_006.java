package TS_016_Frames;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_FRAMES_006
{
	WebDriver driver;
	
	@Parameters("browser")
	@BeforeTest
	public void setup(@Optional("firefox") String browsername)
	{
		if(browsername.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println("Firefox browser is launched");
		}
		
		if(browsername.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println("Chrome browser is launched");
		}
		
		if(browsername.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println("Edge browser is launched");
		}
	}
	
	@Test(description = "Cross browser testing")
	public void FRAMES_006()
	{
		driver.manage().window().maximize();
		driver.get("https://testautomationpractice.blogspot.com/");
		scroll();
		
		WebElement frames = driver.findElement(By.xpath("//h2[normalize-space()='Frames']"));
		Assert.assertTrue(frames.isDisplayed(), "Iframes are NOT loaded successfully");

		driver.switchTo().frame(0);
		WebElement nameElement = driver.findElement(By.cssSelector("input[class='text_field']"));
		WebElement JobElement = driver.findElement(By.cssSelector(".drop_down"));
		WebElement dateElement = driver.findElement(By.cssSelector("input[placeholder='mm/dd/yyyy']"));
		WebElement MaleGenderElement = driver.findElement(By.cssSelector("label[for='RESULT_RadioButton-1_0']"));
		WebElement FemaleGenderElement = driver.findElement(By.cssSelector("label[for='RESULT_RadioButton-1_1']"));		
		WebElement Reportabuse = driver.findElement(By.cssSelector("a[tabindex='-1'][target='_blank']"));
		WebElement PoweredbyFormsite = driver.findElement(By.cssSelector("a[target='_top']"));
		WebElement button = driver.findElement(By.cssSelector("input[value='Submit']"));

		SoftAssert softAssert = new SoftAssert();

		softAssert.assertTrue(nameElement.isDisplayed(), "Name textbox is NOt loaded");
		softAssert.assertTrue(JobElement.isDisplayed(), "Job drop-down is NOt loaded");
		softAssert.assertTrue(dateElement.isDisplayed(), "Date picker is NOt loaded");
		softAssert.assertTrue(MaleGenderElement.isDisplayed(), "Male Gender radio buttons is NOt loaded");
		softAssert.assertTrue(FemaleGenderElement.isDisplayed(), "Female Gender radio buttons is NOt loaded");		
		softAssert.assertTrue(Reportabuse.isDisplayed(), "Report abuse link is NOt loaded");
		softAssert.assertTrue(PoweredbyFormsite.isDisplayed(), "Powered by formsite link is NOt loaded");
		softAssert.assertTrue(button.isDisplayed(), "Submit button is NOt loaded");

		softAssert.assertAll();

	}
	
	@AfterTest
	public void teardown() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.close();
	}
	
	public void scroll()
	{
		WebElement scrolling = driver.findElement(By.xpath("//h2[normalize-space()='Frames']"));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView();", scrolling);
	}
}
