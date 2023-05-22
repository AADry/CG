package Koch;

import java.awt.*;
import javax.swing.*;

public class KochCurve extends JPanel {

    // Рекурсивный метод для рисования кривой Коха
    public void drawKochCurve(Graphics g, int x1, int y1, int x2, int y2, int level) {
        if (level == 0) { // базовый случай: просто рисуем линию
            g.drawLine(x1, y1, x2, y2);
        } else { // рекурсивный случай: разбиваем линию на четыре части
            // Вычисляем координаты трех новых точек
            int x3 = (2 * x1 + x2) / 3;
            int y3 = (2 * y1 + y2) / 3;
            int x4 = (x1 + 2 * x2) / 3;
            int y4 = (y1 + 2 * y2) / 3;
            int x5 = (int) ((x1 + x2) / 2 + Math.sqrt(3) * (y1 - y2) / 6);
            int y5 = (int) ((y1 + y2) / 2 + Math.sqrt(3) * (x2 - x1) / 6);

            // Рисуем четыре кривые Коха меньшего уровня
            drawKochCurve(g, x1, y1, x3, y3, level - 1);
            drawKochCurve(g, x3, y3, x5, y5, level - 1);
            drawKochCurve(g, x5, y5, x4, y4, level - 1);
            drawKochCurve(g, x4, y4, x2, y2, level - 1);
        }
    }

    // Переопределяем метод paintComponent для рисования на панели
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // вызываем родительский метод для очистки панели
        g.setColor(Color.BLUE); // выбираем цвет для рисования
        int level = 4; // выбираем уровень рекурсии
        int width = getWidth(); // получаем ширину панели
        int height = getHeight(); // получаем высоту панели
        // Рисуем три кривые Коха для образования снежинки Коха
        drawKochCurve(g, width / 4, height / 2, width * 3 / 4, height / 2, level);
        drawKochCurve(g, width * 3 / 4, height / 2,
                width / 2 + (int) (Math.sqrt(3) * height / 4),
                height / 2 - height / 4,
                level);
        drawKochCurve(g,
                width / 2 + (int) (Math.sqrt(3) * height / 4),
                height / 2 - height / 4,
                width / 4,
                height / 2,
                level);
    }

    // Главный метод для запуска приложения
    public static void main(String[] args) {
        JFrame frame = new JFrame("Кривая Коха"); // создаем окно с заголовком
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // устанавливаем операцию закрытия окна
        frame.setSize(600, 600); // устанавливаем размер окна
        frame.setLocationRelativeTo(null); // центрируем окно на экране
        frame.add(new KochCurve()); // добавляем панель с рисунком в окно
        frame.setVisible(true); // делаем окно видимым
    }
}