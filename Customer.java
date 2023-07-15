import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.*;

/**
 * This class represents a Customer.
 */
public class Customer extends Person {
    //attributes----------------------------------------------
    private List<Account> accounts;
    private String password;
    private String identificationNumber;

    //construtors----------------------------------------

    public Customer() {} //default 

    /**
     * Constructs a new Customer with the given name and address.
     * @param name The name of the Customer.
     * @param address The address of the Customer.
     */
    public Customer(String name, String dob, String address,String password , String identificationNumber) {
        super(name, address,dob,password);
        this.accounts = new ArrayList<>();
        String phone =  password.replaceAll("[()\\-\\s]", ""); //get ride of spaces and lines
        this.password = phone;
        this.identificationNumber = identificationNumber;
    }

   
    //getters and setters----------------------------------

    /**
     * Returns the list of Accounts that the Customer has.
     * @return The list of Accounts.
     */
    public List<Account> getAccounts() {
        return this.accounts;
    }

    /**
         * Returns the password associated with this customer.
         *
         * @return the password
    */
    public String getPassword(){
        return this.password;
    }

    /**
         * Returns the identification number associated with this customer.
         *
         * @return the identification number
     */
    public String getIdentificationNumber(){
        return this.identificationNumber;
    }

    /**
         * Sets the password for this customer based on the provided phone number.
         *
         * @param phone the phone number used to set the password
    */
    public void setPassword(String phone){
        phone =  phone.replaceAll("[()\\-\\s]", "");
        System.out.println(phone);
        this.password = phone;
    }


    //methods---------------------------------------------

    /**
     * Adds an Account to the Customer's list of accounts.
     * @param account The account to be added.
     */
    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    /**
         * Transfers the specified amount of money from the checking account to the savings account.
         *
         * @param amount the amount of money to transfer
     */
    public void transferMoneyToSaving(double amount) {
        Savings savingsAccount = null;
        Checking checkingAccount = null;
        for (Account account : accounts) {
            if (account instanceof Savings) {
                savingsAccount = (Savings) account;
            } else if (account instanceof Checking) {
                checkingAccount = (Checking) account;
            }
        }
        if (checkingAccount != null && savingsAccount != null) {
            checkingAccount.transferMoneyToSaving(savingsAccount, amount);
        } else {
            System.out.println("Either Savings or Checking account not found.");
        }
    }

    /**
         * Transfers the specified amount of money from the checking account to the savings account.
         *
         * @param amount the amount of money to transfer
    */
    public void transferMoneyToChecking(double amount) {
        Savings savingsAccount = null;
        Checking checkingAccount = null;
        for (Account account : accounts) {
            if (account instanceof Savings) {
                savingsAccount = (Savings) account;
            } else if (account instanceof Checking) {
                checkingAccount = (Checking) account;
            }
        }
        if (checkingAccount != null && savingsAccount != null) {
            savingsAccount.transferMoneyToChecking(checkingAccount, amount); 
        } else {
            System.out.println("Either Savings or Checking account not found.");
        }
    }


    /**
         * Checks the balance of the specified account type.
         *
         * @param accountType the account type to check ("A" for Savings, "B" for Checking)
         * @return the balance of the account, or 0.0 if the account is not found
    */
    public double checkBalance(String accountType) {
        for (Account account : accounts) {
            if ((account instanceof Checking && accountType.equalsIgnoreCase("B")) || 
                (account instanceof Savings && accountType.equalsIgnoreCase("A"))) {
                return account.getBalance();
            }
        }
        System.out.println("Account not found.");
        return 0.0;
    }
    
    /**
         * Checks the balance of the specified account type.
         *
         * @param accountType the account type to check ("A" for Savings, "B" for Checking)
         * @return the balance of the account, or 0.0 if the account is not found
    */
    public void deposit(String accountType, double amount) {
        for (Account account : accounts) {
            if ((account instanceof Savings && accountType.equalsIgnoreCase("A")) || 
                (account instanceof Checking && accountType.equalsIgnoreCase("B"))) {
                account.deposit(amount);
                System.out.println("Deposit of " + amount + " made to " + accountType + " account.");
                return;
            }
        }
        System.out.println("Account not found.");
    }
    
    /**
         * Withdraws the specified amount from the specified account type.
         *
         * @param accountType the account type to withdraw from ("A" for Savings, "B" for Checking)
         * @param amount the amount to withdraw
     */
    public void withdraw(String accountType, double amount) {
        for (Account account : accounts) {
            if ((account instanceof Checking && accountType.equalsIgnoreCase("B")) || 
                (account instanceof Savings && accountType.equalsIgnoreCase("A"))) {
                account.withdraw(amount);
                System.out.println("Withdraw of " + amount + " made to " + accountType + " account.");
                return;
            }
        }
        System.out.println("Account not found.");
    }

    /**
         * Prints the checking account information for the customer.
    */
    public void printCheckingInfo(){
        Checking checkingAccount = (Checking) accounts.get(0); // if checking account is always the first in the list
        System.out.println("Customer ID: " + this.getIdentificationNumber());
        System.out.println("Account Number: " + checkingAccount.getAccountNumber());
        System.out.println("Current Balance: " + checkingAccount.getBalance());
    }
    
    /**
        * Prints the savings account information for the customer.
    */
    public void printSavingsInfo(){
        Savings savingsAccount = (Savings) accounts.get(1); // if savings account is always the second in the list
        System.out.println("Customer ID: " + this.getIdentificationNumber());
        System.out.println("Account Number: " + savingsAccount.getAccountNumber());
        System.out.println("Current Balance: " + savingsAccount.getBalance());
    }
    
    /**
         * Prints the credit account information for the customer.
    */
    public void printCreditInfo(){
        Credit creditAccount = (Credit) accounts.get(2); // if credit account is always the third in the list
        System.out.println("Customer ID: " + this.getIdentificationNumber());
        System.out.println("Account Number: " + creditAccount.getAccountNumber());
        System.out.println("Current Balance: " + creditAccount.getBalance());
    }

}

