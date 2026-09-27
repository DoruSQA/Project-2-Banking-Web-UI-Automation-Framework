package project.testdata;


import project.testdata.model.BankingAccountUser;
import project.testdata.model.BillPaymentUser;
import project.testdata.model.LoanUser;
import project.testdata.model.LoginUser;
import project.testdata.model.SendMoneyUser;
import project.testdata.model.TransactionUser;
import project.testdata.model.TransferMoneyUser;
import project.utils.DataReader;

public class TestDataFactory {

	public static LoginUser createStandardUser() {

		return DataReader.read("standardUser", LoginUser.class);
	}
	
	public static LoanUser createValidLoanUser() {

		return DataReader.read("validLoanUser", LoanUser.class);
	}
	
	public static LoginUser createFrozendUser() {

		return DataReader.read("frozenUser", LoginUser.class);
	}
	
	public static BankingAccountUser createValidBankingAccount() {

		return DataReader.read("validBankingAccountUser", BankingAccountUser.class);
	}
	
	public static LoginUser createInvalidUser() {

		return DataReader.read("invalidUser", LoginUser.class);
	}
	
	public static LoginUser createLockedoutUser()
	{
		return DataReader.read("lockedOutUser", LoginUser.class);
	}
	
	public static BillPaymentUser createValidBillPaymentUser()
	{
		return DataReader.read("validPaymentUser", BillPaymentUser.class);
	}

	public static TransactionUser createValidTransaction()
	{
		return DataReader.read("transactionUser", TransactionUser.class);
	}
	
	public static SendMoneyUser createValidSendMoneyUser()
	{
		return DataReader.read("sendMoneyUser", SendMoneyUser.class);
	}

	public static TransferMoneyUser createValidTransferMoneyUser()
	{
		return DataReader.read("validTransferMoneyUser", TransferMoneyUser.class);
	}
	public static TransferMoneyUser createPastTransferMoneyUser()
	{
		return DataReader.read("pastTransferMoneyUser", TransferMoneyUser.class);
	}
}
