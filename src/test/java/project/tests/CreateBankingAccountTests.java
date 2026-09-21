package project.tests;

import org.testng.Assert;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static dorrusqa.z.Z.*;
import project.base.BaseTest;
import project.expected.AccountExpected;
import dorrusqa.testdata.TestDataProvider;
import dorrusqa.model.*;

public class CreateBankingAccountTests extends BaseTest {

	@BeforeMethod(alwaysRun = true)
	public void setUpCreateBankingAccount() {
		loginPage.authentication(standardUser);
		dashboardPage.clickAccountsButton();
		myAccountPage.clickAddBankingAccount();
	}

	@Test(groups = { "Smoke", "Regression","Banking Account" }, description = TC_BA_001, 
	dataProvider = "bankingAccountWithValidDetails", dataProviderClass = TestDataProvider.class)
	public void createBankingAccountWithValidDetails(BankingAccountUser validBankingAccount) {

		// Capture the current number of banking accounts
		int prevNumberOfBankingAccounts = myAccountPage.getNumberOfBankingAccount();

		// Enter valid banking account details
		myAccountPage.fillBankingAccountDetails(validBankingAccount);

		// Create the banking account
		myAccountPage.clickCreateBankingAcoount();

		// Verify the new banking account was created with the expected details
		softAssertion.assertEquals(prevNumberOfBankingAccounts, myAccountPage.getNumberOfBankingAccount() - 1);
		softAssertion.assertEquals(myAccountPage.getCreatedBankingAccountName(), validBankingAccount.getAccountName());
		softAssertion.assertEquals(myAccountPage.getCreatedBankingAccountType(), validBankingAccount.getAccountType());
		softAssertion.assertEquals(myAccountPage.getCreatedBankingAccountBalance(),"$" + validBankingAccount.getStartingBalance() + ".00");
		softAssertion.assertAll();
	}

	@Test(groups = { "Regression","Banking Account" }, description = TC_BA_002, 
	dataProvider = "bankingAccountWithEmptyAccountName", dataProviderClass = TestDataProvider.class)
	public void createBankingAccountWithEmptyAccountName(BankingAccountUser bankingAccountWithEmptyName) {

		// Enter banking account details with an empty account name
		int prevNumberOfBankingAccounts = myAccountPage.getNumberOfBankingAccount();
		myAccountPage.fillBankingAccountDetails(bankingAccountWithEmptyName);
		myAccountPage.clickCreateBankingAcoount();

		// Verify the empty account name validation
		Assert.assertEquals(myAccountPage.getBankingAccountErrorMsg(),AccountExpected.FAILED_MSG_WITH_EMPTY_BANKING_USERNAME);

		// Cancel the account creation form
		myAccountPage.clickCancelFormBtn();

		// Verify that no banking account was created
		softAssertion.assertEquals(prevNumberOfBankingAccounts, myAccountPage.getNumberOfBankingAccount());
		softAssertion.assertAll();
	}

	@Test(groups = { "Regression","Banking Account" }, description = TC_BA_003, 
	dataProvider = "bankingAccountWithEmptyAccountType", dataProviderClass = TestDataProvider.class)
	public void createBankingAccountWithEmptyAccountType(BankingAccountUser validBankingAccount) {

		// Capture the current number of banking accounts
		int prevNumberOfBankingAccounts = myAccountPage.getNumberOfBankingAccount();

		// Enter banking account details without selecting an account type
		myAccountPage.fillBankingAccountDetailsWithoutAccountTypeOption(validBankingAccount);
		myAccountPage.clickCreateBankingAcoount();

		// Verify the account type validation
		Assert.assertEquals(myAccountPage.getBankingAccountErrorMsg(),AccountExpected.FAILED_MSG_WITH_EMPTY_BANKING_ACCOUNT_TYPE);

		// Cancel the account creation form
		myAccountPage.clickCancelFormBtn();

		// Verify that no banking account was created
		softAssertion.assertEquals(prevNumberOfBankingAccounts, myAccountPage.getNumberOfBankingAccount());
		softAssertion.assertAll();
	}

	@Test(groups = { "Regression","Banking Account" }, description = TC_BA_004, 
	dataProvider = "bankingAccountWithoutAcceptingTerms", dataProviderClass = TestDataProvider.class)
	public void createBankingAccountWithoutAcceptingTerms(BankingAccountUser validBankingAccount) {

		// Capture the current number of banking accounts
		int prevNumberOfBankingAccounts = myAccountPage.getNumberOfBankingAccount();

		// Enter banking account details without accepting the terms
		myAccountPage.fillBankingAccountDetailsWithoutAcceptingTerms(validBankingAccount);
		myAccountPage.clickCreateBankingAcoount();

		// Verify the terms and conditions validation
		Assert.assertEquals(myAccountPage.getBankingAccountErrorMsg(),AccountExpected.FAILED_MSG_WITHOUT_ACCEPTING_TERMS);

		// Cancel the account creation form
		myAccountPage.clickCancelFormBtn();

		// Verify that no banking account was created
		softAssertion.assertEquals(prevNumberOfBankingAccounts, myAccountPage.getNumberOfBankingAccount());
		softAssertion.assertAll();
	}

	@Test(groups = { "Regression","Banking Account" }, description = TC_BA_005, 
	dataProvider = "bankingAccountWithSpecialCharactersInAccountName", dataProviderClass = TestDataProvider.class)
	public void createBankingAccountWithSpecialCharactersInAccountName(
			BankingAccountUser bankingAccountWithSpecialCharacters) {

		// Capture the current number of banking accounts
		int prevNumberOfBankingAccounts = myAccountPage.getNumberOfBankingAccount();

		// Enter banking account details with special characters in the account name
		myAccountPage.fillBankingAccountDetails(bankingAccountWithSpecialCharacters);
		myAccountPage.clickCreateBankingAcoount();

		// Verify that the banking account was not created
		softAssertion.assertEquals(prevNumberOfBankingAccounts, myAccountPage.getNumberOfBankingAccount());
		softAssertion.assertAll();
	}

	@Test(groups = { "Regression","Banking Account" }, description = TC_BA_006, 
	dataProvider = "bankingAccountWithAccountNameExceedingMaximumLength", dataProviderClass = TestDataProvider.class)
	public void createBankingAccountWithAccountNameExceedingMaximumLength(
			BankingAccountUser bankingAccountWithLongName) {

		// Capture the current number of banking accounts
		int prevNumberOfBankingAccounts = myAccountPage.getNumberOfBankingAccount();

		// Enter banking account details with an account name exceeding the maximum length
		myAccountPage.fillBankingAccountDetails(bankingAccountWithLongName);
		myAccountPage.clickCreateBankingAcoount();

		// Verify that the banking account was not created
		softAssertion.assertEquals(prevNumberOfBankingAccounts, myAccountPage.getNumberOfBankingAccount());
		softAssertion.assertAll();
	}
}
