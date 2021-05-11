package com.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
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
import com.automaion.pagefactory.VitalsPage;
import com.automation.resources.GenericLibrary;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FindPatientRecordTest extends GenericLibrary {
	
	public  WebDriver driver;
	public LoginPage loginPageObj;
	public Homepage homePageObj;
	public VitalsPage vitalsPageObj;
	public DesiredCapabilities caps;
	//public RemoteWebDriver driver;
	ExtentReports extent;
	 ExtentTest logger;
	Properties prop;
	File filename=null;
	
	@BeforeTest
	public void configure() throws Exception {
		
		
		try(InputStream input =new FileInputStream(System.getProperty("user.dir")+"/config.properties")) {
			prop = new Properties();
			prop.load(input);
			
			}
		
		  ExtentHtmlReporter reporter=new ExtentHtmlReporter("./target/FindPatientVitalsTestReport.html");
			
		    extent = new ExtentReports();
		    
		    extent.attachReporter(reporter);
		    
		   logger=extent.createTest("FindPatientVitalsTestReport");
		
		caps=DesiredCapabilities.chrome();
//		driver= new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),caps);
		driver=new RemoteWebDriver(new URL(prop.getProperty("Grid.URL")),caps);
		
//		WebDriverManager.edgedriver().setup();
//	 	driver= new EdgeDriver();
	
	 	loginPageObj =PageFactory.initElements(driver, LoginPage.class);
	 	homePageObj =PageFactory.initElements(driver, Homepage.class);
	 	vitalsPageObj =PageFactory.initElements(driver, VitalsPage.class);
	
	 	driver.get(prop.getProperty("web.URL"));
	 
	 	filename=takeScreenshot(driver, "MainPage");
	 	 logger.addScreenCaptureFromPath(filename.getAbsolutePath().toString());
	 	logger.pass("Test Execution started and Browser is opened");
	 	//logger.pass("Test Execution started and Browser is opened", MediaEntityBuilder.createScreenCaptureFromPath(filename.toString()).build());	
	}

	@Parameters({"Username","Password"})
	@Test(priority=0)
	public  void loginToAppTest(String username,String password) throws Exception
	{
		int count=0;
		boolean status =true; 
		filename=takeScreenshot(driver, "LOGINPAGE1");
		logger.addScreenCaptureFromPath(filename.getAbsolutePath().toString());
	//	logger.pass("Navigated to LOGIN PAGE", MediaEntityBuilder.createScreenCaptureFromPath(filename.toString()).build());
		loginPageObj.usernameField.sendKeys(username);
		loginPageObj.passwordtext.sendKeys(password);
		loginPageObj.inpatientWard.click();
		
		
		while(status) {
		
			driver.findElement(By.xpath("//input[@id='loginButton' and @type='submit']")).submit();
			count++;
		java.util.List<WebElement> list =homePageObj.homePageLabel;
		
		if(list.size()>0 ) {
			status=false;
		}
		if(count==5) {break;}
		}
        
		filename=takeScreenshot(driver, "MAINPAGE1");
		 logger.addScreenCaptureFromPath(filename.getAbsolutePath().toString());
		 logger.pass("User logged into Home Page Successfully");
		//logger.pass("User logged into Home Page Successfully", MediaEntityBuilder.createScreenCaptureFromPath(filename.toString()).build()); 	
	}
	
	@Parameters({"pageName","patientName"})
	@Test(priority=1)
	public void findPatientRecordInLabModule(String pageName,String patientName) throws Exception {
		
		
		java.util.List<WebElement> list =homePageObj.homePageLabel;
		
		takeScreenshot(driver, "PATIENT_RECORD_PAGE");
          homePageObj.findPatientRecordButton.click();
          homePageObj.patientSearchText.clear();
          homePageObj.patientSearchText.sendKeys(patientName);
          filename=takeScreenshot(driver, "SearchPage");
          logger.addScreenCaptureFromPath(filename.getAbsolutePath().toString());
          logger.pass("User searched with the patient name :"+ patientName);
  		//logger.pass("User searched with the patient name :"+ patientName , MediaEntityBuilder.createScreenCaptureFromPath(filename.toString()).build()); 	
          
          Thread.sleep(10000);
          
          if (homePageObj.patientRecordTable.size() >0) {
        	  
        	  System.out.println(driver.findElement(By.xpath("//*[@role='alert']//tr//td[2]")).getText());
        	  Assert.assertEquals(patientName,driver.findElement(By.xpath("//*[@role='alert']//tr//td[2]")).getText());
        	  
        	  
        	  
        	  
          }
          filename=takeScreenshot(driver, "Patient_Record_In_Lab_Module");
          logger.addScreenCaptureFromPath(filename.getAbsolutePath().toString());
          logger.pass("User find the  patient details with patient name  :"+ patientName );
          //logger.pass("User find the  patient details with patient name  :"+ patientName , MediaEntityBuilder.createScreenCaptureFromPath(filename.toString()).build()); 
	}
	
	@Parameters({"pageName","patientName"})
	@Test(priority=1)
	public void findPatientVitalsRecordInLabModule(String pageName,String patientName) throws Exception {
		
		Assert.assertEquals(patientName, driver.findElement(By.xpath("//*[text()='"+patientName+"']")).getText());
		clickByUsingActionsClass(driver,driver.findElement(By.xpath("//*[text()='"+patientName+"']")));
		Thread.sleep(10000);
		Assert.assertTrue(vitalsPageObj.vitalsSectionLabel.size() >0);
		Assert.assertTrue(vitalsPageObj.vitalsSectionListCount.size() >0);
		
		
		System.out.println("vitals section Attributes count is "+vitalsPageObj.vitalsSectionListCount.size());
		
		System.out.println(" Height value is "+ vitalsPageObj.heightValue.get(0).getText() +" "+ vitalsPageObj.heightMeasurementTypeValue.get(0).getText()); 
		System.out.println(" Weight value is "+ vitalsPageObj.weightValue.get(0).getText() +" "+ vitalsPageObj.weightMeasurementTypeValue.get(0).getText());
		System.out.println(" Temperature value is "+ vitalsPageObj.tempValue.get(0).getText() +" "+ vitalsPageObj.tempMeasurementTypeValue.get(0).getText());
		System.out.println(" Heart Rate  value is "+ vitalsPageObj.heart_rate_Value.get(0).getText() +" "+ vitalsPageObj.heart_rate_TypeValue.get(0).getText());
		System.out.println(" Respiratory_Rate value is "+ vitalsPageObj.respiratory_rate_Value.get(0).getText() +" "+ vitalsPageObj.respiratory_rate_Type_Value.get(0).getText());
		System.out.println(" BP value is "+ vitalsPageObj.bp_systolic_Value.get(0).getText() +" / "+ vitalsPageObj.bp_diastolic_Value.get(0).getText());
		System.out.println(" Oxygen Measurement value is "+ vitalsPageObj.o2_sat_Value.get(0).getText() +" "+ vitalsPageObj.o2_sat_Type_Value.get(0).getText());
		System.out.println(" BMI value is "+ vitalsPageObj.bmi_Value.get(0).getText() );
		
		logger.pass("vitals Details of a patient "+ patientName +": Height value is "+ vitalsPageObj.heightValue.get(0).getText() +" "+ vitalsPageObj.heightMeasurementTypeValue.get(0).getText()); 
		logger.pass("vitals Details of a patient "+ patientName +":Weight value is "+ vitalsPageObj.weightValue.get(0).getText() +" "+ vitalsPageObj.weightMeasurementTypeValue.get(0).getText());
		logger.pass("vitals Details of a patient "+ patientName + ":Temperature value is "+ vitalsPageObj.tempValue.get(0).getText() +" "+ vitalsPageObj.tempMeasurementTypeValue.get(0).getText());
		logger.pass("vitals Details of a patient "+ patientName + ":Heart Rate  value is "+ vitalsPageObj.heart_rate_Value.get(0).getText() +" "+ vitalsPageObj.heart_rate_TypeValue.get(0).getText());
		logger.pass("vitals Details of a patient "+ patientName +":Respiratory_Rate value is "+ vitalsPageObj.respiratory_rate_Value.get(0).getText() +" "+ vitalsPageObj.respiratory_rate_Type_Value.get(0).getText());
		logger.pass("vitals Details of a patient "+ patientName +":BP value is "+ vitalsPageObj.bp_systolic_Value.get(0).getText() +" / "+ vitalsPageObj.bp_diastolic_Value.get(0).getText());
		logger.pass("vitals Details of a patient "+ patientName + ":Oxygen Measurement value is "+ vitalsPageObj.o2_sat_Value.get(0).getText() +" "+ vitalsPageObj.o2_sat_Type_Value.get(0).getText());
		logger.pass("vitals Details of a patient "+ patientName +":BMI value is "+ vitalsPageObj.bmi_Value.get(0).getText() );
		
		
		filename=takeScreenshot(driver, "Patient_Vitals_In_LabModule_page");
		logger.addScreenCaptureFromPath(filename.getAbsolutePath().toString());
		 logger.pass("User can see the   patient vital  details with patient name  :"+ patientName);
		//logger.pass("User can see the   patient vital  details with patient name  :"+ patientName , MediaEntityBuilder.createScreenCaptureFromPath(filename.toString()).build()); 
          
	}
	
	@AfterTest
	public void tearDown() throws Exception
	{
		
		
		//loginPageObj.logoutbtn.click();
		filename=takeScreenshot(driver, "Logout_Page");
		logger.addScreenCaptureFromPath(filename.getAbsolutePath().toString());
		 logger.pass("User logged out successfully");
		//logger.pass("User logged out successfully" , MediaEntityBuilder.createScreenCaptureFromPath(filename.toString()).build()); 
		extent.flush();
		driver.quit();
	}
}
