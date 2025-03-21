package com.DoctorPomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ViewAppointmentHistory {
	
	WebDriver driver;
	
	@FindBy(xpath = "//a[text()='Cancel']")
	private WebElement appCancel;

	public WebElement getAppCancel() {
		return appCancel;
	}
	
	

}
