package SaltPepper_Wiener_filter;

// Создаем класс Complex для работы с комплексными числами
public class Complex {

    // Объявляем поля для хранения действительной и мнимой частей комплексного числа
    private double re; // действительная часть
    private double im; // мнимая часть

    // Создаем конструктор, который принимает действительную и мнимую части комплексного числа
    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    // Создаем метод для получения действительной части комплексного числа
    public double re() {
        return re;
    }

    // Создаем метод для получения мнимой части комплексного числа
    public double im() {
        return im;
    }

    // Создаем метод для сложения двух комплексных чисел
    public Complex plus(Complex b) {
        // Складываем действительные и мнимые части по отдельности
        double real = this.re + b.re;
        double imag = this.im + b.im;
        // Возвращаем новый объект Complex с результатом сложения
        return new Complex(real, imag);
    }

    // Создаем метод для вычитания двух комплексных чисел
    public Complex minus(Complex b) {
        // Вычитаем действительные и мнимые части по отдельности
        double real = this.re - b.re;
        double imag = this.im - b.im;
        // Возвращаем новый объект Complex с результатом вычитания
        return new Complex(real, imag);
    }

    // Создаем метод для умножения двух комплексных чисел
    public Complex times(Complex b) {
        // Умножаем действительные и мнимые части по формуле (a + bi) * (c + di) = (ac - bd) + (ad + bc)i
        double real = this.re * b.re - this.im * b.im;
        double imag = this.re * b.im + this.im * b.re;
        // Возвращаем новый объект Complex с результатом умножения
        return new Complex(real, imag);
    }

    // Создаем метод для деления двух комплексных чисел
    public Complex divides(Complex b) {
        // Делим действительные и мнимые части по формуле (a + bi) / (c + di) = (ac + bd) / (c^2 + d^2) + (bc - ad) / (c^2 + d^2)i
        double real = (this.re * b.re + this.im * b.im) / (b.re * b.re + b.im * b.im);
        double imag = (this.im * b.re - this.re * b.im) / (b.re * b.re + b.im * b.im);
        // Возвращаем новый объект Complex с результатом деления
        return new Complex(real, imag);
    }

    // Создаем метод для получения комплексно-сопряженного числа
    public Complex conjugate() {
        // Меняем знак мнимой части на противоположный
        return new Complex(re, -im);
    }

}
