package Displaying_n_combconv;

import Shifting_n_scaling.Scaling;

import javax.swing.*;
import java.awt.*;

public class Displaying extends JPanel {
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

        //можно менять для получения линии
        int[][] line = new int[2][2];
        line[0][0] = 0;
        line[0][1] = 1;
        line[1][0] = 1;
        line[1][1] = 0;

        g.drawLine(50,50,100,100);
        g.drawPolygon(x, y, 4);

        int xcur, ycur;
        for(int i = 0; i < 4; i++) {
            xcur = x[i];
            ycur = y[i];
            x[i] = xcur * line[0][0] + ycur * line[1][0];
            y[i] = xcur * line[0][1] + ycur * line[1][1];
        }
        g.setColor(Color.blue);
        g.drawPolygon(x, y, 4);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Polygon Demo");
        // Устанавливаем размер окна
        frame.setSize(300, 300);
        // Устанавливаем действие при закрытии окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Добавляем панель с многоугольником в окно
        frame.add(new Displaying());
        // Делаем окно видимым
        frame.setVisible(true);
    }
}
