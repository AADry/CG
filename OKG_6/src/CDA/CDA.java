package CDA;

import Brezenhem_line.Lining;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class CDA extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Random r;
        for (int i = 0; i < 100; i++) {
            r = new Random();
            double x1, y1, x2, y2;
            x1 = r.nextInt(10,300);
            x2 = r.nextInt(100, 500);
            y1 = r.nextInt(50, 500);
            y2 = r.nextInt(100,500);
            double Px, Py;
            Px = x2 - x1;
            Py = y2 - y1;
            g.drawLine((int) x1, (int) y1, (int) x1, (int) y1);
            while (x1 < x2) {
                x1 = x1 + 1;
                y1 = y1 + Py / Px;
                g.drawLine((int) x1, (int) Math.round(y1), (int) x1, (int) Math.round(y1));
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
        frame.add(new CDA());
        // Делаем окно видимым
        frame.setVisible(true);
    }
}

