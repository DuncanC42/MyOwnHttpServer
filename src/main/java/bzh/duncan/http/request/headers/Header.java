package bzh.duncan.http.request.headers;

import bzh.duncan.Constants;

public class Header {
    private Host serversHost = new Host("localHost", 4221);
    private UserAgent clientsUserAgent = new UserAgent("curl", "7.64.1");
    private String accept = "*" + "/*" + Constants.CRLF;

    public Header() {
    }

    public Header(Host serversHost, UserAgent clientsUserAgent, String accept) {
        this.serversHost = serversHost;
        this.clientsUserAgent = clientsUserAgent;
        this.accept = accept;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("Host: ").append(serversHost);
        sb.append("User-Agent: ").append(clientsUserAgent);
        sb.append("Accept: ").append(accept);
        sb.append(Constants.CRLF);
        return sb.toString();
    }

    public Host getServersHost() {
        return serversHost;
    }

    public void setServersHost(Host serversHost) {
        this.serversHost = serversHost;
    }

    public UserAgent getClientsUserAgent() {
        return clientsUserAgent;
    }

    public void setClientsUserAgent(UserAgent clientsUserAgent) {
        this.clientsUserAgent = clientsUserAgent;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }
}