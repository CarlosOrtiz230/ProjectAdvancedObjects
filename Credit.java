public class Credit extends Account {
    private double creditLimit;

    public Credit(String accountNumber, double creditLimit) {
        super(accountNumber);
        this.creditLimit = creditLimit;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (balance + creditLimit >= amount) {
            balance -= amount;
        } else {
            System.out.println("Withdrawal amount exceeds credit limit.");
        }
    }
}
