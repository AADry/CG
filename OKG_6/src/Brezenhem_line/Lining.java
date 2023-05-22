package Brezenhem_line;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.jar.JarEntry;

public class Lining extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Random r;

        for (int i = 0; i < 100; i++) {
            r = new Random();
            int x0, y0;
            int x1, y1;
            int c;
            x0 = r.nextInt(10,300);
            x1 = r.nextInt(100, 500);
            y0 = r.nextInt(50, 500);
            y1 = r.nextInt(100,500);
            c = r.nextInt(1, 4);
            switch (c) {
                case 1:
                    g.setColor(Color.red);
                    break;
                case 2:
                    g.setColor(Color.blue);
                    break;
                case 3:
                    g.setColor(Color.black);
                    break;
                default:
                    break;
            }

            int dx = Math.abs(x1 - x0);
            int dy = Math.abs(y1 - y0);
            int error = 0;
            int derror = dy + 1;
            int y = y0;
            int diry = y1 - y0;
            if (diry > 0) {
                diry = 1;
            } else {
                diry = -1;
            }
            for (int x = x0; x < x1; x++) {
                g.drawLine(x, y, x, y);
                error = error + derror;
                if (error >= (dx + 1)) {
                    y = y + diry;
                    error = error - (dx + 1);
                }
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
        frame.add(new Lining());
        // Делаем окно видимым
        frame.setVisible(true);
    }
}
