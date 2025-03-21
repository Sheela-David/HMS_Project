package com.smokeScenarios;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.PatientPOMClasses.PatientLogin;

public class PatHomepageVerificationTest {
	
	WebDriver driver;
    PatientLogin pLog; 

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
        driver.manage().window().maximize();

        pLog = new PatientLogin(driver); 
    }

    @Test(groups = "Smoke")
    public void loginAsPatient() throws Throwable {
        pLog.ploginToApp();
        WebElement homepage = driver.findElement(By.xpath("//h1[text()='User | Dashboard']"));
        Assert.assertTrue(homepage.isDisplayed(), "Dashboard header is not displayed, login may have failed");
        System.out.println("Login successful! User is on the dashboard.");
    }

	@AfterClass
	public void logout() {
		pLog.logout();
		driver.close();
	}

}
