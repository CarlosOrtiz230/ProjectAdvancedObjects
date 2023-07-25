import java.util.List;

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

    @Override
    public String getAccountNumber() {
        return this.accountNumber;
    }

    @Override
    public void deposit(double amount){
        try {
            balance += amount;
        } catch (Exception e) {
            System.out.println("Error with deposit: " + e.getMessage());
        }
    }
    

    @Override
    public void withdraw(double amount) {
        try {
            if (balance + creditLimit >= amount) {
                balance -= amount;
            } else {
                throw new IllegalArgumentException("Withdrawal amount exceeds credit limit.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

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
