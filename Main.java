import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.plaf.TreeUI;

public class Main {
    public static void main(String[] args) throws IOException {
        String csvFile = "BankUser.CSV"; // Name of DataBase
        List<Customer> customers = new ArrayList<>(); //Array List to hold database during the program 
        customers = bankUserReader(csvFile);
        Customer currentCustomer = null; //before assigning a customer

        //Check if the user is a manager to change the functionality
   
        boolean isManager = checkIfManager();
   
        // Implement user/password authentication based on cellphone 
        while(currentCustomer == null && !isManager){ //loop while log in 
             currentCustomer = userLogin( currentCustomer, customers); //if the function returns true we will continue and the               
        }
        
        //Moving forward main menu

        if(isManager){ // main menu for managers
            while(true){
                displayManagerMenu();
                managerOptions(customers);
            }
        }

        //main menu for customers

        // switch(role.toLowerCase()){
        //     case "a":
        //         while(true){
        //             System.out.println("What would you like to do today?  \n1. Inquiry about a balance \n2. Deposit money to an account \n3. Withdraw money from an account \n4. Transfer money between accounts \n5. EXIT");
        //             int option = x.nextInt();
        //             x.nextLine(); // To skp line

        //             switch(option){
        //                 case 1:
        //                     System.out.println("Which account's balance would you like to check? \nA.Savings\nB.Checkings");
        //                     String accountType = x.nextLine();
        //                     System.out.println("Your current balance is: " + currentCustomer.checkBalance(accountType));
        //                     break;

        //                 case 2:
        //                     System.out.println("To which account would you like to deposit money? \nA.Savings\nB.Checkings");
        //                     String depositAccount = x.nextLine().trim().toUpperCase();
        //                     System.out.println("Enter the amount you want to deposit:");
        //                     double depositAmount = x.nextDouble();
        //                     x.nextLine(); // Consume newline left-over
        //                     if(depositAccount.equals("A") || depositAccount.equals("B")){
        //                         currentCustomer.deposit(depositAccount, depositAmount);
        //                         System.out.println("Deposit Successful!");
        //                     } else {
        //                         System.out.println("Invalid account type entered! Please try again.");
        //                     }
        //                     break;
                    
        //                 case 3:
        //                     System.out.println("From which account you would like to withdraw money? \nA.Savings\nB.Checkings");
        //                     String withdrawAccount = x.nextLine();
        //                     System.out.println("Enter the amount you want to withdraw:");
        //                     double withdrawAmount = x.nextDouble();
        //                     //currentCustomer.withdraw(withdrawAccount, withdrawAmount);
        //                     break;
        //                 case 4:
        //                     System.out.println("Enter the amount you would like to transfer from Checking to Savings account:");
        //                     double transferAmount = x.nextDouble();
        //                     currentCustomer.transferMoneyToSaving(transferAmount);
        //                     break;
        //                 case 5:
        //                     System.out.println("Exiting... Bye!");
        //                     System.exit(0);
        //             }
        //         }//case a ends
        //     case "b":
        //         while(true){
        //             // Manager tasks
        //         }
      //  }
    }

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

                Customer customer = new Customer(firstName + " " + lastName, address, phoneNumber);
                Checking checkingAccount = new Checking(checkingAccountNum);
                Savings savingsAccount = new Savings(savingsAccountNum);
                Credit creditAccount = new Credit(creditAccountNumber, creditMax);

                // Set the starting balances
                checkingAccount.deposit(checkingStartingBalance);
                savingsAccount.deposit(savingsStartingBalance);
                creditAccount.deposit(creditStartingBalance);

                // Add the accounts to the customer
                customer.addAccount(checkingAccount); // 1 checkings 2 savings 3 credit
                customer.addAccount(savingsAccount);
                customer.addAccount(creditAccount);

                // Add the customer to our list
                customers.add(customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }//file reader ends

    public static Customer userLogin(Customer currentCustomer,List<Customer> customers) throws IOException {
        Scanner x = new Scanner(System.in); //ask the user's name
        System.out.println("Enter your first and last name:");
        System.out.print(">");
        String userName = x.nextLine();
        

        // Find the customer in ArrayList to check existence
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(userName)) {
                currentCustomer = customer;
                break;
            }
        }
        if (currentCustomer == null) {
            System.out.println("xxxx----User not found. Try Again----xxxx");
            return currentCustomer;
        }
        System.out.println("Enter your phone Number to get the access (no spaces or characters)");  
        System.out.print(">");
        String password = x.nextLine();

        //check password with phone
        if(currentCustomer.getPassword().equals(password)) { //if the user and password match return the current Customer else return null
            return currentCustomer;
        }else{
            System.out.println("xxxx----Password Did not Match----xxxx");
            return null;
        }
    }//userLogin ends

    public static boolean checkIfManager(){
        Scanner x = new Scanner(System.in);
        System.out.println("are you a Bank Client or Manager?");
        System.out.println("A. Bank Client\nB. Bank Manager");
        String role = x.nextLine();
        System.out.print(">");
        if(role.equalsIgnoreCase("A") ||  role.equalsIgnoreCase("a")){ //if the user is a manager just return
            return false;
        }
        else if(role.equalsIgnoreCase("B") || role.equalsIgnoreCase("b")){
            while(true){
                System.out.println("Enter manager password:");
                System.out.print(">");
                role = x.nextLine();
                if(role.equals("manager")){
                    return true;
                }else{
                    System.out.println("xxxx----Wrong Password----xxxx");
                }

            }

        }//elif 
        else{
            System.out.println("xxxx----this entry is not valid----xxxx");
            return false;
        }
        
    }//checkIfManager ends

    public static void displayManagerMenu(){
        System.out.println("----Manager Menu----");
        System.out.println("Would you like to inquire by name or by type/number");
        System.out.println("A. Inquire account by name.\nB. Inquire account by type/number");
        System.out.print(">");
    }//displayManagerMenu ends
    
    public static void managerOptions(List<Customer> customers){
        Scanner userInput = new Scanner(System.in);
        Customer desiredCustomer = null;

        String managerInput = userInput.nextLine();
        switch(managerInput){
            case "A":
                System.out.print("What is the name of the customer");
                System.out.println(">");
                managerInput = userInput.nextLine();
                
                //loking for the customer
                for (Customer customer : customers) {
                     if (customer.getName().equalsIgnoreCase(managerInput)) {
                            System.out.println("Costumer Found!");
                            desiredCustomer = customer;
                            while(true){
                                System.out.println("Which account would you like to inquire?");
                                System.out.println("1. Checkings\n2. Savings \n3. Credit");
                                System.out.print(">");
                                managerInput = userInput.nextLine();
                                switch(managerInput){
                                    case "1":
                                        System.out.println("\nPrinting Checkings Information........");
                                        // desiredCustomer.printHistoryCheckings()
                                        return;

                                    case "2":
                                        System.out.println("\nPrinting Savings Information........");
                                        // desiredCustomer.printHistorySavings()
                                        return;
                                    
                                    case "3":
                                        System.out.println("\nPrinting Credits Information........");
                                        // desiredCustomer.printHistoryCredit()
                                        return;
                                    default:
                                        System.out.println("xxxx----This is not a valid entry----x");
                                        return;
                                }//switch ends
                            }//while ends
                     }//if ends
                }
                
                System.out.println("xxxx----customer was not found----xxxx");
                return; //return to the begining

            case "B": //if user
                System.out.println("What type of account is it?\n 1. Checkings\n 2. Savings\n 3. Credit");
                System.out.print(">");
                managerInput = userInput.nextLine();
                switch(managerInput){
                                case "1": //checking
                                    System.out.print("Enter your checking account number");
                                    System.out.println(">"); managerInput = userInput.nextLine();
                                    for (Customer customer : customers) {
                                        Checking currentCheckingAccount = (Checking) customer.getAccounts().get(0); //index 0 is checking
                                       
                                        if(currentCheckingAccount.accountNumber.equals(managerInput)) {
                                       
                                           // customer.printCheckingAccountHistory();
                                           return;
                                        }
                                    }
                                    System.out.println("xxxx----Checking Account not Found----xxxx");
                                    return;

                                case "2": //savings
                                    System.out.print("Enter your Saving account number");
                                    System.out.println(">"); managerInput = userInput.nextLine();
                                    for (Customer customer : customers) {
                                        Savings currentSavingsAccount = (Savings) customer.getAccounts().get(1); //index 1 is savings
                                        if(currentSavingsAccount.accountNumber.equals(managerInput) ) {
                                           // customer.SavingsAccountHistory();
                                           return;
                                        }
                                    }
                                    System.out.println("xxxx----Saving Account not Found----xxxx");
                                    return;

                                case "3": //credit
                                    System.out.print("Enter your Credit account number");
                                    System.out.println(">"); managerInput = userInput.nextLine();
                                    for (Customer customer : customers) {
                                        Credit currentCreditAccount = (Credit) customer.getAccounts().get(2); //index 2 is credit
                                        if(currentCreditAccount.accountNumber.equals(managerInput)) {
                                           // customer.SavingsAccountHistory();
                                           return;
                                        }
                                    }
                                    System.out.println("xxxx----Credit Account not Found----xxxx");
                                    return;
                                default:
                                    System.out.println("xxxx----This was not a valid entry. Enter 1 2 or 3----xxxx");
                                    break;
                }//switch ends
                break;
            default: 
                System.out.println("xxxx----This is not a valid entry----xxxx");
                break;
        }//switch
    } //manager Options


}// class ends
