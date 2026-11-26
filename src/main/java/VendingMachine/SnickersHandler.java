package VendingMachine;

public class SnickersHandler extends SnackDispenseHandler {
    public SnickersHandler(Snack snack, SnackDispenseHandler next) {
        super(snack, next);
    }
}
