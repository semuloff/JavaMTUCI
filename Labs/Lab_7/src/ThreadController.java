package Labs.Lab_7.src;

public class ThreadController implements Runnable {
    private Thread[] threads;
    private URLPool pool = Crawler.pool;

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

            System.out.println("Count waiting threads: " + countWaitingThreads);

            if (countWaitingThreads == countThreads) {
                interruptThreads();
                break;
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Shutting down child threads.
    private void interruptThreads() {
        for (int threadID = 0; threadID < countThreads; threadID++) {
            threads[threadID].interrupt();
        }
    }
}
