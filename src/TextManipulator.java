import java.util.concurrent.Semaphore;

public class TextManipulator extends Thread{

    static int lenText = 10;
    static int asciiMinValueUpper = 65; static int asciiMaxValueUpper = 90;
    static int asciiMinValueLower = 97; static int asciiMaxValueLower = 122;
    public int[] text;
    public int[] textReady;
    public Semaphore semaphore1;
    public Semaphore semaphore2;
    int operations;

    TextManipulator(int[] text, Semaphore semaphore1,Semaphore semaphore2){
        this.text = text;
        this.semaphore1 = semaphore1;
        this.semaphore2 = semaphore2;
    }

    boolean lowerAlphabeticCharacter(int unicodeCharacter){
        return asciiMinValueLower <= unicodeCharacter && unicodeCharacter <= asciiMaxValueLower;
    }

}
