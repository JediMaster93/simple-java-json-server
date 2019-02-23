import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private PersonList personsList;

    public Server() {
        personsList = new PersonList();
    }

    public static void main(String[] args) throws IOException {
        Server s = new Server();
        s.startServer();

    }

    private void startServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(4444);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

            Thread t = new Thread(new RequestHandler(out, in, personsList));
            t.start();

        }
    }
}


