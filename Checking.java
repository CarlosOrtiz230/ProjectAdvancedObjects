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
    public  void deposit(double amount){
        if(amount >= 0){
            balance += amount;
        }else{
            System.out.println("Error with deposit.");
        }
    }
}
