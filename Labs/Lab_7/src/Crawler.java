package Labs.Lab_7.src;

import java.io.*;
import java.net.*;
import java.util.HashSet;
import java.util.LinkedList;

public class Crawler {
    static final int PORT = 80;
    static final int TIMEOUT = 5000;

    static int depth;
    static int countThreads;
    static Thread[] threads;
    static URLPool pool;
    static String URL;

    static HashSet<String> scan(URLDepthPair page) throws IOException {
        Socket socket = new Socket(page.getHost(), PORT);
        socket.setSoTimeout(TIMEOUT);

        PrintWriter post = new PrintWriter(socket.getOutputStream(), true);
        requestForm(post, page);

        BufferedReader get = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // URL parsing.
        String partPageCode;
        HashSet<String> allURL = new HashSet<>();
        LinkedList<String> urlIsInLine;

        while ((partPageCode = get.readLine()) != null) {
            urlIsInLine = URLDepthPair.urlDetermination(partPageCode);
            if (urlIsInLine != null) {
                for (String URL : urlIsInLine) {
                    allURL.add(URL);
                }
            }
        }

        socket.close();
        post.close();
        get.close();

        return (allURL.size() > 0) ? allURL : null;
    }

    // HTTP request form.
    private static void requestForm(PrintWriter post, URLDepthPair page) {
        post.println("GET " + page.getPath() + " HTTP/1.1");
        post.println("Host: " + page.getHost());
        post.println("Connection: close");
        post.println();
    }

    public static void main(String[] args) throws IOException {
        /**
         * "http://" - format.
         * for example:
         *  http://www.all-met.narod.ru/
         *  http://www.rgrafika.ru/
         *  http://economicus.ru/site/grebenikov/E_Micro/index.html
         **/
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter the depth >> ");
        try {
            depth = Integer.parseInt(reader.readLine());
            if (depth < 1)
                throw new NumberFormatException("[Incorrect depth] - entered depth < 1");
        } catch (NumberFormatException exception) {
            throw new NumberFormatException("[Incorrect depth] - usage: java Crawler <URL> <depth> <threads>");
        }

        System.out.print("Enter the URL >> ");
        try {
            URL = reader.readLine();
            pool = new URLPool(new URLDepthPair<>(URL, 0), depth);
        } catch (MalformedURLException exception) {
            throw new MalformedURLException("[Incorrect URL] - usage: java Crawler <URL> <depth> <threads>");
        }

        System.out.print("Enter the number of threads >> ");
        try {
            countThreads = Integer.parseInt(reader.readLine());
            if (countThreads < 1)
                throw new NumberFormatException("[Incorrect count of thread] - entered thread < 1");
        } catch (NumberFormatException exception) {
            throw new NumberFormatException("[Incorrect threads] - usage: java Crawler <URL> <depth> <threads>");
        }

        System.out.println("\nWorks...\n");

        threads = new Thread[countThreads];

        for (int indexThread = 0; indexThread < countThreads; indexThread++) {
            CrawlerTask crawler = new CrawlerTask(pool);
            Thread thread = new Thread(crawler);
            thread.start();
            threads[indexThread] = thread;
        }

        ThreadController controller = new ThreadController(threads);
        Thread threadController = new Thread(controller);
        threadController.start();

        while (ThreadController.countWaitingThreads != countThreads) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        pool.getSites();
    }
}