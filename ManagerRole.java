import java.util.*;
import java.io.*;
public class ManagerRole{

    private static IDGenerator idGenerator = new IDGeneratorReal(); 
    private static CreditLimitGenerator creditLimitGenerator = new CreditLimitGeneratorReal(); 
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
            case "3":
                addNewBankUser(customers, idGenerator, creditLimitGenerator);
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

    public static void addNewBankUser(List<Customer> customers, IDGenerator idGenerator, CreditLimitGenerator creditLimitGenerator) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your name:");
        String name = scanner.nextLine();

        System.out.println("Enter your date of birth:");
        String dob = scanner.nextLine();

        System.out.println("Enter your address (including city and state):");
        String address = scanner.nextLine();

        System.out.println("Enter your phone number:");
        String phoneNumberDivided = scanner.nextLine();

        System.out.println("Enter your credit score:");
        int creditScore = scanner.nextInt();
        scanner.nextLine(); // consume newline left-over

        // Generate an identification number
        String identificationNumber = idGenerator.generateID();
        String creditAccountNumber = idGenerator.generateID();
        String savingAccountNumber = idGenerator.generateID();
        String checkingAccountNumber = idGenerator.generateID();

        // Generate a credit limit based on the credit score
        double creditLimit = creditLimitGenerator.generateCreditLimit(creditScore);

        // Create a new Customer object
        Customer newCustomer = new Customer(name, dob, address, identificationNumber, phoneNumberDivided);
        Checking checkingaccount = new Checking(checkingAccountNumber);
        Savings savingsAccount = new Savings(savingAccountNumber);
        Credit creditAccount = new Credit(creditAccountNumber, creditLimit);

        //Set the starting balances of 0
        checkingaccount.deposit(0);
        savingsAccount.deposit(0);
        creditAccount.deposit(creditLimit);

        //Add the accounts to the customer
        newCustomer.addAccount(checkingaccount);
        newCustomer.addAccount(savingsAccount);
        newCustomer.addAccount(creditAccount);
        
        // Add the new customer to the list of customers
        customers.add(newCustomer);

        System.out.println("New bank user added successfully!");
    }

    
}