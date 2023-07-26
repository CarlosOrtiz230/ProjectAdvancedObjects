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
        List<Customer> customers = new ArrayList<Customer>(); //Array List to hold database during the program 
        
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
            
            //Moving forward main menu 2

            if(isManager){ // main menu for managers
                while(true){ManagerRole.managerOptions(customers, CSVReaderWriter.log);}
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
                        userFunctions.handleInquiry(currentCustomer, x);
                        break; 

                    case "2":
                        userFunctions.handleDeposit(currentCustomer, x);
                        break;
                
                    case "3":
                        userFunctions.handleWithdrawal(currentCustomer, x);
                        break;

                    case "4":
                        userFunctions.handleTransfer(currentCustomer, x);
                        break;

                    case "5":
                        System.out.println("Back to Role Selection");
                        break;

                    case "6":
                        userFunctions.handleThirdPartyPayment(currentCustomer, customers, x);
                        break; //breake case 6
                    
                    case "7":
                        userFunctions.payCreditCard(currentCustomer, x);
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


