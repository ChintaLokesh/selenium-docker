package com.automation.resources;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericLibrary {

	public void maximizeBrowser(WebDriver driver) {
		driver.manage().window().maximize();
	}
	
	public  File takeScreenshot(WebDriver driver,String fileName) throws Exception {
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	 	File dst= new File(System.getProperty("user.dir")+"/target/"+fileName+".jpg");
	 	FileUtils.copyFile(src,dst);
	 	return dst;
	}
	
	public void waitUntilPageIsLoaded(WebDriver driver,int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}
	
	public void waitUntilElementIsDisplayed(WebDriver driver,By by) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}
	
	
	public void clickByUsingActionsClass(WebDriver driver,WebElement wb) {
		 Actions act = new Actions(driver);
		 act.moveToElement(wb).click().build().perform();
	}
}
