package Brezenhem_circle;

import Brezenhem_line.Lining;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Circling extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Random r;
        for (int i = 0; i < 100; i++) {
            r = new Random();
            int x1;
            int y1;
            int R;
            x1 = r.nextInt(30, 500);
            y1 = r.nextInt(30, 500);
            R = r.nextInt(5, 60);
            int x = 0;
            int y = R;
            int delta = 1 - 2 * R;
            int error = 0;
            while (y >= x) {
                g.drawRect(x1 + x, y1 + y, 0, 0);
                g.drawRect(x1 + x, y1 - y, 0, 0);
                g.drawRect(x1 - x, y1 + y, 0, 0);
                g.drawRect(x1 - x, y1 - y, 0, 0);
                g.drawRect(x1 + y, y1 + x, 0, 0);
                g.drawRect(x1 + y, y1 - x, 0, 0);
                g.drawRect(x1 - y, y1 + x, 0, 0);
                g.drawRect(x1 - y, y1 - x, 0, 0);
                error = 2 * (delta + 1) - 1;
                if (delta < 0 && error <= 0) {
                    delta += 2 * ++x + 1;
                    continue;
                }
                if (delta > 0 && error > 0) {
                    delta -= 2 * --y + 1;
                }
                delta += 2 * (++x - --y);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Polygon Demo");
        // Устанавливаем размер окна
        frame.setSize(300, 300);
        // Устанавливаем действие при закрытии окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Добавляем панель с многоугольником в окно
        frame.add(new Circling());
        // Делаем окно видимым
        frame.setVisible(true);
    }
}
