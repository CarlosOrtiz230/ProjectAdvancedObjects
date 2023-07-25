import java.util.*;


public class userFunctions{
 
    public static void handleInquiry(Customer currentCustomer, Scanner x) {
        PrintMenu.whichAccountInquire(); 
        String accountType = x.nextLine();
        System.out.println("Your current balance is: " + currentCustomer.checkBalance(accountType));
        CSVReaderWriter.logTransaction(currentCustomer, new Customer(), AccountTypes.CHECKING, "NA", TransactionTypes.INQUIRE, 0.0, currentCustomer.checkBalance(AccountTypes.CHECKING), CSVReaderWriter.log, CSVReaderWriter.transactions);
    }

    public static void handleDeposit(Customer currentCustomer, Scanner x) {
        PrintMenu.whichAccountDeposit();
        String depositAccount = x.nextLine().trim().toUpperCase();
        PrintMenu.enterAmmountDeposit();
        String depositInput = x.nextLine(); //to avoid crashing
        double depositAmount;

        if(interfaceClass.isNumeric(depositInput)) {
            depositAmount = Double.parseDouble(depositInput);
        } else {
            System.out.println("\nxxxx----Invalid input. Please enter a valid number.----xxxx");
            return; //exit the method
        }
        x.nextLine(); // Consume newline left-over
        if(depositAccount.equalsIgnoreCase(AccountTypes.CHECKING) || depositAccount.equalsIgnoreCase(AccountTypes.SAVINGS)){
            currentCustomer.deposit(depositAccount, depositAmount);
            CSVReaderWriter.logTransaction(new Customer(),currentCustomer, "NA", depositAccount, TransactionTypes.DEPOSIT, depositAmount,currentCustomer.checkBalance(depositAccount),CSVReaderWriter.log, CSVReaderWriter.transactions);
        }else {
            System.out.println("\nxxxx----Invalid account type entered! Please try again.----xxxx");
        }
    }   

    public static void handleWithdrawal(Customer currentCustomer, Scanner x) {
        System.out.println("From which account you would like to withdraw money? \n1.Savings\n2.Checkings");
        System.out.print(">");
        String withdrawAccount = x.nextLine();
        if (withdrawAccount.equalsIgnoreCase("1") || withdrawAccount.equalsIgnoreCase("2")){
            System.out.println("Enter the amount you want to withdraw:");
            System.out.print(">");
            String withdrawInput =  x.nextLine();
            double withdrawAmount;
        if (interfaceClass.isNumeric(withdrawInput)) {
            withdrawAmount = Double.parseDouble(withdrawInput);
        }else {
            System.out.println("xxxx----Invalid input. Please enter a valid number.----xxxx");
            return; //exit the program
        }
        currentCustomer.withdraw(withdrawAccount, withdrawAmount);
        System.out.println("With DrawSuccessful!");
        CSVReaderWriter.logTransaction(currentCustomer, currentCustomer ,withdrawAccount, withdrawAccount,"withdrawal", withdrawAmount,currentCustomer.checkBalance(withdrawAccount),CSVReaderWriter.log,CSVReaderWriter.transactions);
        }else{
         System.out.println("xxxx----Invalid account type entered! Please try again.----xxxx");
        }
    }

    public static void handleTransfer(Customer currentCustomer, Scanner x) {
        System.out.println("From which account would you like to transfer?\n1.Savings to Checkings \n2.Checkings to Savings");
        System.out.print(">"); 
        String transferAccount = x.nextLine().trim();
        System.out.println("Enter the amount you would like to transfer between your accounts:");
        System.out.print(">");
        String transferInput = x.nextLine();
        double transferAmount;
        if (interfaceClass.isNumeric(transferInput)) {
            transferAmount = Double.parseDouble(transferInput);
        }else {
            System.out.println("xxxx----Invalid input. Please enter a valid number.----xxxx");
            return; //exit the program
        }
        x.nextLine(); // This line is added to consume the newline character
        if (transferAccount.equalsIgnoreCase("1") || transferAccount.equalsIgnoreCase("2")){
            switch (transferAccount.toLowerCase()) {
            case "1"://savings
                currentCustomer.transferMoneyToSaving(transferAmount);
                CSVReaderWriter.logTransaction(currentCustomer,currentCustomer, "Checkings", "Savings","transfer" ,transferAmount,(Double)(currentCustomer.checkBalance(transferAccount)) , CSVReaderWriter.log,CSVReaderWriter.transactions);
                break;
            case "2":
                currentCustomer.transferMoneyToChecking(transferAmount);
                CSVReaderWriter.logTransaction(currentCustomer,currentCustomer, "Savings", "Checkings","Transfer" ,transferAmount,currentCustomer.checkBalance(transferAccount), CSVReaderWriter.log,CSVReaderWriter.transactions);
                break;
            default:
                System.out.println("xxxx----Invalid account type entered! Please try again.----xxxx");
                break;
             }
        } else {
            System.out.println("xxxx----Invalid account type entered! Please try again.----xxxx");
        }
    }

    public static void handleThirdPartyPayment(Customer currentCustomer, List<Customer> customers, Scanner x) {
        System.out.println("From which account would you like to pay/deposit thirdParty?\n1.Savings\n2.Checkings");
        System.out.print(">"); 
        String payAccount = x.nextLine().trim();
        System.out.println("What is the first name of the recipient");
        System.out.print(">"); 
        String name = x.nextLine().trim();
        System.out.println("What is the last name of the recipient");
        System.out.print(">"); 
        String lastName = x.nextLine().trim();

        //account info
        System.out.println("What is the recipient account number");
        System.out.print(">"); 
        String reciepientAccountNumber = x.nextLine().trim();
        System.out.println("Enter the amount you would like to pay to thirdParty:");
        System.out.print(">");
        String payInput = x.nextLine();
        double payAmount;
        if (interfaceClass.isNumeric(payInput)){
            payAmount = Double.parseDouble(payInput);
        } else {
            System.out.println("xxxx----Invalid input. Please enter a valid number.----xxxx");
            return; //exit the program
        }
        x.nextLine(); // This line is added to consume the newline character
        if (payAccount.equalsIgnoreCase("1") || payAccount.equalsIgnoreCase("2")){
            Customer reciever = BankManager.findCustomerByName(x, customers);
            switch (payAccount.toLowerCase()) {
                case "1"://savings
                    ((Savings)currentCustomer.getAccounts().get(1)).payToThirdParty(customers,name,lastName,reciepientAccountNumber,payAmount); //index 1 is savings
                    CSVReaderWriter.logTransaction(currentCustomer,reciever, "Savings","NEEDS TO ADD TYPE OF ACCOUNT", ("transfer to " + name + " " + lastName  + " "), payAmount,currentCustomer.checkBalance("1"), CSVReaderWriter.log,CSVReaderWriter.transactions);
                    break;
                case "2"://checking
                    ((Checking)currentCustomer.getAccounts().get(0)).payToThirdParty(customers,name,lastName,reciepientAccountNumber,payAmount); //index 1 is savings
                    CSVReaderWriter.logTransaction(currentCustomer,reciever, "Checking", "NEEDS TO ADD TYPE OF ACCOUNT", ("transfer to " + name + " " + lastName  + " "), payAmount, currentCustomer.checkBalance("2") ,CSVReaderWriter.log,CSVReaderWriter.transactions);
                    break;
                default:
                    System.out.println("xxxx----Invalid account type entered! Please try again.----xxxx");
                    break;
            }
        } else {
            System.out.println("xxxx----Invalid account type entered! Please try again.----xxxx");
        }
    }


    

//inmutable classes 
public static class TransactionTypes {
    public static final String INQUIRE = "inquire";
    public static final String DEPOSIT = "deposit";
    public static final String WITHDRAW = "withdraw";
    public static final String TRANSFER = "transfer";
    // Add more transaction types as needed
}

public static final class AccountTypes {
    public static final String CHECKING = "Checking";
    public static final String SAVINGS = "Savings";
    public static final String CREDIT = "Credit";
    // Add more account types as needed
}
}//class ends
