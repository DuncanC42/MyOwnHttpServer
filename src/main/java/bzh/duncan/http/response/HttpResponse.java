package bzh.duncan.http.response;

import bzh.duncan.http.response.headers.ResponseHeaders;

public class HttpResponse {
    private StatusLine statusLine;
    private ResponseHeaders header;
    private ResponseBody responseBody;

    public HttpResponse(StatusLine statusLine, ResponseHeaders header, ResponseBody responseBody) {
        this.statusLine = statusLine;
        this.header = header;
        this.responseBody = responseBody;
    }

    public HttpResponse(StatusLine statusLine, ResponseHeaders header) {
        this.statusLine = statusLine;
        this.header = header;
    }

    public StatusLine getStatusLine() {
        return statusLine;
    }

    public void setStatusLine(StatusLine statusLine) {
        this.statusLine = statusLine;
    }

    public ResponseHeaders getHeader() {
        return header;
    }

    public void setHeader(ResponseHeaders header) {
        this.header = header;
    }

    public ResponseBody getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    @Override
    public String toString() {
        StringBuilder response = new StringBuilder();

        response.append(statusLine.toString());

        response.append(header.toString());

        if (responseBody != null && !responseBody.getBodyContent().isEmpty()) {
            response.append(responseBody.getBodyContent());
        }

        return response.toString();
    }
}