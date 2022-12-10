package Labs.Lab_7.src;

import java.net.*;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLDepthPair<E, N> {
    private E URL;
    private N depth;
    private java.net.URL urlFormat;

    static final String REGEX_PATTERN = "<a href=\"(http://www\\.[a-zA-Z0-9]+\\.[a-z]+" +
            "/[-a-zA-Z0-9+&@#/%=~._|]*|http://[a-zA-Z0-9]+\\.[a-z]+" +
            "/[-a-zA-Z0-9+&@#/%=~._|]*|https://www\\.[a-zA-Z0-9]+\\.[a-z]+" +
            "/[-a-zA-Z0-9+&@#/%=~._|]*|https://[a-zA-Z0-9]+\\.[a-z]+/[-a-zA-Z0-9+&@#/%=~._|]*)\"";
    static Pattern pattern = Pattern.compile(REGEX_PATTERN);

    URLDepthPair(E URL, N currentDepth) throws MalformedURLException {
        // assert: URL format: http://anyLink.domen/..., depth >= 0.
        this.URL = URL;
        this.depth = currentDepth;
        urlFormat = new java.net.URL((String) URL);
    }

    int getDepth() {
        return (int) depth;
    }

    String getURL() {
        return (String) URL;
    }

    String getHost() throws MalformedURLException {
        return urlFormat.getHost();
    }

    String getPath() throws MalformedURLException {
         return urlFormat.getPath();
    }

    // Determination of links of the required format from a piece of HTML code.
    static HashSet<String> urlDetermination(String htmlCode) {
        HashSet<String> urlSet = new HashSet<>();
        Matcher matcher = pattern.matcher(htmlCode);

        while (matcher.find()) {
            /**
             * assert: group(index):
             * 0 - with "<a href=...".
             * 1 - only url.
             **/
            urlSet.add(matcher.group(1));
        }

        return urlSet.size() > 0 ? urlSet : null;
    }

    @Override
    public String toString() {
        return "URL: " + getURL() + ", Depth: " + getDepth();
    }
}