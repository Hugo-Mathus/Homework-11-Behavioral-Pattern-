package VendingMachine;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {

    private StateOfVendingMachine state;
    private Map<String, Snack> snacks;
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


}
