package bzh.duncan.http.request;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequestParser {

    public static HttpRequest parseRequest(BufferedReader reader) throws IOException {
        HttpRequest request = new HttpRequest();

        // Parse request line
        String requestLine = reader.readLine();
        if (requestLine == null || requestLine.trim().isEmpty()) {
            throw new IOException("Invalid request line");
        }

        System.out.println("Request: " + requestLine);
        parseRequestLine(request, requestLine);

        // Parse headers
        parseHeaders(request, reader);

        // Parse body if present (pour les requêtes POST/PUT)
        parseBody(request, reader);

        return request;
    }

    private static void parseRequestLine(HttpRequest request, String requestLine) {
        String[] parts = requestLine.split(" ");
        if (parts.length >= 3) {
            request.setMethod(parts[0]);
            request.setPath(parts[1]);
            request.setHttpVersion(parts[2]);
        }
    }

    private static void parseHeaders(HttpRequest request, BufferedReader reader) throws IOException {
        String headerLine;
        while ((headerLine = reader.readLine()) != null && !headerLine.isEmpty()) {
            System.out.println("Header: " + headerLine);

            int colonIndex = headerLine.indexOf(':');
            if (colonIndex > 0) {
                String headerName = headerLine.substring(0, colonIndex).trim();
                String headerValue = headerLine.substring(colonIndex + 1).trim();
                request.addHeader(headerName, headerValue);
            }
        }
    }

    private static void parseBody(HttpRequest request, BufferedReader reader) throws IOException {
        int contentLength = request.getContentLength();
        if (contentLength > 0) {
            char[] bodyChars = new char[contentLength];
            reader.read(bodyChars, 0, contentLength);
            request.setBody(new String(bodyChars));
        }
    }
}
