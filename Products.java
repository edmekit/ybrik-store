import java.util.Scanner;

public class Products {
    // attributes, set to private so only this class can change them
    public String name;
    public  double price;
    public int quantity;

    public Products(String name, double price, int quantity) {
        // **this** keyword refers to the object where u are using the method
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // self explanatory stuff
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
            System.out.printf(" Choose which field you want to edit: \n  [1] %s\n  [2] %.2f\n  [3] %d\n  [4] Cancel\n  Choose an option: ", this.name, this.price, this.quantity);
            int choice = Integer.parseInt(input.nextLine());
            
            switch (choice) {
                case 1 -> {
                    System.out.print("  Enter the name of the item: ");
                    String name = input.nextLine();
                    this.setName(name);
                    break;
                }
                case 2 -> {
                    System.out.print("  Enter the price of the item: ");
                    double price = Double.parseDouble(input.nextLine());
                    this.setPrice(price);
                    break;
                }
                case 3 -> {
                    System.out.print("  Enter the quantity of the item: ");
                    int quantity = Integer.parseInt(input.nextLine());
                    this.setQuantity(quantity);
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


    private void setName(String name) {
        this.name = name;
    }

    private void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        } else {
            System.out.println("\nInvalid price. Please enter a positive number.\n");
        }
    }

    private void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        } else {
            System.out.println("\nInvalid quantity. Please enter a non-negative number.\n");
        }
    }
} 
