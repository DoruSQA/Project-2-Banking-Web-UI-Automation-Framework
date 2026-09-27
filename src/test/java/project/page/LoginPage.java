package project.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import project.base.BasePage;
import dorrusqa.model.*;
import project.utils.ElementWaits;

public class LoginPage extends BasePage {

	private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

	// Constructor
	public LoginPage(WebDriver d) {
		super(d);
		logger.debug("LoginPage initialized");
	}

	// Page Locator
	private By usernameInputBox = By.xpath("//input[@id='login-username']");
	private By passwordInputBox = By.xpath("//input[@id='login-password']");
	private By loginButton = By.xpath("//button[@data-testid='login-submit-btn']");
	private By loginPannel = By.xpath("//div[@data-testid='login-card']");
	private By failedLoginErrorMessage = By.xpath("//span[@data-testid='login-error-message']");

	// Page Actions
	public void enterUsername(String username) {

		clearFieldBox(usernameInputBox);
		ElementWaits.waitToBeInteractable(driver, usernameInputBox).sendKeys(username);
	}
		
	private void waitForLoginPannelToBeLoaded()
	{
		ElementWaits.waitForVisibility(driver, loginPannel);

	}
	
	public void enterPassword(String password) {
		
		clearFieldBox(passwordInputBox);
		ElementWaits.waitForVisibility(driver, passwordInputBox).sendKeys(password);
	}

	public void clickLogin() {

		ElementWaits.waitForVisibility(driver, loginButton).click();
	}

	public boolean isLoginButtonVisible() {

		return ElementWaits.waitForVisibility(driver, loginButton).isDisplayed();
	}

	public void navigateTo(String url) {

		driver.navigate().to(url);
	}

	public String getFailedLoginErrorMessage() {

		return  ElementWaits.waitForVisibility(driver, failedLoginErrorMessage).getText();
	}

	// Page Behavior
	public void authentication(String Username, String Password) {

		logger.info("Starting authentication for user: {}", Username);

		waitForLoginPannelToBeLoaded();
		enterUsername(Username);
		enterPassword(Password);
		clickLogin();

		logger.info("Authentication flow completed for user: {}", Username);
	}

	public void authentication(LoginUser user) {

		logger.info("Starting authentication for user: {}", user.getUsername());
		
		waitForLoginPannelToBeLoaded();
		enterUsername(user.getUsername());
		enterPassword(user.getPassword());
		clickLogin();

		logger.info("Authentication flow completed for user: {}", user.getUsername());
	}
}
