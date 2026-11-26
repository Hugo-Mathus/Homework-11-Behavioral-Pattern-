package VendingMachine;

public class WaitingForMoneyState implements StateOfVendingMachine {

    @Override
    public void selectSnack(VendingMachine machine, String snackName) {
        System.out.println("A snack has already been selected.");
    }

    @Override
    public void insertMoney(VendingMachine machine, double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        machine.addMoney(amount);
        if (machine.getInsertedMoney() >= machine.getSelectedSnack().getPrice()) {
            machine.setState(new DispensingSnackState());
            machine.dispenseSnack();
        }
        else{
            System.out.printf("Remaining amount: $%.2f%n", machine.getSelectedSnack().getPrice() - machine.getInsertedMoney());
        }
    }

    @Override
    public void dispenseSnack(VendingMachine machine) {
        System.out.println("Insufficient money.");
    }
}
