package Labs.Lab_7.src;

import java.io.IOException;
import java.util.HashSet;

public class CrawlerTask implements Runnable {
    private static URLPool mainPull;

    private URLDepthPair currentPair;

    CrawlerTask(URLPool pull) {
        mainPull = pull;
    }

    @Override
    public void run() {
        while (true) {
            if (mainPull.getSize() == 0) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            currentPair = mainPull.get();

            try {
                HashSet<String> URLs = Crawler.scan(currentPair);
                if (URLs != null) {
                    for (String URL : URLs) {
                        mainPull.put(new URLDepthPair(URL, currentPair.getDepth() + 1));
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
