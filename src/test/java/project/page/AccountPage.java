package project.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import project.base.BasePage;
import dorrusqa.model.*;
import project.utils.ElementWaits;

public class AccountPage extends BasePage {

	private static final Logger logger = LoggerFactory.getLogger(AccountPage.class);

	// Constructor
	public AccountPage(WebDriver d) {
		super(d);
		logger.debug("AccountPage initialized");
	}

	// Page Locator
	private By frozenAccountMsg = By.xpath("//div[@data-testid='frozen-account-banner']");
	private By addAccountButton = By.xpath("//button[@data-testid='add-account-btn']");
	private By addAccountButtonForm = By.xpath("//button[@data-testid='save-account-form-btn']");
	private By accountNameInputBox = By.xpath("//input[@data-testid='account-form-name-input']");
	private By startingBalanceInputBox=By.xpath("//*[text()='Starting Balance']/following-sibling::div//input");
	private By acceptTerms = By.xpath("//label[@id='account-form-accept-terms-label']");
	private By formPannel = By.xpath("//div[@data-testid='add-account-dialog']");
	private By numberOfBankingAccounts = By.xpath("//tbody/tr");
	private By bankingAccountsPannel = By.xpath("//div[@data-testid='accounts-table-wrapper']");
	private By createdBankingAccountName = By.xpath("//tbody/tr[last()]//p[@data-testid='account-row-name']");
	private By createdBankingAccountType= By.xpath("//tbody/tr[last()]//span[@data-testid='account-row-type-badge']");
	private By createdBankingAccountBalance = By.xpath("//tbody/tr[last()]//td[@data-testid='account-row-balance']");
	private By bankingAccountErrorMsg = By.xpath("//div[@data-testid='account-form-error-message']");
	private By formAccountTypeSelect = By.xpath("//*[text()='Account Type']");
	private By formCancelBtn = By.xpath("//button[text()='Cancel']");
	
	private By formCreditOptionLocator(String option)
	{
		return By.xpath("//div[text()='" + option + "']");
	}
	
	// Page Actions
	public boolean isFrozenAccountMsgDisplayed()
	{
		return ElementWaits.waitForVisibility(driver, frozenAccountMsg).isDisplayed();
	}

	public void clickCancelFormBtn()
	{
		ElementWaits.waitForVisibility(driver, formCancelBtn).click();
	}
	
	public void clickAddBankingAccount()
	{
		ElementWaits.waitForVisibility(driver, addAccountButton).click();
	}
	
	public String getBankingAccountErrorMsg()
	{
		return ElementWaits.waitForVisibility(driver, bankingAccountErrorMsg).getText();
	}
	
	public int getNumberOfBankingAccount()
	{
		waitForBankingAccountsPannelToBeLoaded();// to not get the value too quickly..
		return driver.findElements(numberOfBankingAccounts).size();
	}
	
	public String getCreatedBankingAccountName()
	{
		return ElementWaits.waitToBeInteractable(driver, createdBankingAccountName).getText();
	}

	public String getCreatedBankingAccountType()
	{
		return ElementWaits.waitToBeInteractable(driver, createdBankingAccountType).getText();
	}

	public String getCreatedBankingAccountBalance()
	{
		return ElementWaits.waitToBeInteractable(driver, createdBankingAccountBalance).getText();
	}
	
	public void waitForFormPannelToBeLoaded()
	{
		ElementWaits.waitToBeInteractable(driver, formPannel);
	}
	
	public void waitForBankingAccountsPannelToBeLoaded()
	{
		ElementWaits.waitForVisibility(driver, bankingAccountsPannel);
	}

	public void checkAcceptTerms()
	{
		ElementWaits.waitForVisibility(driver, acceptTerms).click();
	}
	
	public void clickCreateBankingAcoount()
	{
		ElementWaits.waitForVisibility(driver, addAccountButtonForm).click();
	}
	
	public void selectOption(String accountType)
	{
		ElementWaits.waitForVisibility(driver, formAccountTypeSelect).click();
		ElementWaits.waitForVisibility(driver, formCreditOptionLocator(accountType)).click();
	}
	
	public void enterBankingAccountName(String bankingAccountName) {

		clearFieldBox(accountNameInputBox);
		ElementWaits.waitForVisibility(driver, accountNameInputBox).sendKeys(bankingAccountName);
	}
	
	public void enterStartingBalanceAmount(String Amout) {

		clearFieldBox(startingBalanceInputBox);
		ElementWaits.waitForVisibility(driver, startingBalanceInputBox).sendKeys(Amout);
	}
	
	// Page Behavior
	public void fillBankingAccountDetails(BankingAccountUser bankingAccountUser)
	{
		logger.info("Filling banking account details");
		
		waitForFormPannelToBeLoaded();
		enterBankingAccountName(bankingAccountUser.getAccountName());
		selectOption(bankingAccountUser.getAccountType());
		enterStartingBalanceAmount(bankingAccountUser.getStartingBalance());
		checkAcceptTerms();
		
		logger.info("Banking account details filled successfully");
	}
	
	public void fillBankingAccountDetailsWithoutAccountTypeOption(BankingAccountUser bankingAccountUser)
	{
		logger.info("Filling banking account details without selecting account type");
		
		waitForFormPannelToBeLoaded();
		enterBankingAccountName(bankingAccountUser.getAccountName());
		enterStartingBalanceAmount(bankingAccountUser.getStartingBalance());
		checkAcceptTerms();
		
		logger.info("Banking account details without account type filled successfully");
	}
	
	public void fillBankingAccountDetailsWithoutAcceptingTerms(BankingAccountUser bankingAccountUser)
	{
		logger.info("Filling banking account details without accepting terms");
		
		waitForFormPannelToBeLoaded();
		enterBankingAccountName(bankingAccountUser.getAccountName());
		selectOption(bankingAccountUser.getAccountType());
		enterStartingBalanceAmount(bankingAccountUser.getStartingBalance());
		
		logger.info("Banking account details without accepting terms filled successfully");
	}
}