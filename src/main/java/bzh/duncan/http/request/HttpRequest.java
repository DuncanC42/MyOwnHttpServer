package bzh.duncan.http.request;

import bzh.duncan.http.HttpMethodEnum;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private HttpMethodEnum method;
    private String path;
    private String httpVersion;
    private Map<String, String> headers;
    private String body;

    public HttpRequest() {
        this.headers = new HashMap<>();
    }

    public HttpRequest(HttpMethodEnum method, String path, String httpVersion) {
        this();
        this.method = method;
        this.path = path;
        this.httpVersion = httpVersion;
    }

    // Getters and setters
    public HttpMethodEnum getMethod() {
        return method;
    }

    public void setMethod(HttpMethodEnum method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void addHeader(String name, String value) {
        this.headers.put(name.toLowerCase(), value.trim());
    }

    public String getHeader(String name) {
        return this.headers.get(name.toLowerCase());
    }

    public String getUserAgent() {
        return getHeader("user-agent");
    }

    public String getContentType() {
        return getHeader("content-type");
    }

    public int getContentLength() {
        String length = getHeader("content-length");
        return length != null ? Integer.parseInt(length) : 0;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "method='" + method + '\'' +
                ", path='" + path + '\'' +
                ", httpVersion='" + httpVersion + '\'' +
                ", headers=" + headers +
                '}';
    }
}