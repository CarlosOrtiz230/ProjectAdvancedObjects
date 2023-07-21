
    import java.io.*;
    import java.util.*;   
    public class RoleSelection { 
    /**
         * Performs user login by requesting the user's name and phone number, and validating the credentials against
         * the list of customers.
         *
         * @param currentCustomer the current Customer object, which will be updated if the login is successful
         * @param customers       the list of Customer objects to check for user existence and validate credentials
         * @return the updated currentCustomer object if the login is successful, otherwise null
    */
    public static Customer userLogin(List<Customer> customers) throws IOException {
        Scanner x = new Scanner(System.in); //ask the user's name
        System.out.println("Enter your first and last name:");
        System.out.print(">");
        String userName = x.nextLine();
    
        // Find the customer in ArrayList to check existence
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(userName)) {
                return customer; // Return the found customer
            }
        }
        
        System.out.println("xxxx----User not found. Try Again----xxxx");
        return null; // Return null if no customer 
    }//userLogin ends
    

    /**
         * Checks if the user is a bank manager or a bank client based on the user input.
         *
         * @return true if the user is a bank manager, false if the user is a bank client or the program should exit
    */
    public static boolean checkIfManager() {
        Scanner x = new Scanner(System.in);
        System.out.println("are you a Bank Client or Manager?");
        System.out.println("1. Bank Client\n2. Bank Manager\ntype 'exit' to finish the program");
        System.out.print(">");
        String role = x.nextLine();
        if(role.equalsIgnoreCase("exit")){
            System.exit(0);
        }
        if(role.equalsIgnoreCase("1") ||  role.equalsIgnoreCase("1")){ //if the user is a client, return false
            return false;
        }
        else if(role.equalsIgnoreCase("2") || role.equalsIgnoreCase("2")){ //if the user is a manager, return true
            return true;
        }
        else{
            System.out.println("xxxx----this entry is not valid----xxxx");
            return false;
        }
    }
    

}