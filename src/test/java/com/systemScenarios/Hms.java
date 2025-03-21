package com.systemScenarios;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.DoctorPomClasses.DocHomePage;
import com.DoctorPomClasses.DoctorLogin;
import com.PatientPOMClasses.PatientLogin;
import com.PatientPOMClasses.PatientUpdateProfile;
import com.generic.fileUtility.ExcelUtility;

public class Hms {
	
	  WebDriver driver;
	    WebDriverWait wait;
	    PatientLogin pLog;
	    DoctorLogin dLog;
	    ExcelUtility eUtil = new ExcelUtility();

	    @BeforeClass
	    public void setUp() {
	    	ChromeOptions options = new ChromeOptions();
	    	options.addArguments("--disable-notifications");
	        driver = new ChromeDriver(options);
	        pLog = new PatientLogin(driver);
	        dLog = new DoctorLogin(driver);
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.manage().window().maximize();
	        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    }

	    @Test(priority = 1)
	    public void patientLoginAndUpdateProfile() throws Throwable {
	    	
	        pLog.ploginToApp();
	        
	        // Verify Login
	        wait.until(ExpectedConditions.titleContains("User Dashboard"));
	        Assert.assertTrue(driver.getTitle().contains("User Dashboard"), "Login Failed");

	        // Navigate to Profile
	        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='edit-profile.php']")));
	        profileLink.click();

	        // Update Profile
	        PatientUpdateProfile pProfile = new PatientUpdateProfile(driver);
	        WebElement city =  pProfile.getCity();
	        city.clear();
	        city.sendKeys(eUtil.getDataFromExcel("hms_data", 14, 2));
	        pProfile.getSubmit().click();

	        // Verify Update
	        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("profileSuccessMsg")));
	        Assert.assertTrue(successMsg.isDisplayed(), "Profile update failed");

	        // Logout
	        pLog.logout();
	    }

	    @Test(priority = 2)
	    public void doctorLoginAndViewPatientList() throws Throwable {
	        dLog.doctorLogin();

	        // Verify Login
	        wait.until(ExpectedConditions.titleContains("Doctor Dashboard"));
	        Assert.assertTrue(driver.getTitle().contains("Doctor Dashboard"), "Login Failed");

	        // Navigate to Patient List
	        DocHomePage dHome = new DocHomePage(driver);
	        WebElement patientLink = wait.until(ExpectedConditions.elementToBeClickable(dHome.getPatients()));
	        patientLink.click();
	        
	        WebElement patientsList = wait.until(ExpectedConditions.elementToBeClickable(dHome.getManagePatient()));
	        patientsList.click();

	        // Search for Patient
	        WebElement searchField = wait.until(ExpectedConditions.visibilityOf(dHome.getSearch()));
	        searchField.sendKeys(eUtil.getDataFromExcel("hms_data", 18, 0));
	        
	        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(dHome.getSearchBtn()));
	        searchButton.click(); 
	        

	        // Verify Patient Appears
	        WebElement patientRow = wait.until(ExpectedConditions.visibilityOfElementLocated(dHome.getPatientListTable()));
	        Assert.assertTrue(patientRow.isDisplayed(), "Patient not found in list");

	        // Logout
	        dLog.doctorLogout();
	    }

	    

	    @AfterClass
	    public void tearDown() {
	        driver.quit();
	    }

}
