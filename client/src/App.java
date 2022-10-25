import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class App {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost", 4999);
        System.out.println("Hello, World!");

        // send data to server
        PrintWriter pr = new PrintWriter(s.getOutputStream());
        pr.println("Hello server");
        pr.flush();

        // receive data from server
        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);

        String str = bf.readLine();
        System.out.println("Server: " + str);
        s.close();
    }
}
