import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test extends JFrame {
    public Test() {
        JButton button = new JButton("Press Me!");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button pressed");
            }
        });
        getContentPane().add(button);
        setSize(75,75);
        setVisible(true);
    }
    public static void main (String argv[]) {
        new Test();
    }
}
