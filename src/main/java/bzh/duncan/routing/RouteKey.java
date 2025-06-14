package bzh.duncan.routing;

import bzh.duncan.http.HttpMethodEnum;

import java.util.Objects;


public class RouteKey {

    private HttpMethodEnum methodEnum;
    private String path;

    public RouteKey(HttpMethodEnum methodEnum, String path) {
        this.methodEnum = methodEnum;
        this.path = path;
    }

    public HttpMethodEnum getMethodEnum() {
        return methodEnum;
    }

    public void setMethodEnum(HttpMethodEnum methodEnum) {
        this.methodEnum = methodEnum;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouteKey routeKey = (RouteKey) o;
        return Objects.equals(path, routeKey.path) && methodEnum == routeKey.methodEnum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, methodEnum);
    }

    @Override
    public String toString() {
        return methodEnum + " " + path;
    }
}
