import java.util.ArrayList;
import java.util.Observable;
/**
 * Model class.
 *
 * */
public class Model extends Observable{
    protected int iteration_current;
    protected double minReal_current;
    protected double maxReal_current;
    protected double minImaginary_current;
    protected double maxImaginary_current;
    protected int xResolution_current;
    protected int yResolution_current;
    protected int colorr;
    protected int colorg;
    protected int colorb;
    ArrayList<ModelRecorder> list = new ArrayList<>();
    ModelRecorder strore;
    ModelRecorder getmodel;
    /**
     * model constructor
     * */
    public Model() {
        //initialise
        iteration_current = MandelbrotCalculator.INITIAL_MAX_ITERATIONS;
        maxReal_current = MandelbrotCalculator.INITIAL_MAX_REAL;
        minReal_current = MandelbrotCalculator.INITIAL_MIN_REAL;
        minImaginary_current = MandelbrotCalculator.INITIAL_MIN_IMAGINARY;
        maxImaginary_current = MandelbrotCalculator.INITIAL_MAX_IMAGINARY;
        xResolution_current = Numbers.X_Resolution;
        yResolution_current = Numbers.Y_Resolution;
        colorb = Numbers.DEFAULTWHITECOLOR;
        colorg = Numbers.DEFAULTWHITECOLOR;
        colorr = Numbers.DEFAULTWHITECOLOR;
        strore = new ModelRecorder(MandelbrotCalculator.INITIAL_MAX_ITERATIONS, MandelbrotCalculator.INITIAL_MAX_REAL, MandelbrotCalculator.INITIAL_MIN_REAL, MandelbrotCalculator.INITIAL_MAX_IMAGINARY, MandelbrotCalculator.INITIAL_MIN_IMAGINARY, colorr, colorb, colorg);
        list.add(strore);
    }
    /**
     * get iteration.
     * */
    public int getIteration_current() {
        return iteration_current;
    }
    /**
     * zoom method.
     * @param x1 the left location
     *           @param x2 the right location
     *                     @param y1
     *                     @param y2
     * */
    public void zoom(double x1, double x2, double y1, double y2) {
        minReal_current = x1;
        maxReal_current = x2;
        minImaginary_current = y1;
        maxImaginary_current = y2;
        strore = new ModelRecorder(iteration_current, maxReal_current, minReal_current, maxImaginary_current, minImaginary_current, colorr, colorb,colorg);
        list.add(strore);
        setChanged();
        notifyObservers();
    }
    /**
     * get.
     * */
    public double getMinReal_current() {
        return minReal_current;
    }
    /**
     * get.
     * */
    public double getMinImaginary_current() {
        return minImaginary_current;
    }
    /**
     * get.
     * */
    public double getMaxReal_current() {
        return maxReal_current;
    }
    /**
     * get.
     * */
    public double getMaxImaginary_current() {
        return maxImaginary_current;
    }
    /**
     * get.
     * */
    public int getxResolution_current() {
        return xResolution_current;
    }
    /**
     * get.
     * */
    public int getyResolution_current() {
        return yResolution_current;
    }
    /**
     * get.
     * */
    public int getColorr() {
        return colorr;
    }
    /**
     * get.
     * */
    public void setColorr(int colr) {
        colorr = colr;
    }
    /**
     * get.
     * */
    public int getColorg() {
        return colorg;
    }
    /**
     * get.
     * */
    public int getColorb() {
        return colorb;
    }
    /**
     * update.
     * @param str  the input iteration
     * */
    public void update(String str) {
        iteration_current = Integer.valueOf(str);
        minImaginary_current = MandelbrotCalculator.INITIAL_MIN_IMAGINARY;
        maxImaginary_current = MandelbrotCalculator.INITIAL_MAX_IMAGINARY;
        minReal_current = MandelbrotCalculator.INITIAL_MIN_REAL;
        maxReal_current = MandelbrotCalculator.INITIAL_MAX_REAL;
        setIterations(str);
        setChanged();
        notifyObservers();
    }
    /**
     * change colour.
     * @param str  the input color's r
     * */
    public void changeColor(String str) {
        colorr = Integer.valueOf(str);
        maxImaginary_current = MandelbrotCalculator.INITIAL_MAX_IMAGINARY;
        minImaginary_current = MandelbrotCalculator.INITIAL_MIN_IMAGINARY;
        maxReal_current = MandelbrotCalculator.INITIAL_MAX_REAL;
        minReal_current = MandelbrotCalculator.INITIAL_MIN_REAL;
        strore = new ModelRecorder(iteration_current, maxReal_current, minReal_current, maxImaginary_current, minImaginary_current, colorr, colorb, colorg);
        list.add(strore);
        setChanged();
        notifyObservers();
    }
    /**
     * get MANDELCALC DATA
     * */
    protected int[][] getData(){
        int[][] data;
        MandelbrotCalculator mandelCalc = new MandelbrotCalculator();
        data = mandelCalc.calcMandelbrotSet(xResolution_current, yResolution_current, minReal_current, maxReal_current, minImaginary_current, maxImaginary_current, iteration_current, MandelbrotCalculator.DEFAULT_RADIUS_SQUARED);
        return data;
    }
    /**
     * SET.
     * */
    protected int setIterations(String str) {
        int integer = Integer.valueOf(str);
        strore = new ModelRecorder(integer, maxReal_current, minReal_current, maxImaginary_current, minImaginary_current, colorr, colorb, colorg);
        list.add(strore);
        return integer;
    }
    /**
     * undo, use counters.
     * */
    protected int undo(int count1, int count2) {
        int undoIteration = list.size() - count1 + count2 - Numbers.SIZE;
        if (undoIteration < Numbers.ZERO) {
            iteration_current = MandelbrotCalculator.INITIAL_MAX_ITERATIONS;
            maxReal_current = MandelbrotCalculator.INITIAL_MAX_REAL;
            minReal_current = MandelbrotCalculator.INITIAL_MIN_REAL;
            maxImaginary_current = MandelbrotCalculator.INITIAL_MAX_IMAGINARY;
            minImaginary_current = MandelbrotCalculator.INITIAL_MIN_IMAGINARY;
            colorr = Numbers.DEFAULTWHITECOLOR;
            colorg = Numbers.DEFAULTWHITECOLOR;
            colorb = Numbers.DEFAULTWHITECOLOR;
        } else {
            getmodel = list.get(undoIteration);
            iteration_current = getmodel.getIteration();
            maxReal_current = getmodel.getMaxreal();
            minReal_current = getmodel.getMinreal();
            maxImaginary_current = getmodel.getMaxmaginary();
            minImaginary_current = getmodel.getMinmaginary();
            colorr = getmodel.getColorr();
            colorb = getmodel.getColorb();
            colorg = getmodel.getColorg();
        }
        setChanged();
        notifyObservers();
        return undoIteration;
    }
    /**
     * redo, use counters.
     * */
    protected int redo(int count1, int count2) {
        int redoIteration = list.size() + count2 - count1 - Numbers.SIZE;
        if (redoIteration > list.size()) {
            iteration_current = getIteration_current();
            maxReal_current = getMaxReal_current();
            minReal_current = getMinReal_current();
            maxImaginary_current = getMaxImaginary_current();
            minImaginary_current = getMinImaginary_current();
            colorr = getColorr();
            colorg = getColorg();
            colorb = getColorb();
        } else {
            redoIteration = list.size() - Numbers.SIZE;
            getmodel = list.get(redoIteration);
            iteration_current = getmodel.getIteration();
            maxReal_current = getmodel.getMaxreal();
            minReal_current = getmodel.getMinreal();
            maxImaginary_current = getmodel.getMaxmaginary();
            minImaginary_current = getmodel.getMinmaginary();
            colorr = getmodel.getColorr();
            colorb = getmodel.getColorb();
            colorg = getmodel.getColorg();
        }
        setChanged();
        notifyObservers();
        return count2 - count1;
    }
}
