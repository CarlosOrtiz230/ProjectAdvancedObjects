/**
 * This class represents a Checking account, which is a type of Account.
 */
public class Checking extends Account {

    /**
     * Constructs a new Checking account with the given account number.
     *
     * @param accountNumber The unique identifier for this account.
     */
    public Checking(String accountNumber) {
        super(accountNumber);
    }

    /**
     * Withdraws the specified amount from the account if the balance is sufficient.
     *
     * @param amount The amount to be withdrawn.
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
     * Deposits the given amount to the account if the amount is positive.
     *
     * @param amount The amount to be deposited.
     */
    @Override
    public  void deposit(double amount){
        if(amount >= 0){
            balance += amount;
        }else{
            System.out.println("Error with deposit.");
        }
    }
}
