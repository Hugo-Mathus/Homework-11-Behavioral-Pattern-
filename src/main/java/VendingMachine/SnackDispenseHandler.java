package VendingMachine;

public abstract class SnackDispenseHandler {

    protected Snack snack;
    protected SnackDispenseHandler nextHandler;

    public SnackDispenseHandler(Snack snack, SnackDispenseHandler next) {
        this.snack = snack;
    }

    public SnackDispenseHandler getNextHandler() {
        return nextHandler;
    }
}