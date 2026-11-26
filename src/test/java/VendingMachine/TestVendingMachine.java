package VendingMachine;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestVendingMachine {

    @Test
    void testInitialState() {
        VendingMachine machine = new VendingMachine();
        assertInstanceOf(IdleState.class, machine.getState());
    }

    @Test
    void testAddAndGetSnack() {
        VendingMachine machine = new VendingMachine();
        Snack coke = new Snack("Coke", 2.00, 5);
        machine.addSnack(coke);
        assertSame(coke, machine.getSnack("Coke"));
    }

    @Test
    void testSelectValidSnack() {
        VendingMachine machine = new VendingMachine();
        Snack coke = new Snack("Coke", 2.00, 5);

        machine.addSnack(coke);
        machine.selectSnack("Coke");

        assertSame(coke, machine.getSelectedSnack());
        assertInstanceOf(WaitingForMoneyState.class, machine.getState());
    }

    @Test
    void testInvalidSnackSelection() {
        VendingMachine machine = new VendingMachine();
        Snack coke = new Snack("Coke", 2.00, 5);

        machine.addSnack(coke);
        machine.selectSnack("Sprite");

        assertNull(machine.getSelectedSnack());
        assertInstanceOf(IdleState.class, machine.getState());
    }
}
