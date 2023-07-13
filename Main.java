import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String csvFile = "BankUser.CSV"; // Name of DataBase
        List<Customer> customers = new ArrayList<>(); //Array List to hold database during the program 
        customers = bankUserReader(csvFile);

        // Implement user password authentication based on cellphone and DOB here

        Scanner x = new Scanner(System.in);
        System.out.println("Enter your first and last name:");
        String userName = x.nextLine();

        Customer currentCustomer = null;

        // Find the customer in ArrayList
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(userName)) {
                currentCustomer = customer;
                break;
            }
        }

        if (currentCustomer == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println("A. Bank Client\nB. Bank Manager");
        String role = x.nextLine();

        switch(role.toLowerCase()){
            case "a":
                while(true){
                    System.out.println("What would you like to do today?  \n1. Inquiry about a balance \n2. Deposit money to an account \n3. Withdraw money from an account \n4. Transfer money between accounts \n5. EXIT");
                    int option = x.nextInt();
                    x.nextLine(); // To skp line

                    switch(option){
                        case 1:
                            System.out.println("Which account's balance would you like to check? \nA.Savings\nB.Checkings");
                            String accountType = x.nextLine();
                            System.out.println("Your current balance is: " + currentCustomer.checkBalance(accountType));
                            break;
                        case 2:
                            System.out.println("To which account would you like to deposit money? \nA.Savings\nB.Checkings");
                            String depositAccount = x.nextLine().trim().toUpperCase();
                            System.out.println("Enter the amount you want to deposit:");
                            double depositAmount = x.nextDouble();
                            x.nextLine(); // Consume newline left-over
                            if(depositAccount.equals("A") || depositAccount.equals("B")){
                                currentCustomer.deposit(depositAccount, depositAmount);
                                System.out.println("Deposit Successful!");
                            } else {
                                System.out.println("Invalid account type entered! Please try again.");
                            }
                            break;
                    
                        case 3:
                            System.out.println("From which account you would like to withdraw money? \nA.Savings\nB.Checkings");
                            String withdrawAccount = x.nextLine();
                            System.out.println("Enter the amount you want to withdraw:");
                            double withdrawAmount = x.nextDouble();
                            //currentCustomer.withdraw(withdrawAccount, withdrawAmount);
                            break;
                        case 4:
                            System.out.println("Enter the amount you would like to transfer from Checking to Savings account:");
                            double transferAmount = x.nextDouble();
                            currentCustomer.transferMoneyToSaving(transferAmount);
                            break;
                        case 5:
                            System.out.println("Exiting... Bye!");
                            System.exit(0);
                    }
                }//case a ends
            case "b":
                while(true){
                    // Manager tasks
                }
        }
    }

    public static List<Customer> bankUserReader(String csvFile) throws IOException {
        String line;
        List<Customer> customers = new ArrayList<Customer>();
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
                String checkingAccountNum = data[8];
                double checkingStartingBalance = Double.parseDouble(data[9]);
                String savingsAccountNum = data[10];
                double savingsStartingBalance = Double.parseDouble(data[11]);
                String creditAccountNumber = data[12];
                double creditMax = Double.parseDouble(data[13]);
                double creditStartingBalance = Double.parseDouble(data[14]);

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
        return customers;
    }//file reader ends

}// class ends
