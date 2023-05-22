package BitCoding;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.BufferOverflowException;
import java.util.Scanner;

public class RLE {
    public static void main(String[] args) throws IOException {
        PrintWriter writer = new PrintWriter("src/BitCoding/decode", "UTF-8");


        BufferedImage in = ImageIO.read(new File("src/BitCoding/15454.jpg"));
        // Создаем изображение
        BufferedImage image = new BufferedImage( in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_RGB);
         BufferedImage newImage = new BufferedImage( in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_RGB);

        int[][] decode = new int[in.getHeight()][in.getWidth()];

        int[][] compress = new int[in.getHeight()][in.getWidth()+200];

        for (int i = 0; i < in.getHeight(); i++) {
            for (int j = 0; j < in.getHeight(); j++) {
                image.setRGB(i, j, in.getRGB(i, j));
            }
        }
        System.out.println(in.getWidth());
        System.out.println(in.getHeight());

        int color;
        for (int i = 0; i < in.getHeight(); i++) {
            for (int j = 0; j < in.getWidth(); j++) {
                color = image.getRGB(i, j);
                decode[i][j] = color;
            }
        }


        int color1;
        int column;
        for (int i = 0; i < in.getHeight(); i++) {
            color1 = image.getRGB(i, 0);
            column = 1;
            for (int j = 0; j < in.getWidth(); j++) {
                if (image.getRGB(i, j) != color1) {
                    compress[i][column] = color1;
                    color1 = image.getRGB(i, j);
                    column += 2;
                }
                compress[i][column - 1] += 1;
            }
            compress[i][column] = color1;
        }


        //декодирование
        for (int i = 0; i < in.getHeight(); i++) {
            for (int j = 0; j < in.getWidth(); j++) {
                newImage.setRGB(i, j, decode[i][j]);
            }
        }

        //
        for (int i = 0; i < compress.length; i++) {
            for (int j = 0; j < compress[0].length; j++) {
                if (compress[i][j] == 0) {
                    break;
                }
                writer.print(compress[i][j] + " ");
            }
            writer.print(System.getProperty("line.separator"));
        }
        writer.close();

        //отображение
        JFrame frame = new JFrame("Image");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        // Создаем метку с иконкой из изображения
        JLabel label = new JLabel(new ImageIcon(newImage));
        // Добавляем метку к окну
        frame.add(label);
        // Показываем окно
        frame.setVisible(true);

    }
}
