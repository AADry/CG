package SaltPepper_median_filter;// Импортируем необходимые библиотеки
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

// Создаем класс SaltPepper_median_filtration.SaltPepperNoise
public class SaltPepperNoise {

    // Создаем метод main, который принимает имя файла изображения в качестве аргумента
    public static void main(String[] args) throws IOException {
        // Проверяем, что аргумент задан

        // Создаем объект BufferedImage для хранения изображения
        BufferedImage image = null;
        String fileName = "src/image.bmp";
        // Пытаемся прочитать изображение из файла
        image = ImageIO.read(new File("src/zakat.jpg"));

        // Получаем ширину и высоту изображения в пикселях
        int width = image.getWidth();
        int height = image.getHeight();
        // Создаем объект Random для генерации случайных чисел
        Random random = new Random();
        // Задаем вероятность появления шума соль-перец в процентах
        int noiseProbability = 10;
        // Проходим по всем пикселям изображения
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // Генерируем случайное число от 0 до 99
                int randomNumber = random.nextInt(100);
                // Если число меньше вероятности шума, то добавляем шум соль-перец
                if (randomNumber < noiseProbability) {
                    // Генерируем случайное число от 0 до 1
                    int saltOrPepper = random.nextInt(2);
                    // Если число равно 0, то добавляем белый пиксель (соль)
                    if (saltOrPepper == 0) {
                        // Задаем значение пикселя в формате RGB как 255, 255, 255
                        int whitePixel = (255 << 16) | (255 << 8) | 255;
                        // Устанавливаем значение пикселя в изображении
                        image.setRGB(x, y, whitePixel);
                    }
                    // Если число равно 1, то добавляем черный пиксель (перец)
                    else {
                        // Задаем значение пикселя в формате RGB как 0, 0, 0
                        int blackPixel = (0 << 16) | (0 << 8) | 0;
                        // Устанавливаем значение пикселя в изображении
                        image.setRGB(x, y, blackPixel);
                    }
                }
            }
        }
        // Пытаемся записать измененное изображение в файл с добавлением "_noise" к имени
        ImageIO.write(image, "jpg", new File(fileName.substring(0, fileName.lastIndexOf(".")) + "_noise.jpg"));

    }
}
