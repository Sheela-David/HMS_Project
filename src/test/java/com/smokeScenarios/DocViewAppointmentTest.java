package com.smokeScenarios;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.DoctorPomClasses.DocHomePage;
import com.DoctorPomClasses.DoctorLogin;
import com.generic.fileUtility.BaseClass;

public class DocViewAppointmentTest extends BaseClass{
	
    DoctorLogin dLog;

    @Test(groups = "Smoke")
    public void loginAsDoctor() throws Throwable {
    	DoctorLogin dLog = new DoctorLogin(driver);
        dLog.doctorLogin();
        Thread.sleep(3000);
        DocHomePage dHome = new DocHomePage(driver);
        WebElement homepage = dHome.getDashboard();
        Assert.assertTrue(homepage.isDisplayed(), "Doctor Dashboard is not displayed");
    }

    @Test(dependsOnMethods = {"loginAsDoctor"})
    public void verifyDoctorCanViewAppointments() throws InterruptedException {
        handlePopup();
        Thread.sleep(2000);
        
        //"View Appointments" Page
        driver.findElement(By.xpath("//p[@class='cl-effect-1']//a[@href='appointment-history.php']")).click(); 
        Thread.sleep(3000);

        //appointments table is displayed
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement appointmentsTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sample-table-1")));
        Assert.assertTrue(appointmentsTable.isDisplayed(), "Appointments table is not displayed");

        
        List<WebElement> appointments = driver.findElements(By.xpath("//table[@id='sample-table-1']//tr"));
        Assert.assertTrue(appointments.size() > 0, "No appointments found for the doctor!");
    }
    
    public void handlePopup() throws InterruptedException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            
            List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
            if (!iframes.isEmpty()) {
                driver.switchTo().frame(iframes.get(0)); 
                System.out.println("Switched to iframe containing the popup.");
            }
            Thread.sleep(2000);

            
            WebElement popupOkButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
            popupOkButton.click();
            System.out.println("Popup handled successfully.");

      
            driver.switchTo().defaultContent();
        } catch (TimeoutException e) {
            System.out.println("No pop-up found.");
        } catch (NoSuchElementException e) {
            System.out.println("Popup element not found.");
        } catch (ElementNotInteractableException e) {
            System.out.println("Popup found but not interactable, retrying with JavaScript.");
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("document.querySelector(\"button[text='OK']\").click();");
            } catch (Exception jsException) {
                System.out.println("JavaScript click failed.");
            }
        }
        dLog.doctorLogout();
    }  

}
