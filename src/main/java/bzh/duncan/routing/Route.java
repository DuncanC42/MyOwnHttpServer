package bzh.duncan.routing;

import bzh.duncan.http.request.HttpRequest;
import bzh.duncan.http.response.HttpResponse;

@FunctionalInterface
public interface Route {
    HttpResponse handle(HttpRequest request);
}