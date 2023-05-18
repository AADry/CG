import java.awt.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Random rand = new Random();
        int f = rand.nextInt(3,6);
        for (int i = 0; i < 10; i++) {
            System.out.println(new Random().nextInt(3,6));
        }
    }
}