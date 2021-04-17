
/**
 * Thread Barrier Class Processes join the barrier and are held until
 * barrierSize processes have joined.
 */
public class Barrier {

    /* Size of the barrier, which is the minimum number of processes to proceed */
    private int barrierSize;
    private int count;
    private int pos;

    public Barrier(int size) {
        barrierSize = size;
    }

    /* Processes join at barrier and either wait or are allowed past */
    public synchronized void joinBarrier(Process p) throws InterruptedException {
        System.out.println("\u001B[34m" + p.getName() + "\twaiting on barrier" + "\u001B[0m");

        p.setPos(count);

        count++;
        notifyAll();

        while (count <= barrierSize) {
            // wait for barrier to fill
            wait();
        }
        while (pos != p.getPos()) {
            // wait for turn in queue
            wait();
        }
        pos++;

        System.out.println("\u001B[33m" + p.getName() + "\tpassed the barrier" + "\u001B[0m");
        notifyAll();
    }
}
