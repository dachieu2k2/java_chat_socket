import java.io.Console;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class WriteThread extends Thread {
    private PrintWriter writer;
    private Socket socket;
    private ChatClient client;

    public WriteThread(Socket socket, ChatClient client) {
        this.socket = socket;
        this.client = client;
        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException e) {
            System.out.println("error getting output stream: " + e.getMessage());
            e.getStackTrace();
        }
    }

    public void run() {
        Console console = System.console();
        String username = console.readLine("\nEnter your name: ");
        client.setUsername(username);
        writer.println(username);
        String text;
        do {
            text = console.readLine("[" + username + "]: ");
            writer.println(text);
        } while (!text.equals("bye"));
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Error writing to server: " + e.getMessage());
        }
    }

}
