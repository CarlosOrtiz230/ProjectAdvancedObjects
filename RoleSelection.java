import java.util.*;   
/**
 * The RoleSelection class provides methods for user login and checking if the user is a bank client or manager.
 */
public class RoleSelection { 

    /**
     * Performs user login by requesting the user's name and phone number, and validating the credentials against
     * the list of customers.
     *
     * @param customers the list of Customer objects to check for user existence and validate credentials
     * @return the updated currentCustomer object if the login is successful, otherwise null
     */
    public static Customer userLogin(List<Customer> customers) {
        try {
            Scanner x = new Scanner(System.in); 
            System.out.println("Enter your first and last name:");
            System.out.print(">");
            String userName = x.nextLine();
        
            for (Customer customer : customers) {
                if (customer.getName().equalsIgnoreCase(userName)) {
                    return customer; 
                }
            }

            System.out.println("xxxx----User not found. Try Again----xxxx");
        } catch (Exception e) {
            System.out.println("An error occurred during user login: " + e.getMessage());
            e.printStackTrace();
        }
        return null; 
    }

    /**
     * Checks if the user is a bank manager or a bank client based on the user input.
     *
     * @return true if the user is a bank manager, false if the user is a bank client or the program should exit
     */
    public static boolean checkIfManager() {
        try {
            Scanner x = new Scanner(System.in);
            System.out.println("Are you a Bank Client or Manager?");
            System.out.println("1. Bank Client\n2. Bank Manager\ntype 'exit' to finish the program");
            System.out.print(">");
            String role = x.nextLine();
            if(role.equalsIgnoreCase("exit")){
                System.exit(0);
            }
            if(role.equalsIgnoreCase("1")){ 
                return false;
            }
            else if(role.equalsIgnoreCase("2")){ 
                return true;
            }
            else{
                System.out.println("xxxx----this entry is not valid----xxxx");
            }
    
        } catch (Exception e) {
            System.out.println("An error occurred while checking if user is a manager: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
}
