package bzh.duncan;

import java.util.function.Predicate;

public class Constants {

    public static final String CRLF = "\r\n";

    public static final Predicate<String> containsGzip = header ->
            header != null && header.split(",").length > 0 && header.contains("gzip");

    public Constants() {
    }

    public String getCRLF() {
        return CRLF;
    }
}
