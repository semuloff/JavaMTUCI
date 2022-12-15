package Labs.Lab_7.src;

import java.util.LinkedList;
import java.util.List;

public class URLPool {
    private LinkedList<URLDepthPair> rawLinks;
    private LinkedList<URLDepthPair> processedLinks;

    private int maxDepth;

    URLPool(URLDepthPair initialPair, int maxDepth) {
        this.maxDepth = maxDepth;
        rawLinks = new LinkedList<>(List.of(initialPair));
        processedLinks = new LinkedList<>();
    }

    // Getting a pair from the pool.
    synchronized URLDepthPair get() throws InterruptedException {
        if (rawLinks.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException exception) {
                throw new InterruptedException("[Thread completed.]");
            }
        }

        URLDepthPair resultingPair = null;

        if (rawLinks.size() != 0) {
            resultingPair = rawLinks.removeFirst();
            processedLinks.add(resultingPair);
        }

        return resultingPair;
    }

    // Adding a pair to the pool.
    synchronized void put(URLDepthPair pair) {
        if (pair.getDepth() < maxDepth) {
            boolean flag = true;

            for (URLDepthPair element : processedLinks) {
                if ((pair.getURL().equals(element.getURL()))) {
                    flag = false;
                }
            }

            if (flag) {
                rawLinks.addLast(pair);
            }

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
        System.out.println("\nThe result of the work:\n" + "-".repeat(50));
        for (URLDepthPair pair : processedLinks) {
            System.out.println(pair);
        }
    }
}
