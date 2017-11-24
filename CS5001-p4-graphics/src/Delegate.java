import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
/**
 * Delegate class of a Delegate-Model model.
 *
 *  */
public class Delegate implements Observer{
    private JFrame mainFrame;
    private JToolBar toolbar;
    private JTextField inputField;
    private JTextField inputField2;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JScrollPane outputPane;
    private JTextArea outputField;
    private JMenuBar menu;
    private Panel panel;
    private Model model;
    private JLabel pictureLabel;
    private JPanel picturePanel;
    private int undoClickCount = Numbers.INITIALCLICK;
    private int redoClickCount = Numbers.INITIALCLICK;
    /**
     * Instantiate a new Delegate object.
     * @param model is the model to observer
     *
     */
    public Delegate(Model model){
        this.model = model;
        this.mainFrame = new JFrame();  // set up the main frame for this GUI
        menu = new JMenuBar();
        toolbar = new JToolBar();
        inputField = new JTextField(Numbers.TEXT_WIDTH);
        inputField2 = new JTextField(Numbers.TEXT_WIDTH);
        outputField = new JTextArea(Numbers.TEXT_WIDTH, Numbers.TEXT_HEIGHT);
        //outputField.setEditable(false);
        outputPane = new JScrollPane(outputField);
        setupComponents();
        // add the delegate UI component as an observer of the model
        // so as to detect changes in the model and update the GUI view accordingly
        model.addObserver(this);
    }
    /**
     * set buttons reset, undo and redo and input texts.
     *
     * */
    private void setupToolbar(){
        button1 = new JButton("Reset");
        button1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                model.update(Numbers.REPAINT);
                panel.repaint();
            }
        });
        button2 = new JButton("Undo");
        button2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                undoClickCount++;
                if (model.undo(undoClickCount, redoClickCount) < Numbers.ZERO) {
                    JOptionPane.showMessageDialog(mainFrame, "Ooops, this is the first picture");
                    undoClickCount--;
                } else {
                    panel.repaint();
                }
            }
        });
        button3 = new JButton("Redo");
        button3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                redoClickCount++;
                if (model.redo(redoClickCount, undoClickCount) < Numbers.ZERO) {
                    JOptionPane.showMessageDialog(mainFrame, "Ooops, this is the latest picture");
                    redoClickCount--;
                } else {
                    panel.repaint();
                }
            }
        });
        JLabel label = new JLabel("Enter Iteration: ");

        inputField.addKeyListener(new KeyListener(){
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    model.update(inputField2.getText());
                    inputField.setText("");
                }
            }
            public void keyReleased(KeyEvent e) {
            }
            public void keyTyped(KeyEvent e) {
            }
        });
        JLabel label2 = new JLabel("Enter A Random Number from 0 - 255: ");
        inputField2.addKeyListener(new KeyListener(){
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    model.changeColor(inputField2.getText());
                    inputField2.setText("");
                }
            }
            public void keyReleased(KeyEvent e) {
            }
            public void keyTyped(KeyEvent e) {
            }
        });
        JButton add_button = new JButton("Change Iterations");
        add_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                model.update(inputField.getText());
                inputField.setText("");
            }
        });
        JButton color_button = new JButton("Change Color");
        color_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                model.changeColor(inputField2.getText());
                inputField.setText("");
            }
        });
        toolbar.add(button1);
        toolbar.add(button2);
        toolbar.add(button3);
        toolbar.add(label);
        toolbar.add(inputField);
        toolbar.add(add_button);
        toolbar.add(label2);
        toolbar.add(inputField2);
        toolbar.add(color_button);
        mainFrame.add(toolbar, BorderLayout.NORTH);
    }


    /**
     * keep studre's examples annotations.
     * Sets up File menu with Load and Save entries
     * The Load and Save actions would normally be translated to appropriate model method calls similar to the way the code does this
     * above in @see #setupToolbar(). However, load and save functionality is not implemented here, instead the code below merely displays
     * an error message.
     */
    private void setupMenu(){
        JMenu file = new JMenu ("File");
        JMenuItem load = new JMenuItem ("Load");
        JMenuItem save = new JMenuItem ("Save");
        file.add (load);
        file.add (save);
        menu.add (file);
        load.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(mainFrame, "Ooops, Load not linked to model!");
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "JPG & GIF Images", "jpg", "gif");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println(chooser.getSelectedFile()
                            .getAbsolutePath());
                    pictureLabel.setIcon(new ImageIcon(
                            chooser.getSelectedFile().getAbsolutePath()));
                }
                toolbar.add(pictureLabel);
                picturePanel = new JPanel();
                panel.add(picturePanel, BorderLayout.CENTER);
                picturePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
                {
                    pictureLabel = new JLabel("");
                    picturePanel.add(pictureLabel);
                }
            }
        });
        save.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                File dir = new File("");
                String path = dir.getAbsolutePath();
                BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D g2 = image.createGraphics();
                panel.paint(g2);
                try {
                    ImageIO.write(image, "jpeg", new File(path + "/image.jpg"));
                    JOptionPane.showMessageDialog(mainFrame, "Your image has been stored under " + path);
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(mainFrame, "Ooops, there is something wrong!");
                    e1.printStackTrace();
                }
            }
        });
        mainFrame.setJMenuBar(menu);
    }
    /**
     * Method to setup the menu and toolbar components.
     */
    private void setupComponents(){
        setupMenu();
        setupToolbar();
        mainFrame.add(outputPane, BorderLayout.CENTER);
        panel = new Panel(model);
        mainFrame.add(panel,BorderLayout.CENTER);
        mainFrame.setSize (Numbers.FRAME_WIDTH, Numbers.FRAME_HEIGHT);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void update(Observable o, Object arg) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                panel.repaint();
            }
        });
    }
}
