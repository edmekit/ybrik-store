import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
     
            int choice = scanner.nextInt();
     
            switch (choice) {
                /*case "1" -> addItem(scanner);
                case "2" -> editItem(scanner);
                case "3" -> deleteItem(scanner);
                case "4" -> listItems(); */
                case 5 -> { return; }  // exit the loop
                default  -> System.out.println("\nInvalid option. Please enter 1-7.\n");
            }
       }
    }


    private static void printDivider(char ch, int length) {
        System.out.println("  " + String.valueOf(ch).repeat(length));
    }


}