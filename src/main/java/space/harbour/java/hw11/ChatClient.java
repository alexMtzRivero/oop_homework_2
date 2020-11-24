package space.harbour.java.hw11;

//import java.io.;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {
    //TODO: DATA WITH
    // MESAGE:
    //USERNAME:
    //TIMESTAMP:
    //-- greet user when conected
    // -- non blocking implementation: recive mesages while typing

    public ChatClient(String name, String server, int port) {
        try (Socket socket = new Socket(server, port);
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {

            out.writeUTF(name);
            out.flush();

            System.out.println(in.readUTF());

            while (true) {
                System.out.print(">");
                out.writeUTF(console.readLine());
                out.flush();

                String s = in.readUTF();
                System.out.println("got input");
                System.out.println(s);

            }

        } catch (UnknownHostException e) {
            System.out.println("Server " + server + " not found");
        } catch (IOException e) {
            System.out.println("Lost connection to server " + server);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("write your username to connect:");
        String name = console.readLine();
        ChatClient client = new ChatClient(name, "127.0.0.1", 8008);
    }
}
