import java.util.List;

/**
 * This class represents a Credit account.
 */
public class Credit extends Account {
    // attributes

    private double creditLimit;

    //constructors

    /**
     * Constructs a new Credit account with the given account number and credit limit.
     *
     * @param accountNumber The account number.
     * @param creditLimit The credit limit.
     */
    public Credit(String accountNumber, double creditLimit) {
        super(accountNumber);
        this.creditLimit = creditLimit;
    }


    //setters and getters

    /**
     * Returns the credit limit.
     * @return The credit limit.
     */
    public double getCreditLimit() {
        return creditLimit;
    }


    @Override
    public String getAccountNumber() {
        return this.accountNumber;
    }

    //methods 
    
    @Override
    public  void deposit(double amount){
        balance += amount;// adds amount to current balance
    }//deposit ends

    /**
     * Withdraws the amount from the account
     * @param amount The amount to be withdrawn.
     */
    @Override
    public void withdraw(double amount) {
        if (balance + creditLimit >= amount) {
            balance -= amount;
        } else {
            System.out.println("Withdrawal amount exceeds credit limit.");
        }
    }//withdraw

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

}//class ends
