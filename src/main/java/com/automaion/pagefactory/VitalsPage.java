package com.automaion.pagefactory;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VitalsPage {
	
	@FindBy(xpath="//*[@id='vitals']")
	public List<WebElement> vitalsSectionLabel;

	
	@FindBy(xpath="//*[@id='vitals']/fieldset")
	public List<WebElement> vitalsSectionListCount;
	
	
	@FindBy(xpath="//*[@id='vitals']/fieldset//span[@id='height']/span[@class='value']")
	public List<WebElement> heightValue;
	
	
	@FindBy(xpath="//*[@id='vitals']/fieldset//span[@id='height']/span[@class='append-to-value']")
	public List<WebElement> heightMeasurementTypeValue;
	
	@FindBy(xpath="//*[@id='vitals']/fieldset//span[@id='weight']/span[@class='value']")
	public List<WebElement> weightValue;
	
	
	@FindBy(xpath="//*[@id='vitals']/fieldset//span[@id='weight']/span[@class='append-to-value']")
	public List<WebElement> weightMeasurementTypeValue;
	
	
	@FindBy(xpath="//*[@id='vitals']/fieldset//span[@id='temperature']/span[1]")
	public List<WebElement> tempValue;
	
	
	@FindBy(xpath="//*[@id='vitals']/fieldset//span[@id='temperature']/span[@class='append-to-value']")
	public List<WebElement> tempMeasurementTypeValue;
	
	@FindBy(xpath="//*[@id='vitals']/fieldset//span[@id='heart_rate']/span[1]")
	public List<WebElement> heart_rate_Value;
	
	
	@FindBy(xpath="//*[@id='vitals']/fieldset//span[@id='heart_rate']/span[@class='append-to-value']")
	public List<WebElement> heart_rate_TypeValue;
	
	@FindBy(xpath="//*[@id='vitals']/fieldset//span[@id='respiratory_rate']/span[1]")
	public List<WebElement> respiratory_rate_Value;
	
	
	@FindBy(xpath="//*[@id='vitals']/fieldset//span[@id='respiratory_rate']/span[@class='append-to-value']")
	public List<WebElement> respiratory_rate_Type_Value;
	
	
	
	@FindBy(xpath="//*[@id='vitals']/fieldset//span[@id='bp_systolic']/span[1]")
	public List<WebElement> bp_systolic_Value;
	
	
	@FindBy(xpath="//*[@id='vitals']/fieldset//span[@id='bp_diastolic']/span[1]")
	public List<WebElement> bp_diastolic_Value;
	
	
	@FindBy(xpath="//*[@id='vitals']/fieldset//span[@id='o2_sat']/span[1]")
	public List<WebElement> o2_sat_Value;
	
	
	@FindBy(xpath="//*[@id='vitals']/fieldset//span[@id='o2_sat']/span[@class='append-to-value']")
	public List<WebElement> o2_sat_Type_Value;
	
	
	@FindBy(xpath="//*[@id='vitals']/fieldset//span[@id='calculated-bmi']")
	public List<WebElement> bmi_Value;
}
