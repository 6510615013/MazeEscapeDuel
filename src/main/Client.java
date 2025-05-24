package main;
import java.net.*;
import java.io.*;

public class Client implements Runnable {
    private final String serverIP;
    private final int port;
    private final String name;

    public Client(String serverIP, int port, String name) {
        this.serverIP = serverIP;
        this.port = port;
        this.name = name;
    }

    public void run() {
        try {
            Socket socket = new Socket(serverIP, port);
            System.out.println("Connected to server!");
            NetworkHandler.setSocket(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}