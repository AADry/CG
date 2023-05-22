package Displaying_n_combconv;

import javax.swing.*;
import java.awt.*;

public class CombConv extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        int[] x = new int[]{20, 40, 40, 20};
        int[] y = new int[]{5, 5, 15, 15};

        for (int i = 0; i < 4; i++) {
            x[i] += 50;
            y[i] += 50;
        }
        g.translate(200,200);
        int[][] Otrazhenie = new int[2][2];
        Otrazhenie[0][0] = 0;
        Otrazhenie[0][1] = -1;
        Otrazhenie[1][0] = -1;
        Otrazhenie[1][1] = 0;

        int[][] Povorot = new int[2][2];
        Povorot[0][0] = 0;
        Povorot[0][1] = 1;
        Povorot[1][0] = -1;
        Povorot[1][1] = 0;

        g.drawPolygon(x, y, 4);
        g.drawLine(50,-50, -50,50);
        g.setColor(Color.red);
        g.drawRect(0,0,0,0);
        int xcur, ycur;
        // Отражение прямоугольника относительно y=-x
        for(int i = 0; i < 4; i++) {
            xcur = x[i];
            ycur = y[i];
            x[i] = xcur * Otrazhenie[0][0] + ycur * Otrazhenie[1][0];
            y[i] = xcur * Otrazhenie[0][1] + ycur * Otrazhenie[1][1];
        }
        g.setColor(Color.BLUE);
        g.drawPolygon(x, y, 4);
        // Поворот на 90 градусов
        for (int i = 0; i < 4; i++) {
            xcur = x[i];
            ycur = y[i];
            x[i] = xcur * Povorot[0][0] + ycur * Povorot[1][0];
            y[i] = xcur * Povorot[0][1] + ycur * Povorot[1][1];
        }
        g.setColor(Color.GREEN);
        g.drawPolygon(x, y, 4);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Polygon Demo");
        // Устанавливаем размер окна
        frame.setSize(300, 300);
        // Устанавливаем действие при закрытии окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Добавляем панель с многоугольником в окно
        frame.add(new CombConv());
        // Делаем окно видимым
        frame.setVisible(true);
    }
}
