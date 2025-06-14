package bzh.duncan.routing;

import bzh.duncan.http.request.HttpRequest;
import bzh.duncan.http.response.HttpResponse;
import bzh.duncan.http.response.ResponseBody;
import bzh.duncan.http.response.StatusLine;
import bzh.duncan.http.response.headers.ResponseContentLength;
import bzh.duncan.http.response.headers.ResponseContentType;
import bzh.duncan.http.response.headers.ResponseHeaders;

public class HandleEcho {

    public static HttpResponse handleGet(HttpRequest request) {
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
        return null;
    }

}
