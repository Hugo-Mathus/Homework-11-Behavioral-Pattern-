package VendingMachine;

public class WaitingForMoneyState implements StateOfVendingMachine {

    @Override
    public void selectSnack(VendingMachine machine, String snackName) {
    }

    @Override
    public void insertMoney(VendingMachine machine, double amount) {
        machine.addMoney(amount);
        if(machine.getInsertedMoney() >= machine.getSelectedSnack().getPrice()) {
            machine.setState(new DispensingSnackState());
        }
    }

    @Override
    public void dispenseSnack(VendingMachine machine) {
    }
}
