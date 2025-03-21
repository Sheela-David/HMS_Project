package com.integrationScenarios;

import java.io.IOException;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.DoctorPomClasses.DoctorLogin;
import com.generic.fileUtility.ExcelUtility;
import com.generic.fileUtility.JavaUtility;

public class DocProfileUpdateTest {

	WebDriver driver;
	JavaUtility jUtil = new JavaUtility();
	ExcelUtility eUtil = new ExcelUtility();
	DoctorLogin dLog;
	WebDriverWait wait;

	@BeforeClass
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		dLog = new DoctorLogin(driver);
	}

	@Test(priority = 1)
	public void loginAsDoctor() throws Throwable {
		dLog.doctorLogin();
		WebElement homepage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=' Dashboard ']")));
		Assert.assertTrue(homepage.isDisplayed(), "Doctor Dashboard is not displayed");
	}

	@Test(priority = 2, dependsOnMethods = { "loginAsDoctor" })
	public void updateDoctorProfile() throws Throwable {
		// Navigate to Profile Settings
		WebElement profileLink = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//p[@class='links cl-effect-1']//a[@href='edit-profile.php']")));
		profileLink.click();

		// Wait for page to load fully
		Thread.sleep(3000); // Ensure elements are loaded

		// Check if inside an iframe (if applicable)
		try {
			driver.switchTo().frame(0); // Adjust index if necessary
		} catch (NoSuchFrameException ignored) {
		}

		// Wait for the clinic address field to be visible
		WebElement clinicAddress = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("clinicaddress")));
		clinicAddress.clear();

		// Fetch new address from Excel and update
		String newAddress = eUtil.getDataFromExcel("hms_data", 10, 2);
		clinicAddress.sendKeys(newAddress);

		// Click submit button using JavaScript to avoid click interception
		WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.name("submit")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", submitButton);

		// Switch back from iframe (if applicable)
		driver.switchTo().defaultContent();

		// Handle Alert (if present)
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (TimeoutException e) {
			System.out.println("No alert present after profile update.");
		}

		// Verify Success Message
		WebElement successMessage = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//b[contains(text(),'Profile Last Updation Date:')]")));
		Assert.assertTrue(successMessage.isDisplayed(), "Profile update failed!");
	}

	@Test(priority = 3, dependsOnMethods = { "updateDoctorProfile" })
	public void verifyUpdatedProfile() throws Throwable {
		dLog.doctorLogout();
		dLog.doctorLogin();

		// Navigate to Profile Settings again
		WebElement profileLink = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//p[@class='links cl-effect-1']//a[@href='edit-profile.php']")));
		profileLink.click();

		// Verify updated Clinic Address
		WebElement clinicAddressField = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.name("clinicaddress")));
		String actualAddress = clinicAddressField.getAttribute("value");
		String expectedAddress = eUtil.getDataFromExcel("hms_data", 10, 2);
		Assert.assertEquals(actualAddress, expectedAddress, "Address update verification failed!");
	}

	@AfterClass
	public void tearDown() {
		try {
			// Scroll into view and attempt logout
			WebElement logoutBtn = wait
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='logout.php']")));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", logoutBtn);
			Thread.sleep(1000);

			// Click logout button
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", logoutBtn);

			// Wait to ensure logout happens
			Thread.sleep(2000);
		} catch (Exception e) {
			System.out.println("Logout failed: " + e.getMessage());
		} finally {
			driver.quit();
		}
	}
}
