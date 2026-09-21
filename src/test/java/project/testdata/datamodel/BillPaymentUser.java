package project.testdata.datamodel;

public class BillPaymentUser 
{
	
	
	public String getBankingAccountName() {
		return bankingAccountName;
	}
	public void setBankingAccountName(String bankingAccountName) {
		this.bankingAccountName = bankingAccountName;
	}
	public String getBillerEntity() {
		return billerEntity;
	}
	public void setBillerEntity(String billerEntity) {
		this.billerEntity = billerEntity;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	private String bankingAccountName;
	private String billerEntity;
	private String amount;
	private String date;
	


	

}
