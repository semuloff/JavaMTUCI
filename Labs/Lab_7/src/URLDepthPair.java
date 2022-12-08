package Labs.Lab_7.src;

import java.net.*;

public class URLDepthPair<E, N> {
    private E URL;
    private N depth;

    public static final String URL_PREFIX = "<a href=\"http://";

    URLDepthPair(E URL, N depth) {
        this.URL = URL;
        this.depth = depth;
    }

    N getDepth() {
        return depth;
    }

    E getURL() {
        return URL;
    }

    String getHost() throws MalformedURLException {
        java.net.URL url = new URL((String) URL);

        return url.getHost();
    }

    String getPath() throws MalformedURLException {
        java.net.URL url = new URL((String) URL);

        return url.getPath();
    }
}