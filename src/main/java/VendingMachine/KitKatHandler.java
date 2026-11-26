package VendingMachine;

public class KitKatHandler extends SnackDispenseHandler {
    public KitKatHandler(Snack snack, SnackDispenseHandler next) {
        super(snack, next);
    }
    @Override
    public boolean dispenseSnack(String snackName, double money) {
        if (snackName.equals("KitKat")) {
            return processDispense(money);
        }
        return super.dispenseSnack(snackName, money);
    }
}