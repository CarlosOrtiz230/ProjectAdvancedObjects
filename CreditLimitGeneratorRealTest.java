import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CreditLimitGeneratorRealTest {

    CreditLimitGeneratorReal creditLimitGenerator = new CreditLimitGeneratorReal();

    @Test
    void testNegativeCreditScore() {
        assertThrows(IllegalArgumentException.class, () -> {
            creditLimitGenerator.generateCreditLimit(-1);
        });
    }

    @Test
    void testCreditScoreLessThanOrEqualTo580() {
        double limit = creditLimitGenerator.generateCreditLimit(580);
        assertTrue(limit >= 100 && limit <= 699);
    }

    @Test
    void testCreditScoreBetween581And669() {
        double limit = creditLimitGenerator.generateCreditLimit(669);
        assertTrue(limit >= 700 && limit <= 4999);
    }

    @Test
    void testCreditScoreBetween670And739() {
        double limit = creditLimitGenerator.generateCreditLimit(739);
        assertTrue(limit >= 5000 && limit <= 7499);
    }

    @Test
    void testCreditScoreBetween740And799() {
        double limit = creditLimitGenerator.generateCreditLimit(799);
        assertTrue(limit >= 7500 && limit <= 15999);
    }

    @Test
    void testCreditScoreGreaterThan800() {
        double limit = creditLimitGenerator.generateCreditLimit(801);
        assertTrue(limit >= 16000 && limit <= 25000);
    }
}