package bzh.duncan.http.request.headers;

import bzh.duncan.Constants;

public class UserAgent {
    private String productName; // e.g., "curl"
    private String version;     // e.g., "7.64.1"
    private final String CRLF = Constants.CRLF; // "\r\n"

    public UserAgent(String productName, String version) {
        this.productName = productName;
        this.version = version;
    }

    @Override
    public String toString() {
        return productName + "/" + version + CRLF;
    }

    // Getters and setters
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCRLF() {
        return CRLF;
    }
}