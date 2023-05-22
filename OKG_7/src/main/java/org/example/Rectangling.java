package org.example;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class Rectangling {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat image = Imgcodecs.imread("rect.jpg");

        Mat grayImage = new Mat();
        Imgproc.cvtColor(image, grayImage, Imgproc.COLOR_BGR2GRAY);

        Mat binaryImage = new Mat();
        Imgproc.threshold(grayImage, binaryImage, 0, 255, Imgproc.THRESH_BINARY_INV + Imgproc.THRESH_OTSU);

        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(binaryImage, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        for (MatOfPoint contour : contours) {
            MatOfPoint2f contour2f = new MatOfPoint2f(contour.toArray());
            double approxDistance = Imgproc.arcLength(contour2f, true) * 0.03;
            MatOfPoint2f approxCurve = new MatOfPoint2f();
            Imgproc.approxPolyDP(contour2f, approxCurve, approxDistance, true);

            if (approxCurve.total() == 4) {
                Rect rect = Imgproc.boundingRect(contour);
                Imgproc.rectangle(image, rect.tl(), rect.br(), new Scalar(255, 0, 255), 3);
            }
        }

        Imgcodecs.imwrite("output2.jpg", image);
    }
}