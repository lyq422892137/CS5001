import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
/**
 * the panel to draw models.
 *
 * */
public class Panel extends JPanel implements MouseListener, MouseMotionListener {
    Model model;
    private int x1, x2, y1, y2;
    private int width;
    private int height;
    private Color color_current = new Color(Numbers.DEFAULTWHITECOLOR, Numbers.DEFAULTWHITECOLOR, Numbers.DEFAULTWHITECOLOR);
    private Color mycolor;
    /**
     * the constructor of the panel.
     * @param model refers to the model.
     * */
    public Panel(Model model) {
        setLayout(null);
        color_current = new Color(model.getColorr(), model.getColorg(), model.getColorg());
        setBackground(color_current);
        this.model = model;
        super.addMouseListener(this);
        super.addMouseMotionListener(this);
        width = Numbers.X_Resolution;
        height = Numbers.Y_Resolution;
    }
    /**
     * get Jpanel's width.
     * */
    public int getWidth() {
        return width;
    }
    /**
     * get height.
     * */
    public int getHeight() {
        return height;
    }
    /**
     * override paint method.
     * @param g graphics
     * */
    public void paint(Graphics g) {
        super.paint(g);
        int[][] madelbrotData = model.getData();
        double random = Math.random();
        for (int i = Numbers.ZERO; i < madelbrotData.length; i++) {
            for (int q = Numbers.ZERO; q < madelbrotData[i].length; q++) {
                if (madelbrotData[i][q] >= model.getIteration_current()) {
                    g.setColor(Color.black);
                } else {
                    /*
                    * this part seems a little annoying. But i like the way to generate colours.
                    * */
                    int differ = model.getIteration_current() - madelbrotData[i][q];
                    if (differ <= Numbers.COLORADJUST) {
                        mycolor = new Color(Math.abs(model.getColorr() - Numbers.COLORADJUST), Math.abs(model.getColorg() - Numbers.COLORADJUST), Math.abs(model.getColorb()- Numbers.COLORADJUST));
                    } else if (madelbrotData[i][q] <= model.getIteration_current() * Numbers.COLOARCLASS) {
                        mycolor = new Color(Math.abs(Numbers.DEFAULTWHITECOLOR - model.getColorr()), (int)(model.getColorg() * random), (int)(model.getColorg() * (Numbers.SIZE - random)));
                    } else if (madelbrotData[i][q] <= model.getIteration_current() * (Numbers.COLOARCLASS * Numbers.DOUBLE)) {
                        mycolor = new Color(model.getColorr(), Math.abs(Numbers.DEFAULTWHITECOLOR - model.getColorr()), model.getColorb());
                    } else if (madelbrotData[i][q] <= model.getIteration_current() * (Numbers.COLOARCLASS * Numbers.DOUBLE + Numbers.COLOARCLASS)) {
                        mycolor = new Color((int)(Math.floor(model.getColorr() * random)), (int) ( Math.floor(model.getColorg() * random)), (int) (Math.floor(model.getColorb() * random)));
                    } else if (madelbrotData[i][q] <= model.getIteration_current() * (Numbers.COLOARCLASS * Numbers.DOUBLE * Numbers.DOUBLE)) {
                        mycolor = new Color((int)(Math.floor(model.getColorr() * random)), (int) ( Math.floor(model.getColorg() * random)), (int) (Math.floor(model.getColorb() * random)));
                    } else if (madelbrotData[i][q] <= model.getIteration_current() * (Numbers.COLOARCLASS * Numbers.DOUBLE * Numbers.DOUBLE + Numbers.COLOARCLASS)) {
                        mycolor = new Color((int)(Math.floor(model.getColorr() * random)), (int) ( Math.floor(model.getColorg() * random)), (int) (Math.floor(model.getColorb() * random)));
                    } else if (madelbrotData[i][q] <= model.getIteration_current() * (Numbers.COLOARCLASS * Numbers.DOUBLE * Numbers.DOUBLE * Numbers.DOUBLE)) {
                        mycolor = new Color((int)(Math.floor(model.getColorr() * random)), (int) ( Math.floor(model.getColorg() * random)), (int) (Math.floor(model.getColorb() * random)));
                    } else if (madelbrotData[i][q] <= model.getIteration_current() * (Numbers.COLOARCLASS * Numbers.DOUBLE * Numbers.DOUBLE * Numbers.DOUBLE + Numbers.COLOARCLASS)) {
                        mycolor = new Color((int)(Math.floor(model.getColorr() * random)), (int) ( Math.floor(model.getColorg() * random)), (int) (Math.floor(model.getColorb() * random)));
                    } else {
                        mycolor = new Color((int) (Math.floor(model.getColorr() * Math.random())), (int) (Math.floor(model.getColorg() * Math.random())), (int) (Math.floor(model.getColorb() * Math.random())));
                    }
                    g.setColor(mycolor);
                }
                g.fillRect(q,i,Numbers.TRANGLEWIDTH,Numbers.TRANGLEHEIGHT);
            }
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x1 = e.getX();
        y1 = e.getY();
    }
    /**
     * for zooming.
     * @param e mouser events
     * */
    @Override
    public void mouseReleased(MouseEvent e) {
        double realDifference = model.getMaxReal_current() - model.getMinReal_current();
        double imaginaryDifference = model.getMaxImaginary_current() - model.getMinImaginary_current();
        if (x1 - x2 > Numbers.ZERO) {
            int xexchange = x1;
            x1 = x2;
            x2 = xexchange;
        }
        if (y1 - y2 > Numbers.ZERO) {
            int yexchange = y1;
            y1 = y2;
            y2 = yexchange;
        }
        y2 = y1 + x2 - x1;
        double newx1 = model.getMinReal_current() + ((double) x1 / model.getxResolution_current()) * realDifference;
        double newx2 = model.getMaxReal_current() - ((double) model.getxResolution_current() - x2) / model.getxResolution_current() * realDifference;
        double newy1 = model.getMinImaginary_current() + ((double) y1 / model.getyResolution_current()) * imaginaryDifference;
        double newy2 = model.getMaxImaginary_current() - ((double) model.getyResolution_current() - y2) / model.getyResolution_current() * imaginaryDifference;
        model.zoom(newx1, newx2, newy1, newy2);
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
    }
    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
