package Labs.Lab_7.src;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class URLPool {
    LinkedList<URLDepthPair> rawLinks;
    LinkedList<URLDepthPair> processedLinks;

    private int maxDepth;

    URLPool(URLDepthPair initialPair, int maxDepth) {
        this.maxDepth = maxDepth;
        rawLinks = new LinkedList<>(List.of(initialPair));
        processedLinks = new LinkedList<>();
    }

    // Getting a pair from the pool.
    synchronized URLDepthPair get() {
        if (rawLinks.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException exception) {
                System.out.println("[Thread wait]: " + exception.getMessage());
            }
        }

        URLDepthPair resultingPair = null;
        try {
            resultingPair = rawLinks.removeFirst();
            processedLinks.add(resultingPair);
        } catch (NoSuchElementException exception) {
            System.out.println("[Removing from an empty list]: " + exception.getMessage());
        }

        return resultingPair;
    }

    // Adding a pair to the pool.
    synchronized void put(URLDepthPair pair) {
        if (pair.getDepth() < maxDepth) {
            rawLinks.addLast(pair);
            this.notify();
        } else {
            processedLinks.addLast(pair);
        }
    }

    synchronized int getSize() {
        return rawLinks.size();
    }

    // Output processed URLs with respect to depth.
    synchronized void getSites() {
        System.out.println("The result of the work:\n" + "-".repeat(50));
        for (URLDepthPair pair : processedLinks) {
            System.out.println(pair);
        }
    }
}
