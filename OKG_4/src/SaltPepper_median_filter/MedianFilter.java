package SaltPepper_median_filter;// Импортируем необходимые библиотеки
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;

// Создаем класс SaltPepper_median_filtration.MedianFilter
public class MedianFilter {

    // Создаем метод main, который принимает имя файла изображения в качестве аргумента
    public static void main(String[] args) throws IOException {

        // Получаем имя файла изображения из аргумента
        String fileName = "src/output.jpg";
        // Создаем объект BufferedImage для хранения изображения
        BufferedImage image = null;
        // Пытаемся прочитать изображение из файла

        image = ImageIO.read(new File(fileName));

        // Получаем ширину и высоту изображения в пикселях
        int width = image.getWidth();
        int height = image.getHeight();
        // Создаем новый объект BufferedImage для хранения измененного изображения
        BufferedImage filteredImage = new BufferedImage(width, height, image.getType());
        // Задаем размер окна фильтрации в пикселях
        int windowSize = 3;
        // Проходим по всем пикселям изображения, кроме краев
        for (int x = windowSize / 2; x < width - windowSize / 2; x++) {
            for (int y = windowSize / 2; y < height - windowSize / 2; y++) {
                // Создаем массивы для хранения значений красного, зеленого и синего цветов пикселей в окне
                int[] redValues = new int[windowSize * windowSize];
                int[] greenValues = new int[windowSize * windowSize];
                int[] blueValues = new int[windowSize * windowSize];
                // Проходим по всем пикселям в окне вокруг текущего пикселя
                for (int i = -windowSize / 2; i <= windowSize / 2; i++) {
                    for (int j = -windowSize / 2; j <= windowSize / 2; j++) {
                        // Получаем значение пикселя в формате RGB
                        int pixel = image.getRGB(x + i, y + j);
                        // Извлекаем значения красного, зеленого и синего цветов из RGB
                        int red = (pixel >> 16) & 0xff;
                        int green = (pixel >> 8) & 0xff;
                        int blue = pixel & 0xff;
                        // Добавляем значения цветов в соответствующие массивы
                        redValues[(i + windowSize / 2) * windowSize + (j + windowSize / 2)] = red;
                        greenValues[(i + windowSize / 2) * windowSize + (j + windowSize / 2)] = green;
                        blueValues[(i + windowSize / 2) * windowSize + (j + windowSize / 2)] = blue;
                    }
                }
                // Сортируем массивы значений цветов по возрастанию
                Arrays.sort(redValues);
                Arrays.sort(greenValues);
                Arrays.sort(blueValues);
                // Находим медиану значений цветов в массивах, то есть значение в середине отсортированного массива
                int medianRed = redValues[windowSize * windowSize / 2];
                int medianGreen = greenValues[windowSize * windowSize / 2];
                int medianBlue = blueValues[windowSize * windowSize / 2];
                // Собираем значение пикселя в формате RGB из медианных значений цветов
                int medianPixel = (medianRed << 16) | (medianGreen << 8) | medianBlue;
                // Устанавливаем значение пикселя в измененном изображении
                filteredImage.setRGB(x, y, medianPixel);
            }
        }
        // Пытаемся записать измененное изображение в файл с добавлением "_filtered" к имени
        ImageIO.write(filteredImage, "jpg", new File(fileName.substring(0, fileName.lastIndexOf(".")) + "_filtered.jpg"));

    }
}
