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
        if (creditScore < 0) {
            throw new IllegalArgumentException("Credit score cannot be negative.");
        }

        if (creditScore <= 580) {
            return Math.random() * (699 - 100) + 100;
        } else if (creditScore <= 669) {
            return Math.random() * (4999 - 700) + 700;
        } else if (creditScore <= 739) {
            return Math.random() * (7499 - 5000) + 5000;
        } else if (creditScore <= 799) {
            return Math.random() * (15999 - 7500) + 7500;
        } else {
            return Math.random() * (25000 - 16000) + 16000;
        }
    }
}
