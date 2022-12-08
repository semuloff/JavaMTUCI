package Labs.Lab_7.src;

import java.net.*;
import java.util.HashSet;
import java.util.LinkedList;

public class URLDepthPair<E, N> {
    private E URL;
    private N depth;

    public static final String URL_PREFIX = "<a href=\"";
    public static final String PROTOCOL_PREFIX = "http://";

    // assert: URL format: http://anyLink.domen/..., depth >= 0.
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

    // TODO: Проверка URL-ссылки на валидность.
    static void validator(String URL) {
    }

    // Determination of links of the required format from a piece of HTML code.
    static HashSet<String> urlDetermination(String htmlPiece) {
        // assert: format = <a href="http://anyLink.domen/..."
        HashSet<String> urlSet = new HashSet<>();
        StringBuilder builderString = new StringBuilder();

        if (htmlPiece.contains(URL_PREFIX + PROTOCOL_PREFIX)) {
            // Index of the beginning of the URL link.
            int indexURL = htmlPiece.indexOf(PROTOCOL_PREFIX);

            // Look for raw references in the string until they run out.
            while (indexURL != -1) {
                for (int indexLine = indexURL; true; indexLine++) {
                    if (htmlPiece.charAt(indexLine) == '"') {
                        htmlPiece = htmlPiece.replace(URL_PREFIX + builderString.toString(), "");
                        urlSet.add(builderString.toString());
                        builderString.setLength(0);

                        indexURL = htmlPiece.indexOf(URL_PREFIX);
                        indexURL += (indexURL == -1) ? 0 : URL_PREFIX.length();
                        break;
                    }

                    builderString.append(htmlPiece.charAt(indexLine));
                }
            }
        }

        return urlSet.size() > 0 ? urlSet : null;
    }
}