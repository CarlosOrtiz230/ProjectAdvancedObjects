import java.util.HashSet;
import java.util.List;
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

    private static final Set<String> ACCOUNT_TYPES = new HashSet<String>() {{ //Creating a HashSet with unique constant account types. 
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
        return ACCOUNT_TYPES.contains(type.toUpperCase()); //grabbing input to uppercase to match with constants.
    }

    
    //overloaded to different situation // dont think it is recursion
    public static boolean isValidAccountType(String type,String transactionType){
        if((type.equalsIgnoreCase(AccountTypes.CREDIT)) && (transactionType.equalsIgnoreCase(TransactionTypes.DEPOSIT))){ //prevents depositing to Credit account.
            System.out.println("Credit can't be deposited");
            return false;
        }
        return isValidAccountType(type);
    }

    /**
     * Retrieves the standardized account type for the provided account type.
     * @param accountType The account type to retrieve 
     * @return The uppercase account type 
     */
    public static String getAccountType(String accountType) {
        String upperCaseAccountType = accountType.toUpperCase(); //grabs account type in upper case
        if (ACCOUNT_TYPES.contains(upperCaseAccountType)) { //checks that constants matches with input
            return upperCaseAccountType; 
        } else {
            System.out.println(accountType + " is not a valid account type"); //incorrect account chosen
            return "Not valid";
        }
    }

    public static String instanceOfAccountByNumber(String recipientAccountNumber, List<Customer> customers){
        for (Customer customer : customers) { //traversing through all customers in ArrayList customers"
            for (Account currentAccount : customer.getAccounts()) { // traversing data within current client
                if (currentAccount.accountNumber.equals(recipientAccountNumber)) {  //check that accountNumber of currentAccount matches with recipient accountNumber
                        if(currentAccount instanceof Checking){return CHECKING;}
                        else if (currentAccount instanceof Savings){return SAVINGS;}
                        else{return CREDIT;}//checks that account type in database is instance of checking or savings
                }
            }
        } 
        PrintMenu.accountNubmerNotfound();
        return null; //if loops complete and no account number is found, then print error message. 
    }
}
