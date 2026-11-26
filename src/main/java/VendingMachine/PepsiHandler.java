package VendingMachine;

public class PepsiHandler extends SnackDispenseHandler {
    public PepsiHandler(Snack snack, SnackDispenseHandler next) {
        super(snack,next);
    }

    @Override
    public boolean dispenseSnack(String snackName, double money) {
        if (snackName.equals("Pepsi")) {
            if(money >= snack.getPrice() && snack.isAvailable()) {
                snack.decreaseQuantity();
                return true;
            }
            return false;
        }
        return super.dispenseSnack(snackName, money);
    }
}
