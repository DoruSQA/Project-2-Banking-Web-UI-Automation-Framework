package project.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import project.base.BasePage;
import project.utils.ElementWaits;

public class DashboardPage extends BasePage {

	private static final Logger logger = LoggerFactory.getLogger(DashboardPage.class);

	// Constructor
	public DashboardPage(WebDriver d) {
		super(d);
		logger.debug("DashboardPage initialized");
	}

	// Page Locator
	By frozenAccountMsg = By.xpath("//div[@data-testid='frozen-account-banner']");
	By accountsButton = By.xpath("//a[@data-testid='sidebar-link-accounts']//span");
	By billPayButton = By.xpath("//a[@data-testid='sidebar-link-bill-pay']");
	By applyLoanButton = By.xpath("//a[@data-testid='sidebar-link-apply-loan']");
	By transactionButton = By.xpath("//a[@data-testid='sidebar-link-transactions']");
	By sendMoneyButton = By.xpath("//a[@data-testid='sidebar-link-send-money']");
	By transferMoneyButton = By.xpath("//a[@data-testid='sidebar-link-transfer']");
	 
	// Page Actions
	public boolean isFrozenAccountMsgDisplayed()
	{
		return ElementWaits.waitForVisibility(driver, frozenAccountMsg).isDisplayed();
	}
	 
	public void clickAccountsButton()
	{
		ElementWaits.waitForVisibility(driver, accountsButton).click();
	}
	 
	public void clickTransferFundsButton()
	{
		ElementWaits.waitForVisibility(driver, transferMoneyButton).click();
	}
	 
	public void clickTransactionButton()
	{
		ElementWaits.waitForVisibility(driver, transactionButton).click();
	}
	 
	public void clickApplyLoan()
	{
		ElementWaits.waitForVisibility(driver, applyLoanButton).click();
	}
	 
	public void clickSendMoneyButton()
	{
		ElementWaits.waitForVisibility(driver, sendMoneyButton).click();
	}
	 
	public void clickBillPayButton()
	{
		ElementWaits.waitForVisibility(driver, billPayButton).click();
	}

	 
}