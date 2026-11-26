package VendingMachine;

public class KitKatHandler extends SnackDispenseHandler {
    public KitKatHandler(Snack snack, SnackDispenseHandler next) {
        super(snack, next);
    }
}