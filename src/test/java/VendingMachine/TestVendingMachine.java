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
    @Test
    void testInsertMoney() {
        VendingMachine machine = new VendingMachine();
        Snack coke = new Snack("Coke", 2.00, 5);

        machine.addSnack(coke);
        machine.selectSnack("Coke");

        machine.insertMoney(0.50);
        assertEquals(0.50, machine.getInsertedMoney(), 0.001);

        machine.insertMoney(1.00);
        assertEquals(1.50, machine.getInsertedMoney(), 0.001);
    }
    @Test
    void testInsufficientMoney() {
        VendingMachine machine = new VendingMachine();
        Snack coke = new Snack("Coke", 2.00, 5);

        machine.addSnack(coke);
        machine.selectSnack("Coke");
        machine.insertMoney(1.00);

        assertInstanceOf(WaitingForMoneyState.class, machine.getState());

        assertEquals(1.00, machine.getInsertedMoney(), 0.001);
        assertEquals(5, coke.getQuantity());
    }

    @Test
    void testEnoughMoney() {
        VendingMachine machine = new VendingMachine();
        Snack coke = new Snack("Coke", 2.00, 5);

        machine.addSnack(coke);
        machine.selectSnack("Coke");

        machine.insertMoney(1.00);

        assertInstanceOf(WaitingForMoneyState.class, machine.getState());

        machine.insertMoney(1.00);

        assertInstanceOf(DispensingSnackState.class, machine.getState());

        assertEquals(2.00, machine.getInsertedMoney(), 0.001);
    }
}
