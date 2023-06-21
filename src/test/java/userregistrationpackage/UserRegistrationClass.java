package userregistrationpackage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import extentreportpackage.ExtentReportClass;

public class UserRegistrationClass {
	
	
	
	
public ExtentReports extent;			
public ExtentTest test;			
public WebDriver driver;


	
@BeforeClass			
public void BroweserLaunch() {			
extent = new ExtentReports(System.getProperty("D:\\Eclipseworkspace 1\\OnlineShoppingAmazon\\target") + "UserRegistration.html",true);		
}			
@Test(priority=1, enabled=true)			
public void Browser()				
{				
test = extent.startTest("Amazon Online Shopping");		
System.setProperty("webdriver.chrome.driver", "D:\\SELENIUM\\DRIVERS\\chromedriver.exe");		
driver=new ChromeDriver();		
driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);		
driver.manage().window().maximize();		
driver.navigate().to("https://www.amazon.in/");		
test.log(LogStatus.PASS, " Browser launching Successfully ");		
test.log(LogStatus.INFO," Browser Method Running Successsfully");		
}


@Test(priority=2, enabled=true)
public void userregistration() {
	test = extent.startTest("Verify that a new user can successfully register on the website\"");
	
	WebElement Account=driver.findElement(By.xpath("//span[normalize-space()='Account & Lists']"));
	Account.click();
	
	WebElement AcctCreate=driver.findElement(By.xpath("//a[@id='createAccountSubmit']"));
	AcctCreate.click();
	
	WebElement customer_name=driver.findElement(By.xpath("//input[@id='ap_customer_name']"));
	customer_name.click();
	customer_name.sendKeys("john");
	
	WebElement phone=driver.findElement(By.xpath("//input[@id='ap_phone_number']"));
	phone.click();
	phone.sendKeys("9080068247");	
	
	
	WebElement email=driver.findElement(By.xpath("//input[@id='ap_email']"));
	email.click();
	email.sendKeys("bjohnpaul0609@gmail.com");
	
	WebElement Password=driver.findElement(By.xpath("//input[@id='ap_password']"));
	Password.click();
	Password.sendKeys("John@123");
	
	WebElement signup=driver.findElement(By.xpath("//input[@id='continue']"));
	signup.click();	
	
	test.log(LogStatus.PASS, " Clicking on the 'Sign Up' button ");		
	test.log(LogStatus.INFO," Registration Successsfully Completed");	
	
}

@AfterMethod			
public void getResult(ITestResult result) throws IOException {			
	if (result.getStatus() == ITestResult.SUCCESS) {		
		String screenShotPath = ExtentReportClass.capture(driver, "screenShotName");	
		test.log(LogStatus.PASS, "Test case is passed " + result.getStatus());	
			
			
	} else {		
		if (result.getStatus() == ITestResult.FAILURE) {	
			String screenShotPath = ExtentReportClass.capture(driver, "screenShotName");
			test.log(LogStatus.FAIL, "Test case is failed at below location " + result.getThrowable());
			test.log(LogStatus.FAIL, "Snapshot below: " + test.addScreenCapture(screenShotPath));
		}	
	}}
	
    @AfterClass
   private void endreport() {
	extent.flush();
    extent.endTest(test);
    driver.quit();	
}}		
			
			





	


