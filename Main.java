import java.util.*;
import java.io.*;

public class Main {

    /**
         * The main entry point of the program.
         *
         * @param args the command line arguments
    */
    public static void main(String[] args) throws IOException {
        List<String> log = new ArrayList<>();
        String csvFile = "BankUser.CSV"; // Name of DataBase
        List<Customer> customers = new ArrayList<>(); //Array List to hold database during the program 
        customers = CSVReaderWriter.bankUserReader(csvFile);
        Customer currentCustomer = null; //before assigning a customer
        while(true){
        //Check if the user is a manager to change the functionality
   
            boolean isManager = checkIfManager();
    
            // Implement user/password authentication based on cellphone 
            while(currentCustomer == null && !isManager){ //loop while log in 
                currentCustomer = userLogin(customers); //if the function returns true we will continue and the               
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
                Menu.displayCustomerMenu();
                Scanner x = new Scanner(System.in);
                String option = x.nextLine();
                //x.nextLine(); // To skp line

                switch(option.toLowerCase()){
                    case "1":
                        System.out.println("Which account's balance would you like to check? \nA.Savings\nB.Checkings");
                        System.out.print(">");
                        String accountType = x.nextLine();
                        System.out.println("Your current balance is: " + currentCustomer.checkBalance(accountType));
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
                            logTransaction(currentCustomer, depositAccount, "deposit", depositAmount,currentCustomer.checkBalance(depositAccount), log);
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
                            logTransaction(currentCustomer, withdrawAccount, "withdrawal", withdrawAmount,currentCustomer.checkBalance(withdrawAccount),log);
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
                                        logTransaction(currentCustomer, "Checkings", "transfer to Savings", transferAmount,currentCustomer.checkBalance(transferAccount) ,log);
                                        break;
                                    case "b":
                                        currentCustomer.transferMoneyToChecking(transferAmount);
                                        logTransaction(currentCustomer, "Savings", "transfer to Checkings", transferAmount,currentCustomer.checkBalance(transferAccount), log);
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
                                    case "A"://savings
                                        ((Savings)currentCustomer.getAccounts().get(1)).payToThirdParty(customers,name,lastName,reciepientAccountNumber,payAmount); //index 1 is savings
                                        logTransaction(currentCustomer, "Savings", ("transfer to " + name + " " + lastName  + " "), payAmount,currentCustomer.checkBalance("A"), log);
                                        break;
                                    case "B"://checking
                                        ((Checking)currentCustomer.getAccounts().get(0)).payToThirdParty(customers,name,lastName,reciepientAccountNumber,payAmount); //index 1 is savings
                                        logTransaction(currentCustomer, "Checking", ("transfer to " + name + " " + lastName  + " "), payAmount, currentCustomer.checkBalance("B") ,log);
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
                        CSVReaderWriter.writeCustomersToCSV(customers, csvFile);
                        createTextFile(log, "outputBalance.txt");
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



    /**
         * Performs user login by requesting the user's name and phone number, and validating the credentials against
         * the list of customers.
         *
         * @param currentCustomer the current Customer object, which will be updated if the login is successful
         * @param customers       the list of Customer objects to check for user existence and validate credentials
         * @return the updated currentCustomer object if the login is successful, otherwise null
    */
    public static Customer userLogin(List<Customer> customers) throws IOException {
        Scanner x = new Scanner(System.in); //ask the user's name
        System.out.println("Enter your first and last name:");
        System.out.print(">");
        String userName = x.nextLine();
    
        // Find the customer in ArrayList to check existence
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(userName)) {
                return customer; // Return the found customer
            }
        }
        
        System.out.println("xxxx----User not found. Try Again----xxxx");
        return null; // Return null if no customer 
    }//userLogin ends
    

    /**
         * Checks if the user is a bank manager or a bank client based on the user input.
         *
         * @return true if the user is a bank manager, false if the user is a bank client or the program should exit
    */
    public static boolean checkIfManager() {
        Scanner x = new Scanner(System.in);
        System.out.println("are you a Bank Client or Manager?");
        System.out.println("A. Bank Client\nB. Bank Manager\ntype 'exit' to finish the program");
        String role = x.nextLine();
        System.out.print(">");
        if(role.equalsIgnoreCase("exit")){
            System.exit(0);
        }
        if(role.equalsIgnoreCase("A") ||  role.equalsIgnoreCase("a")){ //if the user is a client, return false
            return false;
        }
        else if(role.equalsIgnoreCase("B") || role.equalsIgnoreCase("b")){ //if the user is a manager, return true
            return true;
        }
        else{
            System.out.println("xxxx----this entry is not valid----xxxx");
            return false;
        }
    }
    



    /**
         * Displays and handles the menu options for the bank manager.
         *
         * @param customers the list of Customer objects representing the bank customers
         * @param log       the list of String objects representing the transaction log
     */
    public static void managerOptions(List<Customer> customers, List<String> log) throws IOException{
    Menu.displayManagerMenu(); //display main options
    Scanner userInput = new Scanner(System.in);
    Customer desiredCustomer = null;

    String managerInput = userInput.nextLine();
    
    // Determine customer first
    switch(managerInput.toLowerCase()) {
        case "a":
            desiredCustomer = BankManager.findCustomerByName(userInput, customers);
            break;
        case "b":
            desiredCustomer = BankManager.findCustomerByAccount(userInput, customers);
            break;
        case "exit":
            String csvFile = "BankUser.CSV"; // Name of DataBase
            CSVReaderWriter.writeCustomersToCSV(customers, csvFile);
            createTextFile(log, "outputBalance.txt");
            System.exit(0);//leave program
        default: 
            System.out.println("xxxx----This is not a valid entry----xxxx");
            //userInput.close(); //added
            return;
    }
    
    // If no customer is found, return
    if (desiredCustomer == null) {
        System.out.println("xxxx----Customer or Account was NOT Found----xxxx");
        return;
    }
    
    while(true){
        System.out.println("Which account would you like to inquire?");
        System.out.println("1. Checkings\n2. Savings \n3. Credit\n4. Transaction Log");
        System.out.print(">");
        managerInput = userInput.nextLine();
        switch(managerInput.toLowerCase()){
            case "1":
                System.out.println("\nPrinting Checkings Information:");
                desiredCustomer.printCheckingInfo();
                return;
            case "2":
                System.out.println("\nPrinting Savings Information:");
                desiredCustomer.printSavingsInfo();
                return;
            case "3":
                System.out.println("\nPrinting Credits Information");
                desiredCustomer.printCreditInfo();
                return;
            case "4":
                System.out.println("Log:");
                for (String history : log){
                    System.out.println(history);
                }
                return;
            default:
                System.out.println("xxxx----This is not a valid entry----x");
                return;
        }//switch ends
    }//while ends
} //manager Options


    /**
         * Logs a transaction by creating an entry in the transaction log.
         *
         * @param customer        the Customer object associated with the transaction
         * @param accountType     the type of account involved in the transaction
         * @param transactionType the type of transaction performed
         * @param balance         the current balance after the transaction
         * @param log             the list of String objects representing the transaction log
     */
    public static void logTransaction(Customer customer, String accountType, String transactionType, double ammount, double balance ,List<String> log) {
        String logEntry = "Customer " + customer.getName() + " with account type: " + accountType + " made a " + transactionType + " of " + ammount + " --->Current balance: " + balance;
        log.add(logEntry);
    }
    
    /**
         * Checks if a given string can be parsed as a numeric value.
         *
         * @param str the string to check
         * @return true if the string is numeric, false otherwise
     */
    public static boolean isNumeric(String str) { //to check if an string is numeric
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }



    /**
         * Creates a text file with the given lines of text.
         *
         * @param lines    the list of String objects representing the lines of text
         * @param fileName the name of the text file to create
    */
    public static void createTextFile(List<String> lines, String fileName) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (String line : lines) {
                writer.write(line + "\n");
            }
        }
    }

}// class ends
