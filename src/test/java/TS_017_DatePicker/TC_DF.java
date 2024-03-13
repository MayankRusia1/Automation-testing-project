package TS_017_DatePicker;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.ui.Select;
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
import com.google.common.base.CaseFormat;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TC_DF 
{
	WebDriver driver ;
	ExtentReports extent;
	ExtentTest test;

	@BeforeSuite
	public void configurereport()
	{
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(".//Reports//DF_008.html");
		sparkReporter.config().setReportName("Date picker testing report");
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


	@Test(description = "Validate whether Date picker is opening a date menu after clicking on Date picker functionality.")
	public void DF_002()
	{
		test = extent.createTest("Date picker functionality").assignAuthor("Mayank Rusia");
		WebElement Datepicker = driver.findElement(By.xpath("//input[@id='datepicker']"));
		Datepicker.click();
		test.pass("Date picker is opened");
		test.addScreenCaptureFromBase64String(capturescreenshot());
	}



	@Test(dataProvider = ("datedata"), description = "Validate different formats of dates allowed / not allowed as entered by user directly.")
	public void DF_003(String data) throws InterruptedException
	{
		test = extent.createTest("Date picker functionality").assignAuthor("Mayank Rusia");
		scroll();
		WebElement datepicker = driver.findElement(By.xpath("//input[@id='datepicker']"));

		if(data.equalsIgnoreCase("05/11/2023"))
		{
			datepicker.sendKeys(data);										Thread.sleep(1500);
			datepicker.sendKeys(Keys.ENTER);
			String exptext = "05/11/2023";
			String acttext = datepicker.getAttribute("value");				Thread.sleep(1500);
			//			System.out.println(acttext);
			if(exptext.equals(acttext))
			{
				test.pass("Correct date is picked for: "+data);
			}
			else 
			{
				test.fail("Wrong date is picked for: "+data);
				test.addScreenCaptureFromBase64String(screenshots(),"1st test data");
			}
			datepicker.clear();
		}


		if(data.equalsIgnoreCase("12/05/2055"))
		{
			datepicker.sendKeys(data);								Thread.sleep(1500);
			datepicker.sendKeys(Keys.ENTER);
			String exptext = "12/05/2055";
			String acttext = datepicker.getAttribute("value");		Thread.sleep(1500);
			//			System.out.println(acttext);
			if(exptext.equals(acttext))
			{
				test.pass("Correct date is picked for: "+data);
			}
			else 
			{
				test.fail("Wrong date is picked for: "+data);
				test.addScreenCaptureFromBase64String(screenshots(),"2nd test data");
			}
			datepicker.clear();
		}

		//picking current local date
		LocalDate currentDate = LocalDate.now();
		// Format the date using DateTimeFormatter
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formattedDate = currentDate.format(formatter);		

		if(data.equalsIgnoreCase("31/5/1997"))
		{
			datepicker.sendKeys(data);								Thread.sleep(1500);
			datepicker.sendKeys(Keys.ENTER);
			String exptext = formattedDate;
			String acttext = datepicker.getAttribute("value");		Thread.sleep(1500);
			//			System.out.println(acttext);
			if(exptext.equals(acttext))
			{
				test.pass("Correct date is picked for: "+data);
			}
			else 
			{
				test.fail("Wrong date is picked for: "+data);
				test.addScreenCaptureFromBase64String(screenshots(),"5th test data");
			}
			datepicker.clear();
		}


		if(data.equalsIgnoreCase("3*15*2022"))
		{
			datepicker.sendKeys(data);								Thread.sleep(1500);
			datepicker.sendKeys(Keys.ENTER);
			String exptext = "03/15/2022";
			String acttext = datepicker.getAttribute("value");		Thread.sleep(1500);
			//			System.out.println(acttext);
			if(exptext.equals(acttext))
			{
				test.pass("Correct date is picked for :"+data);
			}
			else 
			{
				test.fail("Wrong date is picked for: "+data);
				test.addScreenCaptureFromBase64String(screenshots(),"4th test data");
			}
			datepicker.clear();
		}

		if(data.equalsIgnoreCase("8-22-2023"))
		{
			datepicker.sendKeys(data);								Thread.sleep(1500);
			datepicker.sendKeys(Keys.ENTER);
			String exptext = "08/22/2023";
			String acttext = datepicker.getAttribute("value");		Thread.sleep(1500);
			//			System.out.println(acttext);
			if(exptext.equals(acttext))
			{
				test.pass("Correct date is picked for: "+data);
			}
			else 
			{
				test.fail("Wrong date is picked for: "+data);
				test.addScreenCaptureFromBase64String(screenshots(),"3rd test data");
			}
			datepicker.clear();
		}

		if(data.equalsIgnoreCase("15,dec 1997"))
		{
			datepicker.sendKeys(data);								Thread.sleep(1500);
			datepicker.sendKeys(Keys.ENTER);
			String exptext = formattedDate;
			String acttext = datepicker.getAttribute("value");		Thread.sleep(1500);
			//			System.out.println(acttext);
			if(exptext.equals(acttext))
			{
				test.pass("Correct date is picked for: "+data);
			}
			else 
			{
				test.fail("Wrong date is picked for: "+data);
				test.addScreenCaptureFromBase64String(screenshots(),"6th test data");
			}
			datepicker.clear();
		}

		if(data.equalsIgnoreCase("1997/6/11"))
		{
			datepicker.sendKeys(data);								Thread.sleep(1500);
			datepicker.sendKeys(Keys.ENTER);
			String exptext = formattedDate;
			String acttext = datepicker.getAttribute("value");		Thread.sleep(1500);
			//			System.out.println(acttext);
			if(exptext.equals(acttext))
			{
				test.pass("Correct date is picked for: "+data);
			}
			else 
			{
				test.fail("Wrong date is picked for: "+data);
				test.addScreenCaptureFromBase64String(screenshots(),"7th test data");
			}
			datepicker.clear();
		}

		if(data.equalsIgnoreCase("23,may 23"))
		{
			datepicker.sendKeys(data);								Thread.sleep(1500);
			datepicker.sendKeys(Keys.ENTER);
			String exptext = formattedDate;
			String acttext = datepicker.getAttribute("value");		Thread.sleep(1500);
			//			System.out.println(acttext);
			if(exptext.equals(acttext))
			{
				test.pass("Correct date is picked for: "+data);
			}
			else 
			{
				test.fail("Wrong date is picked for: "+data);
				test.addScreenCaptureFromBase64String(screenshots(),"8th test data");
			}
			datepicker.clear();
		}

	}


	@DataProvider(name = "datedata")
	public Object[][] getdata()
	{
		Object [][] data = {
				{"05/11/2023"},{"12/05/2055"},{"8-22-2023"},
				{"3*15*2022"},{"31/5/1997"},{"15,dec 1997"},
				{"1997/6/11"},{"23,may 23"}
		};
		return data;
	}



	@Test(description = "Validate whether user is able to select current month, year date from calendar.")	
	public void DF_004() throws Exception
	{
		test = extent.createTest("Date picker functionality").assignAuthor("Mayank Rusia");
		scroll();
		WebElement datepicker = driver.findElement(By.xpath("//input[@id='datepicker']"));
		datepicker.click();

		//create calendar(static class) instance & using it we can get current calendar details
		Calendar calendar = Calendar.getInstance();

		String orgdate = "03/20/2024";	
		SimpleDateFormat dateformatting = new SimpleDateFormat("MM/dd/yyyy");

		Date formatteddate1;
		try 
		{
			//check for date entered, whether it is Correct or not
			dateformatting.setLenient(false);  //if not used, & if we enter date beyond month date limit, it will shift extra date in next month & this adding extra date in next month is bydefault enable, so we need to disable this feature by giving (false)
			formatteddate1 = dateformatting.parse(orgdate);
			calendar.setTime(formatteddate1);				

			int targetday = calendar.get(Calendar.DAY_OF_MONTH);
			int targetmonth = calendar.get(Calendar.MONTH)+1;
			int targetyear = calendar.get(Calendar.YEAR);

			driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']//td[not(contains(@class,'ui-datepicker-other-month'))]//a[text()="+targetday+"]")).click();

			Thread.sleep(2000);

			test.addScreenCaptureFromBase64String(screenshots());
		}
		catch (ParseException e) 
		{
			throw new Exception("Invalid date is entered...please check the input date");
		}



	}


	@Test(description = "Validate whether user is able to select past month, year date from calendar.")
	public void DF_005() throws Exception
	{
		test = extent.createTest("Date picker functionality").assignAuthor("Mayank Rusia");
		scroll();
		WebElement datepicker = driver.findElement(By.xpath("//input[@id='datepicker']"));
		datepicker.click();

		test.info("Date picker is clicked & calender is opened");

		Calendar calendar = Calendar.getInstance();

		String orgdate = "3/15/2020";	
		SimpleDateFormat dateformatting = new SimpleDateFormat("MM/dd/yyyy");

		Date formatteddate1;
		Date formatteddate2;
		try 
		{
			//check for date entered, whether it is Correct or not
			dateformatting.setLenient(false);  //if not used, & if we enter date beyond month date limit, it will shift extra date in next month & this adding extra date in next month is bydefault enable, so we need to disable this feature by giving (false)
			formatteddate1 = dateformatting.parse(orgdate);
			calendar.setTime(formatteddate1);				

			int targetday = calendar.get(Calendar.DAY_OF_MONTH);
			int targetmonth = calendar.get(Calendar.MONTH)+1;
			int targetyear = calendar.get(Calendar.YEAR);


			//original month year data on calendar
			WebElement monthyear = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']"));

			String actualdate = monthyear.getText();
			SimpleDateFormat dateformat = new SimpleDateFormat("MMM yyyy");
			dateformat.setLenient(false);
			formatteddate2 = dateformat.parse(actualdate);
			calendar.setTime(formatteddate2);

			int tmonth = calendar.get(calendar.MONTH)+1;
			int tyear = calendar.get(calendar.YEAR);

			if(tmonth == targetmonth && tyear == targetyear)
			{
				driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']//td[not(contains(@class,'ui-datepicker-other-month'))]//a[text()="+targetday+"]")).click();
			}

			else
			{
				while(targetmonth < tmonth || targetyear < tyear)
				{
					driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-w']")).click();

					//retrieve month year details every time from each calendar we click previous button for checking purpose
					monthyear = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']"));          //if not using it here, it is giving StaleElementReferenceException
					actualdate = monthyear.getText();
					dateformat = new SimpleDateFormat("MMM yyyy");
					dateformat.setLenient(false);
					formatteddate2 = dateformat.parse(actualdate);
					calendar.setTime(formatteddate2);

					tmonth = calendar.get(calendar.MONTH)+1;
					tyear = calendar.get(calendar.YEAR);				
				}

				driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']//td[not(contains(@class,'ui-datepicker-other-month'))]//a[text()="+targetday+"]")).click();

				test.addScreenCaptureFromBase64String(screenshots());
				test.info("Desire date is selected & displayed inside date picker box");
			}
		}
		catch (ParseException e) 
		{
			throw new Exception("Invalid date is entered...please check the input date");
		}

	}


	@Test(description = "Validate whether user is able to select future month, year date from calendar.")
	public void DF_006() throws Exception
	{
		test = extent.createTest("Date picker functionality").assignAuthor("Mayank Rusia");
		scroll();
		WebElement datepicker = driver.findElement(By.xpath("//input[@id='datepicker']"));
		datepicker.click();
		test.info("Date picker is clicked");
		Calendar calendar = Calendar.getInstance();

		String orgdate = "8/25/2028";	
		SimpleDateFormat dateformatting = new SimpleDateFormat("MM/dd/yyyy");

		Date formatteddate1;
		Date formatteddate2;
		try 
		{
			//check for date entered, whether it is Correct or not
			dateformatting.setLenient(false);  //if not used, & if we enter date beyond month date limit, it will shift extra date in next month & this adding extra date in next month is bydefault enable, so we need to disable this feature by giving (false)
			formatteddate1 = dateformatting.parse(orgdate);
			calendar.setTime(formatteddate1);				

			int targetday = calendar.get(Calendar.DAY_OF_MONTH);
			int targetmonth = calendar.get(Calendar.MONTH)+1;
			int targetyear = calendar.get(Calendar.YEAR);


			//original month year data on calendar
			WebElement monthyear = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']"));

			String actualdate = monthyear.getText();
			SimpleDateFormat dateformat = new SimpleDateFormat("MMM yyyy");
			dateformat.setLenient(false);
			formatteddate2 = dateformat.parse(actualdate);
			calendar.setTime(formatteddate2);

			int tmonth = calendar.get(calendar.MONTH)+1;
			int tyear = calendar.get(calendar.YEAR);

			if(tmonth == targetmonth && tyear == targetyear)
			{
				driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']//td[not(contains(@class,'ui-datepicker-other-month'))]//a[text()="+targetday+"]")).click();
			}

			else
			{
				test.info("User start navigating to future month, using navigation buttons displayed on calendar");
				while(targetmonth > tmonth || targetyear > tyear)
				{
					driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']")).click();

					//retrieve month year details every time from each calendar we click previous button for checking purpose
					monthyear = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']"));          //if not using it here, it is giving StaleElementReferenceException
					actualdate = monthyear.getText();
					dateformat = new SimpleDateFormat("MMM yyyy");
					dateformat.setLenient(false);
					formatteddate2 = dateformat.parse(actualdate);
					calendar.setTime(formatteddate2);

					tmonth = calendar.get(calendar.MONTH)+1;
					tyear = calendar.get(calendar.YEAR);				
				}

				driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']//td[not(contains(@class,'ui-datepicker-other-month'))]//a[text()="+targetday+"]")).click();
				test.addScreenCaptureFromBase64String(screenshots(),"Date selected");
			}

			Thread.sleep(2000);

		}
		catch (ParseException e) 
		{
			throw new Exception("Invalid date is entered...please check the input date");
		}	

	}


	@Test(description = "Validate whether all dates in month are arranged in ascending order.")
	public void DF_007()
	{
		test = extent.createTest("Date picker functionality").assignAuthor("Mayank Rusia");
		scroll();
		WebElement datepicker = driver.findElement(By.xpath("//input[@id='datepicker']"));
		datepicker.click();
		List<WebElement> rows =	driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//td[not(contains(@class,'ui-datepicker-other-month'))]"));
		String [] datedata = new String[rows.size()];

		for(int i=0; i< rows.size(); i++)
		{
			datedata[i] = rows.get(i).getText();
		}

		Arrays.sort(datedata);

		String [] sorteddatedata = new String[rows.size()];

		for(int i=0; i< rows.size(); i++)
		{
			sorteddatedata[i] = datedata[i];
		}

		Assert.assertEquals(sorteddatedata, datedata,"Calender dates are NOT arranged in ascending order");
		if(Arrays.equals(sorteddatedata, datedata))
		{
			test.pass("Dates are arranged in asceding order");
		}
		else 
		{
			test.fail("Dates are NOT arranged in ascending order");
			test.addScreenCaptureFromBase64String(capturescreenshot(),"Calendar");
		}
	}



	@Test(description = "Validate whether by default, datepicker will blink current day & show month,year")
	public void DF_008()
	{
		test = extent.createTest("Date picker functionality").assignAuthor("Mayank Rusia");
		scroll();

		//retrieving current date
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formatteddate = currentDate.format(formatter);

		String mm = formatteddate.split("/")[0].trim(); 
		String day = formatteddate.split("/")[1].trim();
		String yy = formatteddate.split("/")[2].trim();

		//removing leading zero, if any
		String updatedday = day.replaceFirst("^0+", "");		//removing leading zero, if any

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		//click date picker
		driver.findElement(By.xpath("//input[@id='datepicker']")).click();

		//retrieving current month & year from calendar
		WebElement currentMonth = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']"));
		WebElement currentYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']"));

		String month = currentMonth.getText();
		String year = currentYear.getText();

		switch(mm)
		{
		case "01": 	if(month.equals("January"))
		{
			test.pass("Correct month name displayed");
			break;
		}
		else 
		{
			test.fail("Wrong month name displayed");
			test.addScreenCaptureFromBase64String(yearss(),"January");
			break;
		}


		case "02": 	if(month.equals("February"))
		{
			test.pass("Correct month name displayed");
			break;
		}
		else 
		{
			test.fail("Wrong month name displayed");
			test.addScreenCaptureFromBase64String(yearss(),"February");
			break;
		}

		case "03": 	if(month.equals("March"))
		{
			test.pass("Correct month name displayed");
			break;
		}
		else 
		{
			test.fail("Wrong month name displayed");
			test.addScreenCaptureFromBase64String(yearss(),"March");
			break;
		}

		case "04": 	if(month.equals("April"))
		{
			test.pass("Correct month name displayed");
			break;
		}
		else 
		{
			test.fail("Wrong month name displayed");
			test.addScreenCaptureFromBase64String(yearss(),"April");
			break;
		}

		case "05": 	if(month.equals("May"))
		{
			test.pass("Correct month name displayed");
			break;
		}
		else 
		{
			test.fail("Wrong month name displayed");
			test.addScreenCaptureFromBase64String(yearss(),"May");
			break;
		}

		case "06": 	if(month.equals("June"))
		{
			test.pass("Correct month name displayed");
			break;
		}
		else 
		{
			test.fail("Wrong month name displayed");
			test.addScreenCaptureFromBase64String(yearss(),"June");
			break;
		}

		case "07": 	if(month.equals("July"))
		{
			test.pass("Correct month name displayed");
			break;
		}
		else 
		{
			test.fail("Wrong month name displayed");
			test.addScreenCaptureFromBase64String(yearss(),"July");
			break;
		}

		case "08": 	if(month.equals("August"))
		{
			test.pass("Correct month name displayed");
			break;
		}
		else 
		{
			test.fail("Wrong month name displayed");
			test.addScreenCaptureFromBase64String(yearss(),"August");
			break;
		}

		case "09": 	if(month.equals("September"))
		{
			test.pass("Correct month name displayed");
			break;
		}
		else 
		{
			test.fail("Wrong month name displayed");
			test.addScreenCaptureFromBase64String(yearss(),"September");
			break;
		}

		case "10": 	if(month.equals("October"))
		{
			test.pass("Correct month name displayed");
			break;
		}
		else 
		{
			test.fail("Wrong month name displayed");
			test.addScreenCaptureFromBase64String(yearss(),"October");
			break;
		}

		case "11": 	if(month.equals("November"))
		{
			test.pass("Correct month name displayed");
			break;
		}
		else 
		{
			test.fail("Wrong month name displayed");
			test.addScreenCaptureFromBase64String(yearss(),"November");
			break;
		}

		case "12": 	if(month.equals("December"))
		{
			test.pass("Correct month name displayed");
			break;
		}
		else 
		{
			test.fail("Wrong month name displayed");
			test.addScreenCaptureFromBase64String(yearss(),"December");
			break;
		}

		}


		if(year.equals(yy))
		{
			test.pass("Current year is displayed");
		}
		else 
		{
			test.fail("Wrong year displayed");
			test.addScreenCaptureFromBase64String(yearss(),"Month-year display");
		}


		// retrieving no. of rows & columns in calendar
		List<WebElement> rows =	 driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']/tbody/tr"));
		List<WebElement> columns = 	driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']/tbody/tr[1]/td"));

		//storing all dates in an array
		String [][] dates = new String[rows.size()][columns.size()];

		for(int i=0; i< rows.size(); i++)
		{
			for(int j=0; j < columns.size(); j++)
			{
				dates[i][j] = driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']/tbody/tr["+(i+1)+"]/td["+(j+1)+"]")).getText();
			}
		}

		String actborder1 =null;
		String actbgcolor1 =null;
		String actcolor1 =null;


		// checking & navigating to the element (target date) & retrieving its Css properties
		for(int i=0; i< rows.size(); i++)
		{
			for(int j=0; j < columns.size(); j++)
			{
				if(driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']/tbody/tr["+(i+1)+"]/td["+(j+1)+"]")).getText().equals(updatedday))
				{
					WebElement target = driver.findElement(By.xpath("//body[1]/div[5]/table[1]/tbody[1]/tr["+(i+1)+"]/td["+(j+1)+"]/a"));
					actborder1 =	target.getCssValue("border");
					actbgcolor1 = target.getCssValue("background");
					actcolor1 = target.getCssValue("color");
				}
			}
		}

		WebElement other = driver.findElement(By.xpath("//td[contains(@class,' ui-datepicker-week-end ')]//a"));
		String actborder2 =	other.getCssValue("border");
		String actbgcolor2 = other.getCssValue("background-color");
		String actcolor2 = other.getCssValue("color");


		String expborder1 = "0.8px solid rgb(218, 213, 94)" ;
		String expbgcolo1 = "rgb(255, 250, 144)";
		String expcolor1 = "rgb(119, 118, 32)";

		String expborder2 = "0.8px solid rgb(197, 197, 197)";
		String expbgcolor2 = "rgb(246, 246, 246)";
		String expcolor2 = "rgb(69, 69, 69)";

		//assertions
		if(expborder1.equals(actborder1))
		{
			test.pass("Correct border property with current date");
		}
		else 
		{
			test.fail("Wrong border property with current date");
			test.addScreenCaptureFromBase64String(capturescreenshot(),"Current date property");
		}

		if(expbgcolo1.equals(actbgcolor1))
		{
			test.pass("Correct background color with current date");
		}
		else 
		{
			test.fail("Wrong background color with current date");
			test.addScreenCaptureFromBase64String(capturescreenshot(),"Current date property");
		}

		if(expcolor1.equals(actcolor1))
		{
			test.pass("Correct border color with current date");
		}
		else 
		{
			test.fail("Wrong border color with current date");
			test.addScreenCaptureFromBase64String(capturescreenshot(),"Current date property");
		}

		if(expborder2.equals(actborder2))
		{
			test.pass("Correct border property with other dates");
		}
		else 
		{
			test.fail("Wrong border property with other dates");
			test.addScreenCaptureFromBase64String(capturescreenshot(),"Other date property");
		}

		if(expbgcolor2.equals(actbgcolor2))
		{
			test.pass("Correct background color with other date");
		}
		else 
		{
			test.fail("Wrong background color with other date");
			test.addScreenCaptureFromBase64String(capturescreenshot(),"Other date property");
		}

		if(expcolor2.equals(actcolor2))
		{
			test.pass("Correct border color with other date");
		}
		else 
		{
			test.fail("Wrong border color with other date");
			test.addScreenCaptureFromBase64String(capturescreenshot(),"Other date property");
		}

	}



	@Test(dataProvider = "datedata1", description = "Validate that only Numbers & special character (/) is allowed for entering date in date textbox.")
	public void DF_009(String data) throws InterruptedException
	{
		test = extent.createTest("Date picker functionality").assignAuthor("Mayank Rusia");
		scroll();
		WebElement datepicker = driver.findElement(By.xpath("//input[@id='datepicker']"));


		//picking current local date
		LocalDate currentDate = LocalDate.now();
		// Format the date using DateTimeFormatter
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formattedDate = currentDate.format(formatter);		


		if(data.equalsIgnoreCase("fkjbdkfj"))
		{
			datepicker.sendKeys(data);								Thread.sleep(1000);
			datepicker.sendKeys(Keys.ENTER);
			String exptext = formattedDate;
			String acttext = datepicker.getAttribute("value");			//this is not working, try to improve code
			if(exptext.equals(acttext))
			{
				test.pass("Current date is picked for: "+data);
				test.addScreenCaptureFromBase64String(screenshots());
			}
			else 
			{
				test.fail("Wrong date is picked for: "+data);
				test.addScreenCaptureFromBase64String(screenshots());
			}
			datepicker.clear();		
		}


		if(data.equalsIgnoreCase("32423/*-*/dfdf"))
		{
			datepicker.sendKeys(data);								Thread.sleep(1000);
			datepicker.sendKeys(Keys.ENTER);
			String exptext = formattedDate;
			String acttext = datepicker.getAttribute("value");
			System.out.println(exptext);
			System.out.println(acttext);
			if(exptext.equals(acttext))
			{
				test.pass("Current date is picked for: "+data);
				test.addScreenCaptureFromBase64String(screenshots());
			}
			else 
			{
				test.fail("Wrong date is picked for: "+data);
				test.addScreenCaptureFromBase64String(screenshots());
			}
			datepicker.clear();
		}


		if(data.equalsIgnoreCase("df    _4548937-**"))
		{
			datepicker.sendKeys(data);								Thread.sleep(1000);
			datepicker.sendKeys(Keys.ENTER);
			String exptext = formattedDate;
			String acttext = datepicker.getAttribute("value");
			System.out.println(exptext);
			System.out.println(acttext);
			if(exptext.equals(acttext))
			{
				test.pass("Current date is picked for: "+data);
				test.addScreenCaptureFromBase64String(screenshots());
			}
			else 
			{
				test.fail("Wrong date is picked for: "+data);
				test.addScreenCaptureFromBase64String(screenshots());
			}
			datepicker.clear();
		}


		if(data.equalsIgnoreCase("6464**54"))
		{
			datepicker.sendKeys(data);								Thread.sleep(1000);
			datepicker.sendKeys(Keys.ENTER);
			String exptext = formattedDate;
			String acttext = datepicker.getAttribute("value");
			System.out.println(exptext);
			System.out.println(acttext);
			if(exptext.equals(acttext))
			{
				test.pass("Current date is picked for: "+data);
				test.addScreenCaptureFromBase64String(screenshots());
			}
			else 
			{
				test.fail("Wrong date is picked for: "+data);
				test.addScreenCaptureFromBase64String(screenshots());
			}
			datepicker.clear();
		}


		if(data.equalsIgnoreCase("5465/*-+dfds$#@"))
		{
			datepicker.sendKeys(data);								Thread.sleep(1000);
			datepicker.sendKeys(Keys.ENTER);
			String exptext = formattedDate;
			String acttext = datepicker.getAttribute("value");
			System.out.println(exptext);
			System.out.println(acttext);
			if(exptext.equals(acttext))
			{
				test.pass("Current date is picked for: "+data);
				test.addScreenCaptureFromBase64String(screenshots());
			}
			else 
			{
				test.fail("Wrong date is picked for: "+data);
				test.addScreenCaptureFromBase64String(screenshots());
			}
			datepicker.clear();
		}


		if(data.equalsIgnoreCase("third december, two thousand twenty three"))
		{
			datepicker.sendKeys(data);								Thread.sleep(1000);
			datepicker.sendKeys(Keys.ENTER);
			String exptext = formattedDate;
			String acttext = datepicker.getAttribute("value");
			System.out.println(exptext);
			System.out.println(acttext);
			if(exptext.equals(acttext))
			{
				test.pass("Current date is picked for: "+data);
				test.addScreenCaptureFromBase64String(screenshots());
			}
			else 
			{
				test.fail("Wrong date is picked for: "+data);
				test.addScreenCaptureFromBase64String(screenshots());
			}
			datepicker.clear();
		}


		if(data.equalsIgnoreCase("5/15/1997"))
		{
			datepicker.sendKeys(data);								Thread.sleep(1000);
			datepicker.sendKeys(Keys.ENTER);
			String exptext = "5/15/1997";
			String acttext = datepicker.getAttribute("value");
			System.out.println(exptext);
			System.out.println(acttext);
			if(exptext.equals(acttext))
			{
				test.pass("Correct date is picked for: "+data);
				test.addScreenCaptureFromBase64String(screenshots());
			}
			else 
			{
				test.fail("Wrong date is picked for: "+data);
				test.addScreenCaptureFromBase64String(screenshots());
			}
			datepicker.clear();
		}


		if(data.equalsIgnoreCase("36*15*twenty three"))
		{
			datepicker.sendKeys(data);								Thread.sleep(1000);
			datepicker.sendKeys(Keys.ENTER);
			String exptext = formattedDate;
			String acttext = datepicker.getAttribute("value");
			System.out.println(exptext);
			System.out.println(acttext);
			if(exptext.equals(acttext))
			{
				test.pass("Current date is picked for: "+data);
				test.addScreenCaptureFromBase64String(screenshots());
			}
			else 
			{
				test.fail("Wrong date is picked for: "+data);
				test.addScreenCaptureFromBase64String(screenshots());
			}
			datepicker.clear();
		}


		if(data.equalsIgnoreCase("213/8978/654/2131"))
		{
			datepicker.sendKeys(data);								Thread.sleep(1000);
			datepicker.sendKeys(Keys.ENTER);
			String exptext = formattedDate;
			String acttext = datepicker.getAttribute("value");
			System.out.println(exptext);
			System.out.println(acttext);
			if(exptext.equals(acttext))
			{
				test.pass("Current date is picked for: "+data);
				test.addScreenCaptureFromBase64String(screenshots());
			}
			else 
			{
				test.fail("Wrong date is picked for: "+ data);
				test.addScreenCaptureFromBase64String(screenshots());
			}
			datepicker.clear();
		}

	}

	@DataProvider(name = "datedata1")
	public Object[][] getdata1()
	{
		Object [][] data = {
				{"fkjbdkfj"},{"32423/*-*/dfdf"},{"df    _4548937-**"},
				{"6464**54"},{"5465/*-+dfds$#@"},{"third december, two thousand twenty three"},
				{"5/15/1997"},{"36*15*twenty three"},{"213/8978/654/2131"}
		};
		return data;
	}


	@Test(description = "Validate proper placeholder is displayed inside date picker textbox.")
	public void DF_010() throws IOException
	{
		test = extent.createTest("Date picker functionality").assignAuthor("Mayank Rusia");
		WebElement dateeElement = driver.findElement(By.xpath("//input[@id='datepicker']"));
		String exptext = "MM/dd/yyyy";
		String acttxt = dateeElement.getAttribute("value");
		if(exptext.equals(acttxt))
		{
			test.pass("Correct placeholder is present");
		}
		else 
		{
			test.fail("Wrong placeholder / no placeholder is present");
			test.addScreenCaptureFromBase64String(screenshots());
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
		WebElement scrollingElement = driver.findElement(By.xpath("//a[normalize-space()='open cart']"));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView();", scrollingElement);
	}

	public String screenshots()
	{
		WebElement tss = driver.findElement(By.xpath("//p[normalize-space()='Date:']"));
		String img = tss.getScreenshotAs(OutputType.BASE64);
		return img;
	}

	public String yearss()
	{
		WebElement tss = driver.findElement(By.xpath("//div[@class='ui-datepicker-title']"));
		String img = tss.getScreenshotAs(OutputType.BASE64);
		return img;
	}
}