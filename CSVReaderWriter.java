import java.io.*;
import java.util.*;

/**
 * The CSVReaderWriter class provides methods to read customer data from a CSV file and manage transaction logs.
 */
public class CSVReaderWriter {
    // Contains all information that might be printed
    public static List<String> log = new ArrayList<String>();
    public static List<String> transactions = new ArrayList<String>(); // ArrayList to create transactions.csv file

    /**
     * Reads customer data from a CSV file and creates a list of Customer objects with their associated accounts.
     *
     * @param csvFile The file path of the CSV file containing customer data.
     * @return A list of Customer objects representing the customers read from the CSV file.
     * @throws IOException If an error occurs while reading the CSV file.
     */
    public static List<Customer> bankUserReader(String csvFile) throws IOException {
        String line;
        List<Customer> customers = new ArrayList<>();
        AccountFactory accountFactory = new AccountFactory(); // Create an instance of AccountFactory
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine(); //skip first line
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String identificationNumber = data[0];
                String firstName = data[1];
                String lastName = data[2];
                String dob = data[3];
                String address = data[4] + "," + data[5] + "," + data[6]; // Combine address
                String phoneNumber = data[7];
                String checkingAccountNum = data[8];
                double checkingStartingBalance = Double.parseDouble(data[9]);
                String savingsAccountNum = data[10];
                double savingsStartingBalance = Double.parseDouble(data[11]);
                String creditAccountNumber = data[12];
                double creditLimit = Double.parseDouble(data[13]);
                double creditStartingBalance = Double.parseDouble(data[14]);
    
                Customer customer = new Customer(firstName + " " + lastName, dob,address, identificationNumber, phoneNumber);
                Checking checkingAccount = (Checking) accountFactory.createAccount("Checking", checkingAccountNum);
                Savings savingsAccount = (Savings) accountFactory.createAccount("Savings", savingsAccountNum);
                Credit creditAccount = (Credit) accountFactory.createAccount("Credit", creditAccountNumber, creditLimit);
    
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
    

    /**
     * This function makes the global transactions to a .csv file
     * @param customers
     * @param csvFile
     * @throws IOException
     */
    public static void writeCustomersToCSV(List<Customer> customers, String csvFile) throws IOException {
        //constant
        final String CSV_HEADER = "Identification Number,First Name,Last Name,Date of Birth,Address,Phone Number," +
        "Checking Account Number,Checking Starting Balance," +
        "Savings Account Number,Savings Starting Balance," +
        "Credit Account Number,Credit Max,Credit Starting Balance";

        try (FileWriter writer = new FileWriter(csvFile, false)) {
            writer.write(CSV_HEADER);
            writer.write("\n"); // Add a new line after the header
    
            // Write the customer data in the CSV
            for (Customer customer : customers) {
                // Get customer information
                String identificationNumber = customer.getIdentificationNumber();
                String[] nameAndLastName = customer.getName().split(" ");
                String firstName = nameAndLastName[0];
                String lastName = nameAndLastName[1];
                String dob = customer.getDob();
                String address = customer.getAddress();
                String phoneNumber = customer.getPhoneNumber();
    
                // Account info from account subclasses
                String checkingAccountNumber = customer.getAccounts().get(0).getAccountNumber(); // Index 0 is checking
                double checkingBalance = customer.getAccounts().get(0).getBalance();
                String savingAccountNumber = customer.getAccounts().get(1).getAccountNumber(); // Index 1 is savings
                double savingBalance = customer.getAccounts().get(1).getBalance();
                String creditAccountNumber = customer.getAccounts().get(2).getAccountNumber();
                double creditLimit = ((Credit) (customer.getAccounts().get(2))).getCreditLimit(); // Need to cast Credit because Account does not have this function
                double creditBalance = customer.getAccounts().get(2).getBalance();
    
                String line = identificationNumber + "," +
                        firstName + "," +
                        lastName + "," +
                        dob + "," +
                        address + "," +
                        phoneNumber + "," +
                        checkingAccountNumber + "," +
                        checkingBalance + "," +
                        savingAccountNumber + "," +
                        savingBalance + "," +
                        creditAccountNumber + "," +
                        creditLimit + "," +
                        creditBalance;
    
                writer.write(line);
                writer.write("\n");
            }
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

    /**
         * Logs a transaction by creating an entry in the transaction log.
         *
         * @param customer        the Customer object associated with the transaction
         * @param accountType     the type of account involved in the transaction
         * @param transactionType the type of transaction performed
         * @param balance         the current balance after the transaction
         * @param log             the list of String objects representing the transaction log
     */
    
    public static void logTransaction(Customer customer ,Customer reciever, String accountType, String recieverAccountType, String transactionType, double ammount, double balance ,List<String> log,List<String> transaction) {
        StringBuilder logEntry = new StringBuilder();
        logEntry.append("Customer ").append(customer.getName()).append(" from: ").append(accountType).append(" made a ").append(transactionType).append(" of ").append(ammount).append(" --->Current balance: ").append(balance);
        log.add(logEntry.toString());
    
        // THIS IS FOR .CSV ONLY
        String[] tokens = customer.getName().split(" "); // Assuming the name is in the format "First Name Last Name"
        String name = tokens[0];
        String lastName = tokens[1];

        String[] tokens2 = reciever.getName().split(" "); // Assuming the name is in the format "First Name Last Name"
        String receiverFirstName = tokens2[0];
        String receiverLastName = tokens2[1];
       
        // Prepare the CSV entry based on the transaction type
        StringBuilder currentTransaction = new StringBuilder();
        //pay
        if (transactionType.equalsIgnoreCase("pay")||transactionType.equalsIgnoreCase(TransactionTypes.PAY)) {
            // Pay transaction requires all information
            currentTransaction.append(name).append(",").append(lastName).append(",").append(accountType).append(",").append(transactionType).append(",");
            currentTransaction.append(receiverFirstName).append(",").append(receiverLastName).append(",").append(recieverAccountType).append(",").append(ammount);
        //deposist  
        } else if (transactionType.equalsIgnoreCase("deposit")||transactionType.equalsIgnoreCase(TransactionTypes.DEPOSIT)) {
            // Deposit transaction only requires receiver information
            currentTransaction.append(",").append(",").append(",").append(transactionType).append(",").append(receiverFirstName).append(",");
            currentTransaction.append(receiverFirstName).append(",").append(receiverLastName).append(",").append(recieverAccountType).append(",").append(ammount);
        //transfer  
        }else if(transactionType.equalsIgnoreCase("transfers")||transactionType.equalsIgnoreCase("transfer")||transactionType.equalsIgnoreCase(TransactionTypes.TRANSFER)){
            currentTransaction.append(name).append(",").append(lastName).append(",").append(accountType).append(",").append(transactionType).append(",");
            currentTransaction.append(receiverFirstName).append(",").append(receiverLastName).append(",").append(recieverAccountType).append(",").append(ammount);  
        //withdraw
        }else if(transactionType.equalsIgnoreCase("withdraw")||transactionType.equalsIgnoreCase(TransactionTypes.WITHDRAW)){
            currentTransaction.append(name).append(",").append(lastName).append(",").append(accountType).append(",").append(transactionType).append(",");
            currentTransaction.append(",").append(",").append(ammount);  
        //inquire
        }else if(transactionType.equalsIgnoreCase("inquire")||transactionType.equalsIgnoreCase(TransactionTypes.INQUIRE)){
            currentTransaction.append(name).append(",").append(lastName).append(",").append(",").append(transactionType).append(",").append(accountType);
            currentTransaction.append(",").append(",");  
        
        }else{System.out.println("Something went wrong in log transactions\naccount Type not detected");}
        
        
        transaction.add(currentTransaction.toString());
        customer.addTransaction(currentTransaction.toString()); //individual transaction
    }//method
}//class


