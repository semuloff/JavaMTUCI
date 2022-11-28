package Labs.Lab_4.src;

import java.awt.geom.Rectangle2D;


public class Mandelbrot extends FractalGenerator {
    // Устанавливаем максимальное количество итераций.
    public static final int MAX_ITERATIONS = 2000;

    public void getInitialRange(Rectangle2D.Double rectangle) {
        // Значения для фрактала Мандельброта.
        rectangle.x = -2;
        rectangle.y = -1.5;
        rectangle.height = 3;
        rectangle.width = 3;
    }

    public int numIterations(double x, double y) {
        /**
         * Функция для фрактала Мандельброта.
         * Zn = (Zn-1)^2 + c
         * **/
        int iteration = 0; // Текущая итерация.
        double zReal = 0.0; // Действительная часть.
        double zComplex = 0.0; // Комплексная часть.

        while (iteration < MAX_ITERATIONS && zReal * zReal + zComplex * zComplex < 4) {
            double zRealTemp = zReal * zReal - zComplex * zComplex + x;
            double zComplexTemp = 2 * zReal * zComplex + y;
            zReal = zRealTemp;
            zComplex = zComplexTemp;
            iteration++;
        }

        if (iteration == MAX_ITERATIONS)
            return -1;

        return iteration;
    }
}