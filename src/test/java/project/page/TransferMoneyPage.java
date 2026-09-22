package project.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import project.base.BasePage;
import dorrusqa.model.*;
import project.utils.ElementWaits;

public class TransferMoneyPage extends BasePage {

	private static final Logger logger = LoggerFactory.getLogger(TransferMoneyPage.class);

	// Constructor
	public TransferMoneyPage(WebDriver d) {
		super(d);
		logger.debug("TransferMoneyPage initialized");
	}

	// Page Locator
	private By transferMoneyPannel = By.xpath("//form[@data-testid='transfer-form']");
	private By selectPayerAccountFiled = By.xpath("//button[@id='transfer-from-trigger']");
	private By selectPayeeAccountFiled = By.xpath("//button[@id='transfer-to-trigger']");
	private By amountInputBox = By.xpath("//input[@id='transfer-amount']");
	private By dateInputBox = By.xpath("//input[@id='transfer-scheduled-date']");
	private By reviewTransferBtn = By.xpath("//button[@data-testid='review-transfer-btn']");
	private By confirmationPannel = By.xpath("//div[@data-testid='transfer-confirm-dialog']");
	private By confirmTransferBtn = By.xpath("//button[@data-testid='confirm-transfer-btn']");
	private By transferPayerName = By.xpath("//span[@data-testid='confirm-from-account']");
	private By transferPayeeName = By.xpath("//span[@data-testid='confirm-to-account']");
	private By transferAmount = By.xpath("//span[@data-testid='confirm-amount']");
	private By transferErrorMsg = By.xpath("//div[@data-testid='transfer-error-message']");
	 
	private By transferMoneyPayerLocator(String account)
	{
		return By.xpath("//div[contains(text(),'" + account + "')]");
	}
	 
	private By transferMoneyPayeeLocator(String account)
	{
		return By.xpath("//div[@data-testid='transfer-to-option']/div[contains(text(),'" + account + "')]");
	}
	 
	private By dateOptionLocator(String dateOption)
	{
		return By.xpath("//label[contains(@data-testid,'date-type-" + dateOption + "')]");
	}
	 
	// Page Actionn
	public String getTransferPayerName()
	{
		return ElementWaits.waitToBeInteractable(driver, transferPayerName).getText();
	}

	public String getTransferErrorMsg()
	{
		return ElementWaits.waitToBeInteractable(driver, transferErrorMsg).getText();
	}

	public String getTransferPayeeName()
	{
		return ElementWaits.waitToBeInteractable(driver, transferPayeeName).getText();
	}

	public String getTransferAmount()
	{
		return ElementWaits.waitToBeInteractable(driver, transferAmount).getText();
	}
	 
	public void waitForTransferMoneyPannelToBeLoaded()
	{
		ElementWaits.waitToBeInteractable(driver, transferMoneyPannel);
	}
	 
	public boolean isConfirmationPannelDisplayed()
	{
		logger.debug("Checking if transfer confirmation panel is displayed");
		try {
			return ElementWaits.waitToBeInteractable(driver, confirmationPannel).isDisplayed();
		}catch (Exception e)
		{
			logger.debug("Transfer confirmation panel is not displayed");
			return false;
		}
	}

	public void clickConfirmTransferButton()
	{
		ElementWaits.waitToBeInteractable(driver, confirmTransferBtn).click();
	}
	 
	public void clickReviewTransferButton()
	{
		ElementWaits.waitToBeInteractable(driver, reviewTransferBtn).click();
	}
	 
	public void enterDate(String date)
	{
		ElementWaits.waitToBeInteractable(driver, dateInputBox).sendKeys(date);
	}
	 
	public void clickSelectedDateOption(String dateOption) // today or scheduled
	{
		ElementWaits.waitToBeInteractable(driver, dateOptionLocator(dateOption)).click();
	}
	 
	public void selectPayerOption(String selectedAccount)
	{
		ElementWaits.waitForVisibility(driver, transferMoneyPayerLocator(selectedAccount)).click();
	}
	 
	public void enterAmount(String amount)
	{
		ElementWaits.waitForVisibility(driver,amountInputBox).sendKeys(amount);
	}
	 
	public void selectPayeeOption(String selectedAccount)
	{
		ElementWaits.waitForVisibility(driver, transferMoneyPayeeLocator(selectedAccount)).click();
	}
	 
	public void clickSelectPayerAccountField()
	{
		ElementWaits.waitToBeInteractable(driver, selectPayerAccountFiled).click();
	}
	 
	public void clickSelectPayeeAccountField()
	{
		ElementWaits.waitToBeInteractable(driver, selectPayeeAccountFiled).click();
	}
	 
	// Page Behavior
	public void fillTransferMoneyDetails(TransferMoneyUser transferMoneyUser)
	{
		logger.info("Starting to fill Transfer Money details");
		
		waitForTransferMoneyPannelToBeLoaded();
		clickSelectPayerAccountField();
		selectPayerOption(transferMoneyUser.getPayerAccount());
		clickSelectPayeeAccountField();
		selectPayeeOption(transferMoneyUser.getPayeeAccount());
		enterAmount(transferMoneyUser.getAmount());
		clickSelectedDateOption(transferMoneyUser.getDateOption());
		if(transferMoneyUser.getDateOption().equals("scheduled"))
		{
			enterDate(transferMoneyUser.getDate());  
		}
		clickReviewTransferButton();
		
		logger.info("Transfer Money details filled successfully");
	}

	public void fillTransferMoneyWithoutSourceAccount(TransferMoneyUser transferMoneyUser)
	{
		logger.info("Starting to fill Transfer Money details without source account");
		
		waitForTransferMoneyPannelToBeLoaded();
		//clickSelectPayerAccountField();
		//selectPayerOption(transferMoneyUser.getPayerAccount());
		//clickSelectPayeeAccountField();
		//selectPayeeOption(transferMoneyUser.getPayeeAccount());
		enterAmount(transferMoneyUser.getAmount());
		clickSelectedDateOption(transferMoneyUser.getDateOption());
		if(transferMoneyUser.getDateOption().equals("scheduled"))
		{
			enterDate(transferMoneyUser.getDate());  
		}
		clickReviewTransferButton();
		
		logger.info("Transfer Money details without source account filled successfully");
	}

	public void fillTransferMoneyWithoutDestinationAccount(TransferMoneyUser transferMoneyUser)
	{
		logger.info("Starting to fill Transfer Money details without destination account");
		
		waitForTransferMoneyPannelToBeLoaded();
		clickSelectPayerAccountField();
		selectPayerOption(transferMoneyUser.getPayerAccount());
		//clickSelectPayeeAccountField();
		//selectPayeeOption(transferMoneyUser.getPayeeAccount());
		enterAmount(transferMoneyUser.getAmount());
		clickSelectedDateOption(transferMoneyUser.getDateOption());
		if(transferMoneyUser.getDateOption().equals("scheduled"))
		{
			enterDate(transferMoneyUser.getDate());  
		}
		clickReviewTransferButton();
		
		logger.info("Transfer Money details without destination account filled successfully");
	}
	 
}