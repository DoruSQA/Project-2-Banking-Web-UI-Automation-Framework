package project.expected;

public class TransferMoneyExpected {
	
	/* Static text */ 
	public final static String URL = "https://qaplayground.com/bank/transfer";
	
	/* Error messages */
	public final static String FAILED_MSG_WITH_EMPTY_AMOUNT = "Please enter a valid amount.";
	public final static String FAILED_MSG_WITH_INSUFFICIENT_FUNDS = "Insufficient funds.";
	public final static String FAILED_MSG_WITH_NEGATIVE_AMOUNT = "Please enter a valid amount.";
	public final static String FAILED_MSG_WITHOUT_SELECTING_SOURCE_ACCOUNT ="Please select a From account.";
	public final static String FAILED_MSG_WITHOUT_SELECTING_DESTINATION_ACCOUNT ="Please select a To account.";
	public final static String FAILED_MSG_WITH_PAST_DATE ="Transfer date cannot be in the past.";
	

}
