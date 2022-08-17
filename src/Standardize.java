import java.util.concurrent.Semaphore;

public class Standardize extends Thread{

    static int lenText = 10;
    static int asciiMinValueUpper = 65; static int asciiMaxValueUpper = 90;
    static int asciiMinValueLower = 97; static int asciiMaxValueLower = 122;
    public int[] text;
    public int[] textReady;
    public Semaphore semaphore1;
    public Semaphore semaphore2;
    public Semaphore semaphore3;
    public Semaphore semaphore4;
    Standardize(int[] text, int[] textReady, Semaphore semaphore1, Semaphore semaphore2, Semaphore semaphore3, Semaphore semaphore4){
        this.text = text;
        this.textReady = textReady;
        this.semaphore1 = semaphore1;
        this.semaphore2 = semaphore2;
        this.semaphore3 = semaphore3;
        this.semaphore4 = semaphore4;
    }
    boolean lowerAlphabeticCharacter(int unicodeCharacter){
        return asciiMinValueLower <= unicodeCharacter && unicodeCharacter <= asciiMaxValueLower;
    }

    void standardizeText(){
        for (int i = 0; i < lenText; i++) {
            if(lowerAlphabeticCharacter(text[i])){
                textReady[i] = text[i] - 32;
            } else {
                textReady[i] = text[i];
            }

        }

    }

    public void run(){
        int operations = 0;
        while (operations<100){
            operations++;

            try {

                semaphore2.acquire();
                semaphore4.acquire();
                standardizeText();
                semaphore3.release();
                semaphore1.release();



            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }
    }
}
