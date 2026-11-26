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

    @Test
    public void testAvailability(){
        Snack snack = new Snack("Coke",2.00,5);
        assertEquals(true,snack.isAvailable());
    }

    @Test
    public void testDecreaseQuantity(){
        Snack snack = new Snack("Coke",2.00,5);
        snack.decreaseQuantity();
        assertEquals(4,snack.getQuantity());
    }

    @Test
    void testOutOfStock() {
        Snack snack = new Snack("Snickers", 1.75, 1);

        assertTrue(snack.isAvailable());

        snack.decreaseQuantity();

        assertEquals(0, snack.getQuantity());
        assertFalse(snack.isAvailable());
    }
}
