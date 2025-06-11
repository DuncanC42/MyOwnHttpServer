package bzh.duncan;

public class ResponseHeaders {
    private ResponseContentType contentType;
    private ResponseContentLength contentLength;
    private final String CRLF = Constants.CRLF;

    public ResponseHeaders() {
    }

    public ResponseHeaders(ResponseContentType contentType, ResponseContentLength contentLength) {
        this.contentType = contentType;
        this.contentLength = contentLength;
    }

    public ResponseContentType getContentType() {
        return contentType;
    }

    public void setContentType(ResponseContentType contentType) {
        this.contentType = contentType;
    }

    public ResponseContentLength getContentLength() {
        return contentLength;
    }

    public void setContentLength(ResponseContentLength contentLength) {
        this.contentLength = contentLength;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("Content-Type: ").append(contentType);
        sb.append("Content-Length: ").append(contentLength);
        sb.append(Constants.CRLF);
        return sb.toString();
    }
}
