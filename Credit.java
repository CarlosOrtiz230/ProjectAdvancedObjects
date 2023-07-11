/**
 * This class represents a Credit account.
 */
public class Credit extends Account {
    private double creditLimit;
    /**
     * Constructs a new Credit account with the given account number and credit limit.
     *
     * @param accountNumber The account number.
     * @param creditLimit The credit limit.
     */
    public Credit(String accountNumber, double creditLimit) {
        super(accountNumber);
        this.creditLimit = creditLimit;
    }

    /**
     * Returns the credit limit.
     * @return The credit limit.
     */
    public double getCreditLimit() {
        return creditLimit;
    }

    /**
     * Withdraws the amount from the account
     * @param amount The amount to be withdrawn.
     */
    @Override
    public void withdraw(double amount) {
        if (balance + creditLimit >= amount) {
            balance -= amount;
        } else {
            System.out.println("Withdrawal amount exceeds credit limit.");
        }
    }
}
