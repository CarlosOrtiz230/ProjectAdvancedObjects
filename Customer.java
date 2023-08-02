import java.util.ArrayList;
import java.util.List;
 

/**
 * This class represents a Customer.
 */
public class Customer extends Person {
    //attributes----------------------------------------------
    /**
     * Initializing all attributes of the customers 
     */
    private List<Account> accounts;
    private List<String> customerTransactions = new ArrayList<String>();
    private String identificationNumber;
    private String phoneNumber;
    //private List<Transaction> customerTransactions = new ArrayList<>();


    //constructors----------------------------------------

    public Customer() {} //default 

    /**
     * Constructs a new Customer with the given personal information.
     *
     * @param name               The name of the Customer.
     * @param dob                The date of birth of the Customer in the format "yyyy-MM-dd".
     * @param address            The address of the Customer.
     * @param identificationNumber The identification number (e.g., passport, national ID) of the Customer.
     * @param phoneNumber        The phone number of the Customer.
     */
    public Customer(String name, String dob, String address, String identificationNumber, String phoneNumber) {
        super(Integer.parseInt(identificationNumber), name, address, dob, phoneNumber); //passes everything to Person class constructor
        this.accounts = new ArrayList<>();
        this.identificationNumber = identificationNumber; 
        this.phoneNumber = phoneNumber; //accessing attributes from within the class.
    }
    

   
    //getters and setters----------------------------------

    /**
     * Returns the list of Accounts that the Customer has.
     * @return The list of Accounts.
     */
    public List<Account> getAccounts() {
        return this.accounts; //returns accounts from user
    }

    /**
     * Returns the identification number associated with this customer.
     *
     * @return the identification number
     */
    public String getIdentificationNumber(){
        return this.identificationNumber;
    }

    //methods---------------------------------------------

    /**
     * Adds an Account to the Customer's list of accounts.
     * @param account The account to be added.
     */
    public void addAccount(Account account) {
        try {
            this.accounts.add(account); //adding account to current instance of accounts
        } catch (Exception e) {
            System.out.println("An error occurred while adding account: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Checks the balance of the specified account type.
     *
     * @param accountType the account type to check ("A" for Savings, "B" for Checking)
     * @return the balance of the account, or 0.0 if the account is not found
     */
    public double checkBalance(String accountType) {
        try {
            for (Account account : accounts) {
                if  ((account instanceof Checking && accountType.equalsIgnoreCase("checking")) || //checking if account type is an instance of checking and it matches with userinput
                    (account instanceof Checking && accountType.equalsIgnoreCase("checkings")) ||
                    (account instanceof Savings && accountType.equalsIgnoreCase("saving"))||
                    (account instanceof Savings && accountType.equalsIgnoreCase("savings"))||
                    (account instanceof Credit && accountType.equalsIgnoreCase("credit"))) {// in case user adds "s" to end of term
                    return account.getBalance(); //Calls Account and return current balance of current account. 
                }
            }
            System.out.println("Account not found.");
        } catch (Exception e) {
            System.out.println("An error occurred while checking balance: " + e.getMessage());
            e.printStackTrace();
        }
        return 0.0;
    }

    /**
     * Deposits the specified amount to the specified account type.
     *
     * @param accountType the account type to deposit to ("A" for Savings, "B" for Checking)
     * @param amount the amount to deposit
     */
    public void deposit(String accountType, double amount) {
        try {
            for (Account account : accounts) {
                if ((account instanceof Savings && accountType.equalsIgnoreCase("savings")) || 
                    (account instanceof Checking && accountType.equalsIgnoreCase("checking"))||
                    (account instanceof Credit && accountType.equalsIgnoreCase("Credit"))) { //checking and matching account type 
                    account.deposit(amount); // add amount to account current balance. Calls upon Account then overrides to account type
                    System.out.println("Deposit of " + amount + " made to " + accountType + " account."); //prints deposit information
                    return;
                }
            }
            System.out.println("Account not found.");
        } catch (Exception e) {
            System.out.println("An error occurred while depositing: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Withdraws the specified amount from the specified account type.
     *
     * @param accountType the account type to withdraw from ("A" for Savings, "B" for Checking)
     * @param amount the amount to withdraw
     */
    public void withdraw(String accountType, double amount) {
        try {
            for (Account account : accounts) {
                if ((account instanceof Checking && accountType.equalsIgnoreCase("checking")) || 
                    (account instanceof Savings && accountType.equalsIgnoreCase("savings"))||
                    (account instanceof Credit && accountType.equalsIgnoreCase("Credit"))) {
                    account.withdraw(amount); // subtracts current balance from account type by ammount. 
                    System.out.println("Withdraw of " + amount + " made to " + accountType + " account.");
                    return;
                }
            }
            System.out.println("Account not found.");
        } catch (Exception e) {
            System.out.println("An error occurred while withdrawing: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Prints the checking account information for the customer.
     */
    public void printCheckingInfo(){
        try {
            Checking checkingAccount = (Checking) accounts.get(0); // if checking account is always the first in the list
            System.out.println("Customer ID: " + this.getIdentificationNumber());
            System.out.println("Account Number: " + checkingAccount.getAccountNumber());
            System.out.println("Current Balance: " + checkingAccount.getBalance());
        } catch (Exception e) {
            System.out.println("An error occurred while printing checking info: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Prints the savings account information for the customer.
     */
    public void printSavingsInfo(){
        try {
            Savings savingsAccount = (Savings) accounts.get(1); // if savings account is always the second in the list
            System.out.println("Customer ID: " + this.getIdentificationNumber());
            System.out.println("Account Number: " + savingsAccount.getAccountNumber());
            System.out.println("Current Balance: " + savingsAccount.getBalance());
        } catch (Exception e) {
            System.out.println("An error occurred while printing savings info: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Prints the credit account information for the customer.
     */
    public void printCreditInfo(){
        try {
            Credit creditAccount = (Credit) accounts.get(2); // if credit account is always the third in the list
            System.out.println("Customer ID: " + this.getIdentificationNumber());
            System.out.println("Account Number: " + creditAccount.getAccountNumber());
            System.out.println("Current Balance: " + creditAccount.getBalance());
        } catch (Exception e) {
            System.out.println("An error occurred while printing credit info: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // New method to add a transaction
    public void addTransaction(String transaction) {
        this.customerTransactions.add(transaction);
    }

    // New method to get all transactions
    public List<String> getTransactions() {
        return this.customerTransactions;
    }
    //-=-=-=-=
    
}

