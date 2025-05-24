package main;
import java.net.*;
import java.io.*;

public class NetworkHandler {
    private static Socket socket;
    static BufferedReader in;
    private static PrintWriter out;

    public static void setSocket(Socket s) throws IOException {
        socket = s;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public static void send(String message) {
        out.println(message);
    }

    public static String receive() throws IOException {
        return in.readLine();
    }
}