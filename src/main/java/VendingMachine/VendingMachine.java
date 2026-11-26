package VendingMachine;

public class VendingMachine {

    private StateOfVendingMachine state;

    public VendingMachine() {
        this.state = new IdleState();
    }

    public StateOfVendingMachine getState() {
        return state;
    }
}
