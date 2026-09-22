package project.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import project.base.BaseTest;
import project.expected.TransferMoneyExpected;
import dorrusqa.testdata.TestDataProvider;
import static dorrusqa.z.Z.*;
import dorrusqa.model.*;

public class TransferFundsTests extends BaseTest {

	@BeforeMethod(alwaysRun = true)
	public void setUpTransferMoney() {
		loginPage.authentication(standardUser);
		dashboardPage.clickTransferFundsButton();
	}
	 
	
	@Test(groups = { "Smoke", "Regression", "Funds Management" }, description = TC_TF_001,
	dataProvider = "transferMoneyWithValidDetails", dataProviderClass = TestDataProvider.class)
	public void transferMoneyWithValidDetails(TransferMoneyUser transferWithValidDetails) {
		
		// Enter valid transfer details
		transferMoneyPage.fillTransferMoneyDetails(transferWithValidDetails);
		
		// Verify the transfer confirmation panel is displayed
		softAssertion.assertTrue(transferMoneyPage.isConfirmationPannelDisplayed());
		
		// Confirm the transfer
		transferMoneyPage.clickConfirmTransferButton();
		
		// Verify the transfer receipt details
		softAssertion.assertEquals(transferMoneyPage.getTransferPayerName(), transferWithValidDetails.getPayerAccount());
		softAssertion.assertEquals(transferMoneyPage.getTransferPayeeName(), transferWithValidDetails.getPayeeAccount());
		softAssertion.assertEquals(transferMoneyPage.getTransferAmount(), "$" + transferWithValidDetails.getAmount() + ".00");
		softAssertion.assertAll();
	}
	
	@Test(groups = {"Regression", "Funds Management" }, description = TC_TF_002,
	dataProvider = "transferMoneyWithEmptyAmount", dataProviderClass = TestDataProvider.class)
	public void transferMoneyWithEmptyAmount(TransferMoneyUser transferWithEmptyAmount) {
		
		// Enter transfer details with an empty amount
		transferMoneyPage.fillTransferMoneyDetails(transferWithEmptyAmount);
		
		// Verify the empty amount validation
		softAssertion.assertEquals(transferMoneyPage.getTransferErrorMsg(), TransferMoneyExpected.FAILED_MSG_WITH_EMPTY_AMOUNT);
		softAssertion.assertFalse(transferMoneyPage.isConfirmationPannelDisplayed());
		softAssertion.assertAll();
		
	}
	
	@Test(groups = {"Regression", "Funds Management" }, description = TC_TF_003, 
	dataProvider = "transferMoneyWithInsufficientFunds", dataProviderClass = TestDataProvider.class)
	public void transferMoneyWithInsufficientFunds(TransferMoneyUser transferWithInsufficientFunds) {

		// Enter transfer details with insufficient funds
		transferMoneyPage.fillTransferMoneyDetails(transferWithInsufficientFunds);
		
		// Verify the transfer confirmation panel is displayed
		softAssertion.assertTrue(transferMoneyPage.isConfirmationPannelDisplayed());
				
		// Confirm the transfer
		transferMoneyPage.clickConfirmTransferButton();
		
		// Verify the insufficient funds validation
		softAssertion.assertTrue(transferMoneyPage.getTransferErrorMsg().contains(TransferMoneyExpected.FAILED_MSG_WITH_INSUFFICIENT_FUNDS));
		softAssertion.assertAll();
	}
	
	@Test(groups = {"Regression", "Funds Management" }, description = TC_TF_004,
	dataProvider = "transferMoneyWithValidDetails", dataProviderClass = TestDataProvider.class)
	public void transferMoneyWithoutSelectingSourceAccount(TransferMoneyUser transferWithValidDetails) {
		
		// Enter transfer details without selecting a source account
		transferMoneyPage.fillTransferMoneyWithoutSourceAccount(transferWithValidDetails);
		
		// Verify the source account validation
		softAssertion.assertEquals(transferMoneyPage.getTransferErrorMsg(), TransferMoneyExpected.FAILED_MSG_WITHOUT_SELECTING_SOURCE_ACCOUNT);
		softAssertion.assertFalse(transferMoneyPage.isConfirmationPannelDisplayed());
		softAssertion.assertAll();
	}
	
	@Test(groups = {"Regression", "Funds Management" }, description = TC_TF_005,
	dataProvider = "transferMoneyWithValidDetails", dataProviderClass = TestDataProvider.class)
	public void transferMoneyWithoutSelectingDestinationAccount(TransferMoneyUser transferWithValidDetails) {

		// Enter transfer details without selecting a destination account
		transferMoneyPage.fillTransferMoneyWithoutDestinationAccount(transferWithValidDetails);
				
		// Verify the destination account validation
		softAssertion.assertEquals(transferMoneyPage.getTransferErrorMsg(), TransferMoneyExpected.FAILED_MSG_WITHOUT_SELECTING_DESTINATION_ACCOUNT);
		softAssertion.assertFalse(transferMoneyPage.isConfirmationPannelDisplayed());
		softAssertion.assertAll();
	}

	
	@Test(groups = {"Regression", "Funds Management" }, description = TC_TF_006,
	dataProvider = "transferMoneyWithNegativeAmount", dataProviderClass = TestDataProvider.class)
	public void transferMoneyWithNegativeAmount(TransferMoneyUser transferWithNegativeAmount) {
		
		// Enter transfer details with a negative amount
		transferMoneyPage.fillTransferMoneyDetails(transferWithNegativeAmount);
				
		// Verify the negative amount validation
		softAssertion.assertEquals(transferMoneyPage.getTransferErrorMsg(), TransferMoneyExpected.FAILED_MSG_WITH_NEGATIVE_AMOUNT);
		softAssertion.assertFalse(transferMoneyPage.isConfirmationPannelDisplayed());
		softAssertion.assertAll();
	}
	
	@Test(groups = {"Regression", "Funds Management" }, description = TC_TF_007,
	dataProvider = "transferMoneyWithNegativeAmount", dataProviderClass = TestDataProvider.class)
	public void transferMoneyWithPastDate(TransferMoneyUser transferWithPastDate) {

		// Enter transfer details with a past date
		transferMoneyPage.fillTransferMoneyDetails(transferWithPastDate);
		
		// Verify the past date validation
		softAssertion.assertEquals(transferMoneyPage.getTransferErrorMsg(), TransferMoneyExpected.FAILED_MSG_WITH_PAST_DATE);
		softAssertion.assertFalse(transferMoneyPage.isConfirmationPannelDisplayed());
		softAssertion.assertAll();
	}
	
}

