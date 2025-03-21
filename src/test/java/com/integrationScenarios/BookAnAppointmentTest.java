package com.integrationScenarios;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.PatientPOMClasses.BookAnAppointment;
import com.PatientPOMClasses.PatHomePage;
import com.PatientPOMClasses.PatientLogin;
import com.generic.fileUtility.JavaUtility;

public class BookAnAppointmentTest {
	WebDriver driver;
	JavaUtility jUtil = new JavaUtility();
	
	@Test
	public void bookAnApp() throws Throwable {
		ChromeOptions cp = new ChromeOptions();
		cp.addArguments("--disable-notifications");
		WebDriver driver = new EdgeDriver();
		PatientLogin patLog = new PatientLogin(driver);
		patLog.ploginToApp();
		
		
		PatHomePage pHome = new PatHomePage(driver);
		pHome.getBookAppointment().click();
	}
		
	public void bookingAnApp() throws Throwable {
		BookAnAppointment bApp = new BookAnAppointment(driver);
		bApp.doctorSpecialization();
		
		bApp.selectingDoctor();
		Thread.sleep(2000);
		bApp.getDate().sendKeys(jUtil.getSystemDateYYYYMMDD());
		Thread.sleep(2000);
		bApp.getTime().sendKeys(jUtil.getTime());
		Thread.sleep(2000);
		bApp.getSubmitBtn().click();
		Thread.sleep(2000);
		String actualValue = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		
		Assert.assertEquals(actualValue, "Your appointment booked successfully");
		
	}

}
