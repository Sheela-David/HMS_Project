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

import com.DoctorPomClasses.DoctorLogin;

public class DocHomepageVerification {
	
	  WebDriver driver;
	  DoctorLogin dLog;

	    @BeforeClass
	    public void setUp() {
	        driver = new ChromeDriver();
	        
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.manage().window().maximize();
	        dLog = new DoctorLogin(driver); 
	    }

	    @Test(groups = "Smoke")
	    public void loginAsDoctor() throws Throwable {
	        dLog.doctorLogin();
	        WebElement homepage = driver.findElement(By.xpath("//h1[text()='Doctor | Dashboard']"));
	        Assert.assertTrue(homepage.isDisplayed(), "Doctor Dashboard is not displayed");
	        
	        System.out.println("✅ Homepage verification is successful.");
	    }

	    @AfterClass
	    public void logout() {
	        dLog.doctorLogout(); 
	        driver.quit(); 
	        System.out.println("✅ Browser closed successfully.");
	    }

}
