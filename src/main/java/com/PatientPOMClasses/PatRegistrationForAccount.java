package com.PatientPOMClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PatRegistrationForAccount {
	
	WebDriver driver;
	@FindBy(name="full_name")
	private WebElement fullName;
	
	@FindBy(name="address")
	private WebElement address;
	
	@FindBy(name="city")
	private WebElement city;
	
	@FindBy(xpath="//label[@for='rg-female']")
	private WebElement genderFemale;
	
	@FindBy(xpath="//label[@for='rg-male']")
	private WebElement genderMale;
	
	@FindBy(id="email")
	private WebElement email;
	
	@FindBy(id="password")
	private WebElement password;
	
	@FindBy(id="password_again")
	private WebElement confirmPwd;
	
	@FindBy(xpath="//label[@for='agree']")
	private WebElement checkBox;
	
	@FindBy(xpath = "//button[@name='submit']")
	private WebElement submitBtn;

	public PatRegistrationForAccount(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getFullName() {
		return fullName;
	}

	public WebElement getAddress() {
		return address;
	}

	public WebElement getCity() {
		return city;
	}

	public WebElement getGenderFemale() {
		return genderFemale;
	}

	public WebElement getGenderMale() {
		return genderMale;
	}

	public WebElement getEmail() {
		return email;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getConfirmPwd() {
		return confirmPwd;
	}

	public WebElement getCheckBox() {
		return checkBox;
	}

	public WebElement getSubmitBtn() {
		return submitBtn;
	}
	
	
	
	

}
