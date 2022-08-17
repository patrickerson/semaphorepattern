import java.util.Random;
import java.util.concurrent.Semaphore;

public class Generator extends Thread {
    static int lenText = 10;
    static int asciiMinValueUpper = 65; static int asciiMaxValueUpper = 90;
    static int asciiMinValueLower = 97; static int asciiMaxValueLower = 122;
    public int[] text;
    public Semaphore semaphore1;
    public Semaphore semaphore2;
    int maxOperations;
    Generator(int[] text, Semaphore semaphore1,Semaphore semaphore2, int maxOperations ){
        this.text = text;
        this.semaphore1 = semaphore1;
        this.semaphore2 = semaphore2;
        this.maxOperations = maxOperations;
    }
    void createString(){
        Random r = new Random();
        int count = 0;
        while (count < lenText) {
            int randomLimitedInt = r.nextInt(asciiMaxValueLower - asciiMinValueUpper) + asciiMinValueUpper;

            if (validAlphabeticCharacter(randomLimitedInt)){
                text[count] = randomLimitedInt;
                count++;
            }
        }
    }

    boolean validAlphabeticCharacter(int unicodeCharacter){
        return upperAlphabeticCharacter(unicodeCharacter) || lowerAlphabeticCharacter(unicodeCharacter);
    }
    boolean upperAlphabeticCharacter(int unicodeCharacter){
        return asciiMinValueLower <= unicodeCharacter && unicodeCharacter <= asciiMaxValueLower;
    }

    boolean lowerAlphabeticCharacter(int unicodeCharacter){
        return asciiMinValueUpper <= unicodeCharacter && unicodeCharacter <= asciiMaxValueUpper;
    }


    public void run(){
        int operations = 0;
        while (operations<maxOperations){

            try {
                semaphore1.acquire();
                createString();
                semaphore2.release();
                operations++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }



        }
    }

}
