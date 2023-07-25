import java.util.*;

public class BankManager {

    public static Customer findCustomerByName(Scanner userInput, List<Customer> customers){
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


    public static Customer findCustomerByAccount(Scanner userInput, List<Customer> customers){
        try {
            System.out.print("Enter your account number: ");
            System.out.println(">"); 
            String accountNumber = userInput.nextLine();
            
            for (Customer customer : customers) {
                for(Account currentAccount : customer.getAccounts()){
                    if(currentAccount.accountNumber.equals(accountNumber)) {
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
