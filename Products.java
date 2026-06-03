import java.util.Scanner;

public class Products {
    private String name;
    private double price;
    private int quantity;

    public Products(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void editItem(Scanner input) {
        while (true) {
            System.out.printf(" Choose which field you want to edit: \n  [1] %s\n  [2] %f\n  [3] %d\n  [4] Cancel\n  Choose an option: ", this.name, this.price, this.quantity);
            int choice = Integer.parseInt(input.nextLine());
            
            switch (choice) {
                case 1 -> {
                    System.out.print("  Enter the name of the item: ");
                    this.name = input.nextLine();
                    break;
                }
                case 2 -> {
                    System.out.print("  Enter the price of the item: ");
                    this.price = Double.parseDouble(input.nextLine());
                    break;
                }
                case 3 -> {
                    System.out.print("  Enter the quantity of the item: ");
                    this.quantity = Integer.parseInt(input.nextLine());
                    break;
                }
                case 4 -> {
                    return;
                }
                default -> {
                    System.out.println("\nInvalid option. Please enter 1-4.\n");
                    break;
                }
            }            
        }

    }

} 
