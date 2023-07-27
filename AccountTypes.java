import java.util.HashSet;
import java.util.Set;

public final class AccountTypes {
    public static final String CHECKING = "CHECKING";
    public static final String SAVINGS = "SAVINGS";
    public static final String CREDIT = "CREDIT";

    private static final Set<String> ACCOUNT_TYPES = new HashSet<String>() {{
        add(CHECKING);
        add(SAVINGS);
        add(CREDIT);
    }};

    public static boolean isValidAccountType(String type) {
        return ACCOUNT_TYPES.contains(type.toUpperCase());
    }

    public static String getAccountType(String accountType){
        String upperCaseAccountType = accountType.toUpperCase();
        if(ACCOUNT_TYPES.contains(upperCaseAccountType)) {
            return upperCaseAccountType;
        } else {
            System.out.println(accountType + " is not a valid account type");
            return "Not valid";
        }
    }
}
