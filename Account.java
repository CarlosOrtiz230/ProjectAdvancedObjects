import java.util.List;

/**
 * This class represents a general bank account. 
 */
public abstract class Account {

    /**
     * The current balance of the account.
     */
    protected double balance;

    /**
     * The unique identifier of the account.
     */
    protected String accountNumber;

    /**
     * Constructs a new Account with the given account number.
     * @param accountNumber The unique number for this account.
     */
    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }

    /**
     * Returns the current balance of the account.
     * @return The current balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Returns the account number of the account.
     * @return The account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Adds the given amount to the account's balance.
     *
     * @param amount The amount to be deposited.
     */

    /**
     * Abstract method to be implemented in subclasses for withdrawing money from the account.
     *
     * @param amount The amount to be withdrawn.
     */
    public abstract void withdraw(double amount);
    
    public abstract void deposit(double amount);

    public abstract void payToThirdParty(List<Customer> dataBase,String name, String lastname, String accountNumber,double amount);

}
