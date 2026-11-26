package VendingMachine;

public class IdleState implements StateOfVendingMachine {

    @Override
    public void selectSnack(VendingMachine machine, String snackName) {
        Snack snack = machine.getSnack(snackName);

        if (snack != null) {
            machine.setSelectedSnack(snack);
            machine.setState(new WaitingForMoneyState());
        }
        else {
            System.out.println("Invalid Snack Selection");
        }

    }

    @Override
    public void insertMoney(VendingMachine machine, double amount) {
    }

    @Override
    public void dispenseSnack(VendingMachine machine) {
    }
}