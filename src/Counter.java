import java.util.concurrent.Semaphore;

public class Counter extends Thread {

    static int[] vocal = {65, 69, 73, 79, 85};
    int[] word;
    public Semaphore semaphore2;
    public Semaphore semaphore3;
    Counter(int[] word, Semaphore semaphore2, Semaphore semaphore3){
        this.word = word;
        this.semaphore2 = semaphore2;
        this.semaphore3 = semaphore3;
    }
    boolean isVocal(int character){
        for (int i:
                vocal) {
            if (i==character){
                return true;
            }
        }
        return false;
    }

    int counter(){
        int count = 0;
        for (int character:
             word) {
            if(isVocal(character)){
                count+=1;
            }
        }
        return count;
    }

    void print_result(){
        for (int character:
             word) {
            System.out.print((char) character);
        }
        System.out.println(": " + counter());
    }

    public void run(){
        int operations = 0;
        while (true){
            operations++;

            try {
                semaphore3.acquire();
                print_result();
                semaphore2.release();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }
    }
}
