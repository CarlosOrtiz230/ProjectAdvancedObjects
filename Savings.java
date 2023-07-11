/**
 * A class representing a savings account that extends Account.
 */
public class Savings extends Account {

    /**
     * Constructs a new instance of the Savings class with the specified account number.
     *
     * @param accountNumber the account number associated with the savings account
     */
    public Savings(String accountNumber) {
        super(accountNumber);
    }

    /**
     * Overrides the withdraw method from the parent class (Account) to allow withdrawals
     * from the savings account, if the balance is sufficient.
     *
     * @param amount the amount to be withdrawn from the account
     */
    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient balance.");
        }
    }
    
    /**
     * Overrides the deposit method from the parent class (Account) to allow deposits
     * into the savings account, if the amount is non-negative.
     *
     * @param amount the amount to be deposited into the account
     */
    @Override
    public void deposit(double amount) {
        if (amount >= 0) {
            balance += amount;
        } else {
            System.out.println("Error with deposit.");
        }
    }
}
