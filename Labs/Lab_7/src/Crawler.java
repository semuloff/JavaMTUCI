package Labs.Lab_7.src;

import java.io.*;
import java.net.*;
import java.util.LinkedList;

public class Crawler {
    public static final int PORT = 80;
    public static final int TIMEOUT = 5000;

    private Socket socket;
    private BufferedReader get;
    private PrintWriter post;
    private LinkedList<URLDepthPair> rawLinks;
    private LinkedList<URLDepthPair> processedLinks;

    public Crawler(String URL, int maxDepth) throws IOException {
        rawLinks = new LinkedList<>();
        processedLinks = new LinkedList<>();

        rawLinks.add(new URLDepthPair(URL, 0));

        scan(rawLinks.get(0));
    }

    private void scan(URLDepthPair page) throws IOException {
        try {
            socket = new Socket(page.getHost(), PORT);
            socket.setSoTimeout(TIMEOUT);

            post = new PrintWriter(socket.getOutputStream(), true);
            requestForm(post, page);

            get = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String partPageCode;
            while ((partPageCode = get.readLine()) != null) {
//                StringBuilder builder = new StringBuilder();
                System.out.println(partPageCode);

            }
        } catch (Exception e) {
            System.out.println("Ooop-s: " + e.getMessage());
        } finally {
            socket.close();
            post.close();
            get.close();

            System.out.println("Socket was closed!");
        }
    }

    private void requestForm(PrintWriter post, URLDepthPair page) throws MalformedURLException {
        this.post.println("GET " + page.getPath() + " HTTP/1.1");
        this.post.println("Host: " + page.getHost());
        this.post.println("Connection: close");
        this.post.println();
    }

    public static void main(String[] args) throws IOException {
        /**
         * http:// - format.
         * for example - http://www.all-met.narod.ru/
         **/
//        BufferedReader readerURL = new BufferedReader(new InputStreamReader(System.in));
//
//        System.out.println("Enter the URL: ");
//        final String URL = readerURL.readLine();
        final String URL = "http://www.all-met.narod.ru/";
        final int depth = 5;

        Crawler Scanner = new Crawler(URL, depth);
    }
}