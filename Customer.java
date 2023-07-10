import java.util.List;
import java.util.ArrayList;

public class Customer extends Person {
    private List<Account> accounts;

    public Customer(String name, String address) {
        super(name, address);
        accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}