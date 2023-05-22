package Shifting_n_scaling;

import Rotation_n_displacement.Displacement;

import javax.swing.*;
import java.awt.*;

public class Shifting extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);


        double tanX, tanY;
        tanX = 1.4;
        tanY = 1.3;
        //массив для координатной оси 0 0
        int[] x = new int[]{10, 25, 25, 10};
        int[] y = new int[]{10, 10, 40, 40};

        g.drawPolygon(x, y, 4);

        int xcurr;
        int ycurr;

        //преобразуем для новой координатной оси
        for (int i = 0; i < 4; i++) {
            xcurr = x[i];
            ycurr = y[i];
            x[i] =(int) (xcurr + ycurr * tanX);
            //y[i] = (int) (xcurr * tanY + ycurr);
        }
g.setColor(Color.green);
        //новое расположение прямоугольника
        g.drawPolygon(x, y, 4);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Polygon Demo");
        // Устанавливаем размер окна
        frame.setSize(300, 300);
        // Устанавливаем действие при закрытии окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Добавляем панель с многоугольником в окно
        frame.add(new Shifting());
        // Делаем окно видимым
        frame.setVisible(true);
    }
}
