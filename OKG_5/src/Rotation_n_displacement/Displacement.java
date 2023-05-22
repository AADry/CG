package Rotation_n_displacement;

import javax.swing.*;
import java.awt.*;

public class Displacement extends JPanel{


    public static void main(String[] args) {

        JFrame frame = new JFrame("Polygon Demo");
        // Устанавливаем размер окна
        frame.setSize(300, 300);
        // Устанавливаем действие при закрытии окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Добавляем панель с многоугольником в окно
        frame.add(new Displacement());
        // Делаем окно видимым
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        // зададим старое и новое начало координат
        int Ox_old, Oy_old;
        int Ox_new, Oy_new;
        int dx, dy;
        Ox_old = 100;
        Oy_old = 100;
        Ox_new = 100;
        Oy_new = 50;

        //изменение координаты
        dx = Ox_new - Ox_old;
        dy = Oy_new - Oy_old;

        //массив для координатной оси 0 0
        int[] x = new int[]{10, 25, 25, 10};
        int[] y = new int[]{10, 10, 40, 40};

        //преобразуем для Ox_old и Oy_old
        for (int i = 0; i < 4; i++) {
            x[i] += Ox_old;
            y[i] += Oy_old;
        }
        //старая точка начала координат
        g.drawRect(Ox_old,Oy_old,0,0);
        //старое расположение многоугольника
        g.drawPolygon(x, y, 4);

        //преобразуем для новой координатной оси
        for (int i = 0; i < 4; i++) {
            x[i] += dx;
            y[i] += dy;
        }
        //новое начало координат
        g.drawRect(Ox_new, Oy_new, 0, 0);
        g.setColor(Color.green);
        //новое расположение прямоугольника
        g.drawPolygon(x, y, 4);
    }
}
