package Rotation_n_displacement;

import javax.swing.*;
import java.awt.*;

public class Rotation extends JPanel {


    public static void main(String[] args) {

        JFrame frame = new JFrame("Polygon Demo");
        // Устанавливаем размер окна
        frame.setSize(300, 300);
        // Устанавливаем действие при закрытии окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Добавляем панель с многоугольником в окно
        frame.add(new Rotation());
        // Делаем окно видимым
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        // Вызываем метод суперкласса
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        int[] xnew = new int[4];
        int[] ynew = new int[4];
        int[] x = new int[]{10, 25, 25, 10};
        int[] y = new int[]{10, 10, 40, 40};
        //угол поворота
        int alpha = 360;

        //точки после смены начала координат
        for (int i = 0; i < 4; i++) {
            xnew[i] = x[i] + 50;
            ynew[i] = y[i] + 50;
        }
        g.drawPolygon(xnew, ynew, 4);

        g.setColor(Color.red);
        //точка начала координат, точка вращения
        g.drawRect(50,50,0,0);
        g.setColor(Color.green);
        double xcurr, ycurr;
        double xnow, ynow;
        for (int i = 0; i < x.length; i++) {
            xcurr = x[i];
            ycurr = y[i];
            xnow = xcurr * Math.cos(Math.toRadians(alpha)) - ycurr * Math.sin(Math.toRadians(alpha));
            ynow = xcurr * Math.sin(Math.toRadians(alpha)) + ycurr * Math.cos(Math.toRadians(alpha));
            x[i] = (int) Math.round(xnow) + 50;
            y[i] = (int) Math.round(ynow) + 50;
        }
        g.drawPolygon(x, y, 4);
    }

}