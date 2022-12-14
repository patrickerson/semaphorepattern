import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args)  {
        int[] text = new int[10];
        int[] textReady = new int[10];
        Semaphore s1 = new Semaphore(1);
        Semaphore s2 = new Semaphore(0);
        Semaphore s3 = new Semaphore(0);
        Semaphore s4 = new Semaphore(1);

        int maxOperation = 100;

        Generator generator = new Generator(text, s1, s2, maxOperation);
        Standardize standardize = new Standardize(text, textReady, s1, s2, s3, s4, maxOperation);
        Counter counter = new Counter(textReady, s4, s3, maxOperation);

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


        System.out.println("FIM DO PROGRAMA");


    }
}
