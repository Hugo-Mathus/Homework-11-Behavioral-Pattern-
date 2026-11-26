package VendingMachine;

public class CheetosHandler extends SnackDispenseHandler {
    public CheetosHandler(Snack snack, SnackDispenseHandler next) {
        super(snack, next);
    }

    @Override
    public boolean dispenseSnack(String snackName, double money) {
        if (snackName.equals("Cheetos")) {
            return processDispense(money);
        }
        return super.dispenseSnack(snackName, money);
    }
}
