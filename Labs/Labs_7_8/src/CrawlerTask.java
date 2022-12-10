package Labs.Labs_7_8.src;

import java.io.IOException;
import java.net.MalformedURLException;

import java.util.HashSet;

public class CrawlerTask implements Runnable {
    private static URLPool mainPool;
    private URLDepthPair currentPair;

    CrawlerTask(URLPool pull) {
        mainPool = pull;
    }

    @Override
    public void run() {
        while (true) {
            currentPair = mainPool.get();

            try {
                HashSet<String> URLs = null;

                if (currentPair != null) {
                    URLs = Crawler.scan(currentPair);
                }

                if (URLs != null) {
                    for (String URL : URLs) {
                        try {
                            mainPool.put(new URLDepthPair(URL, currentPair.getDepth() + 1));
                        } catch (MalformedURLException exception) {
                            System.out.println("[Error while creating pairs]: " + exception.getMessage());
                        }
                    }
                }
            } catch (IOException exception) {
                System.out.println("[Error while scanning]: " + exception.getMessage());
            }
        }
    }
}
