package VendingMachine;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestVendingMachine {

    @Test
    void testInitialState() {
        VendingMachine machine = new VendingMachine();
        assertInstanceOf(IdleState.class, machine.getState());
    }
}
