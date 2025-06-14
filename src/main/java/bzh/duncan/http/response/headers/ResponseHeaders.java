package bzh.duncan.http.response.headers;

import bzh.duncan.Constants;
import org.apache.commons.lang3.StringUtils;

public class ResponseHeaders {
    private ResponseContentType contentType;
    private ResponseContentLength contentLength;
    private static final String CRLF = Constants.CRLF;

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
        if (contentType != null && StringUtils.isNotEmpty(contentType.getContentType())) {
            sb.append("Content-Type: ").append(contentType.toString());
        }
        if (contentLength != null && StringUtils.isNotEmpty(contentLength.toString())) {
            sb.append("Content-Length: ").append(contentLength.toString());
        }
        sb.append(CRLF);
        return sb.toString();
    }
}
