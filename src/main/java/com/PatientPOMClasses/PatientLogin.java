package com.PatientPOMClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.generic.fileUtility.FileUtility;

public class PatientLogin {
	FileUtility fUtil = new FileUtility();
	WebDriver driver;
	@FindBy(name="username")                              //rule 2: Object creation
	private WebElement usernameEdt;
	
	@FindBy(name="password")
	private WebElement passwordEdt;
	
	@FindBy(name="submit")
	private WebElement loginBtn;
	
	@FindBy(className="ti-angle-down")
	private WebElement accountBtn;
	
	@FindBy(xpath="//a[@href='logout.php']")
	private WebElement logout;
	
	
	public PatientLogin(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	public WebElement getAccountBtn() {
		return accountBtn;
	}

	public WebElement getLogout() {
		return logout;
	}

	/**
	 * login to application based on username, password, url arguments
	 * @param url
	 * @param username
	 * @param password
	 * @throws Throwable 
	 */
	//rule 5: object utilization(done in tc this is one way) 
	// 2 way create business library
	public void ploginToApp() throws Throwable {
		driver.get(fUtil.getDataFromPropertiesFile("PatUrl"));
		driver.manage().window().maximize();
		usernameEdt.sendKeys(fUtil.getDataFromPropertiesFile("pusername"));
		passwordEdt.sendKeys(fUtil.getDataFromPropertiesFile("ppassword"));
		loginBtn.click();
	}

	public void rloginToApp() throws Throwable {
		driver.get(fUtil.getDataFromPropertiesFile("PatRegistrationUrl"));
		driver.manage().window().maximize();
		usernameEdt.sendKeys(fUtil.getDataFromPropertiesFile("rusername"));
		passwordEdt.sendKeys(fUtil.getDataFromPropertiesFile("rpassword"));
		loginBtn.click();
	}

	public void logout() {
		getAccountBtn().click();
		getLogout().click();
	}

}
