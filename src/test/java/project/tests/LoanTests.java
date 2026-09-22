package project.tests;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;
import static dorrusqa.z.Z.*;
import project.base.BaseTest;
import project.expected.ApllyLoanExpected;
import dorrusqa.testdata.TestDataProvider;
import dorrusqa.model.*;

public class LoanTests extends BaseTest {

	@BeforeMethod(alwaysRun = true)
	public void setUpLoan() {
		loginPage.authentication(standardUser);
		dashboardPage.clickApplyLoan();
	}

	@Test(groups = { "Smoke", "Regression","Loan Management" }, description = TC_LOAN_001, 
	dataProvider = "loanWithValidDetails", dataProviderClass = TestDataProvider.class)
	public void applyForLoanWithValidDetails(LoanUser validLoan) {

		// Enter valid loan details
		applyLoanPage.fillLoanDetails(validLoan);

		// Submit the loan application
		applyLoanPage.clickSubmitButton();

		// Verify the loan confirmation receipt and details
		softAssertion.assertTrue(applyLoanPage.isConfirmationLoanReceipDisplayed());
		softAssertion.assertEquals(applyLoanPage.getLoanAccount(), validLoan.getDisbursementAcc());
		softAssertion.assertEquals(applyLoanPage.getLoanAmount(), "$" + validLoan.getLoanAmount() + ".00");
		softAssertion.assertEquals(applyLoanPage.getLoanType(), validLoan.getLoanType());
		softAssertion.assertAll();
	}

	@Test(groups = { "Regression","Loan Management" }, description = TC_LOAN_002, dataProvider = "loanWithoutLoanType", 
	dataProviderClass = TestDataProvider.class)
	public void applyForLoanWithoutSelectingLoanType(LoanUser loanWithoutLoanType) {

		// Enter loan details without selecting a loan type
		applyLoanPage.fillLoanDetailsWithoutLoanType(loanWithoutLoanType);

		// Verify the loan type validation
		softAssertion.assertFalse(applyLoanPage.isConfirmationLoanReceipDisplayed());
		softAssertion.assertEquals(applyLoanPage.getLoanErrorMessage(), ApllyLoanExpected.FAILED_MSG_WITHOUT_LOAN_TYPE,);
		softAssertion.assertAll();
	}

	@Test(groups = { "Regression","Loan Management" }, description = TC_LOAN_003, 
	dataProvider = "loanWithEmptyAmount", dataProviderClass = TestDataProvider.class)
	public void applyForLoanWithEmptyLoanAmount(LoanUser loanWithEmptyAmount) {

		// Enter loan details with an empty loan amount
		applyLoanPage.fillLoanDetails(loanWithEmptyAmount);

		// Verify the empty loan amount validation
		softAssertion.assertFalse(applyLoanPage.isConfirmationLoanReceipDisplayed());
		softAssertion.assertEquals(applyLoanPage.getLoanErrorMessage(),ApllyLoanExpected.FAILED_MSG_WITH_EMPTY_LOAN_AMOUNT);
		softAssertion.assertAll();
	}

	@Test(groups = { "Regression","Loan Management" }, description = TC_LOAN_004, 
	dataProvider = "loanWithNegativeAmount", dataProviderClass = TestDataProvider.class)
	public void applyForLoanWithNegativeLoanAmount(LoanUser loanWithNegativeAmount) {

		// Enter loan details with a negative loan amount
		applyLoanPage.fillLoanDetails(loanWithNegativeAmount);

		// Verify the negative loan amount validation
		softAssertion.assertFalse(applyLoanPage.isConfirmationLoanReceipDisplayed());
		softAssertion.assertEquals(applyLoanPage.getLoanErrorMessage(),ApllyLoanExpected.FAILED_MSG_WITH_NEGATIVE_LOAN_AMOUNT);
		softAssertion.assertAll();
	}

	@Test(groups = { "Regression","Loan Management" }, description = TC_LOAN_005, 
	dataProvider = "loanWithNegativeInterestRate", dataProviderClass = TestDataProvider.class)
	public void applyForLoanWithNegativeInterestRate(LoanUser loanWithNegativeInterestRate) {

		// Enter loan details with a negative interest rate
		applyLoanPage.fillLoanDetails(loanWithNegativeInterestRate);

		// Verify the negative interest rate validation
		softAssertion.assertFalse(applyLoanPage.isConfirmationLoanReceipDisplayed());
		softAssertion.assertEquals(applyLoanPage.getLoanErrorMessage(),ApllyLoanExpected.FAILED_MSG_WITH_NEGATIVE_INTEREST_RATE);
		softAssertion.assertAll();
	}

	@Test(groups = { "Regression","Loan Management" }, description = TC_LOAN_006, 
	dataProvider = "loanWithoutDisbursementAccount", dataProviderClass = TestDataProvider.class)
	public void applyForLoanWithoutSelectingDisbursementAccount(LoanUser loanWithoutDisbursementAccount) {

		// Enter loan details without selecting a disbursement account
		applyLoanPage.fillLoanDetailsWithoutSelectingDisbursementAccount(loanWithoutDisbursementAccount);

		// Verify the disbursement account validation
		softAssertion.assertFalse(applyLoanPage.isConfirmationLoanReceipDisplayed());
		softAssertion.assertEquals(applyLoanPage.getLoanErrorMessage(),ApllyLoanExpected.FAILED_MSG_WITHOUT_SELECTING_DISBURSEMENT_ACC);
		softAssertion.assertAll();
	}

	@Test(groups = { "Regression","Loan Management" }, description = TC_LOAN_007, 
	dataProvider = "loanWithAmountAboveMaximumLimit", dataProviderClass = TestDataProvider.class)
	public void applyForLoanWithAmountAboveMaximumLimit(LoanUser loanWithAmountAboveMaximumLimit) {

		// Enter loan details with an amount above the maximum allowed limit
		applyLoanPage.fillLoanDetails(loanWithAmountAboveMaximumLimit);

		// Verify the maximum loan amount validation
		softAssertion.assertFalse(applyLoanPage.isConfirmationLoanReceipDisplayed());
		softAssertion.assertEquals(applyLoanPage.getLoanErrorMessage(),ApllyLoanExpected.FAILED_MSG_WITH_LOAN_AMOUNT_EXCEEDING_MAX_VALUE);
		softAssertion.assertAll();
	}

}
