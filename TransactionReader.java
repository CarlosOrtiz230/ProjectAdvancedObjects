import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class TransactionReader {
    private String filename;
    private List<Customer> customers;

    public TransactionReader(String filename, List<Customer> customers) {
        this.filename = filename;
        this.customers = customers;
    }

    public void processTransactions() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.filename))) {
            br.readLine(); //skip first line
            String line;
            while ((line = br.readLine()) != null) {
                
                String[] values = line.split(",");
    
                String fromFirstName = values[0]; //gets first elements
                String fromLastName = values[1];
                String fromWhere = values[2];
                String action = values[3];
                String toFirstName = values.length > 4 ? values[4] : null; //assigns null if no recipient is on csv and prevent ArrayIndexOutOfBoundsExceptions
                String toLastName = values.length > 5 ? values[5] : null;
                String toWhere = values.length > 6 ? values[6] : null;
                double amount = values.length > 7 ? Double.parseDouble(values[7]) : 0.0; 
    
                // Call the appropriate method based on the action
                switch (action.toLowerCase()) {
                    case "pays":
                        Customer payer = findCustomer(fromFirstName, fromLastName);
                        Customer payee = findCustomer(toFirstName, toLastName);
                        if (payer != null && payee != null) {
                            if (AccountTypes.isValidAccountType(fromWhere)) {
                                fromWhere = AccountTypes.getAccountType(fromWhere);
                                double currentBalance = payer.checkBalance(fromWhere);
                                if(amount > currentBalance){
                                    System.out.println("Amount to pay is more than the balance");
                                } else {
                                    payer.withdraw(fromWhere, amount);
                                    // Assuming that the payee account will be deposited into the same type of account from which the payer is paying.
                                    payee.deposit(fromWhere, amount);
                                    CSVReaderWriter.logTransaction(payer, payee, fromWhere, fromWhere, "pays", amount, payer.checkBalance(fromWhere), CSVReaderWriter.log, CSVReaderWriter.transactions);
                                }
                            } else {
                                System.out.println("Not a valid account Type");
                            }
                        } else {
                            System.out.println("Either payer or payee customer not found.");
                        }
                        break;
                    case "transfers":
                        Customer transferCustomer = findCustomer(fromFirstName, fromLastName);
                        if(transferCustomer != null) {
                            if(AccountTypes.isValidAccountType(fromWhere)) {
                                fromWhere = AccountTypes.getAccountType(fromWhere);
                                String receiverAccount;
                                if(fromWhere.equals(AccountTypes.CHECKING)){
                                    receiverAccount = AccountTypes.SAVINGS;
                                } else {
                                    receiverAccount = AccountTypes.CHECKING;
                                }
                                transferCustomer.deposit(receiverAccount, amount);
                                transferCustomer.withdraw(fromWhere, amount);
                                double currentBalance = transferCustomer.checkBalance(receiverAccount);
                                CSVReaderWriter.logTransaction(transferCustomer, transferCustomer, fromWhere, receiverAccount, TransactionTypes.TRANSFER, amount, currentBalance, CSVReaderWriter.log, CSVReaderWriter.transactions);
                            } else {
                                System.out.println("Not a valid account Type");
                            }
                        }
                        break;
                    case "inquires":
                        Customer inquiryCustomer;
                    
                        if (fromFirstName != null && fromLastName != null && fromWhere != null && !fromWhere.trim().isEmpty()) {
                            inquiryCustomer = findCustomer(fromFirstName, fromLastName);
                        } else {
                            System.out.println("From customer fields or account type are empty");
                            break;
                        }
                    
                        if (inquiryCustomer != null) {
                            String accountType;
                            if (AccountTypes.isValidAccountType(fromWhere)) {
                                accountType = AccountTypes.getAccountType(fromWhere);
                                double currentBalance = inquiryCustomer.checkBalance(accountType);
                                System.out.println("Your current " + accountType + " balance is: " + currentBalance);
                                CSVReaderWriter.logTransaction(inquiryCustomer, inquiryCustomer, accountType, accountType, TransactionTypes.INQUIRE, 0.0, currentBalance, CSVReaderWriter.log, CSVReaderWriter.transactions);
                            } else {
                                System.out.println("Not a valid account Type");
                            }
                        }
                        break;
                    
                    
                    case "withdraws":
                        Customer withdrawCustomer;
                        if (fromFirstName != null && fromLastName != null && fromWhere != null && !fromWhere.trim().isEmpty()) {
                            withdrawCustomer = findCustomer(fromFirstName, fromLastName);
                        } else if (toFirstName != null && toLastName != null && toWhere != null && !toWhere.trim().isEmpty()) {
                            withdrawCustomer = findCustomer(toFirstName, toLastName);
                        } else {
                            System.out.println("Both From and To customer fields or account types are empty");
                            break;
                        }
                        if (withdrawCustomer != null) {
                            String accountType;
                            if (fromWhere != null && AccountTypes.isValidAccountType(fromWhere)) {
                                accountType = AccountTypes.getAccountType(fromWhere);
                            } else if (toWhere != null && AccountTypes.isValidAccountType(toWhere)) {
                                accountType = AccountTypes.getAccountType(toWhere);
                            } else {
                                System.out.println("Not a valid account Type");
                                break;
                            }
                            double currentBalance = withdrawCustomer.checkBalance(accountType);
                            if (amount > currentBalance) {
                                System.out.println("Amount to withdraw is more than the balance");
                            } else {
                                withdrawCustomer.withdraw(accountType, amount);
                                currentBalance = withdrawCustomer.checkBalance(accountType);
                                System.out.println("Withdraw successful!");
                                CSVReaderWriter.logTransaction(withdrawCustomer, withdrawCustomer, accountType, accountType, TransactionTypes.WITHDRAW, amount, currentBalance, CSVReaderWriter.log, CSVReaderWriter.transactions);
                            }
                        }
                        break;
                    
                    case "deposits":
                        Customer depositCustomer;
                        if (fromFirstName != null && fromLastName != null && fromWhere != null && !fromWhere.trim().isEmpty()) {
                            depositCustomer = findCustomer(fromFirstName, fromLastName);
                        } else {
                            depositCustomer = findCustomer(toFirstName, toLastName);
                        }
                        if (depositCustomer != null) {
                            String accountType;
                            if (fromWhere != null && AccountTypes.isValidAccountType(fromWhere)) {
                                accountType = AccountTypes.getAccountType(fromWhere);
                            } else if (toWhere != null && AccountTypes.isValidAccountType(toWhere)) {
                                accountType = AccountTypes.getAccountType(toWhere);
                            } else {
                                System.out.println("Not a valid account Type");
                                break;
                            }
                            depositCustomer.deposit(accountType, amount);
                            double currentBalance = depositCustomer.checkBalance(accountType);
                            CSVReaderWriter.logTransaction(depositCustomer, depositCustomer, accountType, accountType, TransactionTypes.DEPOSIT, amount, currentBalance, CSVReaderWriter.log, CSVReaderWriter.transactions);
                        }
                        break;
                    
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
            e.printStackTrace();
        }
    }
    

    private Customer findCustomer(String firstName, String lastName) {
        for(Customer customer : customers) {
            String[] names = customer.getName().split(" ");
            if(names[0].equals(firstName) && names[1].equals(lastName)) {
                return customer;
            }
        }
        return null;
    }
    
    
}
