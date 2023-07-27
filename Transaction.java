import java.util.List;
//This is the one used to complete actions of csv file
public class Transaction {
    private String fromFirstName;
    private String fromLastName;
    private String fromWhere;
    private String action;
    private String toFirstName;
    private String toLastName;
    private String toWhere;
    private double amount;

    public Transaction(String fromFirstName, String fromLastName, String fromWhere, String action,
                       String toFirstName, String toLastName, String toWhere, double amount) {
        this.fromFirstName = fromFirstName;
        this.fromLastName = fromLastName;
        this.fromWhere = fromWhere;
        this.action = action;
        this.toFirstName = toFirstName;
        this.toLastName = toLastName;
        this.toWhere = toWhere;
        this.amount = amount;
    }

    // getters

    public String getFromFirstName() {
        return fromFirstName;
    }

    public String getFromLastName() {
        return fromLastName;
    }

    public String getFromWhere() {
        return fromWhere;
    }

    public String getAction() {
        return action;
    }

    public String getToFirstName() {
        return toFirstName;
    }

    public String getToLastName() {
        return toLastName;
    }

    public String getToWhere() {
        return toWhere;
    }

    public double getAmount() {
        return amount;
    }

    // Method to handle transactions
    public void handleTransactions(List<Customer> customers) {
        for (Transaction transaction : transactions) {
            String fromFirstName = transaction.getFromFirstName();
            String fromLastName = transaction.getFromLastName();
            String fromWhere = transaction.getFromWhere();
            String action = transaction.getAction();
            String toFirstName = transaction.getToFirstName();
            String toLastName = transaction.getToLastName();
            String toWhere = transaction.getToWhere();
            double amount = transaction.getAmount();

            // Find the customer who initiated the transaction (from customer)
            Customer fromCustomer = findCustomerByName(fromFirstName, fromLastName, customers);

            // Find the customer who received the transaction (to customer)
            Customer toCustomer = findCustomerByName(toFirstName, toLastName, customers);

            if (fromCustomer == null || toCustomer == null) {
                System.out.println("Invalid data format in CSV file: " + transaction.toString());
            } else {
                // Process the transaction based on the action
                switch (action.toUpperCase()) {
                    case "TRANSFERS":
                        fromCustomer.withdraw(fromWhere, amount);
                        toCustomer.deposit(toWhere, amount);
                        break;
                    case "WITHDRAWS":
                        fromCustomer.withdraw(fromWhere, amount);
                        break;
                    case "DEPOSITS":
                        toCustomer.deposit(toWhere, amount);
                        break;
                    case "PAYS":
                        fromCustomer.withdraw(fromWhere, amount);
                        break;
                    case "INQUIRES":
                        // Do nothing for inquires
                        break;
                    default:
                        System.out.println("Invalid action in CSV file: " + action);
                }
            }
        }
    }

    // Method to find a customer by name
    private Customer findCustomerByName(String firstName, String lastName, List<Customer> customers) {
        for (Customer customer : customers) {
            if (customer.getFirstName().equalsIgnoreCase(firstName) && customer.getLastName().equalsIgnoreCase(lastName)) {
                return customer;
            }
        }
        return null;
    }
}
