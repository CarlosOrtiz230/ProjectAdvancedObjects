import java.util.*;
import java.io.*;
public class ManagerRole{

    /**
     * Displays and handles the menu options for the bank manager.
     *
     * @param customers the list of Customer objects representing the bank customers
     * @param log       the list of String objects representing the transaction log
     */
    public static void managerOptions(List<Customer> customers, List<String> log) throws IOException{
        PrintMenu.displayManagerMenu(); //display main options
        Scanner userInput = new Scanner(System.in);
        Customer desiredCustomer = null;
    
        String managerInput = userInput.nextLine();
        
        // Determine customer first
        switch(managerInput.toLowerCase()) {
            case "1":
                desiredCustomer = BankManager.findCustomerByName(userInput, customers);
                break;
            case "2":
                desiredCustomer = BankManager.findCustomerByAccount(userInput, customers);
                break;
            case "exit":
                String csvFile = "BankUser.CSV"; // Name of DataBase
                CSVReaderWriter.writeCustomersToCSV(customers, csvFile);
                CSVReaderWriter.createTextFile(log, "outputBalance.txt");
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
}