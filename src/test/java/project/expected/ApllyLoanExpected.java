package project.expected;

public class ApllyLoanExpected {

	/* Static text */ 
	public final static String URL = "https://qaplayground.com/bank/apply-loan";
	
	/* Error messages */
	public final static String FAILED_MSG_WITHOUT_LOAN_TYPE = "Please select a loan type.";
	public final static String FAILED_MSG_WITH_EMPTY_LOAN_AMOUNT ="Please enter a valid loan amount.";
	public final static String FAILED_MSG_WITH_NEGATIVE_LOAN_AMOUNT ="Please enter a valid loan amount.";
	public final static String FAILED_MSG_WITH_NEGATIVE_INTEREST_RATE ="Please enter a valid interest rate.";
	public final static String FAILED_MSG_WITH_LOAN_AMOUNT_EXCEEDING_MAX_VALUE ="Loan amount cannot exceed $250,000.";
	public final static String FAILED_MSG_WITHOUT_SELECTING_DISBURSEMENT_ACC ="Please select a disbursement account.";
	
	
}
