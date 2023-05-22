package MidPoint;

import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

public class Circle {

    public static void main(String[] args) {
        // Создаем окно с заголовком "Circle"
        Frame frame = new Frame("Circle");
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
        // Устанавливаем цвет рисования в красный
        g2.setColor(Color.blue);
        Random r;
        // Рисуем окружность с помощью метода Средней точки (Midpoint)
        for (int i = 0; i < 100; i++) {
            r = new Random();
            drawCircle(g2, r.nextInt(30,500), r.nextInt(30,500), r.nextInt(10,50)); // центр (250, 250), радиус 200
        }
    }

    // Метод для рисования окружности с помощью метода Средней точки (Midpoint)
    public void drawCircle(Graphics2D g2, int x0, int y0, int r) {
        // Инициализируем переменные x и y
        int x = r;
        int y = 0;
        // Инициализируем переменную d - расстояние от текущей точки до границы окружности
        int d = 1 - r;
        // Рисуем первую точку на окружности
        drawPixel(g2, x0 + x, y0 + y);
        // Пока x больше или равно y
        while (x >= y) {
            // Увеличиваем y на единицу
            y++;
            // Если d меньше нуля
            if (d < 0) {
                // Увеличиваем d на 2 * y + 1
                d += 2 * y + 1;
            } else {
                // Уменьшаем x на единицу
                x--;
                // Увеличиваем d на 2 * (y - x) + 1
                d += 2 * (y - x) + 1;
            }
            // Рисуем восемь симметричных точек на окружности
            drawPixel(g2, x0 + x, y0 + y);
            drawPixel(g2, x0 - x, y0 + y);
            drawPixel(g2, x0 + x, y0 - y);
            drawPixel(g2, x0 - x, y0 - y);
            drawPixel(g2, x0 + y, y0 + x);
            drawPixel(g2, x0 - y, y0 + x);
            drawPixel(g2, x0 + y, y0 - x);
            drawPixel(g2, x0 - y, y0 - x);
        }
    }

    // Метод для рисования пикселя по заданным координатам
    public void drawPixel(Graphics2D g2, int x, int y) {
        // Рисуем линию длиной в один пиксель по заданным координатам
        g2.drawLine(x, y, x, y);
    }
}
