package org.example;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Circling {
    public static void main(String[] args) {
        //Загружаем openCV библиотеки
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //Загрузка изображение, как параметр передаём путь к нему
        Mat image = Imgcodecs.imread("balls1.jpg");
        //Делаем изображение не цветным
        Mat grayImage = new Mat();
        Imgproc.cvtColor(image, grayImage, Imgproc.COLOR_BGR2GRAY);
        //Блюрим изображение(этот шаг и предыдущий нужен для того, чтобы лучше распозновать окружности на изображении и всякий шум не мешал этому
        Mat blurredImage = new Mat();
        Imgproc.GaussianBlur(grayImage, blurredImage, new Size(9, 9), 2, 2);
        //Обнаруживаем круги на изображении. Вот там где в параметре указано 100 и 50. Чем эти значения меньше,
        // тем больше вероятность, что будет найден "неправильный круг". Если нужно, чтоб находились идеальные
        //окружности, можешь поставить их 200 и 100 соответственно
        Mat circles = new Mat();
        Imgproc.HoughCircles(blurredImage, circles, Imgproc.HOUGH_GRADIENT, 1, blurredImage.rows() / 8.0, 150, 50, 5, 0);
        //Выделяем на изображении найденные окружности
        for (int i = 0; i < circles.cols(); i++) {
            double[] circle = circles.get(0, i);
            Point center = new Point(Math.round(circle[0]), Math.round(circle[1]));
            int radius = (int) Math.round(circle[2]);
            Imgproc.circle(image, center, radius, new Scalar(0, 0, 255), 3);
        }
        //Сохраняем изображение с найденными окружностями
        Imgcodecs.imwrite("result.jpg", image);
    }
}