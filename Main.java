import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
    
        String csvFile = "BankUser.CSV"; // Name of DataBase
        List<Customer> customers = new ArrayList<>(); //Array List to hold data base during the program 
        customers = bankUserReader(csvFile);    
        
        //needs to implement user password authentication based on cellphone and DOB
        
    } //main methods ends


     /**
     * Obtains the file name with the dasa base and return an array List with the data base.
     * @param csvfile the name of the file
     */    
    public static List<Customer> bankUserReader(String csvFile) throws IOException {
        String line;
        List<Customer> customers = new ArrayList<Customer>();
         try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String skipLine = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String identificationNumber = data[0];
                String firstName = data[1];
                String lastName = data[2];
                String dob = data[3];
                String address = data[4] + "," + data[5] + "," + data[6]; // Combine address
                String phoneNumber = data[7].replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
                String checkingAccountNum = data[8];
                double checkingStartingBalance = Double.parseDouble(data[9]);
                String savingsAccountNum = data[10];
                double savingsStartingBalance = Double.parseDouble(data[11]);
                String creditAccountNumber = data[12];
                double creditMax = Double.parseDouble(data[13]);
                double creditStartingBalance = Double.parseDouble(data[14]);

                Customer customer = new Customer(firstName + " " + lastName, address);
                Checking checkingAccount = new Checking(checkingAccountNum);
                Savings savingsAccount = new Savings(savingsAccountNum);
                Credit creditAccount = new Credit(creditAccountNumber, creditMax);

                // Set the starting balances
                checkingAccount.deposit(checkingStartingBalance);
                savingsAccount.deposit(savingsStartingBalance);
                creditAccount.deposit(creditStartingBalance);

                // Add the accounts to the customer
                customer.addAccount(checkingAccount);
                customer.addAccount(savingsAccount);
                customer.addAccount(creditAccount);

                // Add the customer to our list
                customers.add(customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  customers;
    }//file reader ends


        Scanner x = new Scanner(System.in);

        System.out.println("A. Bank Client\nB. Bank Manager");
        String role = x.nextLine();

        switch(role.toLowerCase()){
            case "a":
                while(true){
                    System.out.println("What would you like to do today?  1. Inquiry about a balance 2. Deposit money to an account 3. Withdraw money from an account 4. Transfer money between accounts 5. Pay Someone");
                    int option = x.nextInt();
                    switch(option){
                        case 1:
                            System.out.println("Enter your account number");
                            String accountNumber = x.nextLine();
                            System.out.println("Which account's balance would you like to check? \nA.Savings\nB.Checkings");
                            String accountType = x.nextLine();
                            
                            if (accountType.equalsIgnoreCase("A")) {
                                Checking checkingAccount = new Checking(accountNumber);
                                System.out.println("Your current balance is: " + checkingAccount.getBalance());
                                break;
                            }
                            
                            else if(accountType.equalsIgnoreCase("B")){
                                Savings savingAccount = new Savings(accountNumber);
                                System.out.println("Your current balance is: " + savingAccount.getBalance());
                                break;
                            }

                        case 2: 
                            System.out.println("Enter your account number");
                            String accountNum = x.next();
                            System.out.println("Where wouldyou like to deposit your money? \nA.Savings\nB.Checkings");\
                            String choice = x.nextLine();

                            if (choice.equalsIgnoreCase("A")){
                                Checking checkingAccount = new Checking(accountNum);
                                System.out.println("How much would you like to deposit today?");
                                double amt = x.nextDouble();
                                checkingAccount.deposit(amt);
                                break;
                            }
                            else if(choice.equalsIgnoreCase("B")){
                                Savings savingAccount = new Savings(accountNum);
                                System.out.println("How much would you like to deposit today?"):
                                double amt = x.nextDouble();
                                savingAccount.deposit(amt);
                            }
                        case 3:
                            //Withdrawal
                            System.out.println("Enter your account number");
                            String accountNumber = x.nextLine();
                            System.out.println("From which account would you like to withdraw money? \nA.Savings\nB.Checkings");
                            String withdrawChoice = x.nextLine();

                            if (withdrawChoice.equalsIgnoreCase("A")){
                                Checking checkingAccount = new Checking(accountNumber);
                                System.out.println("How much would you like to withdraw today?");
                                double withdrawAmt = x.nextDouble();
                                checkingAccount.withdraw(withdrawAmt);
                            }

                            else if(withdrawChoice.equalsIgnoreCase("B")){
                                Savings savingAccount = new Savings(accountNumber);
                                System.out.println("How much would you like to withdraw today?");
                                double withdrawAmt = x.nextDouble();
                                savingAccount.withdraw(withdrawAmt);
                            }
                            break;
                        case 4:
                        //assumes that checking and savings account exist.
                        System.out.println("Enter your checking account number");
                        String checkingAccountNumber = x.nextLine();
                        System.out.println("Enter your savings account number");
                        String savingAccountNumber = x.nextLine();
                        Checking checkingAccount = new Checking(checkingAccountNumber);
                        Savings savingAccount = new Savings(savingAccountNumber);
                        System.out.println("How much would you like to transfer from checking to savings?");
                        double transferAmt = x.nextDouble();
                        checkingAccount.transferMoneyToSaving(savingAccount, transferAmt);
                        break;
                            

                        

                    }
                }//case a ends
            case "b":
                while(true){

                }
        }

        /**
     * Inquiring about balance
     * @param account
     */
    public static double inquireBalance(Account currAccount){

    }

}// class ends
