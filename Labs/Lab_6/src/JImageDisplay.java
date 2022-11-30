package Labs.Lab_6.src;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class JImageDisplay extends JComponent {
    // Управляет изображением, содержимое которого можно записать.
    private BufferedImage image;

    public JImageDisplay(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Dimension dim = new Dimension(width, height);

        super.setPreferredSize(dim);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
    }

    public void clearImage() {
        // Заменяем каждый пиксель на черный.
        for (int width = 0; width < image.getWidth(); width++) {
            for (int height = 0; height < image.getHeight(); height++) {
                // Делает [width][height] пиксель черным.
                image.setRGB(width, height, 0);
            }
        }
    }

    public void drawPixel(int x, int y, int rgbColor) {
        // Устанавливаем определенный цвет для пикселя.
        image.setRGB(x, y, rgbColor);
    }

    public BufferedImage getImage() {
        return image;
    }
}