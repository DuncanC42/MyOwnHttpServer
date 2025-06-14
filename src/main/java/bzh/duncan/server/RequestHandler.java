package bzh.duncan.server;

import bzh.duncan.http.request.HttpRequest;
import bzh.duncan.http.request.HttpRequestParser;
import bzh.duncan.http.response.HttpResponse;
import bzh.duncan.routing.RouteHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class RequestHandler implements Runnable {

    private Socket clientSocket;

    public RequestHandler(Socket socket) {
        this.clientSocket = socket;
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {

        RouteHandler routeHandler = new RouteHandler();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            HttpRequest httpRequest = HttpRequestParser.parseRequest(reader);

            System.out.println("Parsed request: " + httpRequest);

            HttpResponse httpResponse = routeHandler.handleRequest(httpRequest);

            byte[] responseBytes = httpResponse.toByteArray();
            OutputStream outputStream = clientSocket.getOutputStream();
            outputStream.write(responseBytes);
            outputStream.flush();

        } catch (IOException e) {
            System.out.println("Error processing request: " + e.getMessage());
            // Send a 500 error response
            try {
                OutputStream outputStream = clientSocket.getOutputStream();
                String errorResponse = "HTTP/1.1 500 Internal Server Error\r\n\r\n";
                outputStream.write(errorResponse.getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
            } catch (IOException ex) {
                System.out.println("Error sending error response: " + ex.getMessage());
            }
        } finally {
            try {
                if (clientSocket != null && !clientSocket.isClosed()) {
                    clientSocket.close();
                    System.out.println("Connection closed");
                }
            } catch (IOException e) {
                System.out.println("Error closing socket: " + e.getMessage());
            }
        }
    }
}
