package com.DoctorPomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddPatient {
	
	WebDriver driver;
	
	@FindBy(xpath = "//input[@name='patname']")
	private WebElement patName;
	
	@FindBy(xpath = "//input[@name='patcontact']")
	private WebElement patContactNum;
	
	@FindBy(xpath = "//input[@id='patemail']")
	private WebElement patEmail;
	
	@FindBy(xpath = "//label[@for='rg-female']")
	private WebElement female;
	
	@FindBy(xpath = "//label[@for='rg-male']")
	private WebElement male;
	
	@FindBy(xpath = "//textarea[@name='pataddress']")
	private WebElement patAddress;
	
	@FindBy(xpath = "//input[@name='patage']")
	private WebElement patAge;
	
	@FindBy(xpath = "//textarea[@name='medhis']")
	private WebElement medHistory;
	
	@FindBy(id = "submit")
	private WebElement addButton;
	
	

	public AddPatient(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getPatName() {
		return patName;
	}

	public WebElement getPatContactNum() {
		return patContactNum;
	}

	public WebElement getPatEmail() {
		return patEmail;
	}

	public WebElement getFemale() {
		return female;
	}

	public WebElement getMale() {
		return male;
	}

	public WebElement getPatAddress() {
		return patAddress;
	}

	public WebElement getPatAge() {
		return patAge;
	}

	public WebElement getMedHistory() {
		return medHistory;
	}

	public WebElement getAddButton() {
		return addButton;
	}
	
	

}
