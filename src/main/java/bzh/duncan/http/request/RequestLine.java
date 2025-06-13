package bzh.duncan.http.request;

import bzh.duncan.http.HttpMethodEnum;

public class RequestLine {
    private HttpMethodEnum httpMethod = HttpMethodEnum.GET; // "GET"
    private String requestTarget = "index.html"; // "index.html"
    private String httpVersion = "HTTP/1.1";
    private final String CRLF = "\r\n";

    public RequestLine(HttpMethodEnum httpMethod, String requestTarget, String httpVersion) {
        this.httpMethod = httpMethod;
        this.requestTarget = requestTarget;
        this.httpVersion = httpVersion;
    }

    public HttpMethodEnum getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethodEnum httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getRequestTarget() {
        return requestTarget;
    }

    public void setRequestTarget(String requestTarget) {
        this.requestTarget = requestTarget;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public String getCRLF() {
        return CRLF;
    }
}
