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
        SnackDispenseHandler cokeHandler = new PepsiHandler(coke,pepsiHandler);

        assertSame(pepsiHandler,cokeHandler.getNextHandler());
    }
}
