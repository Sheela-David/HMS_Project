package com.PatientPOMClasses;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.generic.fileUtility.ExcelUtility;
import com.generic.fileUtility.JavaUtility;

public class BookAnAppointment {
	
	WebDriver driver;
	ExcelUtility eUtil = new ExcelUtility();
	JavaUtility jUtil = new JavaUtility();
	
	@FindBy(name = "Doctorspecialization")
	private WebElement dropdown;
	
	@FindBy(name="doctor")
	private WebElement selectingDoctor;
	
	@FindBy(name = "appdate")
	private WebElement date;
	
	@FindBy(id = "timepicker1")
	private WebElement time;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement submitBtn;

	public BookAnAppointment(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getDropdown() {
		return dropdown;
	}

	public WebElement getSelectingDoctor() {
		return selectingDoctor;
	}

	public WebElement getDate() {
		return date;
	}

	public WebElement getTime() {
		return time;
	}
	
	public WebElement getSubmitBtn() {
		return submitBtn;
	}

	public void doctorSpecialization() throws Throwable, Throwable {
		Select sel = new Select(dropdown);
		sel.selectByValue(eUtil.getDataFromExcel("BookAnAppointment", 1, 0));
	}
	
	public void selectingDoctor() throws EncryptedDocumentException, IOException {
		Select sel = new Select(selectingDoctor);
		System.out.println(eUtil.getDataFromExcel("BookAnAppointment", 1, 1));
		sel.selectByVisibleText(eUtil.getDataFromExcel("BookAnAppointment", 1, 1));
	}
	
	
	
	
	
	
	
}
