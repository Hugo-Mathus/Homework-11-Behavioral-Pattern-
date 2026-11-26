package VendingMachine;

public class DoritosHandler extends SnackDispenseHandler {
    public DoritosHandler(Snack snack, SnackDispenseHandler next) {
        super(snack, next);
    }

    @Override
    public boolean dispenseSnack(String snackName, double money) {
        if (snackName.equals("Doritos")) {
            return processDispense(money);
        }
        return super.dispenseSnack(snackName, money);
    }
}
