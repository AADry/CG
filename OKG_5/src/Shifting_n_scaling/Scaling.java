package Shifting_n_scaling;

import javax.swing.*;
import java.awt.*;

public class Scaling extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        // зададим старое и новое начало координат
        int Ox_old, Oy_old;
        Ox_old = 50;
        Oy_old = 50;
        double Scaling = 0.7;

        //массив для координатной оси 0 0
        int[] x = new int[]{10, 25, 25, 10};
        int[] y = new int[]{10, 10, 40, 40};

        //для наглядности переместим прямоугольник
        for (int i = 0; i < 4; i++) {
            x[i] += Ox_old;
            y[i] += Oy_old;
        }
        //точка начала координат
        g.drawRect(Ox_old,Oy_old,0,0);
        //расположение многоугольника
        g.drawPolygon(x, y, 4);

        //масштабируем
        for (int i = 0; i < 4; i++) {
            x[i] *= Scaling;
            y[i] *= Scaling;
        }
        //начало координат
        g.drawRect(50, 50, 0, 0);
        //расположение масштабированного прямоугольника
        g.drawPolygon(x, y, 4);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Polygon Demo");
        // Устанавливаем размер окна
        frame.setSize(300, 300);
        // Устанавливаем действие при закрытии окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Добавляем панель с многоугольником в окно
        frame.add(new Scaling());
        // Делаем окно видимым
        frame.setVisible(true);
    }
}
