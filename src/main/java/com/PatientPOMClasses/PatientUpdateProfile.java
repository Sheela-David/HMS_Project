package com.PatientPOMClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PatientUpdateProfile {
	
	WebDriver driver;
	
	@FindBy(name="fname")
	private WebElement userName;
	
	@FindBy(name="address")
	private WebElement address;
	
	@FindBy(name="city")
	private WebElement city;
	
	@FindBy(name="uemail")
	private WebElement email;
	
	@FindBy(name="submit")
	private WebElement submit;
	
	public PatientUpdateProfile(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getUserName() {
		return userName;
	}

	public WebElement getAddress() {
		return address;
	}

	public WebElement getCity() {
		return city;
	}

	public WebElement getEmail() {
		return email;
	}

	public WebElement getSubmit() {
		return submit;
	}
	
	
	
}
