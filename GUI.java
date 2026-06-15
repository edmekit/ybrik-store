import javax.swing.JFrame;

public class GUI {

    public GUI() {
        JFrame frame = new JFrame();     // creates a frame
        frame.setTitle("Ybrik Store");   // title
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // exit
        frame.setSize(500,500);
        frame.setVisible(true);
        

    }

    public static void main(String[] args){
        new GUI();
    }
}