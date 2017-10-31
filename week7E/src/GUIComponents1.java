/**
 * http://blog.csdn.net/u011847748/article/details/41074561
 * Jpanel不是顶级窗口，不能直接输出。它必须放在象JFrame这样的顶级窗口上才能输出。
 * JcontentPane实际上就是一个JPanel。Jframe中会默认new一个JPanel，塞入JFrame中。
 * JPanel可以放在JFrame中，但是反过来就是不行的！效果上没什么特大的区别~！
 * JFrame用来做主页面框架，JPanel只是普通页面
 * JPanel可以放在JFrame中，反之不行.
 * JFrame可以看成,最底级容器,可以包括其他上级容器包括JPanel
 * JFrame只是一个界面，也就是个框架，要想把控件放在该界面中，必须把控件放在JPanel中，然后再把JPanel放在JFrame中，
 * JPanel作为一个容器使用。
 * JFrame是最底层，JPanel是置于其面上，同一个界面只有一个JFrame，一个JFrame可以放多个JPanel。如果你直接在JFrame上放也可以，
 * 但是首先不规范，然后要是过于复杂的界面你准备如何处理这么多控件呢？你可以写个复杂的界面，应该就可以感受到这种区别了
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;

public class GUIComponents1 extends JFrame {
    private JFrame jf = new JFrame("Week 7");
    private JPanel jp;
    private JTextField jtf;
    boolean blockFlag = true;
    private JButton jb;
    public GUIComponents1() {
        this.setSize(500,100);
        this.jp = new JPanel();
        this.jtf = new JTextField(10);
        this.jtf.setEnabled(true);
        this.jb = new JButton("Change");
        this.jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button Pressed " + blockFlag);
                if(blockFlag == true) {
                    String str = jtf.getText();
                    System.out.println("str: " + str);
                    str = str.toUpperCase();
                    System.out.println("str: " + str);
                    jtf.setText(str);
                    blockFlag = false;
                } else {
                    String str = jtf.getText();
                    str = str.toLowerCase();
                    jtf.setText(str);
                    blockFlag = true;
                }
            }
        });
        this.jp.add(this.jtf);
        this.jp.add(this.jb);
        getContentPane().add(jp);
        // setUpComponents();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
