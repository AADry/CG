package Approximation;

import java.awt.*;
import java.util.Random;

public class Circle {

    public static void main(String[] args) {
        // Создаем окно с заголовком "Approximation.Circle"
        Frame frame = new Frame("Approximation.Circle");
        // Устанавливаем размер окна
        frame.setSize(500, 500);
        // Добавляем панель для рисования
        MyPanel panel = new MyPanel();
        frame.add(panel);
        // Делаем окно видимым
        frame.setVisible(true);
    }
}

class MyPanel extends Panel {
    // Переопределяем метод paint для рисования на панели
    public void paint(Graphics g) {
        // Преобразуем Graphics в Graphics2D для большего контроля над рисованием
        Graphics2D g2 = (Graphics2D) g;
        Random r;
        // Устанавливаем цвет рисования в красный
        g2.setColor(Color.RED);
        // Устанавливаем толщину линии в 2 пикселя
        g2.setStroke(new BasicStroke(2));
        for (int i = 0; i < 100; i++) {
            r = new Random();
            // Рисуем окружность с помощью алгоритма аппроксимации правильным многоугольником
            drawCircle(g2, r.nextInt(30, 500), r.nextInt(30, 500), r.nextInt(10,50), 10); // центр (250, 250), радиус 200, количество сторон 100
        }
    }

    // Метод для рисования окружности с помощью алгоритма аппроксимации правильным многоугольником
    public void drawCircle(Graphics2D g2, int x, int y, int r, int n) {
        // Вычисляем угол между соседними вершинами многоугольника в радианах
        double angle = 2 * Math.PI / n;
        // Создаем массивы для хранения координат вершин многоугольника
        int[] xPoints = new int[n];
        int[] yPoints = new int[n];
        // Заполняем массивы координатами вершин многоугольника
        for (int i = 0; i < n; i++) {
            xPoints[i] = (int) (x + r * Math.cos(i * angle)); // x-координата i-й вершины
            yPoints[i] = (int) (y + r * Math.sin(i * angle)); // y-координата i-й вершины
        }
        // Рисуем многоугольник по координатам вершин
        g2.drawPolygon(xPoints, yPoints, n);
    }
}