package com.DoctorPomClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DocHomePage {
	
	WebDriver driver;
	@FindBy(xpath = "//h1[text()='Doctor | Dashboard']")
	private WebElement dashboard;
	
	@FindBy(xpath = "(//a[@href='edit-profile.php'])[2]")
	private WebElement updateProfile;
	
	@FindBy(xpath = "(//a[@href='appointment-history.php'])[2]")
	private WebElement Myappointments;
	
	@FindBy(xpath = "//span[text() = ' Search ']")
	private WebElement search;
	
	@FindBy(xpath = "//span[text()=' Patients ']")
	private WebElement patients;
	
	@FindBy(xpath = "//span[text()=' Add Patient']")
	private WebElement Addpatient;
	
	@FindBy(xpath = "//span[text()=' Manage Patient ']")
	private WebElement ManagePatient;
	
	@FindBy(id = "submit")
	private WebElement searchBtn;
	
	@FindBy(xpath = "//table[@id='sample-table-1']//tr[td[contains(text(), '+eUtil.getDataFromExcel('hms_data', 18, 0)+')]]'")
	private By patientListTable;

	public DocHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getUpdateProfile() {
		return updateProfile;
	}

	public WebElement getMyappointments() {
		return Myappointments;
	}

	public WebElement getSearch() {
		return search;
	}

	public WebElement getPatients() {
		return patients;
	}

	public WebElement getAddpatient() {
		return Addpatient;
	}

	public WebElement getManagePatient() {
		return ManagePatient;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public By getPatientListTable() {
		return patientListTable;
	}

	public WebElement getDashboard() {
		return dashboard;
	}
	
	
	
	


	
	
	
	
	
	
}
