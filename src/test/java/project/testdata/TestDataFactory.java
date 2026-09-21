package project.testdata;


import project.testdata.datamodel.BankingAccountUser;
import project.testdata.datamodel.BillPaymentUser;
import project.testdata.datamodel.LoanUser;
import project.testdata.datamodel.LoginUser;
import project.testdata.datamodel.SendMoneyUser;
import project.testdata.datamodel.TransactionUser;
import project.testdata.datamodel.TransferMoneyUser;
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
