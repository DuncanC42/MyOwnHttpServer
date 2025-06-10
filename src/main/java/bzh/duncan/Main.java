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

            // Boucle pour accepter plusieurs connexions
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("accepted new connection");

                // Read the HTTP request
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String requestLine = reader.readLine(); // Read the first line of the request
                System.out.println("Request: " + requestLine);

                // Extract the path from the request line
                String[] requestParts = requestLine.split(" ");
                String path = requestParts[1]; // The path is the second part of the request line

                // Determine the response based on the path
                StatusLine statusLine;

                if (path.equals("/")) {
                    statusLine = new StatusLine("HTTP/1.1", 200, "OK");
                } else {
                    statusLine = new StatusLine("HTTP/1.1", 404, "Not Found");
                }

                // Créer la réponse HTTP
                Header header = new Header();
                ResponseBody responseBody = new ResponseBody(""); // Corps vide

                HttpResponse httpResponse = new HttpResponse(statusLine, header, responseBody);

                // Envoyer la réponse au client
                OutputStream outputStream = clientSocket.getOutputStream();
                outputStream.write(httpResponse.toString().getBytes());
                outputStream.flush();

                // Fermer la connexion
                clientSocket.close();
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}