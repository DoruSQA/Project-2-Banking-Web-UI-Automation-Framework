package project.base;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import project.page.AccountPage;
import project.page.ApplyLoanPage;
import project.page.BillPaymentPage;
import project.page.DashboardPage;
import project.page.LoginPage;
import project.page.SendMoneyPage;
import project.page.TransactionPage;
import project.page.TransferMoneyPage;
import dorrusqa.testdata.*;

import dorrusqa.model.*;
import project.utils.ConfigEnvReader;

public abstract class BaseTest {

	// WebDriver
	public WebDriver driver;

	// Web Pages
	public LoginPage loginPage;
	public DashboardPage dashboardPage;
	public AccountPage myAccountPage;
	public BillPaymentPage billPaymentPage;
	public ApplyLoanPage applyLoanPage;
	public TransactionPage transactionPage;
	public SendMoneyPage sendMoneyPage;
	public TransferMoneyPage transferMoneyPage;

	// logger
	private static Logger logger = LoggerFactory.getLogger(BaseTest.class);

	// Soft Assert
	public SoftAssert softAssertion;

	// Test Data
	public LoginUser standardUser;


	public WebDriver createDriver() {

		logger.info("Creating WebDriver instance");

		WebDriver driver = null;
		MutableCapabilities options = null;

		String browser = System.getProperty("browserName", "chrome");

		if (browser.equalsIgnoreCase("chrome")) {

			ChromeOptions chromeOptions = new ChromeOptions();

			// Chrome Browser configuration
			chromeOptions.addArguments("--start-maximized");
			chromeOptions.addArguments("--disable-notifications");
			chromeOptions.addArguments("--disable-popup-blocking");
			chromeOptions.addArguments("--disable-translate");
			chromeOptions.addArguments("--incognito");

			chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

			Map<String, Object> prefs = new HashMap<>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			prefs.put("profile.password_manager_leak_detection", false);

			chromeOptions.setExperimentalOption("prefs", prefs);

			options = chromeOptions;

		} else if (browser.equalsIgnoreCase("firefox")) {

			FirefoxOptions firefoxOptions = new FirefoxOptions();

			firefoxOptions.addArguments("--start-maximized");

			firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

			options = firefoxOptions;

		} else if (browser.equalsIgnoreCase("edge")) {

			EdgeOptions edgeOptions = new EdgeOptions();

			edgeOptions.addArguments("--start-maximized");
			edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

			options = edgeOptions;

		} else
			throw new RuntimeException("Unkown browser " + browser);

		if (options instanceof ChromeOptions)
			driver = new ChromeDriver((ChromeOptions) options);

		else if (options instanceof FirefoxOptions)
			driver = new FirefoxDriver((FirefoxOptions) options);

		else if (options instanceof EdgeOptions)
			driver = new EdgeDriver((EdgeOptions) options);

		return driver;
	}

	public void closeBrowser(WebDriver d) {
		if (d != null)
			d.quit();
	}

	@BeforeClass(alwaysRun = true)
	public void setup() {

		// Init Driver
		driver = createDriver();

		// Init Web Pages
		loginPage = new LoginPage(driver);
		dashboardPage = new DashboardPage(driver);
		myAccountPage = new AccountPage(driver);
		billPaymentPage = new BillPaymentPage(driver);
		applyLoanPage = new ApplyLoanPage(driver);
		transactionPage = new TransactionPage(driver);
		sendMoneyPage = new SendMoneyPage(driver);
		transferMoneyPage = new TransferMoneyPage(driver);

		// Init Soft Assertion
		softAssertion = new SoftAssert();

		// Init Test Data
		standardUser = TestDataFactory.createStandardUser();

	}

	@BeforeMethod(alwaysRun = true)
	public void navigate() {
		
		driver.get(ConfigEnvReader.get("baseUrl"));
	}

	@AfterMethod(alwaysRun = true)
	public void cleanupAfterTest(ITestResult result) {

		clearBrowserState();
		softAssertion = new SoftAssert();
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() {
		
		if (driver != null)
			driver.quit();
	}

	private void clearBrowserState() {

		driver.manage().deleteAllCookies();
		((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
		((JavascriptExecutor) driver).executeScript("window.sessionStorage.clear();");

	}

}
