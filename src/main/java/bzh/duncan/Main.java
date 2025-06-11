package bzh.duncan;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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

                String[] pathContentList = path.split("/");

                ResponseHeaders responseHeaders = new ResponseHeaders();
                ResponseBody responseBody = new ResponseBody(""); // Corps vide

                if (pathContentList.length > 0){
                    statusLine = new StatusLine("HTTP/1.1", 200, "OK");
                    for (int i = 0; i < pathContentList.length; i++){
                        if (pathContentList[i].equals("echo")){
                            responseBody = new ResponseBody(pathContentList[i + 1]); // Corps vide
                            responseHeaders = new ResponseHeaders(new ResponseContentType("text/plain"), new ResponseContentLength(pathContentList[i + 1].getBytes().length));
                            break;
                        }
                    }
                }
                else {
                    statusLine = new StatusLine("HTTP/1.1", 404, "Not Found");
                }

                // Créer la réponse HTTP
                HttpResponse httpResponse = new HttpResponse(statusLine, responseHeaders, responseBody);

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