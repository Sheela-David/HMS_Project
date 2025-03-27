package com.generic.fileUtility;

import java.sql.SQLException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {
	//WebDriver driver;
	public WebDriver driver = null;
	public FileUtility fUtil = new FileUtility();
	public ExcelUtility eUtil = new ExcelUtility();
	public JavaUtility jUtil = new JavaUtility();
	
	
	@BeforeSuite(groups = {"smoke", "regression"})
	public void configBS() throws SQLException {
		System.out.println("===Connect to DB, Report Config===");
	}
	
	@BeforeClass
    public void setUp() {
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize(); 
    }
	
	
//	@Parameters("Browser")
//	@BeforeClass(groups = {"smoke", "regression"})
//	public void configBC(@Optional("chrome") String browser) throws Throwable {
//		System.out.println("===launch browser===");
//		//String Browser = fUtil.getDataFromPropertiesFile("browser");
//		String Browser =browser;
//		if(Browser.equals("chrome")) {
//			driver = new ChromeDriver();
//		}
//		else if(Browser.equals("edge")) {
//			driver = new EdgeDriver();
//		}
//		else if(Browser.equals("firefox")) {
//			driver = new FirefoxDriver();
//		}
//		else {
//			driver = new ChromeDriver();
//		}
//	}

	@BeforeMethod
	public void configBM() throws Throwable {
		System.out.println("===login to appln===");
		String patUrl = fUtil.getDataFromPropertiesFile("PatUrl");
		String username = fUtil.getDataFromPropertiesFile("pusername");
		String password = fUtil.getDataFromPropertiesFile("ppassword");
		String docUrl = fUtil.getDataFromPropertiesFile("DocUrl");
		String dusername = fUtil.getDataFromPropertiesFile("pusername");
		String dpassword = fUtil.getDataFromPropertiesFile("ppassword");
	}
	
//	@AfterMethod(groups = {"smoke", "regression"})
//	public void configAM() {
//		System.out.println("logout from appln");
//		DocHomePage hp = new DocHomePage(driver);
//		hp.logout();
//	}
//	
//	@AfterClass(groups = {"smoke", "regression"})
//	public void configAC() {
//		System.out.println("close browser");
//		driver.quit();
//    }
	
	 @AfterClass
	    public void logout() {
	        driver.quit(); 
	        System.out.println("✅ Browser closed successfully.");
	    }
	
	@AfterSuite(groups = {"smoke", "regression"})
	public void configAS() throws SQLException {
		System.out.println("close db connection");	
	}
	


}
