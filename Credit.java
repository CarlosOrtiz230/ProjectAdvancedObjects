public class Credit extends Person{
    protected double creditMax;
    protected double availableCredit;

    public Credit(String name, double creditMax){
        super(name);
        this.creditMax = creditMax;
        this.availableCredit = creditMax; //0 debt
    }

    /**
     * Allows banker and user to see what their credit line is
     * @return credit line
     */
    public double getCredit(){
        return creditMax;
    }

    /**
     * this will allow banker to set max credit line
     * @param creditMax maximum credit limit
     */
    public void setCredit(double creditMax){
        this.creditMax = creditMax;
    }

    /**
     * Allows user and banker to get available credit
     */
    public double getAvailableCredit(){
        return availableCredit;
    }
    /**
     * deducts the amount from payment to available credit
     */
    public void creditPayment(double amt){
        if(amt < availableCredit){
            availableCredit = availableCredit-amt;
        }else{
            System.out.println("Payment is greater than available credit");
        }
    }
}