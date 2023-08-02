import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * The userFunctions class provides methods to handle various banking operations for a bank customer.
 */
public class userFunctions{
 
    /**
     * Handles the inquiry transaction by asking the user to select an account type and displaying the account's current balance.
     *
     * @param currentCustomer The Customer object representing the currently logged-in customer.
     * @param x Scanner object used to read user input.
     */
    public static void handleInquiry(Customer currentCustomer, Scanner x) {
        PrintMenu.whichAccountInquire(); 
        String accountType = x.nextLine();
        //check if valid accout type
        if (!(AccountTypes.isValidAccountType(accountType))){System.out.println("Not a valid account Type"); return;}
        //if it is a valid account type, it is save it in a variable
        accountType = AccountTypes.getAccountType(accountType);
        //check for correctness
        double currentBalance = currentCustomer.checkBalance(accountType);
        System.out.println("Your current " + accountType + " balance is: " + currentCustomer.checkBalance(accountType ));
        CSVReaderWriter.logTransaction(currentCustomer, currentCustomer, accountType, accountType, TransactionTypes.INQUIRE, 0.0,currentBalance, CSVReaderWriter.log, CSVReaderWriter.transactions);
        System.out.println();
        PrintMenu.success();
    }
    
    public static void handleDeposit(Customer currentCustomer, Scanner x) {
        PrintMenu.whichAccountDeposit();
        String depositAccount = x.nextLine().trim().toUpperCase();
        if (!(AccountTypes.isValidAccountType(depositAccount))){System.out.println("Not a valid account Type"); return;}
        depositAccount = AccountTypes.getAccountType(depositAccount);
        PrintMenu.enterAmmountDeposit();
        String depositInput = x.nextLine(); //to avoid crashing
        double depositAmount;
        if(!(NumericCheck.isNumeric(depositInput))) { return;}//exit method if not numeric
        depositAmount = Double.parseDouble(depositInput);
        if(!(NumericCheck.isPositiveNumber(depositAmount))){System.out.println("No change is done to balance");return;}
        x.nextLine(); // Consume newline left-over
        currentCustomer.deposit(depositAccount, depositAmount);
        double currentBalance = currentCustomer.checkBalance(depositAccount);
        CSVReaderWriter.logTransaction(currentCustomer,currentCustomer,depositAccount,depositAccount,TransactionTypes.DEPOSIT,depositAmount,currentBalance,CSVReaderWriter.log, CSVReaderWriter.transactions);  
        PrintMenu.success();
    }   

    public static void handleWithdrawal(Customer currentCustomer, Scanner x) {
        PrintMenu.WhichAccountWithdraw();
        String accountType = x.nextLine();
        if (!(AccountTypes.isValidAccountType(accountType))){System.out.println("Not a valid account Type"); return;}
        accountType = AccountTypes.getAccountType(accountType);
        PrintMenu.enterAmmounWithdraw();
        String withdrawInput =  x.nextLine();
        double withdrawAmount;
        if (!(NumericCheck.isNumeric(withdrawInput)) ){return;}
        withdrawAmount = Double.parseDouble(withdrawInput);
        if(!(NumericCheck.isPositiveNumber(withdrawAmount))){System.out.println("Withdraw cancelled");return;}
        //type of account 
        double currentBalance  = currentCustomer.checkBalance(accountType);
        if(withdrawAmount>currentBalance){System.out.println("Ammount to withdraw is more than the balance");return;}
        currentCustomer.withdraw(accountType,withdrawAmount);
        currentBalance  = currentCustomer.checkBalance(accountType);
        System.out.println("With DrawSuccessful!");
        CSVReaderWriter.logTransaction(currentCustomer,currentCustomer,accountType,accountType,TransactionTypes.WITHDRAW,withdrawAmount,currentBalance,CSVReaderWriter.log, CSVReaderWriter.transactions);
        PrintMenu.success();
    }

    public static void handleTransfer(Customer currentCustomer, Scanner x) {
        PrintMenu.whichAccountTransfer();
        String transferAccount = x.nextLine().trim();
        if (!(AccountTypes.isValidAccountType(transferAccount))){System.out.println("Not a valid account Type"); return;}
        transferAccount = AccountTypes.getAccountType(transferAccount);
        PrintMenu.enterAmmountTransfer();
        String transferInput = x.nextLine();
        double transferAmount;
        if (!(NumericCheck.isNumeric(transferInput))) {return;}
        transferAmount = Double.parseDouble(transferInput); 
        if(!(NumericCheck.isPositiveNumber(transferAmount))){System.out.println("transfer cancelled");return;} 
        x.nextLine(); // This line is added to consume the newline character
        String recieverAccount;
        if(transferAccount.equals(AccountTypes.CHECKING)){recieverAccount = AccountTypes.SAVINGS;}
        else{recieverAccount = AccountTypes.CHECKING;}
        //to which account to deposit
        currentCustomer.deposit(recieverAccount, transferAmount);
        //from which account take the mone out
        currentCustomer.withdraw(transferAccount, transferAmount);
        double currentBalance = currentCustomer.checkBalance(recieverAccount);
        CSVReaderWriter.logTransaction(currentCustomer,currentCustomer,transferAccount,recieverAccount,TransactionTypes.TRANSFER,transferAmount,currentBalance,CSVReaderWriter.log, CSVReaderWriter.transactions);
        PrintMenu.success();
        return;
        
    }

    public static void handleThirdPartyPayment(Customer currentCustomer, List<Customer> customers, Scanner x) {
        PrintMenu.whichAccountPayThirdPrt();
        String payAccount = x.nextLine().trim();
        if (!(AccountTypes.isValidAccountType(payAccount))){System.out.println("Not a valid account Type"); return;}
        Customer recivier = SearchCustomer.findCustomerByName(x, customers);
        if(recivier == null){return;} //customer not found not found
        //account info
        PrintMenu.whatRecipientAccountNumber();
        String reciepientAccountNumber = x.nextLine().trim();
        String recipientAccountType = AccountTypes.instanceOfAccountByNumber(reciepientAccountNumber,customers);
        if(recipientAccountType == null){return;} //account number not found
        //entering ammount
        PrintMenu.enterAmmountPay3rdPrty();
        String payInput = x.nextLine();
        if (!(NumericCheck.isNumeric(payInput))){ System.out.println("Payment cancelled");return;}
        double payAmount = Double.parseDouble(payInput);
        if(!(NumericCheck.isPositiveNumber(payAmount))){System.out.println("payment cancelled");return;} 
        x.nextLine(); // This line is added to consume the newline character
        //asks for account number
        double currentBalance = currentCustomer.checkBalance(payAccount);
        //paymant is performed
        currentCustomer.withdraw(payAccount, payAmount);
        recivier.deposit(recipientAccountType, payAmount);
        //log is registered
        CSVReaderWriter.logTransaction(currentCustomer, recivier, payAccount, recipientAccountType,TransactionTypes.PAY , payAmount, currentBalance ,CSVReaderWriter.log,CSVReaderWriter.transactions);
        PrintMenu.success();
    }

    public static void payCreditCard(Customer currentCustomer,Scanner x){
        double payAmmount;
        //hola
        PrintMenu.enterAmmountPayCredit();
        String ammount = x.nextLine();
        if (NumericCheck.isNumeric(ammount)){
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
