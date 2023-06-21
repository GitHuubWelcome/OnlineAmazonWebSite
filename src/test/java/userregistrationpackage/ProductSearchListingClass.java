package userregistrationpackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import extentreportpackage.ExtentReportClass;

public class ProductSearchListingClass {
	public ExtentReports extent;			
	public ExtentTest test;			
	public WebDriver driver;
	
	List<WebElement> mobilelist;
	List<WebElement> mobileprice;
	
	@BeforeClass			
	public void BroweserLaunch() {			
	extent = new ExtentReports(System.getProperty("D:\\Eclipseworkspace 1\\OnlineShoppingAmazon\\target") + "ProductSearchListing.html",true);		
	}	

	@Test(priority=3, enabled=true)			
	public void ProductSearch ()				
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
	
	@Test(priority=4, enabled=true)			
	public void Productlist ()				
	{				
	test = extent.startTest("Product Search and Listing");	
	
	List<WebElement> mobilelist =driver.findElements(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"));
	List<WebElement> mobileprice =driver.findElements(By.xpath("//span[@class='a-price-whole']"));
	
	System.out.println(mobilelist.size());
	System.out.println(mobileprice.size());
	
    for (WebElement mobileElement : mobilelist) 
    {
    	   	
    	 for(WebElement mobilePrice : mobileprice) {  
    		 
    		// Perform actions or extract information for each mobile device element
        	 String deviceName = mobileElement.getText();
        	 System.out.println("Device Name: " + deviceName);
        	 assert !deviceName.isEmpty();    
    
         String devicePrice = mobilePrice.getText();      
         System.out.println("Device Price: " + devicePrice);       
   	     assert !devicePrice.isEmpty();   	
   	     break;    
    	 }}       
     test.log(LogStatus.PASS, " Product Listing Successfully ");		
     test.log(LogStatus.INFO," All Product Listed Successsfully");
    	
	}
	
	@Test(priority=5, enabled=true)			
	public void AllProductDetails ()				
	{				
	test = extent.startTest("Product Details ");	
	List<WebElement> mobilelist =driver.findElements(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"));
	List<WebElement> mobileprice =driver.findElements(By.xpath("//span[@class='a-price-whole']"));
	
	
		for(int i = 0; i<=1; i++) 
		{
			WebElement product=mobilelist.get(i);
			product.click();	
			
		}
	/*
		ArrayList<String> Tab=new ArrayList<String>(driver.getWindowHandles());	
		String Childwindow1=driver.getCurrentUrl();	
		sysout
		System.out.println("The Page URL Link:" + Childwindow1);
		

    test.log(LogStatus.PASS, " Product Listing Successfully ");		
    test.log(LogStatus.INFO," All Product Listed Successsfully");
    */
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

	


