package userregistrationpackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import extentreportpackage.ExtentReportClass;

public class ProductDetailsClass {
	
	public ExtentReports extent;			
	public ExtentTest test;			
	public WebDriver driver;
	
	List<WebElement> mobilelist=null;
	
	
	@BeforeClass			
	public void Extentreport() {			
	extent = new ExtentReports(System.getProperty("D:\\Eclipseworkspace 1\\OnlineShoppingAmazon\\target") + "ProductDetails.html",true);		
	}	
	
	@Test(priority=5, enabled=true)			
	public void LaunchBrowser ()				
	{				
	test = extent.startTest("Product Search and Listing");		
	System.setProperty("webdriver.chrome.driver", "D:\\SELENIUM\\DRIVERS\\chromedriver.exe");		
	driver=new ChromeDriver();		
	driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);		
	driver.manage().window().maximize();		
	driver.navigate().to("https://www.amazon.in/");	
	
	driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).click();
	driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("Mobile");
	
	driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();	
		
	test.log(LogStatus.PASS, " Product Listing Successfully ");		
	test.log(LogStatus.INFO," All Product Listed Successsfully");		
	}

	
	@Test(priority=6, enabled=true)			
	public void ProductDetailspage ()				
	{				
	test = extent.startTest("ProductDetailspage ");
	List<WebElement> mobilelist =driver.findElements(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"));
	for(int i = 0; i<=1; i++) 
	{
		WebElement product=mobilelist.get(i);
		product.click();
	}
	test.log(LogStatus.PASS, " Product Listing Successfully ");		
	test.log(LogStatus.INFO," All Product Listed Successsfully");	
	}
	
	@Test(priority=7, enabled=true)			
	public void ValidateProduct () {
		
		test = extent.startTest("ValidateProduct Details ");
		ArrayList<String> Tab=new ArrayList<String>(driver.getWindowHandles());	
		
		driver.switchTo().window(Tab.get(1));
		
		String Childwindow=driver.getCurrentUrl();	
		System.out.println(Childwindow);	
		
		
	// Step 3: Verify product details
    WebElement productTitleElement = driver.findElement(By.xpath("//span[@id='productTitle']")); 
    Assert.assertEquals(productTitleElement.getText(), "Redmi Note 12 5G Mystique Blue 4GB RAM 128GB ROM | 1st Phone with 120Hz Super AMOLED and Snapdragon® 4 Gen 1 | 48MP AI Triple Camera","Product title mismatch.");

    WebElement productImageElement = driver.findElement(By.xpath("//img[@id='landingImage']")); 
    Assert.assertTrue(productImageElement.isDisplayed(), "Product image not displayed.");

    WebElement productPriceElement = driver.findElement(By.xpath("//span[@class='a-offscreen']")); 
    Assert.assertTrue(productPriceElement.isDisplayed(), "Product price not displayed.");

    WebElement productReviewsElement = driver.findElement(By.xpath("//span[@id='acrCustomerReviewText']")); 
    Assert.assertTrue(productReviewsElement.isDisplayed(), "Product reviews not displayed.");
    
    test.log(LogStatus.PASS, " Product Listing Successfully ");		
	test.log(LogStatus.INFO," All Product Listed Successsfully");	
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



