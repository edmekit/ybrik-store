import java.util.*;  
import java.io.*; // for file handling


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);       // scanner object
        File file = new File("Test.txt");
        List<Products> inventory = loadProducts(file);   // load products

        mainMenu(scanner, inventory);

        saveProducts(inventory);                         // save products after choosing exit  
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
                case 3 -> deleteItem(scanner, inventory);
                case 4 -> listItems(inventory); 
                case 5 -> { return; }  // exit the loop
                default  -> System.out.println("\nInvalid option. Please enter 1-5.\n");
            }
       }
    }


    private static void printDivider(char ch, int length) {
        System.out.println("  " + String.valueOf(ch).repeat(length));
    }

    private static Products getProduct(List<Products> inventory, String name) {
        for (Products p : inventory) {       // loop each product in the list  
            if (p.getName().equals(name)) { // get(i) returns a product so I can use Products methods
                                                          // getName() method for a Products object, equals() method to compare strings 
                return p;                   // returns a product
            }
        }
        return null;
    }

    private static void deleteItem(Scanner scanner, List<Products> inventory) {
        System.out.print("\nEnter name of product to delete: ");
        String name = scanner.nextLine();

        Products product = getProduct(inventory, name);

        if (product != null) {
            inventory.remove(product);
            System.out.println("\nItem deleted successfully!\n");
        } else {
            System.out.println("\nProduct not found.\n");
        }
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

    private static List<Products> loadProducts(File file) {
        List<Products> inventory = new ArrayList<>();
        try {
            Scanner fileread = new Scanner(file);

            while (fileread.hasNextLine()){                             // while there is a line
                String line = fileread.nextLine();                      // read a line
                String[] parts = line.split(",");                // split the line based on the regex   
                String name = parts[0];
                double price = Double.parseDouble(parts[1]);
                int quantity = Integer.parseInt(parts[2]);
                Products product = new Products(name, price, quantity); // make a new Products object
                inventory.add(product);                                 // add to the list
            }
            fileread.close();
            return inventory;
        }

        catch (FileNotFoundException e) {                       // error handling of Java
            System.out.println("File not found.");
            return inventory;              // return empty list if file is not found         
        }

    }

    private static void saveProducts(List<Products> inventory) {
        try {
            FileWriter filewrite = new FileWriter("Test.txt");
            for (Products product : inventory) {                    // get product to write
                String name = product.getName();                            
                double price = product.getPrice();
                int quantity = product.getQuantity();
                String line = name + "," + price + "," + quantity + "\n";   // template to write to file
                filewrite.write(line);   
            }
            filewrite.close();

        } catch (IOException e) {                                       // error handling   
            System.out.println("An error occurred while writing to the file.");
            }
  
    }
}