package SaltPepper_Wiener_filter;

// Импортируем необходимые библиотеки
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// Создаем класс WienerFilter
public class WienerFilter {

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
        // Задаем искажающую функцию H(u,v) в частотной области
        // В этом примере используется функция Гаусса с параметром сигма = 0.01
        double sigma = 0.01;
        double[][] H = new double[width][height];
        for (int u = 0; u < width; u++) {
            for (int v = 0; v < height; v++) {
                H[u][v] = Math.exp(-sigma * ((u - width / 2) * (u - width / 2) + (v - height / 2) * (v - height / 2)));
            }
        }
        // Задаем константу К для аппроксимации спектров шума и неискаженного изображения
        // В этом примере используется значение К = 0.01
        double K = 0.01;
        // Проходим по всем пикселям изображения в частотной области
        for (int u = 0; u < width; u++) {
            for (int v = 0; v < height; v++) {
                // Получаем значение пикселя в формате RGB
                int pixel = image.getRGB(u, v);
                // Извлекаем значения красного, зеленого и синего цветов из RGB
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = pixel & 0xff;
                // Преобразуем значения цветов в комплексные числа
                Complex redComplex = new Complex(red, 0);
                Complex greenComplex = new Complex(green, 0);
                Complex blueComplex = new Complex(blue, 0);
                // Применяем винеровский фильтр к каждому цвету отдельно
                // Фильтр задается формулой F(u,v) = G(u,v) * H*(u,v) / (|H(u,v)|^2 + K)
                // Где F(u,v) - оценка неискаженного изображения в частотной области
                // G(u,v) - искаженное изображение в частотной области
                // H(u,v) - искажающая функция в частотной области
                // H*(u,v) - комплексно-сопряженная искажающая функция в частотной области
                // |H(u,v)|^2 - квадрат модуля искажающей функции в частотной области
                // K - константа для аппроксимации спектров шума и неискаженного изображения
                Complex redFiltered = redComplex.times(new Complex(H[u][v], 0)).divides(new Complex(H[u][v] * H[u][v] + K, 0));
                Complex greenFiltered = greenComplex.times(new Complex(H[u][v], 0)).divides(new Complex(H[u][v] * H[u][v] + K, 0));
                Complex blueFiltered = blueComplex.times(new Complex(H[u][v], 0)).divides(new Complex(H[u][v] * H[u][v] + K, 0));
                // Округляем значения цветов до целых чисел
                int redInt = (int) Math.round(redFiltered.re());
                int greenInt = (int) Math.round(greenFiltered.re());
                int blueInt = (int) Math.round(blueFiltered.re());
                // Обрезаем значения цветов до диапазона [0, 255]
                redInt = Math.max(0, Math.min(255, redInt));
                greenInt = Math.max(0, Math.min(255, greenInt));
                blueInt = Math.max(0, Math.min(255, blueInt));
                // Собираем значение пикселя в формате RGB из значений цветов
                int filteredPixel = (redInt << 16) | (greenInt << 8) | blueInt;
                // Устанавливаем значение пикселя в измененном изображении
                filteredImage.setRGB(u, v, filteredPixel);
            }
        }
        // Пытаемся записать измененное изображение в файл с добавлением "_filtered" к имени
        ImageIO.write(filteredImage, "jpg", new File(fileName.substring(0, fileName.lastIndexOf(".")) + "_filtered.jpg"));

    }
}
