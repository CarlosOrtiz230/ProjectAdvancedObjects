import java.util.List;

/**
 * Represents a checking account.
 */
public class Checking extends Account {

    //constructor----------------------------------------------------------------
     
    /**
     * Constructs a Checking object with the specified account number.
     *
     * @param accountNumber The account number for the checking account.
     */
    public Checking(String accountNumber) {
        super(accountNumber);
    }

    //setters and getters----------------------------------------------------------------

    /**
         * Retrieves the account number of the checking account.
         *
         * @return The account number.
     */
    @Override
    public String getAccountNumber() {
        return this.accountNumber;
    }
    

    //methods------------------------------------------------------------------

    /**
         * Withdraws the specified amount from the account.
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
         * Deposits the specified amount into the account.
         *
         * @param amount The amount to be deposited.
    */
    @Override
    public void deposit(double amount){
        if(amount >= 0){
            balance += amount;
        } else {
            System.out.println("Error with deposit.");
        }
    }

    /**
         * Transfers the specified amount from the checking account to the savings account.
         *
         * @param savingAccount The savings account to transfer the money to.
         * @param amount        The amount to be transferred.
    */
    public void transferMoneyToSaving(Savings savingAccount, double amount) {
        if (this.balance >= amount) {
            savingAccount.deposit(amount);
            this.balance -= amount;
            System.out.println("Transfer successful!");
        } else {
            System.out.println("Insufficient funds in checking account.");
        }
    }


    /**
         * Pays a specified amount to a third party recipient.
         *
         * @param dataBase       The database of customers.
         * @param name           The first name of the recipient.
         * @param lastname       The last name of the recipient.
         * @param accountNumber  The account number of the recipient.
         * @param amount         The amount to be paid.
     */
    @Override
    public void payToThirdParty(List<Customer> dataBase ,String name, String lastname, String accountNumber,double amount) {
        String receiverFullName  = name + " " + lastname;
        Customer receiver = null;

        for (Customer customer : dataBase){
            if(customer.getName().equals(receiverFullName)){
                receiver = customer;
                break;
            }
        }

        if(receiver == null){
            System.out.println("Customer was not found");
            return;
        }

        List<Account> receiverAccounts = receiver.getAccounts();

        boolean accountFound = false;
        for (Account currentAccount: receiverAccounts) {
            if (currentAccount.getAccountNumber().equals(accountNumber)) {
                currentAccount.deposit(amount);
                accountFound = true;
                this.balance = this.balance - amount;
                break;
            }
        }

        if (!accountFound) {
            System.out.println("The receiver's account number is incorrect.");
            return;
        }

        System.out.println("Your Deposit to " + receiverFullName + " was successful");
    }
    
}
