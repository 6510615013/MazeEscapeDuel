package main;

import java.net.*;
import java.io.*;
import javax.swing.SwingUtilities;
import java.awt.Color;

public class Server implements Runnable {
    private final int port;

    public Server(int port) {
        this.port = port;
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Waiting for client...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected!");

            NetworkHandler.setSocket(clientSocket);

            long seed = System.currentTimeMillis();  // สร้าง seed
            NetworkHandler.send("SEED:" + seed);     // ส่งให้ client

            Maze maze = new Maze(21, 21, seed);

            SwingUtilities.invokeLater(() -> {
                Player player = new Player("Host", 1, 1, Color.BLUE);
                Player opponent = new Player("Opponent", 19, 1, Color.RED);
                GameGUI gui = new GameGUI(maze, player, opponent);
                GameManager manager = new GameManager(MainFrame.getInstance(), player, maze, gui);
                gui.setManager(manager); // ⭐️ fix NPE

                MainFrame frame = MainFrame.getInstance();
                frame.setContentPane(gui);
                frame.pack();
                frame.revalidate();
                gui.requestFocusInWindow();

                // รับตำแหน่งจาก client
                new Thread(() -> {
                    try {
                        while (true) {
                            String msg = NetworkHandler.receive();
                            if (msg.startsWith("POS:")) {
                                String[] parts = msg.substring(4).split(",");
                                int x = Integer.parseInt(parts[0]);
                                int y = Integer.parseInt(parts[1]);
                                manager.updateOpponent(x, y);
                            } else if (msg.startsWith("Win:")) {
                                String winner = msg.substring(4);
                                manager.handleWin(winner);    
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}