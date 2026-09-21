package project.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import project.base.BasePage;
import dorrusqa.model.*;
import project.utils.ElementWaits;

public class BillPaymentPage extends BasePage {

	private static final Logger logger = LoggerFactory.getLogger(BillPaymentPage.class);

	// Constructor
	public BillPaymentPage(WebDriver d) {
		super(d);
		logger.debug("BillPaymentPage initialized");
	}

	// Static Locator
	private By billPaymentPannel = By.xpath("//div[@data-testid='bill-pay-page']");
	private By fromAccountLabel = By.xpath("//label[text()='From Account']");
	private By billerLabel= By.xpath("//input[@id='biller-search-input']");
	private By billAmountInputBox = By.xpath("//input [@id='bill-amount']");
	private By paymentDate = By.xpath("//input [@id='bill-payment-date']");
	private By reviewPaymentBtn = By.xpath("//button[@data-testid='review-bill-btn']");
	private By confirmPaymentBtn = By.xpath("//button[@data-testid='confirm-bill-btn']");
	private By paymentReceipePannel = By.xpath("//div[@data-testid='bill-pay-confirmation-page']");
	private By paymentAccount = By.xpath("//span[@data-testid='confirm-from-account']");
	private By paymetBiller = By.xpath("//span[@data-testid='confirm-biller']");
	private By paymentAmount = By.xpath("//span[@data-testid='confirm-amount']");
	private By billPayErrorMsg = By.xpath("//div[@data-testid='bill-pay-error']");
	private By billPaymentCofirmationBox = By.xpath("//div[@data-testid='bill-pay-confirm-dialog']");

	// Dynamic Locator
	private By bakingAccountOptionLocator(String option)
	{
		return By.xpath("//div[contains(text(),'" +option   + "')]");
	}

	private By billEntityOptionLocator(String option)
	{
		return By.xpath("//span[text()='" + option  + "']");
	}
	 
	// Page Actions
	public void waitForBillPaymentPannelToBeLoaded()
	{
		ElementWaits.waitToBeInteractable(driver, billPaymentPannel);
	}
	 
	public String getSelectedPaymentAccount()
	{
		return ElementWaits.waitToBeInteractable(driver, paymentAccount).getText();
	}
	 
	public String getBillPayErrorMessage()
	{
		return ElementWaits.waitToBeInteractable(driver, billPayErrorMsg).getText();
	}
	 
	public String getSelectedPaymentBiller()
	{
		return ElementWaits.waitToBeInteractable(driver, paymetBiller).getText();
	}
	 
	public String getSelectedPaymentAmount()
	{
		return ElementWaits.waitToBeInteractable(driver,paymentAmount ).getText();
	}

	public boolean isPaymentReceipePannelDisplayed()
	{
		return ElementWaits.waitToBeInteractable(driver, paymentReceipePannel).isDisplayed();
	}
	 
	public boolean isPaymentConfirmationPannelDisplayed()
	{
		logger.debug("Checking if payment confirmation panel is displayed");
		try {
			return ElementWaits.waitToBeInteractable(driver, billPaymentCofirmationBox).isDisplayed();
		}
		catch (Exception e)
		{
			logger.debug("Payment confirmation panel is not displayed");
			return false;
		}
	}
	 
	public void clickBillInputBox()
	{
		ElementWaits.waitToBeInteractable(driver,billerLabel).click();
	}
	 
	public void clickConfirmPaymentButton()
	{
		ElementWaits.waitToBeInteractable(driver,confirmPaymentBtn).click();
	}
	 
	public void clickReviewPaymentButton()
	{
		ElementWaits.waitToBeInteractable(driver,reviewPaymentBtn).click();
	}
	 
	public void enterBillAmount(String amount)
	{
		ElementWaits.waitToBeInteractable(driver,billAmountInputBox).sendKeys(amount);
	}
	 
	public void enterPaymentDate(String date)
	{
		ElementWaits.waitToBeInteractable(driver,paymentDate).sendKeys(date);
	}
	 
	public void clickFromAccount()
	{
		ElementWaits.waitToBeInteractable(driver,fromAccountLabel).click();
	}
	 
	public void selectBankingAccountOption(String accountType)
	{
		ElementWaits.waitForVisibility(driver, bakingAccountOptionLocator(accountType)).click();
	}

	public void selectBillEntytyOption(String biller)
	{
		ElementWaits.waitForVisibility(driver, billEntityOptionLocator(biller)).click();
	}
	 
	// Page Behavior
	public void fillPaymentBillDetails(BillPaymentUser user)
	{
		logger.info("Starting to fill bill payment details");
		
		waitForBillPaymentPannelToBeLoaded();
		clickFromAccount();
		selectBankingAccountOption(user.getBankingAccountName());
		clickBillInputBox();
		selectBillEntytyOption(user.getBillerEntity());
		enterBillAmount(user.getAmount());
		enterPaymentDate(user.getDate());
		clickReviewPaymentButton();
		//clickConfirmPaymentButton();
		
		logger.info("Bill payment details filled successfully");
	}
	 
	public void fillPaymentBillDetailsWithoutProvidingBankingAccount(BillPaymentUser user)
	{
		logger.info("Starting to fill bill payment details without providing banking account");
		
		waitForBillPaymentPannelToBeLoaded();
		//clickFromAccount();
		//selectBankingAccountOption(user.getBankingAccountName());
		clickBillInputBox();
		selectBillEntytyOption(user.getBillerEntity());
		enterBillAmount(user.getAmount());
		enterPaymentDate(user.getDate());
		clickReviewPaymentButton();
		//clickConfirmPaymentButton();
		
		logger.info("Bill payment details without banking account filled successfully");
	}
	 
	public void fillPaymentBillDetailsWithoutProvidingReceiverAccount(BillPaymentUser user)
	{
		logger.info("Starting to fill bill payment details without providing receiver account");
		
		waitForBillPaymentPannelToBeLoaded();
		clickFromAccount();
		selectBankingAccountOption(user.getBankingAccountName());
		//clickBillInputBox();
		//selectBillEntytyOption(user.getBillerEntity());
		enterBillAmount(user.getAmount());
		enterPaymentDate(user.getDate());
		clickReviewPaymentButton();
		//clickConfirmPaymentButton();
		
		logger.info("Bill payment details without receiver account filled successfully");
	}
	 
}