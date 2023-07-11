import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String csvFile = "BankUser.CSV";
        String line;

        List<Customer> customers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            int skipfirst12 = 0;
            while ((line = br.readLine()) != null) {
                if(skipfirst12 < 12){ // skip first 12 data
                    skipfirst12++;
                    continue;
                }
                String[] data = line.split(",");

                String identificationNumber = data[0];
                String firstName = data[1];
                String lastName = data[2];
                String dob = data[3];
                String address = data[4];
                String phoneNumber = data[5].replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
                String checkingAccountNum = data[6];
                double checkingStartingBalance = Double.parseDouble(data[7]);
                String savingsAccountNum = data[8];
                double savingsStartingBalance = Double.parseDouble(data[9]);
                String creditAccountNumber = data[10];
                double creditMax = Double.parseDouble(data[11]);
                double creditStartingBalance = Double.parseDouble(data[12]);

                Customer customer = new Customer(firstName + " " + lastName, address);
                Checking checkingAccount = new Checking(checkingAccountNum);
                Savings savingsAccount = new Savings(savingsAccountNum);
                Credit creditAccount = new Credit(creditAccountNumber, creditMax);

                // Set the starting balances
                checkingAccount.deposit(checkingStartingBalance);
                savingsAccount.deposit(savingsStartingBalance);
                creditAccount.deposit(creditStartingBalance);

                // Add the accounts to the customer
                customer.addAccount(checkingAccount);
                customer.addAccount(savingsAccount);
                customer.addAccount(creditAccount);

                // Add the customer to our list
                customers.add(customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
