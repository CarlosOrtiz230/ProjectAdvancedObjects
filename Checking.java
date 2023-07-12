import java.util.List;

/**
 * This class represents a Checking account, which is a type of Account.
 */
public class Checking extends Account {

    /**
     * Constructs a new Checking account with the given account number.
     *
     * @param accountNumber The unique identifier for this account.
     */
    public Checking(String accountNumber) {
        super(accountNumber);
    }

    /**
     * Withdraws the specified amount from the account if the balance is sufficient.
     *
     * @param amount The amount to be withdrawn.
     */
    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    /**
     * Deposits the given amount to the account if the amount is positive.
     *
     * @param amount The amount to be deposited.
     */
    @Override
    public  void deposit(double amount){
        if(amount >= 0){
            balance += amount;
        }else{
            System.out.println("Error with deposit.");
        }
    }//desposit endsd


        /**
     * Transfers the specified amount of money from the checking account to the savings account.
     *
     * @param savingAccount The savings account to which the money will be transferred.
     * @param amount The amount of money to transfer.
     */

    public void transferMoneyToSaving(Savings savingAccount, double amount) {
        if  (this.balance >= amount) {
            savingAccount.deposit(amount);
            System.out.println("Transfer successful!");
        } else {
            System.out.println("Insufficient funds in checking account.");
        }
    }// transfer Money ends

    @Override
    public void payToThirdParty(List<Customer> dataBase ,String name, String lastname, String accountNumber,double amount) {
        String recieverFullName  = name + " " + lastname;
        Customer reciever = null;

        //find thirdParty
        for (Customer customer : dataBase){
            if(customer.getName().equals(recieverFullName)){
                reciever = customer;
                break;
            }//if
        }//for
        
        //customer not found

        if(reciever == null){System.out.println("customer was not found"); return;}

        List<Account> recieverAccounts = reciever.getAccounts();
        
        boolean accountFound = false;
        for ( Account currentAccount: recieverAccounts) { //iterate over the customer account to find the correct one
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
        System.out.println("Your Deposit to " + recieverFullName + " was successfull");
    }//payToThirdParty ends



}// class ends
