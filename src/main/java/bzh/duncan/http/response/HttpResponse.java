package bzh.duncan.http.response;

import bzh.duncan.http.response.headers.ResponseHeaders;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HttpResponse {
    private StatusLine statusLine;
    private ResponseHeaders header;
    private ResponseBody responseBody;

    public HttpResponse(StatusLine statusLine, ResponseHeaders header, ResponseBody responseBody) {
        this.statusLine = statusLine;
        this.header = header;
        this.responseBody = responseBody;
    }

    public HttpResponse(StatusLine statusLine, ResponseHeaders header) {
        this.statusLine = statusLine;
        this.header = header;
    }

    public HttpResponse(StatusLine statusLine) {
        this.statusLine = statusLine;
    }

    public StatusLine getStatusLine() {
        return statusLine;
    }

    public void setStatusLine(StatusLine statusLine) {
        this.statusLine = statusLine;
    }

    public ResponseHeaders getHeader() {
        return header;
    }

    public void setHeader(ResponseHeaders header) {
        this.header = header;
    }

    public ResponseBody getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }


    /**
     * Convertit la réponse en byte array pour l'envoi
     * Cette méthode gère correctement les données binaires (gzip)
     */
    public byte[] toByteArray() throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            // Écrire la status line
            baos.write(statusLine.toString().getBytes(StandardCharsets.UTF_8));

            // Écrire les headers
            if (header != null && StringUtils.isNotEmpty(header.toString())) {
                baos.write(header.toString().getBytes(StandardCharsets.UTF_8));
            }

            // Écrire le body
            if (responseBody != null) {
                if (responseBody.isBinary() && responseBody.getBinaryContent() != null) {
                    // Données binaires (gzip) - écrire directement
                    baos.write(responseBody.getBinaryContent());
                } else if (!responseBody.isBinary() && !responseBody.getBodyContent().isEmpty()) {
                    // Données texte
                    baos.write(responseBody.getBodyContent().getBytes(StandardCharsets.UTF_8));
                }
            }

            return baos.toByteArray();
        }
    }

    @Override
    public String toString() {
        StringBuilder response = new StringBuilder();

        response.append(statusLine.toString());

        if (header != null && StringUtils.isNotEmpty(header.toString())){
            response.append(header.toString());
        }

        if (responseBody != null && !responseBody.getBodyContent().isEmpty()) {
            response.append(responseBody.getBodyContent());
        }

        return response.toString();
    }
}