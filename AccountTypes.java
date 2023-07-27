import java.util.HashSet;
import java.util.Set;

/**
 * The AccountTypes class provides constants and utility methods related to different types of accounts.
 */
public final class AccountTypes {

    /**
     * Constant representing the account types.
     */
    public static final String CHECKING = "CHECKING";
    public static final String SAVINGS = "SAVINGS";
    public static final String CREDIT = "CREDIT";

    private static final Set<String> ACCOUNT_TYPES = new HashSet<String>() {{
        add(CHECKING);
        add(SAVINGS);
        add(CREDIT);
    }};

    /**
     * Checks if the provided account type is a valid account type.
     *
     * @param type The account type to check for validity.
     * @return true if the account type is valid, false otherwise.
     */
    public static boolean isValidAccountType(String type) {
        return ACCOUNT_TYPES.contains(type.toUpperCase());
    }

    /**
     * Retrieves the standardized account type for the provided account type.
     * @param accountType The account type to retrieve 
     * @return The uppercase account type 
     */
    public static String getAccountType(String accountType) {
        String upperCaseAccountType = accountType.toUpperCase();
        if (ACCOUNT_TYPES.contains(upperCaseAccountType)) {
            return upperCaseAccountType;
        } else {
            System.out.println(accountType + " is not a valid account type");
            return "Not valid";
        }
    }
}
