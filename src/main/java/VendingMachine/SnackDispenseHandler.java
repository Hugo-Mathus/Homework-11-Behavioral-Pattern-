package VendingMachine;

public abstract class SnackDispenseHandler {

    protected Snack snack;
    protected SnackDispenseHandler nextHandler;

    public SnackDispenseHandler(Snack snack, SnackDispenseHandler next) {
        this.snack = snack;
        this.nextHandler = next;
    }

    public SnackDispenseHandler getNextHandler() {
        return nextHandler;
    }

    public boolean dispenseSnack(String snackName, double money) {
        if(nextHandler != null) {
            return nextHandler.dispenseSnack(snackName, money);
        }
        return false;
    }

    protected boolean processDispense(double money) {
        if (money >= snack.getPrice() && snack.isAvailable()) {
            snack.decreaseQuantity();
            return true;
        }
        return false;
    }
}