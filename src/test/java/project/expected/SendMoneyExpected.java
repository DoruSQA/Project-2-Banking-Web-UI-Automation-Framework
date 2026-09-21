package project.expected;

public class SendMoneyExpected {

	/* Static text */ 
	public final static String URL = "https://qaplayground.com/bank/send-money";
	
	/* Error messages */
	public final static String FAILED_MSG_WITH_EMPTY_AMOUNT = "Please enter a valid amount.";
	public final static String FAILED_MSG_WITH_INSUFFICIENT_FUNDS ="Insufficient funds";
	public final static String FAILED_MSG_WITH_EMPTY_PAYER_ACCOUNT ="Please select an account.";
	public final static String FAILED_MSG_WITH_EMPTY_PAYEE_ACCOUNT ="Please select a payee.";
	public final static String FAILED_MSG_WITH_NEGATIVE_AMOUNT = "Please enter a valid amount.";
	
	
	public final static String FAILED_MSG_WITH_EMPTY_BANKING_ACCOUNT_TYPE ="Please select an account type.";
	public final static String FAILED_MSG_WITHOUT_ACCEPTING_TERMS ="Please accept the terms and conditions to continue.";
	
}
