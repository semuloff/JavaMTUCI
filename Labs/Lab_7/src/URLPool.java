package Labs.Lab_7.src;

import java.util.LinkedList;
import java.util.List;

public class URLPool {
    LinkedList<URLDepthPair> rawLinks;
    LinkedList<URLDepthPair> processedLinks;

    private int maxDepth;

    URLPool(URLDepthPair initialPair, int maxDepth) {
        rawLinks = new LinkedList<>(List.of(initialPair));
        processedLinks = new LinkedList<>();
        this.maxDepth = maxDepth;
    }

    // Getting a pair from the pool.
    synchronized URLDepthPair get() {
        if (rawLinks.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException exception) {
                System.out.println("Error: " + exception.getMessage());

                return null;
            }
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
        }

        return isAdded;
    }

    // Output processed links with respect to depth.
    void getSites() {
        System.out.println("The result of the work:\n" + "-".repeat(50));
        for (URLDepthPair pair : processedLinks) {
            System.out.println(pair);
        }
    }
}
