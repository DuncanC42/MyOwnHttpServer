package bzh.duncan.http.response.headers;

import bzh.duncan.Constants;

public class ResponseContentLength {
    private Long responseBodyLength;
    private final String CRLF = Constants.CRLF;

    public ResponseContentLength(Long responseBodyLength) {
        this.responseBodyLength = responseBodyLength;
    }

    public Long getResponseBodyLength() {
        return responseBodyLength;
    }

    public void setResponseBodyLength(Long responseBodyLength) {
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
