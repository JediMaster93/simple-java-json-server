import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {
    private PersonList personsList;

    public Server() {
        personsList = new PersonList();
    }
    public static void   main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Server s = new Server();
        s.startServer();

    }

    private  void startServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(4444);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

            Thread t = new Thread(new RequestHandler(out, in,personsList));
            t.start();

        }
    }
}


