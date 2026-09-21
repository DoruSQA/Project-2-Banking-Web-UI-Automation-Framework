package project.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import project.base.BaseTest;
import project.expected.SendMoneyExpected;
import dorrusqa.testdata.TestDataProvider;
import static dorrusqa.z.Z.*;
import dorrusqa.model.*;

public class SendMoneyTests extends BaseTest {

	@BeforeMethod(alwaysRun = true)
	public void setUpSendMoney() {
		loginPage.authentication(standardUser);
		dashboardPage.clickSendMoneyButton();
	}
	 
	@Test(groups = { "Smoke", "Regression", "Funds Management" }, description = TC_SM_001, 
	dataProvider = "sendMoneyWithValidDetails", dataProviderClass = TestDataProvider.class)
	public void sendMoneyWithValidDetails(SendMoneyUser sendMoneyWithValidDetails) {
		 
		// Enter valid send money details
		sendMoneyPage.fillSendMoneyDetails(sendMoneyWithValidDetails);
		 
		// Verify the confirmation panel is displayed
		Assert.assertTrue(sendMoneyPage.isConfirmationPannelDisplayed());
		 
		// Accept the send money confirmation
		sendMoneyPage.clickAcceptConfirmationButton();
		 
		// Verify the send money receipt and transaction details
		softAssertion.assertTrue(sendMoneyPage.isSendMoneyReceipDisplayed(), "Receipt is not displayed");
		softAssertion.assertEquals(sendMoneyPage.getSendMoneyAccountName(), sendMoneyWithValidDetails.getAccount());
		softAssertion.assertEquals(sendMoneyPage.getSendMoneyPayeeName(), sendMoneyWithValidDetails.getPayee());
		softAssertion.assertEquals(sendMoneyPage.getSendMoneyAmount(), "$" + sendMoneyWithValidDetails.getAmount() + ".00");
		softAssertion.assertAll();
	}
	 
	@Test(groups = {"Regression", "Funds Management" }, description = TC_SM_002, 
	dataProvider = "sendMoneyWithEmptyAmount", dataProviderClass = TestDataProvider.class)
	public void sendMoneyWithEmptyAmount(SendMoneyUser sendMoneyWithEmptyAmount) {
		 
		// Enter send money details with an empty amount
		sendMoneyPage.fillSendMoneyDetails(sendMoneyWithEmptyAmount);
		 
		// Verify the empty amount validation
		softAssertion.assertEquals(sendMoneyPage.getSendMoneyErrorMsg(), SendMoneyExpected.FAILED_MSG_WITH_EMPTY_AMOUNT);
		softAssertion.assertFalse(sendMoneyPage.isConfirmationPannelDisplayed());
		softAssertion.assertAll();
	}
	 
	@Test(groups = {"Regression", "Funds Management" }, description = TC_SM_003, 
	dataProvider = "sendMoneyWithInsufficientFunds", dataProviderClass = TestDataProvider.class)
	public void sendMoneyWithInsufficientFunds(SendMoneyUser sendMoneyWithInsufficientFunds) {
		 
		// Enter send money details with insufficient funds
		sendMoneyPage.fillSendMoneyDetails(sendMoneyWithInsufficientFunds);
		sendMoneyPage.clickAcceptConfirmationButton();
				 
		// Verify the insufficient funds validation
		softAssertion.assertTrue(sendMoneyPage.getSendMoneyErrorMsg().contains(SendMoneyExpected.FAILED_MSG_WITH_INSUFFICIENT_FUNDS));
		softAssertion.assertAll();
				 
	}
	 
	@Test(groups = {"Regression", "Funds Management" }, description = TC_SM_004, 
	dataProvider = "sendMoneyWithValidDetails", dataProviderClass = TestDataProvider.class)
	public void sendMoneyWithoutSelectingPayerAccount(SendMoneyUser sendMoneyWithValidDetails) {

		// Enter send money details without selecting a payer account
		sendMoneyPage.fillSendMoneyDetailsWithoutPayerAccount(sendMoneyWithValidDetails);
		 
		// Verify the payer account validation
		softAssertion.assertEquals(sendMoneyPage.getSendMoneyErrorMsg(), SendMoneyExpected.FAILED_MSG_WITH_EMPTY_PAYER_ACCOUNT);
		softAssertion.assertFalse(sendMoneyPage.isConfirmationPannelDisplayed());
		softAssertion.assertAll();
	}
	 
	@Test(groups = {"Regression", "Funds Management" }, description = TC_SM_005, 
	dataProvider = "sendMoneyWithValidDetails", dataProviderClass = TestDataProvider.class)
	public void sendMoneyWithoutSelectingPayeeAccount(SendMoneyUser sendMoneyWithValidDetails) {

		// Enter send money details without selecting a payee account
		sendMoneyPage.fillSendMoneyDetailsWithoutPayeeAccount(sendMoneyWithValidDetails);
				 
		// Verify the payee account validation
		softAssertion.assertEquals(sendMoneyPage.getSendMoneyErrorMsg(), SendMoneyExpected.FAILED_MSG_WITH_EMPTY_PAYEE_ACCOUNT);
		softAssertion.assertFalse(sendMoneyPage.isConfirmationPannelDisplayed());
		softAssertion.assertAll();
	}
	 
	@Test(groups = {"Regression", "Funds Management" }, description = TC_SM_006, 
	dataProvider = "sendMoneyWithNegativeAmount", dataProviderClass = TestDataProvider.class)
	public void sendMoneyWithNegativeAmount(SendMoneyUser sendMoneyWithNegativeAmount) {
		 
		// Enter send money details with a negative amount
		sendMoneyPage.fillSendMoneyDetails(sendMoneyWithNegativeAmount);
				 
		// Verify the negative amount validation
		softAssertion.assertEquals(sendMoneyPage.getSendMoneyErrorMsg(), SendMoneyExpected.FAILED_MSG_WITH_NEGATIVE_AMOUNT);
		softAssertion.assertFalse(sendMoneyPage.isConfirmationPannelDisplayed());
		softAssertion.assertAll();
	}
	 
}

