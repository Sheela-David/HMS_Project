package com.smokeScenarios;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.DoctorPomClasses.DocHomePage;
import com.DoctorPomClasses.DoctorLogin;
import com.generic.fileUtility.BaseClass;

public class DocHomepageVerification extends BaseClass{
	
	  DoctorLogin dLog;

	    @Test(groups = "Smoke")
	    public void loginAsDoctor() throws Throwable {
	    	dLog = new DoctorLogin(driver);
	        dLog.doctorLogin();
	        DocHomePage dHome = new DocHomePage(driver);
	        WebElement homepage = dHome.getDashboard();
	        Assert.assertTrue(homepage.isDisplayed(), "Doctor Dashboard is not displayed");
	        
	        System.out.println("Homepage verification is successful.");
	        dLog.doctorLogout();
	    }

}
