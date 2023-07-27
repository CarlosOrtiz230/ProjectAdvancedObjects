import java.util.List;

/**
 * The AccountFactory class is responsible for creating different types of accounts based on the provided parameters.
 */
public class AccountFactory {

    /**
     * Creates an account based on the given account type and account number.
     *
     * @param type         The type of account to create ("Savings", "Checking", or "Credit").
     * @param accountNumber The account number for the new account.
     * @param params       Optional parameters based on the account type:
     *                     - For "Credit" accounts, a single parameter representing the credit limit is required.
     * @return The created account object.
     * @throws IllegalArgumentException If an invalid account type is provided, or for "Credit" accounts if no credit limit is given.
     */
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
