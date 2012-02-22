package no.ntnu.idi.simplebank;

public class Account {
	
	private String accountName;
	private String accountType;
	private double money;
	private User accountOwner;
	
	public Account() {}
	
	public Account(String accountName, String accountType, double money, User accountOwner) {
		this.accountName = accountName;
		this.accountType = accountType;
		this.money = money;
		this.accountOwner = accountOwner;
	}
	
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public User getAccountOwner() {
		return accountOwner;
	}
	public void setAccountOwner(User accountOwner) {
		this.accountOwner = accountOwner;
	}
}
