package VendingMachine;

public class PepsiHandler extends SnackDispenseHandler {
    public PepsiHandler(Snack snack, SnackDispenseHandler next) {
        super(snack,next);
    }

    @Override
    public boolean dispenseSnack(String snackName, double money) {
        if (snackName.equals("Pepsi")) {
            return processDispense(money);
        }
        return super.dispenseSnack(snackName, money);
    }
}
