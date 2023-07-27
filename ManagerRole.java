import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagerRole{

    private static IDGenerator idGenerator = new IDGeneratorReal(); 
    private static CreditLimitGenerator creditLimitGenerator = new CreditLimitGeneratorReal(); 

    /**
     * Displays manager options and handles manager-related operations based on user input.
     *
     * @param customers The list of customers in the banking system.
     * @param log       The list of log entries for transactions and operations.
     */
    public static void managerOptions(List<Customer> customers, List<String> log) {
        try {
            PrintMenu.displayManagerMenu(); 
            Scanner userInput = new Scanner(System.in);
            Customer desiredCustomer = null;
        
            String managerInput = userInput.nextLine();
            
            switch(managerInput.toLowerCase()) {
                case "1":
                    desiredCustomer = BankManager.findCustomerByName(userInput, customers);
                    break;
                case "2":
                    desiredCustomer = BankManager.findCustomerByAccount(userInput, customers);
                    break;
                case "3":
                    addNewBankUser(customers, idGenerator, creditLimitGenerator);
                    return;
                case "exit":
                    String csvFile = "BankUser.CSV"; 
                    CSVReaderWriter.writeCustomersToCSV(customers, csvFile);
                    CSVReaderWriter.createTextFile(log, "outputBalance.txt");
                    System.exit(0);
                default: 
                    System.out.println("xxxx----This is not a valid entry----xxxx");
                    return;
            }
            
            if (desiredCustomer == null) {
                System.out.println("xxxx----Customer or Account was NOT Found----xxxx");
                return;
            }
            
            while(true){
                System.out.println("Which account would you like to inquire?");
                System.out.println("1. Checkings\n2. Savings \n3. Credit\n4. Transaction Log\n5. Indivudial Transactions");
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
                            history = history.replace(",", " ");
                            System.out.println(history);
                        }
                        return;

                    case "5":
                        System.out.println(desiredCustomer.getName() + " transactions:\n");
                        for (String history : desiredCustomer.getTransactions()){
                            history = history.replace(",", " ");
                            System.out.println(history);
                        }
                        return;

                    default:
                        System.out.println("xxxx----This is not a valid entry----x");
                        return;
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * Adds a new bank user to the banking system based on user input.
     *
     * @param customers            The list of customers in the banking system.
     * @param idGenerator          The ID generator used to create unique IDs for customers and accounts.
     * @param creditLimitGenerator The credit limit generator used to determine credit limits based on credit scores.
     */
    public static void addNewBankUser(List<Customer> customers, IDGenerator idGenerator, CreditLimitGenerator creditLimitGenerator) {
        try {
            Scanner scanner = new Scanner(System.in);
    
            System.out.println("Enter your name (First Name, Last Name):");
            String fullName = scanner.nextLine();
            String[] nameParts = fullName.split(",");
            if (nameParts.length != 2) {
                throw new IllegalArgumentException("Name must be in the format 'First Name,Last Name'");
            }
            String firstName = nameParts[0].trim();
            String lastName = nameParts[1].trim();
            String name = firstName + " " + lastName;
    
            System.out.println("Enter your date of birth:");
            String dob = scanner.nextLine();
    
            System.out.println("Enter your address (Street, City, State):");
            String fullAddress = scanner.nextLine();
            String[] addressParts = fullAddress.split(",");
            if (addressParts.length != 3) {
                throw new IllegalArgumentException("Address must be in the format 'Street,City,State'");
            }
            String street = addressParts[0].trim();
            String city = addressParts[1].trim();
            String state = addressParts[2].trim();
            String address = street + ", " + city + ", " + state;
    
            System.out.println("Enter your phone number:");
            String phoneNumberDivided = scanner.nextLine();
    
            System.out.println("Enter your credit score:");
            int creditScore = scanner.nextInt();
            scanner.nextLine(); 
    
            String identificationNumber = idGenerator.generateID();
            String creditAccountNumber = idGenerator.generateAccountID();
            String savingAccountNumber = idGenerator.generateAccountID();
            String checkingAccountNumber = idGenerator.generateAccountID();
    
            double creditLimit = creditLimitGenerator.generateCreditLimit(creditScore);
    
            Customer newCustomer = new Customer(name, dob, address, identificationNumber, phoneNumberDivided);
            Checking checkingaccount = new Checking(checkingAccountNumber);
            Savings savingsAccount = new Savings(savingAccountNumber);
            Credit creditAccount = new Credit(creditAccountNumber, creditLimit);
    
            checkingaccount.deposit(0);
            savingsAccount.deposit(0);
            creditAccount.deposit(creditLimit);
    
            newCustomer.addAccount(checkingaccount);
            newCustomer.addAccount(savingsAccount);
            newCustomer.addAccount(creditAccount);
            
            customers.add(newCustomer);
    
            System.out.println("New bank user added successfully!");
        } catch (Exception e) {
            System.out.println("An error occurred while adding a new bank user: " + e.getMessage());
            e.printStackTrace();
        }
    }    
}
