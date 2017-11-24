/**
 * for storing models.
 * */
public class ModelRecorder{
    protected int iteration;
    protected double maxreal;
    protected double minreal;
    protected double maxmaginary;
    protected double minmaginary;
    protected int colorr;
    protected int colorg;
    protected int colorb;
    /**
     * the constructor.
     * @param iterations
     * @param cb
     * @param cg
     * @param cr
     * @param maxm
     * @param maxr
     * @param minm
     * @param minr
     * */
    protected ModelRecorder(int iterations, double maxr, double minr, double maxm, double minm, int cr, int cb, int cg) {
        iteration = iterations;
        maxreal = maxr;
        minreal = minr;
        maxmaginary = maxm;
        minmaginary = minm;
        colorr = cr;
        colorb = cb;
        colorg = cg;
    }
    /**
     * get.
     * */
    protected int getIteration() {
        return iteration;
    }
    /**
     * get.
     * */
    protected double getMaxreal() {
        return maxreal;
    }
    /**
     * get.
     * */
    protected double getMinreal() {
        return minreal;
    }
    /**
     * get.
     * */
    protected double getMaxmaginary() {
        return maxmaginary;
    }
    /**
     * get.
     * */
    protected double getMinmaginary() {
        return minmaginary;
    }
    /**
     * get.
     * */
    protected int getColorr() {
        return colorr;
    }
    /**
     * get.
     * */
    protected int getColorg() {
        return colorg;
    }
    /**
     * get.
     * */
    protected int getColorb() {
        return colorb;
    }
}
