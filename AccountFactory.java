import java.util.List;

public class AccountFactory {

    public Account createAccount(String type, String accountNumber, double... params) {
        switch (type) {
            case "Savings":
                return new Savings(accountNumber);
            case "Checking":
                return new Checking(accountNumber);
            case "Credit":
                if (params.length > 0) {
                    return new Credit(accountNumber, params[0]);
                } else {
                    throw new IllegalArgumentException("Credit account requires a credit limit.");
                }
            default:
                throw new IllegalArgumentException("Invalid account type.");
        }
    }
}
