package bzh.duncan;

import bzh.duncan.server.RequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class Main {
    private static final int THREAD_POOL_SIZE = 50; // Augmenté pour gérer plus de connexions simultanées
    private static final int SERVER_PORT = 4221;
    private static final int SOCKET_BACKLOG = 100; // Taille de la queue des connexions en attente

    public static void main(String[] args) {
        System.out.println("Starting HTTP server on port " + SERVER_PORT);

        // Créer un pool de threads avec gestion des rejets
        ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        try {
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT, SOCKET_BACKLOG);
            serverSocket.setReuseAddress(true);

            // Optimisations pour les performances
            serverSocket.setPerformancePreferences(0, 1, 2); // Latence > Bande passante > Temps de connexion

            System.out.println("Server started successfully with " + THREAD_POOL_SIZE + " threads");
            System.out.println("Socket backlog: " + SOCKET_BACKLOG);

            // Compteur de connexions pour monitoring
            int connectionCount = 0;

            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    connectionCount++;
                    System.out.println("Connection #" + connectionCount + " accepted from " +
                            clientSocket.getRemoteSocketAddress());

                    // Soumettre la tâche au pool de threads avec gestion d'erreur
                    RequestHandler handler = new RequestHandler(clientSocket);
                    threadPool.submit(handler);

                } catch (RejectedExecutionException e) {
                    System.out.println("Thread pool full, rejecting connection: " + e.getMessage());
                } catch (IOException e) {
                    System.out.println("Error accepting connection: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to start server: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Arrêter proprement le pool de threads
            System.out.println("Shutting down server...");
            threadPool.shutdown();
        }
    }
}