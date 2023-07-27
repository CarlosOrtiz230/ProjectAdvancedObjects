import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class userFunctions{
 
    public static void handleInquiry(Customer currentCustomer, Scanner x) {
        PrintMenu.whichAccountInquire(); 
        String accountType = x.nextLine();
        //check if valid accout type
        if (!(AccountTypes.isValidAccountType(accountType))){System.out.println("Not a valid account Type"); return;}
        accountType = AccountTypes.getAccountType(accountType);
        //check for correctness
        double currentBalance = currentCustomer.checkBalance(accountType);
        System.out.println("Your current " + accountType + " balance is: " + currentCustomer.checkBalance(accountType));
        CSVReaderWriter.logTransaction(currentCustomer, currentCustomer, accountType, accountType, TransactionTypes.INQUIRE, 0.0,currentBalance, CSVReaderWriter.log, CSVReaderWriter.transactions);
        PrintMenu.success(); //inform sucess 
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
            double currentBalance = currentCustomer.checkBalance(depositAccount);
            CSVReaderWriter.logTransaction(currentCustomer,currentCustomer,depositAccount,depositAccount,TransactionTypes.DEPOSIT,depositAmount,currentBalance,CSVReaderWriter.log, CSVReaderWriter.transactions);
        }else {
            System.out.println("\nxxxx----Invalid account type entered! Please try again.----xxxx");
        }
    }   

    public static void handleWithdrawal(Customer currentCustomer, Scanner x) {
        PrintMenu.WhichAccountWithdraw();
        String accountType;
        String withdrawAccount = x.nextLine();
        if (withdrawAccount.equalsIgnoreCase("1") || withdrawAccount.equalsIgnoreCase("2")){
            PrintMenu.enterAmmounWithdraw();
            String withdrawInput =  x.nextLine();
            double withdrawAmount;
            
            if (interfaceClass.isNumeric(withdrawInput)) {
                withdrawAmount = Double.parseDouble(withdrawInput);
            }else {
                System.out.println("xxxx----Invalid input. Please enter a valid number.----xxxx");
                return; //exit the program
            }
            //type of account
            if(withdrawAccount.equals("1")){accountType = AccountTypes.SAVINGS;}else{accountType = AccountTypes.CHECKING;}
            
            currentCustomer.withdraw(withdrawAccount, withdrawAmount);
            System.out.println("With DrawSuccessful!");
            double currentBalance  = currentCustomer.checkBalance(accountType);
            CSVReaderWriter.logTransaction(currentCustomer,currentCustomer,accountType,accountType,TransactionTypes.WITHDRAW,withdrawAmount,currentBalance,CSVReaderWriter.log, CSVReaderWriter.transactions);
        }else{
         System.out.println("xxxx----Invalid account type entered! Please try again.----xxxx");
        }
    }

    public static void handleTransfer(Customer currentCustomer, Scanner x) {
        PrintMenu.whichAccountTransfer();
        String transferAccount = x.nextLine().trim();
        PrintMenu.enterAmmountTransfer();
        String transferInput = x.nextLine();
        double transferAmount;
        String accountType;
        String senderAccount;
        if (interfaceClass.isNumeric(transferInput)) {
            transferAmount = Double.parseDouble(transferInput);
        }else {
            System.out.println("xxxx----Invalid input. Please enter a valid number.----xxxx");
            return; //exit the program
        }
        x.nextLine(); // This line is added to consume the newline character
        if (transferAccount.equalsIgnoreCase("1") || transferAccount.equalsIgnoreCase("2")){
            if(transferAccount.equals("1")){accountType = AccountTypes.SAVINGS; senderAccount = AccountTypes.CHECKING;}
            else{accountType = AccountTypes.CHECKING; senderAccount = AccountTypes.SAVINGS;}

            currentCustomer.deposit(accountType, transferAmount);
            currentCustomer.withdraw(senderAccount, transferAmount);
            double currentBalance = currentCustomer.checkBalance(accountType);
            CSVReaderWriter.logTransaction(currentCustomer,currentCustomer,senderAccount,accountType,TransactionTypes.TRANSFER,transferAmount,currentBalance,CSVReaderWriter.log, CSVReaderWriter.transactions);
            PrintMenu.success();
            return;
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

    public static void payCreditCard(Customer currentCustomer,Scanner x){
        double payAmmount;
        //hola
        PrintMenu.enterAmmountPayCredit();
        String ammount = x.nextLine();
        if (interfaceClass.isNumeric(ammount)){
            payAmmount = Double.parseDouble(ammount);
        } else {
            System.out.println("xxxx----Invalid input. Please enter a valid number.----xxxx");
            return; //exit function
        }
        Credit creditUserAccount = (Credit) currentCustomer.getAccounts().get(2);
        //pay
        creditUserAccount.deposit(payAmmount);
        //register
        double userCurrentBalance = creditUserAccount.getBalance();
        CSVReaderWriter.logTransaction(currentCustomer,currentCustomer,AccountTypes.CREDIT,AccountTypes.CREDIT,TransactionTypes.PAY ,payAmmount,userCurrentBalance,CSVReaderWriter.log,CSVReaderWriter.transactions);
    }//pay credit card ends
    

}//class ends
