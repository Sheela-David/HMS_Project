package com.DoctorPomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class DocUpdateProfile {
	
	WebDriver driver;
	
	@FindBy(name = "Doctorspecialization")
	private WebElement splzndropdown;
	
	@FindBy(name="docname")
	private WebElement docName;
	
	@FindBy(name="clinicaddress")
	private WebElement address;
	
	@FindBy(name="doccontact")
	private WebElement contactNum;
	
	@FindBy(name = "docemail")
	private WebElement email;
	
	
	public DocUpdateProfile(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public WebElement getSplzndropdown() {
		return splzndropdown;
	}

	public WebElement getDocName() {
		return docName;
	}

	public WebElement getAddress() {
		return address;
	}

	public WebElement getContactNum() {
		return contactNum;
	}

	public WebElement getEmail() {
		return email;
	}

	public void selectDropdown(String value) {
		Select sel = new Select(splzndropdown);
		sel.selectByValue(value);
	}

}
