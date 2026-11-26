package VendingMachine;

public class VendingMachine {

    private StateOfVendingMachine state;

    public VendingMachine() {
        this.state = new IdleState();
    }

    public StateOfVendingMachine getState() {
        return state;
    }

    public void addSnack(Snack snack) {
    }

    public Snack getSnack(String snackName) {
        return null;
    }
}
