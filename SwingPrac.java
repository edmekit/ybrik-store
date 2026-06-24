import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class SwingPrac {
    public static void main(String[] args) {
        // Frame or the window
        ArrayList<Products> inventory = new ArrayList<>();  
        JFrame frame = new JFrame("Ybrik Store");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        

        JPanel products = new JPanel(); // product container
        JPanel menu = new JPanel();     // menu container
        products.setLayout(new BorderLayout());  // wraps
        menu.setLayout(new GridLayout(4, 1)); // stacks vertically

        menu.setBackground(Color.LIGHT_GRAY);
        products.setBackground(Color.WHITE);

        // add button
        JButton addbtn = new JButton("Add Product");
        addbtn.addActionListener(e -> addproduct(products, inventory)); // add method to the button
        menu.add(addbtn); // add to the side

        // edit button
        JButton editbtn = new JButton("Edit Product");
        menu.add(editbtn); // add to the side

        // delete button
        JButton deletebtn = new JButton("Delete Product");
        deletebtn.addActionListener(e -> deleteProduct(products, inventory));
        menu.add(deletebtn); // add to the side

        // list button
        JButton listbtn = new JButton("List Products");
        listbtn.addActionListener(e -> listProducts(products, inventory));
        menu.add(listbtn); // add to the side

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


    public static void addproduct(JPanel panel, ArrayList<Products> inventory) {
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

    public static void listProducts(JPanel panel, ArrayList<Products> inventory) {
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

    public static void deleteProduct(JPanel panel, ArrayList<Products> inventory) {
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

    public static Products getProduct(List<Products> inventory, String name) {
    for (Products p : inventory) {       // loop each product in the list  
        if (p.getName().equals(name)) { // get(i) returns a product so I can use Products methods
                                                        // getName() method for a Products object, equals() method to compare strings 
            return p;                   // returns a product
        }
    }
    return null;
    }  
}