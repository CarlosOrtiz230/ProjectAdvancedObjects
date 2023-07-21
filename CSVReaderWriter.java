import java.io.*;
import java.util.*;

public class CSVReaderWriter {
    public static List<Customer> bankUserReader(String csvFile) throws IOException {
        String line;
        List<Customer> customers = new ArrayList<>();
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
                String phoneNumberDivided = data[7];
                String checkingAccountNum = data[8];
                double checkingStartingBalance = Double.parseDouble(data[9]);
                String savingsAccountNum = data[10];
                double savingsStartingBalance = Double.parseDouble(data[11]);
                String creditAccountNumber = data[12];
                double creditMax = Double.parseDouble(data[13]);
                double creditStartingBalance = Double.parseDouble(data[14]);

                Customer customer = new Customer(firstName + " " + lastName, dob,address, phoneNumber, identificationNumber, phoneNumberDivided);
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
                double creditMax = ((Credit) (customer.getAccounts().get(2))).getCreditLimit(); // Need to cast Credit because Account does not have this function
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
                        creditMax + "," +
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
}
