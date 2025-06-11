package bzh.duncan;

public class ResponseContentLength {
    private int responseBodyLength;
    private final String CRLF = Constants.CRLF;

    public ResponseContentLength(int responseBodyLength) {
        this.responseBodyLength = responseBodyLength;
    }

    public int getResponseBodyLength() {
        return responseBodyLength;
    }

    public void setResponseBodyLength(int responseBodyLength) {
        this.responseBodyLength = responseBodyLength;
    }

    public String getCRLF() {
        return CRLF;
    }

    @Override
    public String toString() {
        return responseBodyLength + CRLF;
    }
}
