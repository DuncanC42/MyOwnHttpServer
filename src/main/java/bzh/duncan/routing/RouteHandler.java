package bzh.duncan.routing;

import bzh.duncan.http.request.HttpRequest;
import bzh.duncan.http.response.HttpResponse;
import bzh.duncan.http.response.ResponseBody;
import bzh.duncan.http.response.StatusLine;
import bzh.duncan.http.response.headers.ResponseContentLength;
import bzh.duncan.http.response.headers.ResponseContentType;
import bzh.duncan.http.response.headers.ResponseHeaders;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class RouteHandler {
    private final Map<String, Route> routes = new HashMap<>();

    public RouteHandler() {
        routes.put("echo", this::handleEcho);
        routes.put("user-agent", this::handleUserAgent);
        routes.put("files", this::handleFile); // Route pour "/"
        routes.put("", this::handleRoot); // Route pour "/"
    }

    public HttpResponse handleRequest(HttpRequest request) {
        String path = request.getPath();

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
                new ResponseContentLength(0L)
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
                    new ResponseContentLength((long) echoContent.getBytes().length)
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
                new ResponseContentLength((long) userAgent.getBytes().length)
        );
        StatusLine statusLine = new StatusLine("HTTP/1.1", 200, "OK");
        return new HttpResponse(statusLine, responseHeaders, responseBody);
    }


    private HttpResponse handleFile(HttpRequest request) {
        System.out.println("request : " + request);
        String[] pathParts = request.getPath().split("/");
        if (pathParts.length > 2){
            String searchedFile = pathParts[2];
            try {
                File file = new File("tmp/" + searchedFile);
                if (file.exists()){
                    StatusLine statusLine = new StatusLine("HTTP/1.1", 200, "OK");
                    ResponseHeaders responseHeaders = new ResponseHeaders(
                            new ResponseContentType("application/octet-stream"),
                            new ResponseContentLength(file.length())
                    );
                    ResponseBody responseBody = new ResponseBody(FileUtils.readFileToString(file, StandardCharsets.UTF_8));
                    return new HttpResponse(statusLine, responseHeaders, responseBody);
                }
                else {
                    StatusLine statusLine = new StatusLine("HTTP/1.1", 404, "NOT FOUND");
                    return new HttpResponse(statusLine, new ResponseHeaders());
                }

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    private HttpResponse handleNotFound() {
        StatusLine statusLine = new StatusLine("HTTP/1.1", 404, "Not Found");
        ResponseHeaders responseHeaders = new ResponseHeaders(
                new ResponseContentType("text/plain"),
                new ResponseContentLength(0L)
        );
        return new HttpResponse(statusLine, responseHeaders);
    }

    @FunctionalInterface
    private interface Route {
        HttpResponse handle(HttpRequest request);
    }
}