package com.tests;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.automaion.pagefactory.Homepage;
import com.automaion.pagefactory.LoginPage;
import com.automation.resources.GenericLibrary;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RegisterPatientTest extends GenericLibrary{

	//public  WebDriver driver;
	public LoginPage loginPageObj;
	public Homepage homePageObj;
	public DesiredCapabilities caps;
	Properties prop;
	 ExtentReports extent;
	 ExtentTest logger;
	 File filename = null;
	public RemoteWebDriver driver;
	
	@BeforeTest
	public void configure() throws Exception {
		
		try(InputStream input =new FileInputStream(System.getProperty("user.dir")+"/config.properties")) {
		prop = new Properties();
		prop.load(input);
		
		}
       ExtentHtmlReporter reporter=new ExtentHtmlReporter("./target/RegisterPatientTestReport.html");
		
	    extent = new ExtentReports();
	    
	    extent.attachReporter(reporter);
	    
	   logger=extent.createTest("RegisterPatientTestReport");
		
	   caps=DesiredCapabilities.chrome();
		//driver= new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),caps);
		driver=new RemoteWebDriver(new URL(prop.getProperty("Grid.URL")),caps);
//		WebDriverManager.edgedriver().setup();
//	 	driver= new EdgeDriver();
	 
	 	loginPageObj =PageFactory.initElements(driver, LoginPage.class);
	 	homePageObj =PageFactory.initElements(driver, Homepage.class);
	 	
	 	driver.get(prop.getProperty("web.URL"));
	 	filename=takeScreenshot(driver, "MainPage");
	 	//logger.pass("Test Execution started and Browser is opened", MediaEntityBuilder.createScreenCaptureFromPath(filename.toString()).build());
	    logger.addScreenCaptureFromPath(filename.getAbsolutePath().toString());
	    logger.pass("Test Execution started and Browser is opened");
	 		
	}
	
	@Parameters({"Username","Password"})
	@Test(priority=0)
	public  void loginToAppTest(String username,String password) throws Exception
	{
		int count=0;
		boolean status =true; 	
		
		loginPageObj.usernameField.sendKeys(username);
		loginPageObj.passwordtext.sendKeys(password);
		filename=takeScreenshot(driver, "LOGINPAGE");
	//	logger.pass("user provided user name and password ", MediaEntityBuilder.createScreenCaptureFromPath(filename.toString()).build());
		 logger.addScreenCaptureFromPath(filename.getAbsolutePath().toString());
		loginPageObj.inpatientWard.click();
		
		// DEBUG Purpose 
		//System.out.println("size is "+driver.findElements(By.xpath("//input[@id='loginButton' and @type='submit']")).size());
		while(status) {
		
			driver.findElement(By.xpath("//input[@id='loginButton' and @type='submit']")).submit();
			count++;
		java.util.List<WebElement> list =homePageObj.homePageLabel;
		// DEBUG Purpose 
		//System.out.println("list size is "+list.size());
		if(list.size()>0 ) {
			status=false;
		}
		if(count==5) {break;}
		}
        
		filename=takeScreenshot(driver, "HOMEPAGE");
	//	logger.pass("user logged in successsfully", MediaEntityBuilder.createScreenCaptureFromPath(filename.toString()).build());
		 logger.addScreenCaptureFromPath(filename.getAbsolutePath().toString());
		 logger.pass("user logged in successsfully");
	}
	
	
	@Parameters({"pageName","patientName"})
	@Test(priority=1)
	public  void findPatientRecordTest(String pageName,String patientName) throws Exception
	{
		
		java.util.List<WebElement> list =homePageObj.homePageLabel;
	
		 homePageObj.findPatientRecordButton.click();
          homePageObj.patientSearchText.clear();
          homePageObj.patientSearchText.sendKeys(patientName);
          logger.pass("user entered "+ patientName +" as patient name");
          Thread.sleep(5000);
          if(homePageObj.patientRecordsResults.size()>0) {
        	  System.out.println("patient Name is "+homePageObj.patientName.getText());
        	  Assert.assertEquals(homePageObj.patientName.getText(),patientName);
        	  filename=takeScreenshot(driver, "PatientNameDisplayInUI");
        	
        	//  logger.pass ("user able to find the patient name :  "+ patientName +" successfully",MediaEntityBuilder.createScreenCaptureFromPath(filename.toString()).build());
        	  logger.addScreenCaptureFromPath(filename.getAbsolutePath().toString());
        	  logger.pass ("user able to find the patient name :  "+ patientName +" successfully");
          }
		 	
	}
	
	@AfterTest
	public void tearDown() throws Exception
	{
		//loginPageObj.logoutbtn.click();
		
		
		
		  filename=takeScreenshot(driver, "LogoutPage");
       //	  logger.pass("user logged out and  closed the browser",MediaEntityBuilder.createScreenCaptureFromPath(filename.toString()).build());
       	 logger.addScreenCaptureFromPath(filename.getAbsolutePath().toString());
       	logger.pass("user logged out and  closed the browser");
    	  extent.flush();
		driver.quit();
	}
}
