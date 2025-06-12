package bzh.duncan;

import java.util.HashMap;
import java.util.Map;

public class RouteHandler {
    private final Map<String, Route> routes = new HashMap<>();

    public RouteHandler() {
        // Register routes
        routes.put("echo", this::handleEcho);
        routes.put("user-agent", this::handleUserAgent);
        routes.put("", this::handleRoot); // Route pour "/"
    }

    public HttpResponse handleRequest(HttpRequest request) {
        String path = request.getPath();

        // Handle root path
        if ("/".equals(path)) {
            return handleRoot(request);
        }

        String[] pathParts = path.split("/");
        if (pathParts.length > 1) {
            String routeKey = pathParts[1];
            Route route = routes.get(routeKey);
            if (route != null) {
                return route.handle(request);
            }
        }
        return handleNotFound();
    }

    private HttpResponse handleRoot(HttpRequest request) {
        StatusLine statusLine = new StatusLine("HTTP/1.1", 200, "OK");
        ResponseHeaders responseHeaders = new ResponseHeaders(
                new ResponseContentType("text/plain"),
                new ResponseContentLength(0)
        );
        ResponseBody responseBody = new ResponseBody("");
        return new HttpResponse(statusLine, responseHeaders, responseBody);
    }

    private HttpResponse handleEcho(HttpRequest request) {
        String[] pathParts = request.getPath().split("/");
        if (pathParts.length > 2) {
            String echoContent = pathParts[2];
            ResponseBody responseBody = new ResponseBody(echoContent);
            ResponseHeaders responseHeaders = new ResponseHeaders(
                    new ResponseContentType("text/plain"),
                    new ResponseContentLength(echoContent.getBytes().length)
            );
            StatusLine statusLine = new StatusLine("HTTP/1.1", 200, "OK");
            return new HttpResponse(statusLine, responseHeaders, responseBody);
        }
        return handleNotFound();
    }

    private HttpResponse handleUserAgent(HttpRequest request) {
        String userAgent = request.getUserAgent();
        if (userAgent == null) {
            userAgent = "Unknown";
        }

        ResponseBody responseBody = new ResponseBody(userAgent);
        ResponseHeaders responseHeaders = new ResponseHeaders(
                new ResponseContentType("text/plain"),
                new ResponseContentLength(userAgent.getBytes().length)
        );
        StatusLine statusLine = new StatusLine("HTTP/1.1", 200, "OK");
        return new HttpResponse(statusLine, responseHeaders, responseBody);
    }

    private HttpResponse handleNotFound() {
        StatusLine statusLine = new StatusLine("HTTP/1.1", 404, "Not Found");
        ResponseHeaders responseHeaders = new ResponseHeaders(
                new ResponseContentType("text/plain"),
                new ResponseContentLength(0)
        );
        ResponseBody responseBody = new ResponseBody("");
        return new HttpResponse(statusLine, responseHeaders, responseBody);
    }

    @FunctionalInterface
    private interface Route {
        HttpResponse handle(HttpRequest request);
    }
}