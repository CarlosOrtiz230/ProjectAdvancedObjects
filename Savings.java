/**
 * A class representing a savings account that extends Account.
 */
public class Savings extends Account {

    /**
     * Constructs a new instance of the Savings class with the specified account number.
     * @param accountNumber the account number
     */
    public Savings(String accountNumber) {
        super(accountNumber);
    }

    /**
     * Overrides the withdraw method from the parent class (Account) to allow withdrawals from the savings account
     *
     * @param amount the amount to be withdrawn
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
     * Overrides the deposit method from the parent class (Account) to allow deposits into the savings account
     * @param amount the amount to be deposite
     */
    @Override
    public void deposit(double amount) {
        if (amount >= 0) {
            balance += amount;
        } else {
            System.out.println("Error with deposit.");
        }
    }//despoist ends

    /**
     * Transfers the specified amount of money from the checking account to the checking account.
     *
     * @param checkingAccount The checkings account to which the money will be transferred.
     * @param amount The amount of money to transfer.
     */

     public void transferMoneyToChecking(Checking checkingAccount, double amount) {
        if  (this.balance >= amount) {
            checkingAccount.deposit(amount);
            System.out.println("Transfer successful!");
        } else {
            System.out.println("Insufficient funds in Saving account.");
        }
    }// transfer Money ends

    //DOES NOT IMPLEMENT PAY TO THIRD PARTY

}//class ends
