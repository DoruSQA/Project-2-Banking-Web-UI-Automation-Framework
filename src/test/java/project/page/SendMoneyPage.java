package project.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import project.base.BasePage;
import dorrusqa.model.*;
import project.utils.ElementWaits;

public class SendMoneyPage extends BasePage {

	private static final Logger logger = LoggerFactory.getLogger(SendMoneyPage.class);

	// Constructor
	public SendMoneyPage(WebDriver d) {
		super(d);
		logger.debug("SendMoneyPage initialized");
	}

	// Page Locator
	private By sendMoneyPannel = By.xpath("//form[@data-testid='send-money-form']");
	private By selectAccountField = By.xpath("//span[text()='Select account']");
	private By selectPayeeField = By.xpath("//span[text()='Select a payee']");
	private By amountInputBox = By.xpath("//input[@id='send-amount']");
	private By reviewSendBtn = By.xpath("//button[@data-testid='review-send-btn']");
	private By confirmationPannel = By.xpath("//div[@data-testid='send-money-confirm-dialog']");
	private By acceptConfirmationButton = By.xpath("//button[@data-testid='confirm-send-btn']");
	private By receipPannel = By.xpath("//div[@data-testid='send-confirmation-summary']");
	private By sendMoneyAccount = By.xpath("//span[@data-testid='confirm-from-account']");
	private By sendMoneyPayee = By.xpath("//span[@data-testid='confirm-to-payee']");
	private By sendMoneyAmount = By.xpath("//span[@data-testid='confirm-amount']");
	private By sendMoneyErrorMessage = By.xpath("//div[@data-testid='send-money-error']");
	 
	private By accountOptionLocator(String option)
	{
		return By.xpath("//div[contains(text(),'" + option + "')]");
	}
	 
	private By payeeOptionLocator(String payee)
	{
		return By.xpath("//div[contains(text(),'"+ payee + "')]");
	}
	 
	// Page Actions
	public boolean isSendMoneyReceipDisplayed()
	{
		logger.debug("Checking if Send Money receipt is displayed");
		try {
			return ElementWaits.waitToBeInteractable(driver, receipPannel).isDisplayed();
		}catch (Exception e)
		{
			logger.debug("Send Money receipt is not displayed");
			return false;
		}
		 
	}

	public String getSendMoneyAccountName()
	{
		return ElementWaits.waitToBeInteractable(driver, sendMoneyAccount).getText();
	}
	 
	public String getSendMoneyErrorMsg()
	{
		return ElementWaits.waitToBeInteractable(driver, sendMoneyErrorMessage).getText();
	}
	 
	public String getSendMoneyPayeeName()
	{
		return ElementWaits.waitToBeInteractable(driver, sendMoneyPayee).getText();
	}
	 
	public String getSendMoneyAmount()
	{
		return ElementWaits.waitToBeInteractable(driver, sendMoneyAmount).getText();
	}
	 
	 
	public void selectAccountOption(String accountName)
	{
		ElementWaits.waitForVisibility(driver, accountOptionLocator(accountName)).click();
	}
	 
	public void clickSendButton()
	{
		ElementWaits.waitToBeInteractable(driver, reviewSendBtn).click();
	}
	 
	public void selecPayeeOption(String payeeName)
	{
		ElementWaits.waitForVisibility(driver, payeeOptionLocator(payeeName)).click();
	}
	 
	public void enterAmount(String amount)
	{
		ElementWaits.waitToBeInteractable(driver, amountInputBox).sendKeys(amount);
	}
	 
	public boolean isConfirmationPannelDisplayed()
	{
		logger.debug("Checking if Send Money confirmation panel is displayed");
		try {
			return ElementWaits.waitToBeInteractable(driver, confirmationPannel).isDisplayed();
		}catch (Exception e)
		{
			logger.debug("Send Money confirmation panel is not displayed");
			return false;
		}
		 
	}
	 
	public void waitForSendMoneyPannelToBeLoaded()
	{
		ElementWaits.waitToBeInteractable(driver, sendMoneyPannel);
	}
	 
	public void clickSelectAccountField()
	{
		ElementWaits.waitToBeInteractable(driver, selectAccountField).click();
	}
	 
	public void clickAcceptConfirmationButton()
	{
		ElementWaits.waitToBeInteractable(driver, acceptConfirmationButton).click();
	}
	 
	public void clickSelectPayeeField()
	{
		ElementWaits.waitToBeInteractable(driver, selectPayeeField).click();
	}

	// Page Behavior
	public void fillSendMoneyDetails(SendMoneyUser sendMoneyUser)
	{
		logger.info("Starting to fill Send Money details");
		
		waitForSendMoneyPannelToBeLoaded();
		clickSelectAccountField();
		selectAccountOption(sendMoneyUser.getAccount());
		clickSelectPayeeField();
		selecPayeeOption(sendMoneyUser.getPayee());
		enterAmount(sendMoneyUser.getAmount());
		clickSendButton();
		
		logger.info("Send Money details filled successfully");
	}
	 
	public void fillSendMoneyDetailsWithoutPayerAccount(SendMoneyUser sendMoneyUser)
	{
		logger.info("Starting to fill Send Money details without payer account");
		
		waitForSendMoneyPannelToBeLoaded();
		//clickSelectAccountField();
		//selectAccountOption(sendMoneyUser.getAccount());
		clickSelectPayeeField();
		selecPayeeOption(sendMoneyUser.getPayee());
		enterAmount(sendMoneyUser.getAmount());
		clickSendButton();
		
		logger.info("Send Money details without payer account filled successfully");
	}
	 
	public void fillSendMoneyDetailsWithoutPayeeAccount(SendMoneyUser sendMoneyUser)
	{
		logger.info("Starting to fill Send Money details without payee account");
		
		waitForSendMoneyPannelToBeLoaded();
		clickSelectAccountField();
		selectAccountOption(sendMoneyUser.getAccount());
		//clickSelectPayeeField();
		//selecPayeeOption(sendMoneyUser.getPayee());
		enterAmount(sendMoneyUser.getAmount());
		clickSendButton();
		
		logger.info("Send Money details without payee account filled successfully");
	}
	 
}