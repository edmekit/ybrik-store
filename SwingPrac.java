import javax.swing.*;
import java.awt.*;
import java.util.*;

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
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS)); // stacks vertically

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
        menu.add(deletebtn); // add to the side

        // list button
        JButton listbtn = new JButton("List Products");
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
}