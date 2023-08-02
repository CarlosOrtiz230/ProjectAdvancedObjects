import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class BankStatement{

    /**
     * Generates a bank statement for the given customer.
     * 
     * @param customer The customer
     * @param filename file to which the bank statement will be written.
     */
    public void generateBankStatement(Customer customer, String filename) {
        try {
            FileWriter writer = new FileWriter(filename);

            // Writing the customer's personal details
            writer.write("Bank Statement\n");
            writer.write("==============\n\n");
            writer.write("Full Name: " + customer.getName() + "\n");
            writer.write("Address: " + customer.getAddress() + "\n");
            writer.write("Phone Number: " + customer.getPhoneNumber() + "\n");
            writer.write("Date of Birth: " + customer.getDob() + "\n\n");

            // Writing account details for each account
            List<Account> customerAccounts = customer.getAccounts();
            for (Account account : customerAccounts) {
                writer.write("Account Type: " + account.getClass().getSimpleName() + "\n");
                writer.write("Account Number: " + account.getAccountNumber() + "\n");
                writer.write("Balance: " + account.getBalance() + "\n\n");
            }

            // Writing all transactions
            writer.write("Transactions:\n\n");

            // Read the transaction log file if there is already one made.
            String logFileName = customer.getName().replace(" ", "_") + "_transactions.txt";
            File logFile = new File(logFileName);
            if (logFile.exists()) {
                Scanner scanner = new Scanner(logFile);
                while (scanner.hasNextLine()) {
                    String transaction = scanner.nextLine();
                    writer.write(transaction + "\n");
                }
                scanner.close();
            } else {
                writer.write("No transactions found.\n");
            }

            writer.close();
            System.out.println("Bank statement generated successfully!");
            System.out.println("--------------------------------------------\n\n");
        } catch (IOException e) {
            System.out.println("An error occurred while generating the bank statement: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
