package VendingMachine;

public class SnickersHandler extends SnackDispenseHandler {
    public SnickersHandler(Snack snack, SnackDispenseHandler next) {
        super(snack, next);
    }

    @Override
    public boolean dispenseSnack(String snackName, double money) {
        if (snackName.equals("Snickers")) {
            return processDispense(money);
        }
        return super.dispenseSnack(snackName, money);
    }
}
