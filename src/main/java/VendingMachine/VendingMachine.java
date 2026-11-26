package VendingMachine;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {

    private StateOfVendingMachine state;
    private Map<String, Snack> snacks;
    private Snack selectedSnack;
    private double insertedMoney;
    private SnackDispenseHandler snackDispenser;
    private double returnedMoney;

    public VendingMachine() {
        this.state = new IdleState();
        this.snacks = new HashMap<>();
        this.insertedMoney = 0.0;
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

    public double getInsertedMoney() {
        return insertedMoney;
    }

    public void addMoney(double amount) {
        this.insertedMoney += amount;
    }

    public void insertMoney(double amount) {
        getState().insertMoney(this, amount);
    }

    public void setSnackDispenser(SnackDispenseHandler handler) {
        this.snackDispenser = handler;
    }

    public SnackDispenseHandler getSnackDispenser() {
        return snackDispenser;
    }

    public void dispenseSnack() {
        getState().dispenseSnack(this);
    }

    public double getReturnedMoney() {
        return returnedMoney;
    }

    public void returnMoney() {
        returnedMoney = insertedMoney;
        insertedMoney = 0.0;
        System.out.println("Money returned: $"+ returnedMoney);
    }

    public void resetTransaction() {
        selectedSnack = null;
        insertedMoney = 0.0;
        setState(new IdleState());
    }

    public void returnChange() {
        returnedMoney = insertedMoney - selectedSnack.getPrice();
        if (returnedMoney > 0) {System.out.println("Change returned: $"+ returnedMoney);
        }
    }
}
