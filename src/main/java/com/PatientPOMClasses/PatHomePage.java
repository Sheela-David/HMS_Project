package com.PatientPOMClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PatHomePage {
	
	WebDriver driver;
	@FindBy(xpath = "(//a[@href=\"edit-profile.php\"])[2]")
	private WebElement updateProfile;
	
	@FindBy(xpath = "(//a[@href=\"appointment-history.php\"])[2]")
	private WebElement appointmentHist;
	
	@FindBy(xpath = "(//a[@href=\"book-appointment.php\"])[2]")
	private WebElement bookAppointment;

	public PatHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getUpdateProfile() {
		return updateProfile;
	}

	public WebElement getAppointmentHist() {
		return appointmentHist;
	}

	public WebElement getBookAppointment() {
		return bookAppointment;
	}
	
	
	
	

}
