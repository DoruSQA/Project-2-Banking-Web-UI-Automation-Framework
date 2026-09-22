package project.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import project.base.BasePage;
import dorrusqa.model.*;
import project.utils.ElementWaits;

public class ApplyLoanPage extends BasePage {

	private static final Logger logger = LoggerFactory.getLogger(ApplyLoanPage.class);

	// Constructor
	public ApplyLoanPage(WebDriver d) {
		super(d);
		logger.debug("ApplyLoanPage initialized");
	}

	// Page Locator
	private By applyForLoanBtn = By.xpath("//button[@data-testid='open-apply-loan-btn']");
	private By applyLoanPannel = By.xpath("//div[@data-testid='apply-loan-dialog']");
	private By selectLoanTypeBtn = By.xpath("//button[@id='loan-type-trigger']");
	private By selectDisbursmentAccBtn = By.xpath("//button[@id='loan-account-trigger']");
	private By loanAmountInputBox = By.xpath("//input[@id='loan-amount']");
	private By loanInterestRateInputBox = By.xpath("//input[@id='loan-interest-rate']");
	private By disbursemntAccOption = By.xpath("//div[contains(text(),'Everyday')]");
	private By reviewLoanBtn = By.xpath("//button[@data-testid='review-loan-btn']");
	private By loanPurposeInputBox = By.xpath("//textarea[@name='loan_purpose_field']");
	private By confirmationLoanPannel = By.xpath("//div[@data-testid='loan-confirm-dialog']");
	private By submitButton = By.xpath("//button[@data-testid='confirm-loan-btn']");
	private By confirmationLoanReceip = By.xpath("//div[@data-testid='loan-confirmation-summary']");
	private By loanType = By.xpath("//span[@data-testid='confirm-loan-type']");
	private By loanAmount = By.xpath("//span[@data-testid='confirm-loan-amount']");
	private By loanAccount = By.xpath("//span[@data-testid='confirm-loan-account']");
	private By loanErrorMsg = By.xpath("//div[@data-testid='apply-loan-error-message']");
	 
	private By loanTypeOptionLocator(String option)
	{
		return By.xpath("//div[text()='" + option + "']");
	}
	 
	// Page Actions
	public void clickApplyForLoanButton()
	{
		ElementWaits.waitToBeInteractable(driver, applyForLoanBtn).click();
	}
	 
	public boolean isConfirmationLoanPannelDisplayed()
	{
		logger.debug("Checking if loan confirmation panel is displayed");
		try {
			return ElementWaits.waitToBeInteractable(driver, confirmationLoanPannel).isDisplayed();
		}catch (Exception e)
		{
			logger.debug("Loan confirmation panel is not displayed");
			return false;
		}
	}
	 
	public String getLoanType()
	{
		return ElementWaits.waitForVisibility(driver, loanType).getText();
	}
	 
	public String getLoanErrorMessage()
	{
		return ElementWaits.waitForVisibility(driver, loanErrorMsg).getText();
	}
	 
	public String getLoanAmount()
	{
		return ElementWaits.waitForVisibility(driver, loanAmount).getText();
	}
	 
	public String getLoanAccount()
	{
		return ElementWaits.waitForVisibility(driver, loanAccount).getText();
	}
	 
	 
	public boolean isConfirmationLoanReceipDisplayed()
	{
		logger.debug("Checking if loan confirmation receipt is displayed");
		try {
			return ElementWaits.waitToBeInteractable(driver, confirmationLoanReceip).isDisplayed();
		}catch (Exception e)
		{
			logger.debug("Loan confirmation receipt is not displayed");
			return false;
		}
	}
	 
	public void clickSubmitButton()
	{
		ElementWaits.waitToBeInteractable(driver, submitButton).click();
	}
	 
	public void clickReviewLoanButton()
	{
		ElementWaits.waitToBeInteractable(driver, reviewLoanBtn).click();
	}
	 
	public void selectDisbursmentAccOption()
	{
		ElementWaits.waitToBeInteractable(driver, disbursemntAccOption).click();
	}
	 
	public void clickSelectLoanTypeButton()
	{
		ElementWaits.waitToBeInteractable(driver, selectLoanTypeBtn).click();
	}
	 
	public void clickSelectDisbursmentAccountButton()
	{
		ElementWaits.waitToBeInteractable(driver, selectDisbursmentAccBtn).click();
	}
	 
	public void enterLoanAmount(String amount)
	{
		ElementWaits.waitToBeInteractable(driver, loanAmountInputBox).sendKeys(amount);
	}

	public void enterLoanReason(String purpose)
	{
		ElementWaits.waitToBeInteractable(driver, loanPurposeInputBox).sendKeys(purpose);
	}
	 
	public void enterInterestRate(String interestRate)
	{
		ElementWaits.waitToBeInteractable(driver, loanInterestRateInputBox).sendKeys(interestRate);
	}
	 
	public void waitForApplyLoantPannelToBeLoaded()
	{
		ElementWaits.waitToBeInteractable(driver, applyLoanPannel);
	}
	 
	public void selectLoanTypeOption(String loanType)
	{
		ElementWaits.waitForVisibility(driver, loanTypeOptionLocator(loanType)).click();
	}
	 
	// Page Behavior
	public void fillLoanDetails(LoanUser loanUser)
	{
		logger.info("Starting to fill loan details");
		
		clickApplyForLoanButton();
		waitForApplyLoantPannelToBeLoaded();
		clickSelectLoanTypeButton();
		selectLoanTypeOption(loanUser.getLoanType());
		enterLoanAmount(loanUser.getLoanAmount());
		enterInterestRate(loanUser.getInterestRate());
		clickSelectDisbursmentAccountButton();
		selectDisbursmentAccOption();
		enterLoanReason(loanUser.getPurpose());
		clickReviewLoanButton();
		
		logger.info("Loan details filled successfully");
	}
	 
	public void fillLoanDetailsWithoutLoanType(LoanUser loanUser)
	{
		logger.info("Starting to fill loan details without selecting loan type");
		
		clickApplyForLoanButton();
		waitForApplyLoantPannelToBeLoaded();
		//clickSelectLoanTypeButton();
		//selectLoanTypeOption(loanUser.getLoanType());
		enterLoanAmount(loanUser.getLoanAmount());
		enterInterestRate(loanUser.getInterestRate());
		clickSelectDisbursmentAccountButton();
		selectDisbursmentAccOption();
		enterLoanReason(loanUser.getPurpose());
		clickReviewLoanButton();
		
		logger.info("Loan details without loan type filled successfully");
	}
	 
	public void fillLoanDetailsWithoutSelectingDisbursementAccount(LoanUser loanUser)
	{
		logger.info("Starting to fill loan details without selecting disbursement account");
		
		clickApplyForLoanButton();
		waitForApplyLoantPannelToBeLoaded();
		clickSelectLoanTypeButton();
		selectLoanTypeOption(loanUser.getLoanType());
		enterLoanAmount(loanUser.getLoanAmount());
		enterInterestRate(loanUser.getInterestRate());
		//clickSelectDisbursmentAccountButton();
		//selectDisbursmentAccOption();
		enterLoanReason(loanUser.getPurpose());
		clickReviewLoanButton();
		
		logger.info("Loan details without disbursement account filled successfully");
	}
}