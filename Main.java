import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.plaf.TreeUI;

public class Main {
    public static void main(String[] args) throws IOException {
        //(714) 781-4636
        //7147814636
        List<String> log = new ArrayList<>();
        String csvFile = "BankUser.CSV"; // Name of DataBase
        List<Customer> customers = new ArrayList<>(); //Array List to hold database during the program 
        customers = bankUserReader(csvFile);
        Customer currentCustomer = null; //before assigning a customer
        while(true){
        //Check if the user is a manager to change the functionality
   
            boolean isManager = checkIfManager();
    
            // Implement user/password authentication based on cellphone 
            while(currentCustomer == null && !isManager){ //loop while log in 
                currentCustomer = userLogin( currentCustomer, customers); //if the function returns true we will continue and the               
            }
            
            //Moving forward main menu

            if(isManager){ // main menu for managers
                while(true){
                    managerOptions(customers, log);
                }
            }
            isManager = false;

            //main menu for customers

            while(true){
                displayCustomerMenu();
                Scanner x = new Scanner(System.in);
                String option = x.nextLine();
                //x.nextLine(); // To skp line

                switch(option.toLowerCase()){
                    case "1":
                        System.out.println("Which account's balance would you like to check? \nA.Savings\nB.Checkings");
                        System.out.print(">");
                        String accountType = x.nextLine();
                        System.out.println("Your current balance is: " + currentCustomer.checkBalance(accountType));
                        logTransaction(currentCustomer, accountType, "balance inquiry", currentCustomer.checkBalance(accountType), log );
                        break;

                    case "2":
                        System.out.println("To which account would you like to deposit money? \nA.Savings\nB.Checkings");
                        System.out.print(">");
                        String depositAccount = x.nextLine().trim().toUpperCase();
                        System.out.println("Enter the amount you want to deposit:");
                        System.out.print(">");
                        String depositInput = x.nextLine(); //to avoid crashing
                        double depositAmount;
                        if (isNumeric(depositInput)) {
                            depositAmount = Double.parseDouble(depositInput);
                        } else {
                            System.out.println("xxxx----Invalid input. Please enter a valid number.----xxxx");
                            break; //exit the program
                        }
                        x.nextLine(); // Consume newline left-over
                        if(depositAccount.equalsIgnoreCase("A") || depositAccount.equalsIgnoreCase("B")){
                            currentCustomer.deposit(depositAccount, depositAmount);
                            System.out.println("Deposit Successful!");
                            logTransaction(currentCustomer, depositAccount, "deposit", depositAmount, log);
                        } else {
                            System.out.println("xxxx----Invalid account type entered! Please try again.----xxxx");
                        }
                        break;
                
                    case "3":
                        System.out.println("From which account you would like to withdraw money? \nA.Savings\nB.Checkings");
                        //System.out.print(">");
                        String withdrawAccount = x.nextLine();
                        if (withdrawAccount.equalsIgnoreCase("A") || withdrawAccount.equalsIgnoreCase("B")){
                            System.out.println("Enter the amount you want to withdraw:");
                            System.out.print(">");
                            String withdrawInput =  x.nextLine();
                            double withdrawAmount;
                            if (isNumeric(withdrawInput)) {
                                withdrawAmount = Double.parseDouble(withdrawInput);
                            } else {
                                System.out.println("xxxx----Invalid input. Please enter a valid number.----xxxx");
                                break; //exit the program
                            }
                            currentCustomer.withdraw(withdrawAccount, withdrawAmount);
                            System.out.println("With DrawSuccessful!");
                            logTransaction(currentCustomer, withdrawAccount, "withdrawal", withdrawAmount, log);
                        }else{
                            System.out.println("xxxx----Invalid account type entered! Please try again.----xxxx");
                        }
                        break;

                        case "4":
                            System.out.println("From which account would you like to transfer?\nA.Savings to Checkings \nB.Checkings to Savings");
                            System.out.print(">"); 
                            String transferAccount = x.nextLine().trim();
                            System.out.println("Enter the amount you would like to transfer between your accounts:");
                            System.out.print(">");
                            String transferInput = x.nextLine();
                            double transferAmount;
                            if (isNumeric(transferInput)) {
                                transferAmount = Double.parseDouble(transferInput);
                             } else {
                            System.out.println("xxxx----Invalid input. Please enter a valid number.----xxxx");
                            break; //exit the program
                            }
                            x.nextLine(); // This line is added to consume the newline character
                            if (transferAccount.equalsIgnoreCase("A") || transferAccount.equalsIgnoreCase("B")){
                                switch (transferAccount.toLowerCase()) {
                                    case "a"://savings
                                        currentCustomer.transferMoneyToSaving(transferAmount);
                                        logTransaction(currentCustomer, "Checkings", "transfer to Savings", transferAmount, log);
                                        break;
                                    case "b":
                                        currentCustomer.transferMoneyToChecking(transferAmount);
                                        logTransaction(currentCustomer, "Savings", "transfer to Checkings", transferAmount, log);
                                        break;
                                    default:
                                        System.out.println("xxxx----Invalid account type entered! Please try again.----xxxx");
                                        break;
                                }
                            } else {
                                System.out.println("xxxx----Invalid account type entered! Please try again.----xxxx");
                            }
                            break;
                    case "5":
                        System.out.println("Back to Role Selection");
                        break;

                    case "6":
                            System.out.println("From which account would you like to pay/deposit thirdParty?\nA.Savings\nB.Checkings");
                            System.out.print(">"); 
                            String payAccount = x.nextLine().trim();
                            System.out.println("What is the first name of the recipient");
                            System.out.print(">"); 
                            String name = x.nextLine().trim();
                            System.out.println("What is the last name of the recipient");
                            System.out.print(">"); 
                            String lastName = x.nextLine().trim();
                       
                            //account info
                            System.out.println("What is the recipient account number");
                            System.out.print(">"); 
                            String reciepientAccountNumber = x.nextLine().trim();
                            System.out.println("Enter the amount you would like to pay to thirdParty:");
                            System.out.print(">");
                            String payInput = x.nextLine();
                            double payAmount;
                            if (isNumeric(payInput)){
                                payAmount = Double.parseDouble(payInput);
                             } else {
                            System.out.println("xxxx----Invalid input. Please enter a valid number.----xxxx");
                            break; //exit the program
                            }
                            x.nextLine(); // This line is added to consume the newline character
                            if (payAccount.equalsIgnoreCase("A") || payAccount.equalsIgnoreCase("B")){
                                switch (payAccount.toLowerCase()) {
                                    case "a"://savings
                                        ((Savings)currentCustomer.getAccounts().get(1)).payToThirdParty(customers,name,lastName,reciepientAccountNumber,payAmount); //index 1 is savings
                                        logTransaction(currentCustomer, "Savings", ("transfer to " + name + " " + lastName  + " "), payAmount, log);
                                        break;
                                    case "b"://checking
                                        ((Checking)currentCustomer.getAccounts().get(0)).payToThirdParty(customers,name,lastName,reciepientAccountNumber,payAmount); //index 1 is savings
                                        logTransaction(currentCustomer, "Checking", ("transfer to " + name + " " + lastName  + " "), payAmount, log);
                                        break;
                                    default:
                                        System.out.println("xxxx----Invalid account type entered! Please try again.----xxxx");
                                        break;
                                }
                            } else {
                                System.out.println("xxxx----Invalid account type entered! Please try again.----xxxx");
                            }
                            break; //breake case 6
                    
                    case "exit":
                        System.out.println("Exiting... Bye!");
                        writeCustomersToCSV(customers, csvFile);
                        System.exit(0);
                    default:  
                        System.out.println("xxxx----please enter a valid option----xxxx");
                        break;
                }//switch
                if(option.equals("5")){ //switch Bank Roles
                    break;
                }
            }//while

        } //biggest while end
}//main method ends
   
//complementary methods start

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

                Customer customer = new Customer(firstName + " " + lastName, dob,address, phoneNumber, identificationNumber);
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
        System.out.println("Type 'exit' to leave the program");
        System.out.print(">");
    }//displayManagerMenu ends
    
    public static void displayCustomerMenu(){
        System.out.println("----Customer Menu----");
        System.out.println("What would you like to do today?");
        System.out.println("\n1. Inquiry about a balance \n2. Deposit money to an account");
        System.out.println("3. Withdraw money from an account \n4. Transfer money between accounts \n5. Switch Bank Roles\n" + 
        "6. Pay to someone else" +"\n--Type 'EXIT' to Close--");
    }//displayManagerMenu ends

    public static void managerOptions(List<Customer> customers, List<String> log) throws IOException{
        displayManagerMenu(); //display main options
        Scanner userInput = new Scanner(System.in);
        Customer desiredCustomer = null;

        String managerInput = userInput.nextLine();
        switch(managerInput.toLowerCase()){
            case "a":
                System.out.println("What is the name of the customer");
                System.out.print(">");
                managerInput = userInput.nextLine();
                
                //loking for the customer
                for (Customer customer : customers) {
                     if (customer.getName().equalsIgnoreCase(managerInput)) {
                            System.out.println("Costumer Found!");
                            
                            while(true){
                                System.out.println("Which account would you like to inquire?");
                                System.out.println("1. Checkings\n2. Savings \n3. Credit\n4. Transaction Log");
                                System.out.print(">");
                                managerInput = userInput.nextLine();
                                switch(managerInput.toLowerCase()){
                                    case "1":
                                        System.out.println("\nPrinting Checkings Information:");
                                        customer.printCheckingInfo();
                                        return;

                                    case "2":
                                        System.out.println("\nPrinting Savings Information:");
                                        customer.printSavingsInfo();
                                        return;
                                    
                                    case "3":
                                        System.out.println("\nPrinting Credits Information");
                                        customer.printCreditInfo();
                                        return;
                                    case "4":
                                        System.out.println("Log:");
                                        for (String hisotry : log){
                                            System.out.println(hisotry);
                                        }
                                        break;
                                    default:
                                        System.out.println("xxxx----This is not a valid entry----x");
                                        return;
                                }//switch ends
                            }//while ends
                     }//if ends
                }
                
                System.out.println("xxxx----Customer was NOT Found----xxxx");
                return; //return to the begining

            case "b": //if user
                System.out.println("What type of account is it?\n 1. Checkings\n 2. Savings\n 3. Credit");
                System.out.print(">");
                managerInput = userInput.nextLine();
                switch(managerInput.toLowerCase()){
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
                }// inner switch ends
                break;

            case "exit":
                String csvFile = "BankUser.CSV"; // Name of DataBase
                writeCustomersToCSV(customers, csvFile);
                return; //leave program
            default: 
                System.out.println("xxxx----This is not a valid entry----xxxx");
                break;
        }//switch
    } //manager Options

    public static void logTransaction(Customer customer, String accountType, String transactionType, double balance, List<String> log) {
        String logEntry = "Customer " + customer.getName() + " with account type: " + accountType + " made a " + transactionType + ". Current balance: " + balance;
        log.add(logEntry);
    }
    
    public static boolean isNumeric(String str) { //to check if an string is numeric
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void writeCustomersToCSV(List<Customer> customers, String csvFile) throws IOException {
        try (FileWriter writer = new FileWriter(csvFile, false)) {
            // Escribir los datos de los clientes en el CSV
            for (int i = 0; i < customers.size(); i++) {
                Customer customer = customers.get(i);

                if (i > 0) {
                    writer.write("\n"); // Agregar una nueva línea después de la primera línea
                }

                if(i==0){
                    String line = "Identification Number,First Name,Last Name,Date of Birth,Address,Phone" +
                            "Number,Checking Account Number,Checking Starting Balance," +
                            "Savings Account Number,Savings Starting Balance,Credit Account Number,Credit Max,Credit Starting Balance";
                            writer.write(line);
                    continue;
                }
                //get information that can be retrived before

                //name and last name

                String[] nameAndLastName = customer.getName().split(" "); //since it comes together
                String name = nameAndLastName[0];
                String lastName = nameAndLastName[1];

                //account infos from account subclasses

                String checkingAccountNumber = customer.getAccounts().get(0).getAccountNumber(); //index 0 is checking
                double checkingBalance = customer.getAccounts().get(0).getBalance();
                String savingAccountNumber = customer.getAccounts().get(1).getAccountNumber(); //indedex 1 is savings
                double savingBalance = customer.getAccounts().get(1).getBalance();
                String creditAccountNumber = customer.getAccounts().get(2).getAccountNumber();
                double creditMax = ((Credit) (customer.getAccounts().get(2))).getCreditLimit(); // need to cast Credit because Account does not have this function
                double creditBalance = customer.getAccounts().get(2).getBalance();

                String line = customer.getIdentificationNumber() + "," +
                        name + "," +
                        lastName + "," +
                        customer.getDob() + "," +
                        customer.getAddress() + "," +
                        customer.getPhoneNumber() + "," +
                        checkingAccountNumber + "," +
                        checkingBalance + "," +
                        savingAccountNumber + "," +
                        savingBalance + "," +
                        creditAccountNumber + "," +
                        creditMax + "," +
                        creditBalance;
                writer.write(line);
            }
        }
    }


}// class ends
