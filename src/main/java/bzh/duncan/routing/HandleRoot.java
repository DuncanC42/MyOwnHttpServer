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

public class HandleRoot {

    static HttpResponse handleGet(HttpRequest request) {
        StatusLine statusLine = new StatusLine("HTTP/1.1", 200, "OK");

        ResponseHeaders responseHeaders = new ResponseHeaders(
                new ResponseContentType("text/plain"),
                new ResponseContentLength(0L)
        );

        if (Constants.containsGzip.test(request.getHeader("Accept-Encoding"))) {

            responseHeaders = new ResponseHeaders(
                    new ResponseContentType("text/plain"),
                    new ResponseContentLength(0L),
                    new ResponseContentEncoding("gzip")

            );
        }
        ResponseBody responseBody = new ResponseBody("");
        return new HttpResponse(statusLine, responseHeaders, responseBody);
    }

}
