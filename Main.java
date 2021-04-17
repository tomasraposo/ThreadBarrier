import java.util.Random;
import java.util.ArrayList;

/**
 * Lab 9: Thread Barrier
 * 
 * Main class
 * 
 */
public class Main {
    private static int threadCount;
    private static int barrierSize;
    private static int sleepTime;

    public static void main(String[] args) {
        if (args.length == 3) {
            try {
                threadCount = Integer.parseInt(args[0]);
                sleepTime = Integer.parseInt(args[1]);
                barrierSize = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Usage: java Main [threadCount] [sleepTime] [barrierSize]");
            System.exit(1);
        }
        String info = new String(
                "Thread count = " + threadCount + "\nSleep time = " + sleepTime + "\nBarrier size = " + barrierSize);
        System.out.println(info);

        Random r = new Random();
        Barrier barrier = new Barrier(barrierSize);

        for (int i = 0; i < threadCount; i++) {
            new Thread(new Process(barrier, i, r.nextInt(sleepTime))).start();
        }
    }
}
