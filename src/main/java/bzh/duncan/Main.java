package bzh.duncan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        System.out.println("Logs from your program will appear here!");

        try {
            ServerSocket serverSocket = new ServerSocket(4221);
            serverSocket.setReuseAddress(true);
            RouteHandler routeHandler = new RouteHandler();

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted new connection");

                try {
                    // Parse the HTTP request
                    BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    HttpRequest httpRequest = HttpRequestParser.parseRequest(reader);

                    System.out.println("Parsed request: " + httpRequest);

                    // Handle the request
                    HttpResponse httpResponse = routeHandler.handleRequest(httpRequest);

                    // Send the response
                    OutputStream outputStream = clientSocket.getOutputStream();
                    outputStream.write(httpResponse.toString().getBytes());
                    outputStream.flush();

                } catch (IOException e) {
                    System.out.println("Error processing request: " + e.getMessage());
                    // Send a 500 error response
                    try {
                        OutputStream outputStream = clientSocket.getOutputStream();
                        String errorResponse = "HTTP/1.1 500 Internal Server Error\r\n\r\n";
                        outputStream.write(errorResponse.getBytes());
                        outputStream.flush();
                    } catch (IOException ex) {
                        System.out.println("Error sending error response: " + ex.getMessage());
                    }
                } finally {
                    clientSocket.close();
                }
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}