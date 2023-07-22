import java.util.*;
import java.io.*;

public class Main {

    /**
         * The main entry point of the program.
         *
         * @param args the command line arguments
    */
    public static void main(String[] args) throws IOException {
        
        String csvFile;
        List<Customer> customers = new ArrayList<>(); //Array List to hold database during the program 
        
        try { //try catch for the dataBase creation 
            csvFile = "BankUser.CSV";
            customers = CSVReaderWriter.bankUserReader(csvFile);
        }catch (Exception e) {
            System.out.println("Error during the creation of the dataBase: " +e.getMessage() );
            return;
        }
        
        
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
                    ManagerRole.managerOptions(customers, CSVReaderWriter.log);
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
                    case "1"://desposit 
                        PrintMenu.whichAccountInquire(); 
                        String accountType = x.nextLine();
                        System.out.println("Your current balance is: " + currentCustomer.checkBalance(accountType));
                        CSVReaderWriter.logTransaction(currentCustomer, new Customer(), accountType, "NA","inquire",0.0,currentCustomer.checkBalance(accountType),CSVReaderWriter.log, CSVReaderWriter.transactions);
                        break; 

                    case "2":
                        PrintMenu.whichAccountDeposit();
                        String depositAccount = x.nextLine().trim().toUpperCase();
                        PrintMenu.enterAmmountDeposit();
                        String depositInput = x.nextLine(); //to avoid crashing
                        double depositAmount;

                        if(interfaceClass.isNumeric(depositInput)) {
                            depositAmount = Double.parseDouble(depositInput);
                        }else{
                            System.out.println("\nxxxx----Invalid input. Please enter a valid number.----xxxx");
                            break; //exit the program
                        }
                        x.nextLine(); // Consume newline left-over
                        if(depositAccount.equalsIgnoreCase("1") || depositAccount.equalsIgnoreCase("2")){
                            currentCustomer.deposit(depositAccount, depositAmount);

                            // sender Name, recieverName, senderAccountType, recieverAccountType,transactionsType,ammount,balance ,log,transactions
                            CSVReaderWriter.logTransaction(new Customer(),currentCustomer, "NA", depositAccount,"deposit", depositAmount,currentCustomer.checkBalance(depositAccount),CSVReaderWriter.log, CSVReaderWriter.transactions);
                        } else {
                            System.out.println("\nxxxx----Invalid account type entered! Please try again.----xxxx");
                        }
                        break;
                
                    case "3":
                        System.out.println("From which account you would like to withdraw money? \n1.Savings\n2.Checkings");
                        System.out.print(">");
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
                            CSVReaderWriter.logTransaction(currentCustomer, currentCustomer ,withdrawAccount, withdrawAccount,"withdrawal", withdrawAmount,currentCustomer.checkBalance(withdrawAccount),CSVReaderWriter.log,CSVReaderWriter.transactions);
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
                                        CSVReaderWriter.logTransaction(currentCustomer,currentCustomer, "Checkings", "Savings","transfer" ,transferAmount,(Double)(currentCustomer.checkBalance(transferAccount)) , CSVReaderWriter.log,CSVReaderWriter.transactions);
                                        break;
                                    case "2":
                                        currentCustomer.transferMoneyToChecking(transferAmount);
                                        CSVReaderWriter.logTransaction(currentCustomer,currentCustomer, "Savings", "Checkings","Transfer" ,transferAmount,currentCustomer.checkBalance(transferAccount), CSVReaderWriter.log,CSVReaderWriter.transactions);
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
                                Customer reciever = BankManager.findCustomerByName(x, customers);
                                switch (payAccount.toLowerCase()) {
                                    case "1"://savings
                                        ((Savings)currentCustomer.getAccounts().get(1)).payToThirdParty(customers,name,lastName,reciepientAccountNumber,payAmount); //index 1 is savings
                                        
                                        CSVReaderWriter.logTransaction(currentCustomer,reciever, "Savings","NEEDS TO ADD TYPE OF ACCOUNT", ("transfer to " + name + " " + lastName  + " "), payAmount,currentCustomer.checkBalance("1"), CSVReaderWriter.log,CSVReaderWriter.transactions);
                                        break;
                                    case "2"://checking
                                        ((Checking)currentCustomer.getAccounts().get(0)).payToThirdParty(customers,name,lastName,reciepientAccountNumber,payAmount); //index 1 is savings
                                       
                                        CSVReaderWriter.logTransaction(currentCustomer,reciever, "Checking", "NEEDS TO ADD TYPE OF ACCOUNT", ("transfer to " + name + " " + lastName  + " "), payAmount, currentCustomer.checkBalance("2") ,CSVReaderWriter.log,CSVReaderWriter.transactions);
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
                        CSVReaderWriter.createTextFile(CSVReaderWriter.log, "outputBalance.txt");
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
}   //main method ends
}// class ends
