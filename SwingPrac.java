import javax.swing.*;
import java.awt.*;

public class SwingPrac {
    public static void main(String[] args) {
        // Frame or the window
        JFrame frame = new JFrame("Ybrik Store");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        

        JPanel products = new JPanel(); // product container
        JPanel menu = new JPanel();     // menu container
        products.setLayout(new FlowLayout());  // wraps
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS)); // stacks vertically

        menu.setBackground(Color.RED);
        products.setBackground(Color.GREEN);

        // add button
        JButton b1 = new JButton("Add Product");
        b1.addActionListener(e -> addproduct(products)); // add method to the button
        menu.add(b1); // add to the side

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


    public static void addproduct(JPanel panel) {
        // container for each product
        JPanel p2 = new JPanel(); // container 2
        p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));

        String name = JOptionPane.showInputDialog("Enter product name:"); // get name
        JLabel n2 = new JLabel(name);
        JButton b2 = new JButton("Edit"); // button
        p2.add(n2); 
        p2.add(b2);
        panel.add(p2); // add to the product container
        // basically just refresh the window to get the feeling of instant update
        panel.revalidate(); 
        panel.repaint();    
    }
}