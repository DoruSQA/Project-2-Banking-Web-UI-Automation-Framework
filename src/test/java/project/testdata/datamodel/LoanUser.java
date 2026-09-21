package project.testdata.datamodel;

public class LoanUser 
{
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public String getDisbursementAcc() {
		return disbursementAcc;
	}
	public void setDisbursementAcc(String disbursementAcc) {
		this.disbursementAcc = disbursementAcc;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	private String loanType;
	private String disbursementAcc;
	private String purpose;
	private String loanAmount;
	public String getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}
	
	private String interestRate;
	public String getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	

	

}
