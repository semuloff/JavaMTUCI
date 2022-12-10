package Labs.Labs_7_8.src;

public class ThreadController implements Runnable {
    private Thread[] threads;

    private int countThreads;
    static int countWaitingThreads;

    ThreadController(Thread[] threads) {
        this.threads = threads;
        countThreads = threads.length;
    }

    @Override
    public void run() {
        while (true) {
            countWaitingThreads = 0;

            for (int threadID = 0; threadID < countThreads; threadID++) {
                countWaitingThreads += threads[threadID].getState() == Thread.State.WAITING ? 1 : 0;
            }

            if (countWaitingThreads == countThreads) {
                interruptThreads();
                break;
            }
        }
    }

    // Shutting down child threads.
    private void interruptThreads() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}
