import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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

            writer.close();
            System.out.println("Bank statement generated successfully!");
            System.out.println("--------------------------------------------\n\n")
        } catch (IOException e) {
            System.out.println("An error occurred while generating the bank statement: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
