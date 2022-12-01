package Labs.Lab_5.src;

import java.awt.geom.Rectangle2D;

public class Tricorn extends FractalGenerator {
    public static final int MAX_ITERATIONS = 2000;

    /**
     * This method allows the fractal generator to specify which part
     * of the complex plane is the most interesting for a fractal.
     * It is passed a rectangle object and the method modifies the
     * rectangle's fields to show the correct initial range for the fractal.
     * This implementation sets the initial range to x=-2, y=-2, width=height=4.
     */
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2;
        range.y = -2;
        range.width = 4;
        range.height = 4;
    }

    public int numIterations(double x, double y) {
        // Start with iterations at 0.
        int iteration = 0;
        // Initialize zreal and zimaginary.
        double zreal = 0;
        double zimaginary = 0;

        /**
         * Compute Zn = [complex conjugate(Zn-1)]^2 + c where values are
         * complex numbers represented by zreal and zimaginary, Z0=0, and
         * c is the particular point in the fractal that we are displaying
         * (given by x and y).  It is iterated until Z^2 > 4 (absolute value
         * of Z is greater than 2) or maximum number of iterations is reached.
         */
        while (iteration < MAX_ITERATIONS &&
                zreal * zreal + zimaginary * zimaginary < 4) {
            double zrealUpdated = zreal * zreal - zimaginary * zimaginary + x;
            double zimaginaryUpdated = -2 * zreal * zimaginary + y;
            zreal = zrealUpdated;
            zimaginary = zimaginaryUpdated;
            iteration += 1;
        }

        if (iteration == MAX_ITERATIONS) {
            return -1;
        }

        return iteration;
    }

    public String toString() {
        return "Tricorn.";
    }
}
