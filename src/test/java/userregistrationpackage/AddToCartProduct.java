package userregistrationpackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class AddToCartProduct {
	
	public ExtentReports extent;			
	public ExtentTest test;			
	public WebDriver driver;
	
	List<WebElement> mobilelist=null;
	
	
	@BeforeClass			
	public void Extentreport() {			
	extent = new ExtentReports(System.getProperty("D:\\Eclipseworkspace 1\\OnlineShoppingAmazon\\target") + "AddToCart.html",true);		
	}	
	
	@Test(priority=8, enabled=true)			
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
		
		@Test(priority=9, enabled=true)			
		public void AddToCart () throws InterruptedException   
		{			
			test = extent.startTest("AddToCartProduct ");
			List<WebElement> mobilelist =driver.findElements(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"));
			
			mobilelist.get(0).click();		
			
			ArrayList<String> Tab=new ArrayList<String>(driver.getWindowHandles());	
			driver.switchTo().window(Tab.get(1));
			Thread.sleep(5000);
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,350)", "");		
				
			        // Step 1: Click on the "Add to Cart" button
			        WebElement addToCartButton = driver.findElement(By.xpath("//input[@id='add-to-cart-button']")); 
			        addToCartButton.click();
			        
			        driver.findElement(By.xpath("//span[@id='attach-sidesheet-view-cart-button-announce']")).click();
			        
			        Thread.sleep(5000);
			        

			        // Step 2: Validate that the product is successfully added to the cart
			        WebElement cartCountElement = driver.findElement(By.xpath("//span[@id='sc-subtotal-label-activecart']")); // Update XPath to match the cart count element
			        Assert.assertEquals(cartCountElement.getText(), "Subtotal (2 items):", "Product not added to the cart.");
			        
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
		   public void endreport() {
			extent.flush();
		    extent.endTest(test);
		  
		    
			
		}}	

			

	


