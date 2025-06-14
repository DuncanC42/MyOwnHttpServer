package bzh.duncan.routing;

import bzh.duncan.http.HttpMethodEnum;
import bzh.duncan.http.request.HttpRequest;
import bzh.duncan.http.response.HttpResponse;
import bzh.duncan.http.response.StatusLine;
import bzh.duncan.http.response.headers.ResponseContentLength;
import bzh.duncan.http.response.headers.ResponseContentType;
import bzh.duncan.http.response.headers.ResponseHeaders;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class RouteHandler {
    private final Map<RouteKey, Route> routes = new HashMap<>();

    public RouteHandler() {
        routes.put(new RouteKey(HttpMethodEnum.GET, ""), HandleRoot::handleGet);

        routes.put(new RouteKey(HttpMethodEnum.GET, "echo"), HandleEcho::handleGet);

        routes.put(new RouteKey(HttpMethodEnum.GET, "user-agent"), HandleUserAgent::handleGet);

        routes.put(new RouteKey(HttpMethodEnum.GET, "files"), HandleFile::handleGet);
        routes.put(new RouteKey(HttpMethodEnum.POST, "files"), HandleFile::handlePost);
    }

    public HttpResponse handleRequest(HttpRequest request) {
        String path = request.getPath();

        if (StringUtils.equals("/", path)) {
            RouteKey rootKey = new RouteKey(request.getMethod(), "");
            Route route = routes.get(rootKey);
            if (route != null) {
                return route.handle(request);
            }
        }

        String[] pathParts = path.split("/");
        if (pathParts.length > 1) {
            RouteKey routeKey = new RouteKey(request.getMethod(), pathParts[1]);

            Route route = routes.get(routeKey);
            if (route != null) {
               return route.handle(request);
            }
        }
        return handleNotFound();
    }


    private HttpResponse handleNotFound() {
        StatusLine statusLine = new StatusLine("HTTP/1.1", 404, "Not Found");
        ResponseHeaders responseHeaders = new ResponseHeaders(
                new ResponseContentType("text/plain"),
                new ResponseContentLength(0L)
        );
        return new HttpResponse(statusLine, responseHeaders);
    }


}