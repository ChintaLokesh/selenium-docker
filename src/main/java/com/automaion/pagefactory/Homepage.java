package com.automaion.pagefactory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage {

	
	@FindBy(xpath="//h4")
	public List<WebElement> homePageLabel;
	
	@FindBy(id="coreapps-activeVisitsHomepageLink-coreapps-activeVisitsHomepageLink-extension")
	public WebElement findPatientRecordButton;
	
	@FindBy(id="patient-search")
	public WebElement patientSearchText;
	
	@FindBy(xpath="//tbody/tr")
	public List<WebElement> patientRecordsResults;
	
	@FindBy(xpath="//tbody/tr/td[2]")
	public WebElement patientName;
	
	@FindBy(xpath="//*[@role='alert']//tr")
	public List<WebElement> patientRecordTable;
	
	
	@FindBy(xpath="//*[@role='alert']//tr")
	public List<WebElement> patientRecord;
	
}
