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
import bzh.duncan.util.GzipUtil;

import java.io.IOException;

public class HandleEcho {

    public static HttpResponse handleGet(HttpRequest request) throws IOException {
        String[] pathParts = request.getPath().split("/");
        if (pathParts.length > 2) {
            String echoContent = pathParts[2];

            if (Constants.containsGzip.test(request.getHeader("Accept-Encoding"))) {
                
                byte[] compressedContent = GzipUtil.compress(echoContent);

                ResponseBody responseBody = new ResponseBody(compressedContent);
                ResponseHeaders responseHeaders = new ResponseHeaders(
                        new ResponseContentType("text/plain"),
                        new ResponseContentLength((long) compressedContent.length),
                        new ResponseContentEncoding("gzip")
                );

                StatusLine statusLine = new StatusLine("HTTP/1.1", 200, "OK");
                return new HttpResponse(statusLine, responseHeaders, responseBody);
            } else {
                // Pas de compression
                ResponseBody responseBody = new ResponseBody(echoContent);
                ResponseHeaders responseHeaders = new ResponseHeaders(
                        new ResponseContentType("text/plain"),
                        new ResponseContentLength((long) echoContent.getBytes().length)
                );

                StatusLine statusLine = new StatusLine("HTTP/1.1", 200, "OK");
                return new HttpResponse(statusLine, responseHeaders, responseBody);
            }
        }
        return null;
    }

}
