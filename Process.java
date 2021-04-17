public class Process implements Runnable {

    private Barrier barrier;
    private String name;
    private int sleepTime;
    private int pos;

    public Process(Barrier b, int n, int s) {
        barrier = b;
        name = "Thread-" + n;
        sleepTime = s;
    }

    public String getName() {
        return name;
    }

    /* Set position of Process in the queue */
    void setPos(int pos) {
        this.pos = pos;
    }

    /* Get position of Process in the queue */
    int getPos() {
        return pos;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(sleepTime);
            barrier.joinBarrier(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
