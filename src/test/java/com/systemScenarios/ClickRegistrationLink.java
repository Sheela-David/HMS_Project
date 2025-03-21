package com.systemScenarios;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import com.PatientPOMClasses.PatRegistrationForAccount;
import com.PatientPOMClasses.PatientLogin;
import com.generic.fileUtility.BaseClass;
import com.generic.fileUtility.ExcelUtility;
import com.generic.fileUtility.FileUtility;
import com.integrationScenarios.BookAnAppointmentTest;

public class ClickRegistrationLink extends BaseClass{
	//WebDriver driver;
	ExcelUtility eUtil = new ExcelUtility();
	FileUtility fUtil = new FileUtility();
	@Test
	public void clickingRegLink() throws Throwable {
		ChromeOptions cp = new ChromeOptions();
		cp.addArguments("--disable-notifications");
		navigateToUrl();
		//driver = new ChromeDriver(cp);
		//driver.get(fUtil.getDataFromPropertiesFile("PatRegistrationUrl"));
		PatRegistrationForAccount pReg = new PatRegistrationForAccount(driver);
		pReg.getFullName().sendKeys(eUtil.getDataFromExcel("BookAnAppointment", 4, 0));
		Thread.sleep(2000);
		pReg.getAddress().sendKeys(eUtil.getDataFromExcel("BookAnAppointment", 4, 1));
		Thread.sleep(2000);
		pReg.getCity().sendKeys(eUtil.getDataFromExcel("BookAnAppointment", 4, 2));
		Thread.sleep(2000);
		pReg.getGenderFemale().click();
		pReg.getEmail().sendKeys(eUtil.getDataFromExcel("BookAnAppointment", 4, 4));
		Thread.sleep(2000);
		pReg.getPassword().sendKeys(eUtil.getDataFromExcel("BookAnAppointment", 4, 5));
		Thread.sleep(2000);
		pReg.getConfirmPwd().sendKeys(eUtil.getDataFromExcel("BookAnAppointment", 4, 6));
		Thread.sleep(2000);
		pReg.getCheckBox().click();
		Thread.sleep(2000);
		pReg.getSubmitBtn().submit();
		Thread.sleep(2000);
		
		PatientLogin pLog = new PatientLogin(driver);
		pLog.rloginToApp();
		
		BookAnAppointmentTest book = new BookAnAppointmentTest();
		book.bookingAnApp();
		
		pLog.logout();
	}

}
