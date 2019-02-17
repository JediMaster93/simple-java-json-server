import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private PersonList personsList;

    public Server() {
        personsList = new PersonList();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(4444);
        Server server = new Server();


        while (true) {
            Socket clientSocket = serverSocket.accept();
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

            server.handleRequest(in, out);

            in.close();
            out.close();
            clientSocket.close();

        }

    }

    private void handleRequest(ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException {
        String inputString = (String) in.readObject();
        if (Utils.isValidJson(inputString)) {

            Gson gson = new Gson();
            JsonObj json = gson.fromJson(inputString, JsonObj.class);
            if (json.getMethod().equals("add")) {
                this.personsList.append(json.person);
            }
        }
        System.out.println(personsList);
    }
}

