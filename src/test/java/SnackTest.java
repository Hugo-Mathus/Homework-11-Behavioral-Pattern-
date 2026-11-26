import VendingMachine.Snack;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SnackTest {
    @Test
    public void testSnackCreation() {
        Snack snack = new Snack("Coke",2.00,5);

        assertEquals("Coke",snack.getName());
        assertEquals(2.00,snack.getPrice(),0.001);
        assertEquals(5,snack.getQuantity());
    }
}
