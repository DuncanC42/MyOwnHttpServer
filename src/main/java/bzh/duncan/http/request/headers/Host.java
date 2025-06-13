package bzh.duncan.http.request.headers;

import bzh.duncan.Constants;

public class Host {
    private String domainName = "localhost";
    private int port;

    public Host(String domainName, int port) {
        this.domainName = domainName;
        this.port = port;
    }

    @Override
    public String toString() {
        return this.domainName + ":" + this.port + Constants.CRLF;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
