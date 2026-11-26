package VendingMachine;
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

    @Test
    void testForwardRequest() {
        Snack coke = new Snack("Coke", 2.00, 5);
        Snack pepsi = new Snack("Pepsi", 2.00, 5);

        SnackDispenseHandler handler = new CokeHandler(coke,new PepsiHandler(pepsi, null));

        boolean result = handler.dispenseSnack("Pepsi", 2.00);

        assertTrue(result);
        assertEquals(5, coke.getQuantity());
        assertEquals(4, pepsi.getQuantity());
    }

    @Test
    void testNonexistentSnack() {
        Snack coke = new Snack("Coke", 2.00, 5);
        Snack pepsi = new Snack("Pepsi", 2.00, 5);

        SnackDispenseHandler handler = new CokeHandler(coke, new PepsiHandler(pepsi, null));

        boolean result = handler.dispenseSnack("Sprite", 2.00);

        assertFalse(result);
        assertEquals(5, coke.getQuantity());
        assertEquals(5, pepsi.getQuantity());
    }

    @Test
    void testCompleteChain() {
        Snack coke = new Snack("Coke", 2.00, 5);
        Snack pepsi = new Snack("Pepsi", 2.00, 5);
        Snack cheetos = new Snack("Cheetos", 1.50, 3);
        Snack doritos = new Snack("Doritos", 2.00, 3);
        Snack kitkat = new Snack("KitKat", 1.50, 3);
        Snack snickers = new Snack("Snickers", 1.75, 1);

        SnackDispenseHandler handler = new CokeHandler(coke, new PepsiHandler(pepsi, new CheetosHandler(cheetos, new DoritosHandler(doritos, new KitKatHandler(kitkat, new SnickersHandler(snickers, null))))));

        assertTrue(handler.dispenseSnack("Snickers", 1.75));

        assertEquals(5, coke.getQuantity());
        assertEquals(5, pepsi.getQuantity());
        assertEquals(3, cheetos.getQuantity());
        assertEquals(3, doritos.getQuantity());
        assertEquals(3, kitkat.getQuantity());
        assertEquals(0, snickers.getQuantity());
    }

    @Test
    void testDispenseCheetos() {
        Snack cheetos = new Snack("Cheetos", 1.50, 3);
        SnackDispenseHandler handler =
                new CheetosHandler(cheetos, null);

        assertTrue(handler.dispenseSnack("Cheetos", 1.50));
        assertEquals(2, cheetos.getQuantity());
    }

    @Test
    void testDispenseDoritos() {
        Snack doritos = new Snack("Doritos", 2.00, 3);
        SnackDispenseHandler handler =
                new DoritosHandler(doritos, null);

        assertTrue(handler.dispenseSnack("Doritos", 2.00));
        assertEquals(2, doritos.getQuantity());
    }

    @Test
    void testDispenseKitKat() {
        Snack kitkat = new Snack("KitKat", 1.50, 3);
        SnackDispenseHandler handler =
                new KitKatHandler(kitkat, null);

        assertTrue(handler.dispenseSnack("KitKat", 1.50));
        assertEquals(2, kitkat.getQuantity());
    }
}
