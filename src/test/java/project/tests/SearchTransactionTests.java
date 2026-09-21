package project.tests;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import project.base.BaseTest;
import project.expected.SearchTransactionExpected;
import dorrusqa.testdata.TestDataProvider;
import static dorrusqa.z.Z.*;
import dorrusqa.model.*;

public class SearchTransactionTests extends BaseTest {

	@BeforeMethod(alwaysRun = true)
	public void setUpSearchTransaction() {
		loginPage.authentication(standardUser);
		dashboardPage.clickTransactionButton();
	}

	@Test(groups = { "Smoke", "Regression","Transaction" }, description = TC_T_001, 
	dataProvider = "transactionWithValidDetails", dataProviderClass = TestDataProvider.class)
	public void searchTransactionByDescription(TransactionUser validTransaction) {

		// Search for the transaction by description
		transactionPage.enterTransactionDescription(validTransaction.getDescription());
		transactionPage.waitForTablePannelToUpdate();

		// Verify the transaction details
		softAssertion.assertEquals(transactionPage.getTransactionAccountName(), validTransaction.getAccountName());
		softAssertion.assertEquals(transactionPage.getTransactionAmount(), validTransaction.getAmount());
		softAssertion.assertEquals(transactionPage.getTransactionDescriptionName(), validTransaction.getDescription());
		softAssertion.assertAll();
	}

	@Test(groups = { "Regression","Transaction" }, description = TC_T_002, 
	dataProvider = "transactionWithNonExistingDescription", dataProviderClass = TestDataProvider.class)
	public void searchTransactionWithNonExistingDescription(TransactionUser transactionWithNonExistingDescription) {

		// Search for a transaction using a non-existing description
		transactionPage.enterTransactionDescription(transactionWithNonExistingDescription.getDescription());
		transactionPage.waitForTablePannelToUpdate();

		// Verify the transaction search validation
		softAssertion.assertEquals(transactionPage.getTransactionErrorMessage(),SearchTransactionExpected.FAILED_MSG_WITH_NONEXISTENCE_TRANSACTION);
		softAssertion.assertEquals(transactionPage.getPaginationMessage(),SearchTransactionExpected.FAILED_MSG_PAGINATION);
		softAssertion.assertAll();
	}
}