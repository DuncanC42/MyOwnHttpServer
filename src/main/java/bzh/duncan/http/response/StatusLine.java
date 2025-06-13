package bzh.duncan.http.response;

public class StatusLine {
    private String httpVersion = "HTTP/1.1"; // Valeur par défaut : HTTP/1.1
    private int statusCode = 200;             // Valeur par défaut : 200
    private String reasonPhrase = "OK";       // Valeur par défaut : OK
    private final String CRLF = "\r\n";      // Constante

    public StatusLine() {
    }

    public StatusLine(String httpVersion, int statusCode, String reasonPhrase) {
        this.httpVersion = httpVersion;
        this.statusCode = statusCode;
        this.reasonPhrase = reasonPhrase;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public void setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }

    public String getCRLF() {
        return CRLF;
    }

    @Override
    public String toString() {
        return this.httpVersion + " " + this.statusCode + " " + this.reasonPhrase + CRLF;
    }
}