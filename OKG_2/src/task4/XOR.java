package task4;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class XOR extends JPanel {
    Random rand = new Random();
    int nodes = rand.nextInt(3,5);

    Polygon polygon = new Polygon(new int[] {50, 60, 100, 110, new Random().nextInt(75, 90)}, new int[] {50, 100, 110, 60, new Random().nextInt(75, 90)}, 5);

    public void paintComponent(Graphics g) {
        /*for (int i = 0; i < nodes - 3; i++) {
            polygon.addPoint(new Random().nextInt(90, 100), new Random().nextInt(50, 80));
        }*/
        super.paintComponent(g);
        // Устанавливаем цвет контура
        g.setColor(Color.BLACK);
        // Рисуем многоугольник
        g.drawPolygon(polygon);
        // Устанавливаем цвет заливки
        g.setColor(Color.WHITE);
        // Заливаем многоугольник
        g.fillPolygon(polygon);


        //зададим массив пикселей 1 - черный 0 - белый
        int[][] pyxels = new int[60][60];
        for (int i = 0; i < 60; i++) {
            for (int j = 0; j < 60; j++) {
                pyxels[i][j] = 0;
            }
        }
        // грань
        int Xmax = 110;
        // счетчик для приращения x
        int counter = 0;
        // соотношение для каждого ребра
        double tg;
        int Xcurrent = 0;
        double a;
        g.setColor(Color.BLACK);
        for (int i = 0; i < 1; i++) {
            counter = 0;
            tg = (double) (polygon.xpoints[i] - polygon.xpoints[i+1]) / (double) (polygon.ypoints[i] - polygon.ypoints[i+1]);
            System.out.println(tg);

            for (int j = polygon.ypoints[i]; j < polygon.ypoints[i+1]; j++) {
                a = polygon.xpoints[0] + tg * counter;
                Xcurrent = (int)(polygon.xpoints[0] + tg * counter);
                for (int k = Xcurrent; k < Xmax; k++) {
                    if (pyxels[k-50][j-50] == 0) {
                        g.setColor(Color.black);
                        g.drawLine(k, j, k, j);
                        pyxels[k-50][j-50] = 1;
                    } else {
                        g.setColor(Color.WHITE);
                        g.drawLine(k, j, k, j);
                        pyxels[k-50][j-50] = 0;
                    }
                }
                counter++;
            }
        }
        for (int i = 1; i < 2; i++) {
            counter = 0;
            tg = (double) (polygon.xpoints[i] - polygon.xpoints[i+1]) / (double) (polygon.ypoints[i] - polygon.ypoints[i+1]);
            System.out.println(tg);

            for (int j = polygon.ypoints[i]; j < polygon.ypoints[i+1]; j++) {
                Xcurrent = (int)(polygon.xpoints[1] + tg * counter);
                for (int k = Xcurrent; k < Xmax; k++) {
                    if (pyxels[k-50][j-50] == 0) {
                        g.setColor(Color.black);
                        g.drawLine(k, j, k, j);
                        pyxels[k-50][j-50] = 1;
                    } else {
                        g.setColor(Color.WHITE);
                        g.drawLine(k, j, k, j);
                        pyxels[k-50][j-50] = 0;
                    }
                }
                counter++;
            }
        }
        for (int i = 2; i < 3; i++) {
            counter = 0;
            tg = (double) (polygon.xpoints[i] - polygon.xpoints[i + 1]) / (double) (polygon.ypoints[i] - polygon.ypoints[i + 1]);
            System.out.println(tg);

            for (int j = polygon.ypoints[i]-1; j >= polygon.ypoints[i + 1]; j--) {
                Xcurrent = (int) (polygon.xpoints[2] - tg * counter);
                for (int k = Xcurrent; k < Xmax; k++) {
                    g.setColor(Color.white);
                    g.drawLine(k, j, k, j);
                    if (pyxels[k - 50][j - 50] == 0) {
                        g.setColor(Color.black);
                        g.drawLine(k, j, k, j);
                        pyxels[k - 50][j - 50] = 1;
                    } else {
                        g.setColor(Color.WHITE);
                        g.drawLine(k, j, k, j);
                        pyxels[k - 50][j - 50] = 0;
                    }
                }
                counter++;
            }
        }
        for (int i = 3; i < 4; i++) {
            counter = 0;
            tg = (double) (polygon.xpoints[i] - polygon.xpoints[i + 1]) / (double) (polygon.ypoints[i] - polygon.ypoints[i + 1]);
            System.out.println(tg);

            for (int j = polygon.ypoints[i]; j < polygon.ypoints[i + 1]; j++) {
                Xcurrent = (int) (polygon.xpoints[3] + tg * counter);
                for (int k = Xmax-1; k >= Xcurrent; k--) {
                    g.setColor(Color.white);
                    g.drawLine(k, j, k, j);
                    if (pyxels[k - 50][j - 50] == 0) {
                        g.setColor(Color.black);
                        g.drawLine(k, j, k, j);
                        pyxels[k - 50][j - 50] = 1;
                    } else {
                        g.setColor(Color.WHITE);
                        g.drawLine(k, j, k, j);
                        pyxels[k - 50][j - 50] = 0;
                    }
                }
                counter++;
            }
        }
        for (int i = 4; i < 5; i++) {
            counter = 0;
            tg = (double) (polygon.xpoints[i] - polygon.xpoints[(i + 1)%5]) / (double) (polygon.ypoints[i] - polygon.ypoints[(i + 1)%5]);
            System.out.println(tg);

            for (int j = polygon.ypoints[i]-1; j >= polygon.ypoints[(i + 1)%5]; j--) {
                Xcurrent = (int) (polygon.xpoints[4] - tg * counter);
                System.out.println(Xcurrent);
                for (int k = Xmax-1; k >= Xcurrent; k--) {
                    g.setColor(Color.white);
                    g.drawLine(k, j, k, j);
                    if (pyxels[k - 50][j - 50] == 0) {
                        g.setColor(Color.black);
                        g.drawLine(k, j, k, j);
                        pyxels[k - 50][j - 50] = 1;
                    } else {
                        g.setColor(Color.WHITE);
                        g.drawLine(k, j, k, j);
                        pyxels[k - 50][j - 50] = 0;
                    }
                }
                counter++;
            }
        }

    }
       /*for (int i = 3; i < 4; i++) {
            counter = 0;
            tg = -(double) (polygon.xpoints[i] - polygon.xpoints[i+1]) / (double) (polygon.ypoints[i] - polygon.ypoints[i+1]);
            System.out.println(tg);
            for (int j = polygon.ypoints[i]; j > polygon.ypoints[i+1]; j--) {
                g.drawLine((int) (polygon.xpoints[i] + tg * counter), j, Xmax, j);
                counter++;
            }
        }*/



    public static void main(String[] args) {
        // Создаем окно с заголовком
        JFrame frame = new JFrame("Polygon Demo");
        // Устанавливаем размер окна
        frame.setSize(300, 300);
        // Устанавливаем действие при закрытии окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Добавляем панель с многоугольником в окно
        frame.add(new XOR());
        // Делаем окно видимым
        frame.setVisible(true);
    }
}
