public class PrintMenu{
    /**
         * Displays the menu options for the bank manager.
    */

    public static void success(){
        System.out.println("This transaction was done successfull");
    }

    public static void displayManagerMenu(){
        System.out.println("----Manager Menu----");
        System.out.println("Would you like to inquire by name or by type/number");
        System.out.println("1. Inquire account by name.\n2. Inquire account by type/number\n3. Add new user\n4. Import data from Transaction.csv");
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
        "6. Pay to someone else" +"\n7. Pay Credit Card"+"\n--Type 'EXIT' to Close--");
        System.out.print(">");
    }

    public static void whichAccountInquire(){
        System.out.println("Which account's balance would you like to check? Type one of the following: \n-Checking\n-Savings\n-Credit");
        System.out.print(">");
    }

    public static void whichAccountDeposit(){
        System.out.println("To which account would you like to deposit money? Type one of the following: \n-Checking\n-Savings");
        System.out.print(">");
    }

    public static void enterAmmountDeposit(){
        System.out.println("Enter the amount you want to deposit:");
        System.out.print(">");
    }

    public static void WhichAccountWithdraw(){
        System.out.println("From which account you would like to withdraw money? Type one of the following: \n-Checking\n-Savings");
        System.out.print(">");
    }

    public static void enterAmmounWithdraw(){
        System.out.println("Enter the amount you want to withdraw:");
        System.out.print(">");
    }

    public static void whichAccountTransfer(){
        System.out.println("From which account would you like to transfer? Type one which account you are transferring FROM\n-From Checking to Savings \n-From Savings to Checkings");
        System.out.print(">"); 
    }
    
    public static void enterAmmountTransfer(){
        System.out.println("Enter the amount you would like to transfer between your accounts:");
        System.out.print(">");
    }

    public static void enterAmmountPayCredit(){
        System.out.println("Enter the amount to pay: ");
        System.out.print(">");
    }

    public static void whichAccountPayThirdPrt(){
        System.out.println("From which account would you like to pay/deposit thirdParty? Type the desired account \n-Checking\n-Savings\n-Credit");
        System.out.print(">"); 
    }

    public static void enterAmmountPay3rdPrty(){
        System.out.println("Enter the amount to pay to the third party: ");
        System.out.print(">");
    }

    public static void enterFirstNameRecipient(){
        System.out.println("What is the first name of the recipient");
        System.out.print(">"); 
    }

    public static void enterLastNameRecipient(){
        System.out.println("What is the last name of the recipient");
        System.out.print(">"); 
    }

    public static void whatRecipientAccountNumber(){
        System.out.println("What is the recipient account number");
        System.out.print(">"); 
    }

    public static void accountNubmerNotfound(){
        System.out.println("Account number not found");
    }
}