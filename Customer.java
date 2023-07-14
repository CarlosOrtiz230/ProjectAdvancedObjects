import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.*;

/**
 * This class represents a Customer.
 */
public class Customer extends Person {
    //attributes
    private List<Account> accounts;
    private String password;
    private String identificationNumber;

    //construtors

    public Customer() {} //default 

    /**
     * Constructs a new Customer with the given name and address.
     * @param name The name of the Customer.
     * @param address The address of the Customer.
     */
    public Customer(String name, String address,String password, String identificationNumber) {
        super(name, address);
        this.accounts = new ArrayList<>();
        String phone =  password.replaceAll("[()\\-\\s]", ""); //get ride of spaces and lines
        this.password = phone;
        this.identificationNumber = identificationNumber;
    }

    private String getIdentificationNumber(){
        return this.identificationNumber;
    }

    //getters and setters

    /**
     * Returns the list of Accounts that the Customer has.
     * @return The list of Accounts.
     */
    public List<Account> getAccounts() {
        return this.accounts;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String phone){
        phone =  phone.replaceAll("[()\\-\\s]", "");
        System.out.println(phone);
        this.password = phone;
    }


    //methods 
    /**
     * Adds an Account to the Customer's list of accounts.
     * @param account The account to be added.
     */
    public void addAccount(Account account) {
        this.accounts.add(account);
    }

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


    //checks balance of customer
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
    public void printCheckingInfo(){
        Checking checkingAccount = (Checking) accounts.get(0); // if checking account is always the first in the list
        System.out.println("Customer ID: " + this.getIdentificationNumber());
        System.out.println("Account Number: " + checkingAccount.getAccountNumber());
        System.out.println("Current Balance: " + checkingAccount.getBalance());
    }
    
    public void printSavingsInfo(){
        Savings savingsAccount = (Savings) accounts.get(1); // if savings account is always the second in the list
        System.out.println("Customer ID: " + this.getIdentificationNumber());
        System.out.println("Account Number: " + savingsAccount.getAccountNumber());
        System.out.println("Current Balance: " + savingsAccount.getBalance());
    }
    
    public void printCreditInfo(){
        Credit creditAccount = (Credit) accounts.get(2); // if credit account is always the third in the list
        System.out.println("Customer ID: " + this.getIdentificationNumber());
        System.out.println("Account Number: " + creditAccount.getAccountNumber());
        System.out.println("Current Balance: " + creditAccount.getBalance());
    }
}
