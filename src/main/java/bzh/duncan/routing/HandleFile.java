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

public class HandleFile {

    public static HttpResponse handleGet(HttpRequest request) {
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

    public static HttpResponse handlePost(HttpRequest request) {
        String[] pathParts = request.getPath().split("/");
        if (pathParts.length > 2){
            String fileToCreate = pathParts[2];
            try {
                File file = new File("tmp/" + fileToCreate);
                if (!file.exists()){
                    if(file.createNewFile()){
                        String content = request.getBody();
                        if (content != null && !content.isEmpty()) {
                            FileUtils.writeStringToFile(file, content, StandardCharsets.UTF_8);
                        }
                        return new HttpResponse(new StatusLine("HTTP/1.1", 201, "CREATED"));

                    }
                    else{

                        return new HttpResponse(
                                new StatusLine("HTTP/1.1", 500, "INTERNAL SERVER ERROR")
                        );

                    }

                }
                else {
                    StatusLine statusLine = new StatusLine("HTTP/1.1", 409, "CONFLICT");
                    return new HttpResponse(statusLine);
                }

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }
}
