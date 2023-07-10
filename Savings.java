public class Savings extends Account {
    public Savings(String accountNumber) {
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
}
