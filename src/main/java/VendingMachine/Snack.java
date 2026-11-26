package VendingMachine;

public class Snack {
    private String name;
    private int quantity;
    private double price;

    public Snack(String name,double price, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }
}
