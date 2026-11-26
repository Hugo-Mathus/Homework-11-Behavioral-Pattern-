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

    @Test
    void testSuccessfulDispense() {
        VendingMachine machine = new VendingMachine();
        Snack coke = new Snack("Coke", 2.00, 5);
        machine.addSnack(coke);
        SnackDispenseHandler handler = new CokeHandler(coke, null);

        machine.setSnackDispenser(handler);

        machine.selectSnack("Coke");
        machine.insertMoney(2.00);
        machine.dispenseSnack();
        assertEquals(4, coke.getQuantity());
    }

    @Test
    void testRefundOutOfStock() {
        VendingMachine machine = new VendingMachine();
        Snack snickers = new Snack("Snickers", 1.75, 0);
        machine.addSnack(snickers);

        SnackDispenseHandler handler = new SnickersHandler(snickers, null);
        machine.setSnackDispenser(handler);

        machine.selectSnack("Snickers");
        machine.insertMoney(1.75);
        machine.dispenseSnack();

        assertEquals(1.75, machine.getReturnedMoney(), 0.001);
        assertEquals(0.0, machine.getInsertedMoney(), 0.001);
        assertEquals(0, snickers.getQuantity());
    }

    @Test
    void testResetAfterPurchase() {
        VendingMachine machine = new VendingMachine();
        Snack coke = new Snack("Coke", 2.00, 5);

        machine.addSnack(coke);
        machine.setSnackDispenser(new CokeHandler(coke, null));

        machine.selectSnack("Coke");
        machine.insertMoney(2.00);
        machine.dispenseSnack();

        assertInstanceOf(IdleState.class, machine.getState());
        assertNull(machine.getSelectedSnack());
        assertEquals(0.0, machine.getInsertedMoney(), 0.001);
        assertEquals(4, coke.getQuantity());
    }

    @Test
    void testResetAfterRefund() {
        VendingMachine machine = new VendingMachine();
        Snack snickers = new Snack("Snickers", 1.75, 0);

        machine.addSnack(snickers);
        machine.setSnackDispenser(new SnickersHandler(snickers, null));

        machine.selectSnack("Snickers");
        machine.insertMoney(1.75);
        machine.dispenseSnack();

        assertInstanceOf(IdleState.class, machine.getState());
        assertNull(machine.getSelectedSnack());
        assertEquals(0.0, machine.getInsertedMoney(), 0.001);
        assertEquals(1.75, machine.getReturnedMoney(), 0.001);
    }

    @Test
    void testReturnChange() {
        VendingMachine machine = new VendingMachine();
        Snack coke = new Snack("Coke", 2.00, 5);

        machine.addSnack(coke);
        machine.setSnackDispenser(new CokeHandler(coke, null));

        machine.selectSnack("Coke");
        machine.insertMoney(5.00);
        machine.dispenseSnack();

        assertEquals(3.00, machine.getReturnedMoney(), 0.001);
        assertEquals(0.00, machine.getInsertedMoney(), 0.001);
        assertEquals(4, coke.getQuantity());
        assertInstanceOf(IdleState.class, machine.getState());
    }
}
