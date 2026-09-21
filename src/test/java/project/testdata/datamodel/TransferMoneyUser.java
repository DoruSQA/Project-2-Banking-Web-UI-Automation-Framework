package project.testdata.datamodel;

public class TransferMoneyUser 
{
	
	public String getPayerAccount() {
		return payerAccount;
	}
	public void setPayerAccount(String payerAccount) {
		this.payerAccount = payerAccount;
	}
	public String getPayeeAccount() {
		return payeeAccount;
	}
	public void setPayeeAccount(String payeeAccount) {
		this.payeeAccount = payeeAccount;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDateOption() {
		return dateOption;
	}
	public void setDateOption(String dateOption) {
		this.dateOption = dateOption;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	private String payerAccount;
	private String payeeAccount;
	private String amount;
	private String dateOption;
	private String date;
	


	

}
