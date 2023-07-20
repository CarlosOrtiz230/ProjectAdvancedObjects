public class Menu{
    /**
         * Displays the menu options for the bank manager.
    */
    public static void displayManagerMenu(){
        System.out.println("----Manager Menu----");
        System.out.println("Would you like to inquire by name or by type/number");
        System.out.println("A. Inquire account by name.\nB. Inquire account by type/number");
        System.out.println("Type 'Exit' to leave the program");
        System.out.print(">");
    }//displayManagerMenu ends
    
    /**
        * Displays the menu options for the bank customer.
    */
    public static void displayCustomerMenu(){
        System.out.println("----Customer Menu----");
        System.out.println("What would you like to do today?");
        System.out.println("\n1. Inquiry about a balance \n2. Deposit money to an account");
        System.out.println("3. Withdraw money from an account \n4. Transfer money between accounts \n5. Switch Bank Roles\n" + 
        "6. Pay to someone else" +"\n--Type 'EXIT' to Close--");
    }//displayManagerMenu ends
}