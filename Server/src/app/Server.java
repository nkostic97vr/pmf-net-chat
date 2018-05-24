package app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class Server {
    private final int port;

    final ArrayList<ServerWorker> workers;

    Server(int port) {
        this.port = port;
        workers = new ArrayList<>();
    }

    void run() {
        try {
            boolean working = true;
            ServerSocket serverSocket = new ServerSocket(port);

            while (working) {
                // neprekidno osluskujemo ima li novih konekcija
                System.out.println("Waiting for connection...");
                Socket clientSocket = serverSocket.accept();
                // za svakog klijenta pravimo novi thread
                ServerWorker worker = new ServerWorker(this, clientSocket);
                worker.start();
                workers.add(worker);
            }

            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
