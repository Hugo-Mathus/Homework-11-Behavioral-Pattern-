package VendingMachine;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {

    private StateOfVendingMachine state;
    private Map<String, Snack> snacks;
    private Snack selectedSnack;
    public VendingMachine() {
        this.state = new IdleState();
        this.snacks = new HashMap<>();
    }

    public StateOfVendingMachine getState() {
        return state;
    }

    public void addSnack(Snack snack) {
        snacks.put(snack.getName(), snack);
    }

    public Snack getSnack(String snackName) {
        return snacks.get(snackName);
    }

    public Snack getSelectedSnack() {
        return selectedSnack;
    }

    public void setSelectedSnack(Snack snack) {
        this.selectedSnack = snack;
    }

    public void setState(StateOfVendingMachine state) {
        this.state = state;
    }

    public void selectSnack(String snackName) {
        getState().selectSnack(this, snackName);
    }


}
