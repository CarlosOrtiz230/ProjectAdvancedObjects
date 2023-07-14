import java.util.List;

/**
 * A class representing a savings account that extends Account.
 */
public class Savings extends Account {
    
    //constructor

    /**
     * Constructs a new instance of the Savings class with the specified account number.
     * @param accountNumber the account number
     */
    public Savings(String accountNumber) {
        super(accountNumber);
    }

    /**
     * Overrides the withdraw method from the parent class (Account) to allow withdrawals from the savings account
     *
     * @param amount the amount to be withdrawn
     */

    //setters and getters

    @Override
    public String getAccountNumber() {
        return this.accountNumber;
    }


     //methods 
    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient balance.");
        }
    }
    
    /**
     * Overrides the deposit method from the parent class (Account) to allow deposits into the savings account
     * @param amount the amount to be deposite
     */
    @Override
    public void deposit(double amount) {
        if (amount >= 0) {
            balance += amount;
        } else {
            System.out.println("Error with deposit.");
        }
    }//despoist ends

    /**
     * Transfers the specified amount of money from the checking account to the checking account.
     *
     * @param checkingAccount The checkings account to which the money will be transferred.
     * @param amount The amount of money to transfer.
     */

     public void transferMoneyToChecking(Checking checkingAccount, double amount) {
        if  (this.balance >= amount) {
            checkingAccount.deposit(amount);
            System.out.println("Transfer successful!");
        } else {
            System.out.println("Insufficient funds in Saving account.");
        }
    }// transfer Money ends

    //DOES NOT IMPLEMENT PAY TO THIRD PARTY
       @Override
    public void payToThirdParty(List<Customer> dataBase ,String name, String lastname, String accountNumber,double amount) {
        String receiverFullName  = name + " " + lastname;
        Customer receiver = null;

        //find thirdParty
        for (Customer customer : dataBase){
            if(customer.getName().equals(receiverFullName)){
                receiver = customer;// sets the receiver as customer if found
                break;
            }//if
        }//for
        
        //customer not found

        if(receiver == null){System.out.println("customer was not found"); return;}

        List<Account> receiverAccounts = receiver.getAccounts();
        
        boolean accountFound = false;
        for ( Account currentAccount: receiverAccounts) { //iterate over the customer account to find the correct one
            if (currentAccount.getAccountNumber().equals(accountNumber)) {
                currentAccount.deposit(amount); //deposit the corresponding ammount
                accountFound = true;
                this.balance = this.balance - amount;  //substract the ammount from the balance
                break;
            }
        }
    
        if (!accountFound) {
            System.out.println("The receiver's account number is incorrect.");
            return;
        }
        
        //notice success
        System.out.println("Your Deposit to " + receiverFullName + " was successfull");
    }//payToThirdParty ends
}//class ends
