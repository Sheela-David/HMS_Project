package com.DoctorPomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchingPatient {
	
	WebDriver driver;
	
	@FindBy(xpath = "//span[text() = ' Search ']")
	private WebElement searchBtn;
	
	@FindBy(id = "searchdata")
	private WebElement searchTxtField;
	
	@FindBy(id = "submit")
	private WebElement submitBtn;

	public SearchingPatient(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebElement getSearchTxtField() {
		return searchTxtField;
	}

	public WebElement getSubmitBtn() {
		return submitBtn;
	}
	
	
	
	

}
