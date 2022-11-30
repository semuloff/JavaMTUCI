package Labs.Lab_5.src;

import java.awt.geom.Rectangle2D;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class FractalExplorer {
    private int displaySize;
    private JImageDisplay display;
    private FractalGenerator fractal;
    private Rectangle2D.Double rectangle;

    /**
     * Конструктор сохраняет размер окна и инициализирует все поля.
     **/
    public FractalExplorer(int size) {
        displaySize = size;

        fractal = new Mandelbrot();
        rectangle = new Rectangle2D.Double();
        fractal.getInitialRange(rectangle);

        // Квадратное окно.
        display = new JImageDisplay(displaySize, displaySize);
    }

    /**
     * Этот метод инициализирует Swing GUI из полей находящихся в классе.
     **/
    public void createAndShowGUI() {
        display.setLayout(new BorderLayout());

        /**
         * Создание окна с заголовком и дальнейшее ее заполнение компонентами.
         **/
        JFrame frame = new JFrame("Fractal Explorer.");
        frame.add(display, BorderLayout.CENTER);

        Handler handler = new Handler();

        /**
         * Реализация компонента выборки фракталов.
         **/
        Mandelbrot mandelbrotFractal = new Mandelbrot();
        BurningShip burningShipFractal = new BurningShip();
        Tricorn tricornFractal = new Tricorn();

        JLabel fractalLabel = new JLabel("Fractal: ");
        JComboBox comboFractal = new JComboBox();

        comboFractal.addItem(mandelbrotFractal);
        comboFractal.addItem(burningShipFractal);
        comboFractal.addItem(tricornFractal);

        JPanel chooserPanel = new JPanel();
        chooserPanel.add(fractalLabel);
        chooserPanel.add(comboFractal);

        JButton resetButton = new JButton("Reset");
        JButton saveButton = new JButton("Save");

        JPanel controlPanel = new JPanel();
        controlPanel.add(saveButton);
        controlPanel.add(resetButton);

        comboFractal.addActionListener(handler);
        resetButton.addActionListener(handler);
        saveButton.addActionListener(handler);

        frame.add(chooserPanel, BorderLayout.NORTH);
        frame.add(controlPanel, BorderLayout.SOUTH);

        // Обработчик для клика мыши по фракталу.
        MouseHandler click = new MouseHandler();

        display.addMouseListener(click);

        // Окно закрывается только при нажатие крестика.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /**
     * Класс-обработчик событий, которые происходят в приложении.
     **/
    private class Handler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String commandName = e.getActionCommand();

            if (e.getSource() instanceof JComboBox) {
                JComboBox sourceBox = (JComboBox) e.getSource();
                fractal = (FractalGenerator) sourceBox.getSelectedItem();
                fractal.getInitialRange(rectangle);
                drawFractal();
            } else if (commandName.equals("Reset")) {
                fractal.getInitialRange(rectangle);
                drawFractal();
            } else if (commandName.equals("Save")) {

            }
        }
    }

    /**
     * Метод для отрисовки фрактала. Окрашивает пиксели в зависимотси от итерации.
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

    // Класс-обработчик собитий со стороны мыши.
    private class MouseHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();

            // Новые координаты центра.
            double xCoord = FractalGenerator.getCoord(rectangle.x,
                    rectangle.x + rectangle.width, displaySize, x);
            double yCoord = FractalGenerator.getCoord(rectangle.y,
                    rectangle.y + rectangle.height, displaySize, y);

            // Устанавливаем центр в точку, по которой был клик и приближаем.
            fractal.recenterAndZoomRange(rectangle, xCoord, yCoord, 0.5);

            drawFractal();
        }
    }

    public static void main(String[] args) {
        FractalExplorer fractal = new FractalExplorer(800);
        fractal.createAndShowGUI();
        fractal.drawFractal();
    }
}