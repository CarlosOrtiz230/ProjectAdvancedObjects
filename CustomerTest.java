import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer("John Doe", "1980-01-01", "123 Main St", "123456", "555-555-5555");
    }

    @Test
    void testAddAccount() {
        Account account = new Checking("123");
        customer.addAccount(account);
        assertEquals(1, customer.getAccounts().size());
    }

    @Test
    void testCheckBalance() {
        Account account = new Checking("123");
        account.deposit(100.0);
        customer.addAccount(account);
        assertEquals(100.0, customer.checkBalance("checking"), 0.01);
    }

    @Test
    void testDeposit() {
        Account account = new Checking("123");
        customer.addAccount(account);
        customer.deposit("checking", 100.0);
        assertEquals(100.0, customer.checkBalance("checking"), 0.01);
    }

    @Test
    void testWithdraw() {
        Account account = new Checking("123");
        account.deposit(200.0);
        customer.addAccount(account);
        customer.withdraw("checking", 100.0);
        assertEquals(100.0, customer.checkBalance("checking"), 0.01);
    }

    @Test
    void testPrintCheckingInfo() {
        Account account = new Checking("123");
        account.deposit(200.0);
        customer.addAccount(account);
        assertDoesNotThrow(() -> customer.printCheckingInfo());
    }

    @Test
    void testPrintSavingsInfo() {
        Account account = new Savings("123");
        account.deposit(200.0);
        customer.addAccount(account);
        assertDoesNotThrow(() -> customer.printSavingsInfo());
    }

    @Test
    void testPrintCreditInfo() {
        Account account = new Credit("123", 5000.0);
        account.deposit(200.0);
        customer.addAccount(account);
        assertDoesNotThrow(() -> customer.printCreditInfo());
    }
}