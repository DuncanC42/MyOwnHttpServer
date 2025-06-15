package bzh.duncan.routing;

import bzh.duncan.Constants;
import bzh.duncan.http.request.HttpRequest;
import bzh.duncan.http.response.HttpResponse;
import bzh.duncan.http.response.ResponseBody;
import bzh.duncan.http.response.StatusLine;
import bzh.duncan.http.response.headers.ResponseContentEncoding;
import bzh.duncan.http.response.headers.ResponseContentLength;
import bzh.duncan.http.response.headers.ResponseContentType;
import bzh.duncan.http.response.headers.ResponseHeaders;

public class HandleUserAgent {

    public static HttpResponse handleGet(HttpRequest request) {
        String userAgent = request.getUserAgent();
        if (userAgent == null) {
            userAgent = "Unknown";
        }

        ResponseBody responseBody = new ResponseBody(userAgent);

        ResponseHeaders responseHeaders = new ResponseHeaders(
                new ResponseContentType("text/plain"),
                new ResponseContentLength((long) userAgent.getBytes().length)
        );

        if (Constants.containsGzip.test(request.getHeader("Accept-Encoding"))) {
            responseHeaders = new ResponseHeaders(
                    new ResponseContentType("text/plain"),
                    new ResponseContentLength((long) userAgent.getBytes().length),
                    new ResponseContentEncoding("gzip")
            );
        }
        StatusLine statusLine = new StatusLine("HTTP/1.1", 200, "OK");
        return new HttpResponse(statusLine, responseHeaders, responseBody);
    }
}
