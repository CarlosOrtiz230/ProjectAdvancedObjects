class CreditLimitGeneratorReal implements CreditLimitGenerator {

    @Override
    public double generateCreditLimit(int creditScore) {
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