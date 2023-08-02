/**
 * This class generates a credit limit based on a credit score.
 */
class CreditLimitGeneratorReal implements CreditLimitGenerator {

    /**
     * Generates a credit limit based on the given credit score.
     *
     * @param creditScore The credit score.
     * @return The generated credit limit.
     * @throws IllegalArgumentException if the credit score is negative.
     */
    @Override
    public double generateCreditLimit(int creditScore) throws IllegalArgumentException {
        if (creditScore < 0) {// credit score must be greater than 0
            throw new IllegalArgumentException("Credit score cannot be negative.");
        }
    
        double limit;
        if (creditScore <= 580) {
            limit = Math.random() * (699 - 100) + 100;
        } else if (creditScore <= 669) {
            limit = Math.random() * (4999 - 700) + 700;
        } else if (creditScore <= 739) {
            limit = Math.random() * (7499 - 5000) + 5000;
        } else if (creditScore <= 799) {
            limit = Math.random() * (15999 - 7500) + 7500;
        } else {
            limit = Math.random() * (25000 - 16000) + 16000;
        }
    
        // return the value rounded to 2 decimal places
        return Math.round(limit * 100.0) / 100.0;
    }
}
