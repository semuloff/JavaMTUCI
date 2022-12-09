package Labs.Lab_7.src;

import java.io.*;
import java.net.*;
import java.util.HashSet;
import java.util.LinkedList;

public class Crawler {
    static LinkedList<URLDepthPair> rawLinks;
    static LinkedList<URLDepthPair> processedLinks;
    static final int PORT = 80;
    static final int TIMEOUT = 5000;

    private int maxDepth;
    private Socket socket;
    private BufferedReader get;
    private PrintWriter post;

    public Crawler(int maxDepth) throws IOException {
        this.maxDepth = maxDepth;

        // Iterate until there are no raw references left.
        while (rawLinks.size() != 0) {
            final URLDepthPair pair = rawLinks.get(0);

            scan(pair);

            rawLinks.remove(0);
            processedLinks.add(pair);
        }

        getSites();
    }

    private void scan(URLDepthPair page) throws IOException {
        if (page.getDepth() < maxDepth) {
            try {
                socket = new Socket(page.getHost(), PORT);
                socket.setSoTimeout(TIMEOUT);

                post = new PrintWriter(socket.getOutputStream(), true);
                requestForm(page);

                get = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String partPageCode;
                HashSet<String> URL;

                while ((partPageCode = get.readLine()) != null) {
                    URL = URLDepthPair.urlDetermination(partPageCode);
//                    System.out.println(partPageCode);
                    if (URL != null)
                        addRawLinks(URL, page.getDepth() + 1);
                }
            } catch (Exception e) {
                System.out.println("Ooop-s: " + e.getMessage());
            } finally {
                socket.close();
                post.close();
                get.close();
            }
        }
    }

    // Adds ready unvisited links to array for unvisited links.
    private void addRawLinks(HashSet<String> array, int depth) throws MalformedURLException {
        for (String URL : array) {
            rawLinks.add(new URLDepthPair(URL, depth));
        }
    }

    // HTTP request form.
    private void requestForm(URLDepthPair page) throws MalformedURLException {
        post.println("GET " + page.getPath() + " HTTP/1.1");
        post.println("Host: " + page.getHost());
        post.println("Connection: close");
        post.println();
    }

    // Output processed links with respect to depth.
    private static void getSites() {
        for (URLDepthPair pair : processedLinks) {
            System.out.println(pair);
        }
    }

    public static void main(String[] args) throws IOException {
        /**
         * "http://" - format.
         * for example:
         *  http://www.all-met.narod.ru/
         *  http://www.rgrafika.ru/
         **/
        rawLinks = new LinkedList<>();
        processedLinks = new LinkedList<>();

        String URL = null;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the URL: ");
        URL = reader.readLine();

        try {
            rawLinks.add(new URLDepthPair(URL, 0));
        } catch (MalformedURLException exception) {
            throw new MalformedURLException("usage: java Crawler <URL> <depth>");
        }

        int depth = 0;

        System.out.println("Enter the depth: ");
        try {
            depth = Integer.parseInt(reader.readLine());
            if (depth < 0)
                throw new MalformedURLException("Incorrect depth: depth < 0");
        } catch (NumberFormatException exception) {
            throw new NumberFormatException("usage: java Crawler <URL> <depth>");
        }

        System.out.println("\nWorks...");

        Crawler Scanner = new Crawler(depth);
    }
}