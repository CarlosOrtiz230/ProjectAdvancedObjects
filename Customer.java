import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Customer.
 */
public class Customer extends Person {
    private List<Account> accounts;

    /**
     * Constructs a new Customer with the given name and address.
     * @param name The name of the Customer.
     * @param address The address of the Customer.
     */
    public Customer(String name, String address) {
        super(name, address);
        this.accounts = new ArrayList<>();
    }

    /**
     * Adds an Account to the Customer's list of accounts.
     * @param account The account to be added.
     */
    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    /**
     * Returns the list of Accounts that the Customer has.
     * @return The list of Accounts.
     */
    public List<Account> getAccounts() {
        return this.accounts;
    }
}
