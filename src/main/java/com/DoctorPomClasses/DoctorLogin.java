package com.DoctorPomClasses;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.generic.fileUtility.FileUtility;

public class DoctorLogin {
	WebDriver driver;
	FileUtility fUtil = new FileUtility();
	
	@FindBy(name="username")
	private WebElement usernameedt;
	
	@FindBy(name="password")
	private WebElement passwordedt;
	
	@FindBy(name="submit")
	private WebElement submitBtn;
	
	@FindBy(className="username")
	private WebElement accountsBtn;
	
	@FindBy(xpath="//a[@href='logout.php']")
	private WebElement logout;
	

	public DoctorLogin(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getUsernameedt() {
		return usernameedt;
	}

	public WebElement getPasswordedt() {
		return passwordedt;
	}

	public WebElement getSubmitBtn() {
		return submitBtn;
	}
	
	public WebElement getAccountsBtn() {
		return accountsBtn;
	}

	public WebElement getLogout() {
		return logout;
	}

	public void doctorLogin() throws Throwable {
		driver.manage().window().maximize();
		driver.get(fUtil.getDataFromPropertiesFile("DocUrl"));
		usernameedt.sendKeys(fUtil.getDataFromPropertiesFile("dusername"));
		passwordedt.sendKeys(fUtil.getDataFromPropertiesFile("dpassword"));
		submitBtn.click();
	}
	
	public void doctorLogout() {
		getAccountsBtn().click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		getLogout().click();
	}
	
	

}
