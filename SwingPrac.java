import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;



public class SwingPrac {
    public static void main(String[] args) {
        // Frame or the window
        ArrayList<Products> inventory = loadProducts(new File("Test.txt"));  
        JFrame frame = new JFrame("Ybrik Store");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        

        JPanel products = new JPanel(); // product container
        JPanel menu = new JPanel();     // menu container
        products.setLayout(new BorderLayout());  // wraps
        menu.setLayout(new GridLayout(5, 1)); // stacks vertically

        menu.setBackground(Color.LIGHT_GRAY);
        products.setBackground(Color.WHITE);

        // add button
        JButton addbtn = new JButton("Add Product");
        addbtn.addActionListener(e -> addproduct(products, inventory)); // add method to the button
        menu.add(addbtn); // add to the side

        // edit button
        JButton editbtn = new JButton("Edit Product");
        editbtn.addActionListener(e -> editProduct(products, inventory));
        menu.add(editbtn); // add to the side

        // delete button
        JButton deletebtn = new JButton("Delete Product");
        deletebtn.addActionListener(e -> deleteProduct(products, inventory));
        menu.add(deletebtn); // add to the side

        // list button
        JButton listbtn = new JButton("List Products");
        listbtn.addActionListener(e -> listProducts(products, inventory));
        menu.add(listbtn); // add to the side

        // exit button
        JButton exitbtn = new JButton("Exit");
        exitbtn.addActionListener(e -> {
            saveProducts(inventory);
            System.exit(0);
        });
        menu.add(exitbtn);

        // gets both container and splits them
        JSplitPane splitPane = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            menu,
            products
        );


        splitPane.setDividerLocation(0.3); // 30% left, 70% right
        frame.add(splitPane); // add to the main frame
        frame.setVisible(true);
    }


    private static void addproduct(JPanel panel, ArrayList<Products> inventory) {
        panel.removeAll();
        // container for each product
        JPanel form = new JPanel(new GridLayout(4, 2));
        JTextField nameField = new JTextField(20);
        JTextField priceField = new JTextField(10);
        JTextField quantityField = new JTextField(10);
        JButton addButton = new JButton("Add");

        form.add(new JLabel("Name"));
        form.add(nameField);

        form.add(new JLabel("Price"));
        form.add(priceField);

        form.add(new JLabel("Quantity"));
        form.add(quantityField);

        form.add(new JLabel(""));
        form.add(addButton);

        addButton.addActionListener(e -> {
        String name = nameField.getText();
        double price = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());

        Products product = new Products(name, price, quantity);
        inventory.add(product);
        JOptionPane.showMessageDialog(panel, "Item added successfully!");
        });

        panel.add(form);
        panel.revalidate();
        panel.repaint();
    }

    private static void listProducts(JPanel panel, ArrayList<Products> inventory) {
        panel.removeAll();

        JPanel display = new JPanel();
        display.setLayout(new FlowLayout());
        for (Products p : inventory) {
            String name = p.getName();
            double price = p.getPrice();
            int quantity = p.getQuantity();

            JPanel productPanel = new JPanel();
            productPanel.setLayout(new GridLayout(3, 1, 10, 10));
            productPanel.add(new JLabel("Name: " + name));
            productPanel.add(new JLabel("Price: " + price));
            productPanel.add(new JLabel("Quantity: " + quantity));

            display.add(productPanel);
        }
        panel.add(display);
        panel.revalidate();
        panel.repaint();
    }

    private static void deleteProduct(JPanel panel, ArrayList<Products> inventory) {
        panel.removeAll();

        JPanel disp = new JPanel();
        disp.setLayout(new BoxLayout(disp, BoxLayout.Y_AXIS));
        JTextField nameField = new JTextField(20);
        nameField.setMaximumSize(nameField.getPreferredSize());
        
        disp.add(new JLabel("Name of product to delete: "));
        disp.add(nameField);

        disp.add(new JLabel(""));
        JButton delbtn = new JButton("Delete");
        disp.add(delbtn);



        delbtn.addActionListener(e -> {
            String name = nameField.getText();
            Products p = getProduct(inventory, name);
            if (p != null) {
                inventory.remove(p);
                JOptionPane.showMessageDialog(panel, "Item deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(panel, "Product not found.");
            }
        });

        panel.add(disp);
        panel.revalidate();
        panel.repaint();
    }


    private static void editProduct(JPanel panel, ArrayList<Products> inventory) {
        panel.removeAll();

        JPanel disp = new JPanel();
        
        disp.setLayout(new BoxLayout(disp, BoxLayout.Y_AXIS));
        JTextField nameField = new JTextField(20);
        nameField.setMaximumSize(nameField.getPreferredSize());
        disp.add(new JLabel("Name of product to edit: "));
        disp.add(nameField);
        JButton editbtn = new JButton("Edit");
        disp.add(editbtn);

        editbtn.addActionListener(e -> {
            String name = nameField.getText();
            Products p = getProduct(inventory, name);
            if (p != null) {
                editItem(panel, p);
            } else {
                JOptionPane.showMessageDialog(panel, "Product not found.");
            }
        });


        panel.add(disp);
        panel.revalidate();
        panel.repaint();
    }

    private static void editItem(JPanel panel, Products p) {
        panel.removeAll();
        // container for each product
        JPanel form = new JPanel(new GridLayout(4, 2));
        JTextField nameField = new JTextField(20);
        JTextField priceField = new JTextField(10);
        JTextField quantityField = new JTextField(10);
        JButton addButton = new JButton("Confirm edit");

        form.add(new JLabel("New Name"));
        form.add(nameField);

        form.add(new JLabel("New Price"));
        form.add(priceField);

        form.add(new JLabel("New Quantity"));
        form.add(quantityField);

        form.add(new JLabel(""));
        form.add(addButton);

        addButton.addActionListener(e -> {
        String name = nameField.getText();
        double price = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());
        
        p.name = name;
        p.price = price;
        p.quantity = quantity;

        JOptionPane.showMessageDialog(panel, "Item edited successfully!");
        });

        panel.add(form);
        panel.revalidate();
        panel.repaint();
    }


    private static Products getProduct(ArrayList<Products> inventory, String name) {
    for (Products p : inventory) {       // loop each product in the list  
        if (p.getName().equals(name)) { // get(i) returns a product so I can use Products methods
                                                        // getName() method for a Products object, equals() method to compare strings 
            return p;                   // returns a product
        }
    }
    return null;
    }  

    private static ArrayList<Products> loadProducts(File file) {
    ArrayList<Products> inventory = new ArrayList<>();
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

    private static void saveProducts(ArrayList<Products> inventory) {
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