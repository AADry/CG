package task2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.util.Stack;

public class Test {

    static class Point {
        int x; // координата x
        int y; // координата y

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedImage in = ImageIO.read(new File("src/task2/figure.png"));
        // Создаем изображение
        BufferedImage image = new BufferedImage( in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_RGB);
        // Заполняем его случайными цветами
        int backgroundColor = (255 << 16) | (255 << 8) | 255;

        // Задаем цвет границы многоугольника (черный)
        int borderColor = -16777216;

        // Задаем цвет заливки многоугольника (красный)
        int fillColor = -65536;


        for (int i = 0; i < in.getWidth(); i++) {
            for (int j = 0; j < in.getHeight(); j++) {
                image.setRGB(i, j, in.getRGB(i, j));
            }
        }
        System.out.println(image.getHeight() + " " + image.getWidth());
        // Создаем окно
        JFrame frame = new JFrame("Image");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        // Создаем метку с иконкой из изображения
        JLabel label = new JLabel(new ImageIcon(image));
        // Добавляем метку к окну
        frame.add(label);
        // Показываем окно
        frame.setVisible(true);

        fillPolygon(frame, image, 90,90,fillColor,borderColor);
    }

    public static void fillPolygon(JFrame frame,  BufferedImage image, int xSeed, int ySeed, int fillColor, int borderColor) throws InterruptedException {
        // Создаем стек для хранения точек
        Stack<Test.Point> stack = new Stack<>();

        // Добавляем затравочную точку в стек
        stack.push(new Test.Point(xSeed, ySeed));

        // Пока стек не пустой
        while (!stack.isEmpty()) {
            // Извлекаем верхнюю точку из стека
            Test.Point p = stack.pop();

            // Проверяем, что координаты точки не выходят за границы изображения
            if (p.x >= 0 && p.x < image.getWidth() && p.y >= 0 && p.y < image.getHeight()) {
                // Получаем цвет текущей точки
                int currentColor = image.getRGB(p.x, p.y);
                // Если цвет текущей точки не равен цвету заливки и не равен цвету границы
                if (currentColor != fillColor && currentColor != borderColor) {
                    // Закрашиваем текущую точку цветом заливки
                    image.setRGB(p.x, p.y, fillColor);
                    //stack.remove(p);

                    // Добавляем в стек соседние точки по горизонтали и вертикали
                    stack.push(new Test.Point(p.x + 1, p.y));
                    stack.push(new Test.Point(p.x - 1, p.y));
                    stack.push(new Test.Point(p.x, p.y + 1));
                    stack.push(new Test.Point(p.x, p.y - 1));
                }
            }
            Thread.sleep(1);
            frame.repaint();
        }
    }

}
