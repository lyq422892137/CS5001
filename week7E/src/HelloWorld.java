import javax.swing.*;

public class HelloWorld extends JFrame {
    public static void main(String args[]) {
        new HelloWorld();
    }
    HelloWorld () {
        JLabel jlbHelloWorld = new JLabel("Hello World");
        getContentPane().add(jlbHelloWorld);
        this.setSize(100,100);
        setVisible(true); // set the GUI visible
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
