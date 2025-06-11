package bzh.duncan;

public class ResponseContentType {
    private String contentType;
    private final String CRLF = Constants.CRLF;

    public ResponseContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return contentType + CRLF;
    }
}
