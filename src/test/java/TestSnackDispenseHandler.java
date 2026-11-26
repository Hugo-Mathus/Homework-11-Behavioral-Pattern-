import VendingMachine.CokeHandler;
import VendingMachine.PepsiHandler;
import VendingMachine.Snack;
import VendingMachine.SnackDispenseHandler;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestSnackDispenseHandler {

    @Test
    void testHandlerConnection(){
        Snack coke = new Snack("Coke",2.00,5);
        Snack pepsi = new Snack("Pepsi",2.00,5);

        SnackDispenseHandler pepsiHandler = new PepsiHandler(pepsi,null);
        SnackDispenseHandler cokeHandler = new CokeHandler(coke,pepsiHandler);

        assertSame(pepsiHandler,cokeHandler.getNextHandler());
    }

    @Test
    void testDispenseCoke() {
        Snack coke = new Snack("Coke", 2.00, 5);
        SnackDispenseHandler handler = new CokeHandler(coke, null);

        boolean result = handler.dispenseSnack("Coke", 2.00);

        assertTrue(result);
        assertEquals(4, coke.getQuantity());
    }
    @Test
    void testInsufficientMoney() {
        Snack coke = new Snack("Coke", 2.00, 5);

        SnackDispenseHandler handler =
                new CokeHandler(coke, null);

        boolean result = handler.dispenseSnack("Coke", 1.00);

        assertFalse(result);
        assertEquals(5, coke.getQuantity());
    }

    @Test
    void testOutOfStockSnack() {
        Snack coke = new Snack("Coke", 2.00, 0);
        SnackDispenseHandler handler = new CokeHandler(coke, null);

        boolean result = handler.dispenseSnack("Coke", 2.00);

        assertFalse(result);
        assertEquals(0, coke.getQuantity());
    }
}
