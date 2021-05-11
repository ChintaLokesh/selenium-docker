package com.automaion.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	
	
	
	@FindBy(id="username")
	public WebElement usernameField;
	
	@FindBy(id="password")
	public WebElement passwordtext;
	
	@FindBy(id="loginButton")
	public WebElement loginButton;
	
	@FindBy(id="Inpatient Ward")
	public WebElement inpatientWard;
	
	@FindBy(xpath="//*[@href='/openmrs/appui/header/logout.action?successUrl=openmrs']")
	public WebElement logoutbtn;
	
}
