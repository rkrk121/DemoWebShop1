package com.tricentis.genericutility;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.tricentis.objectrepository.HomePage;
import com.tricentis.objectrepository.LoginPage;
import com.tricentis.objectrepository.WelcomePage;

public class BaseClass {
	public static WebDriver driver;
	public static ExtentReports extReport;
	public ExtentTest test;
	public static ExtentTest listTest;
	public WebDriverWait wait;
	
	public JavaUtility jLib;
	public FileUtility fLib;
	public ExcelUtility eLib;
	
	public WelcomePage welcomePage;
	public LoginPage loginPage;
	public HomePage homePage;
	
	@BeforeSuite(alwaysRun = true)
	public void configReports() {
		jLib=new JavaUtility();
		String time = jLib.getSystemTime();
		ExtentSparkReporter spark=new ExtentSparkReporter("./HTML_reports/ExtentReport-"+time+".html");
		extReport=new ExtentReports();
		extReport.attachReporter(spark);
	}
	@Parameters("Browser")
	@BeforeClass(alwaysRun = true)
	public void launchBrowser(@Optional("chrome") String browserName) throws IOException {
		if (browserName.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}else if (browserName.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}else if (browserName.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		fLib=new FileUtility();	
		String URL = fLib.getDataFromProperty("url");
		driver.get(URL);
		homePage=new HomePage(driver);
	}
	@BeforeMethod(alwaysRun = true)
	public void login(Method method) throws IOException {
		test = extReport.createTest(method.getName());
		listTest=test;
		welcomePage=new WelcomePage(driver);
		welcomePage.getLoginLink().click();
		loginPage=new LoginPage(driver);
		String Email = fLib.getDataFromProperty("email");
		String password = fLib.getDataFromProperty("password");
		loginPage.getEmailTextField().sendKeys(Email);
		loginPage.getPasswordTextFiled().sendKeys(password);
		loginPage.getLoginButton().click();
	}
	@AfterMethod(alwaysRun = true)
	public void logout() {
		homePage.getLogoutLink().click();
	}
	@AfterClass(alwaysRun = true)
	public void closeBrowser() {
		driver.quit();
	}
	@AfterSuite(alwaysRun = true)
	public void reportBackup() {
		extReport.flush();
	}
}
