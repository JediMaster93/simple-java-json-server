import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = new Socket(host.getHostAddress(), 4444);
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

        String outData ="{\n" +
                "  \"method\": \"add\",\n" +
                "  \"person\": {\n" +
                "    \"name\": \"Jakov\",\n" +
                "    \"age\": \"25\"\n" +
                "  }\n" +
                "}";
        outputStream.writeObject(outData);

        //language=JSON
        outData ="{\n" +
                "  \"method\": \"get\"\n" +
                "}";
        outputStream.flush();
        Thread.sleep(1000);
        outputStream.writeObject(outData);
        outputStream.flush();
        String output = (String) inputStream.readObject();
        System.out.println(output);
        Thread.sleep(1000);
        //language=JSON
        outData ="{\n" +
                "  \"method\": \"delete\",\n" +
                "  \"idToDelete\": 1\n" +
                "}";

        outputStream.writeObject(outData);
        outputStream.flush();

        Thread.sleep(1000);
        //language=JSON
        outData ="{\n" +
                "  \"method\": \"get\"\n" +
                "}";
        outputStream.flush();
        Thread.sleep(1000);
        outputStream.writeObject(outData);
        outputStream.flush();
        output = (String) inputStream.readObject();
        System.out.println(output);


        outputStream.close();
        inputStream.close();
    }
}
