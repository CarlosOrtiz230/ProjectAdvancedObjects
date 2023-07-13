import java.util.List;

public class Checking extends Account {

    public Checking(String accountNumber) {
        super(accountNumber);
    }

    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    @Override
    public void deposit(double amount){
        if(amount >= 0){
            balance += amount;
        } else {
            System.out.println("Error with deposit.");
        }
    }

    public void transferMoneyToSaving(Savings savingAccount, double amount) {
        if (this.balance >= amount) {
            savingAccount.deposit(amount);
            this.balance -= amount;
            System.out.println("Transfer successful!");
        } else {
            System.out.println("Insufficient funds in checking account.");
        }
    }

    @Override
    public void payToThirdParty(List<Customer> dataBase ,String name, String lastname, String accountNumber,double amount) {
        String receiverFullName  = name + " " + lastname;
        Customer receiver = null;

        for (Customer customer : dataBase){
            if(customer.getName().equals(receiverFullName)){
                receiver = customer;
                break;
            }
        }

        if(receiver == null){
            System.out.println("Customer was not found");
            return;
        }

        List<Account> receiverAccounts = receiver.getAccounts();

        boolean accountFound = false;
        for (Account currentAccount: receiverAccounts) {
            if (currentAccount.getAccountNumber().equals(accountNumber)) {
                currentAccount.deposit(amount);
                accountFound = true;
                this.balance = this.balance - amount;
                break;
            }
        }

        if (!accountFound) {
            System.out.println("The receiver's account number is incorrect.");
            return;
        }

        System.out.println("Your Deposit to " + receiverFullName + " was successful");
    }
}
