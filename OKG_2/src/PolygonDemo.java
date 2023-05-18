import org.w3c.dom.ls.LSOutput;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;
import javax.swing.*;

public class PolygonDemo extends JPanel {

    // Создаем многоугольник с четырьмя вершинами
    Random rand = new Random();
    int nodes = rand.nextInt(3,5);

    Polygon polygon = new Polygon(new int[] {50, 60, 100, 110, new Random().nextInt(75,90)}, new int[] {50, 100, 110, 60, new Random().nextInt(30,90)}, 5);

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
        g.setColor(Color.BLUE);
        int counter = 1;
        // инициализация
        int[][] Edges = new int[polygon.npoints][4];
        for (int i = 0; i < polygon.npoints; i++) {
            Edges[i][0] = polygon.xpoints[i];
            Edges[i][1] = polygon.ypoints[i];
            Edges[i][2] = polygon.xpoints[(i+1) % polygon.npoints];
            Edges[i][3] = polygon.ypoints[(i+1) % polygon.npoints];
        }

        // массив активных ребер
        double[][] activeEdges = new double[polygon.npoints][3];


        // начальная точка меньше конечной
        for (int i = 0; i < polygon.npoints; i++) {
            if (Edges[i][1] > Edges[i][3]) {
                int swapX = Edges[i][0];
                int swapY = Edges[i][1];
                Edges[i][0] = Edges[i][2];
                Edges[i][1] = Edges[i][3];
                Edges[i][2] = swapX;
                Edges[i][3] = swapY;

            }
        }
        // сортировка
        Arrays.sort(Edges, new java.util.Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[1], b[1]);
            }
        });

        //определение пределов заполнения Ymin и Ymax
        int Ymin = Edges[0][1];
        int Ymax = Edges[polygon.npoints-1][1];

        int Ycurrent = Ymin;

        int[] asd = Arrays.copyOf(polygon.ypoints,polygon.npoints);
        Arrays.sort(asd);
        int Ynext;
        while (true) {
            for (int i = 0; i < polygon.npoints; i++) {
                if (Edges[i][1] == Ycurrent) {
                    if (activeEdges[i][0] == 0.0 && activeEdges[i][2] == 0.0) {
                        //макс y
                        activeEdges[i][0] = Edges[i][3];
                        //приращение
                        activeEdges[i][1] = (double)(Edges[i][2] - Edges[i][0])/(double)(Edges[i][3] - Edges[i][1]) ;
                        //начзнач X
                        activeEdges[i][2] = Edges[i][0];
                    }
                }
            }

            Arrays.sort(activeEdges, new java.util.Comparator<double[]>() {
                public int compare(double[] a, double[] b) {
                    return Double.compare(b[2], a[2]);
                }
            });
            for (int i = 0; i < polygon.npoints; i++) {
                System.out.println(activeEdges[i][0] + " " + activeEdges[i][1] + " " + activeEdges[i][2]);
            }
            System.out.println();
            Ynext = asd[counter];
            counter += 1;

            /*for (int i = 0; i < 4; i++) {
                System.out.println(activeEdges[i][0] + " " + activeEdges[i][1] + " " + activeEdges[i][2]);
            }*/
            for (int i = Ycurrent; i < Ynext; i++) {
                for (int j = 0; j < activeEdges.length; j++) {
                    if ((activeEdges[j][0] != 0.0 || activeEdges[j][2] != 0.0) && j % 2 == 0) {

                        g.drawLine((int) activeEdges[j][2], i, (int) activeEdges[j+1][2], i);
                        activeEdges[j][2] += activeEdges[j][1];
                        activeEdges[j+1][2] += activeEdges[j+1][1];
                    }
                }
            }

            if (Ycurrent == Ymax) {
                break;
            } else {
                for (int i = 0; i < polygon.npoints; i++) {
                    if (activeEdges[i][0] == Ynext) {
                        activeEdges[i][0] = 0;
                        activeEdges[i][1] = 0;
                        activeEdges[i][2] = 0;
                    }
                }
                Arrays.sort(activeEdges, new java.util.Comparator<double[]>() {
                    public int compare(double[] a, double[] b) {
                        return Double.compare(b[2], a[2]);
                    }
                });
                Ycurrent = Ynext;

            }
        }
    }



    public static void main(String[] args) {
        // Создаем окно с заголовком
        JFrame frame = new JFrame("Polygon Demo");
        // Устанавливаем размер окна
        frame.setSize(300, 300);
        // Устанавливаем действие при закрытии окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Добавляем панель с многоугольником в окно
        frame.add(new PolygonDemo());
        // Делаем окно видимым
        frame.setVisible(true);
    }

}