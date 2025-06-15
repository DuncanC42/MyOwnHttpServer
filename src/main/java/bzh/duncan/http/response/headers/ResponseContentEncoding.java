package bzh.duncan.http.response.headers;

import bzh.duncan.Constants;

public class ResponseContentEncoding {
    private String encoding;
    private final String CRLF = Constants.CRLF;

    public ResponseContentEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    @Override
    public String toString() {
        return encoding + CRLF;
    }
}