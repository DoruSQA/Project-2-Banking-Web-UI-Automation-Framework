package project.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import project.base.BasePage;
import project.utils.ElementWaits;

public class TransactionPage extends BasePage {

	private static final Logger logger = LoggerFactory.getLogger(TransactionPage.class);

	// Constructor
	public TransactionPage(WebDriver d) {
		super(d);
		logger.debug("TransactionPage initialized");
	}

	// Locator
	private By searchInputBox = By.xpath("//input[@id='all-txn-search']");
	private By tablePannel = By.xpath("//tbody");
	private By transactionDescriptionName = By.xpath("//tbody//td[@data-testid='all-txn-description']");
	private By transactionAccountName = By.xpath("//tbody//td[@data-testid='all-txn-description']/preceding-sibling::td[contains(text(),'Everyd')]");
	private By transactionAmount = By.xpath("//tbody//td[@data-testid='all-txn-amount']");
	private By failedTransactionMsg = By.xpath("//td[@data-testid='no-all-transactions-message']");
	private By paginationMsg = By.xpath("//p[@data-testid='all-txn-pagination-info']");
	 
	// Page Actions
	public void enterTransactionDescription(String transactionDescription)
	{
		ElementWaits.waitToBeInteractable(driver, searchInputBox).sendKeys(transactionDescription);
	}
	 
	public void waitForTablePannelToUpdate()
	{
		ElementWaits.waitToBeInteractable(driver, tablePannel);
	}

	public String getTransactionDescriptionName()
	{
		return ElementWaits.waitForVisibility(driver, transactionDescriptionName).getText();
	}
	 
	public String getTransactionAccountName()
	{
		return ElementWaits.waitForVisibility(driver, transactionAccountName).getText();
	}
	 
	public String getPaginationMessage()
	{
		return ElementWaits.waitForVisibility(driver, paginationMsg).getText();
	}
	 
	public String getTransactionErrorMessage()
	{
		return ElementWaits.waitForVisibility(driver, failedTransactionMsg).getText();
	}
	 
	public String getTransactionAmount()
	{
		return ElementWaits.waitForVisibility(driver, transactionAmount).getText();
	}
	 
	// Page Behavior
	 
}