package bzh.duncan;

public enum HttpMethodEnum {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    HEAD("HEAD"),
    OPTIONS("OPTIONS"),
    PATCH("PATCH");

    private final String method;

    HttpMethodEnum(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    @Override
    public String toString() {
        return method;
    }
}
