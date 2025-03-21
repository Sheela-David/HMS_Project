package com.integrationScenarios;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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

public class UpdatingPatientReflectedInDoctorTest {
	
	WebDriver driver;
    WebDriverWait wait;
    PatientLogin pLog;
    DoctorLogin dLog;
    ExcelUtility eUtil = new ExcelUtility();

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        pLog = new PatientLogin(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Explicit wait
    }

    @Test(priority = 1)
    public void patientLoginAndUpdateProfile() throws Throwable {
        pLog.ploginToApp();

        wait.until(ExpectedConditions.titleContains("User Dashboard"));
        Assert.assertTrue(driver.getTitle().contains("User Dashboard"), "Login Failed");

        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='links cl-effect-1']//a[@href='edit-profile.php']")));
        profileLink.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("city")));

        WebElement cityField = driver.findElement(By.name("city"));
        cityField.clear();
        cityField.sendKeys(eUtil.getDataFromExcel("hms_data", 14, 2));
        driver.findElement(By.id("saveProfileBtn")).click();

        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("profileSuccessMsg")));
        Assert.assertTrue(successMsg.isDisplayed(), "Profile update failed");

        // Logout
        driver.findElement(By.id("logoutBtn")).click();
    }

  //  @Test(priority = 2)
    public void doctorLoginAndViewPatientList() throws InterruptedException, Throwable {
        dLog = new DoctorLogin(driver);
        dLog.doctorLogin();

        wait.until(ExpectedConditions.titleContains("Doctor Dashboard"));
        Assert.assertTrue(driver.getTitle().contains("Doctor Dashboard"), "Login Failed");

        WebElement patientListLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("patientListLink")));
        patientListLink.click();

        WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(By.id("searchPatient")));
        searchField.sendKeys(eUtil.getDataFromExcel("hms_data", 14, 4));
        DocHomePage dPage = new DocHomePage(driver);
        dPage.getSearch().click();

        WebElement patientRow = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//table[@id='patientTable']//tr[td[contains(text(),"+eUtil.getDataFromExcel("hms_data", 14, 4)+")]]")));
        Assert.assertTrue(patientRow.isDisplayed(), "Patient not found in list");

        dLog.doctorLogout();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
	
}
