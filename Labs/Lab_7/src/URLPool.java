package Labs.Lab_7.src;

import java.util.LinkedList;
import java.util.List;

public class URLPool {
    LinkedList<URLDepthPair> rawLinks;
    LinkedList<URLDepthPair> processedLinks;

    private int waitingThreads;

    private int maxDepth;

    URLPool(URLDepthPair initialPair, int maxDepth) {
        rawLinks = new LinkedList<>(List.of(initialPair));
        processedLinks = new LinkedList<>();
        this.maxDepth = maxDepth;
        waitingThreads = 0;
    }

    // Getting a pair from the pool.
    synchronized URLDepthPair get() {
        if (rawLinks.size() == 0) {
            waitingThreads++;
        }

        URLDepthPair resultingPair = rawLinks.removeFirst();
        processedLinks.add(resultingPair);

        return resultingPair;
    }

    // Adding a pair to the pool.
    synchronized boolean put(URLDepthPair pair) {
        boolean isAdded = false;

        if (pair.getDepth() < maxDepth) {
            rawLinks.addLast(pair);
            isAdded = true;
        } else {
            processedLinks.addLast(pair);
        }

        return isAdded;
    }

    synchronized int getWaitingThreads() {
        return waitingThreads;
    }

    synchronized int getSize() {
        return rawLinks.size();
    }

    // Output processed links with respect to depth.
    synchronized void getSites() {
        System.out.println("The result of the work:\n" + "-".repeat(50));
        for (URLDepthPair pair : processedLinks) {
            System.out.println(pair);
        }
    }
}
