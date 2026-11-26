package VendingMachine;

public class Driver {

    public static void main(String[] args) {

        VendingMachine machine = new VendingMachine();

        // Create snacks
        Snack coke = new Snack("Coke", 2.00, 5);
        Snack pepsi = new Snack("Pepsi", 2.00, 5);
        Snack cheetos = new Snack("Cheetos", 1.50, 3);
        Snack doritos = new Snack("Doritos", 2.00, 3);
        Snack kitkat = new Snack("KitKat", 1.50, 3);
        Snack snickers = new Snack("Snickers", 1.75, 1);

        // Add snacks to the vending machine
        machine.addSnack(coke);
        machine.addSnack(pepsi);
        machine.addSnack(cheetos);
        machine.addSnack(doritos);
        machine.addSnack(kitkat);
        machine.addSnack(snickers);

        // Create Chain of Responsibility
        SnackDispenseHandler handler = new CokeHandler(coke, new PepsiHandler(pepsi, new CheetosHandler(cheetos, new DoritosHandler(doritos, new KitKatHandler(kitkat, new SnickersHandler(snickers, null))))));

        machine.setSnackDispenser(handler);

        // Test 1: Successful purchase
        System.out.println("\n===== PURCHASE COKE =====");

        machine.selectSnack("Coke");
        machine.insertMoney(2.00);

        // Test 2: Insufficient money
        System.out.println("\n===== INSUFFICIENT MONEY =====");

        machine.selectSnack("Pepsi");
        machine.insertMoney(1.00);
        machine.insertMoney(1.00);

        // Test 3: Chain forwarding
        System.out.println("\n===== PURCHASE CHEETOS =====");

        machine.selectSnack("Cheetos");
        machine.insertMoney(1.50);

        // Test 4: Returning change
        System.out.println("\n===== PURCHASE DORITOS WITH CHANGE =====");

        machine.selectSnack("Doritos");
        machine.insertMoney(5.00);

        // Test 5: Purchase KitKat
        System.out.println("\n===== PURCHASE KITKAT =====");

        machine.selectSnack("KitKat");
        machine.insertMoney(1.50);

        // Test 6: Purchase last Snickers
        System.out.println("\n===== PURCHASE LAST SNICKERS =====");

        machine.selectSnack("Snickers");
        machine.insertMoney(1.75);

        System.out.println("Snickers remaining: "
                + snickers.getQuantity());

        // Test 7: Out-of-stock refund
        System.out.println("\n===== SNICKERS OUT OF STOCK =====");

        machine.selectSnack("Snickers");
        machine.insertMoney(1.75);

        // Test 8: Invalid selection
        System.out.println("\n===== INVALID SNACK SELECTION =====");

        machine.selectSnack("Sprite");

        // Display final inventory
        System.out.println("\n===== FINAL INVENTORY =====");

        printSnack(coke);
        printSnack(pepsi);
        printSnack(cheetos);
        printSnack(doritos);
        printSnack(kitkat);
        printSnack(snickers);
    }

    private static void printSnack(Snack snack) {
        System.out.println(snack.getName() + ": "
                + snack.getQuantity() + " remaining");
    }
}