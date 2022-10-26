import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {
    private String hostname;
    private int port;
    private String userName;

    public ChatClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public void execute() {
        try {
            Socket socket = new Socket(hostname, port);
            System.out.println("Connected to the chat server");
            new ReadThread(socket, this).start();
            new WriteThread(socket, this).start();

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }

    }

    void setUsername(String userName) {
        this.userName = userName;
    }

    String getUserName() {
        return this.userName;
    }

    public static void main(String[] args) {
        // when you run command "java ChatClient localhost 8989"
        // if (args.length < 2)
        // return;
        // String hostname = args[0];
        // int port = Integer.parseInt(args[1]);

        // ChatClient client = new ChatClient(hostname, port);

        // you will start on port 8989
        ChatClient client = new ChatClient("localhost", 8989);

        client.execute();
    }
}
