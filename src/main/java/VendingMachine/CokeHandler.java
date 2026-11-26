package VendingMachine;

public class CokeHandler extends SnackDispenseHandler {
    public CokeHandler(Snack snack, SnackDispenseHandler next) {
       super(snack,next);
    }

    @Override
    public boolean dispenseSnack(String snackName, double money) {
        if (snackName.equals("Coke")) {
            if(money >= snack.getPrice()) {
                snack.decreaseQuantity();
                return true;
            }
            return false;
        }
        return super.dispenseSnack(snackName, money);
    }


}
