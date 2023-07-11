/**
 * This class represents a general bank account. 
 * It is designed to be subclassed to create specific types of bank accounts.
 * Each account has an account number and a balance.
 */
public abstract class Account {

    /**
     * The current balance of the account.
     */
    protected double balance;

    /**
     * The unique identifier of the account.
     */
    private String accountNumber;

    /**
     * Constructs a new Account with the given account number.
     * Initial balance is set to 0.0.
     *
     * @param accountNumber The unique identifier for this account.
     */
    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }

    /**
     * Returns the current balance of the account.
     *
     * @return The current balance of the account.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Returns the account number of the account.
     *
     * @return The account number of the account.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Adds the given amount to the account's balance.
     *
     * @param amount The amount to be deposited.
     */
    public void deposit(double amount) {
        balance += amount;
    }

    /**
     * Abstract method to be implemented in subclasses for withdrawing money from the account.
     * Different types of accounts may have different rules for withdrawal.
     *
     * @param amount The amount to be withdrawn.
     */
    public abstract void withdraw(double amount);
}
