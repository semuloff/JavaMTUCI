package Labs.Lab_6.src;

import java.awt.geom.Rectangle2D;

import javax.swing.*;
import javax.swing.filechooser.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;


public class FractalExplorer {
    private JComboBox comboFractal;
    private JButton resetButton;
    private JButton saveButton;

    /** Количество оставшихся строк. **/
    private int rowsRemaining;

    /** Целочисленный размер экрана — это ширина и высота экрана в пикселях. **/
    private int displaySize;

    private JImageDisplay display;

    /** Объект FractalGenerator для каждого типа фрактала. **/
    private FractalGenerator fractal;

    /**
     * Объект Rectangle2D.Double, определяющий диапазон отображаемого комплекса.
     **/
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
        comboFractal = new JComboBox();

        comboFractal.addItem(mandelbrotFractal);
        comboFractal.addItem(burningShipFractal);
        comboFractal.addItem(tricornFractal);

        JPanel chooserPanel = new JPanel();
        chooserPanel.add(fractalLabel);
        chooserPanel.add(comboFractal);

        resetButton = new JButton("Reset");
        saveButton = new JButton("Save");

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
                // Компонент для выбора путей для сохраниения.
                JFileChooser pathChooser = new JFileChooser();

                // Сохраните только изображения PNG формата.
                FileFilter extensionFilter =
                        new FileNameExtensionFilter("PNG Images", "png");
                pathChooser.setFileFilter(extensionFilter);

                /**
                 * Средство выбора гарантированно не разрешит пользователю отличных от png форматов.
                 **/
                pathChooser.setAcceptAllFileFilterUsed(false);

                /**
                 * Выскакивает окно «Сохранить файл», которое позволяет
                 * пользователю выбирать каталог и файл для сохранения.
                 **/
                int userSelection = pathChooser.showSaveDialog(display);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    // Получение файла.
                    java.io.File file = pathChooser.getSelectedFile();

                    // Попытка сохранения изображения на диск.
                    try {
                        BufferedImage displayImage = display.getImage();
                        javax.imageio.ImageIO.write(displayImage, "png", file);
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(display,
                                exception.getMessage(), "Cannot Save Image",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                else return;
            }
        }
    }

    // Класс-обработчик событий со стороны мыши.
    private class MouseHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();

            // Новые координаты центра.
            double xCoord = FractalGenerator.getCoord(rectangle.x,
                    rectangle.x + rectangle.width, displaySize, x);
            double yCoord = FractalGenerator.getCoord(rectangle.y,
                    rectangle.y + rectangle.height, displaySize, y);

            // Устанавливаем центр в точку, по которой был клик, и приближаем.
            fractal.recenterAndZoomRange(rectangle, xCoord, yCoord, 0.5);

            drawFractal();
        }
    }

    private class FractalWorker extends SwingWorker<Object, Object> {
        int yCoordinate;

        /**
         * Массив целых чисел для хранения вычисленных значений RGB
         * для каждого пикселя в строке.
         **/
        int[] computedRGBValues;

        private FractalWorker(int row) {
            yCoordinate = row;
        }

        /**
         * Этот метод вызывается в фоновом потоке. Он вычисляет
         * значение RGB для всех пикселей в 1 строке и сохраняет его в
         * соответствующий элемент массива целых чисел. Возвращает null.
         **/
        protected Object doInBackground() {
            computedRGBValues = new int[displaySize];

            // Перебираем все пиксели в строке.
            for (int pixel = 0; pixel < computedRGBValues.length; pixel++) {

                // Находим соответствующие координаты xCoord и yCoord в области отображения фрактала.
                double xCoord = fractal.getCoord(rectangle.x,
                        rectangle.x + rectangle.width, displaySize, pixel);
                double yCoord = fractal.getCoord(rectangle.y,
                        rectangle.y + rectangle.height, displaySize, yCoordinate);

                // Вычисляет количество итераций для координат в области отображения фрактала.
                int iteration = fractal.numIterations(xCoord, yCoord);

                // Если количество итераций равно -1, устанавливает текущий int в вычисленном массиве значений RGB int в черный цвет.
                if (iteration == -1) {
                    computedRGBValues[pixel] = 0;
                }

                else {
                    // В противном случае выбираем значение оттенка на основе количества итераций.
                    float hue = 0.7f + (float) iteration / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);

                    // Обновляем массив int цветом текущего пикселя.
                    computedRGBValues[pixel] = rgbColor;
                }
            }
            return null;
        }

        /**
         * Вызывается при завершении фоновой задачи. Рисует пиксели
         * для текущей строки и обновляет отображение этой строки.
         **/
        @Override
        protected void done() {
            /**
             * Итерация по массиву данных строки, рисование пикселей,
             * которые были вычислены в doInBackground().
             * Перерисовывает строку, которая была изменена.
             **/
            for (int pixel = 0; pixel < computedRGBValues.length; pixel++) {
                display.drawPixel(pixel, yCoordinate, computedRGBValues[pixel]);
            }

            display.repaint(0, 0, yCoordinate, displaySize, 1);

            // В конце появляется возможность взаимодействие с приложением.
            rowsRemaining--;
            if (rowsRemaining == 0) {
                enableUI(true);
            }
        }
    }

    /**
     * Метод для отрисовки фрактала. Окрашивает пиксели в зависимотси от итерации.
     **/
    private void drawFractal() {
        /** Вызов enableUI(false), чтобы отключить все элементы
         * управления пользовательского интерфейса во время рисования.
         **/
        enableUI(false);

        rowsRemaining = displaySize;

        // Прокручиваем каждую строку на дисплее и вызываем FractalWorker для ее рисования.
        for (int row = 0; row < displaySize; row++){
            FractalWorker drawRow = new FractalWorker(row);
            drawRow.execute();
        }

    }

    // Метод обновляет включенное состояние кнопки сохранения, кнопки сброса и поля со списком.
    private void enableUI(boolean value) {
        comboFractal.setEnabled(value);
        saveButton.setEnabled(value);
        resetButton.setEnabled(value);
    }

    public static void main(String[] args) {
        FractalExplorer fractal = new FractalExplorer(800);
        fractal.createAndShowGUI();
        fractal.drawFractal();
    }
}