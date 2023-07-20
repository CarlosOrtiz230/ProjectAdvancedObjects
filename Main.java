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
   
            boolean isManager = RoleSelection.checkIfManager();
    
            // Implement user/password authentication based on cellphone 
            while(currentCustomer == null && !isManager){ //loop while log in 
                currentCustomer = RoleSelection.userLogin(customers); //if the function returns true we will continue and the               
            }
            
            //Moving forward main menu

            if(isManager){ // main menu for managers
                while(true){
                    ManagerRole.managerOptions(customers, log);
                }
            }
            isManager = false;

            //main menu for customers

            while(true){
                PrintMenu.displayCustomerMenu();
                Scanner x = new Scanner(System.in);
                String option = x.nextLine();
                x.nextLine(); // To skp line

                switch(option.toLowerCase()){
                    case "1":
                        System.out.println("Which account's balance would you like to check? \n1.Savings\n2.Checkings");
                        System.out.print(">");
                        String accountType = x.nextLine();
                        System.out.println("Your current balance is: " + currentCustomer.checkBalance(accountType));
                        break;

                    case "2":
                        System.out.println("To which account would you like to deposit money? \n1.Savings\n2.Checkings");
                        System.out.print(">");
                        String depositAccount = x.nextLine().trim().toUpperCase();
                        System.out.println("Enter the amount you want to deposit:");
                        System.out.print(">");
                        String depositInput = x.nextLine(); //to avoid crashing
                        double depositAmount;
                        if (interfaceClass.isNumeric(depositInput)) {
                            depositAmount = Double.parseDouble(depositInput);
                        } else {
                            System.out.println("xxxx----Invalid input. Please enter a valid number.----xxxx");
                            break; //exit the program
                        }
                        x.nextLine(); // Consume newline left-over
                        if(depositAccount.equalsIgnoreCase("1") || depositAccount.equalsIgnoreCase("2")){
                            currentCustomer.deposit(depositAccount, depositAmount);
                            System.out.println("Deposit Successful!");
                            logTransaction(currentCustomer, depositAccount, "deposit", depositAmount,currentCustomer.checkBalance(depositAccount), log);
                        } else {
                            System.out.println("xxxx----Invalid account type entered! Please try again.----xxxx");
                        }
                        break;
                
                    case "3":
                        System.out.println("From which account you would like to withdraw money? \n1.Savings\n2.Checkings");
                        //System.out.print(">");
                        String withdrawAccount = x.nextLine();
                        if (withdrawAccount.equalsIgnoreCase("1") || withdrawAccount.equalsIgnoreCase("2")){
                            System.out.println("Enter the amount you want to withdraw:");
                            System.out.print(">");
                            String withdrawInput =  x.nextLine();
                            double withdrawAmount;
                            if (interfaceClass.isNumeric(withdrawInput)) {
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
                            System.out.println("From which account would you like to transfer?\n1.Savings to Checkings \n2.Checkings to Savings");
                            System.out.print(">"); 
                            String transferAccount = x.nextLine().trim();
                            System.out.println("Enter the amount you would like to transfer between your accounts:");
                            System.out.print(">");
                            String transferInput = x.nextLine();
                            double transferAmount;
                            if (interfaceClass.isNumeric(transferInput)) {
                                transferAmount = Double.parseDouble(transferInput);
                             } else {
                            System.out.println("xxxx----Invalid input. Please enter a valid number.----xxxx");
                            break; //exit the program
                            }
                            x.nextLine(); // This line is added to consume the newline character
                            if (transferAccount.equalsIgnoreCase("1") || transferAccount.equalsIgnoreCase("2")){
                                switch (transferAccount.toLowerCase()) {
                                    case "1"://savings
                                        currentCustomer.transferMoneyToSaving(transferAmount);
                                        logTransaction(currentCustomer, "Checkings", "transfer to Savings", transferAmount,currentCustomer.checkBalance(transferAccount) ,log);
                                        break;
                                    case "2":
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
                            System.out.println("From which account would you like to pay/deposit thirdParty?\n1.Savings\n2.Checkings");
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
                            if (interfaceClass.isNumeric(payInput)){
                                payAmount = Double.parseDouble(payInput);
                             } else {
                            System.out.println("xxxx----Invalid input. Please enter a valid number.----xxxx");
                            break; //exit the program
                            }
                            x.nextLine(); // This line is added to consume the newline character
                            if (payAccount.equalsIgnoreCase("1") || payAccount.equalsIgnoreCase("2")){
                                switch (payAccount.toLowerCase()) {
                                    case "1"://savings
                                        ((Savings)currentCustomer.getAccounts().get(1)).payToThirdParty(customers,name,lastName,reciepientAccountNumber,payAmount); //index 1 is savings
                                        logTransaction(currentCustomer, "Savings", ("transfer to " + name + " " + lastName  + " "), payAmount,currentCustomer.checkBalance("1"), log);
                                        break;
                                    case "2"://checking
                                        ((Checking)currentCustomer.getAccounts().get(0)).payToThirdParty(customers,name,lastName,reciepientAccountNumber,payAmount); //index 1 is savings
                                        logTransaction(currentCustomer, "Checking", ("transfer to " + name + " " + lastName  + " "), payAmount, currentCustomer.checkBalance("2") ,log);
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
                        CSVReaderWriter.createTextFile(log, "outputBalance.txt");
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
   
//complementary methods start -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-===-=-=-=


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
    


}// class ends
