package project.expected;

public class BillPaymentExpected {
	
	/* Static text */
	public final static String URL = "https://qaplayground.com/bank/bill-pay";

	/* Error messages */
	public final static String FAILED_MSG_WITHOUT_BILLER_SELECTED = "Please select a biller.";
	public final static String FAILED_MSG_WITHOUT_ACCOUNT_SELECTED = "Please select an account.";
	public final static String FAILED_MSG_WITH_EMPTY_AMOUNT = "Please enter a valid amount.";
	public final static String FAILED_MSG_WITH_ZERO_NEGATIVE_AMOUNT_VALUE = "Please enter a valid amount.";
	public final static String FAILED_MSG_PAYMENT_PAST_DATE = "Payment date cannot be in the past.";
	public final static String FAILED_MSG_PAYMENT_INSUFICIENT_FUNDS = "Insufficient funds.";

}
