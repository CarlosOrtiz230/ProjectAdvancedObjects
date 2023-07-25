import java.util.List;

/**
 * This class represents a checking account.
 */
public class Checking extends Account {

    /**
     * Constructs a Checking object with the specified account number.
     *
     * @param accountNumber The account number for the checking account.
     */
    public Checking(String accountNumber) {
        super(accountNumber);
    }

    /**
     * Retrieves the account number of the checking account.
     *
     * @return The account number.
     */
    @Override
    public String getAccountNumber() {
        return this.accountNumber;
    }

    /**
     * Withdraws the specified amount from the account.
     *
     * @param amount The amount to be withdrawn.
     */
    @Override
    public void withdraw(double amount) {
        try {
            if (balance >= amount) {
                balance -= amount;
            } else {
                throw new IllegalArgumentException("Insufficient balance.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deposits the specified amount into the account.
     *
     * @param amount The amount to be deposited.
     */
    @Override
    public void deposit(double amount){
        try {
            if(amount >= 0){
                balance += amount;
            } else {
                throw new IllegalArgumentException("Error with deposit.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Transfers the specified amount from the checking account to the savings account.
     *
     * @param savingAccount The savings account to transfer the money to.
     * @param amount        The amount to be transferred.
     */
    public void transferMoneyToSaving(Savings savingAccount, double amount) {
        try {
            if (this.balance >= amount) {
                savingAccount.deposit(amount);
                this.balance -= amount;
                System.out.println("Transfer successful!");
            } else {
                throw new IllegalArgumentException("Insufficient funds in checking account.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
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
        try {
            String receiverFullName  = name + " " + lastname;
            Customer receiver = null;

            for (Customer customer : dataBase){
                if(customer.getName().equals(receiverFullName)){
                    receiver = customer;
                    break;
                }
            }

            if(receiver == null){
                throw new IllegalArgumentException("Customer was not found");
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
                throw new IllegalArgumentException("The receiver's account number is incorrect.");
            }

            System.out.println("Your Deposit to " + receiverFullName + " was successful");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
