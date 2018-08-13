package com.lindacare.automation;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestCase1 {

	private WebDriver driver;
	private String basePath = System.getProperty("user.dir");

	@Test
	//below function adds 16 as age ,click on calculate and verify the error message
	public void sampleTest1() throws Exception {

		driver.findElement(By.id("age")).sendKeys("16");
		
		//call to screenshot function
		
		takeSnapShot(driver,"D:\\Users\\shokaul\\Desktop\\linda\\screenshots\\TC1age.png");
		
		//click on calculate
		driver.findElement(By.id("btn-calculate")).click();
		
		//this is screenshotfunction created by me , it takes input as webdriver nd location where user wants to have screenshots
		takeSnapShot(driver,"D:\\Users\\shokaul\\Desktop\\linda\\screenshots\\TC1calculate.png");
		
		//validate error message
		
		String Strerror=driver.findElement(By.xpath("//*[@id=\"calculator-error-message\"]/span")).getText();
		
		Assert.assertEquals(Strerror, "Only adults (aged 18 and older) are eligible to open a term deposit account.");
		
		System.out.println("Value of error message is :="+Strerror);
		
		takeSnapShot(driver,"D:\\Users\\shokaul\\Desktop\\linda\\screenshots\\TC1error.png");
	}

	@Test
	public void sampleTest2() throws Exception {
		driver.findElement(By.id("age")).clear();
		driver.findElement(By.id("age")).sendKeys("20");
		takeSnapShot(driver,"D:\\Users\\shokaul\\Desktop\\linda\\screenshots\\TC2age1.png");
		
		//add amount
		
		driver.findElement(By.id("amount")).sendKeys("3000");
		takeSnapShot(driver,"D:\\Users\\shokaul\\Desktop\\linda\\screenshots\\TC2amount1.png");
		
		
		//click on calculate
		driver.findElement(By.id("btn-calculate")).click();
		
		//call to screenshot function
		takeSnapShot(driver,"D:\\Users\\shokaul\\Desktop\\linda\\screenshots\\TC2calculate1.png");
		
		//validate valid message
		
		String StrValid=driver.findElement(By.id("calculator-output-message")).getText();
		
		Assert.assertEquals(StrValid, "The calculated interest rate is: 1.3%");
		
		System.out.println("Value of error message is :="+StrValid);
		
		takeSnapShot(driver,"D:\\Users\\shokaul\\Desktop\\linda\\screenshots\\TC2valid.png");
	}

	
	
	
	@Test
	public void sampleTest3() throws Exception {
		driver.findElement(By.id("age")).clear();
		driver.findElement(By.id("age")).sendKeys("21");
		takeSnapShot(driver,"D:\\Users\\shokaul\\Desktop\\linda\\screenshots\\TC3age3.png");
		
		//add amount less than 100
		driver.findElement(By.id("amount")).clear();
		driver.findElement(By.id("amount")).sendKeys("98");
		
		//call to screenshot function
		takeSnapShot(driver,"D:\\Users\\shokaul\\Desktop\\linda\\screenshots\\TC3amount2.png");
		//click on calculate
		driver.findElement(By.id("btn-calculate")).click();

		takeSnapShot(driver,"D:\\Users\\shokaul\\Desktop\\linda\\screenshots\\TC3calculate2.png");	
		
		//validate error message
		
		String StrErrorAmount=driver.findElement(By.xpath("//*[@id=\"calculator-error-message\"]/span")).getText();
		
		
		Assert.assertEquals(StrErrorAmount, "The deposit amount must be equal or greater than 100.");
		
		System.out.println("Value of error message is :="+StrErrorAmount);
		
		takeSnapShot(driver,"D:\\Users\\shokaul\\Desktop\\linda\\screenshots\\TC3erroramount.png");
	}

	
	
	
	
	
	
	
	@BeforeClass
	public void beforeClass() {
		String phantomJSPath = basePath + "/lib/phantomjs";

		if (SystemUtils.IS_OS_WINDOWS) {
			phantomJSPath += ".exe";
		}

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setJavascriptEnabled(true);
		caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, phantomJSPath);
		driver = new PhantomJSDriver(caps);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String pageURL = "file:///" + basePath + "/InterestCalculator.html";

		driver.get(pageURL);
	
		System.out.println("title is "+driver.getTitle());
		
		
	}

	
	public static void takeSnapShot(WebDriver webdriver,String picname) throws Exception{

        //Convert web driver object to TakeScreenshot

        TakesScreenshot scrShot =((TakesScreenshot)webdriver);

        //Call getScreenshotAs method to create image file

                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

            //Move image file to new destination

                File DestFile=new File(picname);

                //Copy file at destination

                FileUtils.copyFile(SrcFile, DestFile);

    }
	
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
