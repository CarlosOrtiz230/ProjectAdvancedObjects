import java.util.*;

/**
 * The BankManager class provides utility methods to find customers in a list based on their name or account number.
 */
public class BankManager {

    /**
     * Finds a customer in the provided list based on their name.
     *
     * @param userInput Scanner object to read user input.
     * @param customers The list of customers to search for the name.
     * @return The Customer object if found, or null if not found
     */
    public static Customer findCustomerByName(Scanner userInput, List<Customer> customers) {
        try {
            System.out.println("What is the name of the customer");
            System.out.print(">");
            String name = userInput.nextLine();
            for (Customer customer : customers) {
                if (customer.getName().equalsIgnoreCase(name)) {
                    return customer;
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while finding customer by name: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Finds a customer in the provided list based on their account number.
     *
     * @param userInput Scanner object to read user input.
     * @param customers The list of customers to search for
     * @return The Customer object if found, or null if not found
     */
    public static Customer findCustomerByAccount(Scanner userInput, List<Customer> customers) {
        try {
            System.out.print("Enter your account number: ");
            System.out.println(">");
            String accountNumber = userInput.nextLine();

            for (Customer customer : customers) {
                for (Account currentAccount : customer.getAccounts()) {
                    if (currentAccount.accountNumber.equals(accountNumber)) {
                        return customer;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while finding customer by account: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
