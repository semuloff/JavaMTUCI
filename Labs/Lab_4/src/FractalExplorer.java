package Labs.Lab_4.src;

import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.*;

public class FractalExplorer {
    private int displaySize;
    private JImageDisplay display;
    private FractalGenerator fractal;
    private Rectangle2D.Double rectangle;

    /**
     * Конструктор сохраняет размер окна и инициализирует все поля.
     */
    public FractalExplorer(int size) {
        displaySize = size;

        fractal = new Mandelbrot();
        rectangle = new Rectangle2D.Double();
        fractal.getInitialRange(rectangle);

        // Квадратное окно.
        display = new JImageDisplay(displaySize, displaySize);
    }

    /**
     * Этот метод инициализирует Swing GUI из полей находящихся в классе
     */
    public void createAndShowGUI() {
        display.setLayout(new BorderLayout());
        // Создаем окно с названием.
        JFrame frame = new JFrame("Fractal Explorer.");
        frame.add(display, BorderLayout.CENTER);

        // Создаем кнопку для сброса.
        JButton resetButton = new JButton("Reset scale.");

        // Обработчик для кнопки сброса.
        ResetHandler handler = new ResetHandler();
        resetButton.addActionListener(handler);
        frame.add(resetButton, BorderLayout.SOUTH);

        // Обработчик для клика мыши по фракталу.
        MouseHandler click = new MouseHandler();
        display.addMouseListener(click);

        // Окно закрывается только при нажатие крестика.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private class ResetHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Возвращает фрактал к изначальному положению.
            fractal.getInitialRange(rectangle);
            drawFractal();
        }
    }

    /**
     * Метод для отрисовки фрактала.
     * Окрашивает пиксели в зависимотси от итерации.
     **/
    private void drawFractal() {
        for (int x = 0; x < displaySize; x++) {
            for (int y = 0; y < displaySize; y++) {

                double xCoord = FractalGenerator.getCoord(rectangle.x, rectangle.x +
                        rectangle.width, displaySize, x);
                double yCoord = FractalGenerator.getCoord(rectangle.y, rectangle.y +
                        rectangle.height, displaySize, y);

                int iteration = fractal.numIterations(xCoord, yCoord);

                if (iteration == -1)
                    display.drawPixel(x, y, 256);
                else {
                    float hue = 0.7f + (float) iteration / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);

                    display.drawPixel(x, y, rgbColor);
                }
            }
        }
        display.repaint();
    }

    private class MouseHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();

            // Новые координаты центра
            double xCoord = FractalGenerator.getCoord(rectangle.x,
                    rectangle.x + rectangle.width, displaySize, x);
            double yCoord = FractalGenerator.getCoord(rectangle.y,
                    rectangle.y + rectangle.height, displaySize, y);

            // Устанавливаем центр в точку по которой был клик и приближаем.
            fractal.recenterAndZoomRange(rectangle, xCoord, yCoord, 0.5);

            // Перерисовываем.
            drawFractal();
        }
    }

    public static void main(String[] args) {
        FractalExplorer fractal = new FractalExplorer(800);
        fractal.createAndShowGUI();
        fractal.drawFractal();
    }
}