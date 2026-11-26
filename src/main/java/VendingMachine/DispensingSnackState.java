package VendingMachine;

public class DispensingSnackState implements StateOfVendingMachine {

    @Override
    public void selectSnack(VendingMachine machine, String snackName) {
        System.out.println("Please wait. Dispensing snack.");
    }

    @Override
    public void insertMoney(VendingMachine machine, double amount) {
        System.out.println("Enough money has already been inserted.");
    }

    @Override
    public void dispenseSnack(VendingMachine machine) {
        Snack selectedSnack = machine.getSelectedSnack();

        boolean result = machine.getSnackDispenser().dispenseSnack(selectedSnack.getName(), machine.getInsertedMoney());

        if(result) {
            System.out.println(selectedSnack.getName() + " dispensed successfully");
            machine.returnChange();
        }
        else {
            System.out.println("Unable to dispense" + selectedSnack.getName());
            machine.returnMoney();
        }
        machine.resetTransaction();
    }
}
