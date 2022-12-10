package Labs.Lab_7.src;

import java.io.*;
import java.net.*;
import java.util.HashSet;
import java.util.LinkedList;

public class Crawler {
    static final int PORT = 80;
    static final int TIMEOUT = 5000;

    static HashSet<String> scan(URLDepthPair page) throws IOException {
        HashSet<String> allURL = new HashSet<>();

        Socket socket = new Socket(page.getHost(), PORT);
        socket.setSoTimeout(TIMEOUT);

        PrintWriter post = new PrintWriter(socket.getOutputStream(), true);
        requestForm(post, page);

        BufferedReader get = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // URL parsing.
        String partPageCode;
        HashSet<String> URLsInLine;
        while ((partPageCode = get.readLine()) != null) {
            URLsInLine = URLDepthPair.urlDetermination(partPageCode);
            if (URLsInLine != null) {
                for (String URL : URLsInLine) {
                    allURL.add(URL);
                }
            }
        }

        socket.close();
        post.close();
        get.close();

        return allURL;
    }

    // HTTP request form.
    static void requestForm(PrintWriter post, URLDepthPair page) throws MalformedURLException {
        post.println("GET " + page.getPath() + " HTTP/1.1");
        post.println("Host: " + page.getHost());
        post.println("Connection: close");
        post.println();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        /**
         *  "http://" - format.
         * for example:
         *  http://www.all-met.narod.ru/
         *  http://www.rgrafika.ru/
         **/
        String URL = null;
        int depth = 0;
        int countThreads = 0;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter the URL: ");
        URL = reader.readLine();

        System.out.print("Enter the depth: ");
        try {
            depth = Integer.parseInt(reader.readLine());
            if (depth < 0)
                throw new NumberFormatException("[Incorrect depth] - entered depth < 0");
        } catch (NumberFormatException exception) {
            throw new NumberFormatException("[Incorrect depth] - usage: java Crawler <URL> <depth> <threads>");
        }

        URLPool pool;

        try {
            pool = new URLPool(new URLDepthPair<>(URL, 0), depth);
        } catch (MalformedURLException exception) {
            throw new MalformedURLException("[Incorrect URL] - usage: java Crawler <URL> <depth> <threads>");
        }

        System.out.print("Enter the number of threads: ");
        try {
            countThreads = Integer.parseInt(reader.readLine());
            if (countThreads < 1)
                throw new NumberFormatException("[Incorrect count of thread] - entered thread < 1");
        } catch (NumberFormatException exception) {
            throw new NumberFormatException("[Incorrect threads] - usage: java Crawler <URL> <depth> <threads>");
        }


        int defaultActiveThread = Thread.activeCount();
        LinkedList<Thread> threads = new LinkedList<>();

        while (pool.getWaitingThreads() != countThreads) {
            if (Thread.activeCount() - defaultActiveThread < countThreads){
                CrawlerTask crawler = new CrawlerTask(pool);
                Thread thread = new Thread(crawler);
                thread.start();

                threads.add(thread);
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException exception) {
                    System.out.println("Thread: " + exception.getMessage());
                }
            }
        }

        for (Thread element : threads) {
            element.interrupt();
        }

        pool.getSites();
    }
}