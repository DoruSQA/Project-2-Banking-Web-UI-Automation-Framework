package project.tests;

import org.testng.Assert;
import static dorrusqa.z.Z.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import dorrusqa.testdata.TestDataProvider;
import project.base.BaseTest;
import project.expected.BillPaymentExpected;
import dorrusqa.model.*;


public class BillPaymentTests extends BaseTest {

	@BeforeMethod(alwaysRun = true)
	public void setUpBillPayment() {
		loginPage.authentication(standardUser);
		dashboardPage.clickBillPayButton();
	}

	@Test(groups = { "Smoke", "Regression","Bill Payments" }, description = TC_BP_001, 
	dataProvider = "billPaymentWithValidDetails", dataProviderClass = TestDataProvider.class)
	public void payBillWithValidDetails(BillPaymentUser validBillPayment) {

		// Enter valid bill payment details
		billPaymentPage.fillPaymentBillDetails(validBillPayment);

		// Confirm the bill payment
		billPaymentPage.clickConfirmPaymentButton();

		// Verify the payment confirmation panel and payment details
		Assert.assertTrue(billPaymentPage.isPaymentConfirmationPannelDisplayed());
		softAssertion.assertEquals(billPaymentPage.getSelectedPaymentAccount(),validBillPayment.getBankingAccountName());
		softAssertion.assertEquals(billPaymentPage.getSelectedPaymentBiller(), validBillPayment.getBillerEntity());
		softAssertion.assertEquals(billPaymentPage.getSelectedPaymentAmount(),"$" + validBillPayment.getAmount() + ".00");
		softAssertion.assertAll();
	}

	@Test(groups = { "Regression","Bill Payments" }, description = TC_BP_002, 
	dataProvider = "billPaymentWithoutSourceAccount", dataProviderClass = TestDataProvider.class)
	public void payBillWithoutSelectingSourceAccount(BillPaymentUser validBillPayment) {

		// Enter bill payment details without selecting a source account
		billPaymentPage.fillPaymentBillDetailsWithoutProvidingBankingAccount(validBillPayment);

		// Verify the source account validation
		softAssertion.assertEquals(billPaymentPage.getBillPayErrorMessage(),BillPaymentExpected.FAILED_MSG_WITHOUT_ACCOUNT_SELECTED);
		softAssertion.assertFalse(billPaymentPage.isPaymentConfirmationPannelDisplayed());
		softAssertion.assertAll();
	}

	@Test(groups = { "Regression","Bill Payments" }, description = TC_BP_003, 
	dataProvider = "billPaymentWithoutReceiverAccount", dataProviderClass = TestDataProvider.class)
	public void payBillWithoutSelectingReceiverAccount(BillPaymentUser validBillPayment) {

		// Enter bill payment details without selecting a biller
		billPaymentPage.fillPaymentBillDetailsWithoutProvidingReceiverAccount(validBillPayment);

		// Verify the biller validation
		softAssertion.assertEquals(billPaymentPage.getBillPayErrorMessage(),BillPaymentExpected.FAILED_MSG_WITHOUT_BILLER_SELECTED);
		softAssertion.assertFalse(billPaymentPage.isPaymentConfirmationPannelDisplayed());
		softAssertion.assertAll();
	}

	@Test(groups = { "Regression","Bill Payments" }, description = TC_BP_004, 
	dataProvider = "billPaymentWithEmptyAmount", dataProviderClass = TestDataProvider.class)
	public void payBillWithEmptyAmount(BillPaymentUser billPaymentWithEmptyAmount) {

		// Enter bill payment details with an empty amount
		billPaymentPage.fillPaymentBillDetails(billPaymentWithEmptyAmount);

		// Verify the empty amount validation
		softAssertion.assertEquals(billPaymentPage.getBillPayErrorMessage(),BillPaymentExpected.FAILED_MSG_WITH_EMPTY_AMOUNT);
		softAssertion.assertFalse(billPaymentPage.isPaymentConfirmationPannelDisplayed());
		softAssertion.assertAll();
	}

	@Test(groups = { "Regression","Bill Payments" }, description = TC_BP_005, 
	dataProvider = "billPaymentWithNegativeAmount", dataProviderClass = TestDataProvider.class)
	public void payBillWithNegativeAmount(BillPaymentUser billPaymentWithNegativeAmount) {

		// Enter bill payment details with a negative amount
		billPaymentPage.fillPaymentBillDetails(billPaymentWithNegativeAmount);

		// Verify the negative amount validation
		softAssertion.assertEquals(billPaymentPage.getBillPayErrorMessage(),BillPaymentExpected.FAILED_MSG_WITH_ZERO_NEGATIVE_AMOUNT_VALUE);
		softAssertion.assertFalse(billPaymentPage.isPaymentConfirmationPannelDisplayed());
		softAssertion.assertAll();
	}

	@Test(groups = { "Regression","Bill Payments" }, description = TC_BP_006, 
	dataProvider = "billPaymentWithPastDate", dataProviderClass = TestDataProvider.class)
	public void payBillWithPastDate(BillPaymentUser billPaymentWithPastDate) {

		// Enter bill payment details with a past payment date
		billPaymentPage.fillPaymentBillDetails(billPaymentWithPastDate);

		// Verify the past date validation
		softAssertion.assertEquals(billPaymentPage.getBillPayErrorMessage(),BillPaymentExpected.FAILED_MSG_PAYMENT_PAST_DATE);
		softAssertion.assertFalse(billPaymentPage.isPaymentConfirmationPannelDisplayed());
		softAssertion.assertAll();
	}

	@Test(groups = { "Regression","Bill Payments" }, description = TC_BP_007, 
	dataProvider = "billPaymentWithInsufficientFunds", dataProviderClass = TestDataProvider.class)
	public void payBillWithInsufficientFunds(BillPaymentUser billPaymentWithInsufficientFunds) {

		// Enter bill payment details with an amount exceeding the available balance
		billPaymentPage.fillPaymentBillDetails(billPaymentWithInsufficientFunds);

		// Submit the bill payment
		billPaymentPage.clickConfirmPaymentButton();

		// Verify the insufficient funds validation
		softAssertion.assertTrue(billPaymentPage.getBillPayErrorMessage().contains(BillPaymentExpected.FAILED_MSG_PAYMENT_INSUFICIENT_FUNDS));
		softAssertion.assertFalse(billPaymentPage.isPaymentConfirmationPannelDisplayed());
		softAssertion.assertAll();
	}
}