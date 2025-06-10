package bzh.duncan;

public class HttpResponse {
    private StatusLine statusLine;
    private Header header;
    private ResponseBody responseBody;

    public HttpResponse(StatusLine statusLine, Header header, ResponseBody responseBody) {
        this.statusLine = statusLine;
        this.header = header;
        this.responseBody = responseBody;
    }

    public StatusLine getStatusLine() {
        return statusLine;
    }

    public void setStatusLine(StatusLine statusLine) {
        this.statusLine = statusLine;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
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

        // Status line
        response.append(statusLine.toString());

        // Headers (pour l'instant, juste un CRLF pour marquer la fin des headers)
        response.append(header.toString());

        // Response body (optionnel)
        if (responseBody != null && !responseBody.getBodyContent().isEmpty()) {
            response.append(responseBody.getBodyContent());
        }

        return response.toString();
    }
}