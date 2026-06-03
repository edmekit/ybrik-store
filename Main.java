import java.util.*;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);       // scanner object
        List<Products> inventory = new ArrayList<>();   // list of Products objects

        mainMenu(scanner, inventory);

        scanner.close();
    }

    public static void mainMenu(Scanner scanner, List<Products> inventory) {
        while (true) {
            printDivider('=', 55);
            System.out.println("        STORE INVENTORY MANAGER");
            printDivider('=', 55);
            System.out.println("  [1] Add item");
            System.out.println("  [2] Edit item");
            System.out.println("  [3] Delete item");
            System.out.println("  [4] List all items");
            System.out.println("  [5] Exit");

            System.out.print("  Choose an option: ");
     
            int choice = Integer.parseInt(scanner.nextLine()); // lez use this when getting inputs from choice
                                                               // nextInt and others leave a \n in the buffer             
            switch (choice) {
                case 1 -> addItem(scanner, inventory);
                case 2 -> editMenu(scanner, inventory);
                // case "3" -> deleteItem(scanner);
                case 4 -> listItems(inventory); 
                case 5 -> { return; }  // exit the loop
                default  -> System.out.println("\nInvalid option. Please enter 1-5.\n");
            }
       }
    }


    private static void printDivider(char ch, int length) {
        System.out.println("  " + String.valueOf(ch).repeat(length));
    }

    private static Products getProduct(List<Products> products, String name) {
        for (int i = 0; i < products.size(); i++) {       // loop each product in the list  
            if (products.get(i).getName().equals(name)) { // get(i) returns a product so I can use Products methods
                                                          // getName() method for a Products object, equals() method to compare strings 
                return products.get(i);                   // returns a product
            }
        }
        return null;
    }

    private static void addItem(Scanner scanner, List<Products> inventory) {
        // get inputs and store to variables
        System.out.print("\n  Enter the name of the item: ");
        String name = scanner.nextLine();
        System.out.print("  Enter the price of the item: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("  Enter the quantity of the item: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        Products product = new Products(name, price, quantity); // make new Products object
        inventory.add(product);                                 // add to the list

        System.out.println("\nItem added successfully!\n");
    }

    private static void editMenu(Scanner scanner, List<Products> inventory) {
        System.out.print("\nEnter name of product to edit: ");
        String name = scanner.nextLine();

        Products product = getProduct(inventory, name);             // get the product with the name entered

        if (product != null) {
            product.editItem(scanner);
            System.out.println("\nItem edited successfully!\n");
        } else {
            System.out.println("\nProduct not found.\n");
        }
    }

    private static void listItems(List<Products> inventory){
            printDivider('=', 55);
        System.out.println("        INVENTORY");
            printDivider('=', 55);
        for (int i = 0; i < inventory.size(); i++) {
            Products product = inventory.get(i);
            System.out.printf("Name: %s\n", product.getName());
            System.out.printf("Price: %.2f \n", product.getPrice());
            System.out.printf("Quantity: %d \n\n", product.getQuantity());
        }
    }

}