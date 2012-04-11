package no.ntnu.idi.simplebank;

public class Account {

    private int accountId;
    private final String accountName;
    private final String accountType;
    private final double money;
    private final User accountOwner;

    public Account(int accountId, String accountName, String accountType, double money, User accountOwner) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountType = accountType;
        this.money = money;
        this.accountOwner = accountOwner;
    }

    public Account(String accountName, String accountType, double money, User accountOwner) {
        this.accountName = accountName;
        this.accountType = accountType;
        this.money = money;
        this.accountOwner = accountOwner;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getMoney() {
        return money;
    }

    public User getAccountOwner() {
        return accountOwner;
    }

}
