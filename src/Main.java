import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args)  {
        int[] text = new int[10];
        int[] textReady = new int[10];
        Semaphore s1 = new Semaphore(1);
        Semaphore s2 = new Semaphore(1);
        Semaphore s3 = new Semaphore(0);

        Generator generator = new Generator(text, s1, s2);
        Standardize standardize = new Standardize(text, textReady, s1, s2, s3);
        Counter counter = new Counter(textReady, s2, s3);

        generator.start();
        standardize.start();
        counter.start();

        try {
            generator.join();
            standardize.join();
            counter.join();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }



    }
}
