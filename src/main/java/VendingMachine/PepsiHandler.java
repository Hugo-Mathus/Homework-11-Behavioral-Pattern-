package VendingMachine;

public class PepsiHandler extends SnackDispenseHandler {
    public PepsiHandler(Snack snack, SnackDispenseHandler next) {
        super(snack,next);
    }
}
