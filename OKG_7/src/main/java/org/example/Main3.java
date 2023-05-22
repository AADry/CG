package org.example;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import java.util.ArrayList;
import java.util.List;

public class Main3 {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat image = Imgcodecs.imread("figures.jpg");

        Mat grayImage = new Mat();
        Imgproc.cvtColor(image, grayImage, Imgproc.COLOR_BGR2GRAY);

        Mat blurredImage = new Mat();
        Imgproc.GaussianBlur(grayImage, blurredImage, new Size(5, 5), 0);

        Mat edges = new Mat();
        Imgproc.Canny(blurredImage, edges, 50, 150);

        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        List<MatOfPoint> triangles = new ArrayList<>();
        int counter = 0;
        for (MatOfPoint contour : contours) {
            MatOfPoint2f approxCurve = new MatOfPoint2f();
            MatOfPoint2f contour2f = new MatOfPoint2f(contour.toArray());
            double contourLength = Imgproc.arcLength(contour2f, true);
            Imgproc.approxPolyDP(contour2f, approxCurve, 0.02 * contourLength, true);
            if (approxCurve.total() == 3 && Imgproc.isContourConvex(new MatOfPoint(approxCurve.toArray()))) {
                double[] cosines = new double[3];
                Point[] points = approxCurve.toArray();
                for (int i = 0; i < 3; i++) {
                    cosines[i] = calculateAngle(points[(i + 1) % 3], points[i], points[(i + 2) % 3]);
                }
                if (cosines[0] > 0.5 && cosines[1] > 0.5 && cosines[2] > 0.5) {
                    triangles.add(contour);
                }
            }
        }

        Imgproc.drawContours(image, triangles, -1, new Scalar(0, 0, 255), 6);

        Imgcodecs.imwrite("output3.jpg", image);
    }

    private static double calculateAngle(Point p1, Point p2, Point p3) {
        double dx1 = p1.x - p2.x;
        double dy1 = p1.y - p2.y;
        double dx2 = p3.x - p2.x;
        double dy2 = p3.y - p2.y;
        double dotProduct = dx1 * dx2 + dy1 * dy2;
        double length1 = Math.sqrt(dx1 * dx1 + dy1 * dy1);
        double length2 = Math.sqrt(dx2 * dx2 + dy2 * dy2);
        double angle = Math.acos(dotProduct / (length1 * length2));
        return angle * 180 / Math.PI;
    }
}