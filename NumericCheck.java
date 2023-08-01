public interface NumericCheck{
        /**
         * Checks if a given string can be parsed as a numeric value.
         *
         * @param str the string to check
         * @return true if the string is numeric, false otherwise
     */
    public static boolean isNumeric(String str) { //to check if an string is numeric
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Error:" + e.getMessage());
            return false;
        }
    }//this can be an interface

    public static boolean isPositiveNumber(double number){
        if( 0 >number){
             System.out.println("You can't use negative ammounts");
             return false;
        }
        return true;
       
    }
}